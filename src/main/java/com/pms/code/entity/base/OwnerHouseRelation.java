package com.pms.code.entity.base;

import java.sql.Timestamp;

/**
 * 业主与房屋信息关联关系实体
 * @author dengfei E-mail:dengfei200857@163.com
 * @time 2018年3月30日 下午12:37:16
 */
public class OwnerHouseRelation {
	private int id;
	private int ownerid;
	private int infoid;
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
	public int getInfoid() {
		return infoid;
	}
	public void setInfoid(int infoid) {
		this.infoid = infoid;
	}
	public Timestamp getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}
	@Override
	public String toString() {
		return "OwnerHouseRelation [id=" + id + ", ownerid=" + ownerid + ", infoid=" + infoid + ", createtime=" + createtime + "]";
	}
	
}
