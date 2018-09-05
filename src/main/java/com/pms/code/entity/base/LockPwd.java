package com.pms.code.entity.base;

/**
 * 门锁密码表
 * @author Dell
 *
 */
public class LockPwd {
	private int id;
	private String telphone;
	private int doorid;
	private String pwd;
	private int type;
	private String pwdID;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTelphone() {
		return telphone;
	}
	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	public int getDoorid() {
		return doorid;
	}
	public void setDoorid(int doorid) {
		this.doorid = doorid;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getPwdID() {
		return pwdID;
	}
	public void setPwdID(String pwdID) {
		this.pwdID = pwdID;
	}
	@Override
	public String toString() {
		return "LockPwd [id=" + id + ", telphone=" + telphone + ", doorid=" + doorid + ", pwd=" + pwd + ", type=" + type
				+ ", pwdID=" + pwdID + "]";
	}
}
