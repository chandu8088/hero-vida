package com.heromotocorp.vida.core.servlets;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
//import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.nio.charset.StandardCharsets;
import org.apache.sling.api.resource.ResourceResolverFactory;
import com.heromotocorp.vida.core.config.ClientConfig;
import com.heromotocorp.vida.core.config.GlobalConfig;
import com.heromotocorp.vida.core.utils.CommonUtils;
import com.heromotocorp.vida.core.utils.Constants;
import org.osgi.service.component.annotations.Reference;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.http.HttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.ParseException;
import java.io.IOException;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.propertytypes.ServiceDescription;
import org.apache.sling.servlets.post.JSONResponse;
import org.apache.sling.api.request.RequestParameter;
import java.io.BufferedReader;
import com.google.gson.JsonObject;
import org.apache.http.message.BasicHeader;
import org.apache.http.entity.StringEntity;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.BadPaddingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.client.entity.UrlEncodedFormEntity;
/**
 * @author Tanuja dave
 * 
 * This servlet uses the HTTP GET method to read a data from the RESTful webservice
 */
// @Component(service = Servlet.class, property = {
// 		"sling.servlet.methods= get", "sling.servlet.paths=" + "/bin/readjson" })
@Component(service = { Servlet.class })
@SlingServletResourceTypes(resourceTypes = Constants.PAGERESOURCETYPE, methods = {HttpConstants.METHOD_POST,HttpConstants.METHOD_GET}, selectors = "systemlist", extensions = "json")
@ServiceDescription("Create System Servlet")
public class SystemListServlet extends SlingAllMethodsServlet {

	/**
	 * Generated serialVersionUID
	 */
	private static final long serialVersionUID = 4438376868274173005L;
	
	/**
	 * Logger
	 */
	private static final Logger log = LoggerFactory.getLogger(SystemListServlet.class);

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

