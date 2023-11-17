/**
 * 
 */
package com.heromotocorp.vida.core.vo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * JUnit test class for ListModelVO
 */
class ListModelVOTest {

	private ListModelVO listModelVO = new ListModelVO();

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.ListModelVO#getPagePath()}.
	 */
	@Test
	void testGetPagePath() {
		listModelVO.setPagePath("path");

		listModelVO.getPagePath();
		assertEquals("path", listModelVO.getPagePath());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.ListModelVO#setPagePath(java.lang.String)}.
	 */
	@Test
	void testSetPagePath() {
		listModelVO.setPagePath("path");
		assertEquals("path", listModelVO.getPagePath());
	}

	/**
	 * Test method for {@link com.heromotocorp.vida.core.vo.ListModelVO#getTitle()}.
	 */
	@Test
	void testGetTitle() {
		listModelVO.setTitle("Title");

		listModelVO.getTitle();
		assertEquals("Title", listModelVO.getTitle());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.ListModelVO#setTitle(java.lang.String)}.
	 */
	@Test
	void testSetTitle() {
		listModelVO.setTitle("Title");
		assertEquals("Title", listModelVO.getTitle());
	}

}
