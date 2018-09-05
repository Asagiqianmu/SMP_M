package com.pms.code.entity.base;

import java.sql.Timestamp;

/*
 * 门锁实体类
 */
public class UnlockingKey {

	private int id;//钥匙ID
	private int p_m_id;//管理处ID
	private String keyname;//钥匙名称
	private String doorNum;//门口机账号
	private int type;//钥匙类型
	private int dlc_id;//门锁公司ID
	private Timestamp createTime;//创建时间
	private EquipmentSysProvider equipmentSysProvider;
	private LockInfo lockInfo;
	 
	public EquipmentSysProvider getEquipmentSysProvider() {
		return equipmentSysProvider;
	}
	public void setEquipmentSysProvider(EquipmentSysProvider equipmentSysProvider) {
		this.equipmentSysProvider = equipmentSysProvider;
	}
	public LockInfo getLockInfo() {
		return lockInfo;
	}
	public void setLockInfo(LockInfo lockInfo) {
		this.lockInfo = lockInfo;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getP_m_id() {
		return p_m_id;
	}
	public void setP_m_id(int p_m_id) {
		this.p_m_id = p_m_id;
	}
	public String getKeyname() {
		return keyname;
	}
	public void setKeyname(String keyname) {
		this.keyname = keyname;
	}
	public String getDoorNum() {
		return doorNum;
	}
	public void setDoorNum(String doorNum) {
		this.doorNum = doorNum;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getDlc_id() {
		return dlc_id;
	}
	public void setDlc_id(int dlc_id) {
		this.dlc_id = dlc_id;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	} 
	
	@Override
	public String toString() {
		return "UnlockingKey [id=" + id + ", p_m_id=" + p_m_id + ", keyname=" + keyname + ", doorNum=" + doorNum
				+ ", type=" + type + ", dlc_id=" + dlc_id + ", createTime=" + createTime + ", equipmentSysProvider="
				+ equipmentSysProvider + ", lockInfo=" + lockInfo + "]";
	}
}
