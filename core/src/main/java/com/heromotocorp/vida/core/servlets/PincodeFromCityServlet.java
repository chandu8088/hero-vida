package com.heromotocorp.vida.core.servlets;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

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
 * The Class PincodeFromCityServlet.
 */
@Component(service = { Servlet.class })
@SlingServletResourceTypes(resourceTypes = "vida/components/page", methods = HttpConstants.METHOD_GET, selectors = "pincodecity", extensions = "json")
@ServiceDescription("Dealers Branches Servlet")
public class PincodeFromCityServlet extends SlingSafeMethodsServlet {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3592470025905082741L;

	/** The Constant log. */
	private static final Logger log = LoggerFactory.getLogger(PincodeFromCityServlet.class);

	private static final String QUERY1 = "?q=select+dmpl__PostalCodeId__r.name+from+dmpl__BranchPostalCode__c+where+dmpl__PostalCodeId__r.dmpl__City__c=";
	private static final String QUERY2 = "+and+dmpl__BranchId__r.dmpl__IsActive__c=true+and+dmpl__BranchId__r.dmpl__BranchType__c+in+('Pop-Up+Store','Experience+Centre')+group+by+dmpl__PostalCodeId__r.name";

	/** The client config. */
	@Reference
	private transient ClientConfig clientConfig;

	/** The global config. */
	@Reference
	private transient GlobalConfig globalConfig;

	/**
	 * Do get.
	 *
	 * @param req  the req
	 * @param resp the resp
	 * @throws ServletException the servlet exception
	 * @throws IOException      Signals that an I/O exception has occurred.
	 */
	@Override
	protected void doGet(final SlingHttpServletRequest req, final SlingHttpServletResponse resp)
			throws ServletException, IOException {

		PathInfo pathinf = new PathInfo(req.getPathInfo());
		String[] array = pathinf.getSelectors();

		HttpPost postRequst = new HttpPost(globalConfig.postRequestToken());

		List<org.apache.http.NameValuePair> nvps = new ArrayList<>();

		nvps.add(new BasicNameValuePair("grant_type", globalConfig.grantType()));
		nvps.add(new BasicNameValuePair("client_id", globalConfig.clientId()));
		nvps.add(new BasicNameValuePair("client_secret", globalConfig.clientSecret()));
		nvps.add(new BasicNameValuePair("username", globalConfig.username()));
		nvps.add(new BasicNameValuePair("password", globalConfig.password()));
		postRequst.setHeader(new BasicHeader("Content-Type", globalConfig.contentType()));
		postRequst.setEntity(new UrlEncodedFormEntity(nvps));

		if (Objects.nonNull(clientConfig) && Objects.nonNull(clientConfig.client())) {
			CloseableHttpResponse response = clientConfig.client().execute(postRequst);
			String jsonString = EntityUtils.toString(response.getEntity());

			Gson gson = new Gson();
			JsonElement element = gson.fromJson(jsonString, JsonElement.class);
			JsonObject jsonObject = element.getAsJsonObject();
			String token = "Bearer " + jsonObject.get("access_token").getAsString();

			if (array.length == 4) {
				JsonArray cityAllPincode = getPincodeFormCity(token, array);
				JsonArray cityServiceablePincode = getServiceablePincode(token, array);
				JsonObject pincodeJsonObject = new JsonObject();
				pincodeJsonObject.add("allPincodes", cityAllPincode);
				pincodeJsonObject.add("serviceablePincodes", cityServiceablePincode);
				resp.getWriter().write(pincodeJsonObject.toString());

			}
		}
	}

