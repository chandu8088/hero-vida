package com.heromotocorp.vida.core.service.impl;

import java.io.IOException;
import java.util.Objects;

import javax.jcr.Session;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.servlets.post.JSONResponse;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.replication.Replicator;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.heromotocorp.vida.core.config.ClientConfig;
import com.heromotocorp.vida.core.config.GlobalConfig;
import com.heromotocorp.vida.core.service.AvailableTestRideCityService;
import com.heromotocorp.vida.core.utils.CommonUtils;
import com.heromotocorp.vida.core.utils.Constants;

@Component(service = AvailableTestRideCityService.class, immediate = true)
public class AvailableTestRideCityServiceImpl implements AvailableTestRideCityService  {

	/** The Constant log. */
	private Logger log = LoggerFactory.getLogger(this.getClass());

	/** The Resolver Factory. */
	@Reference
	private ResourceResolverFactory resolverFactory;

	/** The Replicator. */
	@Reference
	private Replicator replicator;

	/** The global config. */
	@Reference
	private GlobalConfig globalConfig;

	/** The client config. */
	@Reference
	private ClientConfig clientConfig;

	private static String masterDataQueryForAvailableTestRideCity = "?q=select+dmpl__AddressId__r.dmpl__City__c,dmpl__AddressId__r.dmpl__State__c+from+dmpl__Branch__c+where+TestRideStartDate__c%3C=+today+AND+dmpl__AddressId__c%3C%3ENull+AND+dmpl__IsActive__c=true+GROUP+BY+dmpl__AddressId__r.dmpl__City__c,dmpl__AddressId__r.dmpl__State__c";

	@Override
	public void getAvailableTestRideCity() {
		String token = CommonUtils.getToken(globalConfig, clientConfig);
		processPackageData(token);

	}

	/**
	 * @param token
	 */
	public void processPackageData(String token) {
		log.info("Parameters for getProductData method-\t" + token);

		CloseableHttpClient httpClient = HttpClients.createDefault();
		try {
			String query = new StringBuilder(globalConfig.sfdcQueryEndpoint())
					.append(masterDataQueryForAvailableTestRideCity).toString();
			
			HttpGet httpGet = new HttpGet(query);

			httpGet.addHeader("Authorization", token);
			httpGet.addHeader("Content-Type", JSONResponse.RESPONSE_CONTENT_TYPE);
			CloseableHttpResponse httpResponse = null;
			try {
				httpResponse = httpClient.execute(httpGet);
			} catch (IOException e1) {
				log.error("IOException  occured method:", e1);

			}

			JsonArray outputArray = new JsonArray();
			if (Objects.nonNull(httpResponse)) {
			String jsonString = EntityUtils.toString(httpResponse.getEntity());
				
			log.info("Response from SF " + jsonString);

				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				JsonElement element = gson.fromJson(jsonString, JsonElement.class);
				JsonObject jsonObject = element.getAsJsonObject();
				JsonArray rootRecordArray = jsonObject.get(Constants.RECORDS).getAsJsonArray();
				String city = "";
				String state = "";
				for (JsonElement jsonElement : rootRecordArray) {
					JsonArray citiesArray = new JsonArray();
					JsonObject recordsObject = jsonElement.getAsJsonObject();
					if (recordsObject == null) {
						continue;
					}
					city = recordsObject.get(Constants.CITY) != null ? recordsObject.get(Constants.CITY).getAsString()
							: StringUtils.EMPTY;
					state = recordsObject.get(Constants.STATE) != null
							? recordsObject.get(Constants.STATE).getAsString()
							: StringUtils.EMPTY;
					if(outputArray.isEmpty() || arrayHasElement(outputArray, state)) {
						JsonObject packageObj = new JsonObject();
						citiesArray.add(city); 
						packageObj.addProperty(Constants.STATEKEY, state);
						packageObj.add(Constants.CITIESKEY, citiesArray);
						outputArray.add(packageObj);
					}
					else if(outputArray.size()>0) {
					  for (int i = 0; i < outputArray.size(); i++) {  
							JsonElement elementObj = outputArray.get(i);
							if (elementObj == null) {
								continue;
							}
							JsonObject obj = elementObj.getAsJsonObject();
							String stateObj = obj.get("state").getAsString();
							if( StringUtils.equals(stateObj, state)) {
								JsonArray outputArrayObj = (JsonArray) obj.get("cities");
								outputArrayObj.add(city);
							} 
					  }
					}
				}

			}

			log.info("availableTestRideCity JSON String " + outputArray.toString());
			if (!outputArray.isEmpty()) {
				String masterJsonPath = globalConfig.availableTestRideCities();
				String availableJsonPath = new StringBuilder(StringUtils.substringBefore(masterJsonPath, Constants.DOT))
						.append(Constants.DOT).append(globalConfig.country()).append(Constants.DOT)
						.append(Constants.JSON).toString();
				ResourceResolver resolver = CommonUtils.getResourceResolver(resolverFactory,
						Constants.WRITERSERVICEUSER);
				CommonUtils.createDamAsset(outputArray.toString(), resolver, availableJsonPath);
				Session session = resolver.adaptTo(Session.class);
				CommonUtils.replicateContent(session, availableJsonPath, replicator);
			}
		} catch (Exception e1) {
			log.error("Exception  occured method:", e1.getMessage());

		} finally {
			try {
				httpClient.close();
			} catch (IOException e) {
				log.error("IOException  occured method: getLocationData cause ", e);

			}
		}

	}

	private boolean arrayHasElement(JsonArray outputArray, String state) {
		for (int i = 0; i < outputArray.size(); i++) {  
			JsonElement elementObj = outputArray.get(i);
			JsonObject obj = elementObj.getAsJsonObject();
			String stateObj = obj.get("state").getAsString();
			if( StringUtils.equals(stateObj, state)) {
				return false;
				} 
	  }
		return true;
	}


}
