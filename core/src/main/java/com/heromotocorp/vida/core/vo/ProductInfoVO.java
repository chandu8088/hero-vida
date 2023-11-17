package com.heromotocorp.vida.core.vo;

import org.apache.commons.lang3.StringUtils;

/**
 * The Class ProductInfoVO.
 */
public class ProductInfoVO {

	/** The price. */
	private String price = StringUtils.EMPTY;
	
	/** The city. */
	private String city = StringUtils.EMPTY;

	/**
	 * Gets the price.
	 *
	 * @return the price
	 */
	public String getPrice() {
		return price;
	}

	/**
	 * Sets the price.
	 *
	 * @param price the new price
	 */
	public void setPrice(String price) {
		this.price = price;
	}

	/**
	 * Gets the city.
	 *
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Sets the city.
	 *
	 * @param city the new city
	 */
	public void setCity(String city) {
		this.city = city;
	}

}
