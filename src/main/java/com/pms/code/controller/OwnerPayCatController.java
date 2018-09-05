package com.pms.code.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pms.code.entity.base.CostType;
import com.pms.code.entity.base.EnergyConsumptionDevice;
import com.pms.code.entity.base.OwnerInfo;
import com.pms.code.entity.base.OwnerPaycat;
import com.pms.code.entity.base.User;
import com.pms.code.service.CostTypeService;
import com.pms.code.service.OwnerPayCatService;
import com.pms.code.util.JsonUtil;
import com.pms.code.util.Page;

import net.sf.json.JSONObject;

@Controller
public class OwnerPayCatController extends BaseController {
	private Logger logger = LoggerFactory.getLogger(OwnerPayCatController.class);

	@Autowired
	private OwnerPayCatService ownerPayCatServiceImpl;
	
	@Autowired
	private CostTypeService costTypeServiceImpl;

	/**
	 * 查询已缴费列表
	 * 
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "/queryOwnerPayCatInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String queryOwnerPayCatInfo(@RequestBody String data) {
		JSONObject json = JSONObject.fromObject(data);
		int pageIndex = json.getInt("pageIndex");
		int pageSize = json.getInt("pageSize");
		String ownerName = json.containsKey("owner_name") ? json.getString("owner_name") : "";
		String token = json.getString("token");
		if (pageIndex <= 0) {
			pageIndex = 1;
		}
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("pageIndex", pageIndex);
		hashMap.put("pageSize", pageSize);
		hashMap.put("owner_name", ownerName);
		hashMap.put("STATUS",0);
		User user = (User) session.getAttribute(token);
		if (user.getGm_type() == 3) {
			hashMap.put("pmid", user.getPmid());
			hashMap = ownerPayCatServiceImpl.queryOwnerPayCatList(hashMap);
		} else if (user.getGm_type() == 2) {
			hashMap.put("pid", user.getPid());
			hashMap.put("pmid",json.getString("pmid"));
			hashMap=ownerPayCatServiceImpl.queryOwnerPayCatListCompany(hashMap);
		}
		Page<OwnerPaycat> page = (Page<OwnerPaycat>) hashMap.get("page");
		List<CostType> costTypeList = costTypeServiceImpl.costTypeList();
		if (page == null) {
			map.put("code", "201");
			map.put("msg", "获取不到用户");
		} else {
			map.put("code", "200");
			map.put("data", page);
			if(costTypeList!=null)
			{
				map.put("costTypeList",costTypeList);
			}
		}
		logger.error("------查询已缴费列表------" + map);
		request.setAttribute("map", map);
		return JsonUtil.objectToJson(map);
	}

	/**
	 * 查询未缴费列表 
	 * 
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "/queryOwnerUnPayCatInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String queryUnOwnerPayCatInfo(@RequestBody String data) {
		JSONObject json = JSONObject.fromObject(data);
		int pageIndex = json.getInt("pageIndex");
		int pageSize = json.getInt("pageSize");
		String ownerName = json.containsKey("owner_name") ? json.getString("owner_name") : "";
		String token = json.getString("token");
		if (pageIndex <= 0) {
			pageIndex = 1;
		}
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("pageIndex", pageIndex);
		hashMap.put("pageSize", pageSize);
		hashMap.put("owner_name",ownerName);
		hashMap.put("STATUS",1);
		User user = (User) session.getAttribute(token);
		if (user.getGm_type() == 3) {
			hashMap.put("pmid", user.getPmid());
			hashMap = ownerPayCatServiceImpl.queryUnOwnerPayCatList(hashMap);
		} else if (user.getGm_type() == 2) {
			hashMap.put("pid", user.getPid());
			hashMap.put("pmid",json.getString("pmid"));
			hashMap = ownerPayCatServiceImpl.queryUnOwnerPayCatListCompany(hashMap);
		}

		Page<OwnerPaycat> page = (Page<OwnerPaycat>) hashMap.get("page");
		if (page == null) {
			map.put("code", "201");
			map.put("msg", "获取不到用户");
			logger.error("------查询已缴费列表------" + map);
			return JsonUtil.objectToJson(map);
		}
		map.put("data", page);
		map.put("code", "200");
		logger.error("------查询已缴费列表------" + map);
		return JsonUtil.objectToJson(map);
	}
	
	/**
	 * 编辑缴费信息通过id
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/editPayCatInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String  editPayCatInfo(int id)
	{
		OwnerPaycat ownerPayCat= ownerPayCatServiceImpl.queryPayCatById(id);
		if(ownerPayCat==null)
		{
			map.put("code","201");
			map.put("msg","查询异常");
		}
		else {
			map.put("code","200");
			map.put("data",ownerPayCat);
		}
		logger.error("------编辑缴费信息------" + map);
		return JsonUtil.objectToJson(map);
	}
	
	/**
	 * 录入缴费信息
	 * 
	 * @param ownerPaycat
	 * @return
	 */
	@RequestMapping(value = "/ownerPayCatEnter", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String ownerPayCatEnter(OwnerPaycat ownerPaycat, String token) {
		User user = (User) session.getAttribute(token);
		ownerPaycat.setCreatetime(new Timestamp(new Date().getTime()));
		ownerPaycat.setPmid(user.getPmid());
		ownerPaycat.setStatus(1);
		boolean flag = ownerPayCatServiceImpl.OwnerPayCatEnter(ownerPaycat);
		if (flag) {
			map.put("code", "200");
			map.put("msg", "录入成功");
		} else {
			map.put("code", "201");
			map.put("msg", "录入失败");
		}
		logger.error("-----录入缴费信息------" + map);
		return JsonUtil.objectToJson(map);
	}

	/**
	 * 查询月度流水
	 */
	@RequestMapping(value = "/qureyMonthCost", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String qureyMonthCost(@RequestParam(value = "pageIndex", required = true) int pageIndex,
			@RequestParam(value = "pageSize", required = true) int pageSize) {
		if (pageIndex <= 0) {
			pageIndex = 1;
		}
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("pageIndex", pageIndex);
		hashMap.put("pageSize", pageSize);
		hashMap = ownerPayCatServiceImpl.qureyMonthCost(hashMap);
		Page<OwnerPaycat> page = (Page<OwnerPaycat>) hashMap.get("page");
		if (page == null) {
			map.put("code", "201");
			map.put("msg", "查询不到月度流水信息");
			return JsonUtil.objectToJson(map);
		}
		map.put("data", page);
		map.put("code", "200");
		logger.error("------月度流水-----" + page);
		return JsonUtil.objectToJson(map);
	}
	
	/**
	 * 保存未缴费修改
	 * @param ownerPaycat
	 * @return
	 */
	@RequestMapping(value="/saveEditUnOwnerPayCat", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String saveEditUnOwnerPayCat(OwnerPaycat ownerPaycat)
	{
		boolean flag = ownerPayCatServiceImpl.updateUnpayCatInfo(ownerPaycat);
		if(flag)
		{
			map.put("code","200");
			map.put("msg","修改成功");
		}
		else {
			map.put("code","201");
			map.put("msg","修改失败");
		}
		logger.error("------月度流水-----" + map);
		return JsonUtil.objectToJson(map);
	}
	
	/**
	 * 删除未缴费信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/deleteUnPayCatInfo",method=RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String deleteUnPayCatInfo(int id)
	{
		boolean flag = ownerPayCatServiceImpl.deleteUnPayCatInfo(id);
		if(flag)
		{
			map.put("code","200");
			map.put("msg","删除成功");
		}
		else {
			map.put("code","201");
			map.put("msg","删除失败");
		}
		logger.error("------月度流水-----" + map);
		return JsonUtil.objectToJson(map);
	} 
	
	/**
	 * 查询业主每月未缴费账单
	 * @param data
	 * @return
	 */
	@RequestMapping(value="/queryOwnerPayCat",method=RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String queryOwnerPayCat(@RequestBody String data)
	{
		JSONObject json = JSONObject.fromObject(data);
		int pageIndex = json.getInt("pageIndex");
		int pageSize = json.getInt("pageSize");
		String token = json.getString("token");
		if (pageIndex <= 0) {
			pageIndex = 1;
		}
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("pageIndex", pageIndex);
		hashMap.put("pageSize", pageSize);
		hashMap.put("status",1);
		User user = (User) session.getAttribute(token);
		if (user.getGm_type() == 3) {
			hashMap.put("pmid", user.getPmid());
			hashMap = ownerPayCatServiceImpl.queryOwnerPayCat(hashMap);
		} else if (user.getGm_type() == 2) {
			int pmid=json.containsKey("pmid")?json.getInt("pmid"):0;
			hashMap.put("pmid",pmid);
			hashMap.put("pid",user.getPid());
			hashMap = ownerPayCatServiceImpl.queryOwnerPayCat(hashMap);
		}
		Page<OwnerPaycat> page = (Page<OwnerPaycat>) hashMap.get("page");
		if (page == null) {
			map.put("code", "201");
			map.put("msg", "获取不到用户");
		} else {
			map.put("code", "200");
			map.put("data", page);
		}
		logger.error("------查询业主每月账单------" + map);
		request.setAttribute("map", map);
		return JsonUtil.objectToJson(map);
	}
}
