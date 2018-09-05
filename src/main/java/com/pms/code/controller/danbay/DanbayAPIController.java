package com.pms.code.controller.danbay;

import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.pms.code.controller.BaseController;
import com.pms.code.entity.base.EquipmentSysProvider;
import com.pms.code.entity.base.User;
import com.pms.code.service.EquipmentSysProviderService;
import com.pms.code.util.HttpUtil;
import com.pms.code.util.JsonUtil;
import com.pms.code.util.RandomUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 蛋贝系统API
 * 
 * @author dengfei E-mail:dengfei200857@163.com
 * @time 2018年4月3日 下午2:29:07
 */
@Controller
public class DanbayAPIController extends BaseController {
	public Logger logger = LoggerFactory.getLogger(DanbayAPIController.class);

	@Autowired
	public EquipmentSysProviderService equipmentSysProviderServiceImpl;

	/**
	 * 蛋贝系统登录
	 * 
	 * @author dengfei E-mail:dengfei200857@163.com
	 * @time 2018年4月3日 下午2:38:00
	 * @param data
	 * @return
	 */
	@RequestMapping(value = "/danbayauthorizationLogin", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String authorizationLogin(@RequestBody String data) {
		JSONObject json = JSONObject.fromObject(data);
		String token = json.getString("token");
		System.out.println(null == session);
		System.out.println(null == map);
		User user = (User) session.getAttribute(token);
		if (user != null) {
			int dlc_id = json.getInt("dlc_id");// 门锁公司编号
			EquipmentSysProvider equipmentSysProvider = equipmentSysProviderServiceImpl.findEquipmentSysProviderByID(dlc_id);
			String host = equipmentSysProvider.getHost();// 服务器地址
			String sys_username = equipmentSysProvider.getSys_username();
			String sys_passwd = equipmentSysProvider.getSys_passwd();
			String url = host + "/loginAccess";
			String random_code = RandomUtils.generateString(32);
			HashMap<String, Object> hMap = new HashMap<String, Object>();
			hMap.put("mc_username", sys_username);
			hMap.put("mc_password", sys_passwd);
			hMap.put("random_code", random_code);
			JSONObject reJson = HttpUtil.doPost(url, hMap);
			logger.debug("authorizationLogin-----------" + reJson.toString());
			if (reJson != null) {
				if (reJson.getInt("status") == 200) {
					map.put("code", 200);
					map.put("host", host);
					map.put("result", reJson.getJSONObject("result"));
					return JsonUtil.objectToJson(map);
				}
			}
		}
		map.put("code", 201);
		map.put("msg", "登录授权失败");
		return JsonUtil.objectToJson(map);
	}

	/**
	 * 查询所有设备列表
	 * 
	 * @author dengfei E-mail:dengfei200857@163.com
	 * @time 2018年4月3日 下午2:51:49
	 * @param data
	 * @return
	 */
	@RequestMapping(value = "/danbaygetDeviceListAll", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getDeviceListAll(@RequestBody String data) {
		JSONObject json = JSONObject.fromObject(data);
		String token = json.getString("token");
		User user = (User) session.getAttribute(token);
		if (user != null) {
			String mtoken = json.getString("mtoken");// 用户凭证
			String host = json.getString("host");// 服务器地址
			String url = host + "/deviceInfo/getDeviceListAll";
			HashMap<String, Object> hMap = new HashMap<String, Object>();
			hMap.put("mtoken", mtoken);
			JSONObject reJson = HttpUtil.doPost(url, hMap);
			logger.debug("getDeviceListAll-----------" + reJson.toString());
			if (reJson != null) {
				if (reJson.getInt("status") == 200) {
					map.put("code", 200);
					map.put("result", reJson.getJSONArray("result"));
					return JsonUtil.objectToJson(map);
				}
			}
		}
		map.put("code", 201);
		map.put("msg", "查询失败");
		return JsonUtil.objectToJson(map);
	}

	/**
	 * 查询门锁设备列表
	 * 
	 * @author dengfei E-mail:dengfei200857@163.com
	 * @time 2018年4月3日 下午3:13:11
	 * @param data
	 * @return
	 */
	@RequestMapping(value = "/danbaygetLockList", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getLockList(@RequestBody String data) {
		JSONObject json = JSONObject.fromObject(data);
		String token = json.getString("token");
		User user = (User) session.getAttribute(token);
		if (user != null) {
			String mtoken = json.getString("mtoken");// 用户凭证
			String host = json.getString("host");// 服务器地址
			String url = host + "/deviceInfo/getLockList";
			HashMap<String, Object> hMap = new HashMap<String, Object>();
			hMap.put("mtoken", mtoken);
			JSONObject reJson = HttpUtil.doPost(url, hMap);
			logger.debug("getLockList-----------" + reJson.toString());
			if (reJson != null) {
				if (reJson.getInt("status") == 200) {
					map.put("code", 200);
					map.put("result", reJson.getJSONArray("result"));
					return JsonUtil.objectToJson(map);
				}
			}
		}
		map.put("code", 201);
		map.put("msg", "查询失败");
		return JsonUtil.objectToJson(map);
	}

	/**
	 * 查询单个门锁设备信息（根据设备ID）
	 * 
	 * @author dengfei E-mail:dengfei200857@163.com
	 * @time 2018年4月3日 下午3:15:55
	 * @param data
	 * @return
	 */
	@RequestMapping(value = "/danbaygetLockInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getLockInfo(@RequestBody String data) {
		JSONObject json = JSONObject.fromObject(data);
		String token = json.getString("token");
		User user = (User) session.getAttribute(token);
		if (user != null) {
			String deviceId = json.getString("deviceId");// 设备ID
			String mtoken = json.getString("mtoken");// 用户凭证
			String host = json.getString("host");// 服务器地址
			String url = host + "/deviceInfo/getLockList";
			HashMap<String, Object> hMap = new HashMap<String, Object>();
			hMap.put("mtoken", mtoken);
			hMap.put("deviceId", deviceId);
			JSONObject reJson = HttpUtil.doPost(url, hMap);
			logger.debug("getLockInfo-----------" + reJson.toString());
			if (reJson != null) {
				if (reJson.getInt("status") == 200) {
					map.put("code", 200);
					map.put("deviceId", deviceId);
					map.put("result", reJson.getJSONObject("result"));
					return JsonUtil.objectToJson(map);
				}
			}
		}
		map.put("code", 201);
		map.put("msg", "查询失败");
		return JsonUtil.objectToJson(map);
	}

	/**
	 * 查询门锁密码列表（根据设备ID）
	 * 
	 * @author dengfei E-mail:dengfei200857@163.com
	 * @time 2018年4月3日 下午3:19:05
	 * @param data
	 * @return
	 */
	@RequestMapping(value = "/danbaygetLockPwdList", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getLockPwdList(@RequestBody String data) {
		JSONObject json = JSONObject.fromObject(data);
		String token = json.getString("token");
		User user = (User) session.getAttribute(token);
		if (user != null) {
			String deviceId = json.getString("deviceId");// 设备ID
			String mtoken = json.getString("mtoken");// 用户凭证
			String host = json.getString("host");// 服务器地址
			String url = host + "/deviceInfo/getLockPwdList";
			HashMap<String, Object> hMap = new HashMap<String, Object>();
			hMap.put("mtoken", mtoken);
			hMap.put("deviceId", deviceId);
			JSONObject reJson = HttpUtil.doPost(url, hMap);
			logger.debug("getLockPwdList-----------" + reJson.toString());
			if (reJson != null) {
				if (reJson.getInt("status") == 200) {
					map.put("code", 200);
					map.put("result", reJson.getJSONArray("result"));
					return JsonUtil.objectToJson(map);
				}
			}
		}
		map.put("code", 201);
		map.put("msg", "查询失败");
		return JsonUtil.objectToJson(map);
	}

	/**
	 * 门锁——新增密码 对管家密码、客房密码、临时密码进行新增。
	 * 
	 * @author dengfei E-mail:dengfei200857@163.com
	 * @time 2018年4月3日 下午3:20:22
	 * @param data
	 * @return
	 */
	@RequestMapping(value = "/danbayaddLockPwd", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String addLockPwd(@RequestBody String data) {
		JSONObject json = JSONObject.fromObject(data);
		String token = json.getString("token");
		User user = (User) session.getAttribute(token);
		if (user != null) {
			String deviceId = json.getString("deviceId");// 设备ID
			String password = json.getString("password");// 密码
			int pwdType = json.getInt("pwdType");// 密码类型：0临时密码，2管家密码，3客房密码。其他值不予处理
			String mtoken = json.getString("mtoken");// 用户凭证
			String host = json.getString("host");// 服务器地址
			String url = host + "/deviceCtrl/lockPwd/addPwd";
			HashMap<String, Object> hMap = new HashMap<String, Object>();
			hMap.put("mtoken", mtoken);
			hMap.put("deviceId", deviceId);
			hMap.put("password", password);
			hMap.put("pwdType", pwdType);
			JSONObject reJson = HttpUtil.doPost(url, hMap);
			logger.debug("getLockPwdList-----------" + reJson.toString());
			if (reJson != null) {
				if (reJson.getInt("status") == 200) {
					map.put("code", 200);
					map.put("result", reJson.getJSONObject("result"));
					return JsonUtil.objectToJson(map);
				}
			}
		}
		map.put("code", 201);
		map.put("msg", "新增失败");
		return JsonUtil.objectToJson(map);
	}

	/**
	 * 门锁——新增密码（指定时效） 对管家密码、客房密码、临时密码进行新增，并且改密码在指定的起止时间内有效。
	 * 
	 * @author dengfei E-mail:dengfei200857@163.com
	 * @time 2018年4月3日 下午3:26:04
	 * @param data
	 * @return
	 */
	@RequestMapping(value = "/danbayaddLockPwdWithDate", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String addLockPwdWithDate(@RequestBody String data) {
		JSONObject json = JSONObject.fromObject(data);
		String token = json.getString("token");
		User user = (User) session.getAttribute(token);
		if (user != null) {
			String deviceId = json.getString("deviceId");// 设备ID
			String password = json.getString("password");// 密码
			int pwdType = json.getInt("pwdType");// 密码类型：0临时密码，2管家密码，3客房密码。其他值不予处理
			String mtoken = json.getString("mtoken");// 用户凭证
			String beginTime = json.getString("beginTime");// 起始时间，格式：yyyy-MM-dd
															// HH:mm:ss
			String endTime = json.getString("endTime");// 截止时间，格式：yyyy-MM-dd
														// HH:mm:ss
			String host = json.getString("host");// 服务器地址
			String url = host + "/deviceCtrl/lockPwd/addPwdWithDate";
			HashMap<String, Object> hMap = new HashMap<String, Object>();
			hMap.put("mtoken", mtoken);
			hMap.put("deviceId", deviceId);
			hMap.put("password", password);
			hMap.put("pwdType", pwdType);
			hMap.put("beginTime", beginTime);
			hMap.put("endTime", endTime);
			JSONObject reJson = HttpUtil.doPost(url, hMap);
			logger.debug("addPwdWithDate-----------" + reJson.toString());
			if (reJson != null) {
				if (reJson.getInt("status") == 200) {
					map.put("code", 200);
					map.put("result", reJson.getJSONObject("result"));
					return JsonUtil.objectToJson(map);
				}
			}
		}
		map.put("code", 201);
		map.put("msg", "新增失败");
		return JsonUtil.objectToJson(map);
	}

	/**
	 * 门锁——修改密码
	 * 
	 * @author dengfei E-mail:dengfei200857@163.com
	 * @time 2018年4月3日 下午3:32:16
	 * @param data
	 * @return
	 */
	@RequestMapping(value = "/danbayeditLockPwd", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String editLockPwd(@RequestBody String data) {
		JSONObject json = JSONObject.fromObject(data);
		String token = json.getString("token");
		User user = (User) session.getAttribute(token);
		if (user != null) {
			String deviceId = json.getString("deviceId");// 设备ID
			int pwdType = json.getInt("pwdType");// 密码类型：2管家密码，3客房密码。其他值不予处理
			String password = json.getString("password");// 新的密码
			int pwdID = json.getInt("pwdID");// 密码ID
			String mtoken = json.getString("mtoken");// 用户凭证
			String host = json.getString("host");// 服务器地址
			String url = host + "/deviceCtrl/lockPwd/editPwd";
			HashMap<String, Object> hMap = new HashMap<String, Object>();
			hMap.put("mtoken", mtoken);
			hMap.put("deviceId", deviceId);
			hMap.put("password", password);
			hMap.put("pwdType", pwdType);
			hMap.put("pwdID", pwdID);
			JSONObject reJson = HttpUtil.doPost(url, hMap);
			logger.debug("editLockPwd-----------" + reJson.toString());
			if (reJson != null) {
				if (reJson.getInt("status") == 200) {
					map.put("code", 200);
					map.put("result", reJson.getJSONObject("result"));
					return JsonUtil.objectToJson(map);
				}
			}
		}
		map.put("code", 201);
		map.put("msg", "修改失败");
		return JsonUtil.objectToJson(map);
	}

	/**
	 * 门锁——修改密码(指定时效)
	 * 
	 * @author dengfei E-mail:dengfei200857@163.com
	 * @time 2018年4月3日 下午3:35:29
	 * @param data
	 * @return
	 */
	@RequestMapping(value = "/danbayeditLockPwdWithDate", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String editLockPwdWithDate(@RequestBody String data) {
		JSONObject json = JSONObject.fromObject(data);
		String token = json.getString("token");
		User user = (User) session.getAttribute(token);
		if (user != null) {
			String deviceId = json.getString("deviceId");// 设备ID
			int pwdType = json.getInt("pwdType");// 密码类型：2管家密码，3客房密码。其他值不予处理
			String password = json.getString("password");// 新的密码
			int pwdID = json.getInt("pwdID");// 密码ID
			String mtoken = json.getString("mtoken");// 用户凭证
			String beginTime = json.getString("beginTime");// 起始时间，格式：yyyy-MM-dd
															// HH:mm:ss
			String endTime = json.getString("endTime");// 截止时间，格式：yyyy-MM-dd
														// HH:mm:ss
			String host = json.getString("host");// 服务器地址
			String url = host + "/deviceCtrl/lockPwd/editPwdWithDate";
			HashMap<String, Object> hMap = new HashMap<String, Object>();
			hMap.put("mtoken", mtoken);
			hMap.put("deviceId", deviceId);
			hMap.put("password", password);
			hMap.put("pwdID", pwdID);
			hMap.put("pwdType", pwdType);
			hMap.put("beginTime", beginTime);
			hMap.put("endTime", endTime);
			JSONObject reJson = HttpUtil.doPost(url, hMap);
			logger.debug("editLockPwd-----------" + reJson.toString());
			if (reJson != null) {
				if (reJson.getInt("status") == 200) {
					map.put("code", 200);
					map.put("result", reJson.getJSONObject("result"));
					return JsonUtil.objectToJson(map);
				}
			}
		}
		map.put("code", 201);
		map.put("msg", "修改失败");
		return JsonUtil.objectToJson(map);
	}

	/**
	 * 门锁——删除密码
	 * 
	 * @author dengfei E-mail:dengfei200857@163.com
	 * @time 2018年4月3日 下午3:42:15
	 * @param data
	 * @return
	 */
	@RequestMapping(value = "/danbaydelLockPwd", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String delLockPwd(@RequestBody String data) {
		JSONObject json = JSONObject.fromObject(data);
		String token = json.getString("token");
		User user = (User) session.getAttribute(token);
		if (user != null) {
			String deviceId = json.getString("deviceId");// 设备ID
			int pwdType = json.getInt("pwdType");// 密码类型：0临时密码，2管家密码，3客房密码。其他值不予处理
			int pwdID = json.getInt("pwdID");// 密码ID
			String mtoken = json.getString("mtoken");// 用户凭证
			String host = json.getString("host");// 服务器地址
			String url = host + "/deviceCtrl/lockPwd/delPwd";
			HashMap<String, Object> hMap = new HashMap<String, Object>();
			hMap.put("mtoken", mtoken);
			hMap.put("deviceId", deviceId);
			hMap.put("passID", pwdID);
			hMap.put("pwdType", pwdType);
			JSONObject reJson = HttpUtil.doPost(url, hMap);
			logger.debug("delLockPwd-----------" + reJson.toString());
			if (reJson != null) {
				if (reJson.getInt("status") == 200) {
					map.put("code", 200);
					map.put("result", reJson.getJSONObject("result"));
					return JsonUtil.objectToJson(map);
				}
			}
		}
		map.put("code", 201);
		map.put("msg", "删除失败");
		return JsonUtil.objectToJson(map);
	}

	/**
	 * 门锁——冻结或启用密码
	 * 
	 * @author dengfei E-mail:dengfei200857@163.com
	 * @time 2018年4月3日 下午3:45:21
	 * @param data
	 * @return
	 */
	@RequestMapping(value = "/danbayupdateLockPwd", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String updateLockPwd(@RequestBody String data) {
		JSONObject json = JSONObject.fromObject(data);
		String token = json.getString("token");
		User user = (User) session.getAttribute(token);
		if (user != null) {
			String deviceId = json.getString("deviceId");// 设备ID
			int updateTpye = json.getInt("updateTpye");// 更新类型，0冻结，1启用
			int pwdType = json.getInt("pwdType");// 密码类型：2管家密码，3客房密码。其他值不予处理
			int pwdID = json.getInt("pwdID");// 密码ID
			String mtoken = json.getString("mtoken");// 用户凭证
			String host = json.getString("host");// 服务器地址
			String url = host + "/deviceCtrl/lockPwd/updatePwd";
			HashMap<String, Object> hMap = new HashMap<String, Object>();
			hMap.put("mtoken", mtoken);
			hMap.put("deviceId", deviceId);
			hMap.put("pwdId", pwdID);
			hMap.put("pwdType", pwdType);
			hMap.put("updateTpye", updateTpye);
			JSONObject reJson = HttpUtil.doPost(url, hMap);
			logger.debug("updateLockPwd-----------" + reJson.toString());
			if (reJson != null) {
				if (reJson.getInt("status") == 200) {
					map.put("code", 200);
					map.put("result", reJson.getJSONObject("result"));
					return JsonUtil.objectToJson(map);
				}
			}
		}
		map.put("code", 201);
		map.put("msg", "更新失败");
		return JsonUtil.objectToJson(map);
	}

	/**
	 * 查询能耗设备列表
	 * 
	 * @author dengfei E-mail:dengfei200857@163.com
	 * @time 2018年4月3日 下午3:48:58
	 * @param data
	 * @return
	 */
	@RequestMapping(value = "/danbaygetEnergyDeviceList", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getEnergyDeviceList(@RequestBody String data) {
		JSONObject json = JSONObject.fromObject(data);
		String token = json.getString("token");
		User user = (User) session.getAttribute(token);
		if (user != null) {
			String mtoken = json.getString("mtoken");// 用户凭证
			String host = json.getString("host");// 服务器地址
			String url = host + "/deviceInfo/getEnergyDeviceList";
			HashMap<String, Object> hMap = new HashMap<String, Object>();
			hMap.put("mtoken", mtoken);
			JSONObject reJson = HttpUtil.doPost(url, hMap);
			logger.debug("getEnergyDeviceList-----------" + reJson.toString());
			if (reJson != null) {
				if (reJson.getInt("status") == 200) {
					map.put("code", 200);
					map.put("result", reJson.getJSONArray("result"));
					return JsonUtil.objectToJson(map);
				}
			}
		}
		map.put("code", 201);
		map.put("msg", "更新失败");
		return JsonUtil.objectToJson(map);
	}

	/**
	 * 查询单个能耗设备信息（根据设备ID）
	 * 
	 * @author dengfei E-mail:dengfei200857@163.com
	 * @time 2018年4月3日 下午3:51:09
	 * @param data
	 * @return
	 */
	@RequestMapping(value = "/danbaygetEnergyDeviceInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getEnergyDeviceInfo(@RequestBody String data) {
		JSONObject json = JSONObject.fromObject(data);
		String token = json.getString("token");
		User user = (User) session.getAttribute(token);
		if (user != null) {
			String deviceId = json.getString("deviceId");// 设备ID
			String mtoken = json.getString("mtoken");// 用户凭证
			String host = json.getString("host");// 服务器地址
			String url = host + "/deviceInfo/getEnergyDeviceInfo";
			String houseId = json.getString("houseId");
			HashMap<String, Object> hMap = new HashMap<String, Object>();
			hMap.put("mtoken", mtoken);
			hMap.put("deviceId", deviceId);
			JSONObject reJson = HttpUtil.doPost(url, hMap);
			logger.debug("getEnergyDeviceInfo-----------" + reJson.toString());
			if (reJson != null) {
				if (reJson.getInt("status") == 200) {
					map.put("code", 200);
					map.put("result", reJson.getJSONObject("result"));
					map.put("deviceId", deviceId);
					map.put("houseId", houseId);
					return JsonUtil.objectToJson(map);
				}
			}
		}
		map.put("code", 201);
		map.put("msg", "查询失败");
		return JsonUtil.objectToJson(map);
	}

	/**
	 * 查询当日能耗设备示数
	 * 
	 * @author dengfei E-mail:dengfei200857@163.com
	 * @time 2018年4月3日 下午3:53:32
	 * @param data
	 * @return
	 */
	@RequestMapping(value = "/danbaygetEnergyDailyReading", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getEnergyDailyReading(@RequestBody String data) {
		JSONObject json = JSONObject.fromObject(data);
		String token = json.getString("token");
		User user = (User) session.getAttribute(token);
		if (user != null) {
			String deviceId = json.getString("deviceId");// 设备ID
			String readTime = json.getString("readTime");// 查看日期（格式：yyyy-MM-dd）
			String mtoken = json.getString("mtoken");// 用户凭证
			String host = json.getString("host");// 服务器地址
			String url = host + "/deviceInfo/getEnergyDailyReading";
			HashMap<String, Object> hMap = new HashMap<String, Object>();
			hMap.put("mtoken", mtoken);
			hMap.put("deviceId", deviceId);
			hMap.put("readTime", readTime);
			JSONObject reJson = HttpUtil.doPost(url, hMap);
			logger.debug("getEnergyDailyReading-----------" + reJson.toString());
			if (reJson != null) {
				if (reJson.getInt("status") == 200) {
					map.put("code", 200);
					map.put("result", reJson.getJSONObject("result"));
					return JsonUtil.objectToJson(map);
				}
			}
		}
		map.put("code", 201);
		map.put("msg", "更新失败");
		return JsonUtil.objectToJson(map);
	}

	/**
	 * 查询能耗设备区间用量
	 * 
	 * @author dengfei E-mail:dengfei200857@163.com
	 * @time 2018年4月3日 下午3:56:35
	 * @param data
	 * @return
	 */
	@RequestMapping(value = "/danbaygetEnergySectionConsumption", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getEnergySectionConsumption(@RequestBody String data) {
		JSONObject json = JSONObject.fromObject(data);
		String token = json.getString("token");
		User user = (User) session.getAttribute(token);
		if (user != null) {
			String deviceId = json.getString("deviceId");// 设备ID
			String startTime = json.getString("startTime");// 时间：年月日时 ，格式举例
															// 2017031211
			String endTime = json.getString("endTime");// 时间：年月日时 ，格式举例
														// 2017031212
			String mtoken = json.getString("mtoken");// 用户凭证
			String host = json.getString("host");// 服务器地址
			String url = host + "/deviceInfo/getEnergySectionConsumption";
			HashMap<String, Object> hMap = new HashMap<String, Object>();
			hMap.put("mtoken", mtoken);
			hMap.put("deviceId", deviceId);
			hMap.put("startTime", startTime);
			hMap.put("endTime", endTime);
			JSONObject reJson = HttpUtil.doPost(url, hMap);
			logger.debug("getEnergySectionConsumption-----------" + reJson.toString());
			if (reJson != null) {
				if (reJson.getInt("status") == 200) {
					map.put("code", 200);
					map.put("result", reJson.getJSONObject("result"));
					return JsonUtil.objectToJson(map);
				}
			}
		}
		map.put("code", 201);
		map.put("msg", "更新失败");
		return JsonUtil.objectToJson(map);
	}

	/**
	 * 对电表进行断电/恢复操作
	 * 
	 * @author dengfei E-mail:dengfei200857@163.com
	 * @time 2018年4月3日 下午4:00:04
	 * @param data
	 * @return
	 */
	@RequestMapping(value = "/danbayenergyGateControl", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String energyGateControl(@RequestBody String data) {
		JSONObject json = JSONObject.fromObject(data);
		String token = json.getString("token");
		User user = (User) session.getAttribute(token);
		if (user != null) {
			String deviceId = json.getString("deviceId");// 设备ID
			int control_type = json.getInt("control_type");// 0拉闸，1合闸，2手动合闸
			String mtoken = json.getString("mtoken");// 用户凭证
			String host = json.getString("host");// 服务器地址
			String url = host + "/deviceInfo/energy/gateControl";
			HashMap<String, Object> hMap = new HashMap<String, Object>();
			hMap.put("mtoken", mtoken);
			hMap.put("deviceId", deviceId);
			hMap.put("control_type", control_type);
			JSONObject reJson = HttpUtil.doPost(url, hMap);
			logger.debug("energyGateControl-----------" + reJson.toString());
			if (reJson != null) {
				if (reJson.getInt("status") == 200) {
					map.put("code", 200);
					map.put("result", reJson.getJSONObject("result"));
					return JsonUtil.objectToJson(map);
				}
			}
		}
		map.put("code", 201);
		map.put("msg", "更新失败");
		return JsonUtil.objectToJson(map);
	}

	@RequestMapping(value = "/danbaygetEnergyGateStatus", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getEnergyGateStatus(@RequestBody String data) {
		JSONObject json = JSONObject.fromObject(data);
		String token = json.getString("token");
		User user = (User) session.getAttribute(token);
		if (user != null) {
			String deviceId = json.getString("deviceId");// 设备ID
			String mtoken = json.getString("mtoken");// 用户凭证
			String host = json.getString("host");// 服务器地址
			String url = host + "/deviceInfo/energy/getGateStatus";
			HashMap<String, Object> hMap = new HashMap<String, Object>();
			hMap.put("mtoken", mtoken);
			hMap.put("deviceId", deviceId);
			JSONObject reJson = HttpUtil.doPost(url, hMap);
			logger.debug("getEnergyGateStatus-----------" + reJson.toString());
			if (reJson != null) {
				if (reJson.getInt("status") == 200) {
					map.put("code", 200);
					map.put("result", reJson.getJSONObject("result"));
					return JsonUtil.objectToJson(map);
				}
			}
		}
		map.put("code", 201);
		map.put("msg", "更新失败");
		return JsonUtil.objectToJson(map);
	}

	/**
	 * 向蛋贝系统同步业主房屋信息
	 * 
	 * @author dengfei E-mail:dengfei200857@163.com
	 * @time 2018年4月3日 下午2:43:21
	 * @param data
	 * @return
	 */
	@RequestMapping(value = "/danbaySyncOwnerHouseInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String SyncOwnerHouseInfo(@RequestBody String data) {
		JSONObject json = JSONObject.fromObject(data);
		String token = json.getString("token");
		User user = (User) session.getAttribute(token);
		if (user != null) {
			int type = json.getInt("type");// 房源类型：0集中式，1分散式
			String mtoken = json.getString("mtoken");// 用户凭证
			String host = json.getString("host");// 服务器地址
			JSONArray apartmentList = json.getJSONArray("apartmentList");// 房源信息JSONArray
			String url = host + "/house/sync";
			HashMap<String, Object> hMap = new HashMap<String, Object>();
			hMap.put("mtoken", mtoken);
			hMap.put("apartmentList", apartmentList);
			hMap.put("apartmentType", type);
			JSONObject reJson = HttpUtil.doPost(url, hMap);
			logger.debug("SyncOwnerHouseInfo-----------" + reJson.toString());
			if (reJson != null) {
				if (reJson.getInt("status") == 200) {
					map.put("code", 200);
					map.put("result", reJson.getJSONArray("result"));
					return JsonUtil.objectToJson(map);
				}
			}
		}
		map.put("code", 201);
		map.put("msg", "同步失败");
		return JsonUtil.objectToJson(map);
	}

	/**
	 * 获取所有设备列表（根据小区物业编码）
	 * 
	 * @author dengfei E-mail:dengfei200857@163.com
	 * @time 2018年4月3日 下午4:06:53
	 * @param data
	 * @return
	 */
	@RequestMapping(value = "/danbaygetDeviceList", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getDeviceList(@RequestBody String data) {
		JSONObject json = JSONObject.fromObject(data);
		String token = json.getString("token");
		User user = (User) session.getAttribute(token);
		if (user != null) {
			String buildingId = json.getString("buildingId");// 小区物业编码（ID），合作方提供数据（全局唯一）
			String mtoken = json.getString("mtoken");// 用户凭证
			String host = json.getString("host");// 服务器地址
			String url = host + "/deviceInfo/getDeviceList";
			HashMap<String, Object> hMap = new HashMap<String, Object>();
			hMap.put("mtoken", mtoken);
			hMap.put("buildingId", buildingId);
			JSONObject reJson = HttpUtil.doPost(url, hMap);
			logger.debug("getDeviceList-----------" + reJson.toString());
			if (reJson != null) {
				if (reJson.getInt("status") == 200) {
					map.put("host", host);
					map.put("mtoken", mtoken);
					map.put("code", 200);
					map.put("result", reJson.getJSONArray("result"));
					return JsonUtil.objectToJson(map);
				}
			}
		}
		map.put("code", 201);
		map.put("msg", "查询失败");
		return JsonUtil.objectToJson(map);
	}

	/**
	 * 开门记录请求
	 * 
	 * @author dengfei E-mail:dengfei200857@163.com
	 * @time 2018年4月3日 下午4:10:15
	 * @param data
	 * @return
	 */
	@RequestMapping(value = "/danbaygetOpendoorRecord", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getOpendoorRecord(@RequestBody String data) {
		JSONObject json = JSONObject.fromObject(data);
		String token = json.getString("token");
		User user = (User) session.getAttribute(token);
		if (user != null) {
			String deviceId = json.getString("deviceId");// 设备ID
			int pwdID = json.getInt("pwdID");// 密码ID
			String time = json.getString("time");// 年月日时分秒：格式举例 160318112300
			int type = json.getInt("type");// 密码类型：0临时密码开门，1管理员密码开门，2管家密码开门，3客房密码开门
			String host = json.getString("host");// 服务器地址
			String url = host + "/opendoor";
			HashMap<String, Object> hMap = new HashMap<String, Object>();
			hMap.put("deviceId", deviceId);
			hMap.put("pwdID", pwdID);
			hMap.put("time", time);
			hMap.put("type", type);
			JSONObject reJson = HttpUtil.doPost(url, hMap);
			logger.debug("getOpendoorRecord-----------" + reJson.toString());
			if (reJson != null) {
				if (reJson.getInt("status") == 200) {
					map.put("code", 200);
					map.put("result", reJson.getJSONObject("result"));
					return JsonUtil.objectToJson(map);
				}
			}
		}
		map.put("code", 201);
		map.put("msg", "请求失败");
		return JsonUtil.objectToJson(map);
	}

	/**
	 * 门锁——报警信息请求
	 * 
	 * @author dengfei E-mail:dengfei200857@163.com
	 * @time 2018年4月3日 下午4:24:12
	 * @param data
	 * @return
	 */
	@RequestMapping(value = "/danbaydoorWarning", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doorWarning(@RequestBody String data) {
		JSONObject json = JSONObject.fromObject(data);
		String token = json.getString("token");
		User user = (User) session.getAttribute(token);
		if (user != null) {
			String deviceId = json.getString("deviceId");// 设备ID
			String time = json.getString("time");// 年月日时分秒：格式举例 160318112300
			int code = json.getInt("code");// 报警类型：1密码错误报警，2低电报价，3防撬报警，4斜舌报警
			String host = json.getString("host");// 服务器地址
			String url = host + "/warning";
			HashMap<String, Object> hMap = new HashMap<String, Object>();
			hMap.put("deviceId", deviceId);
			hMap.put("code", code);
			hMap.put("time", time);
			JSONObject reJson = HttpUtil.doPost(url, hMap);
			logger.debug("doorWarning-----------" + reJson.toString());
			if (reJson != null) {
				if (reJson.getInt("status") == 200) {
					map.put("code", 200);
					map.put("result", reJson.getJSONObject("result"));
					return JsonUtil.objectToJson(map);
				}
			}
		}
		map.put("code", 201);
		map.put("msg", "请求失败");
		return JsonUtil.objectToJson(map);
	}

	/**
	 * 门锁——权限动态上报请求
	 * 
	 * @author dengfei E-mail:dengfei200857@163.com
	 * @time 2018年4月3日 下午4:31:04
	 * @param data
	 * @return
	 */
	@RequestMapping(value = "/danbayaccessLock", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String accessLock(@RequestBody String data) {
		JSONObject json = JSONObject.fromObject(data);
		String token = json.getString("token");
		User user = (User) session.getAttribute(token);
		if (user != null) {
			String deviceId = json.getString("deviceId");// 设备ID
			int action = json.getInt("action");// 操作类型：1新增，2修改，3删除，4冻结，5解除冻结（密码恢复使用），0恢复默认值（只有管理员密码对应该值，代表门锁恢复出厂设置）
			int op_code = json.getInt("op_code");// 操作结果：0操作成功，1已存在重复密码2，该类型密码存储空间已满，3改密码不存在，4其他错误
			int pwdID = json.getInt("pwdID");// 密码ID
			String time = json.getString("time");// 年月日时分秒：格式举例 160318112300
			int type = json.getInt("type");// 密码类型：0临时密码开门，1管理员密码开门，2管家密码开门，3客房密码开门
			String host = json.getString("host");// 服务器地址
			String url = host + "/access";
			HashMap<String, Object> hMap = new HashMap<String, Object>();
			hMap.put("deviceId", deviceId);
			hMap.put("action", action);
			hMap.put("op_code", op_code);
			hMap.put("pwdID", pwdID);
			hMap.put("time", time);
			hMap.put("type", type);
			JSONObject reJson = HttpUtil.doPost(url, hMap);
			logger.debug("accessLock-----------" + reJson.toString());
			if (reJson != null) {
				if (reJson.getInt("status") == 200) {
					map.put("code", 200);
					map.put("result", reJson.getJSONObject("result"));
					return JsonUtil.objectToJson(map);
				}
			}
		}
		map.put("code", 201);
		map.put("msg", "请求失败");
		return JsonUtil.objectToJson(map);
	}

	/**
	 * 拼装房源信息
	 * 
	 * @author dengfei E-mail:dengfei200857@163.com
	 * @time 2018年3月23日 下午3:00:54
	 * @param buildingId
	 *            小区物业编码（ID），合作方提供数据（全局唯一）
	 * @param buildingName
	 *            小区名称
	 * @param buildingNo
	 *            小区楼栋
	 * @param unit
	 *            小区单元
	 * @param roomNo
	 *            房间名
	 * @param roomId
	 *            房源编码（ID），合作方提供数据
	 * @param province
	 *            省份（例如广东省）
	 * @param city
	 *            城市（例如：深圳市）
	 * @param area
	 *            区域（例如：南山区）
	 * @param floor
	 *            楼层
	 * @return JSONArray
	 */
	public static synchronized JSONArray buildingList(String buildingId, String buildingName, String buildingNo, String unit, String roomNo, String roomId, String province, String city, String area,
			String floor) {
		JSONArray apartmentList = new JSONArray();
		JSONObject json = new JSONObject();
		json.put("buildingId", buildingId);
		json.put("buildingName", buildingName);
		json.put("buildingNo", buildingNo);
		json.put("unit", unit);
		json.put("roomNo", roomNo);
		json.put("roomId", roomId);
		json.put("province", province);
		json.put("city", city);
		json.put("area", area);
		json.put("floor", floor);
		apartmentList.add(json);
		return apartmentList;
	}

}