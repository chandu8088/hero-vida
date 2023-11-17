/**
 * 
 */
package com.heromotocorp.vida.core.vo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * JUnit test class for Product
 */
class ProductTest {

	private Product product = new Product();

	private List<ProductItemVariant> items = new ArrayList<>();

	/**
	 * Test method for {@link com.heromotocorp.vida.core.vo.Product#getItems()}.
	 */
	@Test
	void testGetItems() {
		product.setItems(items);

		product.getItems();
		assertEquals(items, product.getItems());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.Product#setItems(java.util.List)}.
	 */
	@Test
	void testSetItems() {
		product.setItems(items);
		assertEquals(items, product.getItems());
	}

}
