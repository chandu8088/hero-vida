/**
 * 
 */
package com.heromotocorp.vida.core.vo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * JUnit test class for InstaVO
 */
class InstaVOTest {

	private InstaVO instaVO = new InstaVO();

	/**
	 * Test method for {@link com.heromotocorp.vida.core.vo.InstaVO#getMediaUrl()}.
	 */
	@Test
	void testGetMediaUrl() {
		instaVO.setMediaUrl("MediaUrl");

		instaVO.getMediaUrl();
		assertEquals("MediaUrl", instaVO.getMediaUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.InstaVO#setMediaUrl(java.lang.String)}.
	 */
	@Test
	void testSetMediaUrl() {
		instaVO.setMediaUrl("MediaUrl");
		assertEquals("MediaUrl", instaVO.getMediaUrl());
	}

	/**
	 * Test method for {@link com.heromotocorp.vida.core.vo.InstaVO#getUrl()}.
	 */
	@Test
	void testGetUrl() {
		instaVO.setUrl("Url");

		instaVO.getUrl();
		assertEquals("Url", instaVO.getUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.InstaVO#setUrl(java.lang.String)}.
	 */
	@Test
	void testSetUrl() {
		instaVO.setUrl("Url");
		assertEquals("Url", instaVO.getUrl());
	}

}
