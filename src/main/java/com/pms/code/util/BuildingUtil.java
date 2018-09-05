package com.pms.code.util;

/**
 * 房源信息编码组建规则
 * 
 * @author dengfei E-mail:dengfei200857@163.com
 * @time 2018年3月23日 下午1:22:44
 */
public class BuildingUtil {

	/**
	 * 组建小区物业编码（ID） 小区名称+00+小区主键ID
	 * 
	 * @author dengfei E-mail:dengfei200857@163.com
	 * @time 2018年3月23日 下午2:02:41
	 * @param areaName
	 * @param p_m_id
	 * @return
	 */
	public static String getBuildingID(String areaName, int p_m_id) {
		return areaName + "00" + p_m_id;
	}

	/**
	 * 组建房源编码(ID) 小区名称+单元+房号
	 * 
	 * @param areaName
	 * @param units
	 * @param rooms
	 * @return
	 */
	public static String getHouseID(String areaName, String units, String rooms) {
		System.out.println("----------------------------------"+areaName);
		System.out.println(units);
		System.out.println(rooms);
		String str1 = units.split("栋")[0];
		String str2 = units.split("栋")[1].split("座")[0];
		if (str1.equals("0") && str2.equals("0")) {
			return areaName + rooms;
		} else if (!str1.equals("0") && str2.equals("0")) {
			return areaName + units.split("栋")[0] + rooms;
		} else if (str1.equals("0") && !str2.equals("0")) {
			return areaName + units.split("栋")[1] + rooms;
		}else{
			return areaName + units + rooms;
		}
	}
}
