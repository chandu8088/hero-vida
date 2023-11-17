package com.heromotocorp.vida.core.models;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

/**
 * The type Aadhar Verification model.
 */
@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class AadharVerificationModel {

	/** The Sub text. */
	@ValueMapValue
	@Default(values = StringUtils.EMPTY)
	private String subtext;

	/**
	 * The Constant REGEX.
	 */
	public static final String REGEX = "&lt;pcnxt&gt;";

	/**
	 * The Constant REPLACESTR.
	 */
	public static final String REPLACESTR = "<br>";

	/**
	 * Get Sub text.
	 */
	public String getSubtext() {
		return subtext.replace("\n", "").replace(REGEX, REPLACESTR);
	}
}
