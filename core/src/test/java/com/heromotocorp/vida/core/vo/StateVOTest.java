/**
 * 
 */
package com.heromotocorp.vida.core.vo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * JUnit test class for StateVO
 */
class StateVOTest {

	private StateVO stateVO = new StateVO();

	private List<CityVO> cities = new ArrayList<>();

	/**
	 * Test method for {@link com.heromotocorp.vida.core.vo.StateVO#getLabel()}.
	 */
	@Test
	void testGetLabel() {
		stateVO.setLabel("Label");

		stateVO.getLabel();
		assertEquals("Label", stateVO.getLabel());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.StateVO#setLabel(java.lang.String)}.
	 */
	@Test
	void testSetLabel() {
		stateVO.setLabel("Label");
		assertEquals("Label", stateVO.getLabel());
	}

	/**
	 * Test method for {@link com.heromotocorp.vida.core.vo.StateVO#getValue()}.
	 */
	@Test
	void testGetValue() {
		stateVO.setValue("Value");

		stateVO.getValue();
		assertEquals("Value", stateVO.getValue());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.StateVO#setValue(java.lang.String)}.
	 */
	@Test
	void testSetValue() {
		stateVO.setValue("Value");
		assertEquals("Value", stateVO.getValue());
	}

	/**
	 * Test method for {@link com.heromotocorp.vida.core.vo.StateVO#getCities()}.
	 */
	@Test
	void testGetCities() {
		stateVO.setCities(cities);

		stateVO.getCities();
		assertEquals(cities, stateVO.getCities());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.StateVO#setCities(java.util.List)}.
	 */
	@Test
	void testSetCities() {
		stateVO.setCities(cities);
		assertEquals(cities, stateVO.getCities());
	}

}
