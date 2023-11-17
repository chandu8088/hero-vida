/**
 * 
 */
package com.heromotocorp.vida.core.service.impl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.UnsupportedRepositoryOperationException;
import javax.jcr.ValueFactory;

import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
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
import com.heromotocorp.vida.core.config.AutovertConfig;
import com.heromotocorp.vida.core.config.ClientConfig;
import com.heromotocorp.vida.core.config.GlobalConfig;
import com.heromotocorp.vida.core.utils.Constants;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import junitx.util.PrivateAccessor;

/**
 * JUnit test class for PricesConfigServiceImpl
 */
@ExtendWith({ AemContextExtension.class, MockitoExtension.class })
class PricesConfigServiceImplTest {

	private final AemContext context = new AemContext();

	private PricesConfigServiceImpl pricesConfigServiceImpl = new PricesConfigServiceImpl();

	private GlobalConfig globalConfig;

	private ClientConfig clientConfig;

	@Mock
	private Resource assetResource;

	@Mock
	private Resource assetResource1;

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
	private HttpEntity entity;

	@Mock
	private CloseableHttpClient httpClient;

	@Mock
	private StatusLine statusLine;

	private Replicator replicator;

	private ResourceResolverFactory resolverFactory;

	private AutovertConfig autovertConfig;

	private String text = "City ,State ,Country ,Latitude,Longitude ,Branch Id,Parter Account ID\n"
			+ "New Delhi ,Delhi ,India ,28.6448,77.216721,a0Ip0000003g2lfEAA,001p00000102MzHAAU\n"
			+ "Bengaluru,Karnataka,India ,12.972442,77.580643,a0Ip0000003g9znEAA,001p00000104AZ1AAM\n";

	private String text1 = "{\r\n" + "	\"items\": [{\r\n" + "		\"name\": \"V1 PRO\",\r\n"
			+ "		\"sku\": \"V1PRASBRCEL\",\r\n" + "		\"sf_id\": \"a1W0l000001A8GfEAK\",\r\n"
			+ "		\"__typename\": \"ConfigurableProduct\",\r\n" + "		\"variants\": [{\r\n"
			+ "			\"__typename\": \"SimpleProduct\",\r\n"
			+ "			\"name\": \"VIDA V1 PRO SC 8P RMV MAT ABRAX ORANGE\",\r\n"
			+ "			\"sku\": \"V1PRASBRCELMAO\",\r\n" + "			\"range\": \"110 km\",\r\n"
			+ "			\"charging_time\": \"65 min\",\r\n" + "			\"accelerator\": \"3.5 sec\",\r\n"
			+ "			\"top_speed\": \"80 kmph\",\r\n" + "			\"color\": \"Orange\",\r\n"
			+ "			\"description\": \"VIDA V1 PRO SC 8P RMV MAT ABRAX ORANGE\",\r\n"
			+ "			\"weight\": \"125.0\",\r\n" + "			\"ridingModes\": \"Eco, Ride, Sports, Custom\",\r\n"
			+ "			\"sf_id\": \"a240l000000WKZBAA4\",\r\n" + "			\"seatingType\": \"3.94 kWh\"\r\n"
			+ "		}]\r\n" + "	}, {\r\n" + "		\"name\": \"V1 PLUS\",\r\n" + "		\"sku\": \"V1PLASARCEL\",\r\n"
			+ "		\"sf_id\": \"a1W0l000001A8GaEAK\",\r\n" + "		\"__typename\": \"ConfigurableProduct\",\r\n"
			+ "		\"variants\": [{\r\n" + "			\"__typename\": \"SimpleProduct\",\r\n"
			+ "			\"name\": \"VIDA V1 PLUS SC 7P RMV MAT SPORTS RED\",\r\n"
			+ "			\"sku\": \"V1PLASARCELMSR\",\r\n" + "			\"range\": \"100 km\",\r\n"
			+ "			\"charging_time\": \"65 min\",\r\n" + "			\"accelerator\": \"10 sec\",\r\n"
			+ "			\"top_speed\": \"80 kmph\",\r\n" + "			\"color\": \"Red\",\r\n"
			+ "			\"description\": \"VIDA V1 PLUS SC 7P RMV MAT SPORTS RED\",\r\n"
			+ "			\"weight\": \"150.0\",\r\n" + "			\"ridingModes\": \"Eco, Ride, Sports, Custom\",\r\n"
			+ "			\"sf_id\": \"a240l000000WKZ1AAO\",\r\n" + "			\"seatingType\": \"3.44 kWh\"\r\n"
			+ "		}]\r\n" + "	}]\r\n" + "}";

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		globalConfig = context.getService(GlobalConfig.class);
		globalConfig = mock(GlobalConfig.class);
		PrivateAccessor.setField(pricesConfigServiceImpl, "globalConfig", globalConfig);

