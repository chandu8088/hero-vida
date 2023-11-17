package com.heromotocorp.vida.core.vo;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The Class Brand.
 */
public class Brand {

	/** The brand name. */
	@Expose
	@SerializedName("label")
	private String brandName;
	
	/** The value name. */
	@Expose
	@SerializedName("value")
	private String brandValue;

	/** The brand category. */
	@Expose
	@SerializedName("brand_category")
	private List<ExchangeVehicleVO> brandCategory;

	/**
	 * Gets the brand name.
	 *
	 * @return the brand name
	 */
	public String getBrandName() {
		return brandName;
	}

	/**
	 * Sets the brand name.
	 *
	 * @param brandName the new brand name
	 */
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	/**
	 * Gets the brand category.
	 *
	 * @return the brand category
	 */
	@java.lang.SuppressWarnings("squid:S2384")
	public List<ExchangeVehicleVO> getBrandCategory() {
		return brandCategory;
	}

	/**
	 * Sets the brand category.
	 *
	 * @param brandCategory the new brand category
	 */
	public void setBrandCategory(List<ExchangeVehicleVO> brandCategory) {
		this.brandCategory = new ArrayList<>(brandCategory);
	}

	/**
	 * Gets the brand value.
	 *
	 * @return the brand value
	 */
	public String getBrandValue() {
		return brandValue;
	}

	/**
	 * Sets the brand value.
	 *
	 * @param brandValue the new brand value
	 */
	public void setBrandValue(String brandValue) {
		this.brandValue = brandValue;
	}


}
