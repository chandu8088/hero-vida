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
import org.apache.http.impl.client.CloseableHttpClient;
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
 * JUnit test class for LTTRPickupLocationServlet
 */
@ExtendWith({ AemContextExtension.class, MockitoExtension.class })
class LTTRPickupLocationServletTest {

	private final AemContext context = new AemContext();

	private LTTRPickupLocationServlet lttrPickupLocationServlet = new LTTRPickupLocationServlet();

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
		PrivateAccessor.setField(lttrPickupLocationServlet, "globalConfig", globalConfig);

		clientConfig = context.getService(ClientConfig.class);
		clientConfig = mock(ClientConfig.class);
		PrivateAccessor.setField(lttrPickupLocationServlet, "clientConfig", clientConfig);
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.servlets.LTTRPickupLocationServlet#doGet(org.apache.sling.api.SlingHttpServletRequest, org.apache.sling.api.SlingHttpServletResponse)}.
	 * 
	 * @throws IOException
	 * @throws ServletException
	 */
	@Test
	void testDoGetSlingHttpServletRequestSlingHttpServletResponse() throws ServletException, IOException {
		Assertions.assertTimeoutPreemptively(Duration.ofSeconds(2), () -> {
			Mockito.mockStatic(EntityUtils.class);
			Mockito.when(EntityUtils.toString(entity)).thenReturn(
					"{\"IsSuccess\":true,\"Result\":{\"id\":4,\"cityName\":\"Noida\",\"stateName\":\"Uttar Pradesh\",\"countryName\":\"India\",\"pickUpLocationData\":[{\"id\":1043,\"location_new_name\":\"\",\"location_name\":\"Noida\",\"Address1\":\"H 206A Sector 63 Noida\",\"Address2\":\"Gautam Buddha Nagar\",\"CityName\":\"Noida\",\"StateName\":\"Uttar Pradesh\",\"AddressZip\":201301,\"Latitude\":28.625078,\"Longitude\":77.37772,\"ContactNumber\":\"+91 9311340946\",\"ContactName\":\"FREEDO\"},{\"id\":1044,\"location_new_name\":\"\",\"location_name\":\"Metro Hospital Sector 11\",\"Address1\":\"L94 near Punjab National Bank Sector 11\",\"Address2\":\"Noida Uttar Pradesh\",\"CityName\":\"Noida\",\"StateName\":\"Uttar Pradesh\",\"AddressZip\":201301,\"Latitude\":28.597353,\"Longitude\":77.33378,\"ContactNumber\":\"+91 9311340946\",\"ContactName\":\"FREEDO\"},{\"id\":1049,\"location_new_name\":\"\",\"location_name\":\"abc\",\"Address1\":\"ad1\",\"Address2\":\"ad2\",\"CityName\":\"Noida\",\"StateName\":\"Uttar Pradesh\",\"AddressZip\":232321,\"Latitude\":65,\"Longitude\":69,\"ContactNumber\":\"+91 9311340946\",\"ContactName\":\"FREEDO\"},{\"id\":1055,\"location_new_name\":\"\",\"location_name\":\"Sector 19\",\"Address1\":\"C 561\",\"Address2\":\"Sector 19\",\"CityName\":\"Noida\",\"StateName\":\"Uttar Pradesh\",\"AddressZip\":201301,\"Latitude\":28.578468,\"Longitude\":77.320175,\"ContactNumber\":\"+91 9311340946\",\"ContactName\":\"FREEDO\"},{\"id\":1058,\"location_new_name\":\"\",\"location_name\":\"Noida Sec 62\",\"Address1\":\"J9H75WX Block A\",\"Address2\":\"Industrial Area Sector 62 Noida Uttar Pradesh\",\"CityName\":\"Noida\",\"StateName\":\"Uttar Pradesh\",\"AddressZip\":201309,\"Latitude\":28.627985,\"Longitude\":77.36267,\"ContactNumber\":\"+91 9311340946\",\"ContactName\":\"FREEDO\"}]}}");
			Mockito.when(req.getPathInfo()).thenReturn("/content/vida.CITY.BENGALURU.KARNATAKA.json");
			Mockito.when(clientConfig.client()).thenReturn(httpClient);
			Mockito.when(globalConfig.lttrApiKey()).thenReturn("lttrAPIKey");
			Mockito.when(globalConfig.lttrBaseEndPoint()).thenReturn("lttrBaseEndPoint");
			Mockito.when(globalConfig.lttrBaseEndPoint()).thenReturn("lttrBaseEndPoint");
			Mockito.when(httpClient.execute(Mockito.any())).thenReturn(httpResponse);
			Mockito.when(httpResponse.getEntity()).thenReturn(entity);
			Mockito.when(resp.getWriter()).thenReturn(writer);
			lttrPickupLocationServlet.doGet(req, resp);

			Mockito.when(EntityUtils.toString(entity)).thenReturn("{\"IsSuccess\":false}");
			Mockito.when(resp.getWriter()).thenReturn(writer);
			lttrPickupLocationServlet.doGet(req, resp);

			Mockito.when(httpClient.execute(Mockito.any())).thenReturn(null);
			lttrPickupLocationServlet.doGet(req, resp);

			Mockito.when(req.getPathInfo()).thenReturn("/content/vida");
			lttrPickupLocationServlet.doGet(req, resp);

			assertNotNull(lttrPickupLocationServlet);
		});
	}

}
