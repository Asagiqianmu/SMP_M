package com.pms.code.util;

import java.util.UUID;

/**
 * 
 * 获取随机id
 * @author Administrator
 *
 */
public class IDUtil {
	
	/**
	 * @return UUID
	 */
	public static String getUUID(){
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}
