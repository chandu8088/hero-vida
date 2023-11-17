/**
 * 
 */
package com.heromotocorp.vida.core.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.apache.sling.api.resource.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.day.cq.wcm.api.Page;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;

/**
 * JUnit test class for VideoCarouselBeanModel
 */
@ExtendWith({ AemContextExtension.class, MockitoExtension.class })
class VideoCarouselBeanModelTest {

	private final AemContext context = new AemContext();

	private VideoCarouselBeanModel videoCarouselBeanModel = new VideoCarouselBeanModel();

	private Page page;

	private Resource resource;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		page = context.create().page("/content/samplevida");
		resource = context.create().resource(page, "sample vida", "sling:resourceType", "vida/components/videocarousel",
				"videoPath", "https://www.com");

		videoCarouselBeanModel = resource.adaptTo(VideoCarouselBeanModel.class);

	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.VideoCarouselBeanModel#getVideoPath()}.
	 */
	@Test
	void testGetVideoPath() {
		assertEquals("https://www.com", videoCarouselBeanModel.getVideoPath());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.VideoCarouselBeanModel#getIsExternalBtnURL()}.
	 */
	@Test
	void testGetIsExternalBtnURL() {
		assertEquals(true, videoCarouselBeanModel.getIsExternalBtnURL());
	}

}
