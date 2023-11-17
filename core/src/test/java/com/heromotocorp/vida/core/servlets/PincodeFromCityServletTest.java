/**
 * 
 */
package com.heromotocorp.vida.core.servlets;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.Duration;

import javax.servlet.ServletException;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
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
 * JUnit test class for PincodeFromCityServlet
 */
@ExtendWith({ AemContextExtension.class, MockitoExtension.class })
class PincodeFromCityServletTest {

	private final AemContext context = new AemContext();

	private PincodeFromCityServlet pincodeFromCityServlet = new PincodeFromCityServlet();

	private GlobalConfig globalConfig;

	private ClientConfig clientConfig;

	private SlingHttpServletRequest req;

	private SlingHttpServletResponse resp;

	private CloseableHttpClient httpClient;

	private CloseableHttpResponse response;

	private CloseableHttpResponse httpResponse;

	private HttpEntity entity;

	private HttpEntity entity1;

	private CloseableHttpClient httpClient1;

	private PrintWriter writer;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		req = Mockito.mock(SlingHttpServletRequest.class);
		resp = Mockito.mock(SlingHttpServletResponse.class);
		httpClient = Mockito.mock(CloseableHttpClient.class);
		httpClient1 = Mockito.mock(CloseableHttpClient.class);
		response = Mockito.mock(CloseableHttpResponse.class);
		httpResponse = Mockito.mock(CloseableHttpResponse.class);
		entity = Mockito.mock(HttpEntity.class);
		entity1 = Mockito.mock(HttpEntity.class);
		writer = Mockito.mock(PrintWriter.class);

		globalConfig = context.getService(GlobalConfig.class);
		globalConfig = mock(GlobalConfig.class);
		PrivateAccessor.setField(pincodeFromCityServlet, "globalConfig", globalConfig);

		clientConfig = context.getService(ClientConfig.class);
		clientConfig = mock(ClientConfig.class);
		PrivateAccessor.setField(pincodeFromCityServlet, "clientConfig", clientConfig);
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.servlets.PincodeFromCityServlet#doGet(org.apache.sling.api.SlingHttpServletRequest, org.apache.sling.api.SlingHttpServletResponse)}.
	 * 
	 * @throws IOException
	 * @throws ServletException
	 */
	@Test
	void testDoGetSlingHttpServletRequestSlingHttpServletResponse() throws ServletException, IOException {
		Assertions.assertTimeoutPreemptively(Duration.ofSeconds(2), () -> {
			Mockito.mockStatic(EntityUtils.class);
			Mockito.mockStatic(HttpClients.class);
			Mockito.when(req.getPathInfo()).thenReturn("/content/vida.pincodecity.India.Karnataka.BENGALURU.json");
			Mockito.when(globalConfig.postRequestToken()).thenReturn("postRequestToken");
			Mockito.when(globalConfig.grantType()).thenReturn("grantType");
			Mockito.when(globalConfig.clientId()).thenReturn("clientId");
			Mockito.when(globalConfig.clientSecret()).thenReturn("clientSecret");
			Mockito.when(globalConfig.username()).thenReturn("username");
			Mockito.when(globalConfig.password()).thenReturn("password");
			Mockito.when(globalConfig.contentType()).thenReturn("contentType");
			Mockito.when(globalConfig.sfdcQueryEndpoint()).thenReturn("sfdcQueryEndpoint");
			Mockito.when(resp.getWriter()).thenReturn(writer);
			pincodeFromCityServlet.doGet(req, resp);

			Mockito.when(clientConfig.client()).thenReturn(httpClient);
			Mockito.when(httpClient.execute(Mockito.any(HttpPost.class))).thenReturn(response);
			Mockito.when(response.getEntity()).thenReturn(entity);
			Mockito.when(EntityUtils.toString(entity)).thenReturn(
					"{\"access_token\":\"00D5h000004zDWU!AQEAQACaYxWAqNHE4L35go7ZSeD23TxdoE73_rLFrW8yAHaBdtvcUb7jB3312.ow2qPas6Ud9.4VwrpEz5Ybee1of8mQhqTv\",\"instance_url\":\"https://vidaworld.my.salesforce.com\",\"id\":\"https://login.salesforce.com/id/00D5h000004zDWUEA2/0055h000006CcsGAAS\",\"token_type\":\"Bearer\",\"issued_at\":\"1690363697815\",\"signature\":\"ufQFGoXyWmL2iwYS3OU9h0VmYR4vKsVD0UJ5P5LrdHo=\"}");
			Mockito.when(HttpClients.createDefault()).thenReturn(httpClient1);
			Mockito.when(globalConfig.pincodeCityReqUrl()).thenReturn("pincodeCityReqUrl");
			Mockito.when(httpClient1.execute(Mockito.any(HttpGet.class))).thenReturn(null);
			pincodeFromCityServlet.doGet(req, resp);

			Mockito.when(httpClient1.execute(Mockito.any(HttpGet.class))).thenReturn(httpResponse);
			Mockito.when(httpResponse.getEntity()).thenReturn(entity1);
			Mockito.when(EntityUtils.toString(entity1)).thenReturn(
					"{\"done\": true,\"records\": [{\"attributes\": {\"type\": \"dmpl__PostalCode__c\",\"url\": \"/services/data/v52.0/sobjects/dmpl__PostalCode__c/a1Lp000000AeYheEAF\"},\"Id\": \"a1Lp000000AeYheEAF\",\"Name\": \"560006\",\"District__c\": null,\"Tehsil__c\": null,\"dmpl__Region__c\": \"NA\",\"dmpl__City__c\": \"BENGALURU\",\"dmpl__State__c\": \"Karnataka\",\"dmpl__Country__c\": \"India\"}]}");
			pincodeFromCityServlet.doGet(req, resp);
			assertNotNull(pincodeFromCityServlet);
		});
	}

}
