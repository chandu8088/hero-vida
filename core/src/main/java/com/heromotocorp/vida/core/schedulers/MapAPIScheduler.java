package com.heromotocorp.vida.core.schedulers;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.jcr.Session;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.commons.scheduler.ScheduleOptions;
import org.apache.sling.commons.scheduler.Scheduler;
import org.apache.sling.servlets.post.JSONResponse;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.dam.api.Asset;
import com.day.cq.dam.api.Rendition;
import com.day.cq.replication.Replicator;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.heromotocorp.vida.core.config.ClientConfig;
import com.heromotocorp.vida.core.config.GlobalConfig;
import com.heromotocorp.vida.core.config.impl.MapAPISchedulerConfig;
import com.heromotocorp.vida.core.utils.CommonUtils;
import com.heromotocorp.vida.core.utils.Constants;
import com.heromotocorp.vida.core.vo.ChargingStation;
import com.heromotocorp.vida.core.vo.CityMasterVO;
import com.heromotocorp.vida.core.vo.SwappingStation;
import com.heromotocorp.vida.core.vo.VidaCenter;

/**
 * This scheduler fetch Experience Center and Charging Station data in every 30 minutes and generated JSON
 * file in DAM.
 *
 */
@Component(immediate = true, service = Runnable.class, configurationPolicy = ConfigurationPolicy.REQUIRE)
@Designate(ocd = MapAPISchedulerConfig.class)
public class MapAPIScheduler implements Runnable {

	/** The Constant log. */
	private static final Logger LOG = LoggerFactory.getLogger(MapAPIScheduler.class);

	public static final String CONTENT_DAM_VIDA_CONFIG_CITY_MASTER_CSV = "/content/dam/vida/config/City_Master.csv";

	private String queryParamCity = "?cityName=";

	private String queryParamRadius = "&radius=100";

	/** The Scheduler ID. */
	private int schedulerId;

	/** The Scheduler Service */
	@Reference
	private Scheduler scheduler;

	/** The ResourceResolverFactory. */
	@Reference
	private ResourceResolverFactory resolverFactory;

	@Reference
	private ClientConfig clientConfig;
	
	@Reference
	GlobalConfig globalConfig;
	
	/** The Replicator. */
	@Reference
	private Replicator replicator;
	
	boolean isEnabled;

	/**
	 * Activate Method.
	 * 
	 * @param config
	 */
	@Activate
	protected void activate(MapAPISchedulerConfig config) {
		schedulerId = config.schedulerName().hashCode();
		addScheduler(config);
	}

	/**
	 * Deactivate Method.
	 * 
	 * @param config
	 */
	@Deactivate
	protected void deactivate(MapAPISchedulerConfig config) {
		removeScheduler();
	}

	/**
	 * Method to Remove Scheduler.
	 */
	private void removeScheduler() {
		scheduler.unschedule(String.valueOf(schedulerId));

	}

	/**
	 * Method to Add Scheduler.
	 * 
	 * @param config
	 */
	private void addScheduler(MapAPISchedulerConfig config) {
		isEnabled = config.isMapAPISchedulerEnabled();
		ScheduleOptions scheduleOptions = scheduler.EXPR(config.cronExpression());
		scheduleOptions.name(String.valueOf(schedulerId));
		scheduleOptions.canRunConcurrently(true);
		scheduler.schedule(this, scheduleOptions);
		LOG.info("\n ---------Map Scheduler added----------");
	}

	/**
	 * Method to Run Scheduler.
	 */
	@Override
	public void run() {
		if (isEnabled) {
			LOG.info("\n ====> RUN METHOD  ");
			createJson();
		}
	}

	/**
	 * Method to create JSON for Experience Center, Service Center and Charging
	 * Stations.
	 */
	private void createJson() {
		String token = CommonUtils.getToken(globalConfig, clientConfig);
		String location = globalConfig.cityMasterCSVLocationPath() == null
				? CONTENT_DAM_VIDA_CONFIG_CITY_MASTER_CSV
				: globalConfig.cityMasterCSVLocationPath();

		List<CityMasterVO> cities = new ArrayList<>();
		ResourceResolver resolver = CommonUtils.getResourceResolver(resolverFactory, Constants.WRITERSERVICEUSER);
		Resource assetResource = resolver.getResource(location);
		Asset asset = assetResource.adaptTo(Asset.class);
		Rendition original = asset.getOriginal();
		if (original != null) {

			InputStream stream = original.getStream();
			List<String[]> records = new ArrayList<>();
			try (java.util.Scanner scanner = new java.util.Scanner(stream);) {
				while (scanner.hasNextLine()) {
					records.add(CommonUtils.getRecordFromLine(scanner.nextLine()));
				}
				records.remove(0);
				for (String[] record : records) {
					CityMasterVO vo = new CityMasterVO();

					vo.setCityName(record[0]);
					vo.setStateName(record[1]);
					vo.setCountryName(record[2]);
					vo.setLatitde(record[3]);
					vo.setLongitude(record[4]);
					vo.setId();
					List<VidaCenter> serviceCenters = new ArrayList<>();
					List<SwappingStation> swappingStations = new ArrayList<>();

					vo.setExperienceCenter(getExpericeCenter(token, vo.getCityName().trim(), serviceCenters));
					vo.setServiceCenter(serviceCenters);
					vo.setSwappingStations(swappingStations);

					vo.setChargingStations(getChargingStation(vo.getLatitde(), vo.getLongitude()));

					cities.add(vo);
				}

			}
			String jsonString = new Gson().toJson(cities);
			Session session = resolver.adaptTo(Session.class);
			CommonUtils.createDamAsset(jsonString, resolver, globalConfig.storeDetailsUrl());
			CommonUtils.replicateContent(session, globalConfig.storeDetailsUrl(), replicator);

		}
	}

