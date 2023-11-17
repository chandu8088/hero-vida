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
import java.util.HashMap;
import java.util.Map;

import javax.jcr.RepositoryException;
import javax.servlet.ServletException;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.heromotocorp.vida.core.config.ClientConfig;
import com.heromotocorp.vida.core.config.GlobalConfig;
import com.heromotocorp.vida.core.utils.Constants;
import com.heromotocorp.vida.core.utils.RSADecryptionUtil;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import junitx.util.PrivateAccessor;

/**
 * JUnit test class for NewLeadCreationServlet
 */
@ExtendWith({ AemContextExtension.class, MockitoExtension.class })
class NewLeadCreationServletTest {

	private final AemContext context = new AemContext();

	private NewLeadCreationServlet newLeadCreationServlet = new NewLeadCreationServlet();

	private GlobalConfig globalConfig;

	private ClientConfig clientConfig;

	private SlingHttpServletRequest req;

	private SlingHttpServletResponse resp;

	private CloseableHttpClient httpClient;

	private HttpEntity entity;

	private PrintWriter writer;

	private CloseableHttpResponse response;

	private ResourceResolverFactory resolverFactory;

	private BufferedReader reader;

	private RSADecryptionUtil util;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		req = Mockito.mock(SlingHttpServletRequest.class);
		resp = Mockito.mock(SlingHttpServletResponse.class);
		httpClient = Mockito.mock(CloseableHttpClient.class);
		entity = Mockito.mock(HttpEntity.class);
		writer = Mockito.mock(PrintWriter.class);
		response = Mockito.mock(CloseableHttpResponse.class);
		reader = Mockito.mock(BufferedReader.class);
		util = Mockito.mock(RSADecryptionUtil.class);

		globalConfig = context.getService(GlobalConfig.class);
		globalConfig = mock(GlobalConfig.class);
		PrivateAccessor.setField(newLeadCreationServlet, "globalConfig", globalConfig);

		clientConfig = context.getService(ClientConfig.class);
		clientConfig = mock(ClientConfig.class);
		PrivateAccessor.setField(newLeadCreationServlet, "clientConfig", clientConfig);

		resolverFactory = context.getService(ResourceResolverFactory.class);
		resolverFactory = mock(ResourceResolverFactory.class);
		PrivateAccessor.setField(newLeadCreationServlet, "resolverFactory", resolverFactory);

	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.servlets.StayInTouchServlet#doPost(org.apache.sling.api.SlingHttpServletRequest, org.apache.sling.api.SlingHttpServletResponse)}.
	 * 
	 * @throws IOException
	 * @throws ServletException
	 * @throws LoginException
	 * @throws RepositoryException
	 */
	@Test
	void testDoPostSlingHttpServletRequestSlingHttpServletResponse()
			throws ServletException, IOException, LoginException, RepositoryException {
		Assertions.assertTimeoutPreemptively(Duration.ofSeconds(2), () -> {
			Mockito.mockStatic(EntityUtils.class);
			Mockito.mockStatic(HttpClients.class);

			Map<String, Object> param = new HashMap<>();
			param.put(ResourceResolverFactory.SUBSERVICE, Constants.READERSERVICEUSER);
			Mockito.when(globalConfig.postRequestToken()).thenReturn("postRequestToken");
			Mockito.when(globalConfig.grantType()).thenReturn("grantType");
			Mockito.when(globalConfig.clientId()).thenReturn("clientId");
			Mockito.when(globalConfig.clientSecret()).thenReturn("clientSecret");
			Mockito.when(globalConfig.username()).thenReturn("username");
			Mockito.when(globalConfig.password()).thenReturn("password");
			Mockito.when(globalConfig.contentType()).thenReturn("contentType");
			Mockito.when(clientConfig.client()).thenReturn(httpClient);
			Mockito.when(httpClient.execute(Mockito.any(HttpPost.class))).thenReturn(response);
			Mockito.when(response.getEntity()).thenReturn(entity);
			Mockito.when(EntityUtils.toString(entity)).thenReturn(
					"{\"access_token\":\"00D5h000004zDWU!AQEAQACaYxWAqNHE4L35go7ZSeD23TxdoE73_rLFrW8yAHaBdtvcUb7jB3312.ow2qPas6Ud9.4VwrpEz5Ybee1of8mQhqTv\",\"instance_url\":\"https://vidaworld.my.salesforce.com\",\"id\":\"https://login.salesforce.com/id/00D5h000004zDWUEA2/0055h000006CcsGAAS\",\"token_type\":\"Bearer\",\"issued_at\":\"1690363697815\",\"signature\":\"ufQFGoXyWmL2iwYS3OU9h0VmYR4vKsVD0UJ5P5LrdHo=\"}");
			Mockito.when(req.getReader()).thenReturn(reader);
			Mockito.when(reader.readLine()).thenReturn(
					"{\"FirstName\": \"FIRSTNAME\",\"LastName\": \"LASTNAME\",\"dmpl__PostalCode__c\": \"PINCODE\",\"Email\": \"Email\",\"MobilePhone\": \"9123456789\"}",
					null);
			Mockito.when(globalConfig.leadsUrl()).thenReturn("leadsUrl");
			Mockito.when(resp.getWriter()).thenReturn(writer);
			newLeadCreationServlet.doPost(req, resp);

			Mockito.when(reader.readLine()).thenReturn(
					"{\"FirstName\": \"FIRSTNAME\",\"LastName\": \"LASTNAME\",\"dmpl__PostalCode__c\": \"PINCODE\",\"Email\": \"Email\",\"MobilePhone\": \"9123456789\"}",
					null);
			newLeadCreationServlet.doPost(req, resp);

			assertNotNull(newLeadCreationServlet);
		});
	}

}
