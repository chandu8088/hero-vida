/**
 * 
 */
package com.heromotocorp.vida.core.service.impl;

import static org.mockito.Mockito.mock;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.UnsupportedRepositoryOperationException;
import javax.jcr.ValueFactory;

import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.day.cq.dam.api.Asset;
import com.day.cq.dam.api.AssetManager;
import com.day.cq.dam.api.Rendition;
import com.day.cq.replication.Replicator;
import com.heromotocorp.vida.core.config.ClientConfig;
import com.heromotocorp.vida.core.config.GlobalConfig;
import com.heromotocorp.vida.core.utils.Constants;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import junitx.util.PrivateAccessor;

/**
 * JUnit test class for AvailableTestRideCityServiceImpl
 */
@ExtendWith({ AemContextExtension.class, MockitoExtension.class })
class AvailableTestRideCityServiceImplTest {

	private final AemContext context = new AemContext();

	private AvailableTestRideCityServiceImpl availableTestRideCityServiceImpl = new AvailableTestRideCityServiceImpl();

	private GlobalConfig globalConfig;

	private ClientConfig clientConfig;

	@Mock
	private Asset asset;

	@Mock
	private Asset asset1;

	@Mock
	private Rendition original;

	@Mock
	private Rendition original1;

	@Mock
	private AssetManager assetManager;

	@Mock
	private Session session;

	@Mock
	private ValueFactory factory;

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

	@Mock
	private StatusLine statusLine;

	private Replicator replicator;

	private ResourceResolverFactory resolverFactory;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		globalConfig = context.getService(GlobalConfig.class);
		globalConfig = mock(GlobalConfig.class);
		PrivateAccessor.setField(availableTestRideCityServiceImpl, "globalConfig", globalConfig);

		clientConfig = context.getService(ClientConfig.class);
		clientConfig = mock(ClientConfig.class);
		PrivateAccessor.setField(availableTestRideCityServiceImpl, "clientConfig", clientConfig);

		resolverFactory = context.getService(ResourceResolverFactory.class);
		resolverFactory = mock(ResourceResolverFactory.class);
		PrivateAccessor.setField(availableTestRideCityServiceImpl, "resolverFactory", resolverFactory);

