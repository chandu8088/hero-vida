/**
 * 
 */
package com.heromotocorp.vida.core.service.impl;

import static org.mockito.Mockito.mock;

import java.time.Duration;

import javax.jcr.Session;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.junit.jupiter.api.Assertions;
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
 * JUnit test class for ProductMasterConfigServiceImpl
 */
@ExtendWith({ AemContextExtension.class, MockitoExtension.class })
class ProductMasterConfigServiceImplTest {

	private final AemContext context = new AemContext();

	private ProductMasterConfigServiceImpl productMasterConfigServiceImpl = new ProductMasterConfigServiceImpl();

	private GlobalConfig globalConfig;

	private ClientConfig clientConfig;

	private CloseableHttpResponse httpResponse;

	private HttpEntity entity;

	private HttpEntity entity1;

	private CloseableHttpClient httpClient1;

	private ResourceResolverFactory resolverFactory;

	private ResourceResolver resolver;

	private Session session;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		httpClient1 = Mockito.mock(CloseableHttpClient.class);
		httpResponse = Mockito.mock(CloseableHttpResponse.class);
		entity = Mockito.mock(HttpEntity.class);
		entity1 = Mockito.mock(HttpEntity.class);
		resolver = Mockito.mock(ResourceResolver.class);
		session = Mockito.mock(Session.class);

		globalConfig = context.getService(GlobalConfig.class);
		globalConfig = mock(GlobalConfig.class);
		PrivateAccessor.setField(productMasterConfigServiceImpl, "globalConfig", globalConfig);

		clientConfig = context.getService(ClientConfig.class);
		clientConfig = mock(ClientConfig.class);
		PrivateAccessor.setField(productMasterConfigServiceImpl, "clientConfig", clientConfig);

