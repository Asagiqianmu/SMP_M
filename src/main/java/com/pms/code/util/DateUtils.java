package com.pms.code.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间日期工具类
 * Copyright (c) All Rights Reserved, 2016.
 * 版权所有                  kdf Information Technology Co .,Ltd
 * @Project		hhh
 * @File		DateUtils.java
 * @Date		2016年7月26日 上午9:20:46
 * @Author		cuimiao
 */
public class DateUtils {
	
	/**
	 * date 		yyyy-MM-dd HH:mm:ss
	 * 
	 * dateShort	yyyy-MM-dd
	 * 
	 * timestamp	时间戳
	 */
	
	private static String FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	private static String FORMAT_SHORT = "yyyy-MM-dd";
	
	
	/**
	 * 把二个时间的的年月日分别对比，完全相等就是同一天
	 * 
	 * @author dengfei E-mail:dengfei200857@163.com
	 * @time 2017年7月19日 下午1:47:38
	 * @param date1
	 * @param date2
	 * @return
	 */
	public synchronized static boolean isSameDate(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);

		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);

		boolean isSameYear = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR);
		boolean isSameMonth = isSameYear && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);
		boolean isSameDate = isSameMonth && cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH);

		return isSameDate;
	}
	
	/**
	 * 获得当前日期 yyyy-MM-dd 格式
	 * @Description: 
	 * @return
	 * @Date		2016年7月26日 上午9:31:30
	 * @Author		cuimiao
	 */
	public static String getTheDateShort(){
		SimpleDateFormat sdf=new SimpleDateFormat(FORMAT_SHORT);
		return sdf.format(new Date());
	}
	
	/**
	 * 获得当前时间 yyyy-MM-dd HH:mm:ss 格式
	 * @Description: 
	 * @return
	 * @Date		2016年7月26日 上午9:31:49
	 * @Author		cuimiao
	 */
	public static String getTheDate(){
		SimpleDateFormat sdf=new SimpleDateFormat(FORMAT);
		return sdf.format(new Date());
	}
	/**
	 * 获得当前时间(重载方法)
	 * @Description: 
	 * @return
	 * @Date		2016年7月26日 上午9:32:12
	 * @Author		cuimiao
	 */
	public static String getTheDate(String format){
		try{
			SimpleDateFormat sdf=new SimpleDateFormat(format);
			return sdf.format(new Date());
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 日期类型转化字符串
	 * @Description: 
	 * @return
	 * @Date		2016年7月26日 上午9:32:12
	 * @Author		cuimiao
	 */
	public static String dateToString(Date date){
		try{
			SimpleDateFormat sdf=new SimpleDateFormat(FORMAT);
			return sdf.format(date);
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 日期类型转化字符串(重载方法)
	 * @Description: 
	 * @return
	 * @Date		2016年7月26日 上午9:32:12
	 * @Author		cuimiao
	 */
	public static String dateToString(Date date , String format){
		try{
			SimpleDateFormat sdf=new SimpleDateFormat(format);
			return sdf.format(date);
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 日期类型转化字符串
	 * @Description: 
	 * @return
	 * @Date		2016年7月26日 上午9:32:12
	 * @Author		cuimiao
	 */
	public static String DateShortToString(Date date){
		try{
			SimpleDateFormat sdf=new SimpleDateFormat(FORMAT_SHORT);
			return sdf.format(date);
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 字符串类型转化为日期类 兼容 yyyy-MM-dd HH:mm:ss和yyyy-MM-dd两种 格式
	 * @Description: 
	 * @return
	 * @Date		2016年7月26日 上午9:31:49
	 * @Author		cuimiao
	 */
	public static Date stringToDate(String dateStr){
		SimpleDateFormat dft = null;
		if(dateStr.length() == FORMAT_SHORT.length()){
			dft = new SimpleDateFormat(FORMAT_SHORT);
		}else if(dateStr.length() >= FORMAT.length()){
			dft = new SimpleDateFormat(FORMAT);
		}else{
			return null;
		}
    	try {
			return dft.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	return null;
	}
	
	/**
	 * 字符串类型转化为日期类 yyyy-MM-dd HH:mm:ss 格式
	 * @Description: 
	 * @return
	 * @Date		2016年7月26日 上午9:31:49
	 * @Author		cuimiao
	 */
	public static Date stringToDate(String dateStr , String format){
    	try {
    		SimpleDateFormat dft = new SimpleDateFormat(format);
			return dft.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	return null;
	}
	
	/**
     * 日期加减 
     * @Description: 
     * @param date 日期
     * @param n 加上n天 当n为负数时为减去n天
     * @return 返回yyyy-MM-dd 格式的日期
     * @Date		2016年7月5日 下午5:31:13
     * @Author		cuimiao
     */
    public static Date changeDate(Date beginDate , int n){
    	try {
	    	SimpleDateFormat dft = new SimpleDateFormat(FORMAT);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(beginDate);
			calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + n);
			Date endDate = dft.parse(dft.format(calendar.getTime()));
			return endDate;
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	return null;
    }
    
    /**
     * 日期加减 (重载方法，可根据format格式化日期)
     * @Description: 
     * @param date 日期
     * @param n 加上n天 当n为负数时为减去n天
     * @return 返回yyyy-MM-dd 格式的日期
     * @Date		2016年7月5日 下午5:31:13
     * @Author		cuimiao
     */
    public static Date changeDate(Date beginDate , int n , String formate){
    	try {
	    	SimpleDateFormat dft = new SimpleDateFormat(formate);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(beginDate);
			calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + n);
			Date endDate = dft.parse(dft.format(calendar.getTime()));
			return endDate;
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	return null;
    }
    
    /**
     * 日期加减 
     * @Description: 
     * @param date 日期
     * @param n 加上n天 当n为负数时为减去n天
     * @return 返回yyyy-MM-dd HH:mm:ss格式的日期
     * @Date		2016年7月5日 下午5:31:13
     * @Author		cuimiao
     */
    public static Date changeDateShort(Date beginDate , int n){
    	try {
	    	SimpleDateFormat dft = new SimpleDateFormat(FORMAT_SHORT);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(beginDate);
			calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + n);
			Date endDate = dft.parse(dft.format(calendar.getTime()));
			return endDate;
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	return null;
    }
    
    /**
     * 获得当前时间戳
     * @Description: 
     * @return
     * @Date		2016年7月26日 上午10:22:20
     * @Author		cuimiao
     */
    public static long getTimestamp(){
    	return System.currentTimeMillis();
    }
    
    /**
     * 获得指定时间的时间戳(重载方法)
     * @Description: 
     * @param date
     * @return
     * @Date		2016年7月26日 上午10:27:16
     * @Author		cuimiao
     */
    public static long getTimestamp(Date date){
		try {
			Date dateStandard = new SimpleDateFormat(FORMAT).parse("1970-01-01 08:00:00");
	        long timestamp = date.getTime() - dateStandard.getTime();
	        return timestamp;
		} catch (Exception e) {
			e.printStackTrace();
		}
        return 0;
    }
    
    /**
     * 获得指定时间的时间戳(重载方法)
     * @Description: 
     * @param date
     * @return
     * @Date		2016年7月26日 上午10:27:16
     * @Author		cuimiao
     */
    public static long getTimestamp(String dateStr){
		try {
			Date dateStandard = new SimpleDateFormat(FORMAT).parse("1970-01-01 08:00:00");
	        long timestamp = stringToDate(dateStr).getTime() - dateStandard.getTime();
	        return timestamp;
		} catch (Exception e) {
			e.printStackTrace();
		}
        return 0;
    }
    
    /**
     * 时间戳转化为日期类型
     * @Description: 
     * @param timestamp
     * @return
     * @Date		2016年7月26日 上午10:30:07
     * @Author		cuimiao
     */
	public static Date timestampToDate(Timestamp timestamp){
		return timestamp;
	}
	
	/**
     * 时间戳转化为日期类型(重载方法)
     * @Description: 
     * @param timestamp
     * @return
     * @Date		2016年7月26日 上午10:30:07
     * @Author		cuimiao
     */
	public static Date timestampToDate(long timestamp){
        Date date = new Date(timestamp);  
		return date;
	}
	/** 
     * 两个时间相差距离多少天多少小时多少分多少秒 
     * by:cuimiao
     * @param str1 时间参数 1 格式：1990-01-01 12:00:00 
     * @param str2 时间参数 2 格式：2009-01-01 12:00:00 
     * @return long[] 返回值为：{天, 时, 分, 秒} 
     */
    public static long getDistanceTimes(String str1, String str2) {  
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        Date one;  
        Date two;  
        long day = 0;  
        long hour = 0;  
        long min = 0;  
        long sec = 0;  
        try {  
            one = df.parse(str1);  
            two = df.parse(str2);  
            long time1 = one.getTime();  
            long time2 = two.getTime();  
            long diff ;  
            diff = time2 - time1;
//            day = diff / (24 * 60 * 60 * 1000);  
//            hour = (diff / (60 * 60 * 1000) - day * 24);  
            min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);  
//            sec = (diff/1000-day*24*60*60-hour*60*60-min*60);  
        } catch (ParseException e) {  
            e.printStackTrace();  
        }  
        return min;  
    }  
	
	/**
	 * 返回firstDate与secondDate相差的天数 (firstDate-secondDate)
	 * @Description: 
	 * @param firstDate
	 * @param secondDate
	 * @return
	 * @Date		2016年7月27日 下午6:06:30
	 * @Author		cuimiao
	 */
	public static Integer daysBetween(Date firstDate,Date secondDate) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(FORMAT);
			firstDate = sdf.parse(sdf.format(firstDate));
			secondDate = sdf.parse(sdf.format(secondDate));
			Calendar cal = Calendar.getInstance();
			cal.setTime(firstDate);
			long firstTime = cal.getTimeInMillis();
			cal.setTime(secondDate);
			long secondTime = cal.getTimeInMillis();
			long betweenDays = (firstTime - secondTime) / (1000 * 3600 * 24);
			return Integer.parseInt(String.valueOf(betweenDays));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 根据给定的时间秒数来计算时间的天数，小时，分钟
	 * @return
	 * dfcloud
	 */
	public static String getTimeDay(int  times){
		int day = times/24/60/60;
		int hose=0;
		int sec=0;
		if(day>0){
			hose = (times-day*24*60*60)/60/60;
		}else{
			hose = times/60/60;
		}
		if(hose>0){
			sec = (times-day*24*60*60-hose*60*60)/60;
		}else{
			sec = times/60;
		}
		
		return (day>0?day+"天":"")+(hose>0?hose+"小时":"")+(sec>0?sec+"分钟":"");
	}
	
	/**
	 * 测试方法
	 * @Description: TODO
	 * @param args
	 * @Date		2016年7月27日 下午6:12:00
	 * @Author		cuimiao
	 */
	public static void main(String[] args) {
		System.out.println(new Date().getTime());
		System.out.println(daysBetween(stringToDate("2016-07-24"),new Date()));
	}
}
