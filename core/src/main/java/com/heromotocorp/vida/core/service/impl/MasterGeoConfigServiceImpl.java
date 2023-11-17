package com.heromotocorp.vida.core.service.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.jcr.Session;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.ParseException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
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
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.heromotocorp.vida.core.config.ClientConfig;
import com.heromotocorp.vida.core.config.GlobalConfig;
import com.heromotocorp.vida.core.service.MasterGeoConfigService;
import com.heromotocorp.vida.core.utils.CommonUtils;
import com.heromotocorp.vida.core.utils.Constants;
import com.heromotocorp.vida.core.utils.MasterGeoUtil;
import com.heromotocorp.vida.core.vo.CountryVO;
import com.heromotocorp.vida.core.vo.StateVO;

/**
 * The Class ExtendedVehicleConfigServiceImpl.
 */
@Component(service = MasterGeoConfigService.class, immediate = true)
public class MasterGeoConfigServiceImpl implements MasterGeoConfigService {

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(MasterGeoConfigServiceImpl.class);

	/** The client config. */
	@Reference
	private transient ClientConfig clientConfig;

	/** The Resolver Factory. */
	@Reference
	private ResourceResolverFactory resolverFactory;

	/** The Replicator. */
	@Reference
	private Replicator replicator;

	/** The global config. */
	@Reference
	private transient GlobalConfig globalConfig;

	/**
	 * Vehicle list master json.
	 */
	@Override
	public void StateCityMasterJson() {
		
		iterateCountries(globalConfig.country());
	}

	/**
	 * Iterate countries.
	 *
	 * @param country the country
	 */
	@java.lang.SuppressWarnings("squid:S2095")
	private void iterateCountries(String country) {

		HttpPost postRequst = new HttpPost(globalConfig.postRequestToken());
		ResourceResolver resolver = CommonUtils.getResourceResolver(resolverFactory, Constants.WRITERSERVICEUSER);
		List<org.apache.http.NameValuePair> nvps = new ArrayList<>();
		nvps.add(new BasicNameValuePair(Constants.GRANTTYPE, globalConfig.grantType()));
		nvps.add(new BasicNameValuePair(Constants.CLIENTID, globalConfig.clientId()));
		nvps.add(new BasicNameValuePair(Constants.CLIENTSECRET, globalConfig.clientSecret()));
		nvps.add(new BasicNameValuePair(Constants.USERNAME, globalConfig.username()));
		nvps.add(new BasicNameValuePair(Constants.PSWD, globalConfig.password()));
		postRequst.setHeader(new BasicHeader(Constants.CONTENTTYPE, globalConfig.contentType()));
		try {
			postRequst.setEntity(new UrlEncodedFormEntity(nvps));
		} catch (UnsupportedEncodingException e1) {
			LOG.error("UnsupportedEncodingException in StateCityMasterJson Method");

		}
		if (clientConfig.client() != null) {
			CloseableHttpResponse response;
			String jsonString = StringUtils.EMPTY;
			try {
				response = clientConfig.client().execute(postRequst);
				jsonString = EntityUtils.toString(response.getEntity());
			} catch (IOException e) {
				LOG.error("IOException in StateCityMasterJson Method");

			}

			Gson gson = new Gson();
			JsonElement element = gson.fromJson(jsonString, JsonElement.class);
			JsonObject jsonObject = element.getAsJsonObject();
			String token = Constants.BEARER + " " + jsonObject.get(Constants.ACCESSTOKEN).getAsString();

			CloseableHttpClient masterHttpClient = HttpClients.createDefault();
			String paramString = new StringBuilder(Constants.SINGLE_QUOTE).append(country)
					.append(Constants.GROUP_BY_LOCATION).toString();
			HttpGet httpGet = new HttpGet(
					new StringBuilder(globalConfig.locationDataUrl()).append(paramString).toString());
			httpGet.addHeader("Authorization", token);
			httpGet.addHeader("Content-Type", JSONResponse.RESPONSE_CONTENT_TYPE);
			CloseableHttpResponse httpResponse = null;
			try {
				httpResponse = masterHttpClient.execute(httpGet);
			} catch (IOException e1) {
				LOG.error("IOException  occured method: getLocationData cause : %s", e1);

			}
			if (Objects.nonNull(httpResponse)) {
				String jsonResponseString;
				try {
					jsonResponseString = EntityUtils.toString(httpResponse.getEntity());

					JsonElement elementObj = gson.fromJson(jsonResponseString, JsonElement.class);
					JsonObject object = elementObj.getAsJsonObject();
					JsonArray valuesArray = object.getAsJsonObject().get(Constants.RECORDS).getAsJsonArray();
					String dealerJson = MasterGeoUtil.getDealerBranches(token, country, globalConfig);
					//Creating and publishing dealer json
					String jsonPath = globalConfig.serviceableCitiesUrl();
					jsonPath = new StringBuilder(StringUtils.substringBefore(jsonPath, Constants.DOT))
							.append(Constants.DOT).append(country).append(Constants.DOT).append(Constants.JSON)
							.toString();
					CommonUtils.createDamAsset(dealerJson, resolver, jsonPath);
					Session session = resolver.adaptTo(Session.class);
					CommonUtils.replicateContent(session, jsonPath, replicator);

					List<String> finalStatesList = new ArrayList<>();
					CountryVO countryVO = new CountryVO();
					List<StateVO> states = MasterGeoUtil.setState(valuesArray, countryVO, finalStatesList);

					// setting state to object
					countryVO.setStates(states);

					// setting city to object
					MasterGeoUtil.setCities(valuesArray, countryVO);
					
					MasterGeoUtil.setDealerDataToMasterData(gson, token, countryVO, country, globalConfig);

					String cityJsonString = new Gson().toJson(countryVO);
					//Creating and publishing master json
					if (!cityJsonString.isEmpty()) {
						String masterJsonPath = globalConfig.geoDataUrl();
						masterJsonPath = new StringBuilder(StringUtils.substringBefore(masterJsonPath, Constants.DOT))
								.append(Constants.DOT).append(country).append(Constants.DOT).append(Constants.JSON)
								.toString();
						CommonUtils.createDamAsset(cityJsonString, resolver, masterJsonPath);
						CommonUtils.replicateContent(session, masterJsonPath, replicator);
					}
				} catch (Exception e) {
					LOG.error("Exception in StateCityMasterJson Method", e.getMessage());
				}
			}
		}
	}

}
