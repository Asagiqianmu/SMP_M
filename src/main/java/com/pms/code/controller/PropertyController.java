package com.pms.code.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pms.code.entity.base.Property;
import com.pms.code.entity.base.User;
import com.pms.code.service.PropertyService;
import com.pms.code.util.JsonUtil;
import com.pms.code.util.Page;
import net.sf.json.JSONObject;

/**
 * 物业公司信息
 * 
 * @author dengfei E-mail:dengfei200857@163.com
 * @time 2018年3月23日 下午5:51:16
 */
@Controller
public class PropertyController extends BaseController {
	private Logger logger = LoggerFactory.getLogger(PropertyController.class);

	@Autowired
	private PropertyService propertyServiceImpl;

	/**
	 * 查询物业公司列表
	 * 
	 * @author dengfei E-mail:dengfei200857@163.com
	 * @time 2018年3月23日 下午5:30:14
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/queryPropertylist")
	@ResponseBody
	public String queryPropertylist(@RequestParam(value = "pageIndex", required = true) int pageIndex, @RequestParam(value = "pageSize", required = true) int pageSize) {
		if (pageIndex <= 0) {
			pageIndex = 1;
		}
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("pageIndex", pageIndex);
		paramMap.put("pageSize", pageSize);
		// 获取物业公司
		paramMap = propertyServiceImpl.queryPropertyList(paramMap);
		Page<Property> page = (Page<Property>) paramMap.get("page");
		if (page == null) {
			map.put("code", "201");
			map.put("msg", "获取不到信息");
			logger.error("------查询列表------" + map);
			return JsonUtil.objectToJson(map);
		}

		map.put("code", "200");
		map.put("data", page);
		logger.error("------查询列表------" + map);
		System.out.println(map);
		return JsonUtil.objectToJson(map);
	}

	/**
	 * 查询物业公司信息
	 */
	@RequestMapping(value = "/queryPropertyInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String queryPropertyInfo(@RequestBody String data) {
		JSONObject json = JSONObject.fromObject(data);
		String token = json.getString("token");
		User user = (User) session.getAttribute(token);
		Property property = propertyServiceImpl.queryPropertyByPid(user.getPid());
		if (property == null) {
			map.put("code", "201");
			map.put("msg", "获取的物业公司为空");
		} else {
			map.put("code", "200");
			map.put("email", user.getEmail());
			map.put("data", property);
			map.put("msg", "查询到物业公司");
		}
		logger.error("------编辑用信息------" + map);
		return JsonUtil.objectToJson(map);
	}

	/**
	 * 编辑物业公司信息
	 */
	@RequestMapping(value = "/editPropertyInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String editPropertyInfo(int id) {
		Property property = propertyServiceImpl.queryPropertyById(id);
		if (property == null) {
			map.put("code", "201");
			map.put("msg", "获取的物业公司为空");
		} else {
			map.put("data", property);
			map.put("msg", "查询到物业公司");
		}
		logger.error("------编辑用信息------" + map);
		return JsonUtil.objectToJson(map);
	}

	/**
	 * 保存物业公司编辑信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/saveEditPropertyInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String saveEditPropertyInfo(@RequestBody String data) {
		JSONObject json = JSONObject.fromObject(data);
		int id = json.getInt("id");
		String company_name = json.getString("company_name");
		String addr = json.getString("addr");
		String telphone = json.getString("telphone");
		String contacts = json.getString("contacts");
		String bg_url = json.getString("bg_url");
		String logo_url = json.getString("logo_url");
		String icon_url = json.getString("icon_url");
		Property property = new Property();
		property.setId(id);
		property.setCompany_name(company_name);
		property.setAddr(addr);
		property.setTelphone(telphone);
		property.setContacts(contacts);
		property.setBg_url(bg_url);
		property.setLogo_url(logo_url);
		property.setIcon_url(icon_url);
		boolean flag = propertyServiceImpl.saveEditProperty(property);
		if (flag) {
			map.put("code", "200");
			map.put("msg", "删除成功");
		} else {
			map.put("code", "201");
			map.put("msg", "删除失败");
		}
		logger.error("------保存物业公司编辑信息------" + map);
		return JsonUtil.objectToJson(map);
	}

	/**
	 * 添加物业公司信息
	 * 
	 * @param ownerAccount
	 * @return
	 */
	@RequestMapping(value="/addProperty", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String addProperty(Property property) {
		boolean flag = propertyServiceImpl.insertProperty(property);
		if (flag) {
			map.put("code", "200");
			map.put("msg", "删除成功");
		} else {
			map.put("code", "201");
			map.put("msg", "删除失败");
		}
		logger.error("------添加物业公司信息------" + map);
		return JsonUtil.objectToJson(map);
	}

	/**
	 * 删除物业公司信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/deleteProperty", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String deleteProperty(HttpServletRequest request) {
		String[] str = request.getParameterValues("id");
		int[] ids = new int[str.length];
		for (int i = 0; i < str.length; i++) {
			ids[i] = Integer.parseInt(str[i]);
		}
		boolean accountFlag = propertyServiceImpl.deleteProperty(ids);
		if (accountFlag) {
			map.put("code", "200");
			map.put("msg", "删除成功");
		} else {
			map.put("code", "201");
			map.put("msg", "删除失败");
		}
		logger.error("------删除物业公司信息------" + map);
		return JsonUtil.objectToJson(map);
	}
	
	/**
	 * 修改公司信息url
	 * @param data
	 * @return
	 */
	@RequestMapping(value="/updatePropertyUrl", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String  updatePropertyUrl(@RequestBody String data){
		JSONObject json = JSONObject.fromObject(data);
		String logoUrl=json.containsKey("logoUrl")?json.getString("logoUrl"):"";
		String bgUrl=json.containsKey("bgUrl")?json.getString("bgUrl"):"";
		int id=json.getInt("id");
		HashMap<String,Object> pmap=new HashMap<String,Object>();
		pmap.put("logoUrl",logoUrl);
		pmap.put("bgUrl",bgUrl);
		pmap.put("id",id);
		if("".equals(logoUrl)&&"".equals(bgUrl)){
			map.put("code", "202");
			map.put("msg", "修改异常");
			logger.error("------修改公司信息url------" + map);
			return JsonUtil.objectToJson(map);
		}else{
			boolean flag = propertyServiceImpl.updatePropertyUrl(pmap);
			if (flag) {
				map.put("code", "200");
				map.put("msg", "修改成功");
			} else {
				map.put("code", "201");
				map.put("msg", "修改失败");
			}
		} 
		logger.error("------修改公司信息url------" + map);
		return JsonUtil.objectToJson(map);
	}

	/**
	 * 通过uid查询物业信息
	 * 
	 * @return
	 */
	/*
	 * @RequestMapping("/queryPropertyByUid")
	 * 
	 * @ResponseBody public String queryPropertyByUid() { User user=(User)
	 * session.getAttribute("user"); Property property =
	 * propertyServiceImpl.queryPropertyByUid(user.getId()); if(property==null)
	 * { map.put("code", "200"); map.put("msg", "查询不到物业信息");
	 * logger.error("------查询物业公司信息------" + map); return
	 * JsonUtil.objectToJson(map); } map.put("code","201");
	 * map.put("msg",property); logger.error("------查询物业公司信息------" + map);
	 * return JsonUtil.objectToJson(map); }
	 */
}
