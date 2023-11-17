package com.heromotocorp.vida.core.config.impl;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

import com.heromotocorp.vida.core.config.AutovertConfig;

/**
 * Osgi config for Autovert.
 *
 */
@Component(service = AutovertConfig.class, immediate = true)
@Designate(ocd = AutovertConfigImpl.ServiceConfig.class)
public class AutovertConfigImpl implements AutovertConfig {

	/**
	 * The Interface ServiceConfig.
	 */
	@ObjectClassDefinition(name = "Autovert Config", description = "Autovert Config")
	public @interface ServiceConfig {

		/**
		 * Server name.
		 *
		 * @return the string
		 */
		@AttributeDefinition(name = "Auth Header", description = "Auth Header", type = AttributeType.STRING)
		public String authHeader();

		/**
		 * Server name.
		 *
		 * @return the string
		 */
		@AttributeDefinition(name = "Autovert Client Secret", description = "Autovert Client Secret", type = AttributeType.STRING)
		public String autovertClientSecret();

	}

	/** The auth Header. */
	private String authHeader;

	/** The autovert client secret. */
	private String autovertClientSecret;

	/**
	 * Activate.
	 *
	 * @param serviceConfig the service config
	 */
	@Activate
	protected void activate(ServiceConfig serviceConfig) {
		authHeader = serviceConfig.authHeader();
		autovertClientSecret = serviceConfig.autovertClientSecret();
	}

	/**
	 * authHeader.
	 *
	 * @return the string
	 */
	@Override
	public String authHeader() {
		return authHeader;
	}

	/**
	 * autovertClientSecret
	 *
	 * @return the string
	 */
	@Override
	public String autovertClientSecret() {
		return autovertClientSecret;
	}
}