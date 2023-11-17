package com.heromotocorp.vida.core.models;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

/**
 * Bean model to get all the child nodes of Vertical Slider.
 *
 */
@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class VerticalSliderBeanModel {

	/**
	 * image.
	 */
	@ValueMapValue
	@Default(values = StringUtils.EMPTY)
	private String image;

	/** The full screen image mob. */
	@ValueMapValue
	@Default(values = StringUtils.EMPTY)
	private String fullScreenImageMob;

	/** The variations. */
	@ValueMapValue
	@Default(values = StringUtils.EMPTY)
	private String variations;

	/** The full screenvideo. */
	@ValueMapValue
	@Default(values = StringUtils.EMPTY)
	private String fullScreenvideo;

	/** The mob screen video. */
	@ValueMapValue
	@Default(values = StringUtils.EMPTY)
	private String mobScreenVideo;

	/**
	 * title.
	 */
	@ValueMapValue
	@Default(values = StringUtils.EMPTY)
	private String title;

	/**
	 * desc.
	 */
	@ValueMapValue
	@Default(values = StringUtils.EMPTY)
	private String desc;

	/** The id. */
	private String id;

	/**
	 * Gets Images.
	 * 
	 * @return image
	 */
	public String getImage() {
		return image;
	}

	/**
	 * Gets Title.
	 * 
	 * @return title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Gets Desc.
	 * 
	 * @return desc
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		id = title.replaceAll(" ", "-");
		return id;
	}

	/**
	 * Gets the full screen image mob.
	 *
	 * @return the full screen image mob
	 */
	public String getFullScreenImageMob() {
		return fullScreenImageMob;
	}

	/**
	 * Gets the variations.
	 *
	 * @return the variations
	 */
	public String getVariations() {
		return variations;
	}

	/**
	 * Gets the full screenvideo.
	 *
	 * @return the full screenvideo
	 */
	public String getFullScreenvideo() {
		return fullScreenvideo;
	}

	/**
	 * Gets the mob screen video.
	 *
	 * @return the mob screen video
	 */
	public String getMobScreenVideo() {
		return mobScreenVideo;
	}

}
