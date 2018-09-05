package com.pms.code.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

/**
 * 
 * 类名：DateUtil 功能：时间格式化 详细： 作者：gc 版本：1.0 日期：2013-4-12 下午1:55:18
 *
 */
public class DateUtil {
	/**
	 * 默认 日期时间 格式 yyyy-MM-dd HH:mm:ss
	 */
	public static final String PATTERN_STANDARD = "yyyy-MM-dd HH:mm:ss";
	/**
	 * 默认 日期格式 yyyy-MM-dd
	 */
	public static final String PATTERN_DATE = "yyyy-MM-dd";
	/**
	 * 默认 时间格式
	 */
	public static final String PATTERN_TIME = "HH:mm:ss";
	/**
	 * 默认 日期格式 yyyy-MM-dd
	 */
	public static final String PATTERN_HM = "HH:mm";
	/**
	 * 每月1日
	 */
	public static final String PATTERN_MONTH = "yyyy-MM-01";

	public static final String PATTERN_date = "yyyyMMdd";
	/**
	 * 自动匹配字符串格式
	 */
	public static String[] parsePatterns = { "yyyyMMdd", "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm" };

	public static final String PATTERN_yyyyMMddHHmmss = "yyyyMMddHHmmss";

	/**
	 * Timestamp 格式化成字符串，使用默认格式 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param timestamp
	 * @return
	 */
	public static String timestamp2String(Timestamp timestamp) {
		if (timestamp == null) {
			return null;
		}
		return DateFormatUtils.format(timestamp, PATTERN_STANDARD);
	}

	/**
	 * Timestamp 格式化 自定义格式
	 * 
	 * @param timestamp
	 * @param pattern
	 * @return
	 */
	public static String timestamp2String(Timestamp timestamp, String pattern) {
		if (timestamp == null) {
			return null;
		}
		return DateFormatUtils.format(timestamp, pattern);
	}

	/**
	 * Date 格式化成字符串，使用默认格式 yyyy-MM-dd
	 * 
	 * @param date
	 * @return
	 */
	public static String date2String(Date date) {
		if (date == null) {
			return null;
		}
		return DateFormatUtils.format(date, PATTERN_DATE);
	}

	public static String date3String(Date date) {
		if (date == null) {
			return null;
		}
		return DateFormatUtils.format(date, PATTERN_HM);
	}

	/**
	 * Date 格式化 自定义格式
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String date2String(Date date, String pattern) {
		if (date == null) {
			return null;
		}
		return DateFormatUtils.format(date, pattern);
	}

	/**
	 * 获取当前时间
	 * 
	 * @return
	 */
	public static Timestamp currentTimestamp() {
		return new Timestamp(new Date().getTime());
	}

	public static String getCurrentTime() {
		return String.valueOf(System.currentTimeMillis() / 1000);
	}

	/**
	 * 获取当前日期yyyy-MM-dd String
	 * 
	 * @return
	 */
	public static String currentDateToString() {

		return date2String(new Date());
	}

	/**
	 * 获取当前时间 HH:mm:ss String类型
	 * 
	 * @return
	 */
	public static String currentTimeToString() {

		return date2String(new Date(), PATTERN_TIME);

	}

	/**
	 * 获取当前日期yyyy-MM-dd HH:mm:ss String
	 * 
	 * @return
	 */
	public static String currentDateTimeToString() {

		return date2String(new Date(), PATTERN_STANDARD);
	}

	/**
	 * 两个时间相减
	 * 
	 * @param firsttime
	 * @param secondtime
	 * @return
	 * @throws ParseException
	 */
	public static long subtractTime(String firsttime, String secondtime) throws ParseException {
		return string2Date(firsttime, PATTERN_STANDARD).getTime() - string2Date(secondtime, PATTERN_STANDARD).getTime();
	}

	/**
	 * 字符串转换为 Timestamp 自动匹配格式
	 * 
	 * @param strDateTime
	 * @return 如果传入字符串为null，或者空字符串，则返回null
	 * @throws ParseException
	 */
	public static Timestamp string2Timestamp(String strDateTime) throws ParseException {

		return new Timestamp(string2Date(strDateTime).getTime());
	}

