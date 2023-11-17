package com.heromotocorp.vida.core.models;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.dam.api.Asset;
import com.day.cq.dam.api.Rendition;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.heromotocorp.vida.core.config.TwitterConfig;
import com.heromotocorp.vida.core.config.YoutubeConfig;
import com.heromotocorp.vida.core.schedulers.MapAPIScheduler;
import com.heromotocorp.vida.core.service.InstagramService;
import com.heromotocorp.vida.core.utils.Constants;
import com.heromotocorp.vida.core.vo.InstaVO;
import com.heromotocorp.vida.core.vo.TwitterVO;
import com.heromotocorp.vida.core.vo.YoutubeVO;

/**
 * This class reads YouTube, Twitter and InstaGram JSON.
 *
 */
@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class SocialFeedModel {

	/** The Constant log. */
	private static final Logger LOG = LoggerFactory.getLogger(SocialFeedModel.class);

	/**
	 * ResourceResolver.
	 */
	@SlingObject
	private ResourceResolver resourceResolver;

	/**
	 * Twitter Text.
	 */
	List<TwitterVO> twitterText = new ArrayList<>();

	/**
	 * InstaGram Image.
	 */
	List<InstaVO> insta = new ArrayList<>();

	/**
	 * List to get YouTube Details.
	 */
	List<YoutubeVO> youtube = new ArrayList<>();

	/**
	 * TwitterConfig.
	 */
	@OSGiService
	TwitterConfig twitterConfig;

	/**
	 * YoutubeConfig.
	 */
	@OSGiService
	YoutubeConfig youtubeConfig;

	/**
	 * InstagramService.
	 */
	@OSGiService
	InstagramService instagramService;

	/**
	 * Init Method.
	 */
	@PostConstruct
	protected void init() {

		readTwitterJson();
		readInstagramJson();
		readYoutubeJson();

	}

	/**
	 * Method that reads the YouTube JSON.
	 */
	private void readYoutubeJson() {
		String location = youtubeConfig.youtubeJsonPath();
		JsonElement element = readJson(location);
		if (Objects.nonNull(element)) {
			JsonArray jsonArray = element.getAsJsonArray();
			if (Objects.nonNull(jsonArray)) {
				for (JsonElement jsonElement : jsonArray) {
					JsonObject jsonObject = jsonElement.getAsJsonObject();
					if (Objects.nonNull(jsonObject) && Objects.nonNull(jsonObject.get(Constants.ITEMS))) {
						JsonArray itemsArray = jsonObject.get(Constants.ITEMS).getAsJsonArray();
						if (Objects.nonNull(itemsArray)) {
							for (JsonElement itemsElement : itemsArray) {
								YoutubeVO youtubeVO = new YoutubeVO();

								JsonObject itemsObject = itemsElement.getAsJsonObject();
								if (Objects.nonNull(itemsObject)) {

									JsonObject idObject = itemsObject.get("id").getAsJsonObject();
									if (Objects.nonNull(idObject)) {
										String videoId = idObject.get(Constants.VIDEOID) != null
												? idObject.get(Constants.VIDEOID).getAsString()
												: StringUtils.EMPTY;
										JsonObject snippetObject = itemsObject.get(Constants.SNIPPET).getAsJsonObject();
										if (Objects.nonNull(snippetObject)) {
											String title = snippetObject.get(Constants.TITLE) != null
													? snippetObject.get(Constants.TITLE).getAsString()
													: StringUtils.EMPTY;
											String desc = snippetObject.get(Constants.DESCRIPTION) != null
													? snippetObject.get(Constants.DESCRIPTION).getAsString()
													: StringUtils.EMPTY;
											youtubeVO.setVideoId(
													new StringBuilder(Constants.YOUTUBEURL).append(videoId).toString());
											youtubeVO.setDesc(desc);
											youtubeVO.setTitle(title);

											youtube.add(youtubeVO);
										}
									}

								}
							}
						}
					}
				}
			}
		}

	}

	/**
	 * Method that reads the InstaGram JSON.
	 */
	private void readInstagramJson() {
		String location = instagramService.getInstaGramJsonPath();
		JsonElement element = readJson(location);
		if (Objects.nonNull(element)) {
			JsonArray jsonArray = element.getAsJsonArray();
			if (Objects.nonNull(jsonArray)) {
				for (JsonElement jsonElement : jsonArray) {

					JsonObject jsonObject = jsonElement.getAsJsonObject();
					if (Objects.nonNull(jsonObject) && Objects.nonNull(jsonObject.get(Constants.DATA))) {
						JsonArray dataArray = jsonObject.get(Constants.DATA).getAsJsonArray();
						if (Objects.nonNull(dataArray)) {
							for (JsonElement dataElement : dataArray) {
								InstaVO instaVO = new InstaVO();
								JsonObject dataObject = dataElement.getAsJsonObject();
								if (Objects.nonNull(dataObject)) {
									String mediaUrl = dataObject.get(Constants.MEDIAURL) != null
											? dataObject.get(Constants.MEDIAURL).getAsString()
											: StringUtils.EMPTY;
									String url = dataObject.get(Constants.PERMALINK) != null
											? dataObject.get(Constants.PERMALINK).getAsString()
											: StringUtils.EMPTY;
									instaVO.setMediaUrl(mediaUrl);
									instaVO.setUrl(url);
									insta.add(instaVO);
								}
							}

						}
					}
				}
			}

		}

	}

	/**
	 * Method that reads the Twitter JSON.
	 */
	private void readTwitterJson() {
		String location = twitterConfig.twitterJsonPath();
		JsonElement element = readJson(location);
		if (Objects.nonNull(element)) {
			JsonArray jsonArray = element.getAsJsonArray();
			if (Objects.nonNull(jsonArray)) {
				for (JsonElement jsonElement : jsonArray) {
					JsonObject jsonObject = jsonElement.getAsJsonObject();
					if (Objects.nonNull(jsonObject)) {
						TwitterVO twitterVO = new TwitterVO();
						if (Objects.nonNull(jsonObject.get(Constants.TEXT))) {
							twitterVO.setText(jsonObject.get(Constants.TEXT) != null
									? jsonObject.get(Constants.TEXT).getAsString()
									: StringUtils.EMPTY);
							JsonObject entitiesObject = jsonObject.get(Constants.ENTITIES).getAsJsonObject();
							if (Objects.nonNull(entitiesObject) && Objects.nonNull(entitiesObject.get(Constants.URLS))) {
								JsonArray urlsArray = entitiesObject.get(Constants.URLS).getAsJsonArray();
								if (Objects.nonNull(urlsArray)) {
									for (JsonElement urlsElement : urlsArray) {
										JsonObject urlsObject = urlsElement.getAsJsonObject();
										if (Objects.nonNull(urlsObject)) {
											twitterVO.setUrl(
													urlsObject.get(Constants.URL) != null ? urlsObject.get(Constants.URL).getAsString()
															: StringUtils.EMPTY);
										}
										twitterText.add(twitterVO);
									}
								}
							}
						}
					}
				}

			}
		}

	}

	/**
	 * Method that reads JSON from DAM Asset.
	 */
	private JsonElement readJson(String location) {
		Resource assetResource = resourceResolver.getResource(location);
		Asset asset = assetResource.adaptTo(Asset.class);
		Rendition original = asset.getOriginal();
		try {
			if (original != null) {
				InputStream content = original.adaptTo(InputStream.class);

				StringBuilder sb = new StringBuilder();

				String line;
				BufferedReader br = new BufferedReader(new InputStreamReader(content, StandardCharsets.UTF_8));

				while ((line = br.readLine()) != null) {
					sb.append(line);
				}

				Gson gson = new Gson();
				return gson.fromJson(sb.toString(), JsonElement.class);
			}
		} catch (IOException e) {
			LOG.error("IOException occured method: readJson cause : %s", e);
		}
		return null;

	}

	/**
	 * Gets Twitter Text.
	 * 
	 * @return twitterText
	 */
	public List<TwitterVO> getTwitterText() {
		return twitterText;
	}

	/**
	 * Gets InstaGram Image.
	 * 
	 * @return instaImage
	 */
	public List<InstaVO> getInsta() {
		return insta;
	}

	/**
	 * Gets YouTube Details.
	 * 
	 * @return youtube
	 */
	public List<YoutubeVO> getYoutube() {
		return youtube;
	}

}
