package com.heromotocorp.vida.core.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.propertytypes.ServiceDescription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.commons.PathInfo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
@SlingServletResourceTypes(resourceTypes = Constants.PAGERESOURCETYPE, methods = HttpConstants.METHOD_GET, selectors = "vehicleavailability", extensions = "json")
@ServiceDescription("Vehicle Availability Servlet")
public class LTTRVehicleAvailabilityServlet extends SlingSafeMethodsServlet {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3592470025905082741L;
	
	/** The Constant log. */
	private static final Logger log = LoggerFactory.getLogger(LTTRVehicleAvailabilityServlet.class);

	/** The client config. */
	@Reference
	private transient ClientConfig clientConfig;

	/** The global config. */
	@Reference
	private transient GlobalConfig globalConfig;


	/**
	 * Do get.
	 *
	 * @param req the req
	 * @param resp the resp
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Override
	protected void doGet(final SlingHttpServletRequest req, final SlingHttpServletResponse resp)
			throws ServletException, IOException {

		PathInfo pathinf = new PathInfo(req.getPathInfo());
		String[] array = pathinf.getSelectors();

		if (array.length > 0) {
			int cityId = Integer.parseInt(array[1]);
			//int packageid = Integer.parseInt(array[2]);
			processVehicleMaster(clientConfig.client(), resp, cityId);
		}

	}

	/**
	 * Process vehicle master.
	 *
	 * @param httpClient the http client
	 * @param resp the resp
	 * @param cityId the city id
	 * @param packageid the packageid
	 */
	private void processVehicleMaster(CloseableHttpClient httpClient, SlingHttpServletResponse resp, int cityId
			) {

				/*
				 * DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				 * LocalDateTime now = LocalDateTime.now();
				 */
		String apiKey = globalConfig.lttrApiKey();
		String lttrBaseEndPoint = globalConfig.lttrBaseEndPoint();

		HttpGet httpGet = new HttpGet(new StringBuilder(lttrBaseEndPoint)
				.append(Constants.LTTR_VEHICLE_AVAILBILITY_ENDPOINT).append("&city_id=").append(cityId).toString());

		httpGet.addHeader(Constants.X_API_KEY, apiKey);

		CloseableHttpResponse httpResponse = null;
		try {
			httpResponse = httpClient.execute(httpGet);

			if (Objects.nonNull(httpResponse)) {
				ArrayList<String> arrayList = new ArrayList<>();
				String jsonString = EntityUtils.toString(httpResponse.getEntity());
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				JsonElement element = gson.fromJson(jsonString, JsonElement.class);
				JsonObject jsonObject = element.getAsJsonObject();
				boolean success = jsonObject.get("IsSuccess").getAsBoolean();
				if (success) {
					JsonArray rootRecordArray = jsonObject.get("Result").getAsJsonArray();
					String json = gson.toJson(rootRecordArray);
					log.debug("LTTRVehicleAvailabilityServlet JSON\t");
					log.debug(json);
					resp.getWriter().write(json);
				}
				else {
					resp.getWriter().write(arrayList.toString());
				}
			}

		} catch (IOException e1) {
			log.error("IOException  occured method:", e1);

		}

	}

}
