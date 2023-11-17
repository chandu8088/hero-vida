package com.heromotocorp.vida.core.vo;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

/**
 * Bean model to get all the child nodes of Hotspot.
 *
 */
@Model(adaptables = Resource.class, 
		defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class HotspotBeanModel {

	/**
	 * imagepath.
	 */
	@ValueMapValue
	@Default(values = StringUtils.EMPTY)
	private String imagepath;

	/**
	 * imagealttext.
	 */
	@ValueMapValue
	@Default(values = StringUtils.EMPTY)
	private String imagealttext;

	/**
	 * itemTitle.
	 */
	@ValueMapValue
	@Default(values = StringUtils.EMPTY)
	private String itemTitle;

	/**
	 * itemDesc.
	 */
	@ValueMapValue
	@Default(values = StringUtils.EMPTY)
	private String itemDesc;
	public String getImagepath() {
		return imagepath;
	}

	public String getImagealttext() {
		return imagealttext;
	}

	public String getItemTitle() {
		return itemTitle;
	}

	public String getItemDesc() {
		return itemDesc;
	}

}
