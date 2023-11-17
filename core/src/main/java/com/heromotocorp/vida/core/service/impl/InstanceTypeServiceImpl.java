package com.heromotocorp.vida.core.service.impl;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

import com.heromotocorp.vida.core.service.InstanceTypeService;

/**
 * The Class InstanceTypeServiceImpl.
 */
@Component(service = InstanceTypeService.class)
@Designate(ocd = InstanceTypeServiceImpl.Config.class)
public class InstanceTypeServiceImpl implements InstanceTypeService {

	/** The environment. */
	private String environment;

	/** The instance. */
	private String instance;

	/**
	 * The Interface Config.
	 */
	@ObjectClassDefinition(name = "Instance Type Service Details", description = "Instance Type Service values")
	public static @interface Config {

		/**
		 * Environment.
		 *
		 * @return the string
		 */
		@AttributeDefinition(name = "Environment", type = AttributeType.STRING)
		String environment() ;

		/**
		 * Instance.
		 *
		 * @return the string
		 */
		@AttributeDefinition(name = "Instance", type = AttributeType.STRING)
		String instance();
	}

	/**
	 * Activate.
	 *
	 * @param config the config
	 */
	@Activate
	protected void activate(Config config) {
		environment = config.environment();
		instance = config.instance();

	}

	/**
	 * Gets the environment.
	 *
	 * @return the environment
	 */
	@Override
	public String getEnvironment() {
		return environment;
	}

	/**
	 * Gets the single instance of InstanceTypeServiceImpl.
	 *
	 * @return single instance of InstanceTypeServiceImpl
	 */
	@Override
	public String getInstance() {
		return instance;
	}
}