package com.heromotocorp.vida.core.config.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.jcr.Session;

import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
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
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.heromotocorp.vida.core.config.TwitterConfig;
import com.heromotocorp.vida.core.utils.CommonUtils;
import com.heromotocorp.vida.core.utils.Constants;

/**
 * Osgi configuration to Twitter Configuration.
 *
 */
@Component(service = TwitterConfig.class, immediate = true)
@Designate(ocd = TwitterConfigImpl.ServiceConfig.class)
public class TwitterConfigImpl implements TwitterConfig {

	/** The Constant log. */
	private Logger log = LoggerFactory.getLogger(this.getClass());

	/** The Resolver Factory. */
	@Reference
	private ResourceResolverFactory resolverFactory;

	/** The Replicator. */
	@Reference
	private Replicator replicator;

	@ObjectClassDefinition(name = "Twitter API Configuration", description = "Twitter API Configuration")
	public @interface ServiceConfig {

		@AttributeDefinition(name = "Twiter API Key", description = "This is Twitter API Key.", type = AttributeType.STRING)
		String twitterAPIKey();

		@AttributeDefinition(name = "Twitter Secret Key", description = "This is Twitter Secret Key.", type = AttributeType.STRING)
		String twitterAPIKeySecret();

		@AttributeDefinition(name = "Twitter End Point token", description = "This is Twitter Endpoint Token.", type = AttributeType.STRING)
		String twitterEndPointToken();

		@AttributeDefinition(name = "Twitter End Point Tweets", description = "Twitter End Point Tweets", type = AttributeType.STRING)
		String twitterEndPointTweets();

		@AttributeDefinition(name = "Twitter Json DAM Path", description = "This Twitter Json DAM Path.", type = AttributeType.STRING)
		String twitterJsonPath();
		
		@AttributeDefinition(name = "Twitter Profile Name", description = "This Twitter Profile Name", type = AttributeType.STRING)
		String twitterProfile();

	}

	/** The Twitter API Key. */
	private String twitterAPIKey;

	/** The Twitter API Secret Key. */
	private String twitterAPIKeySecret;

	/** The Twitter End Point Token. */
	private String twitterEndPointToken;

	/** The Twitter End Point Tweets. */
	private String twitterEndPointTweets;

	/** The Twitter Json DAM Asset Path. */
	private String twitterJsonPath;
	
	private String twitterProfile;

	/**
	 * This is activate method.
	 *
	 * @param serviceConfig the service config
	 */
	@Activate
	protected void activate(ServiceConfig serviceConfig) {
		log.info("Parameters for activate method in TwitterConfigImpl-\t" + serviceConfig);

		twitterAPIKey = serviceConfig.twitterAPIKey();
		twitterAPIKeySecret = serviceConfig.twitterAPIKeySecret();
		twitterEndPointToken = serviceConfig.twitterEndPointToken();
		twitterEndPointTweets = serviceConfig.twitterEndPointTweets();
		twitterJsonPath = serviceConfig.twitterJsonPath();
		twitterProfile = serviceConfig.twitterProfile();

	}

	/**
	 * Get the Bearer Token.
	 * 
	 * @return respObj
	 */
	@Override
	public String getBearerToken() {
		HttpPost post = new HttpPost(twitterEndPointToken);
		String authHeader = new StringBuilder(twitterAPIKey).append(Constants.COLON).append(twitterAPIKeySecret)
				.toString();
		String base64Credentials = new String(Base64.getEncoder().encode(authHeader.getBytes()));
		authHeader = new StringBuilder(Constants.BASIC).append(" ").append(base64Credentials).toString();
		post.setHeader(Constants.AUTHORIZATION, authHeader);
		List<NameValuePair> parameters = new ArrayList<>();
		parameters.add(new BasicNameValuePair(Constants.GRANTTYPE, Constants.CLIENTCRED));
		try {
			post.setEntity(new UrlEncodedFormEntity(parameters));
			
			
		} catch (UnsupportedEncodingException e1) {
			log.error("Error while getting bearer token {}", e1.getMessage());
		}
		try (CloseableHttpClient httpClient = HttpClients.createDefault();
				CloseableHttpResponse tokenResponse = httpClient.execute(post)) {
			if (tokenResponse.getStatusLine().getStatusCode() == 200) {
				Gson gson = new Gson();
				JsonElement element = gson.fromJson(EntityUtils.toString(tokenResponse.getEntity()), JsonElement.class);
				JsonObject respObj = element.getAsJsonObject();

				return respObj.get(Constants.ACCESSTOKEN).getAsString();
			} else {
				return null;
			}
		} catch (JsonSyntaxException | ParseException | IOException e) {
			log.error("Exception  occured method: getBearerToken cause : %s", e);
		} 
		return null;

	}

	/**
	 * Gets Tweets.
	 *
	 * @param handler
	 * @param noOfTweets
	 *
	 * @return podcastResponseObj
	 */
	@Override
	public String getTweets(String handler, int noOfTweets) {
		log.info("Parameters for getTweets method-\t" + handler + "\t" + noOfTweets);
		String token = getBearerToken();
		HttpGet getCall = new HttpGet(twitterEndPointTweets);
		getCall.setHeader(Constants.AUTHORIZATION, new StringBuilder(Constants.BEARER).append(" ").append(token).toString());
		try (CloseableHttpClient httpClient = HttpClients.createDefault();
				CloseableHttpResponse tweetsResponse = httpClient.execute(getCall)) {
			if (tweetsResponse.getStatusLine().getStatusCode() == 200) {
				String jsonString = EntityUtils.toString(tweetsResponse.getEntity());
				Gson gson = new Gson();
				JsonElement element = gson.fromJson(jsonString, JsonElement.class);
				JsonArray podcastResponseObj = element.getAsJsonArray();
				ResourceResolver resolver = CommonUtils.getResourceResolver(resolverFactory,
						Constants.WRITERSERVICEUSER);
				CommonUtils.createDamAsset(podcastResponseObj.toString(), resolver, twitterJsonPath);
				Session session = resolver.adaptTo(Session.class);
				CommonUtils.replicateContent(session, twitterJsonPath, replicator);
				return podcastResponseObj.toString();

			} else {
				return null;
			}
		} catch (ParseException | IOException e) {
			log.error("Exception occured method: getTweets cause : %s", e);
		}
		return null;
	}

	/**
	 * Get the TwitterAPi Key.
	 */
	@Override
	public String twitterAPIKey() {
		return twitterAPIKey;
	}

	/**
	 * Get the Twitter API Secret Key.
	 */
	@Override
	public String twitterAPIKeySecret() {
		return twitterAPIKeySecret;
	}

	/**
	 * Get the Twitter End Point Token.
	 */
	@Override
	public String twitterEndPointToken() {
		return twitterEndPointToken;
	}

	/**
	 * Get the Twitter End Point Tweets.
	 */
	@Override
	public String twitterEndPointTweets() {
		return twitterEndPointTweets;
	}

	/**
	 * Get the Twitter JSON DAM Path.
	 */
	@Override
	public String twitterJsonPath() {
		return twitterJsonPath;
	}

	/**
	 * Get the Twitter Profile Name.
	 */
	@Override
	public String twitterProfile() {
		return twitterProfile;
	}

}
