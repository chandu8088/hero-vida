/**
 * 
 */
package com.heromotocorp.vida.core.vo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * JUnit test class for ExperienceCenter
 */
class ExperienceCenterTest {

	private ExperienceCenter experienceCenter = new ExperienceCenter();

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.ExperienceCenter#getExperienceCenterName()}.
	 */
	@Test
	void testGetExperienceCenterName() {
		experienceCenter.setExperienceCenterName("ExperienceCenterName");

		experienceCenter.getExperienceCenterName();
		assertEquals("ExperienceCenterName", experienceCenter.getExperienceCenterName());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.ExperienceCenter#setExperienceCenterName(java.lang.String)}.
	 */
	@Test
	void testSetExperienceCenterName() {
		experienceCenter.setExperienceCenterName("ExperienceCenterName");
		assertEquals("ExperienceCenterName", experienceCenter.getExperienceCenterName());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.ExperienceCenter#getType()}.
	 */
	@Test
	void testGetType() {
		experienceCenter.setType("Type");

		experienceCenter.getType();
		assertEquals("Type", experienceCenter.getType());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.ExperienceCenter#setType(java.lang.String)}.
	 */
	@Test
	void testSetType() {
		experienceCenter.setType("Type");
		assertEquals("Type", experienceCenter.getType());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.ExperienceCenter#getId()}.
	 */
	@Test
	void testGetId() {
		experienceCenter.setId("ID");

		experienceCenter.getId();
		assertEquals("ID", experienceCenter.getId());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.ExperienceCenter#setId(java.lang.String)}.
	 */
	@Test
	void testSetId() {
		experienceCenter.setId("ID");
		assertEquals("ID", experienceCenter.getId());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.ExperienceCenter#getPartnerId()}.
	 */
	@Test
	void testGetPartnerId() {
		experienceCenter.setPartnerId("PartnerId");

		experienceCenter.getPartnerId();
		assertEquals("PartnerId", experienceCenter.getPartnerId());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.ExperienceCenter#setPartnerId(java.lang.String)}.
	 */
	@Test
	void testSetPartnerId() {
		experienceCenter.setPartnerId("PartnerId");
		assertEquals("PartnerId", experienceCenter.getPartnerId());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.ExperienceCenter#getIsServiceCenter()}.
	 */
	@Test
	void testGetIsServiceCenter() {
		experienceCenter.setIsServiceCenter("true");

		experienceCenter.getIsServiceCenter();
		assertEquals("true", experienceCenter.getIsServiceCenter());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.ExperienceCenter#setIsServiceCenter(java.lang.String)}.
	 */
	@Test
	void testSetIsServiceCenter() {
		experienceCenter.setIsServiceCenter("false");
		assertEquals("false", experienceCenter.getIsServiceCenter());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.ExperienceCenter#getLatitude()}.
	 */
	@Test
	void testGetLatitude() {
		experienceCenter.setLatitude("Latitude");

		experienceCenter.getLatitude();
		assertEquals("Latitude", experienceCenter.getLatitude());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.ExperienceCenter#setLatitude(java.lang.String)}.
	 */
	@Test
	void testSetLatitude() {
		experienceCenter.setLatitude("Latitude");
		assertEquals("Latitude", experienceCenter.getLatitude());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.ExperienceCenter#getLongitude()}.
	 */
	@Test
	void testGetLongitude() {
		experienceCenter.setLongitude("longitude");

		experienceCenter.getLongitude();
		assertEquals("longitude", experienceCenter.getLongitude());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.ExperienceCenter#setLongitude(java.lang.String)}.
	 */
	@Test
	void testSetLongitude() {
		experienceCenter.setLongitude("longitude");
		assertEquals("longitude", experienceCenter.getLongitude());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.ExperienceCenter#getAddress()}.
	 */
	@Test
	void testGetAddress() {
		experienceCenter.setAddress("Address");

		experienceCenter.getAddress();
		assertEquals("Address", experienceCenter.getAddress());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.ExperienceCenter#setAddress(java.lang.String)}.
	 */
	@Test
	void testSetAddress() {
		experienceCenter.setAddress("Address");
		assertEquals("Address", experienceCenter.getAddress());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.ExperienceCenter#getPostalCode()}.
	 */
	@Test
	void testGetPostalCode() {
		experienceCenter.setPostalCode("PostalCode");

		experienceCenter.getPostalCode();
		assertEquals("PostalCode", experienceCenter.getPostalCode());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.ExperienceCenter#setPostalCode(java.lang.String)}.
	 */
	@Test
	void testSetPostalCode() {
		experienceCenter.setPostalCode("PostalCode");
		assertEquals("PostalCode", experienceCenter.getPostalCode());
	}

}
