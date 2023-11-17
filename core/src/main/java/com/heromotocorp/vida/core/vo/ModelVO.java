package com.heromotocorp.vida.core.vo;

import org.apache.commons.lang3.StringUtils;

/**
 * The Class ModelVO.
 */
public class ModelVO {

	/** The label. */
	private String label = StringUtils.EMPTY;

	/** The value. */
	private String value = StringUtils.EMPTY;
	
	/**
	 * Gets the label.
	 *
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Sets the label.
	 *
	 * @param label the new label
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Sets the value.
	 *
	 * @param value the new value
	 */
	public void setValue(String value) {
		this.value = value;
	}



}
