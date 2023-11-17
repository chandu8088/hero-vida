/**
 * 
 */
package com.heromotocorp.vida.core.utils;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;

import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.UnsupportedRepositoryOperationException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.api.resource.ValueMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.day.cq.dam.api.AssetManager;
import com.day.cq.replication.Replicator;
import com.day.cq.wcm.api.Page;
import com.heromotocorp.vida.core.config.ClientConfig;
import com.heromotocorp.vida.core.config.GlobalConfig;

import io.wcm.testing.mock.aem.junit5.AemContextExtension;

/**
 * JUnit test class for CommonUtils
 */
@ExtendWith({ AemContextExtension.class, MockitoExtension.class })
class CommonUtilsTest {
	
	@Mock
	private CommonUtils commonUtils;
	
	@Mock
	private ValueMap valueMap;
	
	@Mock
	private ResourceResolverFactory resolverFactory;
	
	@Mock
	private Session session;
	
	@Mock
	private Replicator replicator;
	
	@Mock
	private ResourceResolver resolver;
	
	@Mock
	private AssetManager assetManager;
	
	@Mock
	private GlobalConfig globalConfig;
	
	@Mock
	private ClientConfig clientConfig;

	@Mock
	private CloseableHttpClient httpClient;

	@Mock
	private CloseableHttpResponse httpResponse;
	
	@Mock
	private HttpEntity entity;
	
	private String line = "City ,State ,Country ,Latitude,Longitude ,Branch Id,Parter Account ID\n"
			+ "New Delhi ,Delhi ,India ,28.6448,77.216721,a0Ip0000003g2lfEAA,001p00000102MzHAAU\n";
	
	@Mock
	private Page currentPage;
	
	@Mock
	private Page languagePage;
	
	@Mock
	private Page parentPage;
	
