package com.heromotocorp.vida.core.models;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

/**
 * The type Product info model.
 */
@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ProductCompareModel {

	/** The taxdesc. */
	@ValueMapValue
	@Default(values = StringUtils.EMPTY)
	private String taxdesc;

	/** The effectivedesc. */
	@ValueMapValue
	@Default(values = StringUtils.EMPTY)
	private String effectivedesc;

	/** The exshowroomdesc. */
	@ValueMapValue
	@Default(values = StringUtils.EMPTY)
	private String exshowroomdesc;

	/** The fameincentivedesc. */
	@ValueMapValue
	@Default(values = StringUtils.EMPTY)
	private String fameincentivedesc;

	/** The statesubsidydesc. */
	@ValueMapValue
	@Default(values = StringUtils.EMPTY)
	private String statesubsidydesc;

	/**
	 * The Constant REGEX.
	 */
	public static final String REGEX = "&lt;pcnxt&gt;";

	/**
	 * The Constant REPLACESTR.
	 */
	public static final String REPLACESTR = "<br>";

	/**
	 * Get Tax description.
	 */
	public String getTaxDescription() {
		return taxdesc.replace("\n","").replace(REGEX, REPLACESTR);
	}

	/**
	 * Get effective description.
	 */
	public String getEffectivedesc() {
		return effectivedesc.replace("\n","").replace(REGEX, REPLACESTR);
	}

	/**
	 * Get exshowroom description.
	 */
	public String getExshowroomdesc() {
		return exshowroomdesc.replace("\n","").replace(REGEX, REPLACESTR);
	}

	/**
	 * Get fameincentive description.
	 */
	public String getFameincentivedesc() {
		return fameincentivedesc.replace("\n","").replace(REGEX, REPLACESTR);
	}

	/**
	 * Get statesubsidy description.
	 */
	public String getStatesubsidydesc() {
		return statesubsidydesc.replace("\n","").replace(REGEX, REPLACESTR);
	}
}
