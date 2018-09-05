package com.pms.code.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.pms.code.controller.danbay.DanbayAPI;
import com.pms.code.entity.base.User;
import com.pms.code.service.EquipmentSysProviderService;
import com.pms.code.util.JsonUtil;

import net.sf.json.JSONObject;

@Controller
public class DeviceController extends BaseController {
	private Logger logger = LoggerFactory.getLogger(ConvenienceInfoController.class);

	@Autowired
	public EquipmentSysProviderService equipmentSysProviderServiceImpl;

	/**
	 * 查询所有设备
	 * 
	 * @param data
	 * @return
	 */
	@RequestMapping(value = "/queryDeviceListAll", method = RequestMethod.POST)
	@ResponseBody
	public String getDeviceListAll(@RequestBody String data) {
		String deviceList = null;
		DanbayAPI danbayapi = new DanbayAPI();
		String result = null;
		String loginJson = danbayapi.authorizationLogin(data, equipmentSysProviderServiceImpl);
		JSONObject resjson = JSONObject.fromObject(loginJson);
		if (resjson.getInt("code") == 200) {
			JSONObject jsondata = resjson.getJSONObject("result");
			String ticket = jsondata.getString("ticket");
			String mtoken = jsondata.getString("mtoken");
			String userName = resjson.getString("userName");
			String host = resjson.getString("host");
			String token = resjson.getString("token");
			JSONObject rqjson = new JSONObject();
			rqjson.put("mtoken", mtoken);
			rqjson.put("userName", userName);
			rqjson.put("host", host);
			rqjson.put("token", token);
			result = danbayapi.getDeviceListAll(JsonUtil.objectToJson(rqjson));
			if (JSONObject.fromObject(result).getInt("code") == 200) {
				 
				map.put("code", "200");
				map.put("data", result);
			}
		} else {
			map.put("code", "201");
			map.put("msg", "查询失败");
		}
		logger.error("------查询所有设备------" + map);
		return JsonUtil.objectToJson(map);
	}

	/**
	 * 获取所有设备列表（根据小区物业编码）
	 * 
	 * @param data
	 * @return
	 */
	@RequestMapping(value = "/getDeviceList", method = RequestMethod.POST)
	@ResponseBody
	public String getDeviceList(@RequestBody String data) {
		String result = null;
		DanbayAPI danbayapi = new DanbayAPI();
		String loginJson = danbayapi.authorizationLogin(data, equipmentSysProviderServiceImpl);
		JSONObject resjson = JSONObject.fromObject(loginJson);
		if (resjson.getInt("code") == 200) {
			JSONObject jsondata = resjson.getJSONObject("result");
			String ticket = jsondata.getString("ticket");
			String mtoken = jsondata.getString("mtoken");
			String userName = resjson.getString("userName");
			String host = resjson.getString("host");
			String token = resjson.getString("token");
			JSONObject rqjson = new JSONObject();
			rqjson.put("mtoken", mtoken);
			rqjson.put("userName", userName);
			rqjson.put("host", host);
			rqjson.put("token", token);
			User user = (User) session.getAttribute(token);
			if (user != null) {
				rqjson.put("buildingId", user.getPmid());
				result = danbayapi.getDeviceList(JsonUtil.objectToJson(rqjson));
				if (JSONObject.fromObject(result).getInt("code") == 200) {
					map.put("code", "200");
					map.put("data", result);
					logger.error("------获取所有设备列表------" + map);
					return JsonUtil.objectToJson(map);
				}
			}
		}
		map.put("code", "200");
		map.put("data", result);
		logger.error("------获取所有设备列表------" + map);
		return JsonUtil.objectToJson(map);
	}
}
