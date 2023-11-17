package com.heromotocorp.vida.core.utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

import javax.jcr.Binary;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.ValueFactory;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.ParseException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.servlets.post.JSONResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.dam.api.AssetManager;
import com.day.cq.replication.ReplicationActionType;
import com.day.cq.replication.ReplicationException;
import com.day.cq.replication.Replicator;
import com.day.cq.wcm.api.Page;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.heromotocorp.vida.core.config.ClientConfig;
import com.heromotocorp.vida.core.config.GlobalConfig;

/**
 * The Class CommonUtils.
 */
public class CommonUtils {

	/**
	 * Instantiates a new common utils.
	 */
	private CommonUtils() {
		throw new IllegalStateException("CommonUtils class");

	}

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(CommonUtils.class);

	/**
	 * Gets the property value.
	 *
	 * @param valueMap the value map
	 * @param property the property
	 * @return the corresponding property in the valuemap
	 */
	public static String getPropertyValue(ValueMap valueMap, String property) {
		return valueMap.containsKey(property) ? valueMap.get(property, String.class) : StringUtils.EMPTY;
	}

	/**
	 * Gets the resource resolver.
	 *
	 * @param resourceResolverFactory the resource resolver factory
	 * @param serviceUser             the service user
	 * @return the resource resolver
	 */
	public static ResourceResolver getResourceResolver(ResourceResolverFactory resourceResolverFactory,
			String serviceUser) {
		Map<String, Object> param = new HashMap<>();
		param.put(ResourceResolverFactory.SUBSERVICE, serviceUser);
		try {
			return resourceResolverFactory.getServiceResourceResolver(param);
		} catch (LoginException e) {
			LOGGER.error("LoginException  occured method: getResourceResolver cause : %s", e);
		}
		return null;
	}

	/**
	 * Method to Replicate Content.
	 *
	 * @param session         the session
	 * @param filePath the file path
	 * @param replicator      the replicator
	 */
	public static void replicateContent(Session session, String filePath, Replicator replicator) {
		try {
			replicator.replicate(session, ReplicationActionType.ACTIVATE, filePath);
			LOGGER.info("Replicated: {}", filePath);
		} catch (Exception e) {
			LOGGER.error("Exception cause : %s", e.getMessage());
		}
	}

	/**
	 * Creates JSON in AEM DAM.
	 *
	 * @param jsonString the json string
	 * @param resolver   the resolver
	 * @param jsonPath   the json path
	 */
	public static void createDamAsset(String jsonString, ResourceResolver resolver, String jsonPath) {

		InputStream is = new ByteArrayInputStream(jsonString.getBytes());
		AssetManager assetManager = resolver.adaptTo(AssetManager.class);

		Session session = resolver.adaptTo(Session.class);
		ValueFactory factory;
		try {
			factory = session.getValueFactory();
			Binary binary = factory.createBinary(is);
			assetManager.createOrUpdateAsset(jsonPath, binary, JSONResponse.RESPONSE_CONTENT_TYPE, true);
		} catch (RepositoryException e) {
			LOGGER.error("Unable to create DAM file ", e);
		}

		// assetManager.createAsset(jsonPath, is, JSONResponse.RESPONSE_CONTENT_TYPE,
		// true);

	}

	/**
	 * Method to get access token.
	 *
	 * @param globalConfig the global config
	 * @param clientConfig        the client config
	 * @return the token
	 */
	public static String getToken(GlobalConfig globalConfig, ClientConfig clientConfig) {
		HttpPost postRequst = new HttpPost(globalConfig.postRequestToken());

		List<org.apache.http.NameValuePair> nvps = new ArrayList<>();

		nvps.add(new BasicNameValuePair(Constants.GRANTTYPE, globalConfig.grantType()));
		nvps.add(new BasicNameValuePair(Constants.CLIENTID, globalConfig.clientId()));
		nvps.add(new BasicNameValuePair(Constants.CLIENTSECRET, globalConfig.clientSecret()));

		nvps.add(new BasicNameValuePair(Constants.USERNAME, globalConfig.username()));
		nvps.add(new BasicNameValuePair(Constants.PSWD, globalConfig.password()));

		postRequst.setHeader(new BasicHeader(Constants.CONTENTTYPE, globalConfig.contentType()));

		try {
			postRequst.setEntity(new UrlEncodedFormEntity(nvps));
			if (Objects.nonNull(clientConfig.client())) {
				CloseableHttpResponse response = clientConfig.client().execute(postRequst);
				String jsonString = EntityUtils.toString(response.getEntity());

				Gson gson = new Gson();
				JsonElement element = gson.fromJson(jsonString, JsonElement.class);
				JsonObject jsonObject = element.getAsJsonObject();

				return new StringBuilder(Constants.BEARER).append(Constants.TAB)
						.append(jsonObject.get(Constants.ACCESSTOKEN).getAsString()).toString();
			}

		} catch (JsonSyntaxException | ParseException | IOException e) {

			LOGGER.error("Exception  occured method: getToken cause : %s", e);
		}
		return null;
	}

