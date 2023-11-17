package com.heromotocorp.vida.core.servlets;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.apache.sling.servlets.post.JSONResponse;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.propertytypes.ServiceDescription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.commons.PathInfo;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.heromotocorp.vida.core.config.ClientConfig;
import com.heromotocorp.vida.core.config.GlobalConfig;
import com.heromotocorp.vida.core.utils.Constants;

/**
 * Servlet that writes near by branch details into the response based on the
 * City selected. Servlet can be accessed using via any page using selector
 * nearbybranches.
 *
 */
@Component(service = { Servlet.class })
@SlingServletResourceTypes(resourceTypes = Constants.PAGERESOURCETYPE, methods = HttpConstants.METHOD_GET, selectors = "nearbybranches", extensions = "json")
@ServiceDescription("Near By Branches Servlet")
public class NearByBranchesServlet extends SlingSafeMethodsServlet {

	private static final long serialVersionUID = 3592470025905082741L;
	private static final Logger log = LoggerFactory.getLogger(NearByBranchesServlet.class);

	@Reference
	private transient ClientConfig clientConfig;

	@Reference
	private transient GlobalConfig globalConfig;

	private String queryParamCity = "?cityName=";

	private String queryParamRadius = "&radius=100";

	private String idkey = "id";

	@Override
	protected void doGet(final SlingHttpServletRequest req, final SlingHttpServletResponse resp)
			throws ServletException, IOException {

		PathInfo pathinf = new PathInfo(req.getPathInfo());
		String[] array = pathinf.getSelectors();

		HttpPost postRequst = new HttpPost(globalConfig.postRequestToken());

		List<org.apache.http.NameValuePair> nvps = new ArrayList<>();

		nvps.add(new BasicNameValuePair(Constants.GRANTTYPE, globalConfig.grantType()));
		nvps.add(new BasicNameValuePair(Constants.CLIENTID, globalConfig.clientId()));
		nvps.add(new BasicNameValuePair(Constants.CLIENTSECRET, globalConfig.clientSecret()));

		nvps.add(new BasicNameValuePair(Constants.USERNAME, globalConfig.username()));
		nvps.add(new BasicNameValuePair(Constants.PSWD, globalConfig.password()));

		postRequst.setHeader(new BasicHeader(Constants.CONTENTTYPE, globalConfig.contentType()));

		postRequst.setEntity(new UrlEncodedFormEntity(nvps));

		if (clientConfig.client() != null) {
			CloseableHttpResponse response = clientConfig.client().execute(postRequst);
			String jsonString = EntityUtils.toString(response.getEntity());

			Gson gson = new Gson();
			JsonElement element = gson.fromJson(jsonString, JsonElement.class);
			JsonObject jsonObject = element.getAsJsonObject();
			String token = Constants.BEARER + " " + jsonObject.get(Constants.ACCESSTOKEN).getAsString();

			if (array.length > 0) {
				try {
					getNearByBranches(token, array[1], resp);
				} catch (IOException iOException) {
					log.error("IOException occured method: doGet cause : %s ", iOException);
				}
			}

		}
	}

