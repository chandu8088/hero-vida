package com.heromotocorp.vida.core.models;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

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
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.heromotocorp.vida.core.config.GlobalConfig;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class NetworkCodesModel {

	/** The Constant log. */
	private Logger log = LoggerFactory.getLogger(this.getClass());

	/** The resolver. */
	@SlingObject
	private ResourceResolver resolver;

	/** The global config. */
	@OSGiService
	private GlobalConfig globalConfig;

	public static final String CONTENT_DAM_VIDA_CONFIG_NETWORKCODES = "/content/dam/vida/config/networkCodes.json";

	public JsonArray getNetworkCodes() {
		String location = globalConfig.networkCodesUrl() == null ? CONTENT_DAM_VIDA_CONFIG_NETWORKCODES
				: globalConfig.networkCodesUrl();
		Resource assetResource = resolver.getResource(location);
		Asset asset = assetResource.adaptTo(Asset.class);
		Rendition original = asset.getOriginal();
		JsonArray jsonObject = new JsonArray();
		if (original != null) {

			InputStream stream = original.getStream();
			List<String> records = new ArrayList<>();
			try (java.util.Scanner scanner = new java.util.Scanner(stream);) {
				while (scanner.hasNextLine()) {
					records.add(scanner.nextLine());
				}
				for (String record : records) {

					Gson gson = new GsonBuilder().setPrettyPrinting().create();
					JsonElement element = gson.fromJson(record, JsonElement.class);
					jsonObject = element.getAsJsonArray();
				}
			}
		}
		return jsonObject;
	}
}
