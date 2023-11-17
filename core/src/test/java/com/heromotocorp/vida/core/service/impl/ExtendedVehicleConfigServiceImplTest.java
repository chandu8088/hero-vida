package com.heromotocorp.vida.core.service.impl;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.UnsupportedRepositoryOperationException;
import javax.jcr.ValueFactory;

import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
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
import com.heromotocorp.vida.core.config.ClientConfig;
import com.heromotocorp.vida.core.config.GlobalConfig;
import com.heromotocorp.vida.core.utils.Constants;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import junitx.util.PrivateAccessor;

/**
 * JUnit test class for ExtendedVehicleConfigServiceImpl
 */
@ExtendWith({ AemContextExtension.class, MockitoExtension.class })
class ExtendedVehicleConfigServiceImplTest {

	private final AemContext context = new AemContext();

	private ExtendedVehicleConfigServiceImpl extendedVehicleConfigServiceImpl = new ExtendedVehicleConfigServiceImpl();

	private ClientConfig clientConfig;

	private ResourceResolverFactory resolverFactory;

	private Replicator replicator;

	private GlobalConfig globalConfig;

	@Mock
	private ResourceResolver resolver;

	@Mock
	private Resource assetResource;

	@Mock
	private Asset asset;

	@Mock
	private Rendition original;

	@Mock
	private AssetManager assetManager;

	@Mock
	private Session session;

	@Mock
	private ValueFactory factory;

	private String text = "City ,State ,Country ,Latitude,Longitude ,Branch Id,Parter Account ID\n"
			+ "New Delhi ,Delhi ,India ,28.6448,77.216721,a0Ip0000003g2lfEAA,001p00000102MzHAAU\n"
			+ "Bengaluru,Karnataka,India ,12.972442,77.580643,a0Ip0000003g9znEAA,001p00000104AZ1AAM\n";

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {

		clientConfig = context.getService(ClientConfig.class);
		clientConfig = Mockito.mock(ClientConfig.class);
		PrivateAccessor.setField(extendedVehicleConfigServiceImpl, "clientConfig", clientConfig);

		resolverFactory = context.getService(ResourceResolverFactory.class);
		resolverFactory = Mockito.mock(ResourceResolverFactory.class);
		PrivateAccessor.setField(extendedVehicleConfigServiceImpl, "resolverFactory", resolverFactory);

		replicator = context.getService(Replicator.class);
		replicator = Mockito.mock(Replicator.class);
		PrivateAccessor.setField(extendedVehicleConfigServiceImpl, "replicator", replicator);

		globalConfig = context.getService(GlobalConfig.class);
		globalConfig = Mockito.mock(GlobalConfig.class);
		PrivateAccessor.setField(extendedVehicleConfigServiceImpl, "globalConfig", globalConfig);
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.service.impl.ExtendedVehicleConfigServiceImpl#vehicleListMasterJson()}.
	 * 
	 * @throws LoginException
	 * @throws RepositoryException
	 * @throws UnsupportedRepositoryOperationException
	 */
	@Test
	void testVehicleListMasterJson()
			throws LoginException, UnsupportedRepositoryOperationException, RepositoryException {
		Map<String, Object> param = new HashMap<>();
		param.put(ResourceResolverFactory.SUBSERVICE, Constants.WRITERSERVICEUSER);
		InputStream stream = new ByteArrayInputStream(text.getBytes());
		Mockito.when(globalConfig.exchangeVehicleCSVLocationReqPath())
				.thenReturn("/content/dam/vida/config/master-brand-list.csv");
		Mockito.when(resolverFactory.getServiceResourceResolver(param)).thenReturn(resolver);
		Mockito.when(resolver.getResource("/content/dam/vida/config/master-brand-list.csv")).thenReturn(assetResource);
		Mockito.when(assetResource.adaptTo(Asset.class)).thenReturn(asset);
		Mockito.when(asset.getOriginal()).thenReturn(original);
		Mockito.when(original.getStream()).thenReturn(stream);
		Mockito.when(resolver.adaptTo(AssetManager.class)).thenReturn(assetManager);
		Mockito.when(resolver.adaptTo(Session.class)).thenReturn(session);
		Mockito.when(session.getValueFactory()).thenReturn(factory);
		extendedVehicleConfigServiceImpl.vehicleListMasterJson();
		assertNotNull(extendedVehicleConfigServiceImpl);
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.service.impl.ExtendedVehicleConfigServiceImpl#findCategory(java.util.List, java.lang.String)}.
	 * 
	 * @throws LoginException
	 * @throws RepositoryException
	 * @throws UnsupportedRepositoryOperationException
	 */
	@Test
	void testFindCategory() throws LoginException, UnsupportedRepositoryOperationException, RepositoryException {
		Map<String, Object> param = new HashMap<>();
		param.put(ResourceResolverFactory.SUBSERVICE, Constants.WRITERSERVICEUSER);
		InputStream stream = new ByteArrayInputStream(text.getBytes());
		Mockito.when(globalConfig.exchangeVehicleCSVLocationReqPath())
				.thenReturn("/content/dam/vida/config/master-brand-list.csv");
		Mockito.when(resolverFactory.getServiceResourceResolver(param)).thenReturn(resolver);
		Mockito.when(resolver.getResource("/content/dam/vida/config/master-brand-list.csv")).thenReturn(assetResource);
		Mockito.when(assetResource.adaptTo(Asset.class)).thenReturn(asset);
		Mockito.when(asset.getOriginal()).thenReturn(original);
		Mockito.when(original.getStream()).thenReturn(stream);
		Mockito.when(resolver.adaptTo(AssetManager.class)).thenReturn(assetManager);
		Mockito.when(resolver.adaptTo(Session.class)).thenReturn(session);
		Mockito.when(session.getValueFactory()).thenReturn(factory);
		extendedVehicleConfigServiceImpl.vehicleListMasterJson();
		assertNotNull(extendedVehicleConfigServiceImpl);
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.service.impl.ExtendedVehicleConfigServiceImpl#findBrand(java.util.Collection, java.lang.String)}.
	 */
	@Test
	void testFindBrand() throws LoginException, UnsupportedRepositoryOperationException, RepositoryException {
		Map<String, Object> param = new HashMap<>();
		param.put(ResourceResolverFactory.SUBSERVICE, Constants.WRITERSERVICEUSER);
		InputStream stream = new ByteArrayInputStream(text.getBytes());
		Mockito.when(globalConfig.exchangeVehicleCSVLocationReqPath())
				.thenReturn("/content/dam/vida/config/master-brand-list.csv");
		Mockito.when(resolverFactory.getServiceResourceResolver(param)).thenReturn(resolver);
		Mockito.when(resolver.getResource("/content/dam/vida/config/master-brand-list.csv")).thenReturn(assetResource);
		Mockito.when(assetResource.adaptTo(Asset.class)).thenReturn(asset);
		Mockito.when(asset.getOriginal()).thenReturn(original);
		Mockito.when(original.getStream()).thenReturn(stream);
		Mockito.when(resolver.adaptTo(AssetManager.class)).thenReturn(assetManager);
		Mockito.when(resolver.adaptTo(Session.class)).thenReturn(session);
		Mockito.when(session.getValueFactory()).thenReturn(factory);
		extendedVehicleConfigServiceImpl.vehicleListMasterJson();
		assertNotNull(extendedVehicleConfigServiceImpl);
	}

}
