package com.heromotocorp.vida.core.models;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.servlets.post.JSONResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.heromotocorp.vida.core.config.ClientConfig;
import com.heromotocorp.vida.core.config.GlobalConfig;
import com.heromotocorp.vida.core.utils.Constants;
import com.heromotocorp.vida.core.utils.MasterGeoUtil;
import com.heromotocorp.vida.core.vo.CountryVO;
import com.heromotocorp.vida.core.vo.StateVO;

/**
 * The Class MasterGeoModel.
 */
@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class MasterGeoModel {

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(MasterGeoModel.class);

	/** The client config. */
	@OSGiService
	private ClientConfig clientConfig;

	/** The resource resolver. */
	@SlingObject
	private ResourceResolver resourceResolver;

	/** The global config. */
	@OSGiService
	private GlobalConfig globalConfig;

	private String grantType;

	private String clientId;

	private String clientSecret;

	private String username;

	private String password;

	private String contentType;

	private String locationDataUrl;

	private String country;

	/**
	 * Inits the.
	 */
	@PostConstruct
	protected void init() {
		grantType = globalConfig.grantType();
		clientId = globalConfig.clientId();
		clientSecret = globalConfig.clientSecret();
		username = globalConfig.username();
		password = globalConfig.password();
		contentType = globalConfig.contentType();
		locationDataUrl = globalConfig.locationDataUrl();
		country = globalConfig.country();
	}

	/**
	 * Iterate countries.
	 *
	 * @param country the country
	 */
	public CountryVO iterateCountries() {
		CountryVO countryVO = new CountryVO();
		HttpPost postRequst = new HttpPost(globalConfig.postRequestToken());
		List<org.apache.http.NameValuePair> nvps = new ArrayList<>();
		nvps.add(new BasicNameValuePair(Constants.GRANTTYPE, grantType));
		nvps.add(new BasicNameValuePair(Constants.CLIENTID, clientId));
		nvps.add(new BasicNameValuePair(Constants.CLIENTSECRET, clientSecret));
		nvps.add(new BasicNameValuePair(Constants.USERNAME, username));
		nvps.add(new BasicNameValuePair(Constants.PSWD, password));
		postRequst.setHeader(new BasicHeader(Constants.CONTENTTYPE, contentType));
		try {
			postRequst.setEntity(new UrlEncodedFormEntity(nvps));
		} catch (UnsupportedEncodingException e1) {
			LOG.error("UnsupportedEncodingException in StateCityMasterJson Method");

		}
		if (clientConfig.client() != null) {
			CloseableHttpResponse response;
			String jsonString = StringUtils.EMPTY;
			CloseableHttpClient masterHttpClient = HttpClients.createDefault();
			try {
				response = clientConfig.client().execute(postRequst);
				jsonString = EntityUtils.toString(response.getEntity());

				Gson gson = new Gson();
				JsonElement element = gson.fromJson(jsonString, JsonElement.class);
				JsonObject jsonObject = element.getAsJsonObject();
				String token = Constants.BEARER + " " + jsonObject.get(Constants.ACCESSTOKEN).getAsString();
				String paramString = new StringBuilder(Constants.SINGLE_QUOTE).append(country)
						.append(Constants.GROUP_BY_LOCATION).toString();
				HttpGet httpGet = new HttpGet(new StringBuilder(locationDataUrl).append(paramString).toString());
				httpGet.addHeader("Authorization", token);
				httpGet.addHeader("Content-Type", JSONResponse.RESPONSE_CONTENT_TYPE);
				CloseableHttpResponse httpResponse = null;
				httpResponse = masterHttpClient.execute(httpGet);
				if (Objects.nonNull(httpResponse)) {
					String jsonResponseString;
					jsonResponseString = EntityUtils.toString(httpResponse.getEntity());

					JsonElement elementObj = gson.fromJson(jsonResponseString, JsonElement.class);
					JsonObject object = elementObj.getAsJsonObject();
					JsonArray valuesArray = object.getAsJsonObject().get(Constants.RECORDS).getAsJsonArray();

					List<String> finalStatesList = new ArrayList<>();
					List<StateVO> states = MasterGeoUtil.setState(valuesArray, countryVO, finalStatesList);

					// setting state to object
					countryVO.setStates(states);

					// setting city to object
					MasterGeoUtil.setCities(valuesArray, countryVO);

					MasterGeoUtil.setDealerDataToMasterData(gson, token, countryVO, country, globalConfig);
				}
			} catch (Exception e) {
				LOG.error("Exception in StateCityMasterJson Method", e);
			} finally {
				try {
					masterHttpClient.close();
				} catch (IOException e) {
					LOG.error("IOException  occured method: getLocationData cause ", e);

				}
			}
		}
		return countryVO;
	}

	/**
	 * Iterate countries.
	 *
	 * @param country the country
	 */
	public String getStateCitiesList() {
		CountryVO iterateCountries = iterateCountries();
		return new Gson().toJson(iterateCountries);
	}
}
