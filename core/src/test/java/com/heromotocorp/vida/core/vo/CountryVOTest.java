/**
 * 
 */
package com.heromotocorp.vida.core.vo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * JUnit test class for CountryVO
 */
class CountryVOTest {

	private CountryVO countryVO = new CountryVO();

	private List<StateVO> states = new ArrayList<>();

	/**
	 * Test method for {@link com.heromotocorp.vida.core.vo.CountryVO#getLabel()}.
	 */
	@Test
	void testGetLabel() {
		countryVO.setLabel("label");

		countryVO.getLabel();
		assertEquals("label", countryVO.getLabel());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.CountryVO#setLabel(java.lang.String)}.
	 */
	@Test
	void testSetLabel() {
		countryVO.setLabel("label");
		assertEquals("label", countryVO.getLabel());
	}

	/**
	 * Test method for {@link com.heromotocorp.vida.core.vo.CountryVO#getValue()}.
	 */
	@Test
	void testGetValue() {
		countryVO.setValue("Value");

		countryVO.getValue();
		assertEquals("Value", countryVO.getValue());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.CountryVO#setValue(java.lang.String)}.
	 */
	@Test
	void testSetValue() {
		countryVO.setValue("Value");
		assertEquals("Value", countryVO.getValue());
	}

	/**
	 * Test method for {@link com.heromotocorp.vida.core.vo.CountryVO#getStates()}.
	 */
	@Test
	void testGetStates() {
		countryVO.setStates(states);

		countryVO.getStates();
		assertEquals(states, countryVO.getStates());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.CountryVO#setStates(java.util.List)}.
	 */
	@Test
	void testSetStates() {
		countryVO.setStates(states);
		assertEquals(states, countryVO.getStates());
	}

}
