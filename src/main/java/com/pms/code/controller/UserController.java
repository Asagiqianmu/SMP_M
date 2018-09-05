package com.pms.code.controller;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pms.code.entity.base.User;
import com.pms.code.entity.base.ValidateCode;
import com.pms.code.service.UserService;
import com.pms.code.service.ValidateCodeService;
import com.pms.code.util.DateUtil;
import com.pms.code.util.EmailUtil;
import com.pms.code.util.JsonUtil;
import com.pms.code.util.MD5;
import com.pms.code.util.RandomUtils;
import com.pms.code.util.SHA256;

import net.sf.json.JSONObject;

/**
 * 管理员控制层
 * 
 * @author Dell
 *
 */
@Controller
public class UserController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userServiceImpl;

	@Autowired
	private ValidateCodeService validateCodeServiceImpl;

	/**
	 * 发送邮件验证，开始进行修改密码。
	 * 
	 * @author dengfei E-mail:dengfei200857@163.com
	 * @time 2018年3月29日 下午5:05:16
	 * @param userName
	 * @param session
	 * @return
	 */
	@RequestMapping("/sendMailCode")
	@ResponseBody
	public String sendMailCode(@RequestParam String userName, HttpSession session) {
		User user = userServiceImpl.queryUserInfo(userName);
		if (user != null) {
			String email = user.getEmail();
			String validateCode = RandomUtils.generateString(6);
			/* String code = "123456"; */
			boolean flag = EmailUtil.sendRegisterMail(email, validateCode);
			HashMap<String, Object> paramMap = null;
			if (flag) {
				String createTime = DateUtil.currentDateTimeToString();
				paramMap = new HashMap<String, Object>();
				paramMap.put("user_name", user.getUserName());
				paramMap.put("code", validateCode);
				paramMap.put("createtime", createTime);
				if (validateCodeServiceImpl.insertValidateCode(paramMap)) {
					map.put("code", "200");
					map.put("userName", user.getUserName());
					map.put("ctime", createTime);
				}
			}
			logger.error("------修改密码------" + map);
			return JsonUtil.objectToJson(map);
		} else {
			map.put("code", "201");
			map.put("msg", "查询不到用户信息");
			logger.error("------修改密码------" + map);
			return JsonUtil.objectToJson(map);
		}
		// if(mapResult.size()==0){
		// mapResult.put("code","201");
		// mapResult.put("msg","网络错误请重试");
		// }
	}

	/**
	 * 修改密码
	 * 
	 * @return
	 */
	@RequestMapping("/modifyPassWord")
	@ResponseBody
	public String modifyPassWord(@RequestBody String data) {
		JSONObject json = JSONObject.fromObject(data);
		String validateCode = json.getString("validateCode");
		String userPwd = json.getString("userPwd");
		String confirmPwd = json.getString("confirmPwd");
		String ctime = json.getString("ctime");
		String userName = json.getString("userName");
		ValidateCode queryValidateCodeInfo = validateCodeServiceImpl.queryValidateCodeInfo(userName, ctime);
		if (queryValidateCodeInfo != null) {
			if (validateCode.equals(queryValidateCodeInfo.getCode())) {
				if (userPwd.equals(confirmPwd)) {
					User user = userServiceImpl.queryUserInfo(userName);
					String newPassWord = SHA256.getUserPassword(user.getUserName(), MD5.encode(userPwd).toLowerCase());
					HashMap<String, Object> paramMap = new HashMap<String, Object>();
					paramMap.put("passWord", newPassWord);
					paramMap.put("id", user.getId());
					boolean flag = userServiceImpl.updatePassWord(paramMap);
					if (flag) {
						map.put("code", "200");
						map.put("msg", "重置密码成功");
						logger.error("------重置密码成功------" + map);
						return JsonUtil.objectToJson(map);
					}
				}
			}
		}
		map.put("code", "201");
		map.put("msg", "重置密码失败");
		logger.error("------修改密码------" + map);
		return JsonUtil.objectToJson(map);
	}

	/**
	 * 登录后修改密码
	 * @param data
	 * @return
	 */
	@RequestMapping("/updatePassWord")
	@ResponseBody
	public String updatePassWord(@RequestBody String data) {
		JSONObject json = JSONObject.fromObject(data);
		String userPwd = json.getString("userPwd");
		String confirmPwd = json.getString("confirmPwd");
		String userName = json.getString("userName");
		if (userPwd.equals(confirmPwd)) {
			User user = userServiceImpl.queryUserInfo(userName);
			String newPassWord = SHA256.getUserPassword(user.getUserName(), MD5.encode(userPwd).toLowerCase());
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("passWord", newPassWord);
			paramMap.put("id", user.getId());
			boolean flag = userServiceImpl.updatePassWord(paramMap);
			if (flag) {
				map.put("code", "200");
				map.put("msg", "重置密码成功");
				logger.error("------重置密码成功------" + map);
				return JsonUtil.objectToJson(map);
			}
		}
		map.put("code", "201");
		map.put("msg", "重置密码失败");
		logger.error("------修改密码------" + map);
		return JsonUtil.objectToJson(map);
	}

}
