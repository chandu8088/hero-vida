package com.heromotocorp.vida.core.models;


import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

/**
 * Model to get the multifield values of Login.
 *
 */
@Model(adaptables = { Resource.class, SlingHttpServletRequest.class })
public class LoginModel {


	/** The resolver. */
	@ScriptVariable
	private ResourceResolver resolver;

    /** Redirection URL. */

    @ValueMapValue
    @Default(values = StringUtils.EMPTY)
    private String redirectionUrl;

    /** Terms And Condition URL. */

    @ValueMapValue
    @Default(values = StringUtils.EMPTY)
    private String termsAndConditionUrl;



	/**
	 * Gets the redirection url.
	 *
	 * @return the redirection url
	 */
	public String getRedirectionUrl() {
		return resolver.map(redirectionUrl);
	}

	/**
	 * Gets the terms and condition url.
	 *
	 * @return the terms and condition url
	 */
	public String getTermsAndConditionUrl() {
		return resolver.map(termsAndConditionUrl);
	}


}
