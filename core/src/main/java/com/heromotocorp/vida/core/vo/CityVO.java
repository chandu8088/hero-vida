package com.heromotocorp.vida.core.vo;

import java.util.Objects;

import com.google.gson.annotations.Expose;

/**
 * The Class CityVO.
 */
public class CityVO {
	

	/** The label. */
	@Expose
	private String label;

	/** The value. */
	@Expose
	private String value;
	
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
