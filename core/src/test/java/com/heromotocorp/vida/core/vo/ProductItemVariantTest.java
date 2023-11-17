/**
 * 
 */
package com.heromotocorp.vida.core.vo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * JUnit test class for ProductItemVariant
 */
class ProductItemVariantTest {

	private ProductItemVariant productItemVariant = new ProductItemVariant();

	private List<ProductItemColourVariant> variants = new ArrayList<>();

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.ProductItemVariant#getVariants()}.
	 */
	@Test
	void testGetVariants() {
		productItemVariant.setVariants(variants);

		productItemVariant.getVariants();
		assertEquals(variants, productItemVariant.getVariants());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.ProductItemVariant#setVariants(java.util.List)}.
	 */
	@Test
	void testSetVariants() {
		productItemVariant.setVariants(variants);
		assertEquals(variants, productItemVariant.getVariants());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.ProductItemVariant#getName()}.
	 */
	@Test
	void testGetName() {
		productItemVariant.setName("Name");

		productItemVariant.getName();
		assertEquals("Name", productItemVariant.getName());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.ProductItemVariant#setName(java.lang.String)}.
	 */
	@Test
	void testSetName() {
		productItemVariant.setName("Name");
		assertEquals("Name", productItemVariant.getName());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.ProductItemVariant#getSku()}.
	 */
	@Test
	void testGetSku() {
		productItemVariant.setSku("Sku");

		productItemVariant.getSku();
		assertEquals("Sku", productItemVariant.getSku());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.ProductItemVariant#setSku(java.lang.String)}.
	 */
	@Test
	void testSetSku() {
		productItemVariant.setSku("Sku");
		assertEquals("Sku", productItemVariant.getSku());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.ProductItemVariant#getSf_id()}.
	 */
	@Test
	void testGetSf_id() {
		productItemVariant.setSf_id("Sf_id");

		productItemVariant.getSf_id();
		assertEquals("Sf_id", productItemVariant.getSf_id());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.ProductItemVariant#setSf_id(java.lang.String)}.
	 */
	@Test
	void testSetSf_id() {
		productItemVariant.setSf_id("Sf_id");
		assertEquals("Sf_id", productItemVariant.getSf_id());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.ProductItemVariant#get__typename()}.
	 */
	@Test
	void testGet__typename() {
		productItemVariant.set__typename("_typename");

		productItemVariant.get__typename();
		assertEquals("_typename", productItemVariant.get__typename());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.ProductItemVariant#set__typename(java.lang.String)}.
	 */
	@Test
	void testSet__typename() {
		productItemVariant.set__typename("_typename");
		assertEquals("_typename", productItemVariant.get__typename());
	}

}
