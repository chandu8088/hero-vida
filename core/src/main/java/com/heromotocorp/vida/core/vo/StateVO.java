package com.heromotocorp.vida.core.vo;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;

/**
 * The Class StateVO.
 */
public class StateVO {
	/** The label. */
	@Expose
	private String label;

	/** The value. */
	@Expose
	private String value;

	/** The cities. */
	@Expose
	private List<CityVO> cities= new ArrayList<>();

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
	 * Gets the cities.
	 *
	 * @return the cities
	 */
	@java.lang.SuppressWarnings("squid:S2384")
	public List<CityVO> getCities() {
		return cities;
	}

	/**
	 * Sets the cities.
	 *
	 * @param cities the new cities
	 */
	public void setCities(List<CityVO> cities) {
		this.cities = new ArrayList<>(cities);
	}

}
