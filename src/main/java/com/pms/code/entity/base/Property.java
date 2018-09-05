package com.pms.code.entity.base;

import java.sql.Timestamp;

/*
 * 公司实体类
 */
public class Property {
	private int id;
	private String company_name;//物业公司名称
	private String contacts;//联系人
	private String telphone;//联系电话
	private String addr;//通信地址
	private String appid;//微信小程序ID
	private String app_secret;//微信小程序app secret
	private String app_key;//微信小程序 app key
	private String mch_id;//微信商户号
	private String logo_url;//logo图片url
	private String bg_url;//背景图片url
	private String icon_url;//icon图标urll
	private Timestamp createtime;//创建时间
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
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getApp_secret() {
		return app_secret;
	}
	public void setApp_secret(String app_secret) {
		this.app_secret = app_secret;
	}
	public String getApp_key() {
		return app_key;
	}
	public void setApp_key(String app_key) {
		this.app_key = app_key;
	}
	public String getMch_id() {
		return mch_id;
	}
	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}
	public String getLogo_url() {
		return logo_url;
	}
	public void setLogo_url(String logo_url) {
		this.logo_url = logo_url;
	}
	public String getBg_url() {
		return bg_url;
	}
	public void setBg_url(String bg_url) {
		this.bg_url = bg_url;
	}
	public String getIcon_url() {
		return icon_url;
	}
	public void setIcon_url(String icon_url) {
		this.icon_url = icon_url;
	}
	public Timestamp getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}
	@Override
	public String toString() {
		return "Property [id=" + id + ", company_name=" + company_name + ", contacts=" + contacts + ", telphone=" + telphone + ", addr=" + addr + ", appid=" + appid + ", app_secret=" + app_secret
				+ ", app_key=" + app_key + ", mch_id=" + mch_id + ", logo_url=" + logo_url + ", bg_url=" + bg_url + ", icon_url=" + icon_url + ", createtime=" + createtime + "]";
	}
	
}