	/**
	 * 字符串 转换为 Timestamp 传入字符串格式
	 * 
	 * @param strDateTime
	 * @param pattern
	 * @return
	 * @throws ParseException
	 */
	public static Timestamp string2Timestamp(String strDateTime, String pattern) throws ParseException {

		return new Timestamp(string2Date(strDateTime, pattern).getTime());
	}

	/**
	 * 字符串转换为 Date 自动匹配格式
	 * 
	 * @param strDate
	 * @return 如果传入字符串为null，或者空字符串，则返回null
	 * @throws ParseException
	 */
	public static Date string2Date(String strDate) throws ParseException {
		if (StringUtils.isBlank(strDate)) {
			return null;
		}
		// try {
		return DateUtils.parseDate(strDate.trim(), parsePatterns);
		// } catch (ParseException e) {
		// e.printStackTrace();
		// throw new MyRuntimeException("日期类型转换错误");
		// }
	}

	/**
	 * 字符串转换为 Date 传入字符串格式
	 * 
	 * @param strDate
	 *            时间
	 * @param pattern
	 *            字符串 格式
	 * @return 如果传入字符串为null，或者空字符串，则返回null
	 * @throws ParseException
	 */
	public static Date string2Date(String strDate, String pattern) throws ParseException {

		if (StringUtils.isBlank(strDate)) {
			return null;
		}
		// try {
		return DateUtils.parseDate(strDate.trim(), new String[] { pattern });
		// } catch (ParseException e) {
		// e.printStackTrace();
		// throw new MyRuntimeException("日期类型转换错误");
		// }
	}

