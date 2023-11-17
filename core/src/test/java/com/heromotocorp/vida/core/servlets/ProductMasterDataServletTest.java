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
class ProductMasterDataServletTest {

	private final AemContext context = new AemContext();

	private ProductMasterDataServlet productMasterDataServlet = new ProductMasterDataServlet();

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
		PrivateAccessor.setField(productMasterDataServlet, "globalConfig", globalConfig);

		clientConfig = context.getService(ClientConfig.class);
		clientConfig = mock(ClientConfig.class);
		PrivateAccessor.setField(productMasterDataServlet, "clientConfig", clientConfig);

	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.servlets.ProductMasterDataServlet#doGet(org.apache.sling.api.SlingHttpServletRequest, org.apache.sling.api.SlingHttpServletResponse)}.
	 * 
	 * @throws IOException
	 * @throws ServletException
	 */
	@Test
	void testDoGetSlingHttpServletRequestSlingHttpServletResponse() throws ServletException, IOException {
		Assertions.assertTimeoutPreemptively(Duration.ofSeconds(2), () -> {
			Mockito.mockStatic(EntityUtils.class);
			Mockito.mockStatic(HttpClients.class);
			Mockito.when(req.getPathInfo()).thenReturn("/content/vida.product-master.India.json");
			Mockito.when(globalConfig.postRequestToken()).thenReturn("postRequestToken");
			Mockito.when(globalConfig.grantType()).thenReturn("grantType");
			Mockito.when(globalConfig.clientId()).thenReturn("clientId");
			Mockito.when(globalConfig.clientSecret()).thenReturn("clientSecret");
			Mockito.when(globalConfig.username()).thenReturn("username");
			Mockito.when(globalConfig.password()).thenReturn("password");
			Mockito.when(globalConfig.contentType()).thenReturn("contentType");
			Mockito.when(globalConfig.sfdcQueryEndpoint()).thenReturn("sfdcQueryEndpoint");
			Mockito.when(resp.getWriter()).thenReturn(writer);
			productMasterDataServlet.doGet(req, resp);

			Mockito.when(clientConfig.client()).thenReturn(httpClient);
			Mockito.when(httpClient.execute(Mockito.any(HttpPost.class))).thenReturn(response);
			Mockito.when(response.getEntity()).thenReturn(entity);
			Mockito.when(EntityUtils.toString(entity)).thenReturn(
					"{\"access_token\":\"00D5h000004zDWU!AQEAQACaYxWAqNHE4L35go7ZSeD23TxdoE73_rLFrW8yAHaBdtvcUb7jB3312.ow2qPas6Ud9.4VwrpEz5Ybee1of8mQhqTv\",\"instance_url\":\"https://vidaworld.my.salesforce.com\",\"id\":\"https://login.salesforce.com/id/00D5h000004zDWUEA2/0055h000006CcsGAAS\",\"token_type\":\"Bearer\",\"issued_at\":\"1690363697815\",\"signature\":\"ufQFGoXyWmL2iwYS3OU9h0VmYR4vKsVD0UJ5P5LrdHo=\"}");
			Mockito.when(HttpClients.createDefault()).thenReturn(httpClient1);
			Mockito.when(httpClient1.execute(Mockito.any(HttpGet.class))).thenReturn(null);
			productMasterDataServlet.doGet(req, resp);

			Mockito.when(httpClient1.execute(Mockito.any(HttpGet.class))).thenReturn(httpResponse);
			Mockito.when(httpResponse.getEntity()).thenReturn(entity1);
			Mockito.when(EntityUtils.toString(entity1)).thenReturn(
					"{\"totalSize\":2,\"done\":true,\"records\":[{\"attributes\":{\"type\":\"dmpl__Item__c\",\"url\":\"/services/data/v53.0/sobjects/dmpl__Item__c/a1Gp0000000FaIyEAK\"},\"Id\":\"a1Gp0000000FaIyEAK\",\"Name\":\"V1 PLUS\",\"dmpl__Description__c\":\"VIDA V1 PLUS SC 7P RMV MAT PEARL WHITE\",\"dmpl__ItemCode__c\":\"V1PLASARCEL\",\"dmpl__SKUs__r\":{\"totalSize\":3,\"done\":true,\"records\":[{\"attributes\":{\"type\":\"dmpl__SKU__c\",\"url\":\"/services/data/v53.0/sobjects/dmpl__SKU__c/a1mp0000000EsOLAA0\"},\"Name\":\"VIDA V1 PLUS SC 7P RMV MAT PEARL WHITE\",\"Range__c\":\"\",\"TopSpeed__c\":\"\",\"ChargingTime__c\":\"\",\"HorsePowerKWH__c\":\"\",\"Battery__c\":2.0,\"BatteryType__c\":\"Li-Po\",\"BKTYPE__c\":null,\"CC__c\":125.0,\"ClassofVehicle__c\":\"Scooter\",\"CreatedById\":\"005p0000005AvcjAAC\",\"CreatedDate\":\"2022-10-18T13:42:59.000+0000\",\"dmpl__IsDefault__c\":false,\"dmpl__QuantityUnitOfMeasure__c\":\"Each\",\"FuelUsed__c\":\"Electric (BOV)\",\"GrossWeight__c\":\"\",\"Speedin3Sec__c\":\"40 km/hr\",\"dmpl__ProductColor__c\":\"\",\"SKUDescription__c\":\"\",\"dmpl__SKUCode__c\":\"V1PLASARCELMWT\",\"dmpl__ItemId__c\":\"a1Gp0000000FaIyEAK\",\"Id\":\"a1mp0000000EsOLAA0\"},{\"attributes\":{\"type\":\"dmpl__SKU__c\",\"url\":\"/services/data/v53.0/sobjects/dmpl__SKU__c/a1mp0000000EsOMAA0\"},\"Name\":\"VIDA V1 PLUS SC 7P RMV MAT SPORTS RED\",\"Range__c\":\"143 km\",\"TopSpeed__c\":\"80 kmph\",\"ChargingTime__c\":\"65 min\",\"HorsePowerKWH__c\":10.0,\"Battery__c\":2.0,\"BatteryType__c\":\"Li-Po\",\"BKTYPE__c\":null,\"CC__c\":125.0,\"ClassofVehicle__c\":\"Scooter\",\"CreatedById\":\"005p0000005AvcjAAC\",\"CreatedDate\":\"2022-10-18T13:42:59.000+0000\",\"dmpl__IsDefault__c\":false,\"dmpl__QuantityUnitOfMeasure__c\":\"Each\",\"FuelUsed__c\":\"Electric (BOV)\",\"GrossWeight__c\":115.0,\"Speedin3Sec__c\":\"40 km/hr\",\"dmpl__ProductColor__c\":\"Red\",\"SKUDescription__c\":\"VIDA V1 PLUS SC 7P RMV MAT SPORTS RED\",\"dmpl__SKUCode__c\":\"V1PLASARCELMSR\",\"dmpl__ItemId__c\":\"a1Gp0000000FaIyEAK\",\"Id\":\"a1mp0000000EsOMAA0\"},{\"attributes\":{\"type\":\"dmpl__SKU__c\",\"url\":\"/services/data/v53.0/sobjects/dmpl__SKU__c/a1mp0000001UbX1AAK\"},\"Name\":\"VIDA V1 PLUS SC 7P RMV MAT ABRAX ORANGE\",\"Range__c\":\"143 km\",\"TopSpeed__c\":\"80 kmph\",\"ChargingTime__c\":\"65 min\",\"HorsePowerKWH__c\":10.0,\"Battery__c\":2.0,\"BatteryType__c\":\"Li-Po\",\"BKTYPE__c\":null,\"CC__c\":125.0,\"ClassofVehicle__c\":\"Scooter\",\"CreatedById\":\"005p0000005HwaSAAS\",\"CreatedDate\":\"2023-05-09T10:49:29.000+0000\",\"dmpl__IsDefault__c\":false,\"dmpl__QuantityUnitOfMeasure__c\":\"Each\",\"FuelUsed__c\":\"Electric (BOV)\",\"GrossWeight__c\":150.0,\"Speedin3Sec__c\":\"40 km/hr\",\"dmpl__ProductColor__c\":\"MAT ABRAX ORANGE\",\"SKUDescription__c\":\"VIDA V1 PLUS SC 7P RMV MAT ABRAX ORANGE\",\"dmpl__SKUCode__c\":\"V1PLASARCELMAO\",\"dmpl__ItemId__c\":\"a1Gp0000000FaIyEAK\",\"Id\":\"a1mp0000001UbX1AAK\"}]}},{\"attributes\":{\"type\":\"dmpl__Item__c\",\"url\":\"/services/data/v53.0/sobjects/dmpl__Item__c/a1Gp0000000FaIzEAK\"},\"Id\":\"a1Gp0000000FaIzEAK\",\"Name\":\"V1 PRO\",\"dmpl__Description__c\":\"VIDA V1 PRO SC 8P RMV MAT PEARL WHITE\",\"dmpl__ItemCode__c\":\"V1PRASBRCEL\",\"dmpl__SKUs__r\":{\"totalSize\":3,\"done\":true,\"records\":[{\"attributes\":{\"type\":\"dmpl__SKU__c\",\"url\":\"/services/data/v53.0/sobjects/dmpl__SKU__c/a1mp0000000EsOQAA0\"},\"Name\":\"VIDA V1 PRO SC 8P RMV MAT PEARL WHITE\",\"Range__c\":\"165 km\",\"TopSpeed__c\":\"80 kmph\",\"ChargingTime__c\":\"65 min\",\"HorsePowerKWH__c\":3.94,\"Battery__c\":2.0,\"BatteryType__c\":\"Li-ion NMC\",\"BKTYPE__c\":null,\"CC__c\":0.0,\"ClassofVehicle__c\":\"Scooter\",\"CreatedById\":\"005p0000005AvcjAAC\",\"CreatedDate\":\"2022-10-18T13:59:40.000+0000\",\"dmpl__IsDefault__c\":false,\"dmpl__QuantityUnitOfMeasure__c\":\"Each\",\"FuelUsed__c\":\"Electric (BEV)\",\"GrossWeight__c\":115.0,\"Speedin3Sec__c\":\"40 km/hr\",\"dmpl__ProductColor__c\":\"White\",\"SKUDescription__c\":\"VIDA V1 PRO SC 8P RMV MAT PEARL WHITE\",\"dmpl__SKUCode__c\":\"V1PRASBRCELMWT\",\"dmpl__ItemId__c\":\"a1Gp0000000FaIzEAK\",\"Id\":\"a1mp0000000EsOQAA0\"},{\"attributes\":{\"type\":\"dmpl__SKU__c\",\"url\":\"/services/data/v53.0/sobjects/dmpl__SKU__c/a1mp0000000EsORAA0\"},\"Name\":\"VIDA V1 PRO SC 8P RMV MAT SPORTS RED\",\"Range__c\":\"165 km\",\"TopSpeed__c\":\"80 kmph\",\"ChargingTime__c\":\"65 min\",\"HorsePowerKWH__c\":3.5,\"Battery__c\":2.0,\"BatteryType__c\":\"Li-ion NMC\",\"BKTYPE__c\":null,\"CC__c\":0.0,\"ClassofVehicle__c\":\"Scooter\",\"CreatedById\":\"005p0000005AvcjAAC\",\"CreatedDate\":\"2022-10-18T13:59:40.000+0000\",\"dmpl__IsDefault__c\":false,\"dmpl__QuantityUnitOfMeasure__c\":\"Each\",\"FuelUsed__c\":\"Electric (BEV)\",\"GrossWeight__c\":275.0,\"Speedin3Sec__c\":\"40 km/hr\",\"dmpl__ProductColor__c\":\"Red\",\"SKUDescription__c\":\"VIDA V1 PRO SC 8P RMV MAT SPORTS RED\",\"dmpl__SKUCode__c\":\"V1PRASBRCELMSR\",\"dmpl__ItemId__c\":\"a1Gp0000000FaIzEAK\",\"Id\":\"a1mp0000000EsORAA0\"},{\"attributes\":{\"type\":\"dmpl__SKU__c\",\"url\":\"/services/data/v53.0/sobjects/dmpl__SKU__c/a1mp0000000EsOTAA0\"},\"Name\":\"VIDA V1 PRO SC 8P RMV MAT ABRAX ORANGE\",\"Range__c\":\"165 km\",\"TopSpeed__c\":\"80 kmph\",\"ChargingTime__c\":\"65 min\",\"HorsePowerKWH__c\":3.5,\"Battery__c\":2.0,\"BatteryType__c\":\"Li-ion NMC\",\"BKTYPE__c\":null,\"CC__c\":0.0,\"ClassofVehicle__c\":\"Scooter\",\"CreatedById\":\"005p0000005AvcjAAC\",\"CreatedDate\":\"2022-10-18T13:59:40.000+0000\",\"dmpl__IsDefault__c\":false,\"dmpl__QuantityUnitOfMeasure__c\":\"Each\",\"FuelUsed__c\":\"Electric (BEV)\",\"GrossWeight__c\":275.0,\"Speedin3Sec__c\":\"40 km/hr\",\"dmpl__ProductColor__c\":\"Orange\",\"SKUDescription__c\":\"VIDA V1 PRO SC 8P RMV MAT ABRAX ORANGE\",\"dmpl__SKUCode__c\":\"V1PRASBRCELMAO\",\"dmpl__ItemId__c\":\"a1Gp0000000FaIzEAK\",\"Id\":\"a1mp0000000EsOTAA0\"}]}}]}");
			productMasterDataServlet.doGet(req, resp);
			assertNotNull(productMasterDataServlet);
		});
	}

}
