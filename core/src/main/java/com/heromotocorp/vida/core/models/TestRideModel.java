package com.heromotocorp.vida.core.models;

import com.heromotocorp.vida.core.utils.Constants;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;

import java.util.Objects;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

/**
 * The class Test ride model is to get the country codes and convert them into
 * an array of String.
 */
@Model(adaptables = { Resource.class, SlingHttpServletRequest.class },
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class TestRideModel {

	/** The countrycode. */
	@Inject
	private String countrycode;
	
    /** The profilepageurl. */
    @Inject
    private String profilepageurl;
    
    /** The short term redirect URL. */
    @Inject
    private String shortTermRedirectURL;
    
    /** The long term redirect URL. */
    @Inject
    private String longTermRedirectURL;

	/** The country codes. */
	private String[] countryCodes;
	
	/** The resolver. */
	@ScriptVariable
	private ResourceResolver resolver;

	/**
	 * Init method is to split the countrycode String and store it in the array.
	 */
	@PostConstruct
	protected void init() {
		if (Objects.nonNull(countrycode)) {
			countryCodes = countrycode.split(Constants.COMA);
		} 
	}

	/**
	 * Get country codes string [ ].
	 *
	 * @return the string [ ]
	 */
	public String[] getCountryCodes() {
		return countryCodes != null ? countryCodes.clone() : new String[0] ;
	}
	
	/**
	 * Gets the profilepageurl.
	 *
	 * @return the profilepageurl
	 */
	public String getProfilepageurl() {
		return resolver.map(profilepageurl);
	}

	/**
	 * Gets the short term redirect URL.
	 *
	 * @return the short term redirect URL
	 */
	public String getShortTermRedirectURL() {
		return resolver.map(shortTermRedirectURL);
	}

	/**
	 * Gets the long term redirect URL.
	 *
	 * @return the long term redirect URL
	 */
	public String getLongTermRedirectURL() {
		return resolver.map(longTermRedirectURL);
	}

}
