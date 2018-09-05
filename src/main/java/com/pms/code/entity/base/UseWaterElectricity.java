package com.pms.code.entity.base;

import java.sql.Timestamp;

/*
 * 使用水电量实体类
 */
public class UseWaterElectricity {
	private int id;
	private String device_id;//设备ID
	private int type;//1水量，2电量
	private double use_count;//用量
	private String readtime;//起始时间
	private Timestamp createTime;// 创建时间
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDevice_id() {
		return device_id;
	}
	public void setDevice_id(String device_id) {
		this.device_id = device_id;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public double getUse_count() {
		return use_count;
	}
	public void setUse_count(double use_count) {
		this.use_count = use_count;
	}
	public String getReadtime() {
		return readtime;
	}
	public void setReadtime(String readtime) {
		this.readtime = readtime;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	@Override
	public String toString() {
		return "UseWaterElectricity [id=" + id + ", device_id=" + device_id + ", type=" + type + ", use_count=" + use_count + ", readtime=" + readtime + ", createTime=" + createTime + "]";
	}
	
}
