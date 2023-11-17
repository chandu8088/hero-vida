/**
 * 
 */
package com.heromotocorp.vida.core.models;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.sling.api.resource.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.day.cq.wcm.api.Page;


import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;

/**
 * JUnit test class for AdvancedAccordionModel
 */
@ExtendWith({ AemContextExtension.class, MockitoExtension.class })
class AdvancedAccordionModelTest {

	private final AemContext context = new AemContext();
	
	private AdvancedAccordionModel advancedAccordionModel = new AdvancedAccordionModel();

	private AdvancedAccordionBeanModel advancedAccordionBeanModel = new AdvancedAccordionBeanModel();
	
	private Page page1;
	
	private Page page;

	private Resource resource;
	
	private Resource resource1;
	
	private AdvancedAccordionModel advancedAccordionModel1 = new AdvancedAccordionModel();
	
	private Page page2;

	private Resource resource2;

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
		
		page1 = context.create().page("/content/samplevida");
		resource1 = context.create().resource(page1, "sample vida", "sling:resourceType", "vida/components/advancedaccordionmodel",
				"title", "Sample Title","subtitle", "Sample Sub Title", "advanced",advancedAccordionBeanModel);

		advancedAccordionModel = resource1.adaptTo(AdvancedAccordionModel.class);
		
		page2 = context.create().page("/content/testvida");
		resource2 = context.create().resource(page2, "Test vida", "sling:resourceType", "vida/components/advancedaccordionmodel",
				"title", "Sample Title","subtitle", "Sample Sub Title", "advanced",null);

		advancedAccordionModel = resource2.adaptTo(AdvancedAccordionModel.class);
	
	}

	/**
	 * Test method for {@link com.heromotocorp.vida.core.models.AdvancedAccordionModel#getTitle()}.
	 */
	@Test
	void testGetTitle() {
		advancedAccordionModel.getTitle();

		assertEquals("Sample Title", advancedAccordionModel.getTitle());
	}

	/**
	 * Test method for {@link com.heromotocorp.vida.core.models.AdvancedAccordionModel#getSubtitle()}.
	 */
	@Test
	void testGetSubtitle() {
		advancedAccordionModel.getSubtitle();

		assertEquals("Sample Sub Title", advancedAccordionModel.getSubtitle());
	}

	/**
	 * Test method for {@link com.heromotocorp.vida.core.models.AdvancedAccordionModel#getAdvanced()}.
	 */
	@Test
	void testGetAdvanced() {
		advancedAccordionModel.getAdvanced();

		assertNotNull(advancedAccordionBeanModel);
		
		advancedAccordionModel.getAdvanced();
	}

	/**
	 * Test method for {@link com.heromotocorp.vida.core.models.AdvancedAccordionModel#init()}.
	 */
	@Test
	void testInit() {
		advancedAccordionModel.init();

		assertEquals("Sample Sub Title", advancedAccordionModel.getSubtitle());
	}

}
