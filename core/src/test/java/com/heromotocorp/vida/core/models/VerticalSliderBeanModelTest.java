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
 * JUnit test class for VerticalSliderBeanModel
 */
@ExtendWith({ AemContextExtension.class, MockitoExtension.class })
class VerticalSliderBeanModelTest {

	private final AemContext context = new AemContext();

	private VerticalSliderBeanModel verticalSliderBeanModel = new VerticalSliderBeanModel();

	private Page page;

	private Resource resource;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		page = context.create().page("/content/samplevida");
		resource = context.create().resource(page, "sample vida", "sling:resourceType",
				"vida/components/verticalslider", "image", "image", "fullScreenImageMob", "fullScreenImageMob",
				"variations", "variations", "fullScreenvideo", "fullScreenvideo", "mobScreenVideo", "mobScreenVideo",
				"title", "title", "desc", "desc");

		verticalSliderBeanModel = resource.adaptTo(VerticalSliderBeanModel.class);
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.VerticalSliderBeanModel#getImage()}.
	 */
	@Test
	void testGetImage() {
		assertEquals("image", verticalSliderBeanModel.getImage());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.VerticalSliderBeanModel#getTitle()}.
	 */
	@Test
	void testGetTitle() {
		assertEquals("title", verticalSliderBeanModel.getTitle());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.VerticalSliderBeanModel#getDesc()}.
	 */
	@Test
	void testGetDesc() {
		assertEquals("desc", verticalSliderBeanModel.getDesc());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.VerticalSliderBeanModel#getId()}.
	 */
	@Test
	void testGetId() {
		assertEquals("title", verticalSliderBeanModel.getId());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.VerticalSliderBeanModel#getFullScreenImageMob()}.
	 */
	@Test
	void testGetFullScreenImageMob() {
		assertEquals("fullScreenImageMob", verticalSliderBeanModel.getFullScreenImageMob());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.VerticalSliderBeanModel#getVariations()}.
	 */
	@Test
	void testGetVariations() {
		assertEquals("variations", verticalSliderBeanModel.getVariations());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.VerticalSliderBeanModel#getFullScreenvideo()}.
	 */
	@Test
	void testGetFullScreenvideo() {
		assertEquals("fullScreenvideo", verticalSliderBeanModel.getFullScreenvideo());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.VerticalSliderBeanModel#getMobScreenVideo()}.
	 */
	@Test
	void testGetMobScreenVideo() {
		assertEquals("mobScreenVideo", verticalSliderBeanModel.getMobScreenVideo());
	}

}
