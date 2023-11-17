package com.heromotocorp.vida.core.models;

import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Source;

/**
 * The type Button Tabs bean model.
 */
@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ButtonTabsBeanModel {

	@Inject
	private String buttontext;

	@Inject
	private String sectionid;

	@Inject
	private String buttonurl;

	/** The resolver. */
	@Inject
	@Source("sling-object")
	private ResourceResolver resolver;

	/**
	 * Gets buttontext.
	 *
	 * @return the buttontext
	 */
	public String getButtontext() {
		return buttontext;
	}

	/**
	 * Gets sectionid.
	 *
	 * @return the sectionid
	 */
	public String getSectionid() {
		return sectionid;
	}

	/**
	 * Gets buttonurl.
	 *
	 * @return the buttonurl
	 */
	public String getButtonurl() {
		return resolver.map(buttonurl);
	}

}
