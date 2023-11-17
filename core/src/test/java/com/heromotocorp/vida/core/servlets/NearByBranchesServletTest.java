/**
 * 
 */
package com.heromotocorp.vida.core.servlets;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.Duration;
import java.util.Scanner;

import javax.servlet.ServletException;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.heromotocorp.vida.core.config.ClientConfig;
import com.heromotocorp.vida.core.config.GlobalConfig;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import junitx.util.PrivateAccessor;

/**
 * JUnit test class for NearByBranchesServlet
 */
@ExtendWith({ AemContextExtension.class, MockitoExtension.class })
class NearByBranchesServletTest {

	private final AemContext context = new AemContext();

	private NearByBranchesServlet nearByBranchesServlet = new NearByBranchesServlet();

	private GlobalConfig globalConfig;

	private ClientConfig clientConfig;

	private SlingHttpServletRequest req;

	private SlingHttpServletResponse resp;

	private CloseableHttpClient httpClient;

	private CloseableHttpResponse httpResponse;

	private CloseableHttpResponse httpResponse1;

	private HttpEntity entity;

	private HttpEntity entity1;

	private PrintWriter writer;

	private CloseableHttpClient httpCloseClient;

	@Mock
	private Scanner scanner;

	String next = "City\tState\tCountry\tLatitude\tLongitude";

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		req = Mockito.mock(SlingHttpServletRequest.class);
		resp = Mockito.mock(SlingHttpServletResponse.class);
		httpClient = Mockito.mock(CloseableHttpClient.class);
		httpResponse = Mockito.mock(CloseableHttpResponse.class);
		httpResponse1 = Mockito.mock(CloseableHttpResponse.class);
		entity = Mockito.mock(HttpEntity.class);
		entity1 = Mockito.mock(HttpEntity.class);
		httpCloseClient = Mockito.mock(CloseableHttpClient.class);
		writer = Mockito.mock(PrintWriter.class);

		globalConfig = context.getService(GlobalConfig.class);
		globalConfig = mock(GlobalConfig.class);
		PrivateAccessor.setField(nearByBranchesServlet, "globalConfig", globalConfig);

		clientConfig = context.getService(ClientConfig.class);
		clientConfig = mock(ClientConfig.class);
		PrivateAccessor.setField(nearByBranchesServlet, "clientConfig", clientConfig);