	private List<VidaCenter> getExpericeCenter(String token, String city, List<VidaCenter> serviceCenters) {

		CloseableHttpClient httpClient = HttpClients.createDefault();
		List<VidaCenter> expCenter = new ArrayList<>();
		city = city.replace(Constants.SPACE, Constants.PERCENT_TWENTY);
		HttpGet httpGet = new HttpGet(
				globalConfig.nearByRequestUrl() + queryParamCity + city + queryParamRadius);
		httpGet.addHeader(Constants.AUTHORIZATION, token);
		httpGet.addHeader(Constants.CONTENTTYPE, JSONResponse.RESPONSE_CONTENT_TYPE);
		CloseableHttpResponse httpResponse;

		String jsonString;
		try {

			httpResponse = httpClient.execute(httpGet);
			jsonString = EntityUtils.toString(httpResponse.getEntity());

			Gson gson = new Gson();
			JsonElement element = gson.fromJson(jsonString, JsonElement.class);
			JsonArray jsonArray = element.getAsJsonArray();

			for (JsonElement values : jsonArray) {
				VidaCenter vo = new VidaCenter();
				JsonObject jsonObject = values.getAsJsonObject();
				String experienceCenterName = jsonObject.get(Constants.NAME) != null
						? jsonObject.get(Constants.NAME).getAsString()
						: StringUtils.EMPTY;
				vo.setExperienceCenterName(experienceCenterName);
				String type = jsonObject.get(Constants.BRANCHTYPE) != null
						? jsonObject.get(Constants.BRANCHTYPE).getAsString()
						: StringUtils.EMPTY;
				vo.setType(type);
				String id = jsonObject.get(Constants.ID) != null ? jsonObject.get(Constants.ID).getAsString()
						: StringUtils.EMPTY;
				vo.setId(id);
				String partnerId = jsonObject.get(Constants.PARTNERACCOUNTID) != null
						? jsonObject.get(Constants.PARTNERACCOUNTID).getAsString()
						: StringUtils.EMPTY;
				vo.setAccountpartnerId(partnerId);
				Boolean isServiceCenter = jsonObject.get(Constants.ALLSERVICE) != null
						&& jsonObject.get(Constants.ALLSERVICE).getAsBoolean();
				
				setAddress(jsonObject, token, httpClient, vo);
				setGeoLocation(jsonObject, vo);
				expCenter.add(vo);
				if (Boolean.TRUE.equals(isServiceCenter)) {
					serviceCenters.add(vo);

				}

			}
		} catch (ParseException | IOException e) {
			LOG.error("Exception  occured method: getExpericeCenter cause : %s", e);
		}

		return expCenter;
	}

	/**
	 * Method to Set Geo Location In Response.
	 * 
	 * @param jsonObject
	 * @param rootObject
	 * @param vo
	 * @param expCenter
	 */
	private void setGeoLocation(JsonObject jsonObject, VidaCenter vo) {
		if (!jsonObject.get(Constants.GEOLOCATION).isJsonNull()
				&& Objects.nonNull(jsonObject.get(Constants.GEOLOCATION))) {

			JsonObject geoLocation = jsonObject.get(Constants.GEOLOCATION).getAsJsonObject();

			String latitude = geoLocation.get(Constants.LATITUDE) != null
					? geoLocation.get(Constants.LATITUDE).getAsString()
					: StringUtils.EMPTY;
			vo.setLatitude(latitude);
			String longitude = geoLocation.get(Constants.LONGITUDE) != null
					? geoLocation.get(Constants.LONGITUDE).getAsString()
					: StringUtils.EMPTY;
			vo.setLongitude(longitude);

		}

	}

