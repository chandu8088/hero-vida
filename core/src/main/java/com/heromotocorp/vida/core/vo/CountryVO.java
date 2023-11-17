package com.heromotocorp.vida.core.vo;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;

/**
 * The Class LocationPojo.
 */
public class CountryVO {

	/** The label. */
	@Expose
	private String label;

	/** The value. */
	@Expose
	private String value;

	/** The states. */
	@Expose
	private List<StateVO> states = new ArrayList<>();

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

	/**
	 * Gets the states.
	 *
	 * @return the states
	 */
	@java.lang.SuppressWarnings("squid:S2384")
	public List<StateVO> getStates() {
		return states;
	}

	/**
	 * Sets the states.
	 *
	 * @param states the new states
	 */
	public void setStates(List<StateVO> states) {
		this.states = new ArrayList<>(states);
	}


}
