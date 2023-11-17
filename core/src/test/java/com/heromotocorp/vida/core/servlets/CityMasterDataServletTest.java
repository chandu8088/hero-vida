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
 * JUnit test class for CityMasterDataServlet
 */
@ExtendWith({ AemContextExtension.class, MockitoExtension.class })
class CityMasterDataServletTest {

	private final AemContext context = new AemContext();

	private CityMasterDataServlet cityMasterDataServlet = new CityMasterDataServlet();

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

	private String text = "City ,State ,Country ,Latitude,Longitude ,Branch Id,Parter Account ID\n"
			+ "New Delhi ,Delhi ,India ,28.6448,77.216721,a0Ip0000003g2lfEAA,001p00000102MzHAAU\n"
			+ "Bengaluru,Karnataka,India ,12.972442,77.580643,a0Ip0000003g9znEAA,001p00000104AZ1AAM\n";

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
		PrivateAccessor.setField(cityMasterDataServlet, "globalConfig", globalConfig);

		clientConfig = context.getService(ClientConfig.class);
		clientConfig = mock(ClientConfig.class);
		PrivateAccessor.setField(cityMasterDataServlet, "clientConfig", clientConfig);
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.servlets.CityMasterDataServlet#doGet(org.apache.sling.api.SlingHttpServletRequest, org.apache.sling.api.SlingHttpServletResponse)}.
	 * 
	 * @throws IOException
	 * @throws ServletException
	 */
	@Test
	void testDoGetSlingHttpServletRequestSlingHttpServletResponse() throws ServletException, IOException {
		InputStream stream = new ByteArrayInputStream(text.getBytes());
		Mockito.when(globalConfig.cityMasterCSVLocationPath()).thenReturn("/content/dam/vida/config/City_Master.csv");
		Mockito.when(req.getResourceResolver()).thenReturn(resolver);
		Mockito.when(resolver.getResource("/content/dam/vida/config/City_Master.csv")).thenReturn(assetResource);
		Mockito.when(assetResource.adaptTo(Asset.class)).thenReturn(asset);
		Mockito.when(asset.getOriginal()).thenReturn(original);
		Mockito.when(original.getStream()).thenReturn(stream);
		Mockito.when(resp.getWriter()).thenReturn(writer);
		cityMasterDataServlet.doGet(req, resp);

		Mockito.when(globalConfig.cityMasterCSVLocationPath()).thenReturn(null);
		Mockito.when(asset.getOriginal()).thenReturn(null);
		cityMasterDataServlet.doGet(req, resp);
		assertNotNull(cityMasterDataServlet);
	}

}
