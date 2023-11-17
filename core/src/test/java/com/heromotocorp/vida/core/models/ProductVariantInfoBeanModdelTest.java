/**
 * 
 */
package com.heromotocorp.vida.core.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import io.wcm.testing.mock.aem.junit5.AemContextExtension;

/**
 * JUnit test class for ProductVariantInfoBeanModdel
 */
@ExtendWith({ AemContextExtension.class, MockitoExtension.class })
class ProductVariantInfoBeanModdelTest {

	private ProductVariantInfoBeanModdel productVariantInfoBeanModdel = new ProductVariantInfoBeanModdel();

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.ProductVariantInfoBeanModdel#getImagePath()}.
	 */
	@Test
	void testGetImagePath() {
		productVariantInfoBeanModdel.setImagePath("ImagePath");
		assertEquals("ImagePath", productVariantInfoBeanModdel.getImagePath());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.ProductVariantInfoBeanModdel#setImagePath(java.lang.String)}.
	 */
	@Test
	void testSetImagePath() {
		productVariantInfoBeanModdel.setImagePath("ImagePath");
		assertEquals("ImagePath", productVariantInfoBeanModdel.getImagePath());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.ProductVariantInfoBeanModdel#getVarinat_color()}.
	 */
	@Test
	void testGetVarinat_color() {
		productVariantInfoBeanModdel.setVarinat_color("Varinat_color");
		assertEquals("Varinat_color", productVariantInfoBeanModdel.getVarinat_color());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.ProductVariantInfoBeanModdel#setVarinat_color(java.lang.String)}.
	 */
	@Test
	void testSetVarinat_color() {
		productVariantInfoBeanModdel.setVarinat_color("Varinat_color");
		assertEquals("Varinat_color", productVariantInfoBeanModdel.getVarinat_color());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.ProductVariantInfoBeanModdel#getVarinatColorGradiant()}.
	 */
	@Test
	void testGetVarinatColorGradiant() {
		productVariantInfoBeanModdel.setVarinatColorGradiant("VarinatColorGradiant");
		assertEquals("VarinatColorGradiant", productVariantInfoBeanModdel.getVarinatColorGradiant());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.ProductVariantInfoBeanModdel#getVariant_color_id()}.
	 */
	@Test
	void testGetVariant_color_id() {
		productVariantInfoBeanModdel.setVariant_color_id("Variant_color_id");
		assertEquals("Variant_color_id", productVariantInfoBeanModdel.getVariant_color_id());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.ProductVariantInfoBeanModdel#setVariant_color_id(java.lang.String)}.
	 */
	@Test
	void testSetVariant_color_id() {
		productVariantInfoBeanModdel.setVariant_color_id("Variant_color_id");
		assertEquals("Variant_color_id", productVariantInfoBeanModdel.getVariant_color_id());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.ProductVariantInfoBeanModdel#setVarinatColorGradiant(java.lang.String)}.
	 */
	@Test
	void testSetVarinatColorGradiant() {
		productVariantInfoBeanModdel.setVarinatColorGradiant("VarinatColorGradiant");
		assertEquals("VarinatColorGradiant", productVariantInfoBeanModdel.getVarinatColorGradiant());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.ProductVariantInfoBeanModdel#getVariant_range_value()}.
	 */
	@Test
	void testGetVariant_range_value() {
		productVariantInfoBeanModdel.setVariant_range_value("Variant_range_value");
		assertEquals("Variant_range_value", productVariantInfoBeanModdel.getVariant_range_value());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.ProductVariantInfoBeanModdel#setVariant_range_value(java.lang.String)}.
	 */
	@Test
	void testSetVariant_range_value() {
		productVariantInfoBeanModdel.setVariant_range_value("Variant_range_value");
		assertEquals("Variant_range_value", productVariantInfoBeanModdel.getVariant_range_value());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.ProductVariantInfoBeanModdel#getVariant_range_unit()}.
	 */
	@Test
	void testGetVariant_range_unit() {
		productVariantInfoBeanModdel.setVariant_range_unit("Variant_range_unit");
		assertEquals("Variant_range_unit", productVariantInfoBeanModdel.getVariant_range_unit());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.ProductVariantInfoBeanModdel#setVariant_range_unit(java.lang.String)}.
	 */
	@Test
	void testSetVariant_range_unit() {
		productVariantInfoBeanModdel.setVariant_range_unit("Variant_range_unit");
		assertEquals("Variant_range_unit", productVariantInfoBeanModdel.getVariant_range_unit());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.ProductVariantInfoBeanModdel#getVariant_charging_time_value()}.
	 */
	@Test
	void testGetVariant_charging_time_value() {
		productVariantInfoBeanModdel.setVariant_charging_time_value("Variant_charging_time_value");
		assertEquals("Variant_charging_time_value", productVariantInfoBeanModdel.getVariant_charging_time_value());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.ProductVariantInfoBeanModdel#setVariant_charging_time_value(java.lang.String)}.
	 */
	@Test
	void testSetVariant_charging_time_value() {
		productVariantInfoBeanModdel.setVariant_charging_time_value("Variant_charging_time_value");
		assertEquals("Variant_charging_time_value", productVariantInfoBeanModdel.getVariant_charging_time_value());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.ProductVariantInfoBeanModdel#getVariant_charging_time_unit()}.
	 */
	@Test
	void testGetVariant_charging_time_unit() {
		productVariantInfoBeanModdel.setVariant_charging_time_unit("Variant_charging_time_unit");
		assertEquals("Variant_charging_time_unit", productVariantInfoBeanModdel.getVariant_charging_time_unit());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.ProductVariantInfoBeanModdel#setVariant_charging_time_unit(java.lang.String)}.
	 */
	@Test
	void testSetVariant_charging_time_unit() {
		productVariantInfoBeanModdel.setVariant_charging_time_unit("Variant_charging_time_unit");
		assertEquals("Variant_charging_time_unit", productVariantInfoBeanModdel.getVariant_charging_time_unit());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.ProductVariantInfoBeanModdel#getVariant_accelerator_value()}.
	 */
	@Test
	void testGetVariant_accelerator_value() {
		productVariantInfoBeanModdel.setVariant_accelerator_value("10.00");
		assertEquals(10.00, productVariantInfoBeanModdel.getVariant_accelerator_value());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.ProductVariantInfoBeanModdel#setVariant_accelerator_value(java.lang.String)}.
	 */
	@Test
	void testSetVariant_accelerator_value() {
		productVariantInfoBeanModdel.setVariant_accelerator_value("10.00");
		assertEquals(10.00, productVariantInfoBeanModdel.getVariant_accelerator_value());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.ProductVariantInfoBeanModdel#getVariant_accelerator_unit()}.
	 */
	@Test
	void testGetVariant_accelerator_unit() {
		productVariantInfoBeanModdel.setVariant_accelerator_unit("Variant_accelerator_unit");
		assertEquals("Variant_accelerator_unit", productVariantInfoBeanModdel.getVariant_accelerator_unit());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.ProductVariantInfoBeanModdel#setVariant_accelerator_unit(java.lang.String)}.
	 */
	@Test
	void testSetVariant_accelerator_unit() {
		productVariantInfoBeanModdel.setVariant_accelerator_unit("Variant_accelerator_unit");
		assertEquals("Variant_accelerator_unit", productVariantInfoBeanModdel.getVariant_accelerator_unit());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.ProductVariantInfoBeanModdel#getVariant_top_speed_value()}.
	 */
	@Test
	void testGetVariant_top_speed_value() {
		productVariantInfoBeanModdel.setVariant_top_speed_value("Variant_top_speed_value");
		assertEquals("Variant_top_speed_value", productVariantInfoBeanModdel.getVariant_top_speed_value());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.ProductVariantInfoBeanModdel#setVariant_top_speed_value(java.lang.String)}.
	 */
	@Test
	void testSetVariant_top_speed_value() {
		productVariantInfoBeanModdel.setVariant_top_speed_value("Variant_top_speed_value");
		assertEquals("Variant_top_speed_value", productVariantInfoBeanModdel.getVariant_top_speed_value());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.ProductVariantInfoBeanModdel#getVariant_top_speed_unit()}.
	 */
	@Test
	void testGetVariant_top_speed_unit() {
		productVariantInfoBeanModdel.setVariant_top_speed_unit("Variant_top_speed_unit");
		assertEquals("Variant_top_speed_unit", productVariantInfoBeanModdel.getVariant_top_speed_unit());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.ProductVariantInfoBeanModdel#setVariant_top_speed_unit(java.lang.String)}.
	 */
	@Test
	void testSetVariant_top_speed_unit() {
		productVariantInfoBeanModdel.setVariant_top_speed_unit("Variant_top_speed_unit");
		assertEquals("Variant_top_speed_unit", productVariantInfoBeanModdel.getVariant_top_speed_unit());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.ProductVariantInfoBeanModdel#getVariant_sku()}.
	 */
	@Test
	void testGetVariant_sku() {
		productVariantInfoBeanModdel.setVariant_sku("Variant_sku");
		assertEquals("Variant_sku", productVariantInfoBeanModdel.getVariant_sku());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.ProductVariantInfoBeanModdel#setVariant_sku(java.lang.String)}.
	 */
	@Test
	void testSetVariant_sku() {
		productVariantInfoBeanModdel.setVariant_sku("Variant_sku");
		assertEquals("Variant_sku", productVariantInfoBeanModdel.getVariant_sku());
	}

}