	/**
	 * 前后移动日期
	 * 
	 * @param date
	 * @param move
	 *            -1:向前移动一天，1:向后移动一天
	 * @return
	 */
	public static Date moveDate(Date date, int move) {

		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, move);// 把日期往后增加一天.整数往后推,负数往前移动
		return calendar.getTime(); // 这个时间就是日期移动之后的时间

	}

	/***
	 * 时间字符串转换成long
	 * 
	 * @param strDate
	 * @param pattern
	 * @return
	 * @throws ParseException
	 */
	public static long string2long(String strDate, String pattern) throws ParseException {
		if (strDate == null || strDate.equals("")) {
			return 0l;
		}
		if (pattern == null || pattern.equals("")) {
			pattern = DateUtil.PATTERN_DATE;
		}
		Date d = string2Date(strDate, pattern);
		if (d == null) {
			return 0L;
		}
		return d.getTime();
	}

	/**
	 * 获得当前月初日期 yyyy-MM-01
	 * 
	 * @return
	 */
	public static String currentMonthDateToString() {

		return date2String(new Date(), PATTERN_MONTH);
	}

	/**
	 * 获取当天开始时间
	 * 
	 * @return
	 * @throws ParseException
	 */
	public static String currentDayDateStartToString(String stime) throws ParseException {
		// (stime.split(" ")[0], DateUtil.PATTERN_DATE);
		Date d = string2Date(stime.split(" ")[0], DateUtil.PATTERN_DATE);

		return date2String(d, "yyyy-MM-dd 00:00:01");
	}

	/**
	 * 获取当天结束时间
	 * 
	 * @return
	 * @throws ParseException
	 */
	public static String currentDayEndDateToString(String stime) throws ParseException {
		// (stime.split(" ")[0], DateUtil.PATTERN_DATE);
		Date d = string2Date(stime.split(" ")[0], DateUtil.PATTERN_DATE);

		return date2String(d, "yyyy-MM-dd 23:59:59");
	}

	/**
	 * 生成订单号的方法
	 */
	public static String getOrderNo() {
		int r1 = (int) (Math.random() * (10));// 产生2个0-9的随机数
		int r2 = (int) (Math.random() * (10));
		long now = System.currentTimeMillis() / 1000;// 一个10位的时间戳
		String orderNo = String.valueOf(now) + String.valueOf(r1) + String.valueOf(r2);
		return orderNo;
	}

	/**
	 * 生成项目编号的方法
	 */
	public static String getItemNo() {
		return date2Str(new Date(), "yyyyMMDDHHmmss");
	}

	/**
	 * 生成日期目录的方法
	 */
	public static String getDatePath() {
		return date2Str(new Date(), "yyyyMMDD");
	}

	/**
	 * 日期转字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String dateToString(Date date) {

		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");// 小写的mm表示的是分钟
			String ss = sdf.format(date);
			System.out.println("ss" + ss);
			return ss;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * timestamp转化为String
	 * 
	 * @param ts
	 * @return
	 */
	public static String timestampToString(Timestamp ts) {
		String tsStr = "";
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			tsStr = sdf.format(ts);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tsStr;
	}

	// 按照指定格式将字符串转换为Date类型
	public static Date str2Date(String str, String format) {
		if (null != str && !"".equals(str)) {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			Date date = null;
			try {
				date = sdf.parse(str);
				return date;
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	// 按照指定格式将Date类型转换为字符串
	public static String date2Str(Date date, String format) {
		if (null != date && !"".equals(date)) {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.format(date);
		}
		return null;
	}

	// 时间戳为秒的字符串转换为指定格式的Date字符串
	public static String strToDate(String str, String format) {
		Date date = null;
		if (str != null && !"".equals(str)) {
			long l = Long.parseLong(str) * 1000;
			date = new Date(l);
			return DateUtil.date2Str(date, format);
		}
		return null;
	}

	// 将指定格式的字符串转换为时间戳为秒的字符串
	public static String dateToStr(String str) {
		Date date = DateUtil.str2Date(str, "yyyy-MM-dd HH:mm:ss");
		return String.valueOf(date.getTime() / 1000);
	}

	public static void main(String[] args) {
		// try {
		// long l = DateUtil.subtractTime("2017-07-10 12:02:22","2017-07-10
		// 09:15:27");
		// System.out.println(l);
		// } catch (ParseException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// System.out.println(getItemNo());
		// System.out.println(moveDate(new Date(),1).toLocaleString());

		/*
		 * String t=date2String(new Date(), null);
		 * 
		 * System.out.println(t);
		 * 
		 * Timestamp end=string2Timestamp(t+" 23:59:59", null);
		 * 
		 * System.out.println(end);
		 * 
		 * System.out.println(string2Timestamp("2013-10-20 00:00:00",
		 * null).getTime());
		 * 
		 * 
		 * System.out.println("=========="+Pattern.matches("^192.168.1.*$",
		 * "192.168.2.1"));
		 * 
		 * 
		 * SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_DATE);
		 * System.out.println(sdf.format(0l));
		 */
		// System.out.println(DateUtil.str2Date("1453705165000", "yyyy-MM-dd
		// HH:mm:ss"));
		// System.out.println(DateUtil.getOrderNo());
		// System.out.println(DateUtil.strToDate("1453705165", "yyyy-MM-dd
		// HH:mm:ss"));
		// System.out.println(DateUtil.date2Str(DateUtil.moveDate(new Date(),
		// -7), "yyyy-MM-dd HH:mm:ss"));
		/*
		 * Date date = new Date(); System.out.println(date.getTime()/1000);
		 * System.out.println(String.valueOf(System.currentTimeMillis()/1000));
		 */
		/*
		 * System.out.println(DateUtil.dateToStr("2016-04-15 11:20:00"));
		 * System.out.println(new Date().getTime()/1000);
		 */
		System.out.println(timestamp2String(new Timestamp(new Date().getTime()), "M"));
	}

	/**
	 * 当前时间转化为yyyy-MM-dd HH:mm:ss
	 * 
	 * @return
	 */
	public static String getStringDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new Date());
	}

	/**
	 * @Description 计算两个时间相差几年几小时几分钟
	 * @param diff
	 *            获得两个时间的毫秒时间差
	 * @return
	 * @throws ParseException
	 */
	public static String dateDiff(long diff) throws ParseException {
		long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
		long nh = 1000 * 60 * 60;// 一小时的毫秒数
		long nm = 1000 * 60;// 一分钟的毫秒数
		long hour = diff % nd / nh;// 计算差多少小时
		long min = diff % nd % nh / nm;// 计算差多少分钟
		if (hour <= 0) {
			return "0时" + min + "分";
		}
		return hour + "时" + min + "分";
	}
	
	/**
	 * 获取指定时间对应的毫秒数
	 * 
	 * @param time
	 *            "HH:mm:ss"
	 * @return
	 */
	public static long getTimeMillis(Date date, String time) {
		try {
			DateFormat dateFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
			DateFormat dayFormat = new SimpleDateFormat("yy-MM-dd");
			Date curDate = dateFormat.parse(dayFormat.format(date) + " " + time);
			return curDate.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
