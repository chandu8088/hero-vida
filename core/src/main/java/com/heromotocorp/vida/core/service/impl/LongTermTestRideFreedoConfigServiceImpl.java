package com.heromotocorp.vida.core.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import com.google.gson.JsonSyntaxException;
import com.heromotocorp.vida.core.config.GlobalConfig;
import com.heromotocorp.vida.core.service.LongTermTestRideFreedoConfigService;
import com.heromotocorp.vida.core.utils.CommonUtils;
import com.heromotocorp.vida.core.utils.Constants;
import com.heromotocorp.vida.core.vo.StateVO;

/**
 * Interface Service to get LongTermTestRide Master data from Freedo.
 *
 */
/**
 * @author shissriv
 *
 * 
 */
@Component(service = LongTermTestRideFreedoConfigService.class, immediate = true)
public class LongTermTestRideFreedoConfigServiceImpl implements LongTermTestRideFreedoConfigService {

	private static final String STATE_NAME = "state_name";

	private static final String CITY_NAME = "city_name";

	//private static final String CITY = "city";

	private static final String ID = "id";

	private static final String STATE_CODE = "state_code";

	private static final String STATE = "state";

	private static final String RESULT = "Result";

	private static final String X_API_KEY = "x-api-key";

	private Logger log = LoggerFactory.getLogger(this.getClass());

	/*
	 * String api_key = "hgik3OIzpz8x9qrZKacxtJx4QmPkAWRa8KlQDaB5"; String
	 * LTTR_BASE_ENDPOINT = "https://dev-api.freedo.rentals/extendedride";
	 * 
	 * String LTTR_GET_RELATIONS_ENDPOINT = "/v1/relations"; String
	 * LTTR_GET_ALLCITY_ENDPOINT = "/v1/allActiveCities/?source=vida";
	 * 
	 * String lttrRelationsMasterLocation =
	 * "/content/dam/vida/config/lttr-relation-master.json"; String
	 * lttrCityMasterLocation = "/content/dam/vida/config/lttr-city-master.json";
	 */

	public static final String STATEKEY = STATE;

	/**
	 * The Cities.
	 */
	public static final String CITIESKEY = "cities";

	@Reference
	private ResourceResolverFactory resolverFactory;

	/** The Replicator. */
	@Reference
	private Replicator replicator;

	/** The global config. */
	@Reference
	private GlobalConfig globalConfig;

	@Override
	public void processLTTRMaster() {

		ResourceResolver resolver = CommonUtils.getResourceResolver(resolverFactory, Constants.WRITERSERVICEUSER);

		CloseableHttpClient httpClient = HttpClients.createDefault();

		procesAvailabilityCitiesMaster(resolver, httpClient);
		processRelationMaster(resolver, httpClient);
	}

