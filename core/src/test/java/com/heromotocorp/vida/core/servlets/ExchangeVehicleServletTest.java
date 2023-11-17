/**
 * 
 */
package com.heromotocorp.vida.core.servlets;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.jcr.Session;
import javax.servlet.ServletException;

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
import com.heromotocorp.vida.core.config.ClientConfig;
import com.heromotocorp.vida.core.config.GlobalConfig;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import junitx.util.PrivateAccessor;

/**
 * JUnit test class for ExchangeVehicleServlet
 */
@ExtendWith(MockitoExtension.class)
@ExtendWith(AemContextExtension.class)
class ExchangeVehicleServletTest {

	private final AemContext context = new AemContext();

	private ExchangeVehicleServlet exchangeVehicleServlet = new ExchangeVehicleServlet();

	private GlobalConfig globalConfig;

	private ClientConfig clientConfig;

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

	private PrintWriter writer;

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
		writer = Mockito.mock(PrintWriter.class);
		req = Mockito.mock(SlingHttpServletRequest.class);
		resp = Mockito.mock(SlingHttpServletResponse.class);

		globalConfig = context.getService(GlobalConfig.class);
		globalConfig = mock(GlobalConfig.class);
		PrivateAccessor.setField(exchangeVehicleServlet, "globalConfig", globalConfig);

		clientConfig = context.getService(ClientConfig.class);
		clientConfig = mock(ClientConfig.class);
		PrivateAccessor.setField(exchangeVehicleServlet, "clientConfig", clientConfig);
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.servlets.ExchangeVehicleServlet#doGet(org.apache.sling.api.SlingHttpServletRequest, org.apache.sling.api.SlingHttpServletResponse)}.
	 * 
	 * @throws IOException
	 * @throws ServletException
	 */
	@Test
	void testDoGetSlingHttpServletRequestSlingHttpServletResponse() throws ServletException, IOException {
		InputStream stream = new ByteArrayInputStream(text.getBytes());
		Mockito.when(globalConfig.exchangeVehicleCSVLocationReqPath())
				.thenReturn("/content/dam/vida/config/master-brand-list.csv");
		Mockito.when(req.getResourceResolver()).thenReturn(resolver);
		Mockito.when(resolver.getResource("/content/dam/vida/config/master-brand-list.csv")).thenReturn(assetResource);
		Mockito.when(assetResource.adaptTo(Asset.class)).thenReturn(asset);
		Mockito.when(asset.getOriginal()).thenReturn(original);
		Mockito.when(original.getStream()).thenReturn(stream);
		Mockito.when(resp.getWriter()).thenReturn(writer);
		exchangeVehicleServlet.doGet(req, resp);

		Mockito.when(globalConfig.exchangeVehicleCSVLocationReqPath()).thenReturn(null);
		Mockito.when(asset.getOriginal()).thenReturn(null);
		exchangeVehicleServlet.doGet(req, resp);
		assertNotNull(exchangeVehicleServlet);
	}
}
