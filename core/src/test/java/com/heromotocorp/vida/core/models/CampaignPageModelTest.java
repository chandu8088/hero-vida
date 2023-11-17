/**
 * 
 */
package com.heromotocorp.vida.core.models;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.heromotocorp.vida.core.config.CampaignGlobalConfig;

import io.wcm.testing.mock.aem.junit5.AemContextExtension;

/**
 * JUnit test class for CampaignPageModel
 */
@ExtendWith({ AemContextExtension.class, MockitoExtension.class })
class CampaignPageModelTest {

	@InjectMocks
	private CampaignPageModel campaignPageModel;

	@Mock
	private CampaignGlobalConfig campaignGlobalConfig;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.CampaignPageModel#init()}.
	 */
	@Test
	void testInit() {
		Mockito.when(campaignGlobalConfig.formSubmitURL()).thenReturn("formSubmitURL");
		campaignPageModel.init();
		assertNotNull(campaignPageModel);
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.CampaignPageModel#getCampaignGlobalService()}.
	 */
	@Test
	void testGetCampaignGlobalService() {
		campaignPageModel.getCampaignGlobalService();
		assertNotNull(campaignPageModel);
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.CampaignPageModel#getFormSubmitUrl()}.
	 */
	@Test
	void testGetFormSubmitUrl() {
		campaignPageModel.getFormSubmitUrl();
		assertNotNull(campaignPageModel);
	}

}
