package com.heromotocorp.vida.core.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.sling.servlets.post.JSONResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.SetMultimap;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.heromotocorp.vida.core.config.GlobalConfig;
import com.heromotocorp.vida.core.vo.CityVO;
import com.heromotocorp.vida.core.vo.CountryVO;
import com.heromotocorp.vida.core.vo.PriorityStateCityVO;
import com.heromotocorp.vida.core.vo.StateVO;

/**
 * The Class MasterGeoUtil.
 */
public class MasterGeoUtil {

	/**
	 * Instantiates a new master geo util.
	 */
	private MasterGeoUtil() {
		throw new IllegalStateException("MasterGeoUtil class");

	}

	/** The Constant LOGGER. */
	private static final Logger LOG = LoggerFactory.getLogger(MasterGeoUtil.class);
	
	static final String DMPLBRANCHTYPE = "dmpl__BranchType__c";

	/**
	 * Sets the dealer data to master data.
	 *
	 * @param gson the gson
	 * @param token the token
	 * @param countryVO the country VO
	 * @param country the country
	 * @param globalConfig the global config
	 */
	public static void setDealerDataToMasterData(Gson gson, String token, CountryVO countryVO, String country, GlobalConfig globalConfig) {
		try {
			String dealerJson = getDealerBranches(token, country, globalConfig);
			JsonElement statecityElements = gson.fromJson(dealerJson, JsonElement.class);

			if (Objects.nonNull(statecityElements)) {
				List<StateVO> newStates = setStateCityList(gson, statecityElements);
				// Add new city and state to existing list with priority
				mergeStateCity(countryVO, newStates);
				// Merge new and old state list
				List<StateVO> mergedStates = ListUtils.union(newStates, countryVO.getStates());
				countryVO.setStates(mergedStates);
			}

		} catch (IOException iOException) {
			LOG.error("IOException occured method: doGet cause : %s ", iOException);
		}
	}

	/**
	 * Merge state city.
	 *
	 * @param countryVO the country VO
	 * @param newStates the new states
	 */
	private static void mergeStateCity(CountryVO countryVO, List<StateVO> newStates) {
		for (StateVO stateVO : newStates) {
			// find new state in existing state list
			StateVO state = findState(countryVO.getStates(), stateVO.getValue());
			if (Objects.nonNull(state)) {
				List<CityVO> cityList = new ArrayList<>();
				// Merge new and old city list
				List<CityVO> finalCityList = ListUtils.union(stateVO.getCities(), state.getCities());

				for (CityVO city : finalCityList) {
					// find new city in existing city list
					CityVO cityObj = findCity(cityList, city.getValue());
					if (Objects.isNull(cityObj)) {
						cityList.add(city);
					}
				}
				stateVO.setCities(cityList);
				countryVO.getStates().remove(state);
			}
		}
	}

	/**
	 * Sets the state city list.
	 *
	 * @param gson              the gson
	 * @param statecityElements the statecity elements
	 * @return the list
	 */
	private static List<StateVO> setStateCityList(Gson gson, JsonElement statecityElements) {
		PriorityStateCityVO[] stateCityList = gson.fromJson(statecityElements.toString(), PriorityStateCityVO[].class);
		// Get list of city and state from json
		List<StateVO> newStates = new ArrayList<>();
		for (PriorityStateCityVO priorityStateCityVO : stateCityList) {
			StateVO state = new StateVO();
			state.setLabel(priorityStateCityVO.getState());
			state.setValue(priorityStateCityVO.getState());
			List<CityVO> cities = new ArrayList<>();

			for (String city : priorityStateCityVO.getCities()) {
				CityVO cityVO = new CityVO();
				cityVO.setLabel(city);
				cityVO.setValue(city);
				cities.add(cityVO);
			}
			state.setCities(cities);
			newStates.add(state);
		}
		return newStates;
	}

