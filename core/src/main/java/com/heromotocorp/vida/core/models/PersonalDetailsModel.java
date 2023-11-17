package com.heromotocorp.vida.core.models;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

/**
 * The type Personal details model.
 */
@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class PersonalDetailsModel {

	/** The resolver. */
	@SlingObject
	private ResourceResolver resolver;

	/** The Aadhar Verification url. */
	@ValueMapValue
	@Default(values = StringUtils.EMPTY)
	String usermanualBtnUrl;

	/**
	 * Inits the.
	 */
	@PostConstruct
	protected void init() {
		usermanualBtnUrl = resolver.map(usermanualBtnUrl);
	}

	/**
	 * Gets the usermanualBtnUrl .
	 *
	 * @return the usermanualBtnUrl
	 */
	public String getUsermanualBtnUrl() {
		return usermanualBtnUrl;
	}
}
