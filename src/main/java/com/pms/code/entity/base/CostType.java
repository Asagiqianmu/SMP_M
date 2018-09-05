package com.pms.code.entity.base;

/**
 * 费用类型实体类
 * 
 * @author Dell
 *
 */
public class CostType {
	private int id;
	private int pay_item;//费用ID
	private String pay_name;//费用名称
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPay_item() {
		return pay_item;
	}
	public void setPay_item(int pay_item) {
		this.pay_item = pay_item;
	}
	public String getPay_name() {
		return pay_name;
	}
	public void setPay_name(String pay_name) {
		this.pay_name = pay_name;
	}
	@Override
	public String toString() {
		return "CostType [id=" + id + ", pay_item=" + pay_item + ", pay_name=" + pay_name + "]";
	}
	
	
}