	/**
	 *
	 * Gets the near by branches
	 *
	 * @param token the token
	 * @param array the array
	 * @param resp  the resp
	 * @throws IOException
	 * @throws InterruptedException
	 */
	private void getNearByBranches(String token, String array, SlingHttpServletResponse resp) throws IOException {

		if (array.contains(" ")) {
			array = array.replaceAll(" ", "%20");
		}
		CloseableHttpClient httpClient = HttpClients.createDefault();
		try {

			HttpGet httpGet = new HttpGet(
					globalConfig.nearByRequestUrl() + queryParamCity + array + queryParamRadius);
			httpGet.addHeader(Constants.AUTHORIZATION, token);
			httpGet.addHeader(Constants.CONTENTTYPE, JSONResponse.RESPONSE_CONTENT_TYPE);
			CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
			String jsonString = EntityUtils.toString(httpResponse.getEntity());
	
			Gson gson = new Gson();
			JsonElement element = gson.fromJson(jsonString, JsonElement.class);
			JsonArray jsonArray = element.getAsJsonArray();
			JsonArray nearByBranchesArray = new JsonArray();
			String json = StringUtils.EMPTY;
			for (JsonElement values : jsonArray) {
				JsonObject rootObject = new JsonObject();
				JsonObject jsonObject = values.getAsJsonObject();
				String experienceCenterName = jsonObject.get(Constants.NAME) != null
						? jsonObject.get(Constants.NAME).getAsString()
						: StringUtils.EMPTY;
				Boolean isActive = jsonObject.get(Constants.ISACTIVE) != null
						? jsonObject.get(Constants.ISACTIVE).getAsBoolean()
						: Boolean.FALSE;
				String type = jsonObject.get(Constants.BRANCHTYPE) != null
						? jsonObject.get(Constants.BRANCHTYPE).getAsString()
						: StringUtils.EMPTY;
				String id = jsonObject.get(Constants.ID) != null ? jsonObject.get(Constants.ID).getAsString()
						: StringUtils.EMPTY;
				String partnerId = jsonObject.get(Constants.PARTNERACCOUNTID) != null
						? jsonObject.get(Constants.PARTNERACCOUNTID).getAsString()
						: StringUtils.EMPTY;
				boolean isTestRideAvailable = (jsonObject.get(Constants.TESTRIDESTARTDATE) != null) && (!"".equals(jsonObject.get(Constants.TESTRIDESTARTDATE).getAsString())) ? Boolean.TRUE
						: Boolean.FALSE;
				String phoneNumber = jsonObject.get(Constants.PHONENUMBERC) != null
						? jsonObject.get(Constants.PHONENUMBERC).getAsString()
						: StringUtils.EMPTY;
				String servicePhoneNumber = jsonObject.get(Constants.SERVICEPHNUMBERC) != null
						? jsonObject.get(Constants.SERVICEPHNUMBERC).getAsString()
						: StringUtils.EMPTY;
				String pincode = setAddress(jsonObject, rootObject, httpGet, token, httpClient);
				setGeoLocation(jsonObject, rootObject);
				rootObject.addProperty(idkey, id);
				rootObject.addProperty(Constants.EXPERIENCECENTERNAME, experienceCenterName);
				rootObject.addProperty(Constants.TYPE, type);
				rootObject.addProperty(Constants.ACCOUNTPARTNERIDKEY, partnerId);
				rootObject.addProperty(Constants.TESTRIDEAVAILABLE, isTestRideAvailable);
				rootObject.addProperty(Constants.PHONENUMBER, phoneNumber);
				rootObject.addProperty(Constants.SERVICEPHONENUMBER, servicePhoneNumber);
				if (Boolean.TRUE.equals(isActive) && !StringUtils.isEmpty(pincode)) {
					nearByBranchesArray.add(rootObject);
					json = gson.toJson(nearByBranchesArray);
				}
			}			
			List<JsonObject> list = new ArrayList<>();
			for(JsonElement values : nearByBranchesArray) {
			list.add(values.getAsJsonObject());
			}
			Collections.sort(list, new Comparator<JsonObject>() {		
				@Override
				public int compare(JsonObject a, JsonObject b) {
					String str1 = "";
					String str2 = "";
					str1 = a.get(Constants.EXPERIENCECENTERNAME).toString();
					str2 = b.get(Constants.EXPERIENCECENTERNAME).toString();
					return str1.compareTo(str2);
				}
			});
			json = gson.toJson(list);
			resp.getWriter().write(json);

		} finally {
			httpClient.close();
		}
	}

	/**
	 * Method to Set Geo Location In Response.
	 * 
	 * @param jsonObject
	 * @param rootObject
	 */
	private void setGeoLocation(JsonObject jsonObject, JsonObject rootObject) {
		if (!jsonObject.get(Constants.GEOLOCATION).isJsonNull()
				&& Objects.nonNull(jsonObject.get(Constants.GEOLOCATION))) {

			JsonObject geoLocation = jsonObject.get(Constants.GEOLOCATION).getAsJsonObject();

			String latitude = geoLocation.get(Constants.LATITUDE) != null
					? geoLocation.get(Constants.LATITUDE).getAsString()
					: StringUtils.EMPTY;
			String longitude = geoLocation.get(Constants.LONGITUDE) != null
					? geoLocation.get(Constants.LONGITUDE).getAsString()
					: StringUtils.EMPTY;
			rootObject.addProperty(Constants.LATITUDE, latitude);
			rootObject.addProperty(Constants.LONGITUDE, longitude);
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
	 */
	private String setAddress(JsonObject jsonObject, JsonObject rootObject, HttpGet httpGet, String token,
			CloseableHttpClient httpClient) {
		log.debug("Parameters for setAddress method-\t" + jsonObject + "\t" + rootObject + "\t" + httpGet + "\t" + token
				+ "\t" + httpClient);
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
					httpGet = new HttpGet(addressApi);

					httpGet.addHeader(Constants.AUTHORIZATION, token);
					httpGet.addHeader(Constants.CONTENTTYPE, JSONResponse.RESPONSE_CONTENT_TYPE);
					CloseableHttpResponse httpResponse = httpClient.execute(httpGet);

					String addressJson = EntityUtils.toString(httpResponse.getEntity());

					Gson gson = new Gson();
					JsonElement addressEle = gson.fromJson(addressJson, JsonElement.class);
					JsonObject addressObject = addressEle.getAsJsonObject();
					String address = addressObject.get(Constants.ADDRESS_C) != null ? addressObject.get(Constants.ADDRESS_C).getAsString() : StringUtils.EMPTY;
					rootObject.addProperty(Constants.ADDRESSKEY, address);
					rootObject.addProperty(Constants.PINCODE, postalCode);
					return postalCode;
				}
			}

		} catch (ClientProtocolException clientProtocolException) {
			log.error("ClientProtocolException occured method: setAddress cause : %s ",
					clientProtocolException);

		} catch (IOException iOException) {
			log.error("IOException occured method: setAddress cause : %s ", iOException);
		}
		return StringUtils.EMPTY;
		
	}

}
