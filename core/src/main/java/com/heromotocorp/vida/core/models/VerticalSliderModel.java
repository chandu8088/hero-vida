package com.heromotocorp.vida.core.models;

import java.util.Collections;
import java.util.List;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;

/**
 * Model to get the multifield values of Vertical Slider.
 *
 */
@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class VerticalSliderModel {

	/**
	 * To get all the child nodes of Vertical Slider field.
	 *
	 */
	@ChildResource
	private List<VerticalSliderBeanModel> verticalSlider;

	public List<VerticalSliderBeanModel> getVerticalSlider() {
		return Collections.unmodifiableList(verticalSlider);
	}
}
