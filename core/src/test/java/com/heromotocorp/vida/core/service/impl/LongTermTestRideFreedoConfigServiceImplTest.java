/**
 * 
 */
package com.heromotocorp.vida.core.service.impl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import javax.jcr.Session;
import javax.jcr.ValueFactory;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
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
import com.heromotocorp.vida.core.config.GlobalConfig;
import com.heromotocorp.vida.core.utils.Constants;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import junitx.util.PrivateAccessor;

/**
 * JUnit test class for LongTermTestRideFreedoConfigServiceImpl
 */
@ExtendWith({ AemContextExtension.class, MockitoExtension.class })
class LongTermTestRideFreedoConfigServiceImplTest {

	private final AemContext context = new AemContext();

	private LongTermTestRideFreedoConfigServiceImpl longTermTestRideFreedoConfigServiceImpl = new LongTermTestRideFreedoConfigServiceImpl();

	private ResourceResolverFactory resolverFactory;

	private Replicator replicator;

	private GlobalConfig globalConfig;

	@Mock
	private ResourceResolver resolver;

	@Mock
	private CloseableHttpResponse httpResponse;

	@Mock
	private HttpEntity entity;

	@Mock
	private CloseableHttpClient httpClient;
	
	@Mock
	private AssetManager assetManager;

	@Mock
	private Session session;

	@Mock
	private ValueFactory factory;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		globalConfig = context.getService(GlobalConfig.class);
		globalConfig = mock(GlobalConfig.class);
		PrivateAccessor.setField(longTermTestRideFreedoConfigServiceImpl, "globalConfig", globalConfig);

		resolverFactory = context.getService(ResourceResolverFactory.class);
		resolverFactory = mock(ResourceResolverFactory.class);
		PrivateAccessor.setField(longTermTestRideFreedoConfigServiceImpl, "resolverFactory", resolverFactory);

		replicator = context.getService(Replicator.class);
		replicator = mock(Replicator.class);
		PrivateAccessor.setField(longTermTestRideFreedoConfigServiceImpl, "replicator", replicator);
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.service.impl.LongTermTestRideFreedoConfigServiceImpl#processLTTRMaster()}.
	 * 
	 * @throws LoginException
	 */
	@Test
	void testProcessLTTRMaster() throws LoginException {
		Assertions.assertTimeoutPreemptively(Duration.ofSeconds(2), () -> {
			Mockito.mockStatic(EntityUtils.class);
			Mockito.mockStatic(HttpClients.class);
			Map<String, Object> param = new HashMap<>();
			param.put(ResourceResolverFactory.SUBSERVICE, Constants.WRITERSERVICEUSER);
			Mockito.when(resolverFactory.getServiceResourceResolver(param)).thenReturn(resolver);
			Mockito.when(HttpClients.createDefault()).thenReturn(httpClient);
			Mockito.when(globalConfig.lttrBaseEndPoint()).thenReturn("lttrBaseEndPoint");
			Mockito.when(globalConfig.lttrAllCityEndPoint()).thenReturn("lttrAllCityEndPoint");
			Mockito.when(globalConfig.lttrApiKey()).thenReturn("lttrApiKey");
			Mockito.when(httpClient.execute(Mockito.any(HttpGet.class))).thenReturn(httpResponse);
			Mockito.when(httpResponse.getEntity()).thenReturn(entity);
			Mockito.when(EntityUtils.toString(entity)).thenReturn(
					"{\"IsSuccess\":true,\"ResponseStatusCode\":200,\"Message\":\"Record fetch successfully\",\"Result\":[{\"id\":4,\"city_name\":\"NEW DELHI\",\"state_id\":1,\"state\":{\"id\":1,\"state_name\":\"Delhi\",\"state_code\":\"DL\",\"country\":{\"country_name\":\"India\"}},\"name\":\"name\"},{\"id\":4,\"city_name\":\"GAUTAM BUDDHA NAGAR\",\"state_id\":2,\"state\":{\"id\":2,\"state_name\":\"Uttar Pradesh\",\"state_code\":\"UP\",\"country\":{\"country_name\":\"India\"}},\"name\":\"name\"},{\"id\":3,\"city_name\":\"JAIPUR\",\"state_id\":3,\"state\":{\"id\":3,\"state_name\":\"Rajasthan\",\"state_code\":\"RJ\",\"country\":{\"country_name\":\"India\"}},\"name\":\"name\"},{\"id\":24,\"city_name\":\"BENGALURU\",\"state_id\":6,\"state\":{\"id\":6,\"state_name\":\"Karnataka\",\"state_code\":\"KA\",\"country\":{\"country_name\":\"India\"}},\"name\":\"name\"},{\"id\":25,\"city_name\":\"MYSURU\",\"state_id\":6,\"state\":{\"id\":6,\"state_name\":\"Karnataka\",\"state_code\":\"KA\",\"country\":{\"country_name\":\"India\"}},\"name\":\"name\"}],\"Error\":\"\"}");
			Mockito.when(globalConfig.lttrCityMasterLocation()).thenReturn("lttrCityMasterLocation");
			Mockito.when(resolver.adaptTo(AssetManager.class)).thenReturn(assetManager);
			Mockito.when(resolver.adaptTo(Session.class)).thenReturn(session);
			Mockito.when(session.getValueFactory()).thenReturn(factory);
			longTermTestRideFreedoConfigServiceImpl.processLTTRMaster();
			assertNotNull(longTermTestRideFreedoConfigServiceImpl);
		});
	}

}
