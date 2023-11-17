package com.heromotocorp.vida.core.vo;

import org.apache.commons.lang3.StringUtils;

/**
 * The Class ListModelVO.
 */
public class ListModelVO {

	/** The pagePath. */
	private String pagePath = null;

	/** The title. */
	private String title = StringUtils.EMPTY;

	/**
	 * Gets the pagePath.
	 *
	 * @return the pagePath
	 */
	public String getPagePath() {
		return pagePath;
	}

	/**
	 * Sets the pagePath.
	 *
	 * @param pagePath the new pagePath
	 */
	public void setPagePath(String pagePath) {
		this.pagePath = pagePath;
	}

	/**
	 * Gets the title.
	 *
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title.
	 *
	 * @param title the new title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

}
