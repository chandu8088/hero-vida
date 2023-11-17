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
 * JUnit test class for BannerCarouselBeanModel
 */
@ExtendWith({ AemContextExtension.class, MockitoExtension.class })
class BannerCarouselBeanModelTest {

	private final AemContext context = new AemContext();

	private BannerCarouselBeanModel bannerCarouselBeanModel = new BannerCarouselBeanModel();

	private Page page;

	private Resource resource;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		page = context.create().page("/content/vida");
		resource = context.create().resource(page, "vida", "sling:resourceType", "vida/components/bannercarousel",
				"heading", "Sample Heading", "subheading", "Sample Sub Heading", "description", "Sample Description",
				"itemType", "Sample Item Type", "imagepath", "Sample Image Path", "imagealttext",
				"Sample Image alt text", "videopath", "Sample Video Path");

		bannerCarouselBeanModel = resource.adaptTo(BannerCarouselBeanModel.class);
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.BannerCarouselBeanModel#getHeading()}.
	 */
	@Test
	void testGetHeading() {
		bannerCarouselBeanModel.getHeading();

		assertEquals("Sample Heading", bannerCarouselBeanModel.getHeading());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.BannerCarouselBeanModel#getSubheading()}.
	 */
	@Test
	void testGetSubheading() {
		bannerCarouselBeanModel.getSubheading();

		assertEquals("Sample Sub Heading", bannerCarouselBeanModel.getSubheading());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.BannerCarouselBeanModel#getDescription()}.
	 */
	@Test
	void testGetDescription() {
		bannerCarouselBeanModel.getDescription();

		assertEquals("Sample Description", bannerCarouselBeanModel.getDescription());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.BannerCarouselBeanModel#getItemType()}.
	 */
	@Test
	void testGetItemType() {
		bannerCarouselBeanModel.getItemType();

		assertEquals("Sample Item Type", bannerCarouselBeanModel.getItemType());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.BannerCarouselBeanModel#getImagepath()}.
	 */
	@Test
	void testGetImagepath() {
		bannerCarouselBeanModel.getImagepath();

		assertEquals("Sample Image Path", bannerCarouselBeanModel.getImagepath());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.BannerCarouselBeanModel#getImagealttext()}.
	 */
	@Test
	void testGetImagealttext() {
		bannerCarouselBeanModel.getImagealttext();

		assertEquals("Sample Image alt text", bannerCarouselBeanModel.getImagealttext());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.BannerCarouselBeanModel#getVideopath()}.
	 */
	@Test
	void testGetVideopath() {
		bannerCarouselBeanModel.getVideopath();

		assertEquals("Sample Video Path", bannerCarouselBeanModel.getVideopath());
	}

}
