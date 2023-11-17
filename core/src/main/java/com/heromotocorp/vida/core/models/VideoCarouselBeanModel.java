package com.heromotocorp.vida.core.models;

import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import com.heromotocorp.vida.core.utils.CommonUtils;

/**
 * The type Video carousel bean model.
 */
@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class VideoCarouselBeanModel {

	@Inject
	private String videoPath;

	/**
	 * Gets videoPath.
	 *
	 * @return the videoPath
	 */
	public String getVideoPath() {
		return videoPath;
	}

	/**
	 * Gets the url starting string.
	 *
	 * @return the true if url starting string is https
	 */
	public boolean getIsExternalBtnURL() {
		return CommonUtils.getIsExternalURL(videoPath);
	}
}