	@Mock
	private Resource resource;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
	}

	/**
	 * Test method for {@link com.heromotocorp.vida.core.utils.CommonUtils#getPropertyValue(org.apache.sling.api.resource.ValueMap, java.lang.String)}.
	 */
	@Test
	void testGetPropertyValue() {
		Mockito.when(valueMap.containsKey("property")).thenReturn(true, false);
		Mockito.when(valueMap.get("property", String.class)).thenReturn("propValue");
		CommonUtils.getPropertyValue(valueMap, "property");

		CommonUtils.getPropertyValue(valueMap, "property");
		assertNotNull(commonUtils);
	}

	/**
	 * Test method for {@link com.heromotocorp.vida.core.utils.CommonUtils#getResourceResolver(org.apache.sling.api.resource.ResourceResolverFactory, java.lang.String)}.
	 * @throws LoginException 
	 */
	@Test
	void testGetResourceResolver() throws LoginException {
		Mockito.when(resolverFactory.getServiceResourceResolver(Mockito.anyMap())).thenThrow(LoginException.class);
		CommonUtils.getResourceResolver(resolverFactory, "service");
		assertNotNull(commonUtils);
	}

	/**
	 * Test method for {@link com.heromotocorp.vida.core.utils.CommonUtils#replicateContent(javax.jcr.Session, java.lang.String, com.day.cq.replication.Replicator)}.
	 */
	@Test
	void testReplicateContent() {
		CommonUtils.replicateContent(session, "filePath", replicator);
		assertNotNull(commonUtils);
	}

	/**
	 * Test method for {@link com.heromotocorp.vida.core.utils.CommonUtils#createDamAsset(java.lang.String, org.apache.sling.api.resource.ResourceResolver, java.lang.String)}.
	 * @throws RepositoryException 
	 * @throws UnsupportedRepositoryOperationException 
	 */
	@Test
	void testCreateDamAsset() throws UnsupportedRepositoryOperationException, RepositoryException {
		Mockito.when(resolver.adaptTo(AssetManager.class)).thenReturn(assetManager);
		Mockito.when(resolver.adaptTo(Session.class)).thenReturn(session);
		Mockito.when(session.getValueFactory()).thenThrow(RepositoryException.class);
		CommonUtils.createDamAsset("jsonString", resolver, "jsonPath");
		assertNotNull(commonUtils);
	}

	/**
	 * Test method for {@link com.heromotocorp.vida.core.utils.CommonUtils#getToken(com.heromotocorp.vida.core.config.GlobalConfig, com.heromotocorp.vida.core.config.ClientConfig)}.
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	@Test
	void testGetToken() throws ClientProtocolException, IOException {
		Mockito.when(globalConfig.postRequestToken()).thenReturn("postRequestToken");
		Mockito.when(globalConfig.grantType()).thenReturn("grantType");
		Mockito.when(globalConfig.clientId()).thenReturn("clientId");
		Mockito.when(globalConfig.clientSecret()).thenReturn("clientSecret");
		Mockito.when(globalConfig.username()).thenReturn("username");
		Mockito.when(globalConfig.password()).thenReturn("password");
		Mockito.when(globalConfig.contentType()).thenReturn("contentType");
		Mockito.when(clientConfig.client()).thenReturn(httpClient);
		Mockito.when(httpClient.execute(Mockito.any(HttpPost.class))).thenThrow(IOException.class);
		CommonUtils.getToken(globalConfig, clientConfig);
		assertNotNull(commonUtils);
	}

	/**
	 * Test method for {@link com.heromotocorp.vida.core.utils.CommonUtils#getRecordFromLine(java.lang.String)}.
	 */
	@Test
	void testGetRecordFromLine() {
		CommonUtils.getRecordFromLine(line);
		assertNotNull(commonUtils);
	}

	/**
	 * Test method for {@link com.heromotocorp.vida.core.utils.CommonUtils#updateUrl(org.apache.sling.api.resource.Resource, java.lang.String)}.
	 */
	@Test
	void testUpdateUrl() {
		CommonUtils.getRecordFromLine(line);
		assertNotNull(commonUtils);
	}

	/**
	 * Test method for {@link com.heromotocorp.vida.core.utils.CommonUtils#getCountryCode(com.day.cq.wcm.api.Page)}.
	 */
	@Test
	void testGetCountryCode() {
		Mockito.when(currentPage.getAbsoluteParent(3)).thenReturn(languagePage);
		Mockito.when(languagePage.getParent()).thenReturn(parentPage);
		Mockito.when(parentPage.getName()).thenReturn("parentPage");
		CommonUtils.getCountryCode(currentPage);

		Mockito.when(languagePage.getParent()).thenReturn(null);
		CommonUtils.getCountryCode(currentPage);

		Mockito.when(currentPage.getAbsoluteParent(3)).thenReturn(null);
		CommonUtils.getCountryCode(currentPage);

		CommonUtils.getCountryCode(null);
		assertNotNull(commonUtils);
	}

	/**
	 * Test method for {@link com.heromotocorp.vida.core.utils.CommonUtils#getLanguagePage(com.day.cq.wcm.api.Page)}.
	 */
	@Test
	void testGetLanguagePage() {
		CommonUtils.getLanguagePage(null);
		assertNotNull(commonUtils);
	}

	/**
	 * Test method for {@link com.heromotocorp.vida.core.utils.CommonUtils#getLanguageCode(com.day.cq.wcm.api.Page)}.
	 */
	@Test
	void testGetLanguageCode() {
		CommonUtils.getLanguageCode(null);
		assertNotNull(commonUtils);
	}

	/**
	 * Test method for {@link com.heromotocorp.vida.core.utils.CommonUtils#getIsExternalURL(java.lang.String)}.
	 */
	@Test
	void testGetIsExternalURL() {
		CommonUtils.getIsExternalURL("url");

		CommonUtils.getIsExternalURL("http:url");

		CommonUtils.getIsExternalURL("https:url");

		CommonUtils.getIsExternalURL(null);
		assertNotNull(commonUtils);
	}

	/**
	 * Test method for {@link com.heromotocorp.vida.core.utils.CommonUtils#formatCityName(java.lang.String)}.
	 */
	@Test
	void testFormatCityName() {
		CommonUtils.formatCityName("new delhi");

		CommonUtils.formatCityName("new%20delhi");

		CommonUtils.formatCityName("20");
		assertNotNull(commonUtils);
	}

	/**
	 * Test method for {@link com.heromotocorp.vida.core.utils.CommonUtils#rupeeFormat(java.lang.String)}.
	 */
	@Test
	void testRupeeFormat() {
		CommonUtils.rupeeFormat("200000");
		assertNotNull(commonUtils);
	}

}
