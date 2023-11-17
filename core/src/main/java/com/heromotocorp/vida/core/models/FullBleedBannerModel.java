package com.heromotocorp.vida.core.models;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.heromotocorp.vida.core.utils.CommonUtils;

@Model(adaptables = Resource.class)
public class FullBleedBannerModel {

	/** The button URL. */
	@ValueMapValue
	@Default(values = StringUtils.EMPTY)
	private String btnurl;

	/**
	 * Gets the url starting string.
	 *
	 * @return the true if url starting string is https
	 */
	public boolean getIsExternalBtnURL() {
		return CommonUtils.getIsExternalURL(btnurl);
	}
}
