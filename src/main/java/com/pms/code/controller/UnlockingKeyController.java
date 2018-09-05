package com.pms.code.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pms.code.controller.danbay.DanbayAPI;
import com.pms.code.entity.base.Property;
import com.pms.code.entity.base.UnlockingKey;
import com.pms.code.entity.base.User;
import com.pms.code.service.EquipmentSysProviderService;
import com.pms.code.service.UnlockingKeyService;
import com.pms.code.util.JsonUtil;
import com.pms.code.util.Page;

import net.sf.json.JSONObject;

@Controller
public class UnlockingKeyController extends BaseController {
	private Logger logger = LoggerFactory.getLogger(UnlockingKeyController.class);

	@Autowired
	private UnlockingKeyService unlockingKeyServiceImpl;

	@Autowired
	private EquipmentSysProviderService equipmentSysProviderServiceImpl;

	/**
	 * 插入门锁表
	 * 
	 * @param data
	 * @return
	 */
	@RequestMapping(value = "/insertUnlockingKey", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String insertUnlockingKey(@RequestBody String data) {
		JSONObject json = JSONObject.fromObject(data);
		UnlockingKey unlockingKey = new UnlockingKey();
		unlockingKey.setType(json.getInt("lockType"));
		unlockingKey.setKeyname(json.getString("deviceModel"));
		unlockingKey.setCreateTime(new Timestamp(new Date().getTime()));
		unlockingKey.setDoorNum(json.getString("deviceId"));
		User user = (User) session.getAttribute(json.getString("token"));
		if (null != user) {
			unlockingKey.setP_m_id(user.getPmid());
		}
		boolean flag = unlockingKeyServiceImpl.insertUnlockingKey(unlockingKey);
		if (flag) {
			map.put("code", "200");
			map.put("msg", "插入完成");
		} else {
			map.put("code", "201");
			map.put("msg", "插入失败");
		}
		logger.error("-----插入能耗-----" + map);
		return JsonUtil.objectToJson(map);
	}

	/**
	 * 查询门锁设备列表
	 * 
	 * @param data
	 * @return
	 */
	@RequestMapping(value = "/getLockList", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String queryUnlockingKeyList(String strPageIndex,Model model) {
		int pageIndex=0;
		int pageSize=10;
		if("".equals(pageIndex)||null==strPageIndex)
		{
			pageIndex = 1;
		}else{
			pageIndex=Integer.parseInt(strPageIndex);
		}
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("pageIndex", pageIndex);
		paramMap.put("pageSize", pageSize);
		// 获取物业公司
		paramMap = unlockingKeyServiceImpl.queryUnlockingKey(paramMap);
		Page<Property> page = (Page<Property>) paramMap.get("page");
		model.addAttribute("page",page);
		logger.error("------门锁设备列表-----" + map);
		return "unlockingKey";
	}

	/**
	 * 查询单个门锁设备信息(根据设备ID)
	 * 
	 * @param data
	 * @return
	 */
	@RequestMapping(value = "/getLockInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String queryUnlockingKey(@RequestBody String data) {
		String result = null;
		JSONObject json = JSONObject.fromObject(data);
		UnlockingKey unlockingKey = unlockingKeyServiceImpl.findUnlockingKeyByID(json.getInt("id"));
		String deviceId = unlockingKey.getDoorNum();
		DanbayAPI danbayapi = new DanbayAPI();
		String loginJson = danbayapi.authorizationLogin(data, equipmentSysProviderServiceImpl);
		JSONObject resjson = JSONObject.fromObject(loginJson);
		if (resjson.getInt("code") == 200) {
			JSONObject jsondata = resjson.getJSONObject("result");
			JSONObject rqjson = new JSONObject();
			rqjson.put("mtoken", jsondata.getString("mtoken"));
			rqjson.put("deviceId", deviceId);
			rqjson.put("host", resjson.getString("host"));
			rqjson.put("userName", resjson.getString("userName"));
			rqjson.put("token", resjson.getString("token"));
			danbayapi.getLockInfo(JsonUtil.objectToJson(rqjson));
			result = danbayapi.getLockInfo(JsonUtil.objectToJson(rqjson));
			if (JSONObject.fromObject(result).getInt("code") == 200) {
				map.put("code", "200");
				map.put("data", result);
				logger.error("------查询单个门锁设备信息------" + map);
				return JsonUtil.objectToJson(map);
			}
		}
		map.put("code", "201");
		map.put("msg", "查询单个门锁设备信息失败");
		logger.error("------查询单个门锁设备信息------" + map);
		return JsonUtil.objectToJson(map);
	}
}
