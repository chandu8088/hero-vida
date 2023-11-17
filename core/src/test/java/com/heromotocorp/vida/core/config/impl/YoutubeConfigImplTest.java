/**
 * 
 */
package com.heromotocorp.vida.core.config.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
import org.mockito.Mockito;

import com.day.cq.dam.api.AssetManager;
import com.day.cq.replication.Replicator;
import com.heromotocorp.vida.core.utils.Constants;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import junitx.util.PrivateAccessor;

/**
 * JUnit test class for YoutubeConfigImpl
 */
@ExtendWith(AemContextExtension.class)
class YoutubeConfigImplTest {

	private final AemContext context = new AemContext();

	private YoutubeConfigImpl youtubeConfigImpl = new YoutubeConfigImpl();

	private YoutubeConfigImpl.ServiceConfig serviceConfig;

	private Replicator replicator;

	private CloseableHttpClient httpClient;

	private CloseableHttpResponse tokenResponse;

	private StatusLine status;

	private HttpEntity entity;

	private ResourceResolverFactory resolverFactory;

	private ResourceResolver resolver;

	private Session session;

	private AssetManager assetManager;

	private ValueFactory factory;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		youtubeConfigImpl = context.registerService(new YoutubeConfigImpl());
		serviceConfig = Mockito.mock(YoutubeConfigImpl.ServiceConfig.class);
		httpClient = Mockito.mock(CloseableHttpClient.class);
		tokenResponse = Mockito.mock(CloseableHttpResponse.class);
		status = Mockito.mock(StatusLine.class);
		entity = Mockito.mock(HttpEntity.class);
		resolver = Mockito.mock(ResourceResolver.class);
		session = Mockito.mock(Session.class);
		assetManager = Mockito.mock(AssetManager.class);
		factory = Mockito.mock(ValueFactory.class);

		replicator = context.getService(Replicator.class);
		replicator = mock(Replicator.class);
		PrivateAccessor.setField(youtubeConfigImpl, "replicator", replicator);

		resolverFactory = context.getService(ResourceResolverFactory.class);
		resolverFactory = mock(ResourceResolverFactory.class);
		PrivateAccessor.setField(youtubeConfigImpl, "resolverFactory", resolverFactory);