	/**
	 * Method to Read Record.
	 *
	 * @param line the line
	 * @return the record from line
	 */
	public static String[] getRecordFromLine(String line) {
		List<String> values = new ArrayList<>();
		try (Scanner rowScanner = new Scanner(line)) {
			rowScanner.useDelimiter(Constants.COMA);
			while (rowScanner.hasNext()) {
				values.add(rowScanner.next());
			}
		}
		return Arrays.copyOf(values.toArray(), values.toArray().length, String[].class);
	}

	/**
	 * Update url.
	 *
	 * @param resource the resource
	 * @param url      the url
	 * @return the string
	 */
	public static String updateUrl(Resource resource, String url) {

		String[] urlArray = resource.getPath().split(Constants.PATH_SEPEARTOR);
		String pramone = urlArray[3];
		String pramtwo = urlArray[4];

		String subStringOne = StringUtils.substringAfter(url, Constants.BRAND_NAME + Constants.PATH_SEPEARTOR);
		String country = StringUtils.substringBefore(subStringOne, Constants.PATH_SEPEARTOR);
		String subStringTwo = StringUtils.substringAfter(subStringOne, country + Constants.PATH_SEPEARTOR);
		String language = StringUtils.substringBefore(subStringTwo, Constants.PATH_SEPEARTOR);
		String finalString = StringUtils.substringAfter(subStringTwo, language);

		return new StringBuilder(Constants.ROOT_PATH).append(pramone).append(Constants.PATH_SEPEARTOR).append(pramtwo)
				.append(finalString).toString();

	}

	/**
	 * Gets the country code.
	 *
	 * @param currentPage the current page
	 * @return the country code
	 */
	public static String getCountryCode(final Page currentPage) {
		Page languagePage = getLanguagePage(currentPage);
		final String country = "us";
		if (Objects.nonNull(languagePage) && Objects.nonNull(languagePage.getParent())
				&& Objects.nonNull(languagePage.getParent().getName())) {
			return languagePage.getParent().getName();
		}
		return country;
	}

	/**
	 * Gets the language page.
	 *
	 * @param currentPage the current page
	 * @return the language page
	 */
	public static Page getLanguagePage(final Page currentPage) {
		if (Objects.nonNull(currentPage)) {
			return currentPage.getAbsoluteParent(3);
		}
		return null;
	}

	/**
	 * Gets the language code.
	 *
	 * @param currentPage the current page
	 * @return the language code
	 */
	public static String getLanguageCode(final Page currentPage) {
		if (Objects.nonNull(currentPage)) {
			return currentPage.getLanguage(Boolean.FALSE).getLanguage();
		}
		return StringUtils.EMPTY;
	}

	/**
	 * Gets the url starting string.
	 *
	 * @param url the url
	 * @return the true if url starting string is https
	 */
	public static boolean getIsExternalURL(String url) {
		if (Objects.nonNull(url)) {
			String lowerCase = url.toLowerCase();
			return lowerCase.startsWith("http")||lowerCase.startsWith("https");
		}
		return false;
	}

	/**
	 * Format city name.
	 *
	 * @param message the message
	 * @return the string
	 */
	public static String formatCityName(String message) {
		// stores each characters to a char array
		char[] charArray = message.toCharArray();
		boolean foundSpace = true;

		for (int i = 0; i < charArray.length; i++) {

			// if the array element is a letter
			if (Character.isLetter(charArray[i])) {

				// check space is present before the letter
				if (foundSpace) {

					// change the letter into uppercase
					charArray[i] = Character.toUpperCase(charArray[i]);
					foundSpace = false;
				}
			}

			else {
				// if the new character is not character
				foundSpace = true;
			}
		}

		// convert the char array to the string
		return String.valueOf(charArray);

	}

	/**
	 * Rupee format.
	 *
	 * @param value the value
	 * @return the string
	 */
	public static String rupeeFormat(String value) {
		value = Integer.toString(Math.round(Float.parseFloat(value))).replace(",", "");
		char lastDigit = value.charAt(value.length() - 1);
		String result = StringUtils.EMPTY;
		int len = value.length() - 1;
		int nDigits = 0;

		for (int i = len - 1; i >= 0; i--) {
			result = value.charAt(i) + result;
			nDigits++;
			if (((nDigits % 2) == 0) && (i > 0)) {
				result = "," + result;
			}
		}
		return (result + lastDigit) + ".00";
	}

}