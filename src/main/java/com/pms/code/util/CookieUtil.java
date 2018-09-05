package com.pms.code.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * cookie操作工具类
 * @author dengfei E-mail:dengfei200857@163.com
 * @time 2018年3月29日 上午10:13:39
 */
public class CookieUtil {
	/**
	 * 添加新的cookie
	 * @param response
	 * @param name 
	 * @param value
	 * @param maxAge
	 */
		public static void  addCookie(HttpServletResponse response, String name, String value, int maxAge){
			Cookie cookie = new Cookie(name, value);
			cookie.setPath("/");
			cookie.setMaxAge(maxAge);
			response.addCookie(cookie);
		}
		/**
		 * 判断指定cookie是否存在
		 * @param name
		 * @return
		 */
		public static boolean cookieIsExist(HttpServletRequest request,String name){
			Cookie[] cookies = request.getCookies();
			if(null != cookies){
				for(Cookie cookie : cookies){
					if(cookie.getName().equals(name)){
						return true;
					}
					
				}
				
			}
			return false;
		}
		
}
