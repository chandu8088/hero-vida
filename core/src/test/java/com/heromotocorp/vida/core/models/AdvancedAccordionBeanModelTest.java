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
 * JUnit test class for AdvancedAccordionBeanModel
 */
@ExtendWith({ AemContextExtension.class, MockitoExtension.class })
class AdvancedAccordionBeanModelTest {

	private final AemContext context = new AemContext();

	private AdvancedAccordionBeanModel advancedAccordionBeanModel = new AdvancedAccordionBeanModel();

	private Page page;

	private Resource resource;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		page = context.create().page("/content/vida");
		resource = context.create().resource(page, "vida", "sling:resourceType", "vida/components/advancedaccordion",
				"heading", "Sample Heading", "description", "Sample Description", "accordionicon",
				"Sample accordionicon");

		advancedAccordionBeanModel = resource.adaptTo(AdvancedAccordionBeanModel.class);

	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.AdvancedAccordionBeanModel#getAccordionicon()}.
	 */
	@Test
	void testGetAccordionicon() {
		advancedAccordionBeanModel.getAccordionicon();

		assertEquals("Sample accordionicon", advancedAccordionBeanModel.getAccordionicon());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.AdvancedAccordionBeanModel#getDelaytime()}.
	 */
	@Test
	void testGetDelaytime() {
		advancedAccordionBeanModel.setDelaytime(10.0);
		advancedAccordionBeanModel.getDelaytime();

		assertEquals(10.0, advancedAccordionBeanModel.getDelaytime());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.AdvancedAccordionBeanModel#setDelaytime(double)}.
	 */
	@Test
	void testSetDelaytime() {
		advancedAccordionBeanModel.setDelaytime(10.0);

		assertEquals(10.0, advancedAccordionBeanModel.getDelaytime());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.AdvancedAccordionBeanModel#getHeading()}.
	 */
	@Test
	void testGetHeading() {
		advancedAccordionBeanModel.getHeading();

		assertEquals("Sample Heading", advancedAccordionBeanModel.getHeading());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.AdvancedAccordionBeanModel#getDescription()}.
	 */
	@Test
	void testGetDescription() {
		advancedAccordionBeanModel.getDescription();

		assertEquals("Sample Description", advancedAccordionBeanModel.getDescription());
	}

}
