package com.heromotocorp.vida.core.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
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
import com.heromotocorp.vida.core.utils.RSADecryptionUtil;

/**
 * Servlet that receives user input and send to SFDC for Leads.
 *
 */
@Component(service = { Servlet.class })
@SlingServletResourceTypes(resourceTypes = Constants.PAGERESOURCETYPE, methods = HttpConstants.METHOD_POST, selectors = "contactus", extensions = "json")
@ServiceDescription("Stay In Touch Us Servlet")
public class StayInTouchServlet extends SlingAllMethodsServlet {

	/**
	 * Serial Version Id.
	 */
	private static final long serialVersionUID = -8995530536351764234L;

	/**
	 * The Constant log.
	 */
	private static final Logger log = LoggerFactory.getLogger(StayInTouchServlet.class);

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
	 * The Constant PINCODE.
	 */
	public static final String PINCODE = "dmpl__PostalCode__c";

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
		JsonObject jsonobject = element.getAsJsonObject();

		JsonObject json = new JsonObject();
		ResourceResolver resolver = CommonUtils.getResourceResolver(resolverFactory, Constants.READERSERVICEUSER);
		RSADecryptionUtil util = RSADecryptionUtil.encriptParams(resolver);
		
		try {
			json.addProperty(Constants.FIRSTNAME, jsonobject.get(Constants.FIRSTNAME).getAsString());
			json.addProperty(Constants.LASTNAME, jsonobject.get(Constants.LASTNAME).getAsString());
			json.addProperty(PINCODE, jsonobject.get(PINCODE).getAsString());
			json.addProperty(Constants.LEADSOURCE, Constants.WEBSITE);
			if(globalConfig.encryptionSupportRequired()){
                json.addProperty(Constants.EMAIL, util.decrypt(jsonobject.get(Constants.EMAIL).getAsString()));
                json.addProperty(Constants.MOBILEPHONE, util.decrypt(jsonobject.get(Constants.MOBILEPHONE).getAsString()));
            }else {
                json.addProperty(Constants.EMAIL,jsonobject.get(Constants.EMAIL).getAsString());
                json.addProperty(Constants.MOBILEPHONE,jsonobject.get(Constants.MOBILEPHONE).getAsString());
                
            } 
		} catch (IllegalBlockSizeException | BadPaddingException e1) {
			log.error("IllegalBlockSizeException or BadPaddingException  occured method: postData cause : %s", e1);
		}
		
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