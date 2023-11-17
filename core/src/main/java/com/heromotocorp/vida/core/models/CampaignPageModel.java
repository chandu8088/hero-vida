package com.heromotocorp.vida.core.models;

import javax.annotation.PostConstruct;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;

import com.heromotocorp.vida.core.config.CampaignGlobalConfig;

/**
 * The Class CampaignPageModel.
 */
@Model(adaptables = { Resource.class, SlingHttpServletRequest.class })
public class CampaignPageModel {

	/** The form submit url. */
	String formSubmitUrl;

	/** The campaign global config. */
	@OSGiService
	private CampaignGlobalConfig campaignGlobalConfig;

	/**
	 * Inits the.
	 */
	@PostConstruct
	protected void init() {
		formSubmitUrl = campaignGlobalConfig.formSubmitURL();
	}

	/**
	 * Gets the campaign global service.
	 *
	 * @return the global service
	 */
	public CampaignGlobalConfig getCampaignGlobalService() {
		return campaignGlobalConfig;
	}

	/**
	 * Gets the form submit url.
	 *
	 * @return the form submit url
	 */
	public String getFormSubmitUrl() {
		return formSubmitUrl;
	}
}
