package com.pms.code.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import javax.servlet.http.Cookie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.pms.code.entity.base.User;
import com.pms.code.exception.DAOException;
import com.pms.code.service.LoginService;
import com.pms.code.service.PropertyManagementDepartmentService;
import com.pms.code.util.CookieUtil;
import com.pms.code.util.JsonUtil;
import com.pms.code.util.MD5;
import com.pms.code.util.SHA256;

@Controller
public class LoginController extends BaseController{
	private Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private LoginService loginsServiceImpl;
	
	@Autowired
	private PropertyManagementDepartmentService propertyManagementDepartmentServiceImpl;

	/**
	 * 转首页
	 * @author dengfei E-mail:dengfei200857@163.com
	 * @time 2018年3月29日 上午9:26:25
	 * @return
	 */
	@RequestMapping("/toLogin")
	public String toLogin(){
		return "/index";
	}
	

	/**
	 * 注册
	 * @author dengfei E-mail:dengfei200857@163.com
	 * @time 2018年3月29日 上午9:26:44
	 * @param userName
	 * @param passWord
	 * @return
	 * @throws DAOException
	 */
	@RequestMapping("/register")
	@ResponseBody
	public String register(@RequestParam String userName,@RequestParam String passWord) throws DAOException{
		boolean result=loginsServiceImpl.registerUser(userName, passWord);
		//注册成功则发送激活邮件
		if(result) {
			Cookie[] cookies=request.getCookies();
			for(Cookie cookie:cookies){
				cookie.setMaxAge(0);
				cookie.setPath("/");
				response.addCookie(cookie);
			}
			map.put("code", "200");
			map.put("msg", "注册成功");
		}else{
			map.put("code", "201");
			map.put("msg", "注册失败");
		}
		return JsonUtil.objectToJson(map);
	}

	/**
	 * 登录
	 * @author dengfei E-mail:dengfei200857@163.com
	 * @time 2018年3月29日 上午9:27:07
	 * @param userName
	 * @param passWord
	 * @return
	 */
	@RequestMapping("/doLogin")
	@ResponseBody
	public String doLogin(@RequestParam String userName,@RequestParam String passWord){
		logger.error("-------userName-------"+userName); 
		//将接收到的用户名和密码生产密码串,与用户名一起从数据库中匹配
		String pwd = SHA256.getUserPassword(userName,MD5.encode(passWord).toLowerCase());
		HashMap<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("userName",userName);
		paramMap.put("passWord",pwd);
		User user=loginsServiceImpl.findUser(paramMap); 
		if(user==null){
			map.put("code", "201");
			map.put("msg", "账户名或密码不对");
			return JsonUtil.objectToJson(map); 
		}else{
			if(pwd.equals(user.getPassWord())){
				map.put("code", "200");
				map.put("user", user); 
				String token=MD5.encode(userName+new SimpleDateFormat("yyyy-MM-ddHH:MM:ss:SSS").format(new Date()));
//				将生成的token传到前台
				map.put("token", token); 
				session.setAttribute(token,user);
				session.setAttribute("pmd",propertyManagementDepartmentServiceImpl.queryAllPropertyManagementDepartmentList(user.getPid()));
				//将用户输入的用户名和密码保存在cookie中，以便用户下一次访问免输入
				CookieUtil.addCookie(response, "uName", userName, 7*24*60*60*1000);
				CookieUtil.addCookie(response, "pwd", passWord, 7*24*60*60*1000);
				return JsonUtil.objectToJson(map);
			}else{
				map.put("code", "201");
				map.put("msg", "密码错误");
				return JsonUtil.objectToJson(map);
			}
		}
	}
	
	/**
	 * 退出登录
	 * @author dengfei E-mail:dengfei200857@163.com
	 * @time 2018年3月29日 上午9:27:18
	 * @return
	 */
	@RequestMapping("/outLogin")
	public String outLogin(String token)
	{
//		清空用户session,返回登录页面
		session.removeAttribute(token);
		return "loginb";
	}
}
