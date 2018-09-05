package com.pms.code.util;

import java.util.Arrays;

/**
 * @author songyb E-mail:songyanbiao@kdflink.com
 * @version 创建时间：2017年4月13日 下午2:48:57
 * @describe
 */
public class StringUtils {
	/**
	 * 使用list判断数组中包含某特定值
	 * @author songyb
	 * @2017年4月13日
	 */
	public static boolean useList(String[] arr, String targetValue) {
	    return Arrays.asList(arr).contains(targetValue);
	}
	public static void main(String[] args) {
		String s="dsadas,dsdsaaaa,ddddd";
		System.out.println(StringUtils.useList(s.split(","),"ddddd"));
	}
}
