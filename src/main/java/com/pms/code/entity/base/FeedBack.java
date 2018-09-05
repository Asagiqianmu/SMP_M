package com.pms.code.entity.base;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * 反馈信息实体类
 * @author Dell
 *
 */
public class FeedBack {
	private int id;
	private int ownerId;//业主id
	private String content;//反馈内容
	private String conStr;//反馈内容
	private String telphone;//联系电话
	private Timestamp createtime;//创建时间
	private String create_time;//创建时间
	
	private OwnerAccount ownerAccount;
	
	public OwnerAccount getOwnerAccount() {
		return ownerAccount;
	}
	public void setOwnerAccount(OwnerAccount ownerAccount) {
		this.ownerAccount = ownerAccount;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		if(content.length()>26){
			setConStr(content.substring(0, 26)+"...");
		}else{
			setConStr(content);
		}
		this.content = content;
	}
	public String getConStr() {
		return conStr;
	}
	public void setConStr(String conStr) {
		this.conStr = conStr;
	}
	public String getTelphone() {
		return telphone;
	}
	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	public Timestamp getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Timestamp createtime) {
		setCreate_time(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(createtime));
		this.createtime = createtime;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	@Override
	public String toString() {
		return "FeedBack [id=" + id + ", ownerId=" + ownerId + ", content=" + content + ", telphone=" + telphone + ", createtime=" + createtime + ", create_time=" + create_time + ", ownerAccount="
				+ ownerAccount + "]";
	}
	
}