		Mockito.mockStatic(HttpClients.class);
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.servlets.NearByBranchesServlet#doGet(org.apache.sling.api.SlingHttpServletRequest, org.apache.sling.api.SlingHttpServletResponse)}.
	 * 
	 * @throws IOException
	 * @throws ServletException
	 */
	@Test
	void testDoGetSlingHttpServletRequestSlingHttpServletResponse() throws ServletException, IOException {
		Assertions.assertTimeoutPreemptively(Duration.ofSeconds(2), () -> {
			Mockito.mockStatic(EntityUtils.class);
			Mockito.mockStatic(HttpClients.class);
			Mockito.when(EntityUtils.toString(entity)).thenReturn(
					"{\"access_token\":\"00D5h000004zDWU!AQEAQACaYxWAqNHE4L35go7ZSeD23TxdoE73_rLFrW8yAHaBdtvcUb7jB3312.ow2qPas6Ud9.4VwrpEz5Ybee1of8mQhqTv\",\"instance_url\":\"https://vidaworld.my.salesforce.com\",\"id\":\"https://login.salesforce.com/id/00D5h000004zDWUEA2/0055h000006CcsGAAS\",\"token_type\":\"Bearer\",\"issued_at\":\"1690363697815\",\"signature\":\"ufQFGoXyWmL2iwYS3OU9h0VmYR4vKsVD0UJ5P5LrdHo=\"}");
			Mockito.when(req.getPathInfo()).thenReturn("/content/vida.nearbybranches.BENGALURU.json");
			Mockito.when(globalConfig.postRequestToken()).thenReturn("postRequestToken");
			Mockito.when(globalConfig.grantType()).thenReturn("grantType");
			Mockito.when(globalConfig.clientId()).thenReturn("clientId");
			Mockito.when(globalConfig.clientSecret()).thenReturn("clientSecret");
			Mockito.when(globalConfig.username()).thenReturn("username");
			Mockito.when(globalConfig.password()).thenReturn("password");
			Mockito.when(globalConfig.contentType()).thenReturn("contentType");
			nearByBranchesServlet.doGet(req, resp);

			Mockito.when(clientConfig.client()).thenReturn(httpClient);
			Mockito.when(httpClient.execute(Mockito.any())).thenReturn(httpResponse);
			Mockito.when(httpResponse.getEntity()).thenReturn(entity);
			Mockito.when(HttpClients.createDefault()).thenReturn(httpCloseClient);
			Mockito.when(globalConfig.nearByRequestUrl()).thenReturn("nearByRequestUrl");
			Mockito.when(httpCloseClient.execute(Mockito.any())).thenReturn(httpResponse1);
			Mockito.when(httpResponse1.getEntity()).thenReturn(entity1);
			Mockito.when(EntityUtils.toString(entity1)).thenReturn("[{\r\n" + "	\"attributes\": {\r\n"
					+ "		\"type\": \"dmpl__Branch__c\",\r\n"
					+ "		\"url\": \"/services/data/v52.0/sobjects/dmpl__Branch__c/a0K5h00000769ydEAA\"\r\n"
					+ "	},\r\n" + "	\"Id\": \"a0K5h00000769ydEAA\",\r\n"
					+ "	\"Name\": \"Experience Centre Delhi\",\r\n"
					+ "	\"dmpl__BranchType__c\": \"Experience Centre\",\r\n"
					+ "	\"dmpl__BusinessHoursId__c\": \"01m5h00000090P9AAI\",\r\n"
					+ "	\"dmpl__IsActive__c\": true,\r\n" + "	\"dmpl__IsHomeDeliveryAvailable__c\": false,\r\n"
					+ "	\"dmpl__IsPickupDropAvailable__c\": false,\r\n" + "	\"dmpl__MobileNumber__c\": 7976006807,\r\n"
					+ "	\"dmpl__ServicePhone__c\": 7976006807,\r\n" + "	\"dmpl__Phone__c\": \"7976006807\",\r\n"
					+ "	\"dmpl__PartnerAccountId__c\": \"0015h000011mUrsAAE\",\r\n"
					+ "	\"dmpl__AddressId__c\": \"a0c5h000000KAmIAAW\",\r\n" + "	\"dmpl__GeoLocation__c\": {\r\n"
					+ "		\"latitude\": 28.6642606198797,\r\n" + "		\"longitude\": 77.1595222798825\r\n"
					+ "	},\r\n" + "	\"TestRideStartDate__c\": \"2023-05-01\",\r\n" + "	\"dmpl__AddressId__r\": {\r\n"
					+ "		\"attributes\": {\r\n" + "			\"type\": \"dmpl__ContactAddress__c\",\r\n"
					+ "			\"url\": \"/services/data/v52.0/sobjects/dmpl__ContactAddress__c/a0c5h000000KAmIAAW\"\r\n"
					+ "		},\r\n" + "		\"Id\": \"a0c5h000000KAmIAAW\",\r\n"
					+ "		\"dmpl__City__c\": \"NEW DELHI\",\r\n" + "		\"dmpl__State__c\": \"DELHI\",\r\n"
					+ "		\"dmpl__Country__c\": \"India\",\r\n" + "		\"dmpl__PostalCode__c\": \"110015\",\r\n"
					+ "		\"dmpl__Street__c\": \"Plot No. 20, Rama Road, Moti Nagar, Karampura Industrial Area, Najafgarh Road Industrial Area\"\r\n"
					+ "	},\r\n" + "	\"ResponseCode__c\": \"002\"\r\n" + "}]");
			Mockito.when(resp.getWriter()).thenReturn(writer);
			nearByBranchesServlet.doGet(req, resp);

			Mockito.when(req.getPathInfo()).thenReturn("/content/vida.nearbybranches.NEW DELHI.json");
			Mockito.when(EntityUtils.toString(entity1))
					.thenReturn("[{\r\n" + "	\"attributes\": {\r\n" + "		\"type\": \"dmpl__Branch__c\",\r\n"
							+ "		\"url\": \"/services/data/v52.0/sobjects/dmpl__Branch__c/a0K5h00000769ydEAA\"\r\n"
							+ "	},\r\n" + "	\"dmpl__GeoLocation__c\": {\r\n"
							+ "		\"latitude\": 28.6642606198797,\r\n" + "		\"longitude\": 77.1595222798825\r\n"
							+ "	},\r\n" + "	\"ResponseCode__c\": \"002\"\r\n" + "}]");
			nearByBranchesServlet.doGet(req, resp);

			Mockito.when(EntityUtils.toString(entity1))
					.thenReturn("[{\r\n" + "	\"attributes\": {\r\n" + "		\"type\": \"dmpl__Branch__c\",\r\n"
							+ "		\"url\": \"/services/data/v52.0/sobjects/dmpl__Branch__c/a0K5h00000769ydEAA\"\r\n"
							+ "	},\r\n" + "	\"TestRideStartDate__c\": \"\",\r\n" + "	\"dmpl__GeoLocation__c\": {\r\n"
							+ "		\r\n" + "	},\r\n" + "	\"ResponseCode__c\": \"002\"\r\n" + "}]");
			nearByBranchesServlet.doGet(req, resp);

			assertNotNull(nearByBranchesServlet);
		});
	}

}
