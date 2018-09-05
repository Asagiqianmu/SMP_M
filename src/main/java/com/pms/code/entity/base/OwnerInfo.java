package com.pms.code.entity.base;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/*
 * 业主信息实体类
 */
public class OwnerInfo {
	private int id;
	private String ownername;//业主姓名
	private String telphone;//业主联系电话
	private int pmid;//物业ID
	private String units;//x栋x单元
	private String dong;//x栋x单元
	private String zuo;//x栋x单元
	private String rooms;//房间号
	private double cashPledge;//房间押金
	private double rental;//租金
	private int houseType;//房子类型
	private int isliving;//是否居住，0否，1是
	private String leave_time;//退房时间
	private Timestamp createtime;//创建时间
	private String create_time;//创建时间
	private String managementDepartmentName;//小区办事处名字
	private String province;//小区所在省
	private String city;//小区所在市
	private String area;//小区所在区
	private String houseName;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOwnername() {
		return ownername;
	}
	public void setOwnername(String ownername) {
		this.ownername = ownername;
	}
	public String getTelphone() {
		return telphone;
	}
	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	 
	public int getPmid() {
		return pmid;
	}
	public void setPmid(int pmid) {
		this.pmid = pmid;
	}
	public String getUnits() {
		return units;
	}
	public void setUnits(String units) {
		setDong(units.split("栋")[0]);
		setZuo(units.split("栋")[1].split("座")[0]);
		this.units = units;
	}
	public String getDong() {
		return dong;
	}
	public void setDong(String dong) {
		this.dong = dong;
	}
	public String getZuo() {
		return zuo;
	}
	public void setZuo(String zuo) {
		this.zuo = zuo;
	}
	public String getRooms() {
		return rooms;
	}
	public void setRooms(String rooms) {
		this.rooms = rooms;
	}
	public int getIsliving() {
		return isliving;
	}
	public void setIsliving(int isliving) {
		this.isliving = isliving;
	}
	public String getLeave_time() {
		return leave_time;
	}
	public void setLeave_time(String leave_time) {
		this.leave_time = leave_time;
	}
	public Timestamp getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Timestamp createtime) {
		setCreate_time(new SimpleDateFormat("yyyy年MM月dd日").format(createtime));
		this.createtime = createtime;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	
	public String getManagementDepartmentName() {
		return managementDepartmentName;
	}
	public void setManagementDepartmentName(String managementDepartmentName) {
		this.managementDepartmentName = managementDepartmentName;
	}
	
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	
	public double getCashPledge() {
		return cashPledge;
	}
	public void setCashPledge(double cashPledge) {
		this.cashPledge = cashPledge;
	}
	public double getRental() {
		return rental;
	}
	public void setRental(double rental) {
		this.rental = rental;
	}
	
	public int getHouseType() {
		return houseType;
	}
	public void setHouseType(int houseType) {
		this.houseType = houseType;
	}
	public String getHouseName() {
		return houseName;
	}
	public void setHouseName(String houseName) {
		this.houseName = houseName;
	}
	@Override
	public String toString() {
		return "OwnerInfo [id=" + id + ", ownername=" + ownername + ", telphone=" + telphone + ", pmid=" + pmid
				+ ", units=" + units + ", dong=" + dong + ", zuo=" + zuo + ", rooms=" + rooms + ", cashPledge="
				+ cashPledge + ", rental=" + rental + ", houseType=" + houseType + ", isliving=" + isliving
				+ ", leave_time=" + leave_time + ", createtime=" + createtime + ", create_time=" + create_time
				+ ", managementDepartmentName=" + managementDepartmentName + ", province=" + province + ", city=" + city
				+ ", area=" + area + ", houseName=" + houseName + "]";
	}
	
}