		clientConfig = context.getService(ClientConfig.class);
		clientConfig = mock(ClientConfig.class);
		PrivateAccessor.setField(pricesConfigServiceImpl, "clientConfig", clientConfig);

		resolverFactory = context.getService(ResourceResolverFactory.class);
		resolverFactory = mock(ResourceResolverFactory.class);
		PrivateAccessor.setField(pricesConfigServiceImpl, "resolverFactory", resolverFactory);

		replicator = context.getService(Replicator.class);
		replicator = mock(Replicator.class);
		PrivateAccessor.setField(pricesConfigServiceImpl, "replicator", replicator);

		autovertConfig = context.getService(AutovertConfig.class);
		autovertConfig = mock(AutovertConfig.class);
		PrivateAccessor.setField(pricesConfigServiceImpl, "autovertConfig", autovertConfig);
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.service.impl.PricesConfigServiceImpl#processCityBasedPriceAndOffer()}.
	 * 
	 * @throws LoginException
	 * @throws RepositoryException
	 * @throws UnsupportedRepositoryOperationException
	 */
	@Test
	void testProcessCityBasedPriceAndOffer()
			throws LoginException, UnsupportedRepositoryOperationException, RepositoryException {
		Map<String, Object> param = new HashMap<>();
		param.put(ResourceResolverFactory.SUBSERVICE, Constants.WRITERSERVICEUSER);
		InputStream stream = new ByteArrayInputStream(text.getBytes());
		Mockito.when(resolverFactory.getServiceResourceResolver(param)).thenReturn(resolver);
		Mockito.when(resolver.getResource("/content/dam/vida/config/City_Master.csv")).thenReturn(assetResource);
		Mockito.when(assetResource.adaptTo(Asset.class)).thenReturn(asset);
		Mockito.when(asset.getOriginal()).thenReturn(null);
		Mockito.when(resolver.adaptTo(AssetManager.class)).thenReturn(assetManager);
		Mockito.when(resolver.adaptTo(Session.class)).thenReturn(session);
		Mockito.when(session.getValueFactory()).thenReturn(factory);
		pricesConfigServiceImpl.processCityBasedPriceAndOffer();

		Mockito.when(asset.getOriginal()).thenReturn(original);
		Mockito.when(original.getStream()).thenReturn(stream);
		Mockito.when(globalConfig.cityMasterCSVLocationPath()).thenReturn("/content/dam/vida/config/City_Master.csv");
		Mockito.when(globalConfig.productListUrl()).thenReturn("/content/dam/vida/config/product-master.json");
		Mockito.when(resolver.getResource("/content/dam/vida/config/product-master.json")).thenReturn(assetResource1);
		Mockito.when(assetResource1.adaptTo(Asset.class)).thenReturn(asset1);
		Mockito.when(asset1.getOriginal()).thenReturn(null);
		pricesConfigServiceImpl.processCityBasedPriceAndOffer();
		assertNotNull(pricesConfigServiceImpl);
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.service.impl.PricesConfigServiceImpl#processCityBasedPriceAndOffer()}.
	 * 
	 * @throws LoginException
	 * @throws RepositoryException
	 * @throws UnsupportedRepositoryOperationException
	 */
	@Test
	void testProcessCityBasedPriceAndOffer1()
			throws LoginException, UnsupportedRepositoryOperationException, RepositoryException {
		Assertions.assertTimeoutPreemptively(Duration.ofSeconds(2), () -> {
			Mockito.mockStatic(EntityUtils.class);
			Mockito.mockStatic(HttpClients.class);
			Map<String, Object> param = new HashMap<>();
			param.put(ResourceResolverFactory.SUBSERVICE, Constants.WRITERSERVICEUSER);
			InputStream stream = new ByteArrayInputStream(text.getBytes());
			Mockito.when(resolverFactory.getServiceResourceResolver(param)).thenReturn(resolver);
			Mockito.when(resolver.getResource("/content/dam/vida/config/City_Master.csv")).thenReturn(assetResource);
			Mockito.when(assetResource.adaptTo(Asset.class)).thenReturn(asset);
			Mockito.when(resolver.adaptTo(AssetManager.class)).thenReturn(assetManager);
			Mockito.when(resolver.adaptTo(Session.class)).thenReturn(session);
			Mockito.when(session.getValueFactory()).thenReturn(factory);
			Mockito.when(asset.getOriginal()).thenReturn(original);
			Mockito.when(original.getStream()).thenReturn(stream);
			Mockito.when(globalConfig.cityMasterCSVLocationPath())
					.thenReturn("/content/dam/vida/config/City_Master.csv");
			Mockito.when(globalConfig.productListUrl()).thenReturn("/content/dam/vida/config/product-master.json");
			Mockito.when(resolver.getResource("/content/dam/vida/config/product-master.json"))
					.thenReturn(assetResource1);
			Mockito.when(assetResource1.adaptTo(Asset.class)).thenReturn(asset1);
			Mockito.when(asset1.getOriginal()).thenReturn(original1);
			InputStream stream1 = new ByteArrayInputStream(text1.getBytes());
			Mockito.when(original.getStream()).thenReturn(stream);
			Mockito.when(original1.adaptTo(InputStream.class)).thenReturn(stream1);
			Mockito.when(globalConfig.magentoBearerToken()).thenReturn("token");
			Mockito.when(globalConfig.magentoUrl()).thenReturn("magentoUrl");
			Mockito.when(globalConfig.magentoPriceAPIMethod()).thenReturn("magentoPriceAPIMethod");
			Mockito.when(HttpClients.createDefault()).thenReturn(httpClient);
			Mockito.when(httpClient.execute(Mockito.any(HttpGet.class))).thenReturn(httpResponse);
			Mockito.when(httpResponse.getEntity()).thenReturn(entity);
			Mockito.when(EntityUtils.toString(entity)).thenReturn(
					"{\"sku\":\"a240l000000jURrAAM\",\"price\":\"159366.67\",\"ex_showroom_price\":\"119900\",\"fame_subsidy_amount\":\"-22335\",\"state_subsidy\":\"0\",\"portable_charger\":\"20000\",\"effective_price\":\"119900\",\"data\":[{\"city\":\"JAIPUR\",\"financier_name\":\"Autovert\",\"financier_logo\":\"https://autovertplug.com/autovert_logo.svg\",\"offer_name\":\"Autovert Lease Offer\",\"min_emi\":3278,\"max_emi\":12394,\"min_downpayment\":10000,\"max_downpayment\":65000},{\"city\":\"JAIPUR\",\"financier_name\":\"Autovert\",\"financier_logo\":\"https://autovertplug.com/autovert_logo.svg\",\"offer_name\":\"Autovert Lease Offer\",\"min_emi\":4372,\"max_emi\":9802,\"min_downpayment\":12000,\"max_downpayment\":67000}]}");
			Mockito.when(globalConfig.autovertMinEmiEndPoint()).thenReturn("autovertMinEmiEndPoint");
			Mockito.when(httpResponse.getStatusLine()).thenReturn(statusLine);
			Mockito.when(statusLine.getStatusCode()).thenReturn(200);
			Mockito.when(globalConfig.autovertOfferUrlEndPoint()).thenReturn("autovertOfferUrlEndPoint");
			Mockito.when(autovertConfig.authHeader()).thenReturn("authHeader");
			Mockito.when(autovertConfig.autovertClientSecret()).thenReturn("autovertClientSecret");
			pricesConfigServiceImpl.processCityBasedPriceAndOffer();
		});
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.service.impl.PricesConfigServiceImpl#processCityBasedPriceAndOffer()}.
	 * 
	 * @throws LoginException
	 * @throws RepositoryException
	 * @throws UnsupportedRepositoryOperationException
	 */
	@Test
	void testProcessCityBasedPriceAndOffer2()
			throws LoginException, UnsupportedRepositoryOperationException, RepositoryException {
		Assertions.assertTimeoutPreemptively(Duration.ofSeconds(2), () -> {
			Mockito.mockStatic(EntityUtils.class);
			Mockito.mockStatic(HttpClients.class);
			Map<String, Object> param = new HashMap<>();
			param.put(ResourceResolverFactory.SUBSERVICE, Constants.WRITERSERVICEUSER);
			InputStream stream = new ByteArrayInputStream(text.getBytes());
			Mockito.when(resolverFactory.getServiceResourceResolver(param)).thenReturn(resolver);
			Mockito.when(resolver.getResource("/content/dam/vida/config/City_Master.csv")).thenReturn(assetResource);
			Mockito.when(assetResource.adaptTo(Asset.class)).thenReturn(asset);
			Mockito.when(resolver.adaptTo(AssetManager.class)).thenReturn(assetManager);
			Mockito.when(resolver.adaptTo(Session.class)).thenReturn(session);
			Mockito.when(session.getValueFactory()).thenReturn(factory);
			Mockito.when(asset.getOriginal()).thenReturn(original);
			Mockito.when(original.getStream()).thenReturn(stream);
			Mockito.when(globalConfig.cityMasterCSVLocationPath())
					.thenReturn("/content/dam/vida/config/City_Master.csv");
			Mockito.when(globalConfig.productListUrl()).thenReturn("/content/dam/vida/config/product-master.json");
			Mockito.when(resolver.getResource("/content/dam/vida/config/product-master.json"))
					.thenReturn(assetResource1);
			Mockito.when(assetResource1.adaptTo(Asset.class)).thenReturn(asset1);
			Mockito.when(asset1.getOriginal()).thenReturn(original1);
			InputStream stream1 = new ByteArrayInputStream(text1.getBytes());
			Mockito.when(original.getStream()).thenReturn(stream);
			Mockito.when(original1.adaptTo(InputStream.class)).thenReturn(stream1);
			Mockito.when(globalConfig.magentoBearerToken()).thenReturn("token");
			Mockito.when(globalConfig.magentoUrl()).thenReturn("magentoUrl");
			Mockito.when(globalConfig.magentoPriceAPIMethod()).thenReturn("magentoPriceAPIMethod");
			Mockito.when(HttpClients.createDefault()).thenReturn(httpClient);
			Mockito.when(httpClient.execute(Mockito.any(HttpGet.class))).thenReturn(httpResponse);
			Mockito.when(httpResponse.getEntity()).thenReturn(entity);
			Mockito.when(EntityUtils.toString(entity)).thenReturn(
					"{\"data\":[{\"city\":\"JAIPUR\",\"financier_name\":\"Autovert\",\"financier_logo\":\"https://autovertplug.com/autovert_logo.svg\",\"offer_name\":\"Autovert Lease Offer\",\"min_emi\":3278,\"max_emi\":12394,\"min_downpayment\":10000,\"max_downpayment\":65000},{\"city\":\"JAIPUR\",\"financier_name\":\"Autovert\",\"financier_logo\":\"https://autovertplug.com/autovert_logo.svg\",\"offer_name\":\"Autovert Lease Offer\",\"min_emi\":4372,\"max_emi\":9802,\"min_downpayment\":12000,\"max_downpayment\":67000}]}");
			Mockito.when(globalConfig.autovertMinEmiEndPoint()).thenReturn("autovertMinEmiEndPoint");
			Mockito.when(statusLine.getStatusCode()).thenReturn(400);
			Mockito.when(httpResponse.getStatusLine()).thenReturn(statusLine);
			Mockito.when(globalConfig.autovertOfferUrlEndPoint()).thenReturn("autovertOfferUrlEndPoint");
			Mockito.when(autovertConfig.authHeader()).thenReturn("authHeader");
			Mockito.when(autovertConfig.autovertClientSecret()).thenReturn("autovertClientSecret");
			pricesConfigServiceImpl.processCityBasedPriceAndOffer();
			assertNotNull(pricesConfigServiceImpl);
		});
	}

}
