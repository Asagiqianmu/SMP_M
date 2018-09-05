package com.pms.code.entity.base;

import java.sql.Timestamp;

public class EquipmentSysProvider {
	private int id;
	private String company_name;//门锁公司名称
	private String services;//功能服务
	private String contacts;//联系人
	private String telphone;//联系电话
	private String host;//服务器地址IP:port
	private String sys_username;//系统登录账号
	private String sys_passwd;//系统登录密码
	private Timestamp createTime;// 创建时间
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public String getServices() {
		return services;
	}
	public void setServices(String services) {
		this.services = services;
	}
	public String getContacts() {
		return contacts;
	}
	public void setContacts(String contacts) {
		this.contacts = contacts;
	}
	public String getTelphone() {
		return telphone;
	}
	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getSys_username() {
		return sys_username;
	}
	public void setSys_username(String sys_username) {
		this.sys_username = sys_username;
	}
	public String getSys_passwd() {
		return sys_passwd;
	}
	public void setSys_passwd(String sys_passwd) {
		this.sys_passwd = sys_passwd;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	@Override
	public String toString() {
		return "EquipmentSysProvider [id=" + id + ", company_name=" + company_name + ", services=" + services + ", contacts=" + contacts + ", telphone=" + telphone + ", host=" + host
				+ ", sys_username=" + sys_username + ", sys_passwd=" + sys_passwd + ", createTime=" + createTime + "]";
	}
	
}
