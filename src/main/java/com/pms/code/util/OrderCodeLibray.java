package com.pms.code.util;
/**
 * @author songyb E-mail:songyanbiao@kdflink.com
 * @version 创建时间：2017年5月25日 下午3:17:21
 * @describe
 */
public enum OrderCodeLibray {
	DEVICE_REBEAT("re"),DEVICE_UP("up"),ROUTER_PW("routerPw"),WIFI_PW("wifiPw"),remote("remote");
	
	private String order;
	private OrderCodeLibray(String state){
		this.order=state;
	}
	public String getOrder(){
		return order;
	}
	public static void main(String[] args) {
		System.out.println(OrderCodeLibray.valueOf("DEVICE_REBEAT").getOrder());
	}
}
