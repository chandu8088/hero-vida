package com.heromotocorp.vida.core.vo;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;


/**
 * The Class PriorityStateCityVO.
 */
public class PriorityStateCityVO {
	

	/** The state. */
	@Expose
	private String state;

	/** The cities. */
	@Expose
	private List<String> cities;

	/**
	 * Gets the state.
	 *
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * Sets the state.
	 *
	 * @param state the new state
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * Gets the cities.
	 *
	 * @return the cities
	 */
	@java.lang.SuppressWarnings("squid:S2384")
	public List<String> getCities() {
		return cities;
	}

	/**
	 * Sets the cities.
	 *
	 * @param cities the new cities
	 */
	public void setCities(List<String> cities) {
		this.cities = new ArrayList<>(cities);
	}

}
