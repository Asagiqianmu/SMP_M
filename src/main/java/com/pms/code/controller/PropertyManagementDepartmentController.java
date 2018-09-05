package com.pms.code.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pms.code.entity.base.PropertyManagementDepartment;
import com.pms.code.entity.base.User;
import com.pms.code.service.PropertyManagementDepartmentService;
import com.pms.code.service.PropertyService;
import com.pms.code.service.UserService;
import com.pms.code.util.JsonUtil;
import com.pms.code.util.MD5;
import com.pms.code.util.Page;
import com.pms.code.util.SHA256;

import net.sf.json.JSONObject;

/**
 * 小区物业管理处信息
 * 
 * @author dengfei E-mail:dengfei200857@163.com
 * @time 2018年3月23日 下午5:51:16
 */
@Controller
public class PropertyManagementDepartmentController extends BaseController {
	private Logger logger = LoggerFactory.getLogger(PropertyManagementDepartmentController.class);

	@Autowired
	private PropertyManagementDepartmentService propertyManagementDepartmentServiceImpl;

	@Autowired
	private UserService userServiceImpl;

	/**
	 * 查询小区物业管理处列表
	 * 
	 * @author dengfei E-mail:dengfei200857@163.com
	 * @time 2018年3月23日 下午5:30:14
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "/queryPMDlist", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String queryPropertyManagementDepartmentlist(@RequestBody String data) {
		JSONObject json = JSONObject.fromObject(data);
		String token = json.getString("token");
		int pageIndex = json.getInt("pageIndex");
		int pageSize = json.getInt("pageSize");
		int pid = json.containsKey("pid") ? json.getInt("pid") : 0;// 物业公司id
		if (pageIndex <= 0) {
			pageIndex = 1;
		}
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("pageIndex", pageIndex);
		paramMap.put("pageSize", pageSize);
		if (pid > 0) {
			paramMap.put("pid", pid);
		}
		User user = (User) session.getAttribute(token);
		paramMap.put("pid", user.getPid());
		// 获取用户
		paramMap = propertyManagementDepartmentServiceImpl.queryPropertyManagementDepartmentList(paramMap);
		Page<PropertyManagementDepartment> page = (Page<PropertyManagementDepartment>) paramMap.get("page");
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
	 * 编辑小区物业管理处信息
	 */
	@RequestMapping(value = "/editPMDInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String editPropertyManagementDepartmentInfo(int id) {
		PropertyManagementDepartment department = propertyManagementDepartmentServiceImpl
				.queryPropertyManagementDepartmentById(id);
		if (department == null) {
			map.put("code", "201");
			map.put("msg", "获取的用户为空");
		} else {
			map.put("code", "200");
			map.put("data", department);
			map.put("msg", "查询到用户");
		}
		logger.error("------编辑用信息------" + map);
		return JsonUtil.objectToJson(map);
	}

	/**
	 * 保存小区物业管理处编辑信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/saveEditPMDInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String saveEditPropertyManagementDepartmentInfo(@RequestBody String data) {
		JSONObject json = JSONObject.fromObject(data);
		int id = json.getInt("id");
		String department_name = json.getString("department_name");
		String telphone = json.getString("telphone");
		String province = json.getString("province");
		String city = json.getString("city");
		String area = json.getString("area");
		PropertyManagementDepartment department = new PropertyManagementDepartment();
		department.setId(id);
		department.setManagement_department_name(department_name);
		department.setContact_number(telphone);
		department.setProvince(province);
		department.setCity(city);
		department.setArea(area);
		boolean flag = propertyManagementDepartmentServiceImpl.saveEditPropertyManagementDepartment(department);
		if (flag) {
			map.put("code", "200");
			map.put("msg", "删除成功");
		} else {
			map.put("code", "201");
			map.put("msg", "删除失败");
		}
		logger.error("------保存小区物业管理处编辑信息------" + map);
		return JsonUtil.objectToJson(map);
	}

	/**
	 * 添加小区物业管理处信息
	 * 
	 * @param ownerAccount
	 * @return
	 */
	@RequestMapping(value = "/addPMD", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String addPropertyManagementDepartment(@RequestBody String data) {
		JSONObject json = JSONObject.fromObject(data);
		String department_name = json.getString("department_name");
		String telphone = json.getString("telphone");
		String province = json.getString("province");
		String city = json.getString("city");
		String area = json.getString("area");
		String token = json.getString("token");
		String email = json.getString("email");
		String userName = json.getString("userName");
		String passWord = json.getString("passWord");
		User user = (User) session.getAttribute(token);
		PropertyManagementDepartment department = new PropertyManagementDepartment();
		department.setManagement_department_name(department_name);
		department.setContact_number(telphone);
		department.setProvince(province);
		department.setCity(city);
		department.setArea(area);
		department.setPid(user.getPid());
		boolean flag = propertyManagementDepartmentServiceImpl.insertPropertyManagementDepartment(department);
		if (flag) {
			department = propertyManagementDepartmentServiceImpl.queryPropertyManagementDepartment(department);
			user.setCreateTime(new Timestamp(new Date().getTime()));
			user.setEmail(email);
			user.setUserName(userName);
			user.setPmid(department.getId());
			user.setPassWord(passWord);
			user.setGm_type(3);
			user.setPassWord(SHA256.getUserPassword(userName, MD5.encode(passWord).toLowerCase()));
			boolean flag2 = userServiceImpl.addUser(user);
			if (flag2) {
				map.put("code", "200");
				map.put("msg", "添加成功");
			}
		} else {
			map.put("code", "201");
			map.put("msg", "添加失败");
		}
		logger.error("------添加小区物业管理处信息------" + map);
		return JsonUtil.objectToJson(map);
	}

	/**
	 * 删除小区物业管理处信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/deletePMD")
	@ResponseBody
	public String deletePropertyManagementDepartment(HttpServletRequest request) {
		String[] str = request.getParameterValues("id");
		int[] ids = new int[str.length];
		for (int i = 0; i < str.length; i++) {
			ids[i] = Integer.parseInt(str[i]);
		}
		boolean accountFlag = propertyManagementDepartmentServiceImpl.deletePropertyManagementDepartment(ids);
		if (accountFlag) {
			map.put("code", "200");
			map.put("msg", "删除成功");
		} else {
			map.put("code", "201");
			map.put("msg", "删除失败");
		}
		logger.error("------删除小区物业管理处信息------" + map);
		return JsonUtil.objectToJson(map);
	}

	/**
	 * 查询小区名称
	 * @param data
	 * @return
	 */
	@RequestMapping("/queryPMDName")
	@ResponseBody   
	public String queryPropertyManagementDepartmentName(@RequestBody String data) {
		JSONObject json = JSONObject.fromObject(data);
		HashMap<String, Object> hmap = new HashMap<String,Object>();
		hmap.put("province", json.getString("province"));
		hmap.put("city", json.getString("city"));
		hmap.put("area", json.getString("area"));
		List<PropertyManagementDepartment> pmds = propertyManagementDepartmentServiceImpl
				.queryPropertyManagementDepartmentName(hmap);
		if (pmds.size()>0) {
			map.put("code", "200");
			map.put("pmds", pmds);
		} else {
			map.put("code", "201");
			map.put("msg", "没有查询到数据");
		}
		return JsonUtil.objectToJson(map);
	}
}
