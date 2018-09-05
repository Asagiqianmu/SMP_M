package com.pms.code.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pms.code.entity.base.PropertyManagementDepartment;
import com.pms.code.entity.base.SysNotice;
import com.pms.code.entity.base.User;
import com.pms.code.service.PropertyManagementDepartmentService;
import com.pms.code.service.SysNoticeService;
import com.pms.code.util.JsonUtil;
import com.pms.code.util.Page;

import net.sf.json.JSONObject;

@Controller
// @RequestMapping("/notice")
public class SysNoticeController extends BaseController {
	private Logger logger = LoggerFactory.getLogger(SysNoticeController.class);

	@Autowired
	private SysNoticeService sysNoticeServiceImpl;

	@Autowired
	private PropertyManagementDepartmentService propertyManagementDepartmentServiceImpl;

	/**
	 * 1、Get方式的RequestMapping 不支持@RequestBody注解
	 * 2、为防止接口参数过长过多，防止接口定义凌乱，尽量使用POST方式接收请求,且以json数据格式为准。
	 */
	/**
	 * 查询公告列表
	 * 
	 * @param pageCount
	 * @param pageNum
	 * @return
	 */
	@RequestMapping(value = "/querySysNotice", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String queryNoticeList(@RequestBody String data) {
		JSONObject json = JSONObject.fromObject(data);
		int pageIndex = json.getInt("pageIndex");
		int pageSize = json.getInt("pageSize");
		String token = json.getString("token");
		User user = (User) session.getAttribute(token);

		String title = json.containsKey("title") ? json.getString("title") : "";
		if (pageIndex <= 0) {
			pageIndex = 1;
		}
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("pageSize", pageSize);
		paramMap.put("pageIndex", pageIndex);
		paramMap.put("title", title);
		if (user.getGm_type() == 3) {
			paramMap.put("pmid", user.getPmid());
			paramMap = sysNoticeServiceImpl.querySysNoticeList(paramMap);
		} else if (user.getGm_type() == 2) {
			paramMap.put("pid", user.getPid());
			paramMap.put("pmid",json.getString("pmid"));
			paramMap = sysNoticeServiceImpl.querySysNoticeListCompany(paramMap);
			
			// 查询物业公司下的物业管理员信息
		/*	List<PropertyManagementDepartment> propertyManagementDepartmentList = (List<PropertyManagementDepartment>) session
					.getAttribute("propertyManagementList");
			if (propertyManagementDepartmentList != null) {
				map.put("propertyManagementDepartmentList", propertyManagementDepartmentList);
			}*/ 
		}
		Page<SysNotice> page = (Page<SysNotice>) paramMap.get("page");

		if (page == null) {
			map.put("code", "201");
			map.put("msg", "获取不到公告列表");
			logger.error("------公告列表------" + map);
			return JsonUtil.objectToJson(map);
		}
		map.put("code", "200");
		map.put("data", page);
		logger.error("------公告列表------" + map);
		return JsonUtil.objectToJson(map);
	}

	/**
	 * 查询公告通过pmid
	 * 
	 * @param id
	 * @return
	 */
	/*
	 * @RequestMapping(value = "/querySysNoticeByPmid", method =
	 * RequestMethod.POST, produces = "application/json;charset=UTF-8")
	 * 
	 * @ResponseBody public String querySysNoticeByPmid(@RequestBody String
	 * data) { JSONObject json= JSONObject.fromObject(data); Integer
	 * pageIndex=json.getInt("pageIndex"); Integer
	 * pageSize=json.getInt("pageSize"); Integer pmid=json.getInt("pmid");
	 * if(pageIndex<=0) { pageIndex=1; } HashMap<String,Object> hMap=new
	 * HashMap<String,Object>(); hMap.put("pageSize", pageSize);
	 * hMap.put("pageIndex", pageIndex); hMap.put("pmid",pmid); hMap =
	 * sysNoticeServiceImpl.querySysNoticeByPmid(hMap); Page<SysNotice>
	 * page=(Page<SysNotice>) hMap.get("page"); if (page == null) {
	 * map.put("code", "201"); map.put("msg", "获取不到公告列表");
	 * logger.error("------公告列表------"+map); return JsonUtil.objectToJson(map);
	 * } map.put("code","200"); map.put("data",page);
	 * logger.error("------公告列表------"+map); return JsonUtil.objectToJson(map);
	 * }
	 */

	/**
	 * 删除公告
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/deleteSysNotice", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String deleteSysNotice(HttpServletRequest request) {
		String[] str = request.getParameterValues("id");
		int[] ids = new int[str.length];
		for (int i = 0; i < str.length; i++) {
			ids[i] = Integer.parseInt(str[i]);
		}
		// 调用service方法删除公告
		boolean flag = sysNoticeServiceImpl.deleteSysNotice(ids);
		System.out.println(ids + "" + flag);
		if (flag) {
			map.put("code", "200");
			map.put("msg", "删除成功");
		} else {
			map.put("code", "201");
			map.put("msg", "删除失败");
		}
		logger.error("------删除公告------" + map);
		return JsonUtil.objectToJson(map);
	}

	/***
	 * 编辑公告
	 * 
	 * @return
	 */
	@RequestMapping(value = "/editSysNotice", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String editSysnotice(@RequestParam(value = "id", required = true) int id) {
		// 根据id查询公告
		SysNotice sysNotice = sysNoticeServiceImpl.querySysNoticeById(id);
		if (sysNotice != null) {
			map.put("code", "200");
			map.put("data", sysNotice);
		} else {
			map.put("code", "201");
			map.put("msg", "暂无信息");
		}
		logger.error("------编辑公告------" + map);
		return JsonUtil.objectToJson(map);
	}

	/**
	 * 保存编辑的公告
	 * 
	 * @param sysNotice
	 * @return
	 */
	@RequestMapping(value = "/saveEditSysNotice", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String saveEditSysNotice(SysNotice sysNotice) {
		boolean flag = sysNoticeServiceImpl.saveEditSysNotice(sysNotice);
		if (flag) {
			map.put("code", "200");
			map.put("msg", "保存成功");
		} else {
			map.put("code", "201");
			map.put("msg", "保存失败");
		}
		logger.error("------保存编辑的公告------" + map);
		return JsonUtil.objectToJson(map);
	}

	/**
	 * 添加新公告
	 * 
	 * @param sysNotice
	 * @return
	 */
	@RequestMapping(value = "/insertSysNotice", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String insertNotice(SysNotice sysNotice, String token) {
		User user = (User) session.getAttribute(token);
		sysNotice.setPmid(user.getPmid());
		sysNotice.setPublisher(user.getUserName());
		boolean flag = sysNoticeServiceImpl.insertSysNotice(sysNotice);

		if (flag) {
			map.put("code", "200");
			map.put("msg", "保存成功");
		} else {
			map.put("code", "201");
			map.put("msg", "保存失败");
		}
		logger.error("------添加新公告------" + map);
		return JsonUtil.objectToJson(map);
	}

	/**
	 * 搜索公告
	 * 
	 * @param pageIndex
	 * @param pageSize
	 * @param searchTitle
	 * @return
	 */
	/*
	 * @RequestMapping("/searchSysNotice")
	 * 
	 * @ResponseBody public String searchSysNotice(@RequestParam(value =
	 * "pageIndex", required = true) int pageIndex,
	 * 
	 * @RequestParam(value = "pageSize", required = true) int
	 * pageSize,@RequestParam(value="searchTitle")String searchTitle) {
	 * if(pageIndex<=0) { pageIndex=1; } HashMap<String,Object> paramMap=new
	 * HashMap<String,Object>(); paramMap =
	 * sysNoticeService.searchSysNotice(paramMap); Page<SysNotice>
	 * page=(Page<SysNotice>) paramMap.get("page"); if(page==null) {
	 * map.put("code","201"); map.put("msg","搜索不到结果"); } else {
	 * map.put("data",page); map.put("code","200");
	 * 
	 * } logger.error("------搜索公告------"+map); return
	 * JsonUtil.objectToJson(map); }
	 */
}
