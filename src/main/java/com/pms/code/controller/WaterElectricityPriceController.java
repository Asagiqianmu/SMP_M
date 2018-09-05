package com.pms.code.controller;

import java.sql.Timestamp;
import java.util.Date;
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

import com.pms.code.entity.base.CostType;
import com.pms.code.entity.base.OwnerPaycat;
import com.pms.code.entity.base.User;
import com.pms.code.entity.base.WaterElectricityPrice;
import com.pms.code.service.CostTypeService;
import com.pms.code.service.WaterElectricityPriceService;
import com.pms.code.util.JsonUtil;
import com.pms.code.util.Page;
import net.sf.json.JSONObject;

@Controller
public class WaterElectricityPriceController extends BaseController {
	private Logger logger = LoggerFactory.getLogger(WaterElectricityPriceController.class);

	@Autowired
	private WaterElectricityPriceService waterElectricityPriceServiceImpl;

	@Autowired
	private CostTypeService costTypeServiceImpl;

	/**
	 * 查询水电价格
	 * 
	 * @param data
	 * @return
	 */
	@RequestMapping(value = "/queryWaterElectricityPrice", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String queryWaterElectricityPrice(@RequestBody String data) {
		JSONObject json = JSONObject.fromObject(data);
		String token = json.getString("token");
		User user = (User) session.getAttribute(token);
		HashMap<String, Object> hmap = new HashMap<String, Object>();
		hmap.put("pmid", user.getPmid());
		List<WaterElectricityPrice> waterElectricityPrices = waterElectricityPriceServiceImpl
				.queryWaterElectricityPrice(hmap);
		if (waterElectricityPrices != null) {
			List<CostType> costTypeList = costTypeServiceImpl.costTypeList();
			if (costTypeList != null) {
				map.put("code", "200");
				map.put("data", waterElectricityPrices);
				map.put("costTypeList", costTypeList);
			}
		} else {
			map.put("code", "201");
			map.put("msg", "查询水电价格失败");
		}
		logger.error("------查询水电价格------" + map);
		return JsonUtil.objectToJson(map);
	}

	/**
	 * 修改水电费价格
	 * 
	 * @param data
	 * @return
	 */
	@RequestMapping(value = "/updateWaterElectricityPrice", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String updateWaterElectricityPrice(@RequestBody String data) {
		JSONObject json = JSONObject.fromObject(data);
		HashMap<String, Object> hmap = new HashMap<String, Object>();
		double price = json.getDouble("price");
		String unit=json.containsKey("unit")?json.getString("unit"):"";
		int id = json.getInt("id");
		hmap.put("price", price);
		hmap.put("unit",unit );
		hmap.put("id", id);
		boolean flag = waterElectricityPriceServiceImpl.updateWaterElectricityPrice(hmap);
		if (flag) {
			map.put("code", "200");
			map.put("msg", "修改水电费价格成功");
		} else {
			map.put("code", "201");
			map.put("msg", "修改水电费价格失败");
		}
		logger.error("------修改水电费价格------" + map);
		return JsonUtil.objectToJson(map);
	}

	/**
	 * 插入水电费信息
	 * 
	 * @param data
	 * @return  
	 */
	@RequestMapping(value = "/insertWaterElectricityPrice", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String insertWaterElectricityPrice(@RequestBody String data) {
		JSONObject json = JSONObject.fromObject(data);
		String token = json.getString("token");
		double price = json.getDouble("price");
		int costTypeId = json.getInt("costTypeId");
		String unit = json.getString("unit");
		User user = (User) session.getAttribute(token);
		if (user != null) {
			// 查询是否有相同的物业和缴费类型
			WaterElectricityPrice wp = new WaterElectricityPrice();
			wp.setPmid(user.getPmid());
			wp.setUnit(unit);
			wp.setCreateTime(new Timestamp(new Date().getTime()));
			wp.setPrice(price);
			wp.setCostTypeId(costTypeId);
			wp.setCreateTime(new Timestamp(new Date().getTime()));
			WaterElectricityPrice waterElectricityPriceByWep = waterElectricityPriceServiceImpl
					.queryWaterElectricityPriceByWep(wp);
			//如果存在相同的物业小区缴费类型,则修改价格
			if (waterElectricityPriceByWep != null) {
				HashMap<String, Object> hmap = new HashMap<String, Object>();
				hmap.put("pmid", user.getPmid());
				hmap.put("costTypeId", costTypeId);
				hmap.put("price", price);
				hmap.put("unit", unit);
				boolean updateFlag = waterElectricityPriceServiceImpl.updateWaterElectricityPriceByWep(hmap);
				if (updateFlag) {
					map.put("code", "200");
					map.put("msg", "插入水电费信息成功");
				} else {
					map.put("code", "201");
					map.put("msg", "插入水电费信息失败");
				}
			} else {//如果不存在相同的物业小区缴费类型,则插入
				boolean flag = waterElectricityPriceServiceImpl.insertWaterElectricityPrice(wp);
				if (flag) {
					map.put("code", "200");
					map.put("msg", "插入水电费信息成功");
				} else {
					map.put("code", "201");
					map.put("msg", "插入水电费信息失败");
				}
			}
		} else {
			map.put("code", "201");
			map.put("msg", "查询异常");
		}
		logger.error("------插入水电费信息------" + map);
		return JsonUtil.objectToJson(map);
	}
}
