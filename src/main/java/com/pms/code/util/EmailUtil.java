package com.pms.code.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * 邮箱发送邮件工具类
 * @author dengfei E-mail:dengfei200857@163.com
 * @time 2018年3月29日 下午5:07:40
 */
public class EmailUtil {
	
	
	public static synchronized boolean sendRegisterMail(String email, String code) {
		Map< String, Object> map= new HashMap<String, Object>();
		if(!"".equals(code)){
			//用于加密的时间戳
			map.put("userName",email);
			map.put("timeStr",new Date().getTime());
			StringBuffer content = new StringBuffer();
			String title = "找回密码邮箱验证";//邮件标题
			content.append("<div style='padding: 10px;'>");
			content.append("<div style='text-align: center;font-size: 16px;line-height: 25px;'>找回密码 (重要)！</div>");
			content.append("<div style='line-height: 25px;'>");
			content.append("尊敬的用户");
			content.append("</div>");
			content.append("<div style='line-height: 25px;'>	您好 ！</div>");
			content.append("<div style='line-height: 25px;'>	您正在申请找回密，重设密码步骤需要此邮件的验证："+code+"</div>");
			content.append("<div style='line-height: 25px;'>此为系统消息，请勿回复</div>");
			return SendMail.sendMail(content, email, title);
		}else{
			return false;
		}
	}
}