	/**
	 * Method to Set Address In Response.
	 * 
	 * @param jsonObject
	 * @param rootObject
	 * @param httpGet
	 * @param token
	 * @param httpClient
	 * @param vo
	 * @param expCenter
	 */
	private void setAddress(JsonObject jsonObject, String token, CloseableHttpClient httpClient, VidaCenter vo) {
		LOG.info("Parameters in setAddress method-\t" + jsonObject + "\t" + token + "\t" + httpClient + "\t" + vo);
		try {
			if (Objects.nonNull(jsonObject.get(Constants.ADDRESS)) && !jsonObject.get(Constants.ADDRESS).isJsonNull()) {

				JsonObject addressObj = jsonObject.get(Constants.ADDRESS).getAsJsonObject();
				String postalCode = addressObj.get(Constants.POSTALCODE) != null
						? addressObj.get(Constants.POSTALCODE).getAsString()
						: StringUtils.EMPTY;
				if (!addressObj.get(Constants.ATTRIBUTES).isJsonNull()
						&& Objects.nonNull(addressObj.get(Constants.ATTRIBUTES))) {
					JsonObject attribute = addressObj.get(Constants.ATTRIBUTES).getAsJsonObject();
					URL url = new URL(globalConfig.nearByRequestUrl());
					String addressApi = url.getProtocol() + "://" + url.getHost() + attribute.get("url").getAsString();
					HttpGet httpGet = new HttpGet(addressApi);

					httpGet.addHeader(Constants.AUTHORIZATION, token);
					httpGet.addHeader(Constants.CONTENTTYPE, JSONResponse.RESPONSE_CONTENT_TYPE);
					CloseableHttpResponse httpResponse = httpClient.execute(httpGet);

					String addressJson = EntityUtils.toString(httpResponse.getEntity());

					Gson gson = new Gson();
					JsonElement addressEle = gson.fromJson(addressJson, JsonElement.class);
					JsonObject addressObject = addressEle.getAsJsonObject();
					String address = addressObject.get(Constants.ADDRESS_C).getAsString();
					vo.setAddress(address);
					vo.setPostalCode(postalCode);

				}
			}

		} catch (ClientProtocolException clientProtocolException) {
			LOG.error("ClientProtocolException occured method: setAddress cause : %s ",
					clientProtocolException);

		} catch (IOException iOException) {
			LOG.error("IOException occured method: setAddress cause : %s ", iOException);
		}
	}



	/**
	 * Method to get Charging Station JSON from SFDC.
	 * 
	 * @param latitude
	 * @param longitude
	 * @return chargingStation
	 */
	private List<ChargingStation> getChargingStation(String latitude, String longitude) {
		LOG.info("Parameters for getChargingStation method-\t" + latitude + "\t" + longitude);
		List<ChargingStation> chargingStation = new ArrayList<>();
		String chargingLocationDetail = globalConfig.electrifyEndPoint();
		String token = Constants.BEARER + " " + globalConfig.electrifyToken();
		JsonObject json = new JsonObject();
		json.addProperty(Constants.FILTERkEY, globalConfig.defaultRadius());

		json.addProperty(Constants.ORZANIZATIONID, globalConfig.organizationId());
		json.addProperty(Constants.LATITUDE, latitude.trim());
		json.addProperty(Constants.LONGITUDE, longitude.trim());
		HttpPost postRequst = new HttpPost(chargingLocationDetail);
		postRequst.setHeader(Constants.AUTHORIZATION, token);
		postRequst.setHeader(new BasicHeader(Constants.CONTENTTYPE, JSONResponse.RESPONSE_CONTENT_TYPE));
		postRequst.setEntity(new StringEntity(json.toString(), StandardCharsets.UTF_8));

		CloseableHttpResponse response;
		try {
			response = clientConfig.client().execute(postRequst);
			String jsonString = EntityUtils.toString(response.getEntity());
			Gson gson = new Gson();
			JsonElement element = gson.fromJson(jsonString, JsonElement.class);
			JsonArray jsonArray = element.getAsJsonArray();
			chargingStation = getChargingStationJson(jsonArray);
		} catch (IOException e) {

			LOG.error("IOException  occured method: getChargingStation cause : %s", e);
		}

		return chargingStation;
	}

	/**
	 * Method to get required data for charging station from SFDC API response.
	 * 
	 * @param json
	 * @return chargingStation
	 */
	private List<ChargingStation> getChargingStationJson(JsonArray json) {
		LOG.info("Parameters for getChargingStationJson method-\t" + json);
		List<ChargingStation> chargingStation = new ArrayList<>();
		for (JsonElement jsonElement : json) {
			ChargingStation vo = new ChargingStation();
			JsonObject jsonObject = jsonElement.getAsJsonObject();
			String stationId = jsonObject.get(Constants.CHARGINGSTATIONID).getAsString();
			String stationName = jsonObject.get(Constants.CHARGINGSTATIONNAME).getAsString();
			String chargingStationAddress = jsonObject.get(Constants.CHARGING_STATION_ADDRESS).getAsString();
			String latitude = jsonObject.get(Constants.CHARGING_STATION_LATITUDE).getAsString();
			String longitude = jsonObject.get(Constants.CHARGING_STATION_LONGITUDE).getAsString();
			vo.setLatitude(latitude);
			vo.setLongitude(longitude);
			vo.setStationId(stationId);
			vo.setStationName(stationName);
			vo.setChargingStationAddress(chargingStationAddress);
			chargingStation.add(vo);
		}
		return chargingStation;

	}

}
