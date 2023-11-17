/**
 * 
 */
package com.heromotocorp.vida.core.models;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.time.Duration;

import javax.jcr.Session;
import javax.jcr.ValueFactory;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.sling.api.resource.ResourceResolver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.day.cq.dam.api.AssetManager;
import com.heromotocorp.vida.core.config.ClientConfig;
import com.heromotocorp.vida.core.config.GlobalConfig;

import io.wcm.testing.mock.aem.junit5.AemContextExtension;

/**
 * JUnit test class for MasterGeoModel
 */
@ExtendWith({ AemContextExtension.class, MockitoExtension.class })
class MasterGeoModelTest {

	@InjectMocks
	private MasterGeoModel masterGeoModel;

	@Mock
	private GlobalConfig globalConfig;

	@Mock
	private ClientConfig clientConfig;

	@Mock
	private CloseableHttpResponse response;

	@Mock
	private HttpEntity entity;

	@Mock
	private CloseableHttpClient httpClient;

	@Mock
	private CloseableHttpClient masterHttpClient;

	@Mock
	private HttpEntity entity1;

	@Mock
	private CloseableHttpResponse httpResponse;

	@Mock
	private ResourceResolver resolver;

	@Mock
	private Session session;

	@Mock
	private AssetManager assetManager;

	@Mock
	private ValueFactory factory;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		Mockito.when(globalConfig.grantType()).thenReturn("grantType");
		Mockito.when(globalConfig.clientId()).thenReturn("clientId");
		Mockito.when(globalConfig.clientSecret()).thenReturn("clientSecret");
		Mockito.when(globalConfig.username()).thenReturn("username");
		Mockito.when(globalConfig.password()).thenReturn("password");
		Mockito.when(globalConfig.contentType()).thenReturn("contentType");
		Mockito.when(globalConfig.locationDataUrl()).thenReturn("locationDataUrl");
		Mockito.when(globalConfig.country()).thenReturn("INDIA");
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.MasterGeoModel#init()}.
	 */
	@Test
	void testInit() {
		masterGeoModel.init();
		assertNotNull(masterGeoModel);
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.MasterGeoModel#iterateCountries()}.
	 * 
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	@Test
	void testIterateCountries() throws Exception {
		Assertions.assertTimeoutPreemptively(Duration.ofSeconds(2), () -> {
			masterGeoModel.init();
			Mockito.mockStatic(EntityUtils.class);
			Mockito.mockStatic(HttpClients.class);
			Mockito.when(globalConfig.postRequestToken()).thenReturn("postRequestToken");
			Mockito.when(clientConfig.client()).thenReturn(httpClient);
			Mockito.when(httpClient.execute(Mockito.any(HttpPost.class))).thenReturn(response);
			Mockito.when(response.getEntity()).thenReturn(entity);
			Mockito.when(EntityUtils.toString(entity)).thenReturn(
					"{\"access_token\":\"00D5h000004zDWU!AQEAQACaYxWAqNHE4L35go7ZSeD23TxdoE73_rLFrW8yAHaBdtvcUb7jB3312.ow2qPas6Ud9.4VwrpEz5Ybee1of8mQhqTv\",\"instance_url\":\"https://vidaworld.my.salesforce.com\",\"id\":\"https://login.salesforce.com/id/00D5h000004zDWUEA2/0055h000006CcsGAAS\",\"token_type\":\"Bearer\",\"issued_at\":\"1690363697815\",\"signature\":\"ufQFGoXyWmL2iwYS3OU9h0VmYR4vKsVD0UJ5P5LrdHo=\"}");
			Mockito.when(HttpClients.createDefault()).thenReturn(masterHttpClient);
			Mockito.when(masterHttpClient.execute(Mockito.any(HttpGet.class))).thenReturn(httpResponse);
			Mockito.when(httpResponse.getEntity()).thenReturn(entity1);
			Mockito.when(EntityUtils.toString(entity1)).thenReturn("{\r\n" + "\"totalSize\": 16,\r\n"
					+ "\"done\": true,\r\n" + "\"records\": [{\r\n" + "\"attributes\": {\r\n"
					+ "\"type\": \"Account\",\r\n"
					+ "\"url\": \"/services/data/v53.0/sobjects/Account/001p00000102MzHAAU\"\r\n" + "},\r\n"
					+ "\"AccountNumber\": null,\r\n" + "\"AccountSource\": null,\r\n"
					+ "\"AccountStatus__c\": false,\r\n" + "\"ActiveFlag__c\": false,\r\n" + "\"Age__c\": null,\r\n"
					+ "\"AnnualIncome__c\": null,\r\n" + "\"AnnualRevenue\": null,\r\n" + "\"BillingAddress\": {\r\n"
					+ "\"city\": \"New Delhi\",\r\n" + "\"country\": \"India\",\r\n" + "\"geocodeAccuracy\": null,\r\n"
					+ "\"latitude\": null,\r\n" + "\"longitude\": null,\r\n" + "\"postalCode\": \"110029\",\r\n"
					+ "\"state\": \"Delhi\",\r\n" + "\"street\": \"No B1/4 Block A2, Safdarjung Enclave\"\r\n"
					+ "},\r\n" + "\"BillingCity\": \"New Delhi\",\r\n" + "\"BillingCountry\": \"India\",\r\n"
					+ "\"BillingDistrict__c\": null,\r\n" + "\"BillingGeocodeAccuracy\": null,\r\n"
					+ "\"BillingLatitude\": null,\r\n" + "\"BillingLongitude\": null,\r\n"
					+ "\"BillingPostalCode\": \"110029\",\r\n" + "\"BillingState\": \"Delhi\",\r\n"
					+ "\"BillingStreet\": \"No B1/4 Block A2, Safdarjung Enclave\",\r\n"
					+ "\"BillingTehsil__c\": null,\r\n" + "\"BloodGroup__c\": null,\r\n"
					+ "\"CreatedById\": \"005p00000052csaAAA\",\r\n"
					+ "\"CreatedDate\": \"2022-06-17T11:56:50.000+0000\",\r\n" + "\"CreditCheckFlag__c\": false,\r\n"
					+ "\"CustomerGroup__c\": null,\r\n" + "\"DateOfBirth__c\": null,\r\n"
					+ "\"DealerCode__c\": \"1010001\",\r\n" + "\"DefalutPartDistributorStockist__c\": null,\r\n"
					+ "\"Description\": null,\r\n" + "\"dmpl__AccountContactId__c\": null,\r\n"
					+ "\"dmpl__AccountSource__c\": \"Direct\",\r\n" + "\"dmpl__BillingCity__c\": \"NEW DELHI\",\r\n"
					+ "\"dmpl__BillingCountry__c\": \"India\",\r\n" + "\"dmpl__BillingPostalCode__c\": \"110029\",\r\n"
					+ "\"dmpl__BillingRegion__c\": null,\r\n" + "\"dmpl__BillingState__c\": \"Delhi\",\r\n"
					+ "\"dmpl__BillingStreet__c\": \"No B1/4 Block A2, Safdarjung Enclave\",\r\n"
					+ "\"dmpl__Email__c\": \"nwdelhi@herodealer.com\",\r\n" + "\"dmpl__ExternalId__c\": null,\r\n"
					+ "\"dmpl__FirstName__c\": \"UAT Delhi\",\r\n" + "\"dmpl__IsActive__c\": true,\r\n"
					+ "\"dmpl__IsCustomerAccount__c\": true,\r\n" + "\"dmpl__IsPartnerAccount__c\": true,\r\n"
					+ "\"dmpl__IsSupplierAccount__c\": false,\r\n" + "\"dmpl__IsTaxExempted__c\": false,\r\n"
					+ "\"dmpl__LastName__c\": \"Dealer\",\r\n" + "\"dmpl__Salutation__c\": null,\r\n"
					+ "\"dmpl__ShippingCity__c\": null,\r\n" + "\"dmpl__ShippingCountry__c\": null,\r\n"
					+ "\"dmpl__ShippingPostalCode__c\": null,\r\n" + "\"dmpl__ShippingRegion__c\": null,\r\n"
					+ "\"dmpl__ShippingState__c\": null,\r\n" + "\"dmpl__ShippingStreet__c\": null,\r\n"
					+ "\"dmpl__Status__c\": \"Created\",\r\n" + "\"EducationalQualification__c\": null,\r\n"
					+ "\"FamilyMembersCount__c\": null,\r\n" + "\"Fax\": null,\r\n" + "\"Gender__c\": null,\r\n"
					+ "\"GSTIN__c\": null,\r\n" + "\"GSTPlaceOfSupply__c\": null,\r\n"
					+ "\"GSTRegistrationType__c\": null,\r\n" + "\"Id\": \"001p00000102MzHAAU\",\r\n"
					+ "\"Industry\": null,\r\n" + "\"IsDeleted\": false,\r\n"
					+ "\"IseInvoiceapplicable__c\": false,\r\n" + "\"IsIndividual__c\": false,\r\n"
					+ "\"Jigsaw\": null,\r\n" + "\"JigsawCompanyId\": null,\r\n" + "\"LastActivityDate\": null,\r\n"
					+ "\"LastModifiedById\": \"005p00000052cspAAA\",\r\n"
					+ "\"LastModifiedDate\": \"2023-07-25T11:48:39.000+0000\",\r\n"
					+ "\"LastReferencedDate\": \"2023-07-10T09:24:01.000+0000\",\r\n"
					+ "\"LastViewedDate\": \"2023-07-10T09:24:01.000+0000\",\r\n" + "\"LegalStatus__c\": null,\r\n"
					+ "\"MasterRecordId\": null,\r\n" + "\"Name\": \"UAT Delhi Dealer\",\r\n"
					+ "\"NumberOfEmployees\": null,\r\n" + "\"Occupation__c\": null,\r\n"
					+ "\"OrganizationInstitution__c\": false,\r\n" + "\"OwnerId\": \"005p00000052csaAAA\",\r\n"
					+ "\"Ownership\": null,\r\n" + "\"PAN__c\": null,\r\n" + "\"ParentId\": null,\r\n"
					+ "\"PartnerEndDate__c\": null,\r\n" + "\"PartnerFunctionType__c\": null,\r\n"
					+ "\"Phone\": \"8989898989\",\r\n"
					+ "\"PhotoUrl\": \"/services/images/photo/001p00000102MzHAAU\",\r\n"
					+ "\"ProfileImageURL__c\": null,\r\n" + "\"ShippingAddress\": null,\r\n"
					+ "\"ShippingCity\": null,\r\n" + "\"ShippingCountry\": null,\r\n"
					+ "\"ShippingDistrict__c\": null,\r\n" + "\"ShippingGeocodeAccuracy\": null,\r\n"
					+ "\"ShippingLatitude\": null,\r\n" + "\"ShippingLongitude\": null,\r\n"
					+ "\"ShippingPostalCode\": null,\r\n" + "\"ShippingState\": null,\r\n"
					+ "\"ShippingStreet\": null,\r\n" + "\"ShippingTehsil__c\": null,\r\n" + "\"SicDesc\": null,\r\n"
					+ "\"Site\": null,\r\n" + "\"SystemModstamp\": \"2023-07-25T11:48:39.000+0000\",\r\n"
					+ "\"Type\": \"Partner\",\r\n" + "\"Type__c\": null,\r\n" + "\"TypeOfBlock__c\": null,\r\n"
					+ "\"Website\": null,\r\n" + "\"dmpl__Branches__r\": {\r\n" + "\"totalSize\": 4,\r\n"
					+ "\"done\": true,\r\n" + "\"records\": [{\r\n" + "	\"attributes\": {\r\n"
					+ "		\"type\": \"dmpl__Branch__c\",\r\n"
					+ "		\"url\": \"/services/data/v53.0/sobjects/dmpl__Branch__c/a0Ip0000003gqdWEAQ\"\r\n"
					+ "	},\r\n" + "	\"CreatedById\": \"005p00000054FxHAAU\",\r\n"
					+ "	\"CreatedDate\": \"2022-11-08T09:04:12.000+0000\",\r\n" + "	\"dmpl__AddressId__c\": null,\r\n"
					+ "	\"dmpl__AllowInventory__c\": true,\r\n" + "	\"dmpl__AllowPurchase__c\": true,\r\n"
					+ "	\"dmpl__AllowSales__c\": true,\r\n" + "	\"dmpl__AllowService__c\": false,\r\n"
					+ "	\"dmpl__BranchType__c\": \"Warehouse Branch\",\r\n" + "	\"dmpl__BusinessHoursId__c\": null,\r\n"
					+ "	\"dmpl__Email__c\": null,\r\n" + "	\"dmpl__ExternalId__c\": null,\r\n"
					+ "	\"dmpl__GeoLocation__c\": null,\r\n" + "	\"dmpl__GeoLocation__Latitude__s\": null,\r\n"
					+ "	\"dmpl__GeoLocation__Longitude__s\": null,\r\n"
					+ "	\"dmpl__InventoryValidationMethod__c\": null,\r\n"
					+ "	\"dmpl__InventoryValuationMethod__c\": null,\r\n" + "	\"dmpl__IsActive__c\": true,\r\n"
					+ "	\"dmpl__IsHomeDeliveryAvailable__c\": false,\r\n"
					+ "	\"dmpl__IsPickupDropAvailable__c\": false,\r\n" + "	\"dmpl__MarketingName__c\": null,\r\n"
					+ "	\"dmpl__MobileNumber__c\": 8.527060901E9,\r\n" + "	\"dmpl__Name__c\": null,\r\n"
					+ "	\"dmpl__ParentLocationId__c\": null,\r\n"
					+ "	\"dmpl__PartnerAccountId__c\": \"001p00000102MzHAAU\",\r\n"
					+ "	\"dmpl__Phone__c\": \"8,527,060,901\",\r\n" + "	\"dmpl__SalesEmail__c\": null,\r\n"
					+ "	\"dmpl__SalesPhone__c\": null,\r\n" + "	\"dmpl__ServiceEmail__c\": null,\r\n"
					+ "	\"dmpl__ServicePhone__c\": null,\r\n" + "	\"dmpl__Website__c\": null,\r\n"
					+ "	\"Id\": \"a0Ip0000003gqdWEAQ\",\r\n" + "	\"IsDeleted\": false,\r\n"
					+ "	\"LastModifiedById\": \"005p0000005ABxrAAG\",\r\n"
					+ "	\"LastModifiedDate\": \"2022-12-13T10:42:00.000+0000\",\r\n"
					+ "	\"LastReferencedDate\": null,\r\n" + "	\"LastViewedDate\": null,\r\n"
					+ "	\"Name\": \"UAT Delhi Warehouse Branch\",\r\n" + "	\"OwnerId\": \"005p00000054FxHAAU\",\r\n"
					+ "	\"Owner\": {\r\n" + "		\"attributes\": {\r\n" + "			\"type\": \"Name\",\r\n"
					+ "			\"url\": \"/services/data/v53.0/sobjects/User/005p00000054FxHAAU\"\r\n" + "		},\r\n"
					+ "		\"Type\": \"User\",\r\n" + "		\"Name\": \"RamKrishna Hegade\",\r\n"
					+ "		\"FirstName\": \"RamKrishna\",\r\n" + "		\"LastName\": \"Hegade\"\r\n" + "	},\r\n"
					+ "	\"SystemModstamp\": \"2022-12-13T10:42:00.000+0000\",\r\n"
					+ "	\"dmpl__AddressId__r\": null\r\n" + "}, {\r\n" + "	\"attributes\": {\r\n"
					+ "		\"type\": \"dmpl__Branch__c\",\r\n"
					+ "		\"url\": \"/services/data/v53.0/sobjects/dmpl__Branch__c/a0Ip0000003gqc4EAA\"\r\n"
					+ "	},\r\n" + "	\"CreatedById\": \"005p00000054FxHAAU\",\r\n"
					+ "	\"CreatedDate\": \"2022-11-08T06:30:12.000+0000\",\r\n" + "	\"dmpl__AddressId__c\": null,\r\n"
					+ "	\"dmpl__AllowInventory__c\": false,\r\n" + "	\"dmpl__AllowPurchase__c\": false,\r\n"
					+ "	\"dmpl__AllowSales__c\": false,\r\n" + "	\"dmpl__AllowService__c\": false,\r\n"
					+ "	\"dmpl__BranchType__c\": \"Warehouse Branch\",\r\n" + "	\"dmpl__BusinessHoursId__c\": null,\r\n"
					+ "	\"dmpl__Email__c\": null,\r\n" + "	\"dmpl__ExternalId__c\": null,\r\n"
					+ "	\"dmpl__GeoLocation__c\": null,\r\n" + "	\"dmpl__GeoLocation__Latitude__s\": null,\r\n"
					+ "	\"dmpl__GeoLocation__Longitude__s\": null,\r\n"
					+ "	\"dmpl__InventoryValidationMethod__c\": null,\r\n"
					+ "	\"dmpl__InventoryValuationMethod__c\": null,\r\n" + "	\"dmpl__IsActive__c\": false,\r\n"
					+ "	\"dmpl__IsHomeDeliveryAvailable__c\": false,\r\n"
					+ "	\"dmpl__IsPickupDropAvailable__c\": false,\r\n"
					+ "	\"dmpl__MarketingName__c\": \"UAT Delhi Warehouse Center\",\r\n"
					+ "	\"dmpl__MobileNumber__c\": null,\r\n" + "	\"dmpl__Name__c\": null,\r\n"
					+ "	\"dmpl__ParentLocationId__c\": null,\r\n"
					+ "	\"dmpl__PartnerAccountId__c\": \"001p00000102MzHAAU\",\r\n" + "	\"dmpl__Phone__c\": null,\r\n"
					+ "	\"dmpl__SalesEmail__c\": null,\r\n" + "	\"dmpl__SalesPhone__c\": null,\r\n"
					+ "	\"dmpl__ServiceEmail__c\": null,\r\n" + "	\"dmpl__ServicePhone__c\": null,\r\n"
					+ "	\"dmpl__Website__c\": null,\r\n" + "	\"Id\": \"a0Ip0000003gqc4EAA\",\r\n"
					+ "	\"IsDeleted\": false,\r\n" + "	\"LastModifiedById\": \"005p00000054FxHAAU\",\r\n"
					+ "	\"LastModifiedDate\": \"2022-11-08T06:30:12.000+0000\",\r\n"
					+ "	\"LastReferencedDate\": null,\r\n" + "	\"LastViewedDate\": null,\r\n"
					+ "	\"Name\": \"UAT Delhi Warehouse Center\",\r\n" + "	\"OwnerId\": \"005p00000054FxHAAU\",\r\n"
					+ "	\"Owner\": {\r\n" + "		\"attributes\": {\r\n" + "			\"type\": \"Name\",\r\n"
					+ "			\"url\": \"/services/data/v53.0/sobjects/User/005p00000054FxHAAU\"\r\n" + "		},\r\n"
					+ "		\"Type\": \"User\",\r\n" + "		\"Name\": \"RamKrishna Hegade\",\r\n"
					+ "		\"FirstName\": \"RamKrishna\",\r\n" + "		\"LastName\": \"Hegade\"\r\n" + "	},\r\n"
					+ "	\"SystemModstamp\": \"2022-11-08T06:30:12.000+0000\",\r\n" + "	\"dmpl__AddressId__r\": {\r\n"
					+ "		\"attributes\": {\r\n" + "			\"type\": \"dmpl__ContactAddress__c\",\r\n"
					+ "			\"url\": \"/services/data/v53.0/sobjects/dmpl__ContactAddress__c/a0Zp0000005vbiQEAQ\"\r\n"
					+ "		}\r\n" + "	}\r\n" + "}, {\r\n" + "	\"attributes\": {\r\n"
					+ "		\"type\": \"dmpl__Branch__c\",\r\n"
					+ "		\"url\": \"/services/data/v53.0/sobjects/dmpl__Branch__c/a0Ip0000003g2miEAA\"\r\n"
					+ "	},\r\n" + "	\"CreatedById\": \"005p00000051RdRAAU\",\r\n"
					+ "	\"CreatedDate\": \"2022-06-15T11:02:22.000+0000\",\r\n" + "	\"dmpl__AddressId__c\": null,\r\n"
					+ "	\"dmpl__AllowInventory__c\": true,\r\n" + "	\"dmpl__AllowPurchase__c\": true,\r\n"
					+ "	\"dmpl__AllowSales__c\": true,\r\n" + "	\"dmpl__AllowService__c\": true,\r\n"
					+ "	\"dmpl__BranchType__c\": \"Experience Centre\",\r\n"
					+ "	\"dmpl__BusinessHoursId__c\": \"01m5h00000090P9AAI\",\r\n"
					+ "	\"dmpl__Email__c\": \"karolbaghbranch@gmail.com\",\r\n" + "	\"dmpl__ExternalId__c\": null,\r\n"
					+ "	\"dmpl__GeoLocation__c\": null,\r\n" + "	\"dmpl__GeoLocation__Latitude__s\": null,\r\n"
					+ "	\"dmpl__GeoLocation__Longitude__s\": null,\r\n"
					+ "	\"dmpl__InventoryValidationMethod__c\": \"FIFO\",\r\n"
					+ "	\"dmpl__InventoryValuationMethod__c\": null,\r\n" + "	\"dmpl__IsActive__c\": true,\r\n"
					+ "	\"dmpl__IsHomeDeliveryAvailable__c\": true,\r\n"
					+ "	\"dmpl__IsPickupDropAvailable__c\": false,\r\n" + "	\"dmpl__MarketingName__c\": null,\r\n"
					+ "	\"dmpl__MobileNumber__c\": 9.999121244E9,\r\n" + "	\"dmpl__Name__c\": null,\r\n"
					+ "	\"dmpl__ParentLocationId__c\": null,\r\n"
					+ "	\"dmpl__PartnerAccountId__c\": \"001p00000102MzHAAU\",\r\n" + "	\"dmpl__Phone__c\": null,\r\n"
					+ "	\"dmpl__SalesEmail__c\": null,\r\n" + "	\"dmpl__SalesPhone__c\": null,\r\n"
					+ "	\"dmpl__ServiceEmail__c\": null,\r\n" + "	\"dmpl__ServicePhone__c\": null,\r\n"
					+ "	\"dmpl__Website__c\": \"karolbaghbranch.com\",\r\n" + "	\"Id\": \"a0Ip0000003g2miEAA\",\r\n"
					+ "	\"IsDeleted\": false,\r\n" + "	\"LastModifiedById\": \"005p00000054FxHAAU\",\r\n"
					+ "	\"LastModifiedDate\": \"2023-02-23T11:08:58.000+0000\",\r\n"
					+ "	\"LastReferencedDate\": null,\r\n" + "	\"LastViewedDate\": null,\r\n"
					+ "	\"Name\": \"Karol Bagh\",\r\n" + "	\"OwnerId\": \"005p00000051RdRAAU\",\r\n"
					+ "	\"Owner\": {\r\n" + "		\"attributes\": {\r\n" + "			\"type\": \"Name\",\r\n"
					+ "			\"url\": \"/services/data/v53.0/sobjects/User/005p00000051RdRAAU\"\r\n" + "		},\r\n"
					+ "		\"Type\": \"User\",\r\n" + "		\"Name\": \"Swati Jain\",\r\n"
					+ "		\"FirstName\": \"Swati\",\r\n" + "		\"LastName\": \"Jain\"\r\n" + "	},\r\n"
					+ "	\"SystemModstamp\": \"2023-02-23T11:08:58.000+0000\",\r\n" + "	\"dmpl__AddressId__r\": {\r\n"
					+ "		\"attributes\": {\r\n" + "			\"type\": \"dmpl__ContactAddress__c\",\r\n"
					+ "			\"url\": \"/services/data/v53.0/sobjects/dmpl__ContactAddress__c/a0Zp0000005vbiQEAQ\"\r\n"
					+ "		},\r\n" + "		\"dmpl__City__c\": \"\",\r\n" + "		\"dmpl__State__c\": \"\",\r\n"
					+ "		\"dmpl__Country__c\": \"\"\r\n" + "	}\r\n" + "}, {\r\n" + "	\"attributes\": {\r\n"
					+ "		\"type\": \"dmpl__Branch__c\",\r\n"
					+ "		\"url\": \"/services/data/v53.0/sobjects/dmpl__Branch__c/a0Ip0000003g2lfEAA\"\r\n"
					+ "	},\r\n" + "	\"CreatedById\": \"005p00000051RdRAAU\",\r\n"
					+ "	\"CreatedDate\": \"2022-06-15T09:35:05.000+0000\",\r\n"
					+ "	\"dmpl__AddressId__c\": \"a0Zp0000005vbiQEAQ\",\r\n"
					+ "	\"dmpl__AllowInventory__c\": true,\r\n" + "	\"dmpl__AllowPurchase__c\": true,\r\n"
					+ "	\"dmpl__AllowSales__c\": true,\r\n" + "	\"dmpl__AllowService__c\": true,\r\n"
					+ "	\"dmpl__BranchType__c\": \"Experience Centre\",\r\n"
					+ "	\"dmpl__BusinessHoursId__c\": \"01m5h00000090P9AAI\",\r\n"
					+ "	\"dmpl__Email__c\": \"branch1@gmail.com\",\r\n" + "	\"dmpl__ExternalId__c\": null,\r\n"
					+ "	\"dmpl__GeoLocation__c\": {\r\n" + "		\"latitude\": 28.59214,\r\n"
					+ "		\"longitude\": 77.046051\r\n" + "	},\r\n"
					+ "	\"dmpl__GeoLocation__Latitude__s\": 28.59214,\r\n"
					+ "	\"dmpl__GeoLocation__Longitude__s\": 77.046051,\r\n"
					+ "	\"dmpl__InventoryValidationMethod__c\": \"FIFO\",\r\n"
					+ "	\"dmpl__InventoryValuationMethod__c\": null,\r\n" + "	\"dmpl__IsActive__c\": true,\r\n"
					+ "	\"dmpl__IsHomeDeliveryAvailable__c\": true,\r\n"
					+ "	\"dmpl__IsPickupDropAvailable__c\": false,\r\n" + "	\"dmpl__MarketingName__c\": null,\r\n"
					+ "	\"dmpl__MobileNumber__c\": 9.128893456E9,\r\n" + "	\"dmpl__Name__c\": null,\r\n"
					+ "	\"dmpl__ParentLocationId__c\": null,\r\n"
					+ "	\"dmpl__PartnerAccountId__c\": \"001p00000102MzHAAU\",\r\n" + "	\"dmpl__Phone__c\": null,\r\n"
					+ "	\"dmpl__SalesEmail__c\": null,\r\n" + "	\"dmpl__SalesPhone__c\": null,\r\n"
					+ "	\"dmpl__ServiceEmail__c\": null,\r\n" + "	\"dmpl__ServicePhone__c\": null,\r\n"
					+ "	\"dmpl__Website__c\": null,\r\n" + "	\"Id\": \"a0Ip0000003g2lfEAA\",\r\n"
					+ "	\"IsDeleted\": false,\r\n" + "	\"LastModifiedById\": \"005p00000053HNmAAM\",\r\n"
					+ "	\"LastModifiedDate\": \"2023-07-28T13:02:57.000+0000\",\r\n"
					+ "	\"LastReferencedDate\": \"2023-07-10T09:23:47.000+0000\",\r\n"
					+ "	\"LastViewedDate\": \"2023-07-10T09:23:47.000+0000\",\r\n"
					+ "	\"Name\": \"UAT Delhi Exp Center\",\r\n" + "	\"OwnerId\": \"005p00000051RdRAAU\",\r\n"
					+ "	\"Owner\": {\r\n" + "		\"attributes\": {\r\n" + "			\"type\": \"Name\",\r\n"
					+ "			\"url\": \"/services/data/v53.0/sobjects/User/005p00000051RdRAAU\"\r\n" + "		},\r\n"
					+ "		\"Type\": \"User\",\r\n" + "		\"Name\": \"Swati Jain\",\r\n"
					+ "		\"FirstName\": \"Swati\",\r\n" + "		\"LastName\": \"Jain\"\r\n" + "	},\r\n"
					+ "	\"SystemModstamp\": \"2023-07-28T13:02:57.000+0000\",\r\n" + "	\"dmpl__AddressId__r\": {\r\n"
					+ "		\"attributes\": {\r\n" + "			\"type\": \"dmpl__ContactAddress__c\",\r\n"
					+ "			\"url\": \"/services/data/v53.0/sobjects/dmpl__ContactAddress__c/a0Zp0000005vbiQEAQ\"\r\n"
					+ "		},\r\n" + "		\"dmpl__City__c\": \"NEW DELHI\",\r\n"
					+ "		\"dmpl__State__c\": \"Delhi\",\r\n" + "		\"dmpl__Country__c\": \"INDIA\"\r\n"
					+ "	}\r\n" + "}]\r\n" + "}\r\n" + "}]\r\n" + "}");
			masterGeoModel.iterateCountries();
			assertNotNull(masterGeoModel);
		});
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.MasterGeoModel#getStateCitiesList()}.
	 * 
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	@Test
	void testGetStateCitiesList() throws Exception {
		Assertions.assertTimeoutPreemptively(Duration.ofSeconds(2), () -> {
			masterGeoModel.init();
			Mockito.when(globalConfig.postRequestToken()).thenReturn("postRequestToken");
			Mockito.mockStatic(EntityUtils.class);
			Mockito.mockStatic(HttpClients.class);
			Mockito.when(clientConfig.client()).thenReturn(httpClient);
			Mockito.when(httpClient.execute(Mockito.any(HttpPost.class))).thenReturn(response);
			Mockito.when(response.getEntity()).thenReturn(entity);
			Mockito.when(EntityUtils.toString(entity)).thenReturn(
					"{\"access_token\":\"00D5h000004zDWU!AQEAQACaYxWAqNHE4L35go7ZSeD23TxdoE73_rLFrW8yAHaBdtvcUb7jB3312.ow2qPas6Ud9.4VwrpEz5Ybee1of8mQhqTv\",\"instance_url\":\"https://vidaworld.my.salesforce.com\",\"id\":\"https://login.salesforce.com/id/00D5h000004zDWUEA2/0055h000006CcsGAAS\",\"token_type\":\"Bearer\",\"issued_at\":\"1690363697815\",\"signature\":\"ufQFGoXyWmL2iwYS3OU9h0VmYR4vKsVD0UJ5P5LrdHo=\"}");
			Mockito.when(HttpClients.createDefault()).thenReturn(masterHttpClient);
			Mockito.when(masterHttpClient.execute(Mockito.any(HttpGet.class))).thenReturn(null);
			masterGeoModel.iterateCountries();
			masterGeoModel.getStateCitiesList();
			assertNotNull(masterGeoModel);
		});
	}

}
