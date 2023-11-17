package com.heromotocorp.vida.core.config.impl;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

import com.heromotocorp.vida.core.config.CampaignGlobalConfig;

/**
 * Osgi Config for Campaign Global Configuration.
 *
 */
@Component(service = CampaignGlobalConfig.class, immediate = true)
@Designate(ocd = CampaignGlobalConfigImpl.ServiceConfig.class)
public class CampaignGlobalConfigImpl implements CampaignGlobalConfig {

	/**
	 * The Interface ServiceConfig.
	 */
	@ObjectClassDefinition(name = "Campaign Global Config", description = "Campaign Global Config")
	public @interface ServiceConfig {

		/**
		 * Geo data url.
		 *
		 * @return the string
		 */
		@AttributeDefinition(name = "Nominee Form Submit URL", description = "URL to get data of Nominee Form", type = AttributeType.STRING)
		public String formSubmitUrl();

	}

	/** The form submit url. */
	private String formSubmitUrl;

	/**
	 * Activate Method.
	 *
	 * @param serviceConfig the service config
	 */
	@Activate
	protected void activate(ServiceConfig serviceConfig) {

		formSubmitUrl = serviceConfig.formSubmitUrl();
	}

	/**
	 * Form submit Url.
	 *
	 * @return the string
	 */
	@Override
	public String formSubmitURL() {
		return formSubmitUrl;
	}
}