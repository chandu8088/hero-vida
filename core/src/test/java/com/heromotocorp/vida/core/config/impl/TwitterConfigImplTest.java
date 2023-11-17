/**
 * 
 */
package com.heromotocorp.vida.core.config.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.time.Duration;

import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.sling.api.resource.LoginException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;

/**
 * JUnit test class for TwitterConfigImpl
 */
@ExtendWith(AemContextExtension.class)
class TwitterConfigImplTest {

	private final AemContext context = new AemContext();

	private TwitterConfigImpl twitterConfigImpl = new TwitterConfigImpl();

	private TwitterConfigImpl.ServiceConfig serviceConfig;

	private CloseableHttpClient httpClient;

	private CloseableHttpResponse tokenResponse;

	private StatusLine status;

	private HttpEntity entity;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		twitterConfigImpl = context.registerService(new TwitterConfigImpl());
		serviceConfig = Mockito.mock(TwitterConfigImpl.ServiceConfig.class);
		httpClient = Mockito.mock(CloseableHttpClient.class);
		tokenResponse = Mockito.mock(CloseableHttpResponse.class);
		status = Mockito.mock(StatusLine.class);
		entity = Mockito.mock(HttpEntity.class);

		Mockito.when(serviceConfig.twitterAPIKey()).thenReturn("twitterAPIKey");
		Mockito.when(serviceConfig.twitterAPIKeySecret()).thenReturn("twitterAPIKeySecret");
		Mockito.when(serviceConfig.twitterEndPointToken()).thenReturn("twitterEndPointToken");
		Mockito.when(serviceConfig.twitterEndPointTweets()).thenReturn("twitterEndPointTweets");
		Mockito.when(serviceConfig.twitterJsonPath()).thenReturn("twitterJsonPath");
		Mockito.when(serviceConfig.twitterProfile()).thenReturn("twitterProfile");
		twitterConfigImpl.activate(serviceConfig);
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.TwitterConfigImpl#activate(com.heromotocorp.vida.core.config.impl.TwitterConfigImpl.ServiceConfig)}.
	 */
	@Test
	void testActivate() {
		twitterConfigImpl.activate(serviceConfig);
		assertNotNull(twitterConfigImpl);
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.TwitterConfigImpl#getBearerToken()}.
	 * 
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	@Test
	void testGetBearerToken() throws ClientProtocolException, IOException {
		testActivate();

		Assertions.assertTimeoutPreemptively(Duration.ofSeconds(2), () -> {
			Mockito.mockStatic(EntityUtils.class);
			Mockito.mockStatic(HttpClients.class);
			Mockito.when(HttpClients.createDefault()).thenReturn(httpClient);
			Mockito.when(EntityUtils.toString(entity)).thenReturn(
					"{\"access_token\":\"00D5h000004zDWU!AQEAQACaYxWAqNHE4L35go7ZSeD23TxdoE73_rLFrW8yAHaBdtvcUb7jB3312.ow2qPas6Ud9.4VwrpEz5Ybee1of8mQhqTv\",\"instance_url\":\"https://vidaworld.my.salesforce.com\",\"id\":\"https://login.salesforce.com/id/00D5h000004zDWUEA2/0055h000006CcsGAAS\",\"token_type\":\"Bearer\",\"issued_at\":\"1690363697815\",\"signature\":\"ufQFGoXyWmL2iwYS3OU9h0VmYR4vKsVD0UJ5P5LrdHo=\"}");
			Mockito.when(httpClient.execute(Mockito.any(HttpPost.class))).thenReturn(tokenResponse);
			Mockito.when(tokenResponse.getStatusLine()).thenReturn(status);
			Mockito.when(status.getStatusCode()).thenReturn(400);
			Mockito.when(tokenResponse.getEntity()).thenReturn(entity);
			twitterConfigImpl.getBearerToken();

			Mockito.when(status.getStatusCode()).thenReturn(200);
			twitterConfigImpl.getBearerToken();
		});
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.TwitterConfigImpl#getTweets(java.lang.String, int)}.
	 * 
	 * @throws IOException
	 * @throws ClientProtocolException
	 * @throws LoginException
	 */
	@Test
	void testGetTweets() throws ClientProtocolException, IOException, LoginException {
		testActivate();
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.TwitterConfigImpl#twitterAPIKey()}.
	 */
	@Test
	void testTwitterAPIKey() {
		assertEquals("twitterAPIKey", twitterConfigImpl.twitterAPIKey());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.TwitterConfigImpl#twitterAPIKeySecret()}.
	 */
	@Test
	void testTwitterAPIKeySecret() {
		assertEquals("twitterAPIKeySecret", twitterConfigImpl.twitterAPIKeySecret());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.TwitterConfigImpl#twitterEndPointToken()}.
	 */
	@Test
	void testTwitterEndPointToken() {
		assertEquals("twitterEndPointToken", twitterConfigImpl.twitterEndPointToken());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.TwitterConfigImpl#twitterEndPointTweets()}.
	 */
	@Test
	void testTwitterEndPointTweets() {
		assertEquals("twitterEndPointTweets", twitterConfigImpl.twitterEndPointTweets());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.TwitterConfigImpl#twitterJsonPath()}.
	 */
	@Test
	void testTwitterJsonPath() {
		assertEquals("twitterJsonPath", twitterConfigImpl.twitterJsonPath());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.TwitterConfigImpl#twitterProfile()}.
	 */
	@Test
	void testTwitterProfile() {
		assertEquals("twitterProfile", twitterConfigImpl.twitterProfile());
	}

}
