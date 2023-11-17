package com.heromotocorp.vida.core.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

/**
 * The main Static Product Specification.
 */
@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class StaticProductSpecModel {

	/**
	 * Taking the Static Product Specification data in the below list.
	 */
	@Inject
	private List<StaticProductSpecBeanModel> staticspecifications;

	public List<StaticProductSpecBeanModel> getItems() {
		return staticspecifications != null ? new ArrayList<>(staticspecifications) : Collections.emptyList();
	}

}
