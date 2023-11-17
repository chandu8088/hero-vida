package com.heromotocorp.vida.core.config;

/**
 * Interface for Twitter Configuration.
 *
 */
public interface TwitterConfig {

	String twitterAPIKey();
	String twitterAPIKeySecret();
	String twitterEndPointToken();
	String twitterEndPointTweets();
	String twitterJsonPath();
	String getBearerToken();
	String twitterProfile();
	String getTweets(String handler, int noOfTweets);

}
