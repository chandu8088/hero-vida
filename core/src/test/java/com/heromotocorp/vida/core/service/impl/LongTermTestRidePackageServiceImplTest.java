/**
 * 
 */
package com.heromotocorp.vida.core.service.impl;

import static org.mockito.Mockito.mock;

import java.io.IOException;
import java.time.Duration;

import javax.jcr.RepositoryException;
import javax.jcr.UnsupportedRepositoryOperationException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.day.cq.dam.api.Rendition;
import com.day.cq.replication.Replicator;
import com.heromotocorp.vida.core.config.ClientConfig;
import com.heromotocorp.vida.core.config.GlobalConfig;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import junitx.util.PrivateAccessor;

/**
 * JUnit test class for LongTermTestRidePackageServiceImpl
 */
@ExtendWith({ AemContextExtension.class, MockitoExtension.class })
class LongTermTestRidePackageServiceImplTest {

	private final AemContext context = new AemContext();

	private LongTermTestRidePackageServiceImpl longTermTestRidePackageServiceImpl = new LongTermTestRidePackageServiceImpl();

	private GlobalConfig globalConfig;

	private ClientConfig clientConfig;

	@Mock
	private Rendition original;

	@Mock
	private ResourceResolver resolver;

	@Mock
	private CloseableHttpResponse httpResponse;

	@Mock
	private CloseableHttpResponse httpResponse1;

	@Mock
	private HttpEntity entity;

	@Mock
	private HttpEntity entity1;

	@Mock
	private CloseableHttpClient httpClient;

	@Mock
	private CloseableHttpClient httpClient1;

	private Replicator replicator;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		globalConfig = context.getService(GlobalConfig.class);
		globalConfig = mock(GlobalConfig.class);
		PrivateAccessor.setField(longTermTestRidePackageServiceImpl, "globalConfig", globalConfig);

		clientConfig = context.getService(ClientConfig.class);
		clientConfig = mock(ClientConfig.class);
		PrivateAccessor.setField(longTermTestRidePackageServiceImpl, "clientConfig", clientConfig);

