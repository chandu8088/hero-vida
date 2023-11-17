/**
 * 
 */
package com.heromotocorp.vida.core.servlets;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.Duration;
import java.util.Collections;

import javax.servlet.ServletException;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.testing.mock.osgi.MockOsgi;
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
 * JUnit test class for LTTRVehicleAvailabilityServlet
 */
@ExtendWith({ AemContextExtension.class, MockitoExtension.class })
class LTTRVehicleAvailabilityServletTest {

	private final AemContext context = new AemContext();

	private LTTRVehicleAvailabilityServlet lttrVehicleAvailabilityServlet = new LTTRVehicleAvailabilityServlet();

	private ClientConfig clientConfig;

	private GlobalConfig globalConfig;

	private SlingHttpServletRequest req;

	private SlingHttpServletResponse resp;

	private CloseableHttpClient httpClient;

	private CloseableHttpResponse httpResponse;

	private HttpEntity entity;

	private PrintWriter writer;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		req = Mockito.mock(SlingHttpServletRequest.class);
		resp = Mockito.mock(SlingHttpServletResponse.class);
		httpClient = Mockito.mock(CloseableHttpClient.class);
		httpResponse = Mockito.mock(CloseableHttpResponse.class);
		entity = Mockito.mock(HttpEntity.class);
		writer = Mockito.mock(PrintWriter.class);

		globalConfig = context.getService(GlobalConfig.class);
		globalConfig = Mockito.mock(GlobalConfig.class);
		PrivateAccessor.setField(lttrVehicleAvailabilityServlet, "globalConfig", globalConfig);

		clientConfig = context.getService(ClientConfig.class);
		clientConfig = mock(ClientConfig.class);
		PrivateAccessor.setField(lttrVehicleAvailabilityServlet, "clientConfig", clientConfig);
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.servlets.LTTRVehicleAvailabilityServlet#doGet(org.apache.sling.api.SlingHttpServletRequest, org.apache.sling.api.SlingHttpServletResponse)}.
	 * 
	 * @throws IOException
	 * @throws ServletException
	 */
	@Test
	void testDoGetSlingHttpServletRequestSlingHttpServletResponse() throws ServletException, IOException {

		Assertions.assertTimeoutPreemptively(Duration.ofSeconds(2), () -> {
			Mockito.mockStatic(EntityUtils.class);
			Mockito.when(EntityUtils.toString(entity)).thenReturn(
					"{\"IsSuccess\":true,\"Result\":[{\"ModelId\":35,\"ModelName\":\"V1 Plus\",\"ImageName\":null,\"CityId\":\"4\",\"SkuId\":\"a1Gp0000000FaIvEAK\"},{\"ModelId\":6,\"ModelName\":\"Glamour\",\"ImageName\":\"Glamour B L.png\",\"CityId\":\"4\",\"SkuId\":\"a1Gp0000000FaIvFREEDO\"}]}");

			Mockito.when(req.getPathInfo()).thenReturn("/content/vida.CITY.4.json");
			Mockito.when(clientConfig.client()).thenReturn(httpClient);
			Mockito.when(globalConfig.lttrApiKey()).thenReturn("lttrAPIKey");
			Mockito.when(globalConfig.lttrBaseEndPoint()).thenReturn("lttrBaseEndPoint");
			Mockito.when(globalConfig.lttrBaseEndPoint()).thenReturn("lttrBaseEndPoint");
			Mockito.when(httpClient.execute(Mockito.any())).thenReturn(httpResponse);
			Mockito.when(httpResponse.getEntity()).thenReturn(entity);

			Mockito.when(resp.getWriter()).thenReturn(writer);
			lttrVehicleAvailabilityServlet.doGet(req, resp);

			Mockito.when(EntityUtils.toString(entity)).thenReturn("{\"IsSuccess\":false}");
			Mockito.when(resp.getWriter()).thenReturn(writer);
			lttrVehicleAvailabilityServlet.doGet(req, resp);

			Mockito.when(httpClient.execute(Mockito.any())).thenReturn(null);
			lttrVehicleAvailabilityServlet.doGet(req, resp);

			Mockito.when(req.getPathInfo()).thenReturn("/content/vida");
			lttrVehicleAvailabilityServlet.doGet(req, resp);

			assertNotNull(lttrVehicleAvailabilityServlet);

			MockOsgi.setConfigForPid(context.bundleContext(), "org.apache.http.util.EntityUtils",
					Collections.singletonMap("enabled", false));
		});
	}

}
