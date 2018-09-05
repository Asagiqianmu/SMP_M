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
import org.springframework.web.bind.annotation.ResponseBody;

import com.pms.code.entity.base.ConvenienceInfo;
import com.pms.code.entity.base.User;
import com.pms.code.service.ConvenienceInfoService;
import com.pms.code.util.JsonUtil;
import com.pms.code.util.Page;

import net.sf.json.JSONObject;

/**
 * 便民信息
 * @author Dell
 *
 */
@Controller
public class ConvenienceInfoController  extends BaseController{

	private Logger logger=LoggerFactory.getLogger(ConvenienceInfoController.class);
	
	@Autowired
	private ConvenienceInfoService convenienceInfoServiceImpl;
	
	/**
	 * 查询便民信息
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="/queryConvenienceInfo",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	@ResponseBody
	public String queryConvenienceInfo(@RequestBody String data)
	{
		JSONObject json = JSONObject.fromObject(data);
		int pageIndex = json.getInt("pageIndex");
		int pageSize = json.getInt("pageSize");
		String telphone=json.containsKey("telphone")?json.getString("telphone"):"";
		String contacts=json.containsKey("contacts")?json.getString("contacts"):"";
		String token=json.getString("token");
		User user=(User) session.getAttribute(token);
		if(pageIndex<=0)
		{
			pageIndex=1;
		}
		HashMap<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("pageIndex",pageIndex);
		paramMap.put("pageSize",pageSize);
		paramMap.put("telphone",telphone);
		paramMap.put("contacts",contacts);
		if(user.getGm_type()==3)
		{
			paramMap.put("pmid",user.getPmid());
			paramMap= convenienceInfoServiceImpl.queryConvenienceInfo(paramMap);
		}
		else if(user.getGm_type()==2)
		{
			paramMap.put("pmid",json.getString("pmid"));
			paramMap.put("pid",user.getPid());
			convenienceInfoServiceImpl.queryConvenienceInfoCompany(paramMap);
		} 
		Page<ConvenienceInfo> page=(Page<ConvenienceInfo>) paramMap.get("page");
		if(page==null)
		{
			map.put("code","201");
			map.put("msg","查询不到便民信息");
			logger.error("-----查询便民信息-----"+map);
			return JsonUtil.objectToJson(map);
		}
		map.put("code","200");
		map.put("data",page);
		logger.error("-----查询便民信息-----"+map);
		return JsonUtil.objectToJson(map);
	}
	
	/**
	 * 插入便民信息
	 * @param convenienceInfo
	 * @return
	 */
	@RequestMapping(value="/insertConvenienceInfo",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	@ResponseBody
	public String insertConvenienceInfo(ConvenienceInfo convenienceInfo,String token)
	{
		User user=(User) session.getAttribute(token);
		convenienceInfo.setPmid(user.getPmid());
		convenienceInfo.setCreatetime(new Timestamp(new Date().getTime()));
		boolean flag = convenienceInfoServiceImpl.insertConvenienceInfo(convenienceInfo);
		if(!flag)
		{
			map.put("code","201");
			map.put("msg","插入便民信息失败");
			logger.error("------插入便民信息----"+map);
			return JsonUtil.objectToJson(map);
		}
		map.put("code","200");
		map.put("msg","插入成功");
		logger.error("------插入便民信息----"+map);
		return JsonUtil.objectToJson(map);
	}
	
	/**
	 * 编辑便民信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/editConvenienceInfo",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	@ResponseBody
	public String editConvenienceInfo(int id)
	{
		ConvenienceInfo convenienceInfo= convenienceInfoServiceImpl.queryConvenienceInfoById(id);
		if(convenienceInfo==null)
		{
			map.put("code","201");
			map.put("msg","查询不到便民信息"); 
		}
		else {
			map.put("code","200");
			map.put("data",convenienceInfo);
		} 
		logger.error("-----编辑便民信息-----"+map);
		return JsonUtil.objectToJson(map);
	}
	
	/**
	 * 保存修改便民信息
	 * @param convenienceInfo
	 * @return
	 */  
	@RequestMapping(value="/saveEditConvenienceInfo",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	@ResponseBody
	public String saveEditConvenienceInfo(ConvenienceInfo convenienceInfo)
	{
		boolean flag = convenienceInfoServiceImpl.updateConvenienceInfo(convenienceInfo);
		if (flag) {
			map.put("code", "200");
			map.put("msg", "修改成功");
		} else {
			map.put("code", "201");
			map.put("msg", "修改失败");
		}
		logger.error("------修改便民信息------" + map);
		return JsonUtil.objectToJson(map);
	}
	
	/**
	 * 删除便民信息
	 * @return
	 */
	@RequestMapping(value="/deleteConvenienceInfo",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	@ResponseBody
	public String deleteConvenienceInfo(HttpServletRequest request)
	{
		String[] str = request.getParameterValues("id");
		int[] ids = new int[str.length];
		for (int i = 0; i < str.length; i++) {
			ids[i] = Integer.parseInt(str[i]);
		}
		boolean accountFlag =convenienceInfoServiceImpl.deleteConvenienceInfo(ids);
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
}
