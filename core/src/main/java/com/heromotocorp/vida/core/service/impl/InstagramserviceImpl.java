package com.heromotocorp.vida.core.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.jcr.Session;

import org.apache.commons.io.IOUtils;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.replication.Replicator;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.heromotocorp.vida.core.service.InstagramService;
import com.heromotocorp.vida.core.utils.CommonUtils;
import com.heromotocorp.vida.core.utils.Constants;


/**
 * The type Instagramservice.
 */
@Component(service=InstagramService.class, immediate=true)
@Designate(ocd=InstagramserviceImpl.Config.class)
public class InstagramserviceImpl implements InstagramService {

	/** The Constant log. */
	private static final Logger LOG = LoggerFactory.getLogger(InstagramserviceImpl.class);
	
	@Reference
	private ResourceResolverFactory resolverFactory;

	/** The Replicator. */
	@Reference
	private Replicator replicator;

	private static final Logger LOGGER = LoggerFactory.getLogger(InstagramserviceImpl.class);
	/**
	 * The Instagram account id.
	 */
	String instagramAccountId;

	/**
	 * The Instagram access token.
	 */
	String instagramAccessToken;

	/**
	 * The Instagram url.
	 */
	String instagramUrl;

	/**
	 * The Instagram json.
	 */
	String instagramJSON;

	/**
	 * The Instagram json path.
	 */
	String instaGramJsonPath;

	/**
	 * The interface Config.
	 */

	@ObjectClassDefinition(name="Olamcorp Instagram Api Details",
            description = "Instagram Api values")
	public static @interface Config {
		/**
		 * Instagram account id string.
		 *
		 * @return the string
		 */
		@AttributeDefinition(name = "Instagram Account Id", type = AttributeType.STRING)
		String instagramAccountId();

		/**
		 * Instagram access token string.
		 *
		 * @return the string
		 */
		@AttributeDefinition(name = "Instagram Access Token", type = AttributeType.STRING)
		String instagramAccessToken();

		/**
		 * Instagram url string.
		 *
		 * @return the string
		 */
		@AttributeDefinition(name = "Instagram dev Url", type = AttributeType.STRING)
		String instagramUrl();

		/**
		 * Instagram json string.
		 *
		 * @return the string
		 */
		@AttributeDefinition(name = "Instagram feed json", type = AttributeType.STRING)
		String  instagramJSON();

		/**
		 * Instagram json path.
		 *
		 * @return the string
		 */
		@AttributeDefinition(name = "Instagram feed json dam path", type = AttributeType.STRING)
		String  instaGramJsonPath();
	}

	/**
	 * Activate.
	 *
	 * @param config the config
	 */
	@Activate
    protected void activate(Config config) {
		LOG.info("Parameters for activate method in InstagramserviceImpl-\t" + config);
		instagramAccountId = config.instagramAccountId();
		instagramAccessToken=config.instagramAccessToken();
		instagramUrl=config.instagramUrl();
		instagramJSON=config.instagramJSON();
		instaGramJsonPath=config.instaGramJsonPath();
    }

	/**
	 * User Agent object
	 */
	private static final String USER_AGENT = "Mozilla/5.0";

	@Override
	public String getInstagramAccountId() {
		return instagramAccountId;
	}

	@Override
	public String getInstagramAccessToken() {
		return instagramAccessToken;
	}

	@Override
	public String getInstagramUrl() {
		return instagramUrl;
	}

	@Override
	public String getInstaGramJsonPath() {
		return instaGramJsonPath;
	}

	@Override
	public String getInstagramJson() {
		instagramJSON = "";
		instagramAccountId = getInstagramAccountId();
		instagramAccessToken =getInstagramAccessToken();
		instaGramJsonPath=getInstaGramJsonPath();
		URL url;
		HttpURLConnection connection = null;
		//OWR-1605 Latest Facebook Instagram Graph API
		String targetUrl = String.format(getInstagramUrl(),
				instagramAccountId,instagramAccessToken);
		try {
			url = new URL(targetUrl);
			connection = (HttpURLConnection) url.openConnection();
			connection.setConnectTimeout(5000);
			connection.setReadTimeout(5000);
			connection.setRequestMethod("GET");
			connection.setRequestProperty("User-Agent", USER_AGENT);
			int responseCode = connection.getResponseCode();
			LOGGER.info("Response Code: {}", responseCode);
		if(responseCode == 200) {
			InputStream inputStream = connection.getInputStream();
				if(inputStream != null) {
					List<String> readResponseFromStream = IOUtils.readLines(inputStream, StandardCharsets.UTF_8);
					instagramJSON = String.join(" ",readResponseFromStream);
					Gson gson = new Gson();
					JsonObject element = gson.fromJson(instagramJSON, JsonObject.class);
					ResourceResolver resolver = CommonUtils.getResourceResolver(resolverFactory,
							Constants.WRITERSERVICEUSER);
					JsonArray jsonArray = new JsonArray();
					jsonArray.add(element);
					CommonUtils.createDamAsset(jsonArray.toString(), resolver, instaGramJsonPath);
					Session session = resolver.adaptTo(Session.class);
					CommonUtils.replicateContent(session, instaGramJsonPath, replicator);
					LOGGER.info("==================Instagram Json String====================");
					return instagramJSON;
				}
				else{
					return null;
				}
			} 
		}catch (IOException e) {
			LOGGER.error("Error occured while trying to read from the response stream: {0}", e);
		}
		return null;
	}

}

