package com.pms.code.entity.base;

import java.sql.Timestamp;

public class HouseID {
	private int id;
	private int p_m_id;
	private int infoid;
	private String houseid;
	private Timestamp createtime;
	private PropertyManagementDepartment propertyManagementDepartment;
	
	public PropertyManagementDepartment getPropertyManagementDepartment() {
		return propertyManagementDepartment;
	}
	public void setPropertyManagementDepartment(PropertyManagementDepartment propertyManagementDepartment) {
		this.propertyManagementDepartment = propertyManagementDepartment;
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
	public int getInfoid() {
		return infoid;
	}
	public void setInfoid(int infoid) {
		this.infoid = infoid;
	}
	public String getHouseid() {
		return houseid;
	}
	public void setHouseid(String houseid) {
		this.houseid = houseid;
	}
	public Timestamp getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}
	@Override
	public String toString() {
		return "HouseID [id=" + id + ", p_m_id=" + p_m_id + ", infoid=" + infoid + ", houseid=" + houseid
				+ ", createtime=" + createtime + ", propertyManagementDepartment=" + propertyManagementDepartment + "]";
	}
}
