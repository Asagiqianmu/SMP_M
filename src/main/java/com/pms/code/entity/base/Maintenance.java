package com.pms.code.entity.base;

import java.sql.Timestamp;

/**
 * 维修实体类
 * @author Dell
 *
 */
public class Maintenance {
	private String id;
	private int pmid;//物业管理处ID
	private String company_name;//公司名称
	private String main_business;//主营业务
	private String telephone;//公司电话号码
	private String address;//地址
	private String image_url;//图片地址
	private Timestamp createTime;//创建时间
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	} 
	public int getPmid() {
		return pmid;
	}
	public void setPmid(int pmid) {
		this.pmid = pmid;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public String getMain_business() {
		return main_business;
	}
	public void setMain_business(String main_business) {
		this.main_business = main_business;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getImage_url() {
		return image_url;
	}
	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	@Override
	public String toString() {
		return "Maintenance [id=" + id + ", pmid=" + pmid + ", company_name=" + company_name + ", main_business="
				+ main_business + ", telephone=" + telephone + ", address=" + address + ", image_url=" + image_url
				+ ", createTime=" + createTime + "]";
	}
	
	
}
