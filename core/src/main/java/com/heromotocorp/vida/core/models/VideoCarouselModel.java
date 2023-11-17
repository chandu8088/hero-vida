package com.heromotocorp.vida.core.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

/**
 * This is the main VideoCarousel model
 */
@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class VideoCarouselModel {

	/**
	 * Video link.
	 */
	@Inject
	private List<VideoCarouselBeanModel> videopaths;

	public List<VideoCarouselBeanModel> getVideopaths() {
		return videopaths != null ? new ArrayList<>(videopaths) : Collections.emptyList();
	}

}
