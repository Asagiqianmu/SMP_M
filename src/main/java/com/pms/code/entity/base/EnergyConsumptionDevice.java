package com.pms.code.entity.base;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * 能耗设备表
 * 
 * @author Dell
 *
 */
public class EnergyConsumptionDevice {
	private int id;
	private String deviceModel;
	private int energy_type;
	private String energyTypeName;
	private int sub_type;
	private String subTypeName;
	private String device_id;
	private int device_status;
	private String strDeviceStstus;
	private String house_id;
	private double meter_value;
	private String createTime;
	private Timestamp createtime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Timestamp getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(createtime));
		this.createtime = createtime;
	}

	public String getDeviceModel() {
		return deviceModel;
	}

	public void setDeviceModel(String deviceModel) {
		this.deviceModel = deviceModel;
	}

	public int getEnergy_type() {
		return energy_type;
	}

	public void setEnergy_type(int energy_type) {
		if (energy_type == 0) {
			setEnergyTypeName("水表");
		} else if (energy_type == 1) {
			setEnergyTypeName("电表");
		}
		this.energy_type = energy_type;
	}

	public String getStrDeviceStstus() {
		return strDeviceStstus;
	}

	public void setStrDeviceStstus(String strDeviceStstus) {
		this.strDeviceStstus = strDeviceStstus;
	}

	public int getSub_type() {
		return sub_type;
	}

	public void setSub_type(int sub_type) {
		// 设备为水表
		if (energy_type == 0) {
			if (sub_type == 0) {
				setSubTypeName("冷水表");
			} else if (sub_type == 1) {
				setSubTypeName("热水表");
			}
		} else if (energy_type == 1) { // 设备为电表
			if (sub_type == 0) {
				setSubTypeName("费控");
			} else if (sub_type == 1) {
				setSubTypeName("普通");
			}
		}
		this.sub_type = sub_type;
	}

	public String getEnergyTypeName() {
		return energyTypeName;
	}

	public void setEnergyTypeName(String energyTypeName) {
		this.energyTypeName = energyTypeName;
	}

	public String getSubTypeName() {
		return subTypeName;
	}

	public void setSubTypeName(String subTypeName) {
		this.subTypeName = subTypeName;
	}

	public String getDevice_id() {
		return device_id;
	}

	public void setDevice_id(String device_id) {
		this.device_id = device_id;
	}

	public int getDevice_status() {
		return device_status;
	}

	public void setDevice_status(int device_status) {
		if (device_status == 0) {
			setStrDeviceStstus("在线");
		} else if (device_status == 1) {
			setStrDeviceStstus("离线");
		}
		this.device_status = device_status;
	}

	public String getHouse_id() {
		return house_id;
	}

	public void setHouse_id(String house_id) {
		this.house_id = house_id;
	}

	public double getMeter_value() {
		return meter_value;
	}

	public void setMeter_value(double meter_value) {
		this.meter_value = meter_value;
	}

	@Override
	public String toString() {
		return "EnergyConsumptionDevice [id=" + id + ", deviceModel=" + deviceModel + ", energy_type=" + energy_type
				+ ", energyTypeName=" + energyTypeName + ", sub_type=" + sub_type + ", subTypeName=" + subTypeName
				+ ", device_id=" + device_id + ", device_status=" + device_status + ", strDeviceStstus="
				+ strDeviceStstus + ", house_id=" + house_id + ", meter_value=" + meter_value + ", createTime="
				+ createTime + ", createtime=" + createtime + "]";
	}
}