		replicator = context.getService(Replicator.class);
		replicator = mock(Replicator.class);
		PrivateAccessor.setField(availableTestRideCityServiceImpl, "replicator", replicator);
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.service.impl.AvailableTestRideCityServiceImpl#getAvailableTestRideCity()}.
	 */
	@Test
	void testGetAvailableTestRideCity() {
		Assertions.assertTimeoutPreemptively(Duration.ofSeconds(2), () -> {
			Mockito.mockStatic(EntityUtils.class);
			Mockito.mockStatic(HttpClients.class);
			Mockito.when(globalConfig.postRequestToken()).thenReturn("postRequestToken");
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
			Mockito.when(EntityUtils.toString(entity1)).thenReturn("{\r\n" + "	\"records\": [{\r\n"
					+ "		\"attributes\": {\r\n" + "			\"type\": \"dmpl__Branch__c\",\r\n"
					+ "			\"url\": \"/services/data/v53.0/sobjects/dmpl__Branch__c/a0K5h00000769ydEAA\"\r\n"
					+ "		},\r\n" + "		\"Id\": \"a0K5h00000769ydEAA\",\r\n"
					+ "		\"TestRideStartDate__c\": \"2023-05-01\",\r\n" + "		\"dmpl__AddressId__r\": {\r\n"
					+ "			\"attributes\": {\r\n" + "				\"type\": \"dmpl__ContactAddress__c\",\r\n"
					+ "				\"url\": \"/services/data/v53.0/sobjects/dmpl__ContactAddress__c/a0c5h000000KAmIAAW\"\r\n"
					+ "			},\r\n" + "			\"dmpl__City__c\": \"NEW DELHI\",\r\n"
					+ "			\"dmpl__State__c\": \"DELHI\"\r\n" + "		}\r\n" + "	}, {\r\n"
					+ "		\"attributes\": {\r\n" + "			\"type\": \"dmpl__Branch__c\",\r\n"
					+ "			\"url\": \"/services/data/v53.0/sobjects/dmpl__Branch__c/a0K5h00000769yiEAA\"\r\n"
					+ "		},\r\n" + "		\"Id\": \"a0K5h00000769yiEAA\",\r\n"
					+ "		\"TestRideStartDate__c\": \"2023-03-12\",\r\n" + "		\"dmpl__AddressId__r\": {\r\n"
					+ "			\"attributes\": {\r\n" + "				\"type\": \"dmpl__ContactAddress__c\",\r\n"
					+ "				\"url\": \"/services/data/v53.0/sobjects/dmpl__ContactAddress__c/a0c5h000000KAmhAAG\"\r\n"
					+ "			},\r\n" + "			\"dmpl__City__c\": \"\",\r\n"
					+ "			\"dmpl__State__c\": \"\"\r\n" + "		}\r\n" + "	}, {\r\n"
					+ "		\"attributes\": {\r\n" + "			\"type\": \"dmpl__Branch__c\",\r\n"
					+ "			\"url\": \"/services/data/v53.0/sobjects/dmpl__Branch__c/a0K5h00000769ymEAA\"\r\n"
					+ "		},\r\n" + "		\"Id\": \"a0K5h00000769ymEAA\",\r\n"
					+ "		\"TestRideStartDate__c\": \"2022-11-14\",\r\n" + "		\"dmpl__AddressId__r\": {\r\n"
					+ "			\"attributes\": {\r\n" + "				\"type\": \"dmpl__ContactAddress__c\",\r\n"
					+ "				\"url\": \"/services/data/v53.0/sobjects/dmpl__ContactAddress__c/a0c5h000000KAmxAAG\"\r\n"
					+ "			},\r\n" + "			\"dmpl__City__c\": null,\r\n"
					+ "			\"dmpl__State__c\": null\r\n" + "		}\r\n" + "	}, {\r\n"
					+ "		\"attributes\": {\r\n" + "			\"type\": \"dmpl__Branch__c\",\r\n"
					+ "			\"url\": \"/services/data/v53.0/sobjects/dmpl__Branch__c/a0K5h00000769yoEAA\"\r\n"
					+ "		},\r\n" + "		\"Id\": \"a0K5h00000769yoEAA\",\r\n"
					+ "		\"TestRideStartDate__c\": \"2023-01-12\",\r\n" + "		\"dmpl__AddressId__r\": {}\r\n"
					+ "	}, {\r\n" + "		\"attributes\": {\r\n" + "			\"type\": \"dmpl__Branch__c\",\r\n"
					+ "			\"url\": \"/services/data/v53.0/sobjects/dmpl__Branch__c/a0K5h00000769ypEAA\"\r\n"
					+ "		},\r\n" + "		\"Id\": \"a0K5h00000769ypEAA\",\r\n"
					+ "		\"TestRideStartDate__c\": \"2023-01-30\",\r\n" + "		\"dmpl__AddressId__r\": null\r\n"
					+ "	}]\r\n" + "}");
			Mockito.when(globalConfig.sfdcQueryEndpoint()).thenReturn("sfdcQueryEndpoint");
			availableTestRideCityServiceImpl.getAvailableTestRideCity();
		});
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.service.impl.AvailableTestRideCityServiceImpl#processPackageData(java.lang.String)}.
	 * 
	 * @throws IOException
	 * @throws ClientProtocolException
	 * @throws LoginException
	 * @throws RepositoryException
	 * @throws UnsupportedRepositoryOperationException
	 */
	@Test
	void testProcessPackageData() throws ClientProtocolException, IOException, LoginException,
			UnsupportedRepositoryOperationException, RepositoryException {
		Assertions.assertTimeoutPreemptively(Duration.ofSeconds(2), () -> {
			Mockito.mockStatic(EntityUtils.class);
			Mockito.mockStatic(HttpClients.class);
			Mockito.when(globalConfig.postRequestToken()).thenReturn("postRequestToken");
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
			Mockito.when(EntityUtils.toString(entity1)).thenReturn("{\r\n" + "	\"records\": [{\r\n"
					+ "		\"attributes\": {\r\n" + "			\"type\": \"dmpl__Branch__c\",\r\n"
					+ "			\"url\": \"/services/data/v53.0/sobjects/dmpl__Branch__c/a0K5h00000769ydEAA\"\r\n"
					+ "		},\r\n" + "		\"Id\": \"a0K5h00000769ydEAA\",\r\n"
					+ "		\"TestRideStartDate__c\": \"2023-05-01\",\r\n" + "		\"dmpl__AddressId__r\": {\r\n"
					+ "			\"attributes\": {\r\n" + "				\"type\": \"dmpl__ContactAddress__c\",\r\n"
					+ "				\"url\": \"/services/data/v53.0/sobjects/dmpl__ContactAddress__c/a0c5h000000KAmIAAW\"\r\n"
					+ "			},\r\n" + "			\"dmpl__City__c\": \"NEW DELHI\",\r\n"
					+ "			\"dmpl__State__c\": \"DELHI\"\r\n" + "		}\r\n" + "	}]\r\n" + "}");
			Mockito.when(globalConfig.sfdcQueryEndpoint()).thenReturn("sfdcQueryEndpoint");
			Mockito.when(globalConfig.availableTestRideCities()).thenReturn("availableTestRideCities");
			Mockito.when(globalConfig.country()).thenReturn("INDIA");
			Map<String, Object> param = new HashMap<>();
			param.put(ResourceResolverFactory.SUBSERVICE, Constants.WRITERSERVICEUSER);
			Mockito.when(resolverFactory.getServiceResourceResolver(param)).thenReturn(resolver);
			Mockito.when(resolver.adaptTo(AssetManager.class)).thenReturn(assetManager);
			Mockito.when(resolver.adaptTo(Session.class)).thenReturn(session);
			Mockito.when(session.getValueFactory()).thenReturn(factory);
			availableTestRideCityServiceImpl.getAvailableTestRideCity();
		});
	}

}
