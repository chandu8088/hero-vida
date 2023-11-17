/**
 * 
 */
package com.heromotocorp.vida.core.vo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * JUnit test class for ExchangeVehicleVO
 */
class ExchangeVehicleVOTest {

	private ExchangeVehicleVO exchangeVehicleVO = new ExchangeVehicleVO();

	private List<ModelVO> brand = new ArrayList<>();

	private List<ModelVO> ccList = new ArrayList<>();

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.ExchangeVehicleVO#getName()}.
	 */
	@Test
	void testGetName() {
		exchangeVehicleVO.setName("Name");

		exchangeVehicleVO.getName();
		assertEquals("Name", exchangeVehicleVO.getName());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.ExchangeVehicleVO#setName(java.lang.String)}.
	 */
	@Test
	void testSetName() {
		exchangeVehicleVO.setName("Name");
		assertEquals("Name", exchangeVehicleVO.getName());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.ExchangeVehicleVO#getBrandModel()}.
	 */
	@Test
	void testGetBrandModel() {
		exchangeVehicleVO.setBrandModel(brand);

		exchangeVehicleVO.getBrandModel();
		assertEquals(brand, exchangeVehicleVO.getBrandModel());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.ExchangeVehicleVO#setBrandModel(java.util.List)}.
	 */
	@Test
	void testSetBrandModel() {
		exchangeVehicleVO.setBrandModel(brand);
		assertEquals(brand, exchangeVehicleVO.getBrandModel());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.ExchangeVehicleVO#getNameValue()}.
	 */
	@Test
	void testGetNameValue() {
		exchangeVehicleVO.setNameValue("NameValue");

		exchangeVehicleVO.getNameValue();
		assertEquals("NameValue", exchangeVehicleVO.getNameValue());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.ExchangeVehicleVO#setNameValue(java.lang.String)}.
	 */
	@Test
	void testSetNameValue() {
		exchangeVehicleVO.setNameValue("NameValue");
		assertEquals("NameValue", exchangeVehicleVO.getNameValue());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.ExchangeVehicleVO#getCcList()}.
	 */
	@Test
	void testGetCcList() {
		exchangeVehicleVO.setCcList(ccList);

		exchangeVehicleVO.getCcList();
		assertEquals(ccList, exchangeVehicleVO.getCcList());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.ExchangeVehicleVO#setCcList(java.util.List)}.
	 */
	@Test
	void testSetCcList() {
		exchangeVehicleVO.setCcList(ccList);
		assertEquals(ccList, exchangeVehicleVO.getCcList());
	}

}
