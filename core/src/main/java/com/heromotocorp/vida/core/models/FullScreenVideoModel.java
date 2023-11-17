package com.heromotocorp.vida.core.models;

import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;

/**
 * The Class FullScreenVideoModel.
 */
@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class FullScreenVideoModel {
	/**
	 * Taking the Primary Navigation data in the below list.
	 */
	@Inject
	private String mobScreenVideo;

	/**
	 * Gets the mob screenvideo.
	 *
	 * @return the mob screenvideo
	 */
	public String getMobScreenVideo() {
		return mobScreenVideo;
	}

}
