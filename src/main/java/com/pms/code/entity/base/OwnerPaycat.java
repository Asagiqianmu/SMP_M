package com.pms.code.entity.base;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;  

/*
 * 所有者缴费实体类
 */
public class OwnerPaycat {
	private int id;
	private int pmid;//物业管理处ID
	private int owner_id;//业主ID
	private String owner_name;//业主名字
	private int pay_item;//費用類型
	private double amount;//金额
	private String famount;//转化单位位元，保留小数点后两位
	private int status;//缴费状态0:已缴 1:未缴
	private Timestamp finishtime;//缴费完成时间
	private Timestamp createtime;//创建时间
	
	private String createFormatTime;//格式化创建时间
	private String finishFormatTime;//格式化完成时间
	
	private CostType costType;
	
	public CostType getCostType() {
		return costType;
	}
	public void setCostType(CostType costType) {
		this.costType = costType;
	}
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
	public int getOwner_id() {
		return owner_id;
	}
	public void setOwner_id(int owner_id) {
		this.owner_id = owner_id;
	}
	public String getOwner_name() {
		return owner_name;
	}
	public void setOwner_name(String owner_name) {
		this.owner_name = owner_name;
	}
	public int getPay_item() {
		return pay_item;
	}
	public void setPay_item(int pay_item) {
		this.pay_item = pay_item;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		setFamount(String.format("%.2f", (double)Math.round(amount)/100));
		this.amount = amount;
	}
	public String getFamount() {
		return famount;
	}
	public void setFamount(String famount) {
		this.famount = famount;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Timestamp getFinishtime() {
		return finishtime;
	}
	public void setFinishtime(Timestamp finishtime) {
		setFinishFormatTime(new SimpleDateFormat("yyyy-MM:dd HH:mm:ss").format(finishtime));
		this.finishtime = finishtime;
	}
	public Timestamp getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Timestamp createtime) {
		setCreateFormatTime(new  SimpleDateFormat("yyyy-MM:dd HH:mm:ss").format(createtime));
		this.createtime = createtime;
	}
	
	
	public String getCreateFormatTime() {
		return createFormatTime;
	}
	public void setCreateFormatTime(String createFormatTime) {
		this.createFormatTime = createFormatTime;
	}
	public String getFinishFormatTime() {
		return finishFormatTime;
	}
	public void setFinishFormatTime(String finishFormatTime) {
		this.finishFormatTime = finishFormatTime;
	}
	@Override
	public String toString() {
		return "OwnerPaycat [id=" + id + ", pmid=" + pmid + ", owner_id=" + owner_id + ", owner_name=" + owner_name + ", pay_item=" + pay_item + ", amount=" + amount + ", famount=" + famount
				+ ", status=" + status + ", finishtime=" + finishtime + ", createtime=" + createtime + ", createFormatTime=" + createFormatTime + ", finishFormatTime=" + finishFormatTime
				+ ", costType=" + costType + "]";
	}
	
}
