package com.heromotocorp.vida.core.vo;

/**
 * @author shissriv
 *
 */
public class ProductItemColourVariant {

	String __typename;
	String name;
	String sku;
	String range;
	String charging_time;
	String accelerator;
	String top_speed;
	String color;
	String colourCode;
	String description;
	String weight;
	String ridingModes;
	
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getColourCode() {
		return colourCode;
	}
	public void setColourCode(String colourCode) {
		this.colourCode = colourCode;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	String sf_id ;
	public String get__typename() {
		return __typename;
	}
	public void set_typename(String __typename) {
		this.__typename = __typename;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public String getRange() {
		return range;
	}
	public void setRange(String range) {
		this.range = range;
	}
	public String getCharging_time() {
		return charging_time;
	}
	public void setCharging_time(String charging_time) {
		this.charging_time = charging_time;
	}
	public String getAccelerator() {
		return accelerator;
	}
	public void setAccelerator(String accelerator) {
		this.accelerator = accelerator;
	}
	public String getTop_speed() {
		return top_speed;
	}
	public void setTop_speed(String top_speed) {
		this.top_speed = top_speed;
	}
	public String getSf_id() {
		return sf_id;
	}
	public void setSf_id(String sf_id) {
		this.sf_id = sf_id;
	}
	
	public String getRidingModes() {
		return ridingModes;
	}
	public void setRidingModes(String ridingModes) {
		this.ridingModes = ridingModes;
	}
	public String getSeatingType() {
		return seatingType;
	}
	public void setSeatingType(String seatingType) {
		this.seatingType = seatingType;
	}
	String seatingType;
	
	
	
}