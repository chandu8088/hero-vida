/**
 * 
 */
package com.heromotocorp.vida.core.vo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * JUnit test class for SwappingStation
 */
class SwappingStationTest {

	private SwappingStation swappingStation = new SwappingStation();

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.SwappingStation#getSwappingStationName()}.
	 */
	@Test
	void testGetSwappingStationName() {
		swappingStation.setSwappingStationName("SwappingStationName");

		swappingStation.getSwappingStationName();
		assertEquals("SwappingStationName", swappingStation.getSwappingStationName());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.SwappingStation#setSwappingStationName(java.lang.String)}.
	 */
	@Test
	void testSetSwappingStationName() {
		swappingStation.setSwappingStationName("SwappingStationName");
		assertEquals("SwappingStationName", swappingStation.getSwappingStationName());
	}

}
