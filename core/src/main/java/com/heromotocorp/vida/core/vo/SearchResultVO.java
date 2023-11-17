package com.heromotocorp.vida.core.vo;

import com.google.gson.annotations.Expose;

/**
 * The Class SearchResultVO.
 */
public class SearchResultVO {

	/** The links. */
	@Expose
	private String link;
	
	/** The Title. */
	private String title;
	
	/** The description. */
	private String description;
	
	/** The excerpts. */
	private String excerpts;
	
	
	/**
	 * Gets the link.
	 *
	 * @return the link
	 */
	public String getLink() {
		return link;
	}

	/**
	 * Sets the link.
	 *
	 * @param link the new link
	 */
	public void setLink(String link) {
		this.link = link;
	}

	/**
	 * Gets the title.
	 * 
	 * @return
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

	/**
	 * Gets the excerpts.
	 * 
	 * @return
	 */
	public String getExcerpts() {
		return excerpts;
	}

	/**
	 * Sets the excerpts.
	 *
	 * @param excerpts the new excerpts
	 */
	public void setExcerpts(String excerpts) {
		this.excerpts = excerpts;
	}

	/**
	 * Gets the description.
	 * 
	 * @return
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

}
