package com.heromotocorp.vida.core.vo;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The Class ExchangeVehicleVO.
 */
public class ExchangeVehicleVO {
	
	/** The category. */
	@Expose
	@SerializedName("label")
	private String name = StringUtils.EMPTY;
	
	/** The value. */
	@Expose
	@SerializedName("value")
	private String nameValue = StringUtils.EMPTY;
	
	/** The brand category. */
	@Expose
	@SerializedName("brand_model")
	private List<ModelVO> brandModel;


	/** The cc list. */
	@Expose
	@SerializedName("cc")
	private List<ModelVO> ccList;

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the brand model.
	 *
	 * @return the brand model
	 */
	@java.lang.SuppressWarnings("squid:S2384")
	public List<ModelVO> getBrandModel() {
		return brandModel;
	}

	/**
	 * Sets the brand model.
	 *
	 * @param brandModel the new brand model
	 */
	public void setBrandModel(List<ModelVO> brandModel) {
		this.brandModel = new ArrayList<>(brandModel);
	}

	/**
	 * Gets the name value.
	 *
	 * @return the name value
	 */
	public String getNameValue() {
		return nameValue;
	}

	/**
	 * Sets the name value.
	 *
	 * @param nameValue the new name value
	 */
	public void setNameValue(String nameValue) {
		this.nameValue = nameValue;
	}

	
	
	/**
	 * Gets the cc list.
	 *
	 * @return the cc list
	 */
	@java.lang.SuppressWarnings("squid:S2384")
	public List<ModelVO> getCcList() {
		return ccList;
	}

	/**
	 * Sets the cc list.
	 *
	 * @param ccList the new cc list
	 */
	public void setCcList(List<ModelVO> ccList) {
		this.ccList = new ArrayList<>(ccList);
	}


}
