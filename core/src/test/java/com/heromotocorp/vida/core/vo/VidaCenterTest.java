/**
 * 
 */
package com.heromotocorp.vida.core.vo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * JUnit test class for VidaCenter
 */
class VidaCenterTest {

	private VidaCenter vidaCenter = new VidaCenter();

	/**
	 * Test method for {@link com.heromotocorp.vida.core.vo.VidaCenter#getId()}.
	 */
	@Test
	void testGetId() {
		vidaCenter.setId("ID");

		vidaCenter.getId();
		assertEquals("ID", vidaCenter.getId());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.VidaCenter#setId(java.lang.String)}.
	 */
	@Test
	void testSetId() {
		vidaCenter.setId("ID");
		assertEquals("ID", vidaCenter.getId());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.VidaCenter#getAccountpartnerId()}.
	 */
	@Test
	void testGetAccountpartnerId() {
		vidaCenter.setAccountpartnerId("AccountpartnerId");

		vidaCenter.getAccountpartnerId();
		assertEquals("AccountpartnerId", vidaCenter.getAccountpartnerId());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.VidaCenter#setAccountpartnerId(java.lang.String)}.
	 */
	@Test
	void testSetAccountpartnerId() {
		vidaCenter.setAccountpartnerId("AccountpartnerId");
		assertEquals("AccountpartnerId", vidaCenter.getAccountpartnerId());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.VidaCenter#getExperienceCenterName()}.
	 */
	@Test
	void testGetExperienceCenterName() {
		vidaCenter.setExperienceCenterName("ExperienceCenterName");

		vidaCenter.getExperienceCenterName();
		assertEquals("ExperienceCenterName", vidaCenter.getExperienceCenterName());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.VidaCenter#setExperienceCenterName(java.lang.String)}.
	 */
	@Test
	void testSetExperienceCenterName() {
		vidaCenter.setExperienceCenterName("ExperienceCenterName");
		assertEquals("ExperienceCenterName", vidaCenter.getExperienceCenterName());
	}

	/**
	 * Test method for {@link com.heromotocorp.vida.core.vo.VidaCenter#getType()}.
	 */
	@Test
	void testGetType() {
		vidaCenter.setType("Type");

		vidaCenter.getType();
		assertEquals("Type", vidaCenter.getType());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.VidaCenter#setType(java.lang.String)}.
	 */
	@Test
	void testSetType() {
		vidaCenter.setType("Type");
		assertEquals("Type", vidaCenter.getType());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.VidaCenter#getAddress()}.
	 */
	@Test
	void testGetAddress() {
		vidaCenter.setAddress("Address");

		vidaCenter.getAddress();
		assertEquals("Address", vidaCenter.getAddress());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.VidaCenter#setAddress(java.lang.String)}.
	 */
	@Test
	void testSetAddress() {
		vidaCenter.setAddress("Address");
		assertEquals("Address", vidaCenter.getAddress());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.VidaCenter#getPostalCode()}.
	 */
	@Test
	void testGetPostalCode() {
		vidaCenter.setPostalCode("PostalCode");

		vidaCenter.getPostalCode();
		assertEquals("PostalCode", vidaCenter.getPostalCode());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.VidaCenter#setPostalCode(java.lang.String)}.
	 */
	@Test
	void testSetPostalCode() {
		vidaCenter.setPostalCode("PostalCode");
		assertEquals("PostalCode", vidaCenter.getPostalCode());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.VidaCenter#getLongitude()}.
	 */
	@Test
	void testGetLongitude() {
		vidaCenter.setLongitude("Longitude");

		vidaCenter.getLongitude();
		assertEquals("Longitude", vidaCenter.getLongitude());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.VidaCenter#setLongitude(java.lang.String)}.
	 */
	@Test
	void testSetLongitude() {
		vidaCenter.setLongitude("Longitude");
		assertEquals("Longitude", vidaCenter.getLongitude());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.VidaCenter#getLatitude()}.
	 */
	@Test
	void testGetLatitude() {
		vidaCenter.setLatitude("Latitude");

		vidaCenter.getLatitude();
		assertEquals("Latitude", vidaCenter.getLatitude());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.VidaCenter#setLatitude(java.lang.String)}.
	 */
	@Test
	void testSetLatitude() {
		vidaCenter.setLatitude("Latitude");
		assertEquals("Latitude", vidaCenter.getLatitude());
	}

}
