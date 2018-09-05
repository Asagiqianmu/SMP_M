package com.pms.code.entity.base;

import java.sql.Timestamp;

/**
 * 业主账户实体类
 * @author Dell
 *
 */
public class OwnerAccount {
	private int id;
	private String telphone;//电话
	private String userpwd;//密码 
	private double balance;
	private Timestamp createtime;//创建时间 
	
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
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
	public String getUserpwd() {
		return userpwd;
	}
	public void setUserpwd(String userpwd) {
		this.userpwd = userpwd;
	}
	public Timestamp getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}
	@Override
	public String toString() {
		return "OwnerAccount [id=" + id + ", telphone=" + telphone + ", userpwd=" + userpwd + ", balance=" + balance + ", createtime=" + createtime + "]";
	}
	
}
