package com.heromotocorp.vida.core.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.apache.sling.servlets.post.JSONResponse;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.propertytypes.ServiceDescription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.heromotocorp.vida.core.config.ClientConfig;
import com.heromotocorp.vida.core.config.GlobalConfig;
import com.heromotocorp.vida.core.utils.CommonUtils;
import com.heromotocorp.vida.core.utils.Constants;

/**
 * Servlet that receives user input and send to SFDC for New Leads creation.
 *
 */
@Component(service = { Servlet.class })
@SlingServletResourceTypes(resourceTypes = Constants.PAGERESOURCETYPE, methods = HttpConstants.METHOD_POST, selectors = "newleadcontactus", extensions = "json")
@ServiceDescription("New Lead Creation Servlet")
public class NewLeadCreationServlet extends SlingAllMethodsServlet {

	private static final long serialVersionUID = -8995530536351764239L;

	/**
	 * The Constant log.
	 */
	private static final Logger log = LoggerFactory.getLogger(NewLeadCreationServlet.class);

	/**
	 * ClientConfig object.
	 */
	@Reference
	private transient ClientConfig clientConfig;

	/**
	 * GlobalConfig object.
	 */
	@Reference
	private transient GlobalConfig globalConfig;

	/** The ResourceResolverFactory. */
	@Reference
	private transient ResourceResolverFactory resolverFactory;

	/**
	 * The Constant CAMPAIGNSUBSOURCE.
	 */
	private static final String CAMPAIGNSUBSOURCE = "SubSources__c";

	/**
	 * The Constant LEADSUBSOURCE.
	 */
	private static final String LEADSUBSOURCE = "SubSource__c";
	
	/**
	 * The Constant HERODEALER.
	 */
	private static final String HERODEALER = "Hero Dealer";
	
	/**
	 * The Constant LEADCAMPAIGN.
	 */
	private static final String LEADCAMPAIGN = "dmpl__LeadCampaignId__c";
	
	/**
	 * The Constant LEADSUBSOURCEVALUE.
	 */
	private static final String LEADSUBSOURCEVALUE = "NA";

	/**
	 * Do Post Method.
	 */
	@Override
	protected void doPost(final SlingHttpServletRequest req, final SlingHttpServletResponse resp)
			throws ServletException, IOException {

		String token = CommonUtils.getToken(globalConfig, clientConfig);
		postData(token, resp, req);
	}

	/**
	 * Method to Post Contact Us form data to SFDC.
	 * 
	 * @param token
	 * @param resp
	 * @param req
	 */
	private void postData(String token, SlingHttpServletResponse resp, SlingHttpServletRequest req) {
		log.info("Parameters for postData method-\t" + token + "\t" + resp + "\t" + req);
		StringBuilder sb = new StringBuilder();
		String line = null;

		try {
			BufferedReader reader = req.getReader();
			while ((line = reader.readLine()) != null)
				sb.append(line);
		} catch (IOException e) {
			log.error("IOException  occured method: postData cause : %s", e);
		}

		Gson gson = new Gson();
		JsonElement element = gson.fromJson(sb.toString(), JsonElement.class);
		JsonObject jsonobject = element != null ? element.getAsJsonObject() : new JsonObject();

		JsonObject json = new JsonObject();

		if (!jsonobject.isJsonNull() && jsonobject.size() > 0) {
			String firstName = jsonobject.get(Constants.FIRSTNAME) != null
					? jsonobject.get(Constants.FIRSTNAME).getAsString()
					: StringUtils.EMPTY;
			String lastName = jsonobject.get(Constants.LASTNAME) != null
					? jsonobject.get(Constants.LASTNAME).getAsString()
					: StringUtils.EMPTY;
			String campaignSubSource = jsonobject.get(CAMPAIGNSUBSOURCE) != null
					? jsonobject.get(CAMPAIGNSUBSOURCE).getAsString()
					: StringUtils.EMPTY;
			String city = jsonobject.get(Constants.CITY) != null ? jsonobject.get(Constants.CITY).getAsString()
					: StringUtils.EMPTY;
			String state = jsonobject.get(Constants.STATE) != null ? jsonobject.get(Constants.STATE).getAsString()
					: StringUtils.EMPTY;
			String emailId = jsonobject.get(Constants.EMAIL) != null ? jsonobject.get(Constants.EMAIL).getAsString()
					: StringUtils.EMPTY;
			String mobilePhone = jsonobject.get(Constants.MOBILEPHONE) != null
					? jsonobject.get(Constants.MOBILEPHONE).getAsString()
					: StringUtils.EMPTY;
			json.addProperty(Constants.FIRSTNAME, firstName);
			json.addProperty(Constants.LASTNAME, lastName);
			json.addProperty(Constants.STATE, state);
			json.addProperty(Constants.CITY, city);
			json.addProperty(Constants.LEADSOURCE, HERODEALER);
			json.addProperty(CAMPAIGNSUBSOURCE, campaignSubSource);
			json.addProperty(LEADSUBSOURCE, LEADSUBSOURCEVALUE);
			json.addProperty(LEADCAMPAIGN, globalConfig.leadCampaignId());
			json.addProperty(Constants.EMAIL, emailId);
			json.addProperty(Constants.MOBILEPHONE, mobilePhone);

			log.info("Post request to leads");
			HttpPost postRequst = new HttpPost(globalConfig.leadsUrl());
			postRequst.setHeader(Constants.AUTHORIZATION, token);
			postRequst.setHeader(new BasicHeader(Constants.CONTENTTYPE, JSONResponse.RESPONSE_CONTENT_TYPE));
			postRequst.setEntity(new StringEntity(json.toString(), StandardCharsets.UTF_8));

			if (Objects.nonNull(clientConfig.client())) {
				try {
					CloseableHttpResponse response = clientConfig.client().execute(postRequst);
					String jsonString = EntityUtils.toString(response.getEntity());
					resp.setContentType(JSONResponse.RESPONSE_CONTENT_TYPE);
					resp.getWriter().write(jsonString);
				} catch (ParseException | IOException e) {
					log.error("Exception  occured method: postData cause : %s", e);
				}
			}

		}
	}
}