/**
 * 
 */
package com.heromotocorp.vida.core.vo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * JUnit test class for HotspotBeanModel
 */
class HotspotBeanModelTest {

	private HotspotBeanModel hotspotBeanModel = new HotspotBeanModel();

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.HotspotBeanModel#getImagepath()}.
	 */
	@Test
	void testGetImagepath() {
		hotspotBeanModel.getImagepath();
		assertEquals(null, hotspotBeanModel.getImagepath());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.HotspotBeanModel#getImagealttext()}.
	 */
	@Test
	void testGetImagealttext() {
		hotspotBeanModel.getImagealttext();
		assertEquals(null, hotspotBeanModel.getImagealttext());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.HotspotBeanModel#getItemTitle()}.
	 */
	@Test
	void testGetItemTitle() {
		hotspotBeanModel.getItemTitle();
		assertEquals(null, hotspotBeanModel.getItemTitle());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.HotspotBeanModel#getItemDesc()}.
	 */
	@Test
	void testGetItemDesc() {
		hotspotBeanModel.getItemDesc();
		assertEquals(null, hotspotBeanModel.getItemDesc());
	}

}
