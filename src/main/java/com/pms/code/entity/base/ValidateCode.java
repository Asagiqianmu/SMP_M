package com.pms.code.entity.base;

public class ValidateCode {
	private int id;
	private String code;
	private String user_name;
	private String createtime;
	
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	
	@Override
	public String toString() {
		return "ValidateCode [id=" + id + ", code=" + code + ", user_name=" + user_name + ", createtime=" + createtime
				+ "]";
	}
}
