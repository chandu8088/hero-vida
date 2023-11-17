/**
 * 
 */
package com.heromotocorp.vida.core.vo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * JUnit test class for ModelVO
 */
class ModelVOTest {

	private ModelVO modelVO = new ModelVO();

	/**
	 * Test method for {@link com.heromotocorp.vida.core.vo.ModelVO#getLabel()}.
	 */
	@Test
	void testGetLabel() {
		modelVO.setLabel("Label");

		modelVO.getLabel();
		assertEquals("Label", modelVO.getLabel());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.ModelVO#setLabel(java.lang.String)}.
	 */
	@Test
	void testSetLabel() {
		modelVO.setLabel("Label");
		assertEquals("Label", modelVO.getLabel());
	}

	/**
	 * Test method for {@link com.heromotocorp.vida.core.vo.ModelVO#getValue()}.
	 */
	@Test
	void testGetValue() {
		modelVO.setValue("Value");

		modelVO.getValue();
		assertEquals("Value", modelVO.getValue());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.ModelVO#setValue(java.lang.String)}.
	 */
	@Test
	void testSetValue() {
		modelVO.setValue("Value");
		assertEquals("Value", modelVO.getValue());
	}

}
