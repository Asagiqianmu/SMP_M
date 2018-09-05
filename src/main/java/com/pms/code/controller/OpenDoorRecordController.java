package com.pms.code.controller;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pms.code.entity.base.OwnerPaycat;
import com.pms.code.entity.base.User;
import com.pms.code.service.OpenDoorRecoedService;
import com.pms.code.util.JsonUtil;
import com.pms.code.util.Page;

import net.sf.json.JSONObject;

@Controller
public class OpenDoorRecordController extends BaseController{
	
	private Logger logger=LoggerFactory.getLogger(OpenDoorRecordController.class);
	
	@Autowired
	private OpenDoorRecoedService openDoorRecoedServiceImpl;
	
	/**
	 * 查询开门记录
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="/queryOpenDoorRecord",method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String queryOpenDoorRecord(@RequestBody String data) {
		JSONObject json = JSONObject.fromObject(data);
     	int pageIndex=json.getInt("pageIndex");
     	int pageSize=json.getInt("pageSize");
     	String style=json.containsKey("style")?json.getString("style"):"";
		String token=json.getString("token");
		User user=(User) session.getAttribute(token);
		if (pageIndex <= 0) {
			pageIndex = 1;
		}
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("pageIndex", pageIndex);
		hashMap.put("pageSize", pageSize);
		hashMap.put("style",style);
		if(user.getGm_type()==3)
		{
			hashMap.put("pmid",user.getPmid());
			hashMap = openDoorRecoedServiceImpl.queryOpenDoorRecord(hashMap);
		}
		else if(user.getGm_type()==2)
		{
			hashMap.put("pid",user.getPid());
			hashMap.put("pmid",json.getString("pmid"));
			hashMap=openDoorRecoedServiceImpl.queryOpenDoorRecordCompany(hashMap);
		}
		
		Page<OwnerPaycat> page = (Page<OwnerPaycat>) hashMap.get("page");
		if (page == null) {
			map.put("code", "201");
			map.put("msg", "获取不到用户");
		}
		else
		{
			map.put("code","200");
			map.put("data",page); 
		}
		logger.error("------查询已缴费列表------" + map);
		request.setAttribute("map",map);
		return JsonUtil.objectToJson(map);
		
	}
}
