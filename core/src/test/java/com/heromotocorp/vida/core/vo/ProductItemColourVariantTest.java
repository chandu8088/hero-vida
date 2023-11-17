/**
 * 
 */
package com.heromotocorp.vida.core.vo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * JUnit test class for ProductItemColourVariant
 */
class ProductItemColourVariantTest {

	private ProductItemColourVariant productItemColourVariant = new ProductItemColourVariant();

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.ProductItemColourVariant#getWeight()}.
	 */
	@Test
	void testGetWeight() {
		productItemColourVariant.setWeight("10");

		productItemColourVariant.getWeight();
		assertEquals("10", productItemColourVariant.getWeight());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.ProductItemColourVariant#setWeight(java.lang.String)}.
	 */
	@Test
	void testSetWeight() {
		productItemColourVariant.setWeight("10");
		assertEquals("10", productItemColourVariant.getWeight());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.ProductItemColourVariant#getColor()}.
	 */
	@Test
	void testGetColor() {
		productItemColourVariant.setColor("Color");

		productItemColourVariant.getColor();
		assertEquals("Color", productItemColourVariant.getColor());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.ProductItemColourVariant#setColor(java.lang.String)}.
	 */
	@Test
	void testSetColor() {
		productItemColourVariant.setColor("Color");
		assertEquals("Color", productItemColourVariant.getColor());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.ProductItemColourVariant#getColourCode()}.
	 */
	@Test
	void testGetColourCode() {
		productItemColourVariant.setColourCode("ColourCode");

		productItemColourVariant.getColourCode();
		assertEquals("ColourCode", productItemColourVariant.getColourCode());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.ProductItemColourVariant#setColourCode(java.lang.String)}.
	 */
	@Test
	void testSetColourCode() {
		productItemColourVariant.setColourCode("ColourCode");
		assertEquals("ColourCode", productItemColourVariant.getColourCode());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.ProductItemColourVariant#getDescription()}.
	 */
	@Test
	void testGetDescription() {
		productItemColourVariant.setDescription("Description");

		productItemColourVariant.getDescription();
		assertEquals("Description", productItemColourVariant.getDescription());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.ProductItemColourVariant#setDescription(java.lang.String)}.
	 */
	@Test
	void testSetDescription() {
		productItemColourVariant.setDescription("Description");
		assertEquals("Description", productItemColourVariant.getDescription());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.ProductItemColourVariant#get__typename()}.
	 */
	@Test
	void testGet__typename() {
		productItemColourVariant.set_typename("__typename");

		productItemColourVariant.get__typename();
		assertEquals("__typename", productItemColourVariant.get__typename());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.ProductItemColourVariant#set_typename(java.lang.String)}.
	 */
	@Test
	void testSet_typename() {
		productItemColourVariant.set_typename("__typename");
		assertEquals("__typename", productItemColourVariant.get__typename());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.ProductItemColourVariant#getName()}.
	 */
	@Test
	void testGetName() {
		productItemColourVariant.setName("Name");

		productItemColourVariant.getName();
		assertEquals("Name", productItemColourVariant.getName());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.ProductItemColourVariant#setName(java.lang.String)}.
	 */
	@Test
	void testSetName() {
		productItemColourVariant.setName("Name");
		assertEquals("Name", productItemColourVariant.getName());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.ProductItemColourVariant#getSku()}.
	 */
	@Test
	void testGetSku() {
		productItemColourVariant.setSku("Sku");

		productItemColourVariant.getSku();
		assertEquals("Sku", productItemColourVariant.getSku());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.ProductItemColourVariant#setSku(java.lang.String)}.
	 */
	@Test
	void testSetSku() {
		productItemColourVariant.setSku("Sku");
		assertEquals("Sku", productItemColourVariant.getSku());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.ProductItemColourVariant#getRange()}.
	 */
	@Test
	void testGetRange() {
		productItemColourVariant.setRange("Range");

		productItemColourVariant.getRange();
		assertEquals("Range", productItemColourVariant.getRange());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.ProductItemColourVariant#setRange(java.lang.String)}.
	 */
	@Test
	void testSetRange() {
		productItemColourVariant.setRange("Range");
		assertEquals("Range", productItemColourVariant.getRange());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.ProductItemColourVariant#getCharging_time()}.
	 */
	@Test
	void testGetCharging_time() {
		productItemColourVariant.setCharging_time("Charging_time");

		productItemColourVariant.getCharging_time();
		assertEquals("Charging_time", productItemColourVariant.getCharging_time());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.ProductItemColourVariant#setCharging_time(java.lang.String)}.
	 */
	@Test
	void testSetCharging_time() {
		productItemColourVariant.setCharging_time("Charging_time");
		assertEquals("Charging_time", productItemColourVariant.getCharging_time());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.ProductItemColourVariant#getAccelerator()}.
	 */
	@Test
	void testGetAccelerator() {
		productItemColourVariant.setAccelerator("Accelerator");

		productItemColourVariant.getAccelerator();
		assertEquals("Accelerator", productItemColourVariant.getAccelerator());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.ProductItemColourVariant#setAccelerator(java.lang.String)}.
	 */
	@Test
	void testSetAccelerator() {
		productItemColourVariant.setAccelerator("Accelerator");
		assertEquals("Accelerator", productItemColourVariant.getAccelerator());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.ProductItemColourVariant#getTop_speed()}.
	 */
	@Test
	void testGetTop_speed() {
		productItemColourVariant.setTop_speed("Top_speed");

		productItemColourVariant.getTop_speed();
		assertEquals("Top_speed", productItemColourVariant.getTop_speed());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.ProductItemColourVariant#setTop_speed(java.lang.String)}.
	 */
	@Test
	void testSetTop_speed() {
		productItemColourVariant.setTop_speed("Top_speed");
		assertEquals("Top_speed", productItemColourVariant.getTop_speed());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.ProductItemColourVariant#getSf_id()}.
	 */
	@Test
	void testGetSf_id() {
		productItemColourVariant.setSf_id("Sf_id");

		productItemColourVariant.getSf_id();
		assertEquals("Sf_id", productItemColourVariant.getSf_id());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.ProductItemColourVariant#setSf_id(java.lang.String)}.
	 */
	@Test
	void testSetSf_id() {
		productItemColourVariant.setSf_id("Sf_id");
		assertEquals("Sf_id", productItemColourVariant.getSf_id());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.ProductItemColourVariant#getRidingModes()}.
	 */
	@Test
	void testGetRidingModes() {
		productItemColourVariant.setRidingModes("RidingModes");

		productItemColourVariant.getRidingModes();
		assertEquals("RidingModes", productItemColourVariant.getRidingModes());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.ProductItemColourVariant#setRidingModes(java.lang.String)}.
	 */
	@Test
	void testSetRidingModes() {
		productItemColourVariant.setRidingModes("RidingModes");
		assertEquals("RidingModes", productItemColourVariant.getRidingModes());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.ProductItemColourVariant#getSeatingType()}.
	 */
	@Test
	void testGetSeatingType() {
		productItemColourVariant.setSeatingType("SeatingType");

		productItemColourVariant.getSeatingType();
		assertEquals("SeatingType", productItemColourVariant.getSeatingType());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.ProductItemColourVariant#setSeatingType(java.lang.String)}.
	 */
	@Test
	void testSetSeatingType() {
		productItemColourVariant.setSeatingType("SeatingType");
		assertEquals("SeatingType", productItemColourVariant.getSeatingType());
	}

}
