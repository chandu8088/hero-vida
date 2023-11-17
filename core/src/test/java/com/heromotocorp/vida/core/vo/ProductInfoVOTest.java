/**
 * 
 */
package com.heromotocorp.vida.core.vo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * JUnit test class for ProductInfoVO
 */
class ProductInfoVOTest {

	private ProductInfoVO productInfoVO = new ProductInfoVO();

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.ProductInfoVO#getPrice()}.
	 */
	@Test
	void testGetPrice() {
		productInfoVO.setPrice("Price");

		productInfoVO.getPrice();
		assertEquals("Price", productInfoVO.getPrice());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.ProductInfoVO#setPrice(java.lang.String)}.
	 */
	@Test
	void testSetPrice() {
		productInfoVO.setPrice("Price");
		assertEquals("Price", productInfoVO.getPrice());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.ProductInfoVO#getCity()}.
	 */
	@Test
	void testGetCity() {
		productInfoVO.setCity("City");

		productInfoVO.getCity();
		assertEquals("City", productInfoVO.getCity());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.ProductInfoVO#setCity(java.lang.String)}.
	 */
	@Test
	void testSetCity() {
		productInfoVO.setCity("City");
		assertEquals("City", productInfoVO.getCity());
	}

}
