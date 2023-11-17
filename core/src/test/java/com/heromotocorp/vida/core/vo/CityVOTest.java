/**
 * 
 */
package com.heromotocorp.vida.core.vo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * JUnit test class for CityVO
 */
class CityVOTest {

	private CityVO cityVO = new CityVO();

	/**
	 * Test method for {@link com.heromotocorp.vida.core.vo.CityVO#getLabel()}.
	 */
	@Test
	void testGetLabel() {
		cityVO.setLabel("Label");

		cityVO.getLabel();
		assertEquals("Label", cityVO.getLabel());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.CityVO#setLabel(java.lang.String)}.
	 */
	@Test
	void testSetLabel() {
		cityVO.setLabel("Label");
		assertEquals("Label", cityVO.getLabel());
	}

	/**
	 * Test method for {@link com.heromotocorp.vida.core.vo.CityVO#getValue()}.
	 */
	@Test
	void testGetValue() {
		cityVO.setValue("Value");

		cityVO.getValue();
		assertEquals("Value", cityVO.getValue());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.CityVO#setValue(java.lang.String)}.
	 */
	@Test
	void testSetValue() {
		cityVO.setValue("Value");
		assertEquals("Value", cityVO.getValue());
	}

}
