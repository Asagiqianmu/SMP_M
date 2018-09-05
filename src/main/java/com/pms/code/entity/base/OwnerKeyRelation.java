package com.pms.code.entity.base;

import java.sql.Timestamp;

/*
 * 所有者钥匙关联表实体类
 */
public class OwnerKeyRelation {
	private int id;
	private int owner_id;//业主id
	private int key_id;//钥匙id
	private int status;//状态1为可用，2为无效
	private Timestamp createtime;//创建时间
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
	public int getKey_id() {
		return key_id;
	}
	public void setKey_id(int key_id) {
		this.key_id = key_id;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Timestamp getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}
	@Override
	public String toString() {
		return "OwnerKeyRelation [id=" + id + ", owner_id=" + owner_id + ", key_id=" + key_id + ", status=" + status
				+ ", createtime=" + createtime + "]";
	} 
	
}
