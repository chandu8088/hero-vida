package com.heromotocorp.vida.core.vo;

import org.apache.commons.lang3.StringUtils;

/**
 * VO class for Swapping Station.
 *
 */
public class SwappingStation {
	
	private String swappingStationName = StringUtils.EMPTY;

	/**
	 * Gets Swapping Station Name.
	 * 
	 * @return swappingStationName.
	 */
	public String getSwappingStationName() {
		return swappingStationName;
	}

	/**
	 * Sets Swapping Station Name.
	 * 
	 * @param swappingStationName
	 */
	public void setSwappingStationName(String swappingStationName) {
		this.swappingStationName = swappingStationName;
	}

}
