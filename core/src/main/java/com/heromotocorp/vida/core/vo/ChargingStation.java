package com.heromotocorp.vida.core.vo;

import org.apache.commons.lang3.StringUtils;

public class ChargingStation {

	private String stationId = StringUtils.EMPTY;
	private String stationName = StringUtils.EMPTY;
	private String chargingStationAddress = StringUtils.EMPTY;
	
	private String longitude = StringUtils.EMPTY;
	private String latitude = StringUtils.EMPTY;
	//For Futre use 
	private String cityLocation = StringUtils.EMPTY;
	public String getStationId() {
		return stationId;
	}
	public void setStationId(String stationId) {
		this.stationId = stationId;
	}
	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	public String getChargingStationAddress() {
		return chargingStationAddress;
	}
	public void setChargingStationAddress(String chargingStationAddress) {
		this.chargingStationAddress = chargingStationAddress;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getCityLocation() {
		return cityLocation;
	}
	public void setCityLocation(String cityLocation) {
		this.cityLocation = cityLocation;
	}
	
	
	

	
}
