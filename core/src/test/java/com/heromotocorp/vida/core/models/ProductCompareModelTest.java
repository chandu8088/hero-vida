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
 * JUnit test class for ProductCompareModel
 */
@ExtendWith({ AemContextExtension.class, MockitoExtension.class })
class ProductCompareModelTest {

	private final AemContext context = new AemContext();

	private ProductCompareModel productCompareModel = new ProductCompareModel();

	private Page page;

	private Resource resource;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		page = context.create().page("/content/testvida");
		resource = context.create().resource(page, "test vida", "sling:resourceType", "vida/components/productcompare",
				"taxdesc", "taxdesc", "effectivedesc", "effectivedesc", "exshowroomdesc", "exshowroomdesc",
				"fameincentivedesc", "fameincentivedesc", "statesubsidydesc", "statesubsidydesc");

		productCompareModel = resource.adaptTo(ProductCompareModel.class);

	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.ProductCompareModel#getTaxDescription()}.
	 */
	@Test
	void testGetTaxDescription() {
		assertEquals("taxdesc", productCompareModel.getTaxDescription());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.ProductCompareModel#getEffectivedesc()}.
	 */
	@Test
	void testGetEffectivedesc() {
		assertEquals("effectivedesc", productCompareModel.getEffectivedesc());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.ProductCompareModel#getExshowroomdesc()}.
	 */
	@Test
	void testGetExshowroomdesc() {
		assertEquals("exshowroomdesc", productCompareModel.getExshowroomdesc());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.ProductCompareModel#getFameincentivedesc()}.
	 */
	@Test
	void testGetFameincentivedesc() {
		assertEquals("fameincentivedesc", productCompareModel.getFameincentivedesc());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.ProductCompareModel#getStatesubsidydesc()}.
	 */
	@Test
	void testGetStatesubsidydesc() {
		assertEquals("statesubsidydesc", productCompareModel.getStatesubsidydesc());
	}

}
