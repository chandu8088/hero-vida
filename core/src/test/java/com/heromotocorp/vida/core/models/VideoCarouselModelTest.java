/**
 * 
 */
package com.heromotocorp.vida.core.models;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.apache.sling.api.resource.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.day.cq.wcm.api.Page;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;

/**
 * JUnit test class for VideoCarouselModel
 */
@ExtendWith({ AemContextExtension.class, MockitoExtension.class })
class VideoCarouselModelTest {

	private final AemContext context = new AemContext();

	private VideoCarouselModel videoCarouselModel = new VideoCarouselModel();

	private Page page;

	private Resource resource;

	private VideoCarouselBeanModel videoCarouselBeanModel = new VideoCarouselBeanModel();

	private List<VideoCarouselBeanModel> videopaths = new ArrayList<>();

	private VideoCarouselModel videoCarouselModel1 = new VideoCarouselModel();

	private Page page1;

	private Resource resource1;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		videopaths.add(videoCarouselBeanModel);
		page = context.create().page("/content/samplevida");
		resource = context.create().resource(page, "sample vida", "sling:resourceType", "vida/components/videocarousel",
				"videopaths", videopaths);

		videoCarouselModel = resource.adaptTo(VideoCarouselModel.class);

		page1 = context.create().page("/content/vida");
		resource1 = context.create().resource(page1, "vida", "sling:resourceType", "vida/components/videocarousel",
				"videopaths", null);

		videoCarouselModel1 = resource1.adaptTo(VideoCarouselModel.class);

	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.VideoCarouselModel#getVideopaths()}.
	 */
	@Test
	void testGetVideopaths() {
		videoCarouselModel.getVideopaths();
		assertNotNull(videoCarouselModel);

		videoCarouselModel1.getVideopaths();
	}

}