	/**
	 * Sets the state.
	 *
	 * @param valuesArray     the values array
	 * @param countryVO       the country VO
	 * @param finalStatesList the final states list
	 * @return the list
	 */
	public static List<StateVO> setState(JsonArray valuesArray, CountryVO countryVO, List<String> finalStatesList) {
		String value;
		for (JsonElement jsonElement : valuesArray) {

			JsonObject recordsObject = jsonElement.getAsJsonObject();
			String countryLabel = recordsObject.get(Constants.COUNTRY) != null
					? recordsObject.get(Constants.COUNTRY).getAsString()
					: StringUtils.EMPTY;
			countryVO.setLabel(countryLabel);
			countryVO.setValue(countryLabel);
			value = recordsObject.get(Constants.STATE) != null ? recordsObject.get(Constants.STATE).getAsString()
					: StringUtils.EMPTY;
			finalStatesList.add(value);

		}
		List<String> uniqueStates = finalStatesList.stream().distinct().collect(Collectors.toList());
		List<StateVO> states = new ArrayList<>();
		for (String state : uniqueStates) {
			StateVO stateVO = new StateVO();
			stateVO.setLabel(state);
			stateVO.setValue(state);
			states.add(stateVO);
		}
		Collections.sort(states, new Comparator<StateVO>() {
			@Override
			public int compare(final StateVO object1, final StateVO object2) {
				return object1.getLabel().compareTo(object2.getLabel());
			}
		});

		return states;
	}

	/**
	 * Sets the city.
	 *
	 * @param valuesArray the values array
	 * @param countryVO   the country VO
	 */
	public static void setCities(JsonArray valuesArray, CountryVO countryVO) {
		String value;
		for (JsonElement jsonElement : valuesArray) {
			JsonObject recordsObject = jsonElement.getAsJsonObject();
			value = recordsObject.get(Constants.STATE) != null ? recordsObject.get(Constants.STATE).getAsString()
					: StringUtils.EMPTY;
			if (Objects.nonNull(countryVO.getStates())) {

				setStateVO(countryVO, value, recordsObject);

			}
		}

	}

	/**
	 * Sets the state VO.
	 *
	 * @param countryVO the country VO
	 * @param value the value
	 * @param recordsObject the records object
	 */
	private static void setStateVO(CountryVO countryVO, String value, JsonObject recordsObject) {
		for (StateVO state : countryVO.getStates()) {
			if (state.getLabel().equals(value)) {
				CityVO cityVO = new CityVO();
				String cityLabel = recordsObject.get(Constants.CITY) != null
						? recordsObject.get(Constants.CITY).getAsString()
						: StringUtils.EMPTY;
				cityVO.setLabel(cityLabel);
				cityVO.setValue(cityLabel);

				state.getCities().add(cityVO);
				Collections.sort(state.getCities(), new Comparator<CityVO>() {
					@Override
					public int compare(final CityVO object1, final CityVO object2) {
						return object1.getLabel().compareTo(object2.getLabel());
					}
				});

			}
		}
	}

