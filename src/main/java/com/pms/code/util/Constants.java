/**
 * 
 */
package com.pms.code.util;

/**
 * @author zyl
 * @date 2016年5月25日
 * 
 */
public class Constants {

	public static final String SESSION_USER = "session_user";
	
	public static final String USER_TOKEN = "USER_TOKEN";
	
	public static final boolean SUCESS_STATE = true;
	
	public static final boolean ERROR_STATE = false;
	
	public static final String ERROR_TIP = "系统未知错误";
	
	public static final String VISITOR = "guest";
	
	public static final String DATE_FORMATE = "yyyyMMddHHmmsssss";
	
	
	public static final String LOGIN_SIGN = "LOGIN_SIGN";
	public static final String LOGIN_SUCCESS = "LOGIN_SUCCESS";

	public static final String DB_PATH = "db.properties";
	
	/**
	 * sql批量提交数目
	 */
	public static final Integer BATCH_SIZE = 100;
	
	public static class QueueName{
		//队列中使用到的队列名称
		public static final String QUEUE_USER = "QUEUE_USER";
	}
	
	public static final String UPLOAD_PATH = "/ydx/programfiles/project/image_new/userfiles/userImages/";//linux服务器图片实际上传的位置
	public static final String PICTURE_PATH = "/userfiles/userImages/";//数据库中图片存储的链接
	
	/**
	 * 易点鲜项目图片访问地址的基本路径
	 */
//	public static final String BASE_PATH = "http://139.224.24.64:6002";
	public static final String BASE_PATH = "http://admin.yidianxian.net";
	
	public static final String SYS_USER_OPERATE = "0";
	
	public static final String CREATE_BY = "1";
	public static final String UPDATE_BY = "1";
	
	public static final int DISTANCE_RADIUS = 30000;//地球半径，单位是：米-5公里
	
	public static final Integer PAGE_SIZE = 10;// 商品列表的页容量
	public static final Integer MESSAGE_PAGE_SIZE = 15;// 推送消息列表的页容量
	/**
	 * 易点鲜App使用到的阿里云短信模板
	 */
	public static final String APP_SMS_TEMPLATE_CODE_001  = "SMS_12985238";//注册验证码
	public static final String APP_SMS_TEMPLATE_CODE_002  = "SMS_13015110";//忘记密码验证码
	public static final String APP_SMS_TEMPLATE_CODE_003  = "SMS_13000092";//更换手机号验证码
	public static final String APP_SMS_TEMPLATE_CODE_004  = "SMS_13035202";//设置支付密码验证码
	public static final String APP_SMS_TEMPLATE_CODE_005  = "SMS_12990197";//修改支付密码验证码
	public static final String APP_SMS_TEMPLATE_CODE_006  = "SMS_13000093";//手机快捷登录验证码
	public static final String APP_SMS_TEMPLATE_CODE_007  = "SMS_13025165";//设置登录密码验证码
	public static final String APP_SMS_TEMPLATE_CODE_008  = "SMS_12990198";//修改登录密码验证码
	public static final String APP_SMS_TEMPLATE_CODE_009  = "SMS_16735260";//清空购物车提示
	public static final String APP_SMS_TEMPLATE_CODE_010  = "SMS_16720003";//购买社区商品到店取货提示
	public static final String APP_SMS_TEMPLATE_CODE_011  = "SMS_16695322";//待付款订单提示用户
}

