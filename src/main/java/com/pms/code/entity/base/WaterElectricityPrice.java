package com.pms.code.entity.base;

import java.sql.Timestamp;

/*
 * 水电基价实体类
 */
public class WaterElectricityPrice {
	private int id;
	private double price;
	private String unit;
	private int pmid;
	private int costTypeId;
	private String payName;
	private Timestamp createTime;
	
	public int getCostTypeId() {
		return costTypeId;
	}
	public void setCostTypeId(int costTypeId) {
		this.costTypeId = costTypeId;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	 
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	 
	public int getPmid() {
		return pmid;
	}
	public void setPmid(int pmid) {
		this.pmid = pmid;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	 
	public String getPayName() {
		return payName;
	}
	public void setPayName(String payName) {
		this.payName = payName;
	}
	
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	@Override
	public String toString() {
		return "WaterElectricityPrice [id=" + id + ", price=" + price + ", unit=" + unit + ", pmid=" + pmid
				+ ", costTypeId=" + costTypeId + ", payName=" + payName + ", createTime=" + createTime + "]";
	}
}