		resolverFactory = context.getService(ResourceResolverFactory.class);
		resolverFactory = mock(ResourceResolverFactory.class);
		PrivateAccessor.setField(productMasterConfigServiceImpl, "resolverFactory", resolverFactory);
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.service.impl.ProductMasterConfigServiceImpl#processProductMasterJson()}.
	 */
	@Test
	void testProcessProductMasterJson() {
		Assertions.assertTimeoutPreemptively(Duration.ofSeconds(2), () -> {
			Mockito.mockStatic(EntityUtils.class);
			Mockito.mockStatic(HttpClients.class);
			Mockito.mockStatic(CommonUtils.class);
			Mockito.when(EntityUtils.toString(entity)).thenReturn(
					"{\"access_token\":\"00D5h000004zDWU!AQEAQACaYxWAqNHE4L35go7ZSeD23TxdoE73_rLFrW8yAHaBdtvcUb7jB3312.ow2qPas6Ud9.4VwrpEz5Ybee1of8mQhqTv\",\"instance_url\":\"https://vidaworld.my.salesforce.com\",\"id\":\"https://login.salesforce.com/id/00D5h000004zDWUEA2/0055h000006CcsGAAS\",\"token_type\":\"Bearer\",\"issued_at\":\"1690363697815\",\"signature\":\"ufQFGoXyWmL2iwYS3OU9h0VmYR4vKsVD0UJ5P5LrdHo=\"}");
			Mockito.when(globalConfig.sfdcQueryEndpoint()).thenReturn("sfdcQueryEndpoint");
			Mockito.when(HttpClients.createDefault()).thenReturn(httpClient1);
			Mockito.when(httpClient1.execute(Mockito.any(HttpGet.class))).thenReturn(null);
			Mockito.when(CommonUtils.getResourceResolver(resolverFactory, Constants.WRITERSERVICEUSER))
					.thenReturn(resolver);
			Mockito.when(globalConfig.productListUrl()).thenReturn("productListUrl");
			Mockito.when(resolver.adaptTo(Session.class)).thenReturn(session);
			productMasterConfigServiceImpl.processProductMasterJson();

			Mockito.when(httpClient1.execute(Mockito.any(HttpGet.class))).thenReturn(httpResponse);
			Mockito.when(httpResponse.getEntity()).thenReturn(entity1);
			Mockito.when(EntityUtils.toString(entity1)).thenReturn(
					"{\r\n"
					+ "	\"totalSize\": 2,\r\n"
					+ "	\"done\": true,\r\n"
					+ "	\"records\": [{\r\n"
					+ "		\"attributes\": {\r\n"
					+ "			\"type\": \"dmpl__Item__c\",\r\n"
					+ "			\"url\": \"/services/data/v53.0/sobjects/dmpl__Item__c/a1Gp0000000FaIyEAK\"\r\n"
					+ "		},\r\n"
					+ "		\"Id\": \"a1Gp0000000FaIyEAK\",\r\n"
					+ "		\"Name\": \"V1 PLUS\",\r\n"
					+ "		\"dmpl__Description__c\": \"VIDA V1 PLUS SC 7P RMV MAT PEARL WHITE\",\r\n"
					+ "		\"dmpl__ItemCode__c\": \"V1PLASARCEL\",\r\n"
					+ "		\"dmpl__SKUs__r\": {\r\n"
					+ "			\"totalSize\": 3,\r\n"
					+ "			\"done\": true,\r\n"
					+ "			\"records\": [{\r\n"
					+ "				\"attributes\": {\r\n"
					+ "					\"type\": \"dmpl__SKU__c\",\r\n"
					+ "					\"url\": \"/services/data/v53.0/sobjects/dmpl__SKU__c/a1mp0000000EsOLAA0\"\r\n"
					+ "				},\r\n"
					+ "				\"Name\": \"VIDA V1 PLUS SC 7P RMV MAT PEARL WHITE\",\r\n"
					+ "				\"Range__c\": \"\",\r\n"
					+ "				\"TopSpeed__c\": \"\",\r\n"
					+ "				\"ChargingTime__c\": \"\",\r\n"
					+ "				\"HorsePowerKWH__c\": \"\",\r\n"
					+ "				\"Battery__c\": 2.0,\r\n"
					+ "				\"BatteryType__c\": \"Li-Po\",\r\n"
					+ "				\"BKTYPE__c\": null,\r\n"
					+ "				\"CC__c\": 125.0,\r\n"
					+ "				\"ClassofVehicle__c\": \"Scooter\",\r\n"
					+ "				\"CreatedById\": \"005p0000005AvcjAAC\",\r\n"
					+ "				\"CreatedDate\": \"2022-10-18T13:42:59.000+0000\",\r\n"
					+ "				\"dmpl__IsDefault__c\": false,\r\n"
					+ "				\"dmpl__QuantityUnitOfMeasure__c\": \"Each\",\r\n"
					+ "				\"FuelUsed__c\": \"Electric (BOV)\",\r\n"
					+ "				\"GrossWeight__c\": \"\",\r\n"
					+ "				\"Speedin3Sec__c\": \"40 km/hr\",\r\n"
					+ "				\"dmpl__ProductColor__c\": \"\",\r\n"
					+ "				\"SKUDescription__c\": \"\",\r\n"
					+ "				\"dmpl__SKUCode__c\": \"V1PLASARCELMWT\",\r\n"
					+ "				\"dmpl__ItemId__c\": \"a1Gp0000000FaIyEAK\",\r\n"
					+ "				\"Id\": \"a1mp0000000EsOLAA0\"\r\n"
					+ "			}, {\r\n"
					+ "				\"attributes\": {\r\n"
					+ "					\"type\": \"dmpl__SKU__c\",\r\n"
					+ "					\"url\": \"/services/data/v53.0/sobjects/dmpl__SKU__c/a1mp0000000EsOMAA0\"\r\n"
					+ "				},\r\n"
					+ "				\"Name\": \"VIDA V1 PLUS SC 7P RMV MAT SPORTS RED\",\r\n"
					+ "				\"Range__c\": \"143 km\",\r\n"
					+ "				\"TopSpeed__c\": \"80 kmph\",\r\n"
					+ "				\"ChargingTime__c\": \"65 min\",\r\n"
					+ "				\"HorsePowerKWH__c\": 10.0,\r\n"
					+ "				\"Battery__c\": 2.0,\r\n"
					+ "				\"BatteryType__c\": \"Li-Po\",\r\n"
					+ "				\"BKTYPE__c\": null,\r\n"
					+ "				\"CC__c\": 125.0,\r\n"
					+ "				\"ClassofVehicle__c\": \"Scooter\",\r\n"
					+ "				\"CreatedById\": \"005p0000005AvcjAAC\",\r\n"
					+ "				\"CreatedDate\": \"2022-10-18T13:42:59.000+0000\",\r\n"
					+ "				\"dmpl__IsDefault__c\": false,\r\n"
					+ "				\"dmpl__QuantityUnitOfMeasure__c\": \"Each\",\r\n"
					+ "				\"FuelUsed__c\": \"Electric (BOV)\",\r\n"
					+ "				\"GrossWeight__c\": 115.0,\r\n"
					+ "				\"Speedin3Sec__c\": \"40 km/hr\",\r\n"
					+ "				\"dmpl__ProductColor__c\": \"Red\",\r\n"
					+ "				\"SKUDescription__c\": \"VIDA V1 PLUS SC 7P RMV MAT SPORTS RED\",\r\n"
					+ "				\"dmpl__SKUCode__c\": \"V1PLASARCELMSR\",\r\n"
					+ "				\"dmpl__ItemId__c\": \"a1Gp0000000FaIyEAK\",\r\n"
					+ "				\"Id\": \"a1mp0000000EsOMAA0\"\r\n"
					+ "			}, {\r\n"
					+ "				\"attributes\": {\r\n"
					+ "					\"type\": \"dmpl__SKU__c\",\r\n"
					+ "					\"url\": \"/services/data/v53.0/sobjects/dmpl__SKU__c/a1mp0000001UbX1AAK\"\r\n"
					+ "				},\r\n"
					+ "				\"Name\": \"VIDA V1 PLUS SC 7P RMV MAT ABRAX ORANGE\",\r\n"
					+ "				\"Range__c\": \"143 km\",\r\n"
					+ "				\"TopSpeed__c\": \"80 kmph\",\r\n"
					+ "				\"ChargingTime__c\": \"65 min\",\r\n"
					+ "				\"HorsePowerKWH__c\": 10.0,\r\n"
					+ "				\"Battery__c\": 2.0,\r\n"
					+ "				\"BatteryType__c\": \"Li-Po\",\r\n"
					+ "				\"BKTYPE__c\": null,\r\n"
					+ "				\"CC__c\": 125.0,\r\n"
					+ "				\"ClassofVehicle__c\": \"Scooter\",\r\n"
					+ "				\"CreatedById\": \"005p0000005HwaSAAS\",\r\n"
					+ "				\"CreatedDate\": \"2023-05-09T10:49:29.000+0000\",\r\n"
					+ "				\"dmpl__IsDefault__c\": false,\r\n"
					+ "				\"dmpl__QuantityUnitOfMeasure__c\": \"Each\",\r\n"
					+ "				\"FuelUsed__c\": \"Electric (BOV)\",\r\n"
					+ "				\"GrossWeight__c\": 150.0,\r\n"
					+ "				\"Speedin3Sec__c\": \"40 km/hr\",\r\n"
					+ "				\"dmpl__ProductColor__c\": \"MAT ABRAX ORANGE\",\r\n"
					+ "				\"SKUDescription__c\": \"VIDA V1 PLUS SC 7P RMV MAT ABRAX ORANGE\",\r\n"
					+ "				\"dmpl__SKUCode__c\": \"V1PLASARCELMAO\",\r\n"
					+ "				\"dmpl__ItemId__c\": \"a1Gp0000000FaIyEAK\",\r\n"
					+ "				\"Id\": \"a1mp0000001UbX1AAK\"\r\n"
					+ "			}]\r\n"
					+ "		}\r\n"
					+ "	}, {\r\n"
					+ "		\"attributes\": {\r\n"
					+ "			\"type\": \"dmpl__Item__c\",\r\n"
					+ "			\"url\": \"/services/data/v53.0/sobjects/dmpl__Item__c/a1Gp0000000FaIzEAK\"\r\n"
					+ "		},\r\n"
					+ "		\"Id\": \"a1Gp0000000FaIzEAK\",\r\n"
					+ "		\"Name\": \"V1 PRO\",\r\n"
					+ "		\"dmpl__Description__c\": \"VIDA V1 PRO SC 8P RMV MAT PEARL WHITE\",\r\n"
					+ "		\"dmpl__ItemCode__c\": \"V1PRASBRCEL\",\r\n"
					+ "		\"dmpl__SKUs__r\": {\r\n"
					+ "			\"totalSize\": 3,\r\n"
					+ "			\"done\": true,\r\n"
					+ "			\"records\": [{\r\n"
					+ "				\"attributes\": {\r\n"
					+ "					\"type\": \"dmpl__SKU__c\",\r\n"
					+ "					\"url\": \"/services/data/v53.0/sobjects/dmpl__SKU__c/a1mp0000000EsOQAA0\"\r\n"
					+ "				},\r\n"
					+ "				\"Name\": \"VIDA V1 PRO SC 8P RMV MAT PEARL WHITE\",\r\n"
					+ "				\"Range__c\": \"165 km\",\r\n"
					+ "				\"TopSpeed__c\": \"80 kmph\",\r\n"
					+ "				\"ChargingTime__c\": \"65 min\",\r\n"
					+ "				\"HorsePowerKWH__c\": 3.94,\r\n"
					+ "				\"Battery__c\": 2.0,\r\n"
					+ "				\"BatteryType__c\": \"Li-ion NMC\",\r\n"
					+ "				\"BKTYPE__c\": null,\r\n"
					+ "				\"CC__c\": 0.0,\r\n"
					+ "				\"ClassofVehicle__c\": \"Scooter\",\r\n"
					+ "				\"CreatedById\": \"005p0000005AvcjAAC\",\r\n"
					+ "				\"CreatedDate\": \"2022-10-18T13:59:40.000+0000\",\r\n"
					+ "				\"dmpl__IsDefault__c\": false,\r\n"
					+ "				\"dmpl__QuantityUnitOfMeasure__c\": \"Each\",\r\n"
					+ "				\"FuelUsed__c\": \"Electric (BEV)\",\r\n"
					+ "				\"GrossWeight__c\": 115.0,\r\n"
					+ "				\"Speedin3Sec__c\": \"40 km/hr\",\r\n"
					+ "				\"dmpl__ProductColor__c\": \"White\",\r\n"
					+ "				\"SKUDescription__c\": \"VIDA V1 PRO SC 8P RMV MAT PEARL WHITE\",\r\n"
					+ "				\"dmpl__SKUCode__c\": \"V1PRASBRCELMWT\",\r\n"
					+ "				\"dmpl__ItemId__c\": \"a1Gp0000000FaIzEAK\",\r\n"
					+ "				\"Id\": \"a1mp0000000EsOQAA0\"\r\n"
					+ "			}, {\r\n"
					+ "				\"attributes\": {\r\n"
					+ "					\"type\": \"dmpl__SKU__c\",\r\n"
					+ "					\"url\": \"/services/data/v53.0/sobjects/dmpl__SKU__c/a1mp0000000EsORAA0\"\r\n"
					+ "				},\r\n"
					+ "				\"Name\": \"VIDA V1 PRO SC 8P RMV MAT SPORTS RED\",\r\n"
					+ "				\"Range__c\": \"165 km\",\r\n"
					+ "				\"TopSpeed__c\": \"80 kmph\",\r\n"
					+ "				\"ChargingTime__c\": \"65 min\",\r\n"
					+ "				\"HorsePowerKWH__c\": 3.5,\r\n"
					+ "				\"Battery__c\": 2.0,\r\n"
					+ "				\"BatteryType__c\": \"Li-ion NMC\",\r\n"
					+ "				\"BKTYPE__c\": null,\r\n"
					+ "				\"CC__c\": 0.0,\r\n"
					+ "				\"ClassofVehicle__c\": \"Scooter\",\r\n"
					+ "				\"CreatedById\": \"005p0000005AvcjAAC\",\r\n"
					+ "				\"CreatedDate\": \"2022-10-18T13:59:40.000+0000\",\r\n"
					+ "				\"dmpl__IsDefault__c\": false,\r\n"
					+ "				\"dmpl__QuantityUnitOfMeasure__c\": \"Each\",\r\n"
					+ "				\"FuelUsed__c\": \"Electric (BEV)\",\r\n"
					+ "				\"GrossWeight__c\": 275.0,\r\n"
					+ "				\"Speedin3Sec__c\": \"40 km/hr\",\r\n"
					+ "				\"dmpl__ProductColor__c\": \"Red\",\r\n"
					+ "				\"SKUDescription__c\": \"VIDA V1 PRO SC 8P RMV MAT SPORTS RED\",\r\n"
					+ "				\"dmpl__SKUCode__c\": \"V1PRASBRCELMSR\",\r\n"
					+ "				\"dmpl__ItemId__c\": \"a1Gp0000000FaIzEAK\",\r\n"
					+ "				\"Id\": \"a1mp0000000EsORAA0\",\r\n"
					+ "			    \"HorsePower_Display_Name__c\": null,\r\n"
					+ "			    \"RidingModes__c\": null,\r\n"
					+ "			    \"SeatingType__c\": null\r\n"
					+ "			}, {\r\n"
					+ "				\"attributes\": {\r\n"
					+ "					\"type\": \"dmpl__SKU__c\",\r\n"
					+ "					\"url\": \"/services/data/v53.0/sobjects/dmpl__SKU__c/a1mp0000000EsOTAA0\"\r\n"
					+ "				},\r\n"
					+ "				\"Name\": \"VIDA V1 PRO SC 8P RMV MAT ABRAX ORANGE\",\r\n"
					+ "				\"Range__c\": null,\r\n"
					+ "				\"TopSpeed__c\": null,\r\n"
					+ "				\"ChargingTime__c\": null,\r\n"
					+ "				\"HorsePowerKWH__c\": null,\r\n"
					+ "				\"Battery__c\": 2.0,\r\n"
					+ "				\"BatteryType__c\": \"Li-ion NMC\",\r\n"
					+ "				\"BKTYPE__c\": null,\r\n"
					+ "				\"CC__c\": 0.0,\r\n"
					+ "				\"ClassofVehicle__c\": \"Scooter\",\r\n"
					+ "				\"CreatedById\": \"005p0000005AvcjAAC\",\r\n"
					+ "				\"CreatedDate\": \"2022-10-18T13:59:40.000+0000\",\r\n"
					+ "				\"dmpl__IsDefault__c\": false,\r\n"
					+ "				\"dmpl__QuantityUnitOfMeasure__c\": \"Each\",\r\n"
					+ "				\"FuelUsed__c\": \"Electric (BEV)\",\r\n"
					+ "				\"GrossWeight__c\": null,\r\n"
					+ "				\"Speedin3Sec__c\": \"40 km/hr\",\r\n"
					+ "				\"dmpl__ProductColor__c\": null,\r\n"
					+ "				\"SKUDescription__c\": \"VIDA V1 PRO SC 8P RMV MAT ABRAX ORANGE\",\r\n"
					+ "				\"dmpl__SKUCode__c\": \"V1PRASBRCELMAO\",\r\n"
					+ "				\"dmpl__ItemId__c\": \"a1Gp0000000FaIzEAK\",\r\n"
					+ "				\"Id\": \"a1mp0000000EsOTAA0\",\r\n"
					+ "			    \"HorsePower_Display_Name__c\": \"Horse Power\",\r\n"
					+ "			    \"RidingModes__c\": \"RidingModes__c\",\r\n"
					+ "			    \"SeatingType__c\": \"SeatingType__c\"\r\n"
					+ "			}]\r\n"
					+ "		}\r\n"
					+ "	}, {\r\n"
					+ "		\"attributes\": {\r\n"
					+ "			\"type\": \"dmpl__Item__c\",\r\n"
					+ "			\"url\": \"/services/data/v53.0/sobjects/dmpl__Item__c/a1Gp0000000FaIzEAK\"\r\n"
					+ "		},\r\n"
					+ "		\"Id\": \"a1Gp0000000FaIzEAK\",\r\n"
					+ "		\"Name\": \"V1 PRO\",\r\n"
					+ "		\"dmpl__Description__c\": \"VIDA V1 PRO SC 8P RMV MAT PEARL WHITE\",\r\n"
					+ "		\"dmpl__ItemCode__c\": \"\",\r\n"
					+ "		\"dmpl__SKUs__r\": {}\r\n"
					+ "	}, {\r\n"
					+ "		\"attributes\": {\r\n"
					+ "			\"type\": \"dmpl__Item__c\",\r\n"
					+ "			\"url\": \"/services/data/v53.0/sobjects/dmpl__Item__c/a1Gp0000000FaIzEAK\"\r\n"
					+ "		},\r\n"
					+ "		\"Id\": \"a1Gp0000000FaIzEAK\",\r\n"
					+ "		\"Name\": \"V1 PRO\",\r\n"
					+ "		\"dmpl__Description__c\": \"VIDA V1 PRO SC 8P RMV MAT PEARL WHITE\",\r\n"
					+ "		\"dmpl__ItemCode__c\": null,\r\n"
					+ "		\"dmpl__SKUs__r\": null\r\n"
					+ "	}]\r\n"
					+ "}");
			productMasterConfigServiceImpl.processProductMasterJson();
		});
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.service.impl.ProductMasterConfigServiceImpl#processProductMasterJson()}.
	 */
	@Test
	void testProcessProductMasterJson1() {
		Assertions.assertTimeoutPreemptively(Duration.ofSeconds(2), () -> {
			Mockito.mockStatic(EntityUtils.class);
			Mockito.mockStatic(HttpClients.class);
			Mockito.mockStatic(CommonUtils.class);
			Mockito.when(EntityUtils.toString(entity)).thenReturn(
					"{\"access_token\":\"00D5h000004zDWU!AQEAQACaYxWAqNHE4L35go7ZSeD23TxdoE73_rLFrW8yAHaBdtvcUb7jB3312.ow2qPas6Ud9.4VwrpEz5Ybee1of8mQhqTv\",\"instance_url\":\"https://vidaworld.my.salesforce.com\",\"id\":\"https://login.salesforce.com/id/00D5h000004zDWUEA2/0055h000006CcsGAAS\",\"token_type\":\"Bearer\",\"issued_at\":\"1690363697815\",\"signature\":\"ufQFGoXyWmL2iwYS3OU9h0VmYR4vKsVD0UJ5P5LrdHo=\"}");
			Mockito.when(globalConfig.sfdcQueryEndpoint()).thenReturn("sfdcQueryEndpoint");
			Mockito.when(HttpClients.createDefault()).thenReturn(httpClient1);
			Mockito.when(httpClient1.execute(Mockito.any(HttpGet.class))).thenReturn(null);
			Mockito.when(CommonUtils.getResourceResolver(resolverFactory, Constants.WRITERSERVICEUSER))
					.thenReturn(resolver);
			Mockito.when(globalConfig.productListUrl()).thenReturn("productListUrl");
			Mockito.when(resolver.adaptTo(Session.class)).thenReturn(session);
			productMasterConfigServiceImpl.processProductMasterJson();

			Mockito.when(httpClient1.execute(Mockito.any(HttpGet.class))).thenReturn(httpResponse);
			Mockito.when(httpResponse.getEntity()).thenReturn(entity1);
			Mockito.when(EntityUtils.toString(entity1)).thenReturn(
					"{\"totalSize\":2,\"done\":true,\"records\":[{\"attributes\":{\"type\":\"dmpl__Item__c\",\"url\":\"/services/data/v53.0/sobjects/dmpl__Item__c/a1Gp0000000FaIyEAK\"},\"Id\":\"a1Gp0000000FaIyEAK\",\"Name\":\"V1 PLUS\",\"dmpl__Description__c\":\"VIDA V1 PLUS SC 7P RMV MAT PEARL WHITE\",\"dmpl__ItemCode__c\":\"V1PLASARCEL\",\"dmpl__SKUs__r\":{\"totalSize\":3,\"done\":true,\"records\":[{\"attributes\":{\"type\":\"dmpl__SKU__c\",\"url\":\"/services/data/v53.0/sobjects/dmpl__SKU__c/a1mp0000000EsOLAA0\"},\"Name\":\"VIDA V1 PLUS SC 7P RMV MAT PEARL WHITE\",\"Range__c\":\"\",\"TopSpeed__c\":\"80 kmph\",\"ChargingTime__c\":\"65 mins\",\"HorsePowerKWH__c\":3.5,\"Battery__c\":2.0,\"BatteryType__c\":\"Li-Po\",\"BKTYPE__c\":null,\"CC__c\":125.0,\"ClassofVehicle__c\":\"Scooter\",\"CreatedById\":\"005p0000005AvcjAAC\",\"CreatedDate\":\"2022-10-18T13:42:59.000+0000\",\"dmpl__IsDefault__c\":false,\"dmpl__QuantityUnitOfMeasure__c\":\"Each\",\"FuelUsed__c\":\"Electric (BOV)\",\"GrossWeight__c\":\"\",\"Speedin3Sec__c\":\"40 km/hr\",\"dmpl__ProductColor__c\":\"\",\"SKUDescription__c\":\"\",\"dmpl__SKUCode__c\":\"V1PLASARCELMWT\",\"dmpl__ItemId__c\":\"a1Gp0000000FaIyEAK\",\"Id\":\"a1mp0000000EsOLAA0\",\"HorsePower_Display_Name__c\":\"Horse Power\",\"RidingModes__c\":\"RidingModes__c\",\"SeatingType__c\":\"SeatingType__c\"},{\"attributes\":{\"type\":\"dmpl__SKU__c\",\"url\":\"/services/data/v53.0/sobjects/dmpl__SKU__c/a1mp0000000EsOMAA0\"},\"Name\":\"VIDA V1 PLUS SC 7P RMV MAT SPORTS RED\",\"Range__c\":\"143 km\",\"TopSpeed__c\":\"80 kmph\",\"ChargingTime__c\":\"65 min\",\"HorsePowerKWH__c\":10.0,\"Battery__c\":2.0,\"BatteryType__c\":\"Li-Po\",\"BKTYPE__c\":null,\"CC__c\":125.0,\"ClassofVehicle__c\":\"Scooter\",\"CreatedById\":\"005p0000005AvcjAAC\",\"CreatedDate\":\"2022-10-18T13:42:59.000+0000\",\"dmpl__IsDefault__c\":false,\"dmpl__QuantityUnitOfMeasure__c\":\"Each\",\"FuelUsed__c\":\"Electric (BOV)\",\"GrossWeight__c\":115.0,\"Speedin3Sec__c\":\"40 km/hr\",\"dmpl__ProductColor__c\":\"Red\",\"SKUDescription__c\":\"VIDA V1 PLUS SC 7P RMV MAT SPORTS RED\",\"dmpl__SKUCode__c\":\"V1PLASARCELMSR\",\"dmpl__ItemId__c\":\"a1Gp0000000FaIyEAK\",\"Id\":\"a1mp0000000EsOMAA0\"},{\"attributes\":{\"type\":\"dmpl__SKU__c\",\"url\":\"/services/data/v53.0/sobjects/dmpl__SKU__c/a1mp0000001UbX1AAK\"},\"Name\":\"VIDA V1 PLUS SC 7P RMV MAT ABRAX ORANGE\",\"Range__c\":\"143 km\",\"TopSpeed__c\":\"80 kmph\",\"ChargingTime__c\":\"65 min\",\"HorsePowerKWH__c\":10.0,\"Battery__c\":2.0,\"BatteryType__c\":\"Li-Po\",\"BKTYPE__c\":null,\"CC__c\":125.0,\"ClassofVehicle__c\":\"Scooter\",\"CreatedById\":\"005p0000005HwaSAAS\",\"CreatedDate\":\"2023-05-09T10:49:29.000+0000\",\"dmpl__IsDefault__c\":false,\"dmpl__QuantityUnitOfMeasure__c\":\"Each\",\"FuelUsed__c\":\"Electric (BOV)\",\"GrossWeight__c\":150.0,\"Speedin3Sec__c\":\"40 km/hr\",\"dmpl__ProductColor__c\":\"MAT ABRAX ORANGE\",\"SKUDescription__c\":\"VIDA V1 PLUS SC 7P RMV MAT ABRAX ORANGE\",\"dmpl__SKUCode__c\":\"V1PLASARCELMAO\",\"dmpl__ItemId__c\":\"a1Gp0000000FaIyEAK\",\"Id\":\"a1mp0000001UbX1AAK\"}]}},{\"attributes\":{\"type\":\"dmpl__Item__c\",\"url\":\"/services/data/v53.0/sobjects/dmpl__Item__c/a1Gp0000000FaIzEAK\"},\"Id\":\"a1Gp0000000FaIzEAK\",\"Name\":\"V1 PRO\",\"dmpl__Description__c\":\"VIDA V1 PRO SC 8P RMV MAT PEARL WHITE\",\"dmpl__ItemCode__c\":\"V1PRASBRCEL\",\"dmpl__SKUs__r\":{\"totalSize\":3,\"done\":true,\"records\":[{\"attributes\":{\"type\":\"dmpl__SKU__c\",\"url\":\"/services/data/v53.0/sobjects/dmpl__SKU__c/a1mp0000000EsOQAA0\"},\"Name\":\"VIDA V1 PRO SC 8P RMV MAT PEARL WHITE\",\"Range__c\":\"165 km\",\"TopSpeed__c\":\"80 kmph\",\"ChargingTime__c\":\"65 min\",\"HorsePowerKWH__c\":3.94,\"Battery__c\":2.0,\"BatteryType__c\":\"Li-ion NMC\",\"BKTYPE__c\":null,\"CC__c\":0.0,\"ClassofVehicle__c\":\"Scooter\",\"CreatedById\":\"005p0000005AvcjAAC\",\"CreatedDate\":\"2022-10-18T13:59:40.000+0000\",\"dmpl__IsDefault__c\":false,\"dmpl__QuantityUnitOfMeasure__c\":\"Each\",\"FuelUsed__c\":\"Electric (BEV)\",\"GrossWeight__c\":115.0,\"Speedin3Sec__c\":\"40 km/hr\",\"dmpl__ProductColor__c\":\"White\",\"SKUDescription__c\":\"VIDA V1 PRO SC 8P RMV MAT PEARL WHITE\",\"dmpl__SKUCode__c\":\"V1PRASBRCELMWT\",\"dmpl__ItemId__c\":\"a1Gp0000000FaIzEAK\",\"Id\":\"a1mp0000000EsOQAA0\"},{\"attributes\":{\"type\":\"dmpl__SKU__c\",\"url\":\"/services/data/v53.0/sobjects/dmpl__SKU__c/a1mp0000000EsORAA0\"},\"Name\":\"VIDA V1 PRO SC 8P RMV MAT SPORTS RED\",\"Range__c\":\"165 km\",\"TopSpeed__c\":\"80 kmph\",\"ChargingTime__c\":\"65 min\",\"HorsePowerKWH__c\":3.5,\"Battery__c\":2.0,\"BatteryType__c\":\"Li-ion NMC\",\"BKTYPE__c\":null,\"CC__c\":0.0,\"ClassofVehicle__c\":\"Scooter\",\"CreatedById\":\"005p0000005AvcjAAC\",\"CreatedDate\":\"2022-10-18T13:59:40.000+0000\",\"dmpl__IsDefault__c\":false,\"dmpl__QuantityUnitOfMeasure__c\":\"Each\",\"FuelUsed__c\":\"Electric (BEV)\",\"GrossWeight__c\":275.0,\"Speedin3Sec__c\":\"40 km/hr\",\"dmpl__ProductColor__c\":\"Red\",\"SKUDescription__c\":\"VIDA V1 PRO SC 8P RMV MAT SPORTS RED\",\"dmpl__SKUCode__c\":\"V1PRASBRCELMSR\",\"dmpl__ItemId__c\":\"a1Gp0000000FaIzEAK\",\"Id\":\"a1mp0000000EsORAA0\"},{\"attributes\":{\"type\":\"dmpl__SKU__c\",\"url\":\"/services/data/v53.0/sobjects/dmpl__SKU__c/a1mp0000000EsOTAA0\"},\"Name\":\"VIDA V1 PRO SC 8P RMV MAT ABRAX ORANGE\",\"Range__c\":\"165 km\",\"TopSpeed__c\":\"80 kmph\",\"ChargingTime__c\":\"65 min\",\"HorsePowerKWH__c\":3.5,\"Battery__c\":2.0,\"BatteryType__c\":\"Li-ion NMC\",\"BKTYPE__c\":null,\"CC__c\":0.0,\"ClassofVehicle__c\":\"Scooter\",\"CreatedById\":\"005p0000005AvcjAAC\",\"CreatedDate\":\"2022-10-18T13:59:40.000+0000\",\"dmpl__IsDefault__c\":false,\"dmpl__QuantityUnitOfMeasure__c\":\"Each\",\"FuelUsed__c\":\"Electric (BEV)\",\"GrossWeight__c\":275.0,\"Speedin3Sec__c\":\"40 km/hr\",\"dmpl__ProductColor__c\":\"Orange\",\"SKUDescription__c\":\"VIDA V1 PRO SC 8P RMV MAT ABRAX ORANGE\",\"dmpl__SKUCode__c\":\"V1PRASBRCELMAO\",\"dmpl__ItemId__c\":\"a1Gp0000000FaIzEAK\",\"Id\":\"a1mp0000000EsOTAA0\"}]}}]}");
			productMasterConfigServiceImpl.processProductMasterJson();
		});
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.service.impl.ProductMasterConfigServiceImpl#getProductData(java.lang.String)}.
	 */
	@Test
	void testGetProductData() {
		Assertions.assertTimeoutPreemptively(Duration.ofSeconds(2), () -> {
			Mockito.mockStatic(EntityUtils.class);
			Mockito.mockStatic(HttpClients.class);
			Mockito.mockStatic(CommonUtils.class);
			testProcessProductMasterJson();
			Mockito.when(EntityUtils.toString(entity)).thenReturn(
					"{\"access_token\":\"00D5h000004zDWU!AQEAQACaYxWAqNHE4L35go7ZSeD23TxdoE73_rLFrW8yAHaBdtvcUb7jB3312.ow2qPas6Ud9.4VwrpEz5Ybee1of8mQhqTv\",\"instance_url\":\"https://vidaworld.my.salesforce.com\",\"id\":\"https://login.salesforce.com/id/00D5h000004zDWUEA2/0055h000006CcsGAAS\",\"token_type\":\"Bearer\",\"issued_at\":\"1690363697815\",\"signature\":\"ufQFGoXyWmL2iwYS3OU9h0VmYR4vKsVD0UJ5P5LrdHo=\"}");
			Mockito.when(globalConfig.sfdcQueryEndpoint()).thenReturn("sfdcQueryEndpoint");
			Mockito.when(HttpClients.createDefault()).thenReturn(httpClient1);
			Mockito.when(httpClient1.execute(Mockito.any(HttpGet.class))).thenReturn(null);
			Mockito.when(CommonUtils.getResourceResolver(resolverFactory, Constants.WRITERSERVICEUSER))
					.thenReturn(resolver);
			Mockito.when(globalConfig.productListUrl()).thenReturn("productListUrl");
			Mockito.when(resolver.adaptTo(Session.class)).thenReturn(session);
			productMasterConfigServiceImpl.getProductData(
					"00D5h000004zDWU!AQEAQACaYxWAqNHE4L35go7ZSeD23TxdoE73_rLFrW8yAHaBdtvcUb7jB3312.ow2qPas6Ud9.4VwrpEz5Ybee1of8mQhqTv");
		});
	}

}
