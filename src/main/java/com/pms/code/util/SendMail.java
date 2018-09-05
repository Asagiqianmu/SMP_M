package com.pms.code.util;

import java.security.GeneralSecurityException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.util.MailSSLSocketFactory;

/**
 * 
 * @author dengfei E-mail:dengfei200857@163.com
 * @time 2018年3月29日 下午5:15:37
 */
public class SendMail {
	

	/** 发件服务器 */
	static final private String mailHost = "smtp.qq.com";
	/** 发件人账号 */
	static final private String sendUser = "1812244743@qq.com";
	/** 发件人密码 */
	static final private String password = "zjiudfncqjmjchfg";
	/** 邮件标题 */
	static final private String mailTitle = "系统邮件";


	public static synchronized boolean sendMail(StringBuffer content, String receiverMail, String title) {
		
		MailSSLSocketFactory sf = null;
		try {
			sf = new MailSSLSocketFactory();
		} catch (GeneralSecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}
		sf.setTrustAllHosts(true);
		
		// 配置发送邮件的环境属性
		final Properties props = new Properties();
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.ssl.socketFactory", sf);

		/*
		 * 可用的属性： mail.store.protocol / mail.transport.protocol / mail.host /
		 * mail.user / mail.from
		 */
		// 表示SMTP发送邮件，需要进行身份验证
		props.put("mail.smtp.auth", "true");

		props.put("mail.smtp.host", mailHost);// 这里是腾讯的发件服务器smtp.qq.com

		props.put("mail.user", sendUser);

		props.put("mail.password", password);
		

		// 构建授权信息，用于进行SMTP进行身份验证
		Authenticator authenticator = new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// 用户名、密码
				String userName = props.getProperty("mail.user");
				String password = props.getProperty("mail.password");
				return new PasswordAuthentication(userName, password);
			}
		};

		// 使用环境属性和授权信息，创建邮件会话
		Session mailSession = Session.getInstance(props, authenticator);
		// 创建邮件消息
		MimeMessage message = new MimeMessage(mailSession);
		// 设置发件人
		InternetAddress form;
		try {
			form = new InternetAddress(props.getProperty("mail.user"));
			message.setFrom(form);
			InternetAddress to = new InternetAddress(receiverMail);// 设置收件人
			message.setRecipient(RecipientType.TO, to);
			if (title != null) {
				message.setSubject(title);// TODO 设置邮件标题
			} else {
				message.setSubject(mailTitle);// TODO 设置邮件标题
			}
			// 设置邮件的内容体
			// message.setText(bean.getMailContent());//邮件内容，文本信息
			message.setContent(content.toString(), "text/html;charset=UTF-8");
			// 发送邮件
			Transport.send(message);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
