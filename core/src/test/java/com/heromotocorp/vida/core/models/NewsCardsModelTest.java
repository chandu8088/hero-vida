/**
 * 
 */
package com.heromotocorp.vida.core.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
 * JUnit test class for NewsCardsModel
 */
@ExtendWith({ AemContextExtension.class, MockitoExtension.class })
class NewsCardsModelTest {

	private final AemContext context = new AemContext();

	private NewsCardsModel newsCardsModel = new NewsCardsModel();

	private NewsCardsModel newsCardsModel1 = new NewsCardsModel();

	private Page page;

	private Resource resource;

	private Page page1;

	private Resource resource1;

	private List<NewsCardsBeanModel> items = new ArrayList<>();

	private NewsCardsBeanModel newsCardsBeanModel = new NewsCardsBeanModel();

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		items.add(newsCardsBeanModel);
		page = context.create().page("/content/vida");
		resource = context.create().resource(page, "vida", "sling:resourceType", "vida/components/newscards", "heading",
				"heading", "items", items);

		newsCardsModel = resource.adaptTo(NewsCardsModel.class);

		page1 = context.create().page("/content/samplevida");
		resource1 = context.create().resource(page1, "Sample vida", "sling:resourceType", "vida/components/newscards",
				"heading", "heading", "items", null);

		newsCardsModel1 = resource1.adaptTo(NewsCardsModel.class);
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.NewsCardsModel#getHeading()}.
	 */
	@Test
	void testGetHeading() {
		assertEquals("heading", newsCardsModel.getHeading());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.NewsCardsModel#getItems()}.
	 */
	@Test
	void testGetItems() {
		assertNotNull(newsCardsModel.getItems());

		newsCardsModel1.getItems();
	}

}
