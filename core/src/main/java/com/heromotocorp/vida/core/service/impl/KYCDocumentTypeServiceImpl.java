package com.heromotocorp.vida.core.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
import com.heromotocorp.vida.core.service.KYCDocumentTypeService;
import com.heromotocorp.vida.core.utils.CommonUtils;
import com.heromotocorp.vida.core.utils.Constants;
import com.heromotocorp.vida.core.vo.KYCVo;

@Component(service = KYCDocumentTypeService.class, immediate = true)
public class KYCDocumentTypeServiceImpl implements KYCDocumentTypeService {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());

	/** The global config. */
	@Reference
	GlobalConfig globalConfig;
	
	/** The client config. */
	@Reference
	ClientConfig clientConfig;
	
	/** The Resolver Factory. */
	@Reference
	private ResourceResolverFactory resolverFactory;
	
	/** The Replicator. */
	@Reference
	private Replicator replicator;

	
	
	private static String kycDocumentTypeSfdcEndpoint  = "/services/data/v55.0/ui-api/object-info/dmpl__KYCDocument__c/picklist-values/012000000000000AAA/dmpl__DocumentType__c";  
	
	@Override
	public void getKYCDocumentType() {
		
		log.info("Inside the getKYCDocumentType() method");
		String token = CommonUtils.getToken(globalConfig, clientConfig);
		processKYCDocumentType(token);
	}

	
	/**
	 * @param token
	 */
	public void processKYCDocumentType(String token) {
		
		log.info("Received the token::::::::" +token);
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		try {
			String query = new StringBuilder(globalConfig.serverName().concat(kycDocumentTypeSfdcEndpoint)).toString();
			
			HttpGet httpGet = new HttpGet(query);
			httpGet.addHeader("Authorization", token);
			httpGet.addHeader("Content-Type", JSONResponse.RESPONSE_CONTENT_TYPE);
			
			CloseableHttpResponse httpResponse = null;
			
			try {
				httpResponse = httpClient.execute(httpGet);
			} catch (IOException e1) {
				log.error("IOException  occured method:", e1);

			}
			
			List<KYCVo> kycVoList = new ArrayList<>();
			if (Objects.nonNull(httpResponse)) {
				
				String jsonString = EntityUtils.toString(httpResponse.getEntity());
				log.info("Response from SF " + jsonString);
				
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				JsonElement element = gson.fromJson(jsonString, JsonElement.class);
				JsonObject jsonObject = element.getAsJsonObject();
				JsonArray rootRecordArray = jsonObject.get(Constants.VALUES).getAsJsonArray();
				log.info("JSONArray:::::" +rootRecordArray);
				
				
				
				for(JsonElement jsonElement : rootRecordArray) {
					JsonObject recordsObject = jsonElement.getAsJsonObject();
					String label = recordsObject.get(Constants.LABEL).getAsString();
					String value = recordsObject.get(Constants.VALUE).getAsString();
					
					KYCVo kycVO = new KYCVo();
					kycVO.setLabel(label);
					kycVO.setValue(value);
					kycVoList.add(kycVO);
					log.info("Raw arrayList kycVoList:::" +kycVoList);
					
					GsonBuilder gsonBuilder = new GsonBuilder();
					
					Gson createGson = gsonBuilder.create();
					String jsonOutput =  createGson.toJson(kycVoList);
					log.info("Converted to JSON::::"+jsonOutput );
				}
				
			}
			String jsonString1 = new GsonBuilder().setPrettyPrinting().create().toJson(kycVoList);	
			if (!jsonString1.isEmpty()) {
				String masterJSONPath = globalConfig.addressTypesUrl();
				String kycDocumentTypeJSONPath = new StringBuilder(
						StringUtils.substringBefore(masterJSONPath, Constants.DOT)).append(Constants.DOT)
						.append(Constants.JSON).toString();
				ResourceResolver resolver = CommonUtils.getResourceResolver(resolverFactory,
						Constants.WRITERSERVICEUSER);
				CommonUtils.createDamAsset(jsonString1, resolver, kycDocumentTypeJSONPath);
				Session session = resolver.adaptTo(Session.class);
				CommonUtils.replicateContent(session, kycDocumentTypeJSONPath, replicator);
			}
		}
		
		catch (Exception e1) {
			log.error("Exception  occured method:", e1.getMessage());

		}
		finally {
			try {
				httpClient.close();
			} catch (IOException e) {
				log.error("IOException  occured method: getLocationData cause ", e);

			}
		}
		
	}

}
