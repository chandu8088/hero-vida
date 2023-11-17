/**
 * 
 */
package com.heromotocorp.vida.core.vo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * JUnit test class for SearchResultVO
 */
class SearchResultVOTest {

	private SearchResultVO searchResultVO = new SearchResultVO();

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.SearchResultVO#getLink()}.
	 */
	@Test
	void testGetLink() {
		searchResultVO.setLink("Link");

		searchResultVO.getLink();
		assertEquals("Link", searchResultVO.getLink());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.SearchResultVO#setLink(java.lang.String)}.
	 */
	@Test
	void testSetLink() {
		searchResultVO.setLink("Link");
		assertEquals("Link", searchResultVO.getLink());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.SearchResultVO#getTitle()}.
	 */
	@Test
	void testGetTitle() {
		searchResultVO.setTitle("Title");

		searchResultVO.getTitle();
		assertEquals("Title", searchResultVO.getTitle());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.SearchResultVO#setTitle(java.lang.String)}.
	 */
	@Test
	void testSetTitle() {
		searchResultVO.setTitle("Title");
		assertEquals("Title", searchResultVO.getTitle());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.SearchResultVO#getExcerpts()}.
	 */
	@Test
	void testGetExcerpts() {
		searchResultVO.setExcerpts("Excerpts");

		searchResultVO.getExcerpts();
		assertEquals("Excerpts", searchResultVO.getExcerpts());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.SearchResultVO#setExcerpts(java.lang.String)}.
	 */
	@Test
	void testSetExcerpts() {
		searchResultVO.setExcerpts("Excerpts");
		assertEquals("Excerpts", searchResultVO.getExcerpts());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.SearchResultVO#getDescription()}.
	 */
	@Test
	void testGetDescription() {
		searchResultVO.setDescription("Description");

		searchResultVO.getDescription();
		assertEquals("Description", searchResultVO.getDescription());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.SearchResultVO#setDescription(java.lang.String)}.
	 */
	@Test
	void testSetDescription() {
		searchResultVO.setDescription("Description");
		assertEquals("Description", searchResultVO.getDescription());
	}

}
