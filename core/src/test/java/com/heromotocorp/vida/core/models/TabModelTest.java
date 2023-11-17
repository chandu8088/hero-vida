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
 * JUnit test class for TabModel
 */
@ExtendWith({ AemContextExtension.class, MockitoExtension.class })
class TabModelTest {

	private final AemContext context = new AemContext();

	private TabModel tabModel = new TabModel();

	private TabBeanModel tabBeanModel = new TabBeanModel();

	private Page page;

	private Resource resource;

	private List<TabBeanModel> tabs = new ArrayList<>();

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		tabs.add(tabBeanModel);
		page = context.create().page("/content/testvida");
		resource = context.create().resource(page, "test vida", "sling:resourceType", "vida/components/tab", "tabs",
				tabs);

		tabModel = resource.adaptTo(TabModel.class);
	}

	/**
	 * Test method for {@link com.heromotocorp.vida.core.models.TabModel#getTabs()}.
	 */
	@Test
	void testGetTabs() {
		tabModel.getTabs();
		assertNotNull(tabModel);
	}

}