		Mockito.when(serviceConfig.youtubeAPIKey()).thenReturn("youtubeAPIKey");
		Mockito.when(serviceConfig.youtubeChannelId()).thenReturn("youtubeChannelId");
		Mockito.when(serviceConfig.youtubeJsonPath()).thenReturn("youtubeJsonPath");
		Mockito.when(serviceConfig.youtubeAPIEndPoint()).thenReturn("youtubeAPIEndPoint");
		youtubeConfigImpl.activate(serviceConfig);
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.YoutubeConfigImpl#activate(com.heromotocorp.vida.core.config.impl.YoutubeConfigImpl.ServiceConfig)}.
	 */
	@Test
	void testActivate() {
		youtubeConfigImpl.activate(serviceConfig);
		assertNotNull(youtubeConfigImpl);
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.YoutubeConfigImpl#getYoutubeVideos()}.
	 * 
	 * @throws IOException
	 * @throws ClientProtocolException
	 * @throws LoginException
	 * @throws RepositoryException
	 * @throws UnsupportedRepositoryOperationException
	 */
	@Test
	void testGetYoutubeVideos() throws ClientProtocolException, IOException, LoginException,
			UnsupportedRepositoryOperationException, RepositoryException {
		testActivate();
		Assertions.assertTimeoutPreemptively(Duration.ofSeconds(2), () -> {
			Mockito.mockStatic(EntityUtils.class);
			Mockito.mockStatic(HttpClients.class);
			Mockito.when(HttpClients.createDefault()).thenReturn(httpClient);
			Mockito.when(EntityUtils.toString(entity)).thenReturn(
					"{\"access_token\":\"00D5h000004zDWU!AQEAQACaYxWAqNHE4L35go7ZSeD23TxdoE73_rLFrW8yAHaBdtvcUb7jB3312.ow2qPas6Ud9.4VwrpEz5Ybee1of8mQhqTv\",\"instance_url\":\"https://vidaworld.my.salesforce.com\",\"id\":\"https://login.salesforce.com/id/00D5h000004zDWUEA2/0055h000006CcsGAAS\",\"token_type\":\"Bearer\",\"issued_at\":\"1690363697815\",\"signature\":\"ufQFGoXyWmL2iwYS3OU9h0VmYR4vKsVD0UJ5P5LrdHo=\"}");
			Mockito.when(httpClient.execute(Mockito.any(HttpGet.class))).thenReturn(tokenResponse);
			Mockito.when(tokenResponse.getStatusLine()).thenReturn(status);
			Mockito.when(status.getStatusCode()).thenReturn(200);
			Mockito.when(tokenResponse.getEntity()).thenReturn(entity);
			Map<String, Object> param = new HashMap<>();
			param.put(ResourceResolverFactory.SUBSERVICE, Constants.WRITERSERVICEUSER);
			Mockito.when(resolverFactory.getServiceResourceResolver(param)).thenReturn(resolver);
			Mockito.when(resolver.adaptTo(Session.class)).thenReturn(session);
			Mockito.when(resolver.adaptTo(AssetManager.class)).thenReturn(assetManager);
			Mockito.when(session.getValueFactory()).thenReturn(factory);
			youtubeConfigImpl.getYoutubeVideos();

			Mockito.when(status.getStatusCode()).thenReturn(400);
			youtubeConfigImpl.getYoutubeVideos();
		});
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.YoutubeConfigImpl#getYoutubeVideos()}.
	 * 
	 * @throws IOException
	 * @throws ClientProtocolException
	 * @throws LoginException
	 * @throws RepositoryException
	 * @throws UnsupportedRepositoryOperationException
	 */
	@Test
	void testGetYoutubeVideos1() {
		testActivate();
		Assertions.assertTimeoutPreemptively(Duration.ofSeconds(2), () -> {
			Mockito.mockStatic(EntityUtils.class);
			Mockito.mockStatic(HttpClients.class);
			Mockito.when(HttpClients.createDefault()).thenReturn(httpClient);
			Mockito.when(EntityUtils.toString(entity)).thenReturn(
					"{\"access_token\":\"00D5h000004zDWU!AQEAQACaYxWAqNHE4L35go7ZSeD23TxdoE73_rLFrW8yAHaBdtvcUb7jB3312.ow2qPas6Ud9.4VwrpEz5Ybee1of8mQhqTv\",\"instance_url\":\"https://vidaworld.my.salesforce.com\",\"id\":\"https://login.salesforce.com/id/00D5h000004zDWUEA2/0055h000006CcsGAAS\",\"token_type\":\"Bearer\",\"issued_at\":\"1690363697815\",\"signature\":\"ufQFGoXyWmL2iwYS3OU9h0VmYR4vKsVD0UJ5P5LrdHo=\"}");
			Mockito.when(httpClient.execute(Mockito.any(HttpGet.class))).thenThrow(ClientProtocolException.class);
			Mockito.when(tokenResponse.getStatusLine()).thenReturn(status);
			Mockito.when(status.getStatusCode()).thenReturn(200);
			Mockito.when(tokenResponse.getEntity()).thenReturn(entity);
			Map<String, Object> param = new HashMap<>();
			param.put(ResourceResolverFactory.SUBSERVICE, Constants.WRITERSERVICEUSER);
			Mockito.when(resolverFactory.getServiceResourceResolver(param)).thenReturn(resolver);
			Mockito.when(resolver.adaptTo(Session.class)).thenReturn(session);
			Mockito.when(resolver.adaptTo(AssetManager.class)).thenReturn(assetManager);
			Mockito.when(session.getValueFactory()).thenReturn(factory);
			youtubeConfigImpl.getYoutubeVideos();
		});
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.YoutubeConfigImpl#getYoutubeVideos()}.
	 * 
	 * @throws IOException
	 * @throws ClientProtocolException
	 * @throws LoginException
	 * @throws RepositoryException
	 * @throws UnsupportedRepositoryOperationException
	 */
	@Test
	void testGetYoutubeVideos2() {
		testActivate();
		Assertions.assertTimeoutPreemptively(Duration.ofSeconds(2), () -> {
			Mockito.mockStatic(EntityUtils.class);
			Mockito.mockStatic(HttpClients.class);
			Mockito.when(HttpClients.createDefault()).thenReturn(httpClient);
			Mockito.when(EntityUtils.toString(entity)).thenThrow(IOException.class);
			Mockito.when(httpClient.execute(Mockito.any(HttpGet.class))).thenReturn(tokenResponse);
			Mockito.when(tokenResponse.getStatusLine()).thenReturn(status);
			Mockito.when(status.getStatusCode()).thenReturn(200);
			Mockito.when(tokenResponse.getEntity()).thenReturn(entity);
			Map<String, Object> param = new HashMap<>();
			param.put(ResourceResolverFactory.SUBSERVICE, Constants.WRITERSERVICEUSER);
			Mockito.when(resolverFactory.getServiceResourceResolver(param)).thenReturn(resolver);
			Mockito.when(resolver.adaptTo(Session.class)).thenReturn(session);
			Mockito.when(resolver.adaptTo(AssetManager.class)).thenReturn(assetManager);
			Mockito.when(session.getValueFactory()).thenReturn(factory);
			youtubeConfigImpl.getYoutubeVideos();
		});
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.YoutubeConfigImpl#youtubeAPIKey()}.
	 */
	@Test
	void testYoutubeAPIKey() {
		assertEquals("youtubeAPIKey", youtubeConfigImpl.youtubeAPIKey());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.YoutubeConfigImpl#youtubeChannelId()}.
	 */
	@Test
	void testYoutubeChannelId() {
		assertEquals("youtubeChannelId", youtubeConfigImpl.youtubeChannelId());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.YoutubeConfigImpl#youtubeJsonPath()}.
	 */
	@Test
	void testYoutubeJsonPath() {
		assertEquals("youtubeJsonPath", youtubeConfigImpl.youtubeJsonPath());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.YoutubeConfigImpl#youtubeAPIEndPoint()}.
	 */
	@Test
	void testYoutubeAPIEndPoint() {
		assertEquals("youtubeAPIEndPoint", youtubeConfigImpl.youtubeAPIEndPoint());
	}

}
