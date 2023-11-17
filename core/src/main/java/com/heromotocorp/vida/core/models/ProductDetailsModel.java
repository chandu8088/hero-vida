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
 * Model to get the Product Details values.
 *
 */
@Model(adaptables = { Resource.class, SlingHttpServletRequest.class })
public class ProductDetailsModel {


	/** The resolver. */
	@ScriptVariable
	private ResourceResolver resolver;

    /** Redirection URL. */

    @ValueMapValue
    @Default(values = StringUtils.EMPTY)
    private String pdprimarybtnurl;

    /** Terms And Condition URL. */

    @ValueMapValue
    @Default(values = StringUtils.EMPTY)
    private String pdsecondarybtnurl;

    /**
	 * Gets the Primary Button url.
	 *
	 * @return the pdprimarybtnurl
	 */
	public String getPdprimarybtnurl() {
		return resolver.map(pdprimarybtnurl);
	}
	
	/**
	 * Gets the Secondary Button url.
	 *
	 * @return the pdsecondarybtnurl
	 */
	public String getPdsecondarybtnurl() {
		return resolver.map(pdsecondarybtnurl);
	}
}
