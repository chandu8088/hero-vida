package com.heromotocorp.vida.core.models;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;



/**
 * Bean model to get all the child nodes of Tabs.
 *
 */
@Model(adaptables = Resource.class,
defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class TabBeanModel {
	
	/**
	 * label.
	 */
	@ValueMapValue
	@Default(values = StringUtils.EMPTY)
    private String label;
	
	/**
	 * label.
	 */
	@ValueMapValue
	@Default(values = "false")
    private boolean hidelabel;
	
	/**
	 * iconType.
	 */
	@ValueMapValue
	@Default(values = StringUtils.EMPTY)
    private String iconType;

	
	/**
	 * Gets Label
	 * 
	 * @return label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Gets iconType
	 * 
	 * @return iconType
	 */
	public String getIconType() {
		return iconType;
	}

	/**
	 * Gets hidelabel
	 * 
	 * @return hidelabel
	 */
	public boolean getHidelabel() {
		return hidelabel;
	}

}
