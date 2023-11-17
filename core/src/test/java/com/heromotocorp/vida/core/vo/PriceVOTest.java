/**
 * 
 */
package com.heromotocorp.vida.core.vo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * JUnit test class for PriceVO
 */
class PriceVOTest {

	private PriceVO priceVO = new PriceVO();

	/**
	 * Test method for {@link com.heromotocorp.vida.core.vo.PriceVO#getItem_name()}.
	 */
	@Test
	void testGetItem_name() {
		priceVO.setItem_name("ItemName");

		priceVO.getItem_name();
		assertEquals("ItemName", priceVO.getItem_name());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.PriceVO#setItem_name(java.lang.String)}.
	 */
	@Test
	void testSetItem_name() {
		priceVO.setItem_name("ItemName");
		assertEquals("ItemName", priceVO.getItem_name());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.PriceVO#getItem_sf_id()}.
	 */
	@Test
	void testGetItem_sf_id() {
		priceVO.setItem_sf_id("Item_sf_id");

		priceVO.getItem_sf_id();
		assertEquals("Item_sf_id", priceVO.getItem_sf_id());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.PriceVO#setItem_sf_id(java.lang.String)}.
	 */
	@Test
	void testSetItem_sf_id() {
		priceVO.setItem_sf_id("Item_sf_id");
		assertEquals("Item_sf_id", priceVO.getItem_sf_id());
	}

	/**
	 * Test method for {@link com.heromotocorp.vida.core.vo.PriceVO#getItem_sku()}.
	 */
	@Test
	void testGetItem_sku() {
		priceVO.setItem_sku("Item_sku");

		priceVO.getItem_sku();
		assertEquals("Item_sku", priceVO.getItem_sku());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.PriceVO#setItem_sku(java.lang.String)}.
	 */
	@Test
	void testSetItem_sku() {
		priceVO.setItem_sku("Item_sku");
		assertEquals("Item_sku", priceVO.getItem_sku());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.PriceVO#getVariant_sf_id()}.
	 */
	@Test
	void testGetVariant_sf_id() {
		priceVO.setVariant_sf_id("Variant_sf_id");

		priceVO.getVariant_sf_id();
		assertEquals("Variant_sf_id", priceVO.getVariant_sf_id());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.PriceVO#setVariant_sf_id(java.lang.String)}.
	 */
	@Test
	void testSetVariant_sf_id() {
		priceVO.setVariant_sf_id("Variant_sf_id");
		assertEquals("Variant_sf_id", priceVO.getVariant_sf_id());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.PriceVO#getVariant_name()}.
	 */
	@Test
	void testGetVariant_name() {
		priceVO.setVariant_name("Variant_name");

		priceVO.getVariant_name();
		assertEquals("Variant_name", priceVO.getVariant_name());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.PriceVO#setVariant_name(java.lang.String)}.
	 */
	@Test
	void testSetVariant_name() {
		priceVO.setVariant_name("Variant_name");
		assertEquals("Variant_name", priceVO.getVariant_name());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.PriceVO#getCity_state_id()}.
	 */
	@Test
	void testGetCity_state_id() {
		priceVO.setCity_state_id("City_state_id");

		priceVO.getCity_state_id();
		assertEquals("City_state_id", priceVO.getCity_state_id());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.PriceVO#setCity_state_id(java.lang.String)}.
	 */
	@Test
	void testSetCity_state_id() {
		priceVO.setCity_state_id("City_state_id");
		assertEquals("City_state_id", priceVO.getCity_state_id());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.PriceVO#getVariant_sku()}.
	 */
	@Test
	void testGetVariant_sku() {
		priceVO.setVariant_sku("Variant_sku");

		priceVO.getVariant_sku();
		assertEquals("Variant_sku", priceVO.getVariant_sku());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.PriceVO#setVariant_sku(java.lang.String)}.
	 */
	@Test
	void testSetVariant_sku() {
		priceVO.setVariant_sku("Variant_sku");
		assertEquals("Variant_sku", priceVO.getVariant_sku());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.PriceVO#getOnRoadPrice()}.
	 */
	@Test
	void testGetOnRoadPrice() {
		priceVO.setOnRoadPrice("OnRoadPrice");

		priceVO.getOnRoadPrice();
		assertEquals("OnRoadPrice", priceVO.getOnRoadPrice());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.PriceVO#setOnRoadPrice(java.lang.String)}.
	 */
	@Test
	void testSetOnRoadPrice() {
		priceVO.setOnRoadPrice("OnRoadPrice");
		assertEquals("OnRoadPrice", priceVO.getOnRoadPrice());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.PriceVO#getExShowRoomPrice()}.
	 */
	@Test
	void testGetExShowRoomPrice() {
		priceVO.setExShowRoomPrice("ExShowRoomPrice");

		priceVO.getExShowRoomPrice();
		assertEquals("ExShowRoomPrice", priceVO.getExShowRoomPrice());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.PriceVO#setExShowRoomPrice(java.lang.String)}.
	 */
	@Test
	void testSetExShowRoomPrice() {
		priceVO.setExShowRoomPrice("ExShowRoomPrice");
		assertEquals("ExShowRoomPrice", priceVO.getExShowRoomPrice());
	}

	/**
	 * Test method for {@link com.heromotocorp.vida.core.vo.PriceVO#getPrice()}.
	 */
	@Test
	void testGetPrice() {
		priceVO.setPrice("Price");

		priceVO.getPrice();
		assertEquals("Price", priceVO.getPrice());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.PriceVO#setPrice(java.lang.String)}.
	 */
	@Test
	void testSetPrice() {
		priceVO.setPrice("Price");
		assertEquals("Price", priceVO.getPrice());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.PriceVO#getMinLeaseEMI()}.
	 */
	@Test
	void testGetMinLeaseEMI() {
		priceVO.setMinLeaseEMI("MinLeaseEMI");

		priceVO.getMinLeaseEMI();
		assertEquals("MinLeaseEMI", priceVO.getMinLeaseEMI());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.PriceVO#setMinLeaseEMI(java.lang.String)}.
	 */
	@Test
	void testSetMinLeaseEMI() {
		priceVO.setMinLeaseEMI("MinLeaseEMI");
		assertEquals("MinLeaseEMI", priceVO.getMinLeaseEMI());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.PriceVO#getMinLeaseDownPayment()}.
	 */
	@Test
	void testGetMinLeaseDownPayment() {
		priceVO.setMinLeaseDownPayment("MinLeaseDownPayment");

		priceVO.getMinLeaseDownPayment();
		assertEquals("MinLeaseDownPayment", priceVO.getMinLeaseDownPayment());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.PriceVO#setMinLeaseDownPayment(java.lang.String)}.
	 */
	@Test
	void testSetMinLeaseDownPayment() {
		priceVO.setMinLeaseDownPayment("MinLeaseDownPayment");
		assertEquals("MinLeaseDownPayment", priceVO.getMinLeaseDownPayment());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.PriceVO#getMinLoanDonpayment()}.
	 */
	@Test
	void testGetMinLoanDonpayment() {
		priceVO.setMinLoanDonpayment("MinLoanDonpayment");

		priceVO.getMinLoanDonpayment();
		assertEquals("MinLoanDonpayment", priceVO.getMinLoanDonpayment());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.PriceVO#setMinLoanDonpayment(java.lang.String)}.
	 */
	@Test
	void testSetMinLoanDonpayment() {
		priceVO.setMinLoanDonpayment("MinLoanDonpayment");
		assertEquals("MinLoanDonpayment", priceVO.getMinLoanDonpayment());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.PriceVO#getMinLoanEMI()}.
	 */
	@Test
	void testGetMinLoanEMI() {
		priceVO.setMinLoanEMI("MinLoanEMI");

		priceVO.getMinLoanEMI();
		assertEquals("MinLoanEMI", priceVO.getMinLoanEMI());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.PriceVO#setMinLoanEMI(java.lang.String)}.
	 */
	@Test
	void testSetMinLoanEMI() {
		priceVO.setMinLoanEMI("MinLoanEMI");
		assertEquals("MinLoanEMI", priceVO.getMinLoanEMI());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.PriceVO#getLeaseOfferRL()}.
	 */
	@Test
	void testGetLeaseOfferRL() {
		priceVO.setLeaseOfferRL("LeaseOfferRL");

		priceVO.getLeaseOfferRL();
		assertEquals("LeaseOfferRL", priceVO.getLeaseOfferRL());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.PriceVO#setLeaseOfferRL(java.lang.String)}.
	 */
	@Test
	void testSetLeaseOfferRL() {
		priceVO.setLeaseOfferRL("LeaseOfferRL");
		assertEquals("LeaseOfferRL", priceVO.getLeaseOfferRL());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.PriceVO#getLoanOfferURL()}.
	 */
	@Test
	void testGetLoanOfferURL() {
		priceVO.setLoanOfferURL("LoanOfferURL");

		priceVO.getLoanOfferURL();
		assertEquals("LoanOfferURL", priceVO.getLoanOfferURL());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.PriceVO#setLoanOfferURL(java.lang.String)}.
	 */
	@Test
	void testSetLoanOfferURL() {
		priceVO.setLoanOfferURL("LoanOfferURL");
		assertEquals("LoanOfferURL", priceVO.getLoanOfferURL());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.PriceVO#getEffectivePrice()}.
	 */
	@Test
	void testGetEffectivePrice() {
		priceVO.setEffectivePrice("EffectivePrice");

		priceVO.getEffectivePrice();
		assertEquals("EffectivePrice", priceVO.getEffectivePrice());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.PriceVO#setEffectivePrice(java.lang.String)}.
	 */
	@Test
	void testSetEffectivePrice() {
		priceVO.setEffectivePrice("EffectivePrice");
		assertEquals("EffectivePrice", priceVO.getEffectivePrice());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.PriceVO#getPortablechargerPrice()}.
	 */
	@Test
	void testGetPortablechargerPrice() {
		priceVO.setPortablechargerPrice("PortablechargerPrice");

		priceVO.getPortablechargerPrice();
		assertEquals("PortablechargerPrice", priceVO.getPortablechargerPrice());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.PriceVO#setPortablechargerPrice(java.lang.String)}.
	 */
	@Test
	void testSetPortablechargerPrice() {
		priceVO.setPortablechargerPrice("PortablechargerPrice");
		assertEquals("PortablechargerPrice", priceVO.getPortablechargerPrice());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.PriceVO#getFame2IncentivePrice()}.
	 */
	@Test
	void testGetFame2IncentivePrice() {
		priceVO.setFame2IncentivePrice("Fame2IncentivePrice");

		priceVO.getFame2IncentivePrice();
		assertEquals("Fame2IncentivePrice", priceVO.getFame2IncentivePrice());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.PriceVO#setFame2IncentivePrice(java.lang.String)}.
	 */
	@Test
	void testSetFame2IncentivePrice() {
		priceVO.setFame2IncentivePrice("Fame2IncentivePrice");
		assertEquals("Fame2IncentivePrice", priceVO.getFame2IncentivePrice());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.PriceVO#getStateSubsidyPrice()}.
	 */
	@Test
	void testGetStateSubsidyPrice() {
		priceVO.setStateSubsidyPrice("StateSubsidyPrice");

		priceVO.getStateSubsidyPrice();
		assertEquals("StateSubsidyPrice", priceVO.getStateSubsidyPrice());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.PriceVO#setStateSubsidyPrice(java.lang.String)}.
	 */
	@Test
	void testSetStateSubsidyPrice() {
		priceVO.setStateSubsidyPrice("StateSubsidyPrice");
		assertEquals("StateSubsidyPrice", priceVO.getStateSubsidyPrice());
	}

}
