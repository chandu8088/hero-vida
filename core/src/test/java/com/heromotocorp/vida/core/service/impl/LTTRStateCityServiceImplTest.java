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

import com.day.cq.dam.api.AssetManager;
import com.day.cq.replication.Replicator;
import com.heromotocorp.vida.core.config.ClientConfig;
import com.heromotocorp.vida.core.config.GlobalConfig;
import com.heromotocorp.vida.core.utils.Constants;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import junitx.util.PrivateAccessor;

/**
 * JUnit test class for LTTRStateCityServiceImpl
 */
@ExtendWith({ AemContextExtension.class, MockitoExtension.class })
class LTTRStateCityServiceImplTest {

	private final AemContext context = new AemContext();

	private LTTRStateCityServiceImpl lttrStateCityServiceImpl = new LTTRStateCityServiceImpl();

	private GlobalConfig globalConfig;

	private ClientConfig clientConfig;

	private ResourceResolverFactory resolverFactory;

	private Replicator replicator;

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

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		globalConfig = context.getService(GlobalConfig.class);
		globalConfig = mock(GlobalConfig.class);
		PrivateAccessor.setField(lttrStateCityServiceImpl, "globalConfig", globalConfig);

		clientConfig = context.getService(ClientConfig.class);
		clientConfig = mock(ClientConfig.class);
		PrivateAccessor.setField(lttrStateCityServiceImpl, "clientConfig", clientConfig);

		resolverFactory = context.getService(ResourceResolverFactory.class);
		resolverFactory = mock(ResourceResolverFactory.class);
		PrivateAccessor.setField(lttrStateCityServiceImpl, "resolverFactory", resolverFactory);

		replicator = context.getService(Replicator.class);
		replicator = mock(Replicator.class);
		PrivateAccessor.setField(lttrStateCityServiceImpl, "replicator", replicator);
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.service.impl.LTTRStateCityServiceImpl#getAvailableLTTRStateCity()}.
	 * 
	 * @throws IOException
	 * @throws ClientProtocolException
	 * @throws RepositoryException
	 * @throws UnsupportedRepositoryOperationException
	 * @throws LoginException
	 */
	@Test
	void testGetAvailableLTTRStateCity() throws ClientProtocolException, IOException,
			UnsupportedRepositoryOperationException, RepositoryException, LoginException {
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
			Mockito.when(globalConfig.sfdcQueryEndpoint()).thenReturn("sfdcQueryEndpoint");
			Mockito.when(HttpClients.createDefault()).thenReturn(httpClient1);
			Mockito.when(httpClient1.execute(Mockito.any(HttpGet.class))).thenReturn(httpResponse1);
			Mockito.when(httpResponse1.getEntity()).thenReturn(entity1);
			Mockito.when(EntityUtils.toString(entity1))
					.thenReturn("{\r\n" + "    \"records\": [\r\n" + "        {\r\n"
							+ "            \"attributes\": {\r\n" + "                \"type\": \"AggregateResult\"\r\n"
							+ "            },\r\n" + "            \"dmpl__City__c\": \"BENGALURU\",\r\n"
							+ "            \"dmpl__State__c\": \"Karnataka\"\r\n" + "        },\r\n" + "        {\r\n"
							+ "            \"attributes\": {\r\n" + "                \"type\": \"AggregateResult\"\r\n"
							+ "            },\r\n" + "            \"dmpl__City__c\": \"NEW DELHI\",\r\n"
							+ "            \"dmpl__State__c\": \"Delhi\"\r\n" + "        },\r\n" + "        {\r\n"
							+ "            \"attributes\": {\r\n" + "                \"type\": \"AggregateResult\"\r\n"
							+ "            },\r\n" + "            \"dmpl__City__c\": \"NEW DELHI\",\r\n"
							+ "            \"dmpl__State__c\": \"Delhi\"\r\n" + "        }\r\n" + "    ]}");
			Mockito.when(globalConfig.lttrStateCities()).thenReturn("lttrStateCities");
			Mockito.when(resolver.adaptTo(AssetManager.class)).thenReturn(assetManager);
			Mockito.when(session.getValueFactory()).thenReturn(factory);
			Map<String, Object> param = new HashMap<>();
			param.put(ResourceResolverFactory.SUBSERVICE, Constants.WRITERSERVICEUSER);
			Mockito.when(resolverFactory.getServiceResourceResolver(param)).thenReturn(resolver);
			Mockito.when(resolver.adaptTo(Session.class)).thenReturn(session);
			lttrStateCityServiceImpl.getAvailableLTTRStateCity();
		});
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.service.impl.LTTRStateCityServiceImpl#processPackageData(java.lang.String)}.
	 */
	@Test
	void testProcessPackageData() {
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
			Mockito.when(globalConfig.sfdcQueryEndpoint()).thenReturn("sfdcQueryEndpoint");
			Mockito.when(HttpClients.createDefault()).thenReturn(httpClient1);
			Mockito.when(httpClient1.execute(Mockito.any(HttpGet.class))).thenReturn(null);
			Mockito.when(globalConfig.lttrStateCities()).thenReturn("lttrStateCities");
			Mockito.when(resolver.adaptTo(AssetManager.class)).thenReturn(assetManager);
			Mockito.when(session.getValueFactory()).thenReturn(factory);
			Map<String, Object> param = new HashMap<>();
			param.put(ResourceResolverFactory.SUBSERVICE, Constants.WRITERSERVICEUSER);
			Mockito.when(resolverFactory.getServiceResourceResolver(param)).thenReturn(resolver);
			Mockito.when(resolver.adaptTo(Session.class)).thenReturn(session);
			lttrStateCityServiceImpl.getAvailableLTTRStateCity();
			lttrStateCityServiceImpl.processPackageData(
					"00D5h000004zDWU!AQEAQACaYxWAqNHE4L35go7ZSeD23TxdoE73_rLFrW8yAHaBdtvcUb7jB3312.ow2qPas6Ud9.4VwrpEz5Ybee1of8mQhqTv");
		});

	}

}
