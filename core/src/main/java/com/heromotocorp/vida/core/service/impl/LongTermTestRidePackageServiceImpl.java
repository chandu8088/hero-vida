package com.heromotocorp.vida.core.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

import javax.jcr.Session;

import org.apache.http.ParseException;
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
import com.heromotocorp.vida.core.service.LongTermTestRidePackageService;
import com.heromotocorp.vida.core.utils.CommonUtils;
import com.heromotocorp.vida.core.utils.Constants;

/**
 * Service to get Package data from SFDC
 *
 */
@Component(service = LongTermTestRidePackageService.class, immediate = true)
public class LongTermTestRidePackageServiceImpl implements LongTermTestRidePackageService {

	private static final String CITY_PRICE = "city-price";

	private static final String SF_RENTAL_PACKAGE_PRICES_R = "Rental_Package_Prices__r";

	private static final String PACKAGE_NAME = "packages";

	private static final String PRICE = "price";

	private static final String SF_PRICE_C = "Price__c";

	private static final String SF_CITY_C = "City__c";

	private static final String SF_CITY_ID_C = "City_Id__c";

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
	private transient GlobalConfig globalConfig;

	/** The client config. */
	@Reference
	private transient ClientConfig clientConfig;

	private static final String SF_NAME = "Name";

	private static final String SF_DAY = "Package_days__c";

	private static final String SF_ID = "Package_Id__c";

	//final String lttrPackageMasterLocation = "/content/dam/vida/config/lttr-package-master.json";

	private static String masterDataQueryForProductVariant = "?q=SELECT+Name,Package_days__c,Package_Id__c,(select+id,City__c,City_Id__c,Price__c+from+Rental_Package_Prices__r)+FROM+Rental_Package__c+where+is_Active__c=true";

	@Override
	public void getPackageDataJson() {
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

			HttpGet httpGet = new HttpGet(new StringBuilder(globalConfig.sfdcQueryEndpoint())
					.append(masterDataQueryForProductVariant).toString());

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
				HashMap<String, JsonObject> map = new HashMap<>();

				for (JsonElement jsonElement : rootRecordArray) {

					JsonObject recordsObject = jsonElement.getAsJsonObject();
					String nameObj = recordsObject.get(SF_NAME).getAsString();
					float dayObj = recordsObject.get(SF_DAY).getAsFloat();
					String idObj = recordsObject.get(SF_ID).getAsString();

					JsonArray cityPriceDataArray = recordsObject.get(SF_RENTAL_PACKAGE_PRICES_R).getAsJsonObject()
							.get(Constants.RECORDS).getAsJsonArray();

					for (JsonElement e1 : cityPriceDataArray) {
						String cityID = e1.getAsJsonObject().get(SF_CITY_ID_C).getAsString();
						JsonObject preExistingaStateCode = map.get(cityID);
						float price = e1.getAsJsonObject().get(SF_PRICE_C).getAsFloat();
						if (cityID != null && preExistingaStateCode != null) {
							JsonArray pricearray = preExistingaStateCode.getAsJsonArray(CITY_PRICE);
							JsonObject newpriceObject = new JsonObject();
							newpriceObject.addProperty("id", e1.getAsJsonObject().get(SF_CITY_ID_C).getAsInt());
							newpriceObject.addProperty("name", e1.getAsJsonObject().get(SF_CITY_C).getAsString());

							pricearray.add(newpriceObject);

						} else {
							JsonObject packageObj = new JsonObject();
							packageObj.addProperty("name", nameObj);
							packageObj.addProperty("days", dayObj);
							packageObj.addProperty("packageId", idObj);
							packageObj.addProperty(PRICE, price);

							JsonArray cityarray = new JsonArray();

							JsonObject priceObject1 = new JsonObject();
							priceObject1.addProperty("id", e1.getAsJsonObject().get(SF_CITY_ID_C).getAsInt());
							priceObject1.addProperty("name", e1.getAsJsonObject().get(SF_CITY_C).getAsString());

							cityarray.add(packageObj);
							priceObject1.add(PACKAGE_NAME, cityarray);
							outputArray.add(priceObject1);
							map.put(cityID, priceObject1);

						}

					}
				}

			}

			log.info("lltr JSON String " + outputArray.toString());
			if (!outputArray.toString().isEmpty()) {
				ResourceResolver resolver = CommonUtils.getResourceResolver(resolverFactory,
						Constants.WRITERSERVICEUSER);
				CommonUtils.createDamAsset(outputArray.toString(), resolver, globalConfig.lttrPackageMasterLocation());
				Session session = resolver.adaptTo(Session.class);
				CommonUtils.replicateContent(session, globalConfig.lttrPackageMasterLocation(), replicator);
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

}
