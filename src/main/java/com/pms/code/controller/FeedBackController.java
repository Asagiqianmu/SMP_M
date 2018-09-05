package com.pms.code.controller;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pms.code.entity.base.FeedBack;
import com.pms.code.service.FeedBackService;
import com.pms.code.util.JsonUtil;
import com.pms.code.util.Page;

import net.sf.json.JSONObject;

@Controller
public class FeedBackController extends BaseController {
	private Logger logger = LoggerFactory.getLogger(FeedBackController.class);

	@Autowired
	private FeedBackService feedBackServiceImpl;

	/**
	 * 投诉管理列表
	 * 
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "/queryFeedBackInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String queryFeedBackInfo(@RequestBody String data) {
		JSONObject json = JSONObject.fromObject(data);
		int pageIndex = json.getInt("pageIndex");
		int pageSize = json.getInt("pageSize");
		String telphone=json.containsKey("telphone")?json.getString("telphone"):"";
		String content=json.containsKey("content")?json.getString("content"):"";
		if (pageIndex <= 0) {
			pageIndex = 1;
		}
		HashMap<String, Object> paramType = new HashMap<String, Object>();
		paramType.put("pageIndex", pageIndex);
		paramType.put("pageSize", pageSize);
		paramType.put("telphone",telphone);
		paramType.put("content",content);
		paramType = feedBackServiceImpl.queryFeedBackInfo(paramType);
		Page<FeedBack> page = (Page<FeedBack>) paramType.get("page");
		if (page == null) {
			map.put("code", "201");
			map.put("msg", "获取便民信息为空");
			logger.error("------便民投诉列表------" + map);
			return JsonUtil.objectToJson(map);
		}
		map.put("data", page);
		map.put("code", "200");
		logger.error("------便民投诉列表------" + map);
		return JsonUtil.objectToJson(map);
	}

	/**
	 * 查看投诉信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/sel_FeedbackInfo",method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String queryFeedBackInfoById(int id) {
		FeedBack feedBack = feedBackServiceImpl.queryFeedBackInfoById(id);
		if(null==feedBack)
		{
			map.put("code", "201");
			map.put("msg", "查询投诉信息失败");
			logger.error("------编辑投诉信息-----" + map);
			return JsonUtil.objectToJson(map);
		}
		map.put("code", "200");
		map.put("data",feedBack);
		logger.error("------增加投诉信息-----" + map);
		return JsonUtil.objectToJson(map);
	}
	
	/**
	 * 删除投诉信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value="deleteFeedBackInfo",method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String deleteFeedBackInfo(int id)
	{
		boolean flag = feedBackServiceImpl.deleteFeedBack(id);
		if(flag)
		{
			map.put("code", "200");
			map.put("msg", "删除投诉信息成功");
			logger.error("------删除投诉信息-----" + map);
			return JsonUtil.objectToJson(map);
		}
		map.put("code", "201");
		map.put("msg","删除投诉信息失败");
		logger.error("------删除投诉信息-----" + map);
		return JsonUtil.objectToJson(map);
	}
}