	/**
	 * Gets the dealers and branches.
	 *
	 * @param token the token
	 * @param array the array
	 * @param globalConfig the global config
	 * @return the dealer branches
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static String getDealerBranches(String token, String array, GlobalConfig globalConfig) throws IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		try {
			HttpGet httpGet = new HttpGet(globalConfig.dealersbranchesUrl());
			httpGet.addHeader(Constants.AUTHORIZATION, token);
			httpGet.addHeader(Constants.CONTENTTYPE, JSONResponse.RESPONSE_CONTENT_TYPE);
			CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
			String jsonString = EntityUtils.toString(httpResponse.getEntity());

			Gson gson = new Gson();
			JsonElement element = gson.fromJson(jsonString, JsonElement.class);
			JsonObject jsonObject = element.getAsJsonObject();
			JsonArray rootRecordArray = jsonObject.get(Constants.RECORDS).getAsJsonArray();

			SetMultimap<String, String> map = HashMultimap.create();
			JsonArray statearray = new JsonArray();
			for (JsonElement jsonElement : rootRecordArray) {

				setRecordstoObjects(array, map, jsonElement);

			}

			for (String key : map.keySet()) {
				JsonObject stateObject = new JsonObject();

				JsonArray cityarray = new JsonArray();
				for (String value : map.get(key)) {

					cityarray.add(value);
				}
				stateObject.addProperty(Constants.STATEKEY, key);
				stateObject.add(Constants.CITIESKEY, cityarray);
				statearray.add(stateObject);

			}

			return statearray.toString();
		} finally {
			httpClient.close();
		}
	}

	/**
	 * Sets the recordsto objects.
	 *
	 * @param array the array
	 * @param map the map
	 * @param jsonElement the json element
	 */
	private static void setRecordstoObjects(String array, SetMultimap<String, String> map, JsonElement jsonElement) {
		JsonObject recordsObject = jsonElement.getAsJsonObject();

		if (!recordsObject.get(Constants.BRANCHES).isJsonNull()) {
			JsonObject branchesObjects = recordsObject.get(Constants.BRANCHES).getAsJsonObject();
			JsonArray childRecordArray = branchesObjects.get("records").getAsJsonArray();

			for (JsonElement recordsElement : childRecordArray) {
				JsonObject recordObject = recordsElement.getAsJsonObject();
				if (!recordObject.get(DMPLBRANCHTYPE).isJsonNull() && recordObject.has(DMPLBRANCHTYPE)
						&& recordObject.get(DMPLBRANCHTYPE).getAsString().equalsIgnoreCase("Experience Centre")) {
					setRecordObject(array, map, recordsElement);
				}

			}

		}
	}

	/**
	 * Sets the record object.
	 *
	 * @param array the array
	 * @param map the map
	 * @param recordsElement the records element
	 */
	private static void setRecordObject(String array, SetMultimap<String, String> map, JsonElement recordsElement) {
		JsonObject recordObject = recordsElement.getAsJsonObject();
		if (!recordObject.get(Constants.ADDRESS).isJsonNull() && recordObject.has(Constants.ADDRESS)) {
			setAddressObjects(array, map, recordObject);
		}
	}

	/**
	 * Sets the address objects.
	 *
	 * @param array the array
	 * @param map the map
	 * @param recordObject the record object
	 */
	private static void setAddressObjects(String array, SetMultimap<String, String> map, JsonObject recordObject) {
		JsonObject addressId = recordObject.get(Constants.ADDRESS).getAsJsonObject();
		if (!addressId.get(Constants.CITY).isJsonNull() && addressId.has(Constants.CITY)) {
			String country = addressId.get(Constants.COUNTRY) != null ? addressId.get(Constants.COUNTRY).getAsString()
					: StringUtils.EMPTY;
			if (!country.isEmpty() && country.equalsIgnoreCase(array)) {
				String city = !addressId.get(Constants.CITY).isJsonNull() ? addressId.get(Constants.CITY).getAsString()
						: StringUtils.EMPTY;
					String state = !addressId.get(Constants.STATE).isJsonNull() ? addressId.get(Constants.STATE).getAsString()
						: StringUtils.EMPTY;				
						map.put(state, city);				
				}
		}
	}

	/**
	 * Find state.
	 *
	 * @param stateList the state list
	 * @param state     the state
	 * @return the state VO
	 */
	private static StateVO findState(List<StateVO> stateList, String state) {
		return stateList.stream().filter(stateObj -> state.equals(stateObj.getValue())).findFirst().orElse(null);

	}

	/**
	 * Find city.
	 *
	 * @param cityList the city list
	 * @param city     the city
	 * @return the city VO
	 */
	private static CityVO findCity(List<CityVO> cityList, String city) {
		return cityList.stream().filter(cityObj -> city.equals(cityObj.getValue())).findFirst().orElse(null);

	}

}