	private String getToken()
	{
		String url = globalConfig.tokencreateUrl()+"?grant_type="+globalConfig.grantType()+"&client_id="+globalConfig.clientId()+"&client_secret="+globalConfig.clientSecret()+"&username="+globalConfig.username()+"&password="+globalConfig.password();
		HttpPost getCall = new HttpPost(url);
		try (CloseableHttpClient httpClient = HttpClients.createDefault();
				CloseableHttpResponse tokenResponse = httpClient.execute(getCall)) {
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


	@java.lang.SuppressWarnings("squid:S2095")
	public String getSystemlist(String token) {
		String jsonString =null;
		try{
		HttpGet getCall = new HttpGet(globalConfig.systemUrl());
		getCall.setHeader(Constants.AUTHORIZATION, new StringBuilder(Constants.BEARER).append(" ").append(token).toString());
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse tweetsResponse = httpClient.execute(getCall);
		jsonString= EntityUtils.toString(tweetsResponse.getEntity());
		} catch (ParseException | IOException e) {
			log.error("Exception occured method: getTweets cause : %s", e);
		}
		 return jsonString;
	}

	@java.lang.SuppressWarnings("squid:S2095")
	public String getCategoryList(String token) {
		String jsonString =null;
		try{
		HttpGet getCall = new HttpGet(globalConfig.categoryUrl());
		getCall.setHeader(Constants.AUTHORIZATION, new StringBuilder(Constants.BEARER).append(" ").append(token).toString());
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse tweetsResponse = httpClient.execute(getCall);
		jsonString= EntityUtils.toString(tweetsResponse.getEntity());
		} catch (ParseException | IOException e) {
			log.error("Exception occured method: getTweets cause : %s", e);
		}
		 return jsonString;
	}

	@java.lang.SuppressWarnings("squid:S2095")
	public String getSubCategoryList(String token) {
		String jsonString =null;
		try{
		HttpGet getCall = new HttpGet(globalConfig.subcategoryUrl());
		getCall.setHeader(Constants.AUTHORIZATION, new StringBuilder(Constants.BEARER).append(" ").append(token).toString());
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse tweetsResponse = httpClient.execute(getCall);
		jsonString= EntityUtils.toString(tweetsResponse.getEntity());
		} catch (ParseException | IOException e) {
			log.error("Exception occured method: getTweets cause : %s", e);
		}
		 return jsonString;
	}

		
	
	
	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) {

		try 
		{

		 String token = getToken();
		 String systemdata  = getSystemlist(token);
		 String categorydata = getCategoryList(token);
		 String subCategoryData =getSubCategoryList(token);
		 String responseString = "{\"token\":\""+token+"\",\"result\":"+systemdata+",\"category\":"+categorydata+",\"subcategory\":"+subCategoryData+"}";
		 response.setContentType(JSONResponse.RESPONSE_CONTENT_TYPE);
		 response.getWriter().write(responseString);
		} catch (ParseException | IOException e) {
			log.error("IOException  occured method: doGet cause : %s", e);
		}
	}

	@Override
	protected void doPost(final SlingHttpServletRequest req, final SlingHttpServletResponse resp)
		throws ServletException, IOException {
		StringBuilder sb = new StringBuilder();
		String line = null;
		try {
			BufferedReader reader = req.getReader();
			while ((line = reader.readLine()) != null)
				sb.append(line);
		} catch (IOException e) {
			log.error("IOException  occured method: postData cause : %s", e);
		}

		try
		
		{
		Gson gson = new Gson();
		JsonElement element = gson.fromJson(sb.toString(), JsonElement.class);
		JsonObject jsonobject = element.getAsJsonObject();
		JsonObject json = new JsonObject();
		String token  = new StringBuilder(jsonobject.get("token").getAsString()).toString();
		String types  = new StringBuilder(jsonobject.get("Types").getAsString()).toString();
		String recordType  = new StringBuilder(jsonobject.get("recordType").getAsString()).toString();
		String subject  = new StringBuilder(jsonobject.get("Subject").getAsString()).toString();
		String priority  = new StringBuilder(jsonobject.get("Priority").getAsString()).toString();
		String status  = new StringBuilder(jsonobject.get("Status").getAsString()).toString();
		String description = new StringBuilder(jsonobject.get("Description").getAsString()).toString();
		String complaintCategory  = new StringBuilder(jsonobject.get("Complaint_Category").getAsString()).toString();
		String aggregates  = new StringBuilder(jsonobject.get("Aggregates").getAsString()).toString();
		String phone  = new StringBuilder(jsonobject.get("Phone").getAsString()).toString();
		String contactEmailAddress  = new StringBuilder(jsonobject.get("Contact_Email_Address").getAsString()).toString();
		String fileName  = new StringBuilder(jsonobject.get("fileName").getAsString()).toString();
		String fileExtension  = new StringBuilder(jsonobject.get("fileExtension").getAsString()).toString();
		String base64  = new StringBuilder(jsonobject.get("base64").getAsString()).toString();
        json.addProperty("Types",types );
		json.addProperty("recordType",recordType);
		json.addProperty("Subject", subject);
		json.addProperty("Priority",priority);
		json.addProperty("Status", status);
		json.addProperty("recordType",recordType);
		json.addProperty("Description", description);
		json.addProperty("Complaint_Category",complaintCategory);
		json.addProperty("Aggregates", aggregates);
		json.addProperty("Phone",phone);
		json.addProperty("Contact_Email_Address",contactEmailAddress);
		json.addProperty("fileName",fileName);
		json.addProperty("fileExtension", fileExtension);
		json.addProperty("base64",base64);
		log.info("Post request to Create Case");
		HttpPost postRequst = new HttpPost(globalConfig.internalComplainUrl());
		postRequst.setHeader(Constants.AUTHORIZATION, new StringBuilder(Constants.BEARER).append(" ").append(token).toString());
		postRequst.setHeader("Content-type", "text/plain");
		postRequst.setHeader(new BasicHeader(Constants.CONTENTTYPE, JSONResponse.RESPONSE_CONTENT_TYPE));
		postRequst.setEntity(new StringEntity(json.toString(), StandardCharsets.UTF_8));
		CloseableHttpResponse response = clientConfig.client().execute(postRequst);
		String jsonString= EntityUtils.toString(response.getEntity());
		resp.getWriter().write(jsonString);
			} catch (ParseException | IOException e) {
				log.error("Exception  occured method: postData cause : %s", e);
				resp.getWriter().write(e.toString());
			}
	}
}