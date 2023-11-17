/**
 * 
 */
package com.heromotocorp.vida.core.servlets;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Duration;

import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.heromotocorp.vida.core.config.ClientConfig;
import com.heromotocorp.vida.core.config.GlobalConfig;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import junitx.util.PrivateAccessor;

/**
 * JUnit test class for SystemListServlet
 */
@ExtendWith({ AemContextExtension.class, MockitoExtension.class })
class SystemListServletTest {

	private final AemContext context = new AemContext();

	private SystemListServlet systemListServlet = new SystemListServlet();

	private GlobalConfig globalConfig;

	private ClientConfig clientConfig;

	private SlingHttpServletRequest req;

	private SlingHttpServletResponse resp;

	private CloseableHttpClient httpClient;

	private HttpEntity entity;

	private HttpEntity entity1;

	private CloseableHttpClient httpClient1;

	private PrintWriter writer;

	private ResourceResolverFactory resolverFactory;

	private CloseableHttpResponse tweetsResponse;

	private CloseableHttpResponse tokenResponse;

	private StatusLine status;

	private BufferedReader reader;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		req = Mockito.mock(SlingHttpServletRequest.class);
		resp = Mockito.mock(SlingHttpServletResponse.class);
		httpClient = Mockito.mock(CloseableHttpClient.class);
		httpClient1 = Mockito.mock(CloseableHttpClient.class);
		tweetsResponse = Mockito.mock(CloseableHttpResponse.class);
		entity = Mockito.mock(HttpEntity.class);
		entity1 = Mockito.mock(HttpEntity.class);
		writer = Mockito.mock(PrintWriter.class);
		tokenResponse = Mockito.mock(CloseableHttpResponse.class);
		status = Mockito.mock(StatusLine.class);
		reader = Mockito.mock(BufferedReader.class);

		globalConfig = context.getService(GlobalConfig.class);
		globalConfig = mock(GlobalConfig.class);
		PrivateAccessor.setField(systemListServlet, "globalConfig", globalConfig);

		clientConfig = context.getService(ClientConfig.class);
		clientConfig = mock(ClientConfig.class);
		PrivateAccessor.setField(systemListServlet, "clientConfig", clientConfig);

		resolverFactory = context.getService(ResourceResolverFactory.class);
		resolverFactory = mock(ResourceResolverFactory.class);
		PrivateAccessor.setField(systemListServlet, "resolverFactory", resolverFactory);
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.servlets.SystemListServlet#getSystemlist(java.lang.String)}.
	 * 
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	@Test
	void testGetSystemlist() throws ClientProtocolException, IOException {
		Assertions.assertTimeoutPreemptively(Duration.ofSeconds(2), () -> {
			Mockito.mockStatic(EntityUtils.class);
			Mockito.mockStatic(HttpClients.class);
			Mockito.when(HttpClients.createDefault()).thenReturn(httpClient);
			Mockito.when(globalConfig.systemUrl()).thenReturn("systemUrl");
			Mockito.when(httpClient.execute(Mockito.any(HttpGet.class))).thenReturn(tweetsResponse);
			Mockito.when(tweetsResponse.getEntity()).thenReturn(entity);
			Mockito.when(EntityUtils.toString(entity)).thenReturn(
					"{\"access_token\":\"00D5h000004zDWU!AQEAQACaYxWAqNHE4L35go7ZSeD23TxdoE73_rLFrW8yAHaBdtvcUb7jB3312.ow2qPas6Ud9.4VwrpEz5Ybee1of8mQhqTv\",\"instance_url\":\"https://vidaworld.my.salesforce.com\",\"id\":\"https://login.salesforce.com/id/00D5h000004zDWUEA2/0055h000006CcsGAAS\",\"token_type\":\"Bearer\",\"issued_at\":\"1690363697815\",\"signature\":\"ufQFGoXyWmL2iwYS3OU9h0VmYR4vKsVD0UJ5P5LrdHo=\"}");
			systemListServlet.getSystemlist("token");
			assertNotNull(systemListServlet);
		});
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.servlets.SystemListServlet#getCategoryList(java.lang.String)}.
	 */
	@Test
	void testGetCategoryList() {
		Assertions.assertTimeoutPreemptively(Duration.ofSeconds(2), () -> {
			Mockito.mockStatic(EntityUtils.class);
			Mockito.mockStatic(HttpClients.class);
			Mockito.when(HttpClients.createDefault()).thenReturn(httpClient);
			Mockito.when(globalConfig.categoryUrl()).thenReturn("categoryUrl");
			Mockito.when(httpClient.execute(Mockito.any(HttpGet.class))).thenReturn(tweetsResponse);
			Mockito.when(tweetsResponse.getEntity()).thenReturn(entity);
			Mockito.when(EntityUtils.toString(entity)).thenReturn(
					"{\"access_token\":\"00D5h000004zDWU!AQEAQACaYxWAqNHE4L35go7ZSeD23TxdoE73_rLFrW8yAHaBdtvcUb7jB3312.ow2qPas6Ud9.4VwrpEz5Ybee1of8mQhqTv\",\"instance_url\":\"https://vidaworld.my.salesforce.com\",\"id\":\"https://login.salesforce.com/id/00D5h000004zDWUEA2/0055h000006CcsGAAS\",\"token_type\":\"Bearer\",\"issued_at\":\"1690363697815\",\"signature\":\"ufQFGoXyWmL2iwYS3OU9h0VmYR4vKsVD0UJ5P5LrdHo=\"}");
			systemListServlet.getCategoryList("token");
			assertNotNull(systemListServlet);
		});
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.servlets.SystemListServlet#getSubCategoryList(java.lang.String)}.
	 */
	@Test
	void testGetSubCategoryList() {
		Assertions.assertTimeoutPreemptively(Duration.ofSeconds(2), () -> {
			Mockito.mockStatic(EntityUtils.class);
			Mockito.mockStatic(HttpClients.class);
			Mockito.when(HttpClients.createDefault()).thenReturn(httpClient);
			Mockito.when(globalConfig.subcategoryUrl()).thenReturn("subcategoryUrl");
			Mockito.when(httpClient.execute(Mockito.any(HttpGet.class))).thenReturn(tweetsResponse);
			Mockito.when(tweetsResponse.getEntity()).thenReturn(entity);
			Mockito.when(EntityUtils.toString(entity)).thenReturn(
					"{\"access_token\":\"00D5h000004zDWU!AQEAQACaYxWAqNHE4L35go7ZSeD23TxdoE73_rLFrW8yAHaBdtvcUb7jB3312.ow2qPas6Ud9.4VwrpEz5Ybee1of8mQhqTv\",\"instance_url\":\"https://vidaworld.my.salesforce.com\",\"id\":\"https://login.salesforce.com/id/00D5h000004zDWUEA2/0055h000006CcsGAAS\",\"token_type\":\"Bearer\",\"issued_at\":\"1690363697815\",\"signature\":\"ufQFGoXyWmL2iwYS3OU9h0VmYR4vKsVD0UJ5P5LrdHo=\"}");
			systemListServlet.getSubCategoryList("token");
			assertNotNull(systemListServlet);
		});
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.servlets.SystemListServlet#doGet(org.apache.sling.api.SlingHttpServletRequest, org.apache.sling.api.SlingHttpServletResponse)}.
	 */
	@Test
	void testDoGetSlingHttpServletRequestSlingHttpServletResponse() {
		Assertions.assertTimeoutPreemptively(Duration.ofSeconds(2), () -> {
			Mockito.mockStatic(EntityUtils.class);
			Mockito.mockStatic(HttpClients.class);
			Mockito.when(globalConfig.systemUrl()).thenReturn("systemUrl");
			Mockito.when(globalConfig.categoryUrl()).thenReturn("categoryUrl");
			Mockito.when(globalConfig.subcategoryUrl()).thenReturn("subcategoryUrl");

			Mockito.when(HttpClients.createDefault()).thenReturn(httpClient1);
			Mockito.when(globalConfig.tokencreateUrl()).thenReturn("tokencreateUrl");
			Mockito.when(httpClient1.execute(Mockito.any(HttpGet.class))).thenReturn(tweetsResponse);
			Mockito.when(httpClient1.execute(Mockito.any(HttpPost.class))).thenReturn(tokenResponse);
			Mockito.when(tweetsResponse.getEntity()).thenReturn(entity);
			Mockito.when(EntityUtils.toString(entity)).thenReturn(
					"{\"access_token\":\"token\",\"instance_url\":\"https://vidaworld.my.salesforce.com\",\"id\":\"https://login.salesforce.com/id/00D5h000004zDWUEA2/0055h000006CcsGAAS\",\"token_type\":\"Bearer\",\"issued_at\":\"1690363697815\",\"signature\":\"ufQFGoXyWmL2iwYS3OU9h0VmYR4vKsVD0UJ5P5LrdHo=\"}");
			Mockito.when(tokenResponse.getEntity()).thenReturn(entity1);
			Mockito.when(EntityUtils.toString(entity1)).thenReturn(
					"{\"access_token\":\"token\",\"instance_url\":\"https://vidaworld.my.salesforce.com\",\"id\":\"https://login.salesforce.com/id/00D5h000004zDWUEA2/0055h000006CcsGAAS\",\"token_type\":\"Bearer\",\"issued_at\":\"1690363697815\",\"signature\":\"ufQFGoXyWmL2iwYS3OU9h0VmYR4vKsVD0UJ5P5LrdHo=\"}");
			Mockito.when(tokenResponse.getStatusLine()).thenReturn(status);
			Mockito.when(status.getStatusCode()).thenReturn(400);
			Mockito.when(resp.getWriter()).thenReturn(writer);
			systemListServlet.doGet(req, resp);

			Mockito.when(status.getStatusCode()).thenReturn(200);
			systemListServlet.doGet(req, resp);
			assertNotNull(systemListServlet);
		});
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.servlets.SystemListServlet#doPost(org.apache.sling.api.SlingHttpServletRequest, org.apache.sling.api.SlingHttpServletResponse)}.
	 */
	@Test
	void testDoPostSlingHttpServletRequestSlingHttpServletResponse() {
		Assertions.assertTimeoutPreemptively(Duration.ofSeconds(2), () -> {
			Mockito.mockStatic(EntityUtils.class);
			Mockito.mockStatic(HttpClients.class);
			Mockito.when(req.getReader()).thenReturn(reader);
			Mockito.when(reader.readLine()).thenReturn(
					"{\"token\": \"token\",\"Types\": \"Types\",\"recordType\": \"recordType\",\"Subject\": \"Subject\",\"Priority\": \"Priority\",\"Status\": \"Status\",\"Description\": \"Description\",\"Complaint_Category\": \"Complaint_Category\",\"Aggregates\": \"Aggregates\",\"Phone\": \"Phone\",\"Contact_Email_Address\": \"Contact_Email_Address\",\"fileName\": \"fileName\",\"fileExtension\": \"fileExtension\",\"base64\": \"base64\"}",
					null);
			Mockito.when(globalConfig.internalComplainUrl()).thenReturn("internalComplainUrl");
			Mockito.when(clientConfig.client()).thenReturn(httpClient);
			Mockito.when(httpClient.execute(Mockito.any(HttpPost.class))).thenReturn(tokenResponse);
			Mockito.when(tokenResponse.getEntity()).thenReturn(entity);
			Mockito.when(EntityUtils.toString(entity)).thenReturn(
					"{\"access_token\":\"token\",\"instance_url\":\"https://vidaworld.my.salesforce.com\",\"id\":\"https://login.salesforce.com/id/00D5h000004zDWUEA2/0055h000006CcsGAAS\",\"token_type\":\"Bearer\",\"issued_at\":\"1690363697815\",\"signature\":\"ufQFGoXyWmL2iwYS3OU9h0VmYR4vKsVD0UJ5P5LrdHo=\"}");
			Mockito.when(resp.getWriter()).thenReturn(writer);
			systemListServlet.doPost(req, resp);
			assertNotNull(systemListServlet);
		});
	}

}
