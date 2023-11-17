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
 * JUnit test class for StaticProductSpecBeanModel
 */
@ExtendWith({ AemContextExtension.class, MockitoExtension.class })
class StaticProductSpecBeanModelTest {

	private final AemContext context = new AemContext();

	private StaticProductSpecBeanModel staticProductSpecBeanModel = new StaticProductSpecBeanModel();

	private Page page;

	private Resource resource;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		page = context.create().page("/content/testvida");
		resource = context.create().resource(page, "test vida", "sling:resourceType", "vida/components/productcompare",
				"specificationheading", "specificationheading", "enablenumeric", true, "datanumeric", 18, "datadecimal",
				10.20, "dataunit", "dataunit");

		staticProductSpecBeanModel = resource.adaptTo(StaticProductSpecBeanModel.class);
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.StaticProductSpecBeanModel#getSpecificationheading()}.
	 */
	@Test
	void testGetSpecificationheading() {
		assertEquals("specificationheading", staticProductSpecBeanModel.getSpecificationheading());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.StaticProductSpecBeanModel#isEnablenumeric()}.
	 */
	@Test
	void testIsEnablenumeric() {
		assertEquals(true, staticProductSpecBeanModel.isEnablenumeric());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.StaticProductSpecBeanModel#getDatanumeric()}.
	 */
	@Test
	void testGetDatanumeric() {
		assertEquals(18, staticProductSpecBeanModel.getDatanumeric());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.StaticProductSpecBeanModel#getDatadecimal()}.
	 */
	@Test
	void testGetDatadecimal() {
		assertEquals(10.199999809265137, staticProductSpecBeanModel.getDatadecimal());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.StaticProductSpecBeanModel#getDataunit()}.
	 */
	@Test
	void testGetDataunit() {
		assertEquals("dataunit", staticProductSpecBeanModel.getDataunit());
	}

}
