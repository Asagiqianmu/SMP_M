package com.pms.code.entity.base;

import java.sql.Timestamp;

/**
 * 物业管理部门实体类
 * @author Dell
 *
 */
public class PropertyManagementDepartment {
	private int id;
	private int pid;//物业ID
	private String management_department_name;//物业管理处名称
	private String contact_number;//联系电话
	private String province;// 省份（例如广东省）
	private String city;// 城市（例如：深圳市）
	private String  area;// 区域（例如：南山区）
	private Timestamp createtime;//创建时间
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getManagement_department_name() {
		return management_department_name;
	}
	public void setManagement_department_name(String management_department_name) {
		this.management_department_name = management_department_name;
	}
	public String getContact_number() {
		return contact_number;
	}
	public void setContact_number(String contact_number) {
		this.contact_number = contact_number;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public Timestamp getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}
	@Override
	public String toString() {
		return "PropertyManagementDepartment [id=" + id + ", pid=" + pid + ", management_department_name=" + management_department_name + ", contact_number=" + contact_number + ", province="
				+ province + ", city=" + city + ", area=" + area + ", createtime=" + createtime + "]";
	}
	
	
 }
