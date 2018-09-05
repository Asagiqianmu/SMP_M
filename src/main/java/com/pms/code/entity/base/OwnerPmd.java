package com.pms.code.entity.base;

import java.sql.Timestamp;

/**
 * 业主与小区物业关联关系实体
 * @author dengfei E-mail:dengfei200857@163.com
 * @time 2018年3月30日 下午12:37:16
 */
public class OwnerPmd {
	private int id;
	private int ownerid;
	private int p_m_id;
	private Timestamp createtime;//创建时间 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getOwnerid() {
		return ownerid;
	}
	public void setOwnerid(int ownerid) {
		this.ownerid = ownerid;
	}
	public int getP_m_id() {
		return p_m_id;
	}
	public void setP_m_id(int p_m_id) {
		this.p_m_id = p_m_id;
	}
	public Timestamp getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}
	@Override
	public String toString() {
		return "OwnerPmd [id=" + id + ", ownerid=" + ownerid + ", p_m_id=" + p_m_id + ", createtime=" + createtime + "]";
	}
	
}
