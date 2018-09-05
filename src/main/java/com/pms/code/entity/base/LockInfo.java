package com.pms.code.entity.base;

import java.sql.Timestamp;

/*
 * 门锁信息实体
 */
public class LockInfo {
	private int id;
	private String deviceModel;
	private int lockType;
	private String strLockType;
	private int deviceStatus;
	private String strDeviceStatus;
	private int powerStatus;
	private String strPowerStatus;
	private String deviceId;
	private Timestamp createtime;
	public int getId() {
		return id;
	}
	public String getStrDeviceStatus() {
		return strDeviceStatus;
	}
	public String getStrLockType() {
		return strLockType;
	}
	public void setStrLockType(String strLockType) {
		this.strLockType = strLockType;
	}
	
	public String getStrPowerStatus() {
		return strPowerStatus;
	}
	public void setStrPowerStatus(String strPowerStatus) {
		this.strPowerStatus = strPowerStatus;
	}
	public void setStrDeviceStatus(String strDeviceStatus) {
		this.strDeviceStatus = strDeviceStatus;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDeviceModel() {
		return deviceModel;
	}
	public void setDeviceModel(String deviceModel) {
		this.deviceModel = deviceModel;
	}
	public int getLockType() {
		return lockType;
	}
	public void setLockType(int lockType) {
		if(lockType==0){
			setStrLockType("室外锁(单间房)");
		}else if(lockType==1){
			setStrLockType("室外锁(多间房)");
		}else if(lockType==2){
			setStrLockType("室内锁");
		}
		this.lockType = lockType;
	}
	public int getDeviceStatus() {
		return deviceStatus;
	}
	public void setDeviceStatus(int deviceStatus) {
		if(deviceStatus==0){
			setStrDeviceStatus("在线");
		}else if(deviceStatus==1){
			setStrDeviceStatus("离线");
		}
		this.deviceStatus = deviceStatus;
	}
	public int getPowerStatus() {
		return powerStatus;
	}
	public void setPowerStatus(int powerStatus) {
		if(powerStatus==0){
			setStrPowerStatus("正常");
		}else if(powerStatus==1){
			setStrPowerStatus("低电量");
		}
		this.powerStatus = powerStatus;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public Timestamp getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}
	@Override
	public String toString() {
		return "LockInfo [id=" + id + ", deviceModel=" + deviceModel + ", lockType=" + lockType + ", strLockType="
				+ strLockType + ", deviceStatus=" + deviceStatus + ", strDeviceStatus=" + strDeviceStatus
				+ ", powerStatus=" + powerStatus + ", strPowerStatus=" + strPowerStatus + ", deviceId=" + deviceId
				+ ", createtime=" + createtime + "]";
	} 
}
