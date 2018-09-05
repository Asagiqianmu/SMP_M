package com.pms.code.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;







import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

import net.sf.json.JSONObject;
/**
 * Copyright (c) All Rights Reserved, 2016.
 * 版权所有                  kdf Information Technology Co .,Ltd
 * @Project		deck
 * @File		MsgUtil.java
 * @Date		2016年11月18日 上午10:21:26
 * @Author		gyj
 */
public class MsgUtil {

	private static String URL = "";
	private static  String KEY = "";
	private static  String SECRET = "";
	private static  String SIGN = "";
	private static  String MESTYPE = "";

	static {
		ResourceBundle rb = ResourceBundle.getBundle("commen",Locale.getDefault());
		URL = rb.getString("URL");
		KEY = rb.getString("KEY");
		SECRET = rb.getString("SECRET");
		SIGN = rb.getString("SIGN");
		MESTYPE = rb.getString("MESTYPE");
	}
	 
	/**
	 * @Description 短信发送接口注册接口
	 * @param telephone--- 接收人电话
	 * @param templateCode--- 模板短信id(后台自定义模板)
	 * @param code--- 验证码
	 * @return 成功响应的话返回json格式的字符串,异常了返回error
	 */
	public static String paramer2SendCode(String telephone, String templateCode,String code) {
		JSONObject json = new JSONObject();
		json.put("code", code);
		json.put("phone", telephone);
		return sendMsg(json.toString(),telephone,templateCode);
	}
	
	/**
	 * @Description  忘记密码,修改密码,微信账号解绑发送验证码
	 * @date 2016年11月17日下午5:35:49
	 * @author guoyingjie
	 * @param telephone
	 * @param templateCode
	 * @param code
	 * @return
	 */
	public static String paramer1SendCode(String telephone, String templateCode,String code){
		JSONObject json = new JSONObject();
		json.put("code", code);
		return sendMsg(json.toString(),telephone,templateCode);
	}
	
	/**
	 * @Description  推荐码注册
	 * @date 2016年11月24日下午2:49:13
	 * @author guoyingjie
	 * @param telephone
	 * @param templateCode
	 * @param code
	 * @return
	 */
	public static String registerSendCode(String telephone, String templateCode,String code){
		JSONObject json = new JSONObject();
		json.put("code", code);
		json.put("phone", telephone);
		return sendMsg(json.toString(),telephone,templateCode);
	}
	
	
//	public static void main(String[] args) {
//		String s = paramer2SendCode("18292059695","SMS_12891467",randCode());
//		System.out.println(s);
//		boolean is = sendMsgIsSuccessful(s);
//		System.out.println(is);
//	}
	
	
	
	/**
	 * @Description  一句话描述此方法的功能
	 * @date 2016年11月17日下午5:28:26
	 * @author guoyingjie
	 * @param jsonStr
	 */
	public static String sendMsg(String jsonStr,String telephone,String templateCode){
		TaobaoClient client = new DefaultTaobaoClient(URL, KEY, SECRET);
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		req.setExtend("");
		req.setSmsType(MESTYPE);
		req.setSmsFreeSignName(SIGN);
		req.setSmsParamString(jsonStr);
		req.setRecNum(telephone);
		req.setSmsTemplateCode(templateCode);
		AlibabaAliqinFcSmsNumSendResponse rsp;
		try {
			rsp = client.execute(req);
			return rsp.getBody();
		} catch (ApiException e) {
			return "error";
		}
	}
	
	
	/**
	 * 生成四位随机数的验证码
	 * @return 6位验证码
	 */
	public static String randCode() {
		String code = "";
		for (int i = 0; i < 4; i++) {
			Random rand = new Random();
			code += rand.nextInt(9);
		}
		return code.trim();
	}
	
	/**
	 * 生成三位随机数的验证码
	 * @return 3位验证码
	 */
	public static String rand3Code() {
		String code = "";
		for (int i = 0; i < 3; i++) {
			Random rand = new Random();
			code += rand.nextInt(9);
		}
		return code.trim();
	}
	/**
	 * @Description 判断短信是否发送成功
	 * @param jsonString 发送短信后的响应结果
	 * @return false--发送失败,true--发送成功
	 */
	public static boolean sendMsgIsSuccessful(String jsonString) {
		try {
			JSONObject json = JSONObject.fromObject(jsonString);
			Object obj = json.get("alibaba_aliqin_fc_sms_num_send_response");
			if (obj == null || "".equals(obj)) {
				return false;
			} else {
				return true;
			}
		} catch (Exception e) {
			return false;
		}
	}
	/**
	 * @Description 返回错误信息
	 * @param jsonstring 发送短信的错误响应结果
	 * @return 错误信息的描述
	 */
	public static String backErrorMsg(String jsonstring) {
		JSONObject json = JSONObject.fromObject(jsonstring);
		if(json.get("error_response")!=null){
			JSONObject obj = JSONObject.fromObject(json.get("error_response"));
			return obj.getString("sub_msg") == null ? "error" : obj
					.getString("sub_msg");
		}else{
			return "error";
		}
	}
}
