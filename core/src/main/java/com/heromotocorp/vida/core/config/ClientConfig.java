package com.heromotocorp.vida.core.config;

import org.apache.http.impl.client.CloseableHttpClient;

/**
 * Interface for Client Configuration.
 *
 */
public interface ClientConfig {

	int connectionTimeout();
	int socketTimeout();
	CloseableHttpClient client();
	
}
