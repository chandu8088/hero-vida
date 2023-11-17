/**
 * 
 */
package com.heromotocorp.vida.core.vo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * JUnit test class for CityMasterVO
 */
class CityMasterVOTest {

	private CityMasterVO cityMasterVO = new CityMasterVO();

	List<VidaCenter> center = new ArrayList<>();

	List<ChargingStation> charging = new ArrayList<>();

	List<SwappingStation> swappingStations = new ArrayList<>();

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.CityMasterVO#getCityName()}.
	 */
	@Test
	void testGetCityName() {
		cityMasterVO.setCityName("City");

		cityMasterVO.getCityName();
		assertEquals("City", cityMasterVO.getCityName());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.CityMasterVO#setCityName(java.lang.String)}.
	 */
	@Test
	void testSetCityName() {
		cityMasterVO.setCityName("City");
		assertEquals("City", cityMasterVO.getCityName());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.CityMasterVO#getStateName()}.
	 */
	@Test
	void testGetStateName() {
		cityMasterVO.setStateName("StateName");

		cityMasterVO.getStateName();
		assertEquals("StateName", cityMasterVO.getStateName());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.CityMasterVO#setStateName(java.lang.String)}.
	 */
	@Test
	void testSetStateName() {
		cityMasterVO.setStateName("StateName");
		assertEquals("StateName", cityMasterVO.getStateName());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.CityMasterVO#getCountryName()}.
	 */
	@Test
	void testGetCountryName() {
		cityMasterVO.setCountryName("CountryName");

		cityMasterVO.getCountryName();
		assertEquals("CountryName", cityMasterVO.getCountryName());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.CityMasterVO#setCountryName(java.lang.String)}.
	 */
	@Test
	void testSetCountryName() {
		cityMasterVO.setCountryName("CountryName");
		assertEquals("CountryName", cityMasterVO.getCountryName());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.CityMasterVO#getLatitde()}.
	 */
	@Test
	void testGetLatitde() {
		cityMasterVO.setLatitde("Latitde");

		cityMasterVO.getLatitde();
		assertEquals("Latitde", cityMasterVO.getLatitde());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.CityMasterVO#setLatitde(java.lang.String)}.
	 */
	@Test
	void testSetLatitde() {
		cityMasterVO.setLatitde("Latitde");
		assertEquals("Latitde", cityMasterVO.getLatitde());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.CityMasterVO#getLongitude()}.
	 */
	@Test
	void testGetLongitude() {
		cityMasterVO.setLongitude("Longitude");

		cityMasterVO.getLongitude();
		assertEquals("Longitude", cityMasterVO.getLongitude());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.CityMasterVO#setLongitude(java.lang.String)}.
	 */
	@Test
	void testSetLongitude() {
		cityMasterVO.setLongitude("Longitude");
		assertEquals("Longitude", cityMasterVO.getLongitude());
	}

	/**
	 * Test method for {@link com.heromotocorp.vida.core.vo.CityMasterVO#getId()}.
	 */
	@Test
	void testGetId() {
		cityMasterVO.setId();

		cityMasterVO.getId();
		assertEquals("~~", cityMasterVO.getId());
	}

	/**
	 * Test method for {@link com.heromotocorp.vida.core.vo.CityMasterVO#setId()}.
	 */
	@Test
	void testSetId() {
		cityMasterVO.setId();
		assertEquals("~~", cityMasterVO.getId());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.CityMasterVO#getExperienceCenter()}.
	 */
	@Test
	void testGetExperienceCenter() {
		cityMasterVO.setExperienceCenter(center);

		cityMasterVO.getExperienceCenter();
		assertEquals(center, cityMasterVO.getExperienceCenter());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.CityMasterVO#setExperienceCenter(java.util.List)}.
	 */
	@Test
	void testSetExperienceCenter() {
		cityMasterVO.setExperienceCenter(center);
		assertEquals(center, cityMasterVO.getExperienceCenter());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.CityMasterVO#getServiceCenter()}.
	 */
	@Test
	void testGetServiceCenter() {
		cityMasterVO.setServiceCenter(center);

		cityMasterVO.getServiceCenter();
		assertEquals(center, cityMasterVO.getServiceCenter());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.CityMasterVO#setServiceCenter(java.util.List)}.
	 */
	@Test
	void testSetServiceCenter() {
		cityMasterVO.setServiceCenter(center);
		assertEquals(center, cityMasterVO.getServiceCenter());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.CityMasterVO#getChargigStations()}.
	 */
	@Test
	void testGetChargigStations() {
		cityMasterVO.setChargingStations(charging);

		cityMasterVO.getChargigStations();
		assertEquals(charging, cityMasterVO.getChargigStations());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.CityMasterVO#setcityMasterVOs(java.util.List)}.
	 */
	@Test
	void testSetChargigStations() {
		cityMasterVO.setChargingStations(charging);
		assertEquals(charging, cityMasterVO.getChargigStations());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.CityMasterVO#getDefaultPartnerIdCity()}.
	 */
	@Test
	void testGetDefaultPartnerIdCity() {
		cityMasterVO.setDefaultPartnerIdCity("DefaultPartnerIdCity");

		cityMasterVO.getDefaultPartnerIdCity();
		assertEquals("DefaultPartnerIdCity", cityMasterVO.getDefaultPartnerIdCity());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.CityMasterVO#setDefaultPartnerIdCity(java.lang.String)}.
	 */
	@Test
	void testSetDefaultPartnerIdCity() {
		cityMasterVO.setDefaultPartnerIdCity("DefaultPartnerIdCity");
		assertEquals("DefaultPartnerIdCity", cityMasterVO.getDefaultPartnerIdCity());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.CityMasterVO#getDefaultBranchIdCity()}.
	 */
	@Test
	void testGetDefaultBranchIdCity() {
		cityMasterVO.setDefaultBranchIdCity("DefaultBranchIdCity");

		cityMasterVO.getDefaultBranchIdCity();
		assertEquals("DefaultBranchIdCity", cityMasterVO.getDefaultBranchIdCity());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.CityMasterVO#setDefaultBranchIdCity(java.lang.String)}.
	 */
	@Test
	void testSetDefaultBranchIdCity() {
		cityMasterVO.setDefaultBranchIdCity("DefaultBranchIdCity");
		assertEquals("DefaultBranchIdCity", cityMasterVO.getDefaultBranchIdCity());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.CityMasterVO#getSwappingStations()}.
	 */
	@Test
	void testGetSwappingStations() {
		cityMasterVO.setSwappingStations(swappingStations);

		cityMasterVO.getSwappingStations();
		assertEquals(swappingStations, cityMasterVO.getSwappingStations());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.CityMasterVO#setSwappingStations(java.util.List)}.
	 */
	@Test
	void testSetSwappingStations() {
		cityMasterVO.setSwappingStations(swappingStations);
		assertEquals(swappingStations, cityMasterVO.getSwappingStations());
	}

}
