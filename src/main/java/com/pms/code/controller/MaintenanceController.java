package com.pms.code.controller;

import java.sql.Timestamp;
import java.util.Date;
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

import com.pms.code.entity.base.Maintenance;
import com.pms.code.entity.base.User;
import com.pms.code.service.MaintenanceService;
import com.pms.code.util.JsonUtil;
import com.pms.code.util.Page;

import net.sf.json.JSONObject;

@Controller
public class MaintenanceController extends BaseController{
	private Logger logger = LoggerFactory.getLogger(MaintenanceController.class);

	@Autowired
	private MaintenanceService maintenanceServiceImpl;

	/**
	 * 查询维修服务
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="/queryMaintanance",method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String queryMaintanance(@RequestBody String data) {
		JSONObject json = JSONObject.fromObject(data);
		int pageIndex = json.getInt("pageIndex");
		int pageSize=json.getInt("pageSize");
		String company=json.containsKey("company")?json.getString("company"):"";
		String token=json.getString("token");
		User user= (User) session.getAttribute(token);
		if(pageIndex<=0)
		{
			pageIndex=1;
		}
		HashMap<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("pageIndex",pageIndex);
		paramMap.put("pageSize",pageSize);
		paramMap.put("companyName",company);
		if(user.getGm_type()==3)
		{
			paramMap.put("pmid",user.getPmid());
			paramMap=maintenanceServiceImpl.queryMaintanance(paramMap);
		}
		else if(user.getGm_type()==2)
		{
			paramMap.put("pmid",json.getString("pmid"));
			paramMap.put("pid",user.getPid());
			paramMap=maintenanceServiceImpl.queryMaintenanceCompany(paramMap);
		}
		
		Page<Maintenance> page=(Page<Maintenance>) paramMap.get("page");
		if(page==null)
		{
			map.put("code","201");
			map.put("msg","获取不到维修服务");
			logger.error("------查询维修服务------"+map);
			return JsonUtil.objectToJson(map);
		}
		map.put("data",page);
		map.put("code","200");
		logger.error("------查询维修服务------"+map);
		return JsonUtil.objectToJson(map); 
	}
	
	/**
	 * 删除维修列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/deleteMaintenanceInfo",method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String deleteMaintenance(HttpServletRequest request)
	{
		String [] strs=request.getParameterValues("id");
		int [] ids=new int[strs.length];
		for(int i=0;i<strs.length;i++)
		{
			ids[i]=Integer.parseInt(strs[i]);
		}
		boolean flag = maintenanceServiceImpl.deleteMainTenance(ids);
		if(!flag)
		{
			map.put("code","201");
			map.put("msg","删除失败");
			logger.error("------删除维修列表------"+map);
			return JsonUtil.objectToJson(map);
		}
		map.put("code","200");
		map.put("msg","删除成功");
		logger.error("------删除维修列表-----"+map);
		return JsonUtil.objectToJson(map);
	}
	
	/**
	 * 编辑维系服务信息
	 */
	@RequestMapping(value="/editMaintenance",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	@ResponseBody
	public String editMaintenance(@RequestParam(value = "id", required = true)int id)
	{
		Maintenance maintenance = maintenanceServiceImpl.queryMaintenanceById(id);
		if (null==maintenance) {
			map.put("code","201");
			map.put("msg","获取不到维修服务信息");
			logger.error("------编辑维修服务-----"+map);
			return JsonUtil.objectToJson(map);
		}
		map.put("code","200");
		map.put("data",maintenance);
		logger.error("------编辑维修信息-----"+map);
		return JsonUtil.objectToJson(map);
	}
	
	/**
	 * 保存维修信息的修改
	 */
	@RequestMapping(value="/saveEditMaintenance",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	@ResponseBody
	public String saveEditMaintenance(Maintenance maintenance)
	{
		boolean flag = maintenanceServiceImpl.saveEditMaintenance(maintenance);
		if(!flag)
		{
			map.put("code","201");
			map.put("msg","修改失败");
			logger.error("----修改维修信息----"+map);
			return JsonUtil.objectToJson(map);
		}
		map.put("code","200");;
		map.put("msg","修改成功");
		logger.error("-----修改维修信息-----"+map);
		return JsonUtil.objectToJson(map);
	}
	
	/**
	 * 插入维修信息
	 * @param maintenance
	 * @return
	 */
	
	@RequestMapping(value="/insertMaintenance",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	@ResponseBody
	public String insertMaintenance(Maintenance maintenance,String token)
	{
		User user= (User) session.getAttribute(token);
		maintenance.setPmid(user.getPmid());
		maintenance.setCreateTime(new Timestamp(new Date().getTime()));
		boolean flag = maintenanceServiceImpl.insertMaintenance(maintenance);
		if(!flag)
		{
			map.put("code","201");
			map.put("msg","插入维修信息失败");
			logger.error("----插入维修信息----"+map);
			return JsonUtil.objectToJson(map);
		}
		map.put("code","200");;
		map.put("msg","插入维修信息成功");
		logger.error("-----插入维修信息-----"+map);
		return JsonUtil.objectToJson(map);
	}
}
