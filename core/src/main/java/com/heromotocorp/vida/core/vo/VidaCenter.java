package com.heromotocorp.vida.core.vo;

import org.apache.commons.lang3.StringUtils;

public class VidaCenter {

	private String id = StringUtils.EMPTY;
	private String accountpartnerId = StringUtils.EMPTY;
	private String experienceCenterName = StringUtils.EMPTY;
	private String type  =StringUtils.EMPTY;
	private String address = StringUtils.EMPTY;
	private String postalCode = StringUtils.EMPTY;
	private String longitude = StringUtils.EMPTY;
	private String latitude = StringUtils.EMPTY;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAccountpartnerId() {
		return accountpartnerId;
	}
	public void setAccountpartnerId(String accountpartnerId) {
		this.accountpartnerId = accountpartnerId;
	}
	public String getExperienceCenterName() {
		return experienceCenterName;
	}
	public void setExperienceCenterName(String experienceCenterName) {
		this.experienceCenterName = experienceCenterName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
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
		
}
