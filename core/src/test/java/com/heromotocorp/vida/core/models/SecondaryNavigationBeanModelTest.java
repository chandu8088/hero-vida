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
 * JUnit test class for SecondaryNavigationBeanModel
 */
@ExtendWith({ AemContextExtension.class, MockitoExtension.class })
class SecondaryNavigationBeanModelTest {

	private final AemContext context = new AemContext();

	private SecondaryNavigationBeanModel secondaryNavigationBeanModel = new SecondaryNavigationBeanModel();

	private Page page;

	private Resource resource;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		page = context.create().page("/content/testvida");
		resource = context.create().resource(page, "test vida", "sling:resourceType",
				"vida/components/secondarynavigation", "secondarynavtext", "secondarynavtext", "secondarynavtextlink",
				"secondarynavtextlink");

		secondaryNavigationBeanModel = resource.adaptTo(SecondaryNavigationBeanModel.class);
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.SecondaryNavigationBeanModel#getSecondarynavtext()}.
	 */
	@Test
	void testGetSecondarynavtext() {
		assertEquals("secondarynavtext", secondaryNavigationBeanModel.getSecondarynavtext());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.SecondaryNavigationBeanModel#getSecondarynavtextlink()}.
	 */
	@Test
	void testGetSecondarynavtextlink() {
		assertEquals("secondarynavtextlink", secondaryNavigationBeanModel.getSecondarynavtextlink());
	}

}
