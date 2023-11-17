/**
 * 
 */
package com.heromotocorp.vida.core.config.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;

/**
 * JUnit test class for CampaignGlobalConfigImpl
 */
@ExtendWith(AemContextExtension.class)
class CampaignGlobalConfigImplTest {

	private final AemContext context = new AemContext();

	private CampaignGlobalConfigImpl campaignGlobalConfigImpl = new CampaignGlobalConfigImpl();

	private CampaignGlobalConfigImpl.ServiceConfig serviceConfig;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		campaignGlobalConfigImpl = context.registerService(new CampaignGlobalConfigImpl());
		serviceConfig = Mockito.mock(CampaignGlobalConfigImpl.ServiceConfig.class);

		Mockito.when(serviceConfig.formSubmitUrl()).thenReturn("formSubmitUrl");
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.CampaignGlobalConfigImpl#activate(com.heromotocorp.vida.core.config.impl.CampaignGlobalConfigImpl.ServiceConfig)}.
	 */
	@Test
	void testActivate() {
		campaignGlobalConfigImpl.activate(serviceConfig);
		assertNotNull(campaignGlobalConfigImpl);
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.config.impl.CampaignGlobalConfigImpl#formSubmitURL()}.
	 */
	@Test
	void testFormSubmitURL() {
		assertEquals(null, campaignGlobalConfigImpl.formSubmitURL());
	}

}
