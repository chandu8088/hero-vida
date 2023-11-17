package com.heromotocorp.vida.core.models;

import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

/**
 * The type Static Product Specification bean model.
 */
@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class StaticProductSpecBeanModel {

	@Inject
	private String specificationheading;

	@Inject
	private boolean enablenumeric;

	@Inject
	private int datanumeric;

	@Inject
	private float datadecimal;

	@Inject
	private String dataunit;

	/**
	 * Gets specificationheading.
	 *
	 * @return the specificationheading
	 */
	public String getSpecificationheading() {
		return specificationheading;
	}

	/**
	 * Gets enablenumeric.
	 *
	 * @return the enablenumeric
	 */
	public boolean isEnablenumeric() {
		return enablenumeric;
	}

	/**
	 * Gets datanumeric.
	 *
	 * @return the datanumeric
	 */
	public int getDatanumeric() {
		return datanumeric;
	}

	/**
	 * Gets datadecimal.
	 *
	 * @return the datadecimal
	 */
	public float getDatadecimal() {
		return datadecimal;
	}

	/**
	 * Gets dataunit.
	 *
	 * @return the dataunit
	 */
	public String getDataunit() {
		return dataunit;
	}

}
