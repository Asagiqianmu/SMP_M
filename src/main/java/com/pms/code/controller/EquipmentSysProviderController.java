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

import com.pms.code.entity.base.EquipmentSysProvider;
import com.pms.code.service.EquipmentSysProviderService;
import com.pms.code.util.JsonUtil;
import com.pms.code.util.Page;

import net.sf.json.JSONObject;

@Controller
public class EquipmentSysProviderController extends BaseController{
	
	private Logger logger=LoggerFactory.getLogger(EquipmentSysProviderController.class);
	
	@Autowired
	private EquipmentSysProviderService equipmentSysProviderServiceImpl;
	
	@RequestMapping(value="/queryEquipmentSysProviderInfo",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	@ResponseBody
	public String queryEquipmentSysProviderInfo(@RequestBody String data)
	{
		JSONObject json = JSONObject.fromObject(data);
		int pageIndex=json.getInt("pageIndex");
		int pageSize=json.getInt("pageSize");
		if(pageIndex<0)
		{
			pageIndex=1;
		}
		HashMap<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("pageIndex",pageIndex);
		paramMap.put("pageSize",pageSize);
		paramMap = equipmentSysProviderServiceImpl.queryEquipmentSysProviderInfo(paramMap);
		Page<EquipmentSysProvider> page=(Page<EquipmentSysProvider>) paramMap.get("page");
		if (page == null) {
			map.put("code", "201");
			map.put("msg", "获取不到服务商列表");
		}
		else
		{
			map.put("code","200");
			map.put("data",page);
		} 
		logger.error("------服务商列表------"+map);
		return JsonUtil.objectToJson(map);
	}
	
	/**
	 * 删除服务商信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/deleteEquipmentSysProviderInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String deleteEquipmentSysProviderInfo(HttpServletRequest request) {
		String[] str= request.getParameterValues("id");
		int [] ids=new int[str.length];
		for(int i=0;i<str.length;i++)
		{
			ids[i]=Integer.parseInt(str[i]);
		}
		// 调用service方法删除服务商
		boolean flag = equipmentSysProviderServiceImpl.deleteEquipmentSysProviderInfo(ids);
		if (flag) {
			map.put("code", "200");
			map.put("msg", "删除成功");
		} else {
			map.put("code", "201");
			map.put("msg", "删除失败");
		}
		logger.error("------删除服务商------" + map);
		return JsonUtil.objectToJson(map);
	}

	/***
	 * 编辑服务商信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/editEquipmentSysProviderInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String editSysnotice(@RequestParam(value = "id", required = true)int id) {
//		根据id查询服务商
		EquipmentSysProvider equipmentSysProvider=equipmentSysProviderServiceImpl.findEquipmentSysProviderByID(id);
		if(equipmentSysProvider!=null)
		{ 
			map.put("code","200");
			map.put("data",equipmentSysProvider);
		}
		else
		{
			map.put("code","201");
			map.put("msg","暂无信息");
		}
		logger.error("------编辑服务商------"+map);
		return JsonUtil.objectToJson(map);
	}
	
	/**
	 * 保存编辑的服务商
	 * @param sysNotice
	 * @return
	 */
	@RequestMapping(value="/saveEditEquipmentSysProvider",method=RequestMethod.POST,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String saveEditSysNotice(EquipmentSysProvider equipmentSysProvider)
	{
		boolean flag = equipmentSysProviderServiceImpl.updateEquipmentSysProviderInfo(equipmentSysProvider);
		if(flag)
		{
			map.put("code","200");
			map.put("msg","保存成功");
		}
		else {
			map.put("code","201");
			map.put("msg","保存失败");
		}
		logger.error("------保存编辑的服务商------"+map);
		return JsonUtil.objectToJson(map);
	}
	
	/**
	 * 添加服务商
	 * @param sysNotice
	 * @return
	 */
	@RequestMapping(value="/insertEquipmentSysProvider",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	@ResponseBody
	public String insertNotice(EquipmentSysProvider equipmentSysProvider)
	{ 
		boolean flag= equipmentSysProviderServiceImpl.insertEquipmentSysProviderInfo(equipmentSysProvider);
		if(flag)
		{
			map.put("code","200");
			map.put("msg","添加成功");
		}
		else {
			map.put("code","201");
			map.put("msg","添加失败");
		}
		logger.error("------添加新服务商------"+map);
		return JsonUtil.objectToJson(map);
	}
}
