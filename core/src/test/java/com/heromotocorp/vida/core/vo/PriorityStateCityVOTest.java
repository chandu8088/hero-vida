/**
 * 
 */
package com.heromotocorp.vida.core.vo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * JUnit test class for PriorityStateCityVO
 */
class PriorityStateCityVOTest {

	private PriorityStateCityVO priorityStateCityVO = new PriorityStateCityVO();

	private List<String> cities = new ArrayList<>();

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.PriorityStateCityVO#getState()}.
	 */
	@Test
	void testGetState() {
		priorityStateCityVO.setState("states");

		priorityStateCityVO.getState();
		assertEquals("states", priorityStateCityVO.getState());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.PriorityStateCityVO#setState(java.lang.String)}.
	 */
	@Test
	void testSetState() {
		priorityStateCityVO.setState("states");
		assertEquals("states", priorityStateCityVO.getState());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.PriorityStateCityVO#getCities()}.
	 */
	@Test
	void testGetCities() {
		priorityStateCityVO.setCities(cities);

		priorityStateCityVO.getCities();
		assertEquals(cities, priorityStateCityVO.getCities());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.PriorityStateCityVO#setCities(java.util.List)}.
	 */
	@Test
	void testSetCities() {
		priorityStateCityVO.setCities(cities);
		assertEquals(cities, priorityStateCityVO.getCities());
	}

}