		replicator = context.getService(Replicator.class);
		replicator = mock(Replicator.class);
		PrivateAccessor.setField(longTermTestRidePackageServiceImpl, "replicator", replicator);
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.service.impl.LongTermTestRidePackageServiceImpl#getPackageDataJson()}.
	 * 
	 * @throws IOException
	 * @throws ClientProtocolException
	 * @throws LoginException
	 * @throws RepositoryException
	 * @throws UnsupportedRepositoryOperationException
	 */
	@Test
	void testGetPackageDataJson() throws ClientProtocolException, IOException, LoginException,
			UnsupportedRepositoryOperationException, RepositoryException {
		Assertions.assertTimeoutPreemptively(Duration.ofSeconds(2), () -> {
			Mockito.mockStatic(EntityUtils.class);
			Mockito.mockStatic(HttpClients.class);
			Mockito.when(globalConfig.postRequestToken()).thenReturn("token");
			Mockito.when(globalConfig.grantType()).thenReturn("grantType");
			Mockito.when(globalConfig.clientId()).thenReturn("clientId");
			Mockito.when(globalConfig.clientSecret()).thenReturn("clientSecret");
			Mockito.when(globalConfig.username()).thenReturn("username");
			Mockito.when(globalConfig.password()).thenReturn("password");
			Mockito.when(globalConfig.contentType()).thenReturn("contentType");
			Mockito.when(clientConfig.client()).thenReturn(httpClient);
			Mockito.when(httpClient.execute(Mockito.any(HttpPost.class))).thenReturn(httpResponse);
			Mockito.when(httpResponse.getEntity()).thenReturn(entity);
			Mockito.when(EntityUtils.toString(entity)).thenReturn(
					"{\"access_token\":\"00D5h000004zDWU!AQEAQACaYxWAqNHE4L35go7ZSeD23TxdoE73_rLFrW8yAHaBdtvcUb7jB3312.ow2qPas6Ud9.4VwrpEz5Ybee1of8mQhqTv\",\"instance_url\":\"https://vidaworld.my.salesforce.com\",\"id\":\"https://login.salesforce.com/id/00D5h000004zDWUEA2/0055h000006CcsGAAS\",\"token_type\":\"Bearer\",\"issued_at\":\"1690363697815\",\"signature\":\"ufQFGoXyWmL2iwYS3OU9h0VmYR4vKsVD0UJ5P5LrdHo=\"}");
			Mockito.when(HttpClients.createDefault()).thenReturn(httpClient1);
			Mockito.when(httpClient1.execute(Mockito.any(HttpGet.class))).thenReturn(httpResponse1);
			Mockito.when(httpResponse1.getEntity()).thenReturn(entity1);
			Mockito.when(globalConfig.sfdcQueryEndpoint()).thenReturn("sfdcQueryEndpoint");
			Mockito.when(EntityUtils.toString(entity1)).thenReturn("{\r\n" + "	\"records\": [{\r\n"
					+ "		\"Rental_Package_Prices__r\": {\r\n" + "			\"type\": \"dmpl__PostalCode__c\",\r\n"
					+ "			\"url\": \"/services/data/v52.0/sobjects/dmpl__PostalCode__c/a1Lp000000AeYheEAF\",\r\n"
					+ "			\"records\": [{\r\n" + "				\"City_Id__c\": 4,\r\n"
					+ "				\"Price__c\": 560006,\r\n" + "				\"Package_days__c\": 23082023,\r\n"
					+ "				\"Package_Id__c\": \"a1Lp000000AeYheEAF\",\r\n"
					+ "				\"city-price\": \"NA\",\r\n" + "				\"City__c\": \"BENGALURU\",\r\n"
					+ "				\"city-price\" :[{\r\n" + "					\"City_Id__c\": 4,\r\n"
					+ "					\"Price__c\": 560006,\r\n"
					+ "					\"Package_days__c\": 23082023,\r\n"
					+ "					\"Package_Id__c\": \"a1Lp000000AeYheEAF\",\r\n"
					+ "					\"city-price\": \"NA\",\r\n"
					+ "					\"City__c\": \"BENGALURU\"\r\n" + "				}]\r\n" + "			}, {\r\n"
					+ "				\"City_Id__c\": 4,\r\n" + "				\"Price__c\": 560006,\r\n"
					+ "				\"Package_days__c\": 23082023,\r\n"
					+ "				\"Package_Id__c\": \"a1Lp000000AeYheEAF\",\r\n"
					+ "				\"Tehsil__c\": null,\r\n" + "				\"city-price\": \"NA\",\r\n"
					+ "				\"City__c\": \"BENGALURU\",\r\n" + "				\"city-price\" :[{\r\n"
					+ "					\"City_Id__c\": 4,\r\n" + "					\"Price__c\": 560006,\r\n"
					+ "					\"Package_days__c\": 23082023,\r\n"
					+ "					\"Package_Id__c\": \"a1Lp000000AeYheEAF\",\r\n"
					+ "					\"city-price\": \"NA\",\r\n"
					+ "					\"City__c\": \"BENGALURU\"\r\n" + "				}]\r\n" + "			}, {\r\n"
					+ "				\"City_Id__c\": 5,\r\n" + "				\"Price__c\": 560006,\r\n"
					+ "				\"Package_Id__c\": \"a1Lp000000AeYheEAF\",\r\n"
					+ "				\"Package_days__c\": 23082023,\r\n" + "				\"Tehsil__c\": null,\r\n"
					+ "				\"city-price\": \"NA\",\r\n" + "				\"City__c\": \"BENGALURU\"\r\n"
					+ "			}]\r\n" + "		},\r\n" + "		\"Package_Id__c\": \"a1Lp000000AeYheEAF\",\r\n"
					+ "		\"Name\": \"560006\",\r\n" + "		\"Package_days__c\": 23082023\r\n" + "	}]\r\n" + "}");
			longTermTestRidePackageServiceImpl.getPackageDataJson();
		});
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.service.impl.LongTermTestRidePackageServiceImpl#processPackageData(java.lang.String)}.
	 * 
	 * @throws LoginException
	 * @throws IOException
	 * @throws ClientProtocolException
	 * @throws RepositoryException
	 * @throws UnsupportedRepositoryOperationException
	 */
	@Test
	void testProcessPackageData() throws LoginException, ClientProtocolException, IOException,
			UnsupportedRepositoryOperationException, RepositoryException {
		Assertions.assertTimeoutPreemptively(Duration.ofSeconds(2), () -> {
			Mockito.mockStatic(EntityUtils.class);
			Mockito.mockStatic(HttpClients.class);
			Mockito.when(globalConfig.postRequestToken()).thenReturn("token");
			Mockito.when(globalConfig.grantType()).thenReturn("grantType");
			Mockito.when(globalConfig.clientId()).thenReturn("clientId");
			Mockito.when(globalConfig.clientSecret()).thenReturn("clientSecret");
			Mockito.when(globalConfig.username()).thenReturn("username");
			Mockito.when(globalConfig.password()).thenReturn("password");
			Mockito.when(globalConfig.contentType()).thenReturn("contentType");
			Mockito.when(clientConfig.client()).thenReturn(httpClient);
			Mockito.when(httpClient.execute(Mockito.any(HttpPost.class))).thenReturn(httpResponse);
			Mockito.when(httpResponse.getEntity()).thenReturn(entity);
			Mockito.when(EntityUtils.toString(entity)).thenReturn(
					"{\"access_token\":\"00D5h000004zDWU!AQEAQACaYxWAqNHE4L35go7ZSeD23TxdoE73_rLFrW8yAHaBdtvcUb7jB3312.ow2qPas6Ud9.4VwrpEz5Ybee1of8mQhqTv\",\"instance_url\":\"https://vidaworld.my.salesforce.com\",\"id\":\"https://login.salesforce.com/id/00D5h000004zDWUEA2/0055h000006CcsGAAS\",\"token_type\":\"Bearer\",\"issued_at\":\"1690363697815\",\"signature\":\"ufQFGoXyWmL2iwYS3OU9h0VmYR4vKsVD0UJ5P5LrdHo=\"}");
			Mockito.when(HttpClients.createDefault()).thenReturn(httpClient1);
			Mockito.when(httpClient1.execute(Mockito.any(HttpGet.class))).thenReturn(httpResponse1);
			Mockito.when(httpResponse1.getEntity()).thenReturn(entity1);
			Mockito.when(globalConfig.sfdcQueryEndpoint()).thenReturn("sfdcQueryEndpoint");
			Mockito.when(EntityUtils.toString(entity1)).thenReturn("{\r\n" + "	\"records\": [{\r\n"
					+ "		\"Rental_Package_Prices__r\": {\r\n" + "			\"type\": \"dmpl__PostalCode__c\",\r\n"
					+ "			\"url\": \"/services/data/v52.0/sobjects/dmpl__PostalCode__c/a1Lp000000AeYheEAF\",\r\n"
					+ "			\"records\": [{\r\n" + "				\"City_Id__c\": 4,\r\n"
					+ "				\"Price__c\": 560006,\r\n" + "				\"Package_days__c\": 23082023,\r\n"
					+ "				\"Package_Id__c\": \"a1Lp000000AeYheEAF\",\r\n"
					+ "				\"city-price\": \"NA\",\r\n" + "				\"City__c\": \"BENGALURU\",\r\n"
					+ "				\"city-price\" :[{\r\n" + "					\"City_Id__c\": 4,\r\n"
					+ "					\"Price__c\": 560006,\r\n"
					+ "					\"Package_days__c\": 23082023,\r\n"
					+ "					\"Package_Id__c\": \"a1Lp000000AeYheEAF\",\r\n"
					+ "					\"city-price\": \"NA\",\r\n"
					+ "					\"City__c\": \"BENGALURU\"\r\n" + "				}]\r\n" + "			}, {\r\n"
					+ "				\"City_Id__c\": 4,\r\n" + "				\"Price__c\": 560006,\r\n"
					+ "				\"Package_days__c\": 23082023,\r\n"
					+ "				\"Package_Id__c\": \"a1Lp000000AeYheEAF\",\r\n"
					+ "				\"Tehsil__c\": null,\r\n" + "				\"city-price\": \"NA\",\r\n"
					+ "				\"City__c\": \"BENGALURU\",\r\n" + "				\"city-price\" :[{\r\n"
					+ "					\"City_Id__c\": 4,\r\n" + "					\"Price__c\": 560006,\r\n"
					+ "					\"Package_days__c\": 23082023,\r\n"
					+ "					\"Package_Id__c\": \"a1Lp000000AeYheEAF\",\r\n"
					+ "					\"city-price\": \"NA\",\r\n"
					+ "					\"City__c\": \"BENGALURU\"\r\n" + "				}]\r\n" + "			}, {\r\n"
					+ "				\"City_Id__c\": 5,\r\n" + "				\"Price__c\": 560006,\r\n"
					+ "				\"Package_Id__c\": \"a1Lp000000AeYheEAF\",\r\n"
					+ "				\"Package_days__c\": 23082023,\r\n" + "				\"Tehsil__c\": null,\r\n"
					+ "				\"city-price\": \"NA\",\r\n" + "				\"City__c\": \"BENGALURU\"\r\n"
					+ "			}]\r\n" + "		},\r\n" + "		\"Package_Id__c\": \"a1Lp000000AeYheEAF\",\r\n"
					+ "		\"Name\": \"560006\",\r\n" + "		\"Package_days__c\": 23082023\r\n" + "	}]\r\n" + "}");
			longTermTestRidePackageServiceImpl.getPackageDataJson();
			longTermTestRidePackageServiceImpl.processPackageData(
					"00D5h000004zDWU!AQEAQACaYxWAqNHE4L35go7ZSeD23TxdoE73_rLFrW8yAHaBdtvcUb7jB3312.ow2qPas6Ud9.4VwrpEz5Ybee1of8mQhqTv");
		});
	}

}
