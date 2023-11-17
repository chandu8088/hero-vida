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
 * JUnit test class for HighlightCardCarouselBeanModel
 */
@ExtendWith({ AemContextExtension.class, MockitoExtension.class })
class HighlightCardCarouselBeanModelTest {

	private final AemContext context = new AemContext();

	private Page page;

	private Resource resource;

	private HighlightCardCarouselBeanModel highlightCardCarouselBeanModel = new HighlightCardCarouselBeanModel();

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		page = context.create().page("/content/samplevida");
		resource = context.create().resource(page, "sample vida", "sling:resourceType",
				"vida/components/highlightcardcarousel", "heading", "heading", "description", "description",
				"imagepath", "imagepath", "alttext", "alttext");

		highlightCardCarouselBeanModel = resource.adaptTo(HighlightCardCarouselBeanModel.class);

	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.HighlightCardCarouselBeanModel#getHeading()}.
	 */
	@Test
	void testGetHeading() {
		highlightCardCarouselBeanModel.getHeading();
		assertEquals("heading", highlightCardCarouselBeanModel.getHeading());

	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.HighlightCardCarouselBeanModel#getDescription()}.
	 */
	@Test
	void testGetDescription() {
		highlightCardCarouselBeanModel.getDescription();
		assertEquals("description", highlightCardCarouselBeanModel.getDescription());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.HighlightCardCarouselBeanModel#getImagepath()}.
	 */
	@Test
	void testGetImagepath() {
		highlightCardCarouselBeanModel.getImagepath();
		assertEquals("imagepath", highlightCardCarouselBeanModel.getImagepath());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.HighlightCardCarouselBeanModel#getAlttext()}.
	 */
	@Test
	void testGetAlttext() {
		highlightCardCarouselBeanModel.getAlttext();
		assertEquals("alttext", highlightCardCarouselBeanModel.getAlttext());
	}

}
