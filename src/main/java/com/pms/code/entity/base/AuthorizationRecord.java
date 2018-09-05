package com.pms.code.entity.base;

import java.sql.Timestamp;

public class AuthorizationRecord {
	private int id;
	private int owner_id;//授权人ID
	private String authorized_name;//被授权人姓名
	private String authorized_phone;//被授权人电话
	private String device_id;//门锁设备ID
	private String pwd;//开门密码
	private int pwdType;//0临时密码,2管家密码,客房密码
	private String mtoken;//用户凭证
	private int prescription;//时效(分钟)
	private int pwd_id;//门锁平台返回的密码id
	private Timestamp createTime;// 创建时间
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getOwner_id() {
		return owner_id;
	}
	public void setOwner_id(int owner_id) {
		this.owner_id = owner_id;
	}
	public String getAuthorized_name() {
		return authorized_name;
	}
	public void setAuthorized_name(String authorized_name) {
		this.authorized_name = authorized_name;
	}
	public String getAuthorized_phone() {
		return authorized_phone;
	}
	public void setAuthorized_phone(String authorized_phone) {
		this.authorized_phone = authorized_phone;
	}
	public String getDevice_id() {
		return device_id;
	}
	public void setDevice_id(String device_id) {
		this.device_id = device_id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public int getPwdType() {
		return pwdType;
	}
	public void setPwdType(int pwdType) {
		this.pwdType = pwdType;
	}
	public String getMtoken() {
		return mtoken;
	}
	public void setMtoken(String mtoken) {
		this.mtoken = mtoken;
	}
	public int getPrescription() {
		return prescription;
	}
	public void setPrescription(int prescription) {
		this.prescription = prescription;
	}
	public int getPwd_id() {
		return pwd_id;
	}
	public void setPwd_id(int pwd_id) {
		this.pwd_id = pwd_id;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	@Override
	public String toString() {
		return "AuthorizationRecord [id=" + id + ", owner_id=" + owner_id + ", authorized_name=" + authorized_name + ", authorized_phone=" + authorized_phone + ", device_id=" + device_id + ", pwd="
				+ pwd + ", pwdType=" + pwdType + ", mtoken=" + mtoken + ", prescription=" + prescription + ", pwd_id=" + pwd_id + ", createTime=" + createTime + "]";
	}
	
}
