/**
 * 
 */
package com.heromotocorp.vida.core.models;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintWriter;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.day.cq.dam.api.Asset;
import com.day.cq.dam.api.AssetManager;
import com.day.cq.dam.api.Rendition;
import com.heromotocorp.vida.core.config.GlobalConfig;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;

/**
 * JUnit test class for NetworkCodesModel
 */
@ExtendWith({ AemContextExtension.class, MockitoExtension.class })
class NetworkCodesModelTest {
	
	@InjectMocks
	private NetworkCodesModel networkCodesModel;

	@Mock
	private ResourceResolver resourceResolver;

	@Mock
	private GlobalConfig globalConfig;

	private PrintWriter writer;

	@Mock
	private Resource assetResource;

	@Mock
	private Asset asset;

	@Mock
	private AssetManager assetManager;

	@Mock
	private Rendition original;

	private String text = "[{\"id\":\"10374\",\"value\":\"UPPER INDIA TRADING CO. PV. LTD\"},{\"id\":\"10376\",\"value\":\"Pashupati Motors\"},{\"id\":\"10380\",\"value\":\"Ess Aay Agencies India Pvt Ltd\"},{\"id\":\"10381\",\"value\":\"Khanna Automobiles\"},{\"id\":\"10692\",\"value\":\"Arc Motors Pvt Ltd\"},{\"id\":\"10877\",\"value\":\"Singla Automobiles\"},{\"id\":\"11003\",\"value\":\"Metro Motors\"},{\"id\":\"11105\",\"value\":\"Avni Motors Pvt Ltd.\"},{\"id\":\"11315\",\"value\":\"Aman Motors\"},{\"id\":\"11461\",\"value\":\"Shiv Ganga Automobiles\"},{\"id\":\"11780\",\"value\":\"R.K.Automobiles\"},{\"id\":\"12075\",\"value\":\"Shraman Automobiles\"},{\"id\":\"10265\",\"value\":\"Shradha Automobiles\"},{\"id\":\"10291\",\"value\":\"Bikes Auto\"},{\"id\":\"10950\",\"value\":\"Shradha Motors\"},{\"id\":\"11520\",\"value\":\"Keshav Motors\"},{\"id\":\"11028\",\"value\":\"Karnavati Bikes\"},{\"id\":\"11596\",\"value\":\"Prowheels Automotive\"},{\"id\":\"11916\",\"value\":\"Bikes Automotives\"},{\"id\":\"11979\",\"value\":\"Petal Automotion\"}]";

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		Mockito.when(globalConfig.networkCodesUrl()).thenReturn("/content/dam/vida/config/networkCodes.json");
	}

	/**
	 * Test method for {@link com.heromotocorp.vida.core.models.NetworkCodesModel#getNetworkCodes()}.
	 */
	@Test
	void testGetNetworkCodes() {
		InputStream stream = new ByteArrayInputStream(text.getBytes());
		Mockito.when(resourceResolver.getResource("/content/dam/vida/config/networkCodes.json")).thenReturn(assetResource);
		Mockito.when(assetResource.adaptTo(Asset.class)).thenReturn(asset);
		Mockito.when(asset.getOriginal()).thenReturn(original);
		Mockito.when(original.getStream()).thenReturn(stream);
		networkCodesModel.getNetworkCodes();
	}

}
