package com.pms.code.entity.base;

public class HouseType {
	private int id;
	private int house_type;
	private String house_name;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getHouse_type() {
		return house_type;
	}
	public void setHouse_type(int house_type) {
		this.house_type = house_type;
	}
	public String getHouse_name() {
		return house_name;
	}
	public void setHouse_name(String house_name) {
		this.house_name = house_name;
	}
	@Override
	public String toString() {
		return "HouseType [id=" + id + ", house_type=" + house_type + ", house_name=" + house_name + "]";
	}
	
}
