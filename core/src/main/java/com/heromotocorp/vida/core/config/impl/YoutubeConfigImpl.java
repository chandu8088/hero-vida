package com.heromotocorp.vida.core.config.impl;

import java.io.IOException;

import javax.jcr.Session;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.servlets.post.JSONResponse;
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
import com.heromotocorp.vida.core.config.YoutubeConfig;
import com.heromotocorp.vida.core.utils.CommonUtils;
import com.heromotocorp.vida.core.utils.Constants;

/**
 * Osgi Config for Youtube Configuration.
 *
 */
@Component(service = YoutubeConfig.class, immediate = true)
@Designate(ocd = YoutubeConfigImpl.ServiceConfig.class)
public class YoutubeConfigImpl implements YoutubeConfig {

	/** The Resolver Factory. */
	@Reference
	private ResourceResolverFactory resolverFactory;

	/** The Replicator. */
	@Reference
	private Replicator replicator;

	/** The Constant log. */
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	/** The Constant Channel Id. */
	private String channelId = "part=snippet&channelId=";
	
	/** The Constant Max Results. */
	private String maxresults = "&maxResults=10&order=date&type=video&key=";

	/**
	 * The Interface ServiceConfig.
	 */
	@ObjectClassDefinition(name = "Youtube Configuration", description = "This is Youtube Configuration")
	public @interface ServiceConfig {

		/**
		 * YouTube API Key.
		 *
		 * @return the string
		 */
		@AttributeDefinition(name = "Youtube API Key", description = "This is Youtube API Key", type = AttributeType.STRING)
		public String youtubeAPIKey();

		/**
		 * Youtube Channel Id.
		 *
		 * @return the string
		 */
		@AttributeDefinition(name = "Youtube Channel Id", description = "This is Youtube Channel Id", type = AttributeType.STRING)
		public String youtubeChannelId();

		@AttributeDefinition(name = "Youtube JSON Path", description = "This is Youtube JSON Path", type = AttributeType.STRING)
		public String youtubeJsonPath();

		/**
		 * YouTube API End Point.
		 *
		 * @return the string
		 */
		@AttributeDefinition(name = "YouTube API End Point", description = "This is YouTube API End Point", type = AttributeType.STRING)
		public String youtubeAPIEndPoint();

	}

	/** YouTube API Key. */
	private String youtubeAPIKey;

	/** YouTube Channel Id. */
	private String youtubeChannelId;

	/** YouTube JSON Path. */
	private String youtubeJsonPath;

	/** YouTube API End Point . */
	private String youtubeAPIEndPoint;

	/**
	 * Activate Method.
	 *
	 * @param serviceConfig the service config
	 */
	@Activate
	protected void activate(ServiceConfig serviceConfig) {
		log.info("Parameters for activate method in YoutubeConfigImpl-\t" + serviceConfig);

		youtubeAPIKey = serviceConfig.youtubeAPIKey();
		youtubeChannelId = serviceConfig.youtubeChannelId();
		youtubeJsonPath = serviceConfig.youtubeJsonPath();
		youtubeAPIEndPoint = serviceConfig.youtubeAPIEndPoint();

	}

	/**
	 * Method to get YouTube Videos from Channel.
	 * 
	 * @return jsonArray
	 */
	@Override
	public String getYoutubeVideos() {
		CloseableHttpClient httpClient = HttpClients.createDefault();

		try {
			
			String url = new StringBuilder(youtubeAPIEndPoint).append(channelId)
					.append(youtubeChannelId).append(maxresults)
					.append(youtubeAPIKey).toString();

			HttpGet httpGet = new HttpGet(url);
			httpGet.addHeader(Constants.CONTENTTYPE, JSONResponse.RESPONSE_CONTENT_TYPE);

			CloseableHttpResponse response = httpClient.execute(httpGet);
			
			if (response.getStatusLine().getStatusCode() == 200) {
			String jsonString = EntityUtils.toString(response.getEntity());
			Gson gson = new Gson();
			JsonElement element = gson.fromJson(jsonString, JsonElement.class);
			JsonObject jsonObject = element.getAsJsonObject();
			JsonArray jsonArray = new JsonArray();
			jsonArray.add(jsonObject);
			ResourceResolver resolver = CommonUtils.getResourceResolver(resolverFactory, Constants.WRITERSERVICEUSER);
			CommonUtils.createDamAsset(jsonArray.toString(), resolver, youtubeJsonPath);
			Session session = resolver.adaptTo(Session.class);
			CommonUtils.replicateContent(session, youtubeJsonPath, replicator);
			return jsonArray.toString();
			}
		} catch (ClientProtocolException e) {
			log.error("ClientProtocolException  occured method: getYoutubeVideos cause : %s", e);
		} catch (IOException e) {
			log.error("IOException  occured method: getYoutubeVideos cause : %s", e);
		} finally {
			try {
				httpClient.close();
			} catch (IOException e) {
				log.error("IOException  occured method: getLocationData cause : %s", e.getCause());

			}
		}

		return null;
	}

	/**
	 * YouTube API Key
	 * 
	 * @return youtubeAPIKey
	 */
	@Override
	public String youtubeAPIKey() {
		return youtubeAPIKey;
	}

	/**
	 * YouTube Channel Id.
	 * 
	 * @return youtubeChannelId
	 */
	@Override
	public String youtubeChannelId() {
		return youtubeChannelId;
	}

	/**
	 * YouTube JSON DAM Path.
	 * 
	 * @return youtubeJsonPath
	 */
	@Override
	public String youtubeJsonPath() {
		return youtubeJsonPath;
	}

	/**
	 * YouTube API End Point.
	 * 
	 * @return youtubeAPIEndPoint
	 */
	@Override
	public String youtubeAPIEndPoint() {
		return youtubeAPIEndPoint;
	}
}
