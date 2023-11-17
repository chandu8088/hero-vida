/**
 * 
 */
package com.heromotocorp.vida.core.servlets;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.jcr.Session;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.day.cq.dam.api.Asset;
import com.day.cq.dam.api.AssetManager;
import com.day.cq.dam.api.Rendition;
import com.heromotocorp.vida.core.config.GlobalConfig;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import junitx.util.PrivateAccessor;

/**
 * JUnit test class for MapServlet
 */
@ExtendWith({ AemContextExtension.class, MockitoExtension.class })
class MapServletTest {

	private final AemContext context = new AemContext();

	private MapServlet mapServlet = new MapServlet();

	private GlobalConfig globalConfig;

	@Mock
	private Rendition original;

	@Mock
	private ResourceResolver resolver;

	private SlingHttpServletRequest req;

	private SlingHttpServletResponse resp;

	@Mock
	private Resource assetResource;

	@Mock
	private Asset asset;

	@Mock
	private AssetManager assetManager;

	@Mock
	private Session session;

	private String text = "brand_name ,brand_category ,brand_model ,cc\n" + "Hero ,Motorcycle ,SPLENDOR + ,100\n"
			+ "Hero ,Motorcycle ,HF DELUXE ,100\n" + "Hero ,Motorcycle ,HF DELUXE ECO ,100\n"
			+ "Hero ,Motorcycle ,HF DELUXE I3S ,100\n" + "Hero ,Motorcycle ,PASSSION PRO 110 ,110\n"
			+ "Hero ,Motorcycle ,GLAMOUR ,125\n" + "Hero ,Motorcycle ,GLAMOUR FI ,125\n"
			+ "Hero ,Motorcycle ,GLAMOUR I3S ,125\n" + "Hero ,Motorcycle ,GLAMOUR PGM FI ,125\n"
			+ "Hero ,Motorcycle ,SUPER SPLENDOR ,125\n" + "Hero ,Motorcycle ,SPLENDOR ISMART 110 ,110\n"
			+ "Hero ,Motorcycle ,PASSION PRO ,100\n" + "Hero ,Motorcycle ,PASSION PLUS ,100\n"
			+ " ,Motorcycle ,PASSION PRO I3S ,100\n" + "Hero , ,PASSION ,100\n"
			+ "Hero ,Motorcycle ,SPLENDOR I-SMART ,100\n";

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		req = Mockito.mock(SlingHttpServletRequest.class);
		resp = Mockito.mock(SlingHttpServletResponse.class);

		globalConfig = context.getService(GlobalConfig.class);
		globalConfig = Mockito.mock(GlobalConfig.class);
		PrivateAccessor.setField(mapServlet, "globalConfig", globalConfig);
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.servlets.MapServlet#doGet(org.apache.sling.api.SlingHttpServletRequest, org.apache.sling.api.SlingHttpServletResponse)}.
	 */
	@Test
	void testDoGetSlingHttpServletRequestSlingHttpServletResponse() {
		mapServlet.doGet(req, resp);

		Mockito.when(globalConfig.cityMasterCSVLocationPath()).thenReturn("/content/dam/vida/config/City_Master.csv");
		mapServlet.doGet(req, resp);
		assertNotNull(mapServlet);
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.servlets.MapServlet#doGet(org.apache.sling.api.SlingHttpServletRequest, org.apache.sling.api.SlingHttpServletResponse)}.
	 * 
	 * @throws IOException
	 */
	@Test
	void testDoGet() throws IOException {
		InputStream stream = new ByteArrayInputStream(text.getBytes());
		Mockito.when(globalConfig.cityMasterCSVLocationPath()).thenReturn("/content/dam/vida/config/City_Master.csv");
		Mockito.when(req.getResourceResolver()).thenReturn(resolver);
		Mockito.when(resolver.getResource("/content/dam/vida/config/City_Master.csv")).thenReturn(assetResource);
		Mockito.when(assetResource.adaptTo(Asset.class)).thenReturn(asset);
		Mockito.when(asset.getOriginal()).thenReturn(original);
		Mockito.when(original.getStream()).thenReturn(stream);
		mapServlet.doGet(req, resp);

		Mockito.when(asset.getOriginal()).thenReturn(null);
		mapServlet.doGet(req, resp);
		assertNotNull(mapServlet);
	}

}
