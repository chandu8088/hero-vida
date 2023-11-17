/**
 * 
 */
package com.heromotocorp.vida.core.vo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * JUnit test class for TwitterVO
 */
class TwitterVOTest {

	private TwitterVO twitterVO = new TwitterVO();

	/**
	 * Test method for {@link com.heromotocorp.vida.core.vo.TwitterVO#getText()}.
	 */
	@Test
	void testGetText() {
		twitterVO.setText("Text");

		twitterVO.getText();
		assertEquals("Text", twitterVO.getText());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.TwitterVO#setText(java.lang.String)}.
	 */
	@Test
	void testSetText() {
		twitterVO.setText("Text");
		assertEquals("Text", twitterVO.getText());
	}

	/**
	 * Test method for {@link com.heromotocorp.vida.core.vo.TwitterVO#getUrl()}.
	 */
	@Test
	void testGetUrl() {
		twitterVO.setUrl("Url");

		twitterVO.getUrl();
		assertEquals("Url", twitterVO.getUrl());
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.vo.TwitterVO#setUrl(java.lang.String)}.
	 */
	@Test
	void testSetUrl() {
		twitterVO.setUrl("Url");
		assertEquals("Url", twitterVO.getUrl());
	}

}
