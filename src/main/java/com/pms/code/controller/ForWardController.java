package com.pms.code.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.pms.code.entity.base.User;
import com.pms.code.service.UserService;

/**
 * 跳转jsp
 * 
 * @author Dell
 *
 */
@Controller
public class ForWardController extends BaseController {
	@Autowired
	private UserService userServiceImpl;

	@RequestMapping("/index")
	public String index(String page, String token, Model model) {
		User user = (User) session.getAttribute(token);
		if (user == null) {
			return login();
		}
		if ("1".equals(page)) {
			model.addAttribute("page", "sysNotice.jsp");
		} else if ("2".equals(page)) {
			model.addAttribute("page", "ownerAccount.jsp");
		} else if ("3".equals(page)) {
			model.addAttribute("page", "ownerPayCat.jsp");
		} else if ("4".equals(page)) {
			model.addAttribute("page", "opendoorrecord.jsp");
		} else if ("5".equals(page)) {
			model.addAttribute("page", "convenienceInfo.jsp");
		} else if ("6".equals(page)) {
			model.addAttribute("page", "maintenance.jsp");
		} else if ("7".equals(page)) {
			model.addAttribute("page", "feedback.jsp");
		}  else if ("8".equals(page)) {
			model.addAttribute("page", "waterElectricity.jsp");
		} else if ("9".equals(page)) {
			model.addAttribute("page", "recordquery.jsp");
		} else if ("10".equals(page)) {
			model.addAttribute("page", "Housingresource.jsp");
		} else if ("11".equals(page)) {
			model.addAttribute("page", "propertycompany.jsp");
		} else if ("12".equals(page)) {
			if(user.getGm_type()==2){
				User user2=userServiceImpl.queryUserPropertyById(user.getId());
				if(user2!=null){
					model.addAttribute("user2",user2);
				}
			} else if(user.getGm_type()==3){
				User user3= userServiceImpl.queryUserPmdById(user.getId());
				if(user3!=null){
					model.addAttribute("user2",user3);
				}
			} /*else if(user.getGm_type()==3){
				User user2= userServiceImpl.queryUserPmdById(user.getId());
				if(user2!=null){
					model.addAttribute("user2",user2);
				}
			}*/
			model.addAttribute("page", "change_psw.jsp");
		} else if ("12".equals(page)) {
			model.addAttribute("page", "loginb.jsp");
		} else {
			page = "1";
			System.out.println("未知页面");
			model.addAttribute("page", "sysNotice.jsp");
		}
		 
		model.addAttribute("type", user.getGm_type());
		model.addAttribute("userName", user.getUserName());
		model.addAttribute("token", token);
		model.addAttribute("user",user);
		model.addAttribute("index", page);
		return "index";
	}

	@RequestMapping("/login")
	public String login() {
		return "loginb";
	}

	@RequestMapping("/login_forgetb")
	public String login_forgetb() {
		return "login_forgetb";
	}

	@RequestMapping("/login_forgetb_fls")
	public String login_forgetb_fls(String userName,String ctime,Model model)
	{  
		User user = userServiceImpl.queryUserInfo(userName);
		if (user!=null) {
			model.addAttribute("userName",user.getUserName());
			model.addAttribute("email",user.getEmail());
			model.addAttribute("ctime",ctime);
			return "login_forgetb_fls";
		}
		else{
			return "login_forgetb";
		}  
	}
}
