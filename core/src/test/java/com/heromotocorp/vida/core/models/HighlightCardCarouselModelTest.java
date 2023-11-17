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
 * JUnit test class for HighlightCardCarouselModel
 */
@ExtendWith({ AemContextExtension.class, MockitoExtension.class })
class HighlightCardCarouselModelTest {

	private final AemContext context = new AemContext();

	private HighlightCardCarouselModel highlightCardCarouselModel = new HighlightCardCarouselModel();

	private Page page;

	private Resource resource;

	private Page page1;

	private Resource resource1;

	private HighlightCardCarouselModel highlightCardCarouselModel1 = new HighlightCardCarouselModel();

	private HighlightCardCarouselBeanModel highlightCardCarouselBeanModel = new HighlightCardCarouselBeanModel();

	private List<HighlightCardCarouselBeanModel> items = new ArrayList<>();

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		items.add(highlightCardCarouselBeanModel);
		page = context.create().page("/content/vida");
		resource = context.create().resource(page, "vida", "sling:resourceType",
				"vida/components/highlightcardcarousel", "items", items);

		highlightCardCarouselModel = resource.adaptTo(HighlightCardCarouselModel.class);

		page1 = context.create().page("/content/samplevida");
		resource1 = context.create().resource(page1, "Sample vida", "sling:resourceType",
				"vida/components/highlightcardcarousel", "items", null);

		highlightCardCarouselModel1 = resource1.adaptTo(HighlightCardCarouselModel.class);
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.HighlightCardCarouselModel#getItems()}.
	 */
	@Test
	void testGetItems() {
		assertNotNull(highlightCardCarouselModel.getItems());

		highlightCardCarouselModel1.getItems();
	}

}
