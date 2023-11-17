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
 * JUnit test class for ProductVariantPriceMasterInfoBeanModdel
 */
@ExtendWith({ AemContextExtension.class, MockitoExtension.class })
class ProductVariantPriceMasterInfoBeanModdelTest {

	private ProductVariantPriceMasterInfoBeanModdel productVariantPriceMasterInfoBeanModdel = new ProductVariantPriceMasterInfoBeanModdel();

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.ProductVariantPriceMasterInfoBeanModdel#getVarinatSku()}.
	 */
	@Test
	void testGetVarinatSku() {
		productVariantPriceMasterInfoBeanModdel.setVarinatSku("VarinatSku");
		assertEquals("VarinatSku", productVariantPriceMasterInfoBeanModdel.getVarinatSku());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.ProductVariantPriceMasterInfoBeanModdel#setVarinatSku(java.lang.String)}.
	 */
	@Test
	void testSetVarinatSku() {
		productVariantPriceMasterInfoBeanModdel.setVarinatSku("VarinatSku");
		assertEquals("VarinatSku", productVariantPriceMasterInfoBeanModdel.getVarinatSku());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.ProductVariantPriceMasterInfoBeanModdel#getOnRoadPrice()}.
	 */
	@Test
	void testGetOnRoadPrice() {
		productVariantPriceMasterInfoBeanModdel.setOnRoadPrice("OnRoadPrice");
		assertEquals("OnRoadPrice", productVariantPriceMasterInfoBeanModdel.getOnRoadPrice());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.ProductVariantPriceMasterInfoBeanModdel#setOnRoadPrice(java.lang.String)}.
	 */
	@Test
	void testSetOnRoadPrice() {
		productVariantPriceMasterInfoBeanModdel.setOnRoadPrice("OnRoadPrice");
		assertEquals("OnRoadPrice", productVariantPriceMasterInfoBeanModdel.getOnRoadPrice());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.ProductVariantPriceMasterInfoBeanModdel#getEffectivePrice()}.
	 */
	@Test
	void testGetEffectivePrice() {
		productVariantPriceMasterInfoBeanModdel.setEffectivePrice("EffectivePrice");
		assertEquals("EffectivePrice", productVariantPriceMasterInfoBeanModdel.getEffectivePrice());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.ProductVariantPriceMasterInfoBeanModdel#setEffectivePrice(java.lang.String)}.
	 */
	@Test
	void testSetEffectivePrice() {
		productVariantPriceMasterInfoBeanModdel.setEffectivePrice("EffectivePrice");
		assertEquals("EffectivePrice", productVariantPriceMasterInfoBeanModdel.getEffectivePrice());
	}

}
