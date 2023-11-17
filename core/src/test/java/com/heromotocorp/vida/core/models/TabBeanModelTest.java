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
 * JUnit test class for TabBeanModel
 */
@ExtendWith({ AemContextExtension.class, MockitoExtension.class })
class TabBeanModelTest {

	private final AemContext context = new AemContext();

	private TabBeanModel tabBeanModel = new TabBeanModel();

	private Page page;

	private Resource resource;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		page = context.create().page("/content/testvida");
		resource = context.create().resource(page, "test vida", "sling:resourceType", "vida/components/tab", "label",
				"label", "hidelabel", true, "iconType", "iconType");

		tabBeanModel = resource.adaptTo(TabBeanModel.class);
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.TabBeanModel#getLabel()}.
	 */
	@Test
	void testGetLabel() {
		assertEquals("label", tabBeanModel.getLabel());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.TabBeanModel#getIconType()}.
	 */
	@Test
	void testGetIconType() {
		assertEquals("iconType", tabBeanModel.getIconType());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.TabBeanModel#getHidelabel()}.
	 */
	@Test
	void testGetHidelabel() {
		assertEquals(true, tabBeanModel.getHidelabel());
	}

}