	/**
	 * @param resolver
	 * @param httpClient
	 */
	private void procesAvailabilityCitiesMaster(ResourceResolver resolver, CloseableHttpClient httpClient) {

		HttpGet httpGet = new HttpGet(
				new StringBuilder(globalConfig.lttrBaseEndPoint()).append(globalConfig.lttrAllCityEndPoint()).toString());

		httpGet.addHeader(X_API_KEY, globalConfig.lttrApiKey());
		CloseableHttpResponse httpResponse ;

		try {
			httpResponse = httpClient.execute(httpGet);
			log.info(httpGet.toString());
			String jsonString = EntityUtils.toString(httpResponse.getEntity());
			log.info(jsonString);

			Gson gson = new Gson();
			JsonElement element = gson.fromJson(jsonString, JsonElement.class);
			JsonObject jsonObject = element.getAsJsonObject();
			JsonArray rootRecordArray = jsonObject.get(RESULT).getAsJsonArray();

			HashMap<String, JsonObject> map = new HashMap<String, JsonObject>();

			JsonArray outputArray = new JsonArray();

			for (JsonElement jsonElement : rootRecordArray) {

				JsonObject recordsObject = jsonElement.getAsJsonObject();

				JsonObject stateObj = recordsObject.get(STATE).getAsJsonObject();
				
				String stateCode = stateObj.get(STATE_CODE).getAsString();
				
				JsonObject preExistingaStateCode = map.get(stateCode);

				if (stateCode != null && preExistingaStateCode !=null) {
					JsonArray cityarray = preExistingaStateCode.getAsJsonArray(CITIESKEY);
							
					JsonObject newcityObject = new JsonObject();

					newcityObject.addProperty(ID, recordsObject.get(ID).getAsInt());
					newcityObject.addProperty("name", recordsObject.get(CITY_NAME).getAsString());

					cityarray.add(newcityObject);

				} else {

					JsonObject stateObj2 = new JsonObject();
					stateObj2.addProperty(STATEKEY, stateObj.get(STATE_NAME).getAsString());
					String state_Code= stateObj.get(STATE_CODE).getAsString();
					stateObj2.addProperty(ID, stateObj.get(STATE_CODE).getAsString());
					
					JsonArray cityarray = new JsonArray();

					JsonObject cityObject1 = new JsonObject();
					cityObject1.addProperty(ID, recordsObject.get(ID).getAsInt());
					cityObject1.addProperty("name", recordsObject.get(CITY_NAME).getAsString());

					cityarray.add(cityObject1);
					stateObj2.add(CITIESKEY, cityarray);
					outputArray.add(stateObj2);
					map.put(state_Code, stateObj2);

				}

			}
			log.info("Response- >" + outputArray);
			if (!outputArray.toString().isEmpty()) {
				CommonUtils.createDamAsset(outputArray.toString(), resolver, globalConfig.lttrCityMasterLocation());
				Session session = resolver.adaptTo(Session.class);
				CommonUtils.replicateContent(session, globalConfig.lttrCityMasterLocation(), replicator);
				log.info("Saved and Replicated at >" + globalConfig.lttrCityMasterLocation());
			}
		} catch (Exception e1) {
			log.error("Exception", e1.getMessage());

		}

	}

	/**
	 * @param resolver
	 * @param httpClient
	 */
	private void processRelationMaster(ResourceResolver resolver, CloseableHttpClient httpClient) {
		try {

			HttpGet httpGet = new HttpGet(
					new StringBuilder(globalConfig.lttrBaseEndPoint()).append(globalConfig.lttrRelationsEndPoint()).toString());

			httpGet.addHeader(X_API_KEY, globalConfig.lttrApiKey());
			CloseableHttpResponse httpResponse = null;
				httpResponse = httpClient.execute(httpGet);

			List<LTTRRelation> relations = new ArrayList<>();

			if (Objects.nonNull(httpResponse)) {
				String jsonString = EntityUtils.toString(httpResponse.getEntity());
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				JsonElement element = gson.fromJson(jsonString, JsonElement.class);
				JsonObject jsonObject = element.getAsJsonObject();
				JsonArray rootRecordArray = jsonObject.get(RESULT).getAsJsonArray();

				for (JsonElement jsonElement : rootRecordArray) {

					JsonObject recordsObject = jsonElement.getAsJsonObject();

					LTTRRelation relation = new LTTRRelation();

					relation.setId(recordsObject.get(ID).getAsString());
					relation.setName(recordsObject.get("name").getAsString());
					relations.add(relation);

				}

				String relationSAsString = new GsonBuilder().setPrettyPrinting().create().toJson(relations);
				log.info(relationSAsString);
				if (!relationSAsString.isEmpty()) {
					CommonUtils.createDamAsset(relationSAsString, resolver, globalConfig.lttrRelationsMasterLocation());
					Session session = resolver.adaptTo(Session.class);
					CommonUtils.replicateContent(session, globalConfig.lttrRelationsMasterLocation(), replicator);
				}
			}

		} catch (IOException e1) {
			log.error("IOException  occured method: processRelationMaster  cause : %s", e1);
		} catch (JsonSyntaxException | ParseException e) {
			log.error("Exception  occured method: processRelationMaster cause : %s", e);
		} 
	}

}

class LTTRRelation {
	String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	String name;

}
