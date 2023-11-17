/**
 * 
 */
package com.heromotocorp.vida.core.servlets;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.testing.mock.osgi.MockOsgi;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.heromotocorp.vida.core.config.ClientConfig;
import com.heromotocorp.vida.core.config.GlobalConfig;

import com.heromotocorp.vida.core.utils.CommonUtils;
import com.heromotocorp.vida.core.utils.Constants;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import junitx.util.PrivateAccessor;

/**
 * JUnit test class for CreateCaseServlet
 */
@ExtendWith(MockitoExtension.class)
@ExtendWith(AemContextExtension.class)
class CreateCaseServletTest {

	private final AemContext context = new AemContext();

	private CreateCaseServlet createCaseServlet = new CreateCaseServlet();

	private GlobalConfig globalConfig;

	private ClientConfig clientConfig;

	private SlingHttpServletRequest req;

	private SlingHttpServletResponse resp;

	private ResourceResolverFactory resolverFactory;

	private ResourceResolver resolver;

	private BufferedReader reader;

	Map<String, Object> param = new HashMap<>();

	private String jsonString = "{\"FirstName\": \"value1\", \"LastName\": \"value2\", \"Message\": \"value3\", \"Email\": \"abc@gmail.com\", \"MobilePhone\": \"9123456789\"}";

	private CloseableHttpClient client;

	private CloseableHttpResponse response;

	private HttpEntity entity;

	private PrintWriter writer;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		reader = Mockito.mock(BufferedReader.class);
		req = Mockito.mock(SlingHttpServletRequest.class);
		resp = Mockito.mock(SlingHttpServletResponse.class);
		resolver = Mockito.mock(ResourceResolver.class);
		client = Mockito.mock(CloseableHttpClient.class);
		response = Mockito.mock(CloseableHttpResponse.class);
		entity = Mockito.mock(HttpEntity.class);
		writer = Mockito.mock(PrintWriter.class);

		globalConfig = context.getService(GlobalConfig.class);
		globalConfig = Mockito.mock(GlobalConfig.class);
		PrivateAccessor.setField(createCaseServlet, "globalConfig", globalConfig);

		clientConfig = context.getService(ClientConfig.class);
		clientConfig = mock(ClientConfig.class);
		PrivateAccessor.setField(createCaseServlet, "clientConfig", clientConfig);

		resolverFactory = context.getService(ResourceResolverFactory.class);
		resolverFactory = mock(ResourceResolverFactory.class);
		PrivateAccessor.setField(createCaseServlet, "resolverFactory", resolverFactory);

		param.put(ResourceResolverFactory.SUBSERVICE, Constants.READERSERVICEUSER);
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.servlets.CreateCaseServlet#doPost(org.apache.sling.api.SlingHttpServletRequest, org.apache.sling.api.SlingHttpServletResponse)}.
	 * 
	 * @throws IOException
	 * @throws ServletException
	 * @throws LoginException
	 */
	@Test
	void testDoPostSlingHttpServletRequestSlingHttpServletResponse()
			throws ServletException, IOException, LoginException {

		Mockito.mockStatic(EntityUtils.class);

		Mockito.when(globalConfig.postRequestToken()).thenReturn("token");
		Mockito.when(globalConfig.grantType()).thenReturn("grantType");
		Mockito.when(globalConfig.clientId()).thenReturn("clientId");
		Mockito.when(globalConfig.clientSecret()).thenReturn("clientSecret");
		Mockito.when(globalConfig.username()).thenReturn("username");
		Mockito.when(globalConfig.password()).thenReturn("password");
		Mockito.when(globalConfig.contentType()).thenReturn("contentType");
		lenient().when(globalConfig.cityMasterCSVLocationPath()).thenReturn("CityPath");
		Mockito.when(req.getReader()).thenReturn(reader);
		Mockito.when(reader.readLine()).thenReturn(jsonString, null);
		Mockito.when(resolverFactory.getServiceResourceResolver(param)).thenReturn(resolver);
		Mockito.when(CommonUtils.getResourceResolver(resolverFactory, Constants.READERSERVICEUSER))
				.thenReturn(resolver);
		Mockito.when(globalConfig.caseUrl()).thenReturn("/content/vida/test");
		createCaseServlet.doPost(req, resp);

		Mockito.when(globalConfig.postRequestToken()).thenReturn("token");
		Mockito.when(globalConfig.grantType()).thenReturn("grantType");
		Mockito.when(globalConfig.clientId()).thenReturn("clientId");
		Mockito.when(globalConfig.clientSecret()).thenReturn("clientSecret");
		Mockito.when(globalConfig.username()).thenReturn("username");
		Mockito.when(globalConfig.password()).thenReturn("password");
		Mockito.when(globalConfig.contentType()).thenReturn("contentType");
		Mockito.when(clientConfig.client()).thenReturn(client);
		lenient().when(client.execute(Mockito.any())).thenReturn(response);
		Mockito.when(response.getEntity()).thenReturn(entity);
		lenient().when(entity.toString()).thenReturn("{\"access_token\": \"value1\"}");
		Mockito.when(EntityUtils.toString(entity)).thenReturn("{\"access_token\": \"value1\"}");
		Mockito.when(req.getReader()).thenReturn(reader);
		Mockito.when(reader.readLine()).thenReturn(jsonString, null);
		Mockito.when(resolverFactory.getServiceResourceResolver(param)).thenReturn(resolver);
		Mockito.when(CommonUtils.getResourceResolver(resolverFactory, Constants.READERSERVICEUSER))
				.thenReturn(resolver);
		Mockito.when(globalConfig.caseUrl()).thenReturn("/content/vida/test");
		Mockito.when(resp.getWriter()).thenReturn(writer);
		createCaseServlet.doPost(req, resp);
		assertNotNull(createCaseServlet);

		MockOsgi.setConfigForPid(context.bundleContext(), "org.apache.http.util.EntityUtils",
	            Collections.singletonMap("enabled", false));
	}

}
