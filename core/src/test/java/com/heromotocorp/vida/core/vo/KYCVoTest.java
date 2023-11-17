/**
 * 
 */
package com.heromotocorp.vida.core.vo;

import org.junit.jupiter.api.Test;

/**
 * JUnit test class for KYCVo
 */
class KYCVoTest {

	private KYCVo kYCVo = new KYCVo();

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.KYCVo#setLabel(java.lang.String)}.
	 */
	@Test
	void testSetLabel() {
		kYCVo.setLabel("label");
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.KYCVo#setValue(java.lang.String)}.
	 */
	@Test
	void testSetValue() {
		kYCVo.setValue("values");
	}

}
