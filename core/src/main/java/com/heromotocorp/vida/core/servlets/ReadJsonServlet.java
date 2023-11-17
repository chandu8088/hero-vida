package com.heromotocorp.vida.core.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.propertytypes.ServiceDescription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.commons.PathInfo;
import com.day.cq.dam.api.Asset;
import com.day.cq.dam.api.Rendition;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.heromotocorp.vida.core.config.ClientConfig;
import com.heromotocorp.vida.core.config.GlobalConfig;

/**
 * Servlet to read the json of given path and send to response.
 *
 */
@Component(service = { Servlet.class })
@SlingServletResourceTypes(resourceTypes = "vida/components/page", methods = HttpConstants.METHOD_GET, selectors = {
		"city-master-json", "product-master-json", "price-master-json", "map-json" }, extensions = "json")
@ServiceDescription("Servlet to Read Json")
public class ReadJsonServlet extends SlingSafeMethodsServlet {

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 5784786431941197517L;

	/** The Constant log. */
	private static final Logger log = LoggerFactory.getLogger(ReadJsonServlet.class);

	/** The Global Service. */
	@Reference
	private transient GlobalConfig globalConfig;

	/** The client config. */
	@Reference
	private transient ClientConfig clientConfig;

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
		String selector = array[0];
		String jsonString = StringUtils.EMPTY;
		if (selector.equals("city-master-json")) {
			JsonElement jsonElement = readJson(globalConfig.productBranchesUrl(), req);
			jsonString = jsonElement != null ? jsonElement.getAsJsonArray().toString() : "";
		} else if (selector.equals("product-master-json")) {
			JsonElement jsonElement = readJson(globalConfig.productListUrl(), req);
			jsonString = jsonElement != null ? jsonElement.getAsJsonObject().toString() : "";
		} else if (selector.equals("price-master-json")) {
			JsonElement jsonElement = readJson(globalConfig.productPriceUrl(), req);
			jsonString = jsonElement != null ? jsonElement.getAsJsonArray().toString() : "";
		} else if (selector.equals("map-json")) {
			JsonElement jsonElement = readJson(globalConfig.storeDetailsUrl(), req);
			jsonString = jsonElement != null ? jsonElement.getAsJsonArray().toString() : "";
		}

		resp.getWriter().write(jsonString);

	}

	/**
	 * Method that reads JSON from dam path.
	 * 
	 * @param location
	 * @param req
	 * @return JsonElement
	 */
	private JsonElement readJson(String location, SlingHttpServletRequest req) {
		log.debug("Parameters for readJson method-\t" + location + "\t" + req);
		Resource assetResource = req.getResourceResolver().getResource(location);
		if (Objects.nonNull(assetResource)) {
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
				log.error("IOException occured method: readJson cause : %s", e);
			}
		}
		return null;

	}
}