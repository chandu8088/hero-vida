/**
 * 
 */
package com.heromotocorp.vida.core.config.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.osgi.services.HttpClientBuilderFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;



import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import junitx.util.PrivateAccessor;

/**
 * JUnit test class for ClientConfigImpl
 */
@ExtendWith({AemContextExtension.class, MockitoExtension.class})
class ClientConfigImplTest {

	private final AemContext context = new AemContext();

	private ClientConfigImpl clientConfigImpl = new ClientConfigImpl();

	private ClientConfigImpl.ServiceConfig serviceConfig;

	@Mock
	private HttpClientBuilderFactory clientBuilderFactory;

	@Mock
	private HttpClientBuilder httpBuilder;
	
	@Mock
	private Builder builder;
	
	@Mock
	private RequestConfig requestConfig;
	
	@Mock
	private CloseableHttpClient client;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		context.registerService(clientBuilderFactory);
		clientConfigImpl = context.registerService(new ClientConfigImpl());
		serviceConfig = Mockito.mock(ClientConfigImpl.ServiceConfig.class);
		httpBuilder = Mockito.mock(HttpClientBuilder.class);
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.ClientConfigImpl#activate(com.heromotocorp.vida.core.config.impl.ClientConfigImpl.ServiceConfig)}.
	 * @throws NoSuchFieldException 
	 */
	@Test
	void testActivate() throws NoSuchFieldException {
		PrivateAccessor.setField(clientConfigImpl, "clientBuilderFactory", clientBuilderFactory);
		Mockito.when(serviceConfig.connectionTimeout()).thenReturn(2);
		Mockito.when(serviceConfig.socketTimeout()).thenReturn(5);
		Mockito.when(clientBuilderFactory.newBuilder()).thenReturn(httpBuilder);
		Mockito.mockStatic(RequestConfig.class);
		Mockito.when(RequestConfig.custom()).thenReturn(builder);
		Mockito.when(builder.setConnectTimeout(Mockito.anyInt())).thenReturn(builder);
		Mockito.when(builder.setConnectionRequestTimeout(Mockito.anyInt())).thenReturn(builder);
		Mockito.when(builder.setSocketTimeout(Mockito.anyInt())).thenReturn(builder);
		Mockito.when(builder.build()).thenReturn(requestConfig);
		Mockito.when(httpBuilder.setDefaultRequestConfig(requestConfig)).thenReturn(httpBuilder);
		Mockito.when(httpBuilder.build()).thenReturn(client);
		clientConfigImpl.activate(serviceConfig);
		assertNotNull(clientConfigImpl);
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.ClientConfigImpl#connectionTimeout()}.
	 */
	@Test
	void testConnectionTimeout() {
		assertEquals(0, clientConfigImpl.connectionTimeout());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.ClientConfigImpl#socketTimeout()}.
	 */
	@Test
	void testSocketTimeout() {
		assertEquals(0, clientConfigImpl.socketTimeout());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.ClientConfigImpl#client()}.
	 * @throws NoSuchFieldException 
	 */
	@Test
	void testClient() throws NoSuchFieldException {
		PrivateAccessor.setField(clientConfigImpl, "clientBuilderFactory", clientBuilderFactory);
		Mockito.when(clientBuilderFactory.newBuilder()).thenReturn(httpBuilder);
		Mockito.when(RequestConfig.custom()).thenReturn(builder);
		Mockito.when(httpBuilder.setDefaultRequestConfig(Mockito.any())).thenReturn(httpBuilder);
		Mockito.when(httpBuilder.build()).thenReturn(client);
		assertEquals(client, clientConfigImpl.client());
	}

}
