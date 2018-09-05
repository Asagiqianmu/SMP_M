package com.pms.code.entity.base;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * 打开门记录实体类
 * 
 * @author Dell
 *
 */
public class OpenDoorRecord {
	private int id;
	private int ownerId;// 业主id
	private String keyname;// 钥匙名称
	private int isopen;// 1、开门失败，2开门成功
	private String style;
	private String iswho;
	private Timestamp createtime;// 创建时间
	private String createTime;

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getIswho() {
		return iswho;
	}

	public void setIswho(String iswho) {
		this.iswho = iswho;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}

	public String getKeyname() {
		return keyname;
	}

	public void setKeyname(String keyname) {
		this.keyname = keyname;
	}

	public int getIsopen() {
		return isopen;
	}

	public void setIsopen(int isopen) {
		this.isopen = isopen;
	}

	public Timestamp getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(createtime));
		this.createtime = createtime;
	}

	@Override
	public String toString() {
		return "OpenDoorRecord [id=" + id + ", ownerId=" + ownerId + ", keyname=" + keyname + ", isopen=" + isopen + ", createtime=" + createtime + "]";
	}

}
