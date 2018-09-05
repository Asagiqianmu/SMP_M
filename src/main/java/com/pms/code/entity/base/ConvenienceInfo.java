package com.pms.code.entity.base;

import java.sql.Timestamp;

/**
 * 服务公司信息实体类
 * @author Dell
 *
 */
public class ConvenienceInfo {
	private int id;
	private int pmid;//物业管理处ID
	private String service_type;//服务类型
	private String contacts;//联系人
	private String c_telphone;//联系方式
	private String c_address;//联系地址
	private String headimg;//头像
	private Timestamp createtime;//创建时间
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPmid() {
		return pmid;
	}
	public void setPmid(int pmid) {
		this.pmid = pmid;
	}
	public String getService_type() {
		return service_type;
	}
	public void setService_type(String service_type) {
		this.service_type = service_type;
	}
	public String getContacts() {
		return contacts;
	}
	public void setContacts(String contacts) {
		this.contacts = contacts;
	}
	public String getC_telphone() {
		return c_telphone;
	}
	public void setC_telphone(String c_telphone) {
		this.c_telphone = c_telphone;
	}
	public String getC_address() {
		return c_address;
	}
	public void setC_address(String c_address) {
		this.c_address = c_address;
	}
	public String getHeadimg() {
		return headimg;
	}
	public void setHeadimg(String headimg) {
		this.headimg = headimg;
	}
	public Timestamp getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}
	@Override
	public String toString() {
		return "ConvenienceInfo [id=" + id + ", pmid=" + pmid + ", service_type=" + service_type + ", contacts="
				+ contacts + ", c_telphone=" + c_telphone + ", c_address=" + c_address + ", headimg=" + headimg
				+ ", createtime=" + createtime + "]";
	} 
}
