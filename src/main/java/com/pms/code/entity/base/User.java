package com.pms.code.entity.base;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * 管理员实体类
 * @author Dell
 *
 */
public class User {
	private int id;
	private String userName;//用户账号 不与数据库关联，为业务逻辑操作
	private String passWord;//用户密码
	private int gm_type;//管理类型：1、超级管理员2、物业公司3、小区物业管理处
	private int pid;//物业id
	private int pmid;//小区物业管理处ID
	private String email;//绑定邮箱
	private Timestamp createTime;//创建时间
	private String createtime;
	
	private PropertyManagementDepartment pmd;
	
	private Property property;
	
	public Property getProperty() {
		return property;
	}
	public void setProperty(Property property) {
		this.property = property;
	}
	public PropertyManagementDepartment getPmd() {
		return pmd;
	}
	public void setPmd(PropertyManagementDepartment pmd) {
		this.pmd = pmd;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		setCreatetime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(createTime));
		this.createTime = createTime;
	}
	public int getGm_type() {
		return gm_type;
	}
	public void setGm_type(int gm_type) {
		this.gm_type = gm_type;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public int getPmid() {
		return pmid;
	}
	public void setPmid(int pmid) {
		this.pmid = pmid;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", passWord=" + passWord + ", gm_type=" + gm_type
				+ ", pid=" + pid + ", pmid=" + pmid + ", email=" + email + ", createTime=" + createTime
				+ ", createtime=" + createtime + ", pmd=" + pmd + ", property=" + property + "]";
	} 
}
