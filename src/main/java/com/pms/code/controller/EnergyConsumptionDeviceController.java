package com.pms.code.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pms.code.controller.danbay.DanbayAPI;
import com.pms.code.entity.base.EnergyConsumptionDevice;
import com.pms.code.entity.base.OwnerInfo;
import com.pms.code.entity.base.PropertyManagementDepartment;
import com.pms.code.entity.base.User;
import com.pms.code.service.EnergyConsumptionDeviceService;
import com.pms.code.service.EquipmentSysProviderService;
import com.pms.code.service.OwnerInfoService;
import com.pms.code.service.PropertyManagementDepartmentService;
import com.pms.code.util.JsonUtil;
import com.pms.code.util.Page;

import net.sf.json.JSONObject;

@Controller
public class EnergyConsumptionDeviceController extends BaseController {
	private Logger logger = LoggerFactory.getLogger(EnergyConsumptionDeviceController.class);

	@Autowired
	private EnergyConsumptionDeviceService energyConsumptionDeviceServiceImpl;

	@Autowired
	private OwnerInfoService ownerInfoServiceImpl;

	/**
	 * 查询能耗设备
	 * @param data
	 * @return
	 */
	@RequestMapping(value = "/queryEnergyConsumptionDeviceByHouseIds", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String queryEnergyConsumptionDeviceByHouseIds(@RequestBody String data) {
		JSONObject json = JSONObject.fromObject(data);
		int pageIndex = json.getInt("pageIndex");
		int pageSize = json.getInt("pageSize");
		int energyType=json.getInt("energyType");
		String token = json.getString("token");
		if (pageIndex <= 0) {
			pageIndex = 1;
		}
		User user = (User) session.getAttribute(token);
		if (user != null) {
			HashMap<String, Object> hmap = new HashMap<String, Object>();
			List<OwnerInfo> ownerInfos=null;

			if(user.getGm_type()==3)
			{
				hmap.put("pmid",user.getPmid());
				ownerInfos = ownerInfoServiceImpl.queryOwnerInfoByPidPmid(hmap);
			}
			else if(user.getGm_type()==2)
			{
				int pmid=json.containsKey("pmid")?json.getInt("pmid"):0;
				hmap.put("pmid",pmid);
				hmap.put("pid",user.getPid());
				ownerInfos = ownerInfoServiceImpl.queryOwnerInfoByPidPmid(hmap);
			}
			List<String> houseIds = new ArrayList<String>();
			for (OwnerInfo oi : ownerInfos) {
				houseIds.add(oi.getManagementDepartmentName() + oi.getRooms());
			}
			hmap.put("pageIndex", pageIndex);
			hmap.put("pageSize", pageSize);
			hmap.put("energyType",energyType);
			hmap.put("houseIds", houseIds);
			hmap = energyConsumptionDeviceServiceImpl.queryEnergyConsumptionDeviceByHouseIds(hmap);
			Page<EnergyConsumptionDevice> page = (Page<EnergyConsumptionDevice>) hmap.get("page");
			if (page != null) {
				map.put("code", "200");
				map.put("data",page);
				logger.error("------查询能耗设备成功------" + map);
				return JsonUtil.objectToJson(map);
			}
		}
		map.put("code","201");
		map.put("msg","查询能耗设备异常");
		logger.error("------查询维修服务------" + map);
		return JsonUtil.objectToJson(map);
	}

}



/*	*//**
		 * 插入能耗
		 * 
		 * @param energyConsumptionDevice
		 * @return
		 */
/*
 * @RequestMapping(value = "/insertEnergyConsumptionDevice", method =
 * RequestMethod.POST, produces = "application/json;charset=UTF-8")
 * 
 * @ResponseBody public String insertEnergyConsumptionDevice(@RequestBody String
 * data) { JSONObject json = JSONObject.fromObject(data);
 * EnergyConsumptionDevice energyConsumptionDevice = new
 * EnergyConsumptionDevice();
 * energyConsumptionDevice.setDevice_status(json.getInt("deviceStatus"));
 * energyConsumptionDevice.setEnergy_type(json.getInt("energyType"));
 * energyConsumptionDevice.setSub_type(json.getInt("subType"));
 * energyConsumptionDevice.setMeter_value(json.getDouble("electricQuantity"));
 * energyConsumptionDevice.setDevice_id(json.getString("deviceId"));
 * energyConsumptionDevice.setHouse_id(json.getString("houseId")); boolean flag
 * = energyConsumptionDeviceServiceImpl.insertEnergyConsumptionDevice(
 * energyConsumptionDevice); if (flag) { map.put("code", "200"); map.put("msg",
 * "插入完成"); } else { map.put("code", "201"); map.put("msg", "插入失败"); }
 * logger.error("-----插入能耗-----" + map); return JsonUtil.objectToJson(map); }
 * 
 *//**
	 * 查询能耗设备列表
	 * 
	 * @param data
	 * @return
	 */
/*
 * @RequestMapping(value = "getEnergyDeviceList", method = RequestMethod.POST,
 * produces = "application/json;charset=UTF-8")
 * 
 * @ResponseBody public String getEnergyDeviceList(@RequestBody String data) {
 * String result = null; DanbayAPI danbayapi = new DanbayAPI(); String loginJson
 * = danbayapi.authorizationLogin(data, equipmentSysProviderServiceImpl);
 * JSONObject resjson = JSONObject.fromObject(loginJson); if
 * (resjson.getInt("code") == 200) { JSONObject jsondata =
 * resjson.getJSONObject("result"); JSONObject rqjson = new JSONObject();
 * rqjson.put("mtoken", jsondata.getString("mtoken")); rqjson.put("host",
 * resjson.getString("host")); rqjson.put("userName",
 * resjson.getString("userName")); rqjson.put("token",
 * resjson.getString("token")); result =
 * danbayapi.getEnergyDeviceList(JsonUtil.objectToJson(rqjson)); if
 * (JSONObject.fromObject(resjson).getInt("code") == 200) { map.put("code",
 * "200"); map.put("data", result); logger.error("-----插入能耗-----" + map); return
 * JsonUtil.objectToJson(map); } } map.put("code", "201"); map.put("msg",
 * "查询能耗设备列表失败"); logger.error("-----查询能耗设备列表-----" + map); return
 * JsonUtil.objectToJson(map); }
 * 
 *//**
	 * 查询单个能耗设备信息（根据设备ID）
	 * 
	 * @param data
	 * @return
	 */
/*
 * @RequestMapping(value = "getEnergyDeviceInfo", method = RequestMethod.POST,
 * produces = "application/json;charset=UTF-8")
 * 
 * @ResponseBody public String getEnergyDeviceInfo(@RequestBody String data) {
 * String result = null; JSONObject json = JSONObject.fromObject(data); int id =
 * json.getInt("id"); DanbayAPI danbayapi = new DanbayAPI(); String loginJson =
 * danbayapi.authorizationLogin(data, equipmentSysProviderServiceImpl);
 * JSONObject resjson = JSONObject.fromObject(loginJson); if
 * (resjson.getInt("code") == 200) { EnergyConsumptionDevice
 * energyConsumptionDevice =
 * energyConsumptionDeviceServiceImpl.queryEnergyConsumptionDeviceById(id); if
 * (energyConsumptionDevice != null) { JSONObject jsondata =
 * resjson.getJSONObject("result"); JSONObject rqjson = new JSONObject();
 * rqjson.put("mtoken", jsondata.getString("mtoken")); rqjson.put("deviceId",
 * energyConsumptionDevice.getDevice_id()); rqjson.put("host",
 * resjson.getString("host")); rqjson.put("userName",
 * resjson.getString("userName")); rqjson.put("token",
 * resjson.getString("token")); result =
 * danbayapi.getEnergyDeviceInfo(JsonUtil.objectToJson(rqjson)); if
 * (JSONObject.fromObject(result).getInt("code") == 200) { map.put("code",
 * "200"); map.put("data", result); logger.error("-----查询单个能耗设备信息-----" + map);
 * return JsonUtil.objectToJson(map); } } } map.put("code", "201");
 * map.put("msg", "查询单个能耗设备信息失败"); logger.error("-----查询单个能耗设备信息-----" + map);
 * return JsonUtil.objectToJson(map); }
 * 
 *//**
	 * 查询当日能耗设备示数
	 * 
	 * @param data
	 * @return
	 */
/*
 * @RequestMapping(value = "getEnergyDailyReading", method = RequestMethod.POST,
 * produces = "application/json;charset=UTF-8")
 * 
 * @ResponseBody public String getEnergyDailyReading(@RequestBody String data) {
 * String result = null; JSONObject json = JSONObject.fromObject(data); int id =
 * json.getInt("id"); String readTime = json.getString("readTime"); DanbayAPI
 * danbayapi = new DanbayAPI(); String loginJson =
 * danbayapi.authorizationLogin(data, equipmentSysProviderServiceImpl);
 * JSONObject resjson = JSONObject.fromObject(loginJson); if
 * (resjson.getInt("code") == 200) { EnergyConsumptionDevice
 * energyConsumptionDevice =
 * energyConsumptionDeviceServiceImpl.queryEnergyConsumptionDeviceById(id); if
 * (energyConsumptionDevice != null) { JSONObject jsondata =
 * resjson.getJSONObject("result"); JSONObject rqjson = new JSONObject();
 * rqjson.put("mtoken", jsondata.getString("mtoken")); rqjson.put("deviceId",
 * energyConsumptionDevice.getDevice_id()); rqjson.put("host",
 * resjson.getString("host")); rqjson.put("userName",
 * resjson.getString("userName")); rqjson.put("token",
 * resjson.getString("token")); rqjson.put("readTime", readTime); result =
 * danbayapi.getEnergyDailyReading(JsonUtil.objectToJson(rqjson)); if
 * (JSONObject.fromObject(result).getInt("code") == 200) { map.put("code",
 * "200"); map.put("data", result); logger.error("-----查询当日能耗设备示数信息-----" +
 * map); return JsonUtil.objectToJson(map); } } } map.put("code", "200");
 * map.put("data", result); logger.error("-----查询当日能耗设备示数失败-----" + map); return
 * JsonUtil.objectToJson(map); }
 * 
 *//**
	 * 查询能耗设备区间用量
	 * 
	 * @param data
	 * @return
	 *//*
	 * @RequestMapping(value = "getEnergySectionConsumption", method =
	 * RequestMethod.POST, produces = "application/json;charset=UTF-8")
	 * 
	 * @ResponseBody public String getEnergySectionConsumption(@RequestBody
	 * String data) { String result = null; JSONObject json =
	 * JSONObject.fromObject(data); int id = json.getInt("id"); String startTime
	 * = json.getString("startTime"); String endTime =
	 * json.getString("endTime"); DanbayAPI danbayapi = new DanbayAPI(); String
	 * loginJson = danbayapi.authorizationLogin(data,
	 * equipmentSysProviderServiceImpl); JSONObject resjson =
	 * JSONObject.fromObject(loginJson); if (resjson.getInt("code") == 200) {
	 * EnergyConsumptionDevice energyConsumptionDevice =
	 * energyConsumptionDeviceServiceImpl.queryEnergyConsumptionDeviceById(id);
	 * if (energyConsumptionDevice != null) { JSONObject jsondata =
	 * resjson.getJSONObject("result"); JSONObject rqjson = new JSONObject();
	 * rqjson.put("mtoken", jsondata.getString("mtoken"));
	 * rqjson.put("deviceId", energyConsumptionDevice.getDevice_id());
	 * rqjson.put("host", resjson.getString("host")); rqjson.put("userName",
	 * resjson.getString("userName")); rqjson.put("token",
	 * resjson.getString("token")); rqjson.put("startTime", startTime);
	 * rqjson.put("endTime", endTime); result =
	 * danbayapi.getEnergySectionConsumption(JsonUtil.objectToJson(rqjson)); if
	 * (JSONObject.fromObject(result).getInt("code") == 200) { map.put("code",
	 * "200"); map.put("data", result); logger.error("-----查询能耗设备区间用量-----" +
	 * map); return JsonUtil.objectToJson(map); } } } map.put("code", "200");
	 * map.put("data", result); logger.error("-----查询能耗设备区间用量失败-----" + map);
	 * return JsonUtil.objectToJson(map); } }
	 */