package com.heromotocorp.vida.core.config.impl;


import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.osgi.services.HttpClientBuilderFactory;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.AttributeDefinition;

import com.heromotocorp.vida.core.config.ClientConfig;

/**
 * Osgi configuration to get client.
 *
 */
@Component(service = ClientConfig.class, immediate = true)
public class ClientConfigImpl implements ClientConfig {
	
	private CloseableHttpClient client;

	@Reference
	private HttpClientBuilderFactory clientBuilderFactory;
	
	private static final int DEFAULT_CONNECTION_TIMEOUT = 15000;
	private static final int DEFAULT_SOCKET_TIMEOUT = 15000;
	
    public @interface ServiceConfig {
    	
    	@AttributeDefinition(name = "Connection timeout", description = "Timeout in milliseconds until a connection is established. A timeout value of zero is interpreted as an "
				+ "infinite timeout. Default is 15000ms", defaultValue = { "" + DEFAULT_CONNECTION_TIMEOUT })
		int connectionTimeout() default DEFAULT_CONNECTION_TIMEOUT;

		@AttributeDefinition(name = "Socket timeout", description = "Timeout in milliseconds for waiting for data or a maximum period of inactivity between two consecutive "
				+ "data packets. Default is 15000ms", defaultValue = { "" + DEFAULT_SOCKET_TIMEOUT })
		int socketTimeout() default DEFAULT_SOCKET_TIMEOUT;

            }

    private int connectionTimeout;
    private int socketTimeout;
    
    RequestConfig requestConfig;
   
    @Activate
    protected void activate(ServiceConfig serviceConfig) {
    	
    	connectionTimeout = serviceConfig.connectionTimeout();
    	socketTimeout = serviceConfig.socketTimeout();
    	
    	requestConfig = RequestConfig.custom().setConnectTimeout(connectionTimeout)
				.setConnectionRequestTimeout(connectionTimeout).setSocketTimeout(socketTimeout).build();

		client = clientBuilderFactory.newBuilder().setDefaultRequestConfig(requestConfig).build();
    }
    
    @Override

/** 
 *
 * Connection timeout
 *
 * @return int
 */
	public int connectionTimeout() { 

		return connectionTimeout;
	}


	@Override

/** 
 *
 * Socket timeout
 *
 * @return int
 */
	public int socketTimeout() { 

		return socketTimeout;
	}

	@Override

/** 
 *
 * Client
 *
 * @return CloseableHttpClient
 */
	public CloseableHttpClient client() { 

		return clientBuilderFactory.newBuilder().setDefaultRequestConfig(requestConfig).build();
	}
}