	/**
	 * Gets the All the Pincode of City.
	 *
	 * @param token the token
	 * @param array the array
	 * @param resp  the resp
	 * @return the pincode form city
	 * @throws IOException          Signals that an I/O exception has occurred.
	 * @throws InterruptedException the interrupted exception
	 */
	private JsonArray getPincodeFormCity(String token, String[] array) {
		log.debug("Parameters for getPincodeFormCity method-\t" + token + "\t" + array);
		JsonArray jsonArray = new JsonArray();
		CloseableHttpClient httpClient = HttpClients.createDefault();
		try {
			String queryString = new StringBuilder(Constants.DMPL_COUNTRY).append(URLEncoder.encode(array[1], Constants.UTF))
					.append(Constants.DMPL_STATE).append(URLEncoder.encode(array[2], Constants.UTF))
					.append(Constants.DMPL_CITY).append(URLEncoder.encode(array[3], Constants.UTF))
					.append(Constants.SINGLE_QUOTE).toString();
			String pincodeUrl = new StringBuilder(globalConfig.pincodeCityReqUrl()).append(queryString).toString();
			HttpGet httpGet = new HttpGet(pincodeUrl);
			httpGet.addHeader("Authorization", token);
			httpGet.addHeader("Content-Type", JSONResponse.RESPONSE_CONTENT_TYPE);
			CloseableHttpResponse httpResponse = null;
			httpResponse = httpClient.execute(httpGet);
			if (Objects.nonNull(httpResponse)) {
				String jsonString = EntityUtils.toString(httpResponse.getEntity());

				Gson gson = new Gson();
				JsonElement element = gson.fromJson(jsonString, JsonElement.class);
				JsonObject jsonObject = element.getAsJsonObject();
				JsonArray rootRecordArray = jsonObject.get(Constants.RECORDS).getAsJsonArray();

				for (JsonElement jsonElement : rootRecordArray) {

					JsonObject recordsObject = jsonElement.getAsJsonObject();

					String pinCode = recordsObject.get(Constants.NAME) != null
							? recordsObject.get(Constants.NAME).getAsString()
							: StringUtils.EMPTY;
					jsonArray.add(Integer.parseInt(pinCode));

				}

			}
		} catch (ParseException | IOException e1) {
			log.error("ParseException or  IOException occured method: getPincodeFormCity cause : %s", e1);
		} finally {
			try {
				httpClient.close();
			} catch (IOException e) {
				log.error("IOException occured method: getPincodeFormCity cause : %s", e);

			}
		}
		return jsonArray;
	}

	/**
	 * Method to get only Serviceable Pincode of City.
	 * 
	 * @param token
	 * @param array
	 * @return
	 */
	private JsonArray getServiceablePincode(String token, String[] array) {
		log.debug("Parameters for getServiceablePincode method-\t" + token + "\t" + array);
		JsonArray serviceablePincode = new JsonArray();
		@SuppressWarnings("squid:S2095")
		CloseableHttpClient httpClient = HttpClients.createDefault();
		String queryString;
		try {
			queryString = new StringBuilder(QUERY1).append("'").append(URLEncoder.encode(array[3], "UTF-8")).append("'")
					.append(QUERY2).toString();

			String serviceablePincodeUrl = new StringBuilder(globalConfig.sfdcQueryEndpoint())
					.append(queryString).toString();
			HttpGet httpGet = new HttpGet(serviceablePincodeUrl);
			httpGet.addHeader("Authorization", token);
			httpGet.addHeader("Content-Type", JSONResponse.RESPONSE_CONTENT_TYPE);
			CloseableHttpResponse httpResponse = null;
			httpResponse = httpClient.execute(httpGet);
			if (Objects.nonNull(httpResponse)) {
				String jsonString = EntityUtils.toString(httpResponse.getEntity());

				Gson gson = new Gson();
				JsonElement element = gson.fromJson(jsonString, JsonElement.class);
				JsonObject jsonObject = element.getAsJsonObject();

				JsonArray rootRecordArray = jsonObject.get(Constants.RECORDS).getAsJsonArray();

				for (JsonElement jsonElement : rootRecordArray) {
					JsonObject recordsObject = jsonElement.getAsJsonObject();

					String pinCode = recordsObject.get(Constants.NAME) != null
							? recordsObject.get(Constants.NAME).getAsString()
							: StringUtils.EMPTY;

					serviceablePincode.add(Integer.parseInt(pinCode));

				}

			}

		} catch (ParseException | IOException e1) {
			log.error("IOException occured method: getServiceablePincode cause : %s", e1);
		}

		return serviceablePincode;
	}
}
