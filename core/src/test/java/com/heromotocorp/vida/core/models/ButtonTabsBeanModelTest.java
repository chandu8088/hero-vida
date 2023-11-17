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
 * JUnit test class for ButtonTabsBeanModel
 */
@ExtendWith({ AemContextExtension.class, MockitoExtension.class })
class ButtonTabsBeanModelTest {

	private final AemContext context = new AemContext();

	private ButtonTabsBeanModel buttonTabsBeanModel = new ButtonTabsBeanModel();

	private Page page;

	private Resource resource;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		page = context.create().page("/content/vida");
		resource = context.create().resource(page, "vida", "sling:resourceType", "vida/components/buttontabs",
				"buttonurl", "buttonurl", "sectionid", "sectionid", "buttontext", "buttontext");

		buttonTabsBeanModel = resource.adaptTo(ButtonTabsBeanModel.class);
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.ButtonTabsBeanModel#getButtontext()}.
	 */
	@Test
	void testGetButtontext() {
		buttonTabsBeanModel.getButtontext();

		assertEquals("buttontext", buttonTabsBeanModel.getButtontext());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.ButtonTabsBeanModel#getSectionid()}.
	 */
	@Test
	void testGetSectionid() {
		buttonTabsBeanModel.getSectionid();

		assertEquals("sectionid", buttonTabsBeanModel.getSectionid());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.ButtonTabsBeanModel#getButtonurl()}.
	 */
	@Test
	void testGetButtonurl() {
		buttonTabsBeanModel.getButtonurl();

		assertEquals("buttonurl", buttonTabsBeanModel.getButtonurl());
	}

}
