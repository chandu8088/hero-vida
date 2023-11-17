/**
 * 
 */
package com.heromotocorp.vida.core.vo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * JUnit test class for ChargingStation
 */
class ChargingStationTest {

	private ChargingStation chargingStation = new ChargingStation();

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.ChargingStation#getStationId()}.
	 */
	@Test
	void testGetStationId() {
		chargingStation.setStationId("ID");

		chargingStation.getStationId();

		assertEquals("ID", chargingStation.getStationId());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.ChargingStation#setStationId(java.lang.String)}.
	 */
	@Test
	void testSetStationId() {
		chargingStation.setStationId("ID");

		assertEquals("ID", chargingStation.getStationId());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.ChargingStation#getStationName()}.
	 */
	@Test
	void testGetStationName() {
		chargingStation.setStationName("name");

		chargingStation.getStationName();

		assertEquals("name", chargingStation.getStationName());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.ChargingStation#setStationName(java.lang.String)}.
	 */
	@Test
	void testSetStationName() {
		chargingStation.setStationName("name");

		assertEquals("name", chargingStation.getStationName());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.ChargingStation#getChargingStationAddress()}.
	 */
	@Test
	void testGetChargingStationAddress() {
		chargingStation.setChargingStationAddress("address");

		chargingStation.getChargingStationAddress();
		assertEquals("address", chargingStation.getChargingStationAddress());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.ChargingStation#setChargingStationAddress(java.lang.String)}.
	 */
	@Test
	void testSetChargingStationAddress() {
		chargingStation.setChargingStationAddress("address");
		assertEquals("address", chargingStation.getChargingStationAddress());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.ChargingStation#getLongitude()}.
	 */
	@Test
	void testGetLongitude() {
		chargingStation.setLongitude("longitude");

		chargingStation.getLongitude();
		assertEquals("longitude", chargingStation.getLongitude());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.ChargingStation#setLongitude(java.lang.String)}.
	 */
	@Test
	void testSetLongitude() {
		chargingStation.setLongitude("longitude");
		assertEquals("longitude", chargingStation.getLongitude());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.ChargingStation#getLatitude()}.
	 */
	@Test
	void testGetLatitude() {
		chargingStation.setLatitude("Latitude");

		chargingStation.getLatitude();
		assertEquals("Latitude", chargingStation.getLatitude());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.ChargingStation#setLatitude(java.lang.String)}.
	 */
	@Test
	void testSetLatitude() {
		chargingStation.setLatitude("Latitude");
		assertEquals("Latitude", chargingStation.getLatitude());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.ChargingStation#getCityLocation()}.
	 */
	@Test
	void testGetCityLocation() {
		chargingStation.setCityLocation("CityLocation");

		chargingStation.getCityLocation();
		assertEquals("CityLocation", chargingStation.getCityLocation());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.ChargingStation#setCityLocation(java.lang.String)}.
	 */
	@Test
	void testSetCityLocation() {
		chargingStation.setCityLocation("CityLocation");
		assertEquals("CityLocation", chargingStation.getCityLocation());
	}

}
