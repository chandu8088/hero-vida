/**
 * 
 */
package com.heromotocorp.vida.core.vo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * JUnit test class for StateVO
 */
class YoutubeVOTest {

	private YoutubeVO youtubeVO = new YoutubeVO();

	/**
	 * Test method for {@link com.heromotocorp.vida.core.vo.YoutubeVO#getTitle()}.
	 */
	@Test
	void testGetTitle() {
		youtubeVO.setTitle("Title");

		youtubeVO.getTitle();

		assertEquals("Title", youtubeVO.getTitle());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.YoutubeVO#setTitle(java.lang.String)}.
	 */
	@Test
	void testSetTitle() {
		youtubeVO.setTitle("Title");

		assertEquals("Title", youtubeVO.getTitle());
	}

	/**
	 * Test method for {@link com.heromotocorp.vida.core.vo.YoutubeVO#getDesc()}.
	 */
	@Test
	void testGetDesc() {
		youtubeVO.setDesc("Desc");

		youtubeVO.getDesc();
		assertEquals("Desc", youtubeVO.getDesc());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.YoutubeVO#setDesc(java.lang.String)}.
	 */
	@Test
	void testSetDesc() {
		youtubeVO.setDesc("Desc");
		assertEquals("Desc", youtubeVO.getDesc());
	}

	/**
	 * Test method for {@link com.heromotocorp.vida.core.vo.YoutubeVO#getVideoId()}.
	 */
	@Test
	void testGetVideoId() {
		youtubeVO.setVideoId("VideoId");

		youtubeVO.getVideoId();
		assertEquals("VideoId", youtubeVO.getVideoId());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.YoutubeVO#setVideoId(java.lang.String)}.
	 */
	@Test
	void testSetVideoId() {
		youtubeVO.setVideoId("VideoId");
		assertEquals("VideoId", youtubeVO.getVideoId());
	}

}
