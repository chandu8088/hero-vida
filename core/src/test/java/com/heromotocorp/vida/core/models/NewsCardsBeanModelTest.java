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
 * JUnit test class for NewsCardsBeanModel
 */
@ExtendWith({ AemContextExtension.class, MockitoExtension.class })
class NewsCardsBeanModelTest {

	private final AemContext context = new AemContext();

	private NewsCardsBeanModel newsCardsBeanModel = new NewsCardsBeanModel();

	private Page page;

	private Resource resource;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {

		page = context.create().page("/content/testvida");
		resource = context.create().resource(page, "test vida", "sling:resourceType", "vida/components/newscards",
				"imagepath", "imagepath", "imagealttext", "imagealttext", "newsheading", "newsheading", "source",
				"source", "newsdate", "newsdate");

		newsCardsBeanModel = resource.adaptTo(NewsCardsBeanModel.class);
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.NewsCardsBeanModel#getImagepath()}.
	 */
	@Test
	void testGetImagepath() {
		assertEquals("imagepath", newsCardsBeanModel.getImagepath());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.NewsCardsBeanModel#getImagealttext()}.
	 */
	@Test
	void testGetImagealttext() {
		assertEquals("imagealttext", newsCardsBeanModel.getImagealttext());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.NewsCardsBeanModel#getNewsheading()}.
	 */
	@Test
	void testGetNewsheading() {
		assertEquals("newsheading", newsCardsBeanModel.getNewsheading());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.NewsCardsBeanModel#getSource()}.
	 */
	@Test
	void testGetSource() {
		assertEquals("source", newsCardsBeanModel.getSource());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.NewsCardsBeanModel#getNewsdate()}.
	 */
	@Test
	void testGetNewsdate() {
		assertEquals("newsdate", newsCardsBeanModel.getNewsdate());
	}

}
