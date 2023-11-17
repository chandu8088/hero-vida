package com.heromotocorp.vida.core.vo;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class CityMasterVO {

	

	private String id = StringUtils.EMPTY;
	private String cityName = StringUtils.EMPTY;
	private String stateName = StringUtils.EMPTY;
	private String countryName = StringUtils.EMPTY;
	private String latitde = StringUtils.EMPTY;
	private String longitude = StringUtils.EMPTY;
	private String defaultPartnerIdCity = StringUtils.EMPTY;
	private String defaultBranchIdCity = StringUtils.EMPTY;
	
	List<VidaCenter> experienceCenter = new ArrayList<>();
	List<VidaCenter> serviceCenter = new ArrayList<>();
	List<ChargingStation> chargingStations = new ArrayList<>();
	List<SwappingStation> swappingStations = new ArrayList<>();

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getLatitde() {
		return latitde;
	}

	public void setLatitde(String latitde) {
		this.latitde = latitde;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getId() {
		return this.id;
	}
	
	public void setId() {
		this.id=getCityName().trim().toUpperCase()+"~"+getStateName().toUpperCase().trim()+"~"+getCountryName().toUpperCase().trim();
	}
	public List<VidaCenter> getExperienceCenter() {
		return experienceCenter;
	}

	public void setExperienceCenter(List<VidaCenter> experienceCenter) {
		this.experienceCenter = experienceCenter;
	}

	public List<VidaCenter> getServiceCenter() {
		return serviceCenter;
	}

	public void setServiceCenter(List<VidaCenter> serviceCenter) {
		this.serviceCenter = serviceCenter;
	}

	public List<ChargingStation> getChargigStations() {
		return chargingStations;
	}

	public void setChargingStations(List<ChargingStation> chargigStations) {
		this.chargingStations = chargigStations;
	}

	public String getDefaultPartnerIdCity() {
		return defaultPartnerIdCity;
	}

	public void setDefaultPartnerIdCity(String defaultPartnerIdCity) {
		this.defaultPartnerIdCity = defaultPartnerIdCity;
	}

	public String getDefaultBranchIdCity() {
		return defaultBranchIdCity;
	}

	public void setDefaultBranchIdCity(String defaultBranchIdCity) {
		this.defaultBranchIdCity = defaultBranchIdCity;
	}
	public List<SwappingStation> getSwappingStations() {
		return swappingStations;
	}

	public void setSwappingStations(List<SwappingStation> swappingStations) {
		this.swappingStations = swappingStations;
	}

	/*public void setId(String id) {
		this.id = id;
	}*/
	
}
