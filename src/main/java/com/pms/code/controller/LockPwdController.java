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
import com.pms.code.entity.base.LockPwd;
import com.pms.code.entity.base.UnlockingKey;
import com.pms.code.service.EquipmentSysProviderService;
import com.pms.code.service.LockPwdService;
import com.pms.code.service.UnlockingKeyService;
import com.pms.code.util.JsonUtil;

import net.sf.json.JSONObject;

@Controller
public class LockPwdController extends BaseController {
	private Logger logger = LoggerFactory.getLogger(LockPwdController.class);

	@Autowired
	private LockPwdService lockPwdServiceImpl;

	@Autowired
	private UnlockingKeyService unlockingKeyServiceImpl;

	@Autowired
	private EquipmentSysProviderService equipmentSysProviderServiceImpl;

	/**
	 * 查询门锁密码列表(根据设备ID)
	 * 
	 * @param data
	 * @return
	 */
	@RequestMapping(value = "/getLockPwdList", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String queryLockPwdList(@RequestBody String data) {
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
			rqjson.put("deviceId", deviceId);
			rqjson.put("mtoken", jsondata.getString("mtoken"));
			rqjson.put("token", resjson.getString("token"));
			rqjson.put("host", resjson.getString("host"));
			rqjson.put("userName", resjson.getString("userName"));
			result = danbayapi.getLockPwdList(JsonUtil.objectToJson(rqjson));
			if (JSONObject.fromObject(result).getInt("code") == 200) {
				map.put("code", "200");
				map.put("data", result);
				logger.error("------查询所有设备------" + map);
				return JsonUtil.objectToJson(map);
			}
		}
		map.put("code", "201");
		map.put("msg", "查询失败");
		logger.error("------查询所有设备------" + map);
		return JsonUtil.objectToJson(map);
	}

	/**
	 * 门锁新增密码
	 * 
	 * @param data
	 * @return
	 */
	@RequestMapping(value = "/addLockPwd", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String insertLockPwd(@RequestBody String data) {
		String result = null;
		LockPwd lockPwd = null;
		JSONObject json = JSONObject.fromObject(data);
		UnlockingKey unlockingKey = unlockingKeyServiceImpl.findUnlockingKeyByID(json.getInt("id"));
		String deviceId = unlockingKey.getDoorNum();
		String telphone = json.getString("telphone");
		String password = json.getString("password");
		String pwdType = json.getString("pwdType");
		DanbayAPI danbayapi = new DanbayAPI();
		String loginJson = danbayapi.authorizationLogin(data, equipmentSysProviderServiceImpl);
		JSONObject resjson = JSONObject.fromObject(loginJson);
		if (resjson.getInt("code") == 200) {
			JSONObject jsondata = resjson.getJSONObject("result");
			JSONObject rqjson = new JSONObject();
			rqjson.put("mtoken", jsondata.getString("mtoken"));
			rqjson.put("deviceId", deviceId);
			rqjson.put("password", password);
			rqjson.put("token", resjson.getString("token"));
			rqjson.put("pwdType", pwdType);
			rqjson.put("userName", resjson.getString("userName"));
			rqjson.put("host", resjson.getString("host"));
			result = danbayapi.addLockPwd(JsonUtil.objectToJson(rqjson));
			System.out.println(result);
			if (JSONObject.fromObject(result).getInt("code") == 200) {
				lockPwd = new LockPwd();
				lockPwd.setDoorid(json.getInt("id"));
				lockPwd.setPwd(password);
				JSONObject rejson = JSONObject.fromObject(result);
				rejson = rejson.getJSONObject("result");
				lockPwd.setPwdID(rejson.getString("pwdID"));
				lockPwd.setTelphone(telphone);
				// 新增密码
				boolean flag = lockPwdServiceImpl.insertLockPwd(lockPwd);
				if (flag) {
					map.put("code", "200");
					map.put("data", result);
					logger.error("------门锁新增密码------" + map);
					return JsonUtil.objectToJson(map);
				}
			}
		}
		map.put("code", "201");
		map.put("msg", "门锁新增密码失败");
		logger.error("------门锁新增密码------" + map);
		return JsonUtil.objectToJson(map);
	}

	/**
	 * 门锁新增密码(指定时效)
	 * 
	 * @param data
	 * @return
	 */
	@RequestMapping(value = "/addLockPwdWithDate", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String addPwdWithDate(@RequestBody String data) {
		JSONObject json = JSONObject.fromObject(data);
		int id = json.getInt("id");
		String beginTime = json.getString("beginTime");
		String endTime = json.getString("endTime");
		String passWord = json.getString("passWord");
		String pwdType = json.getString("pwdType");
		String telphone = json.getString("telphone");
		UnlockingKey unlockingKey = unlockingKeyServiceImpl.findUnlockingKeyByID(id);
		String deviceId = unlockingKey.getDoorNum();
		DanbayAPI danbayapi = new DanbayAPI();
		String loginJson = danbayapi.authorizationLogin(data, equipmentSysProviderServiceImpl);
		JSONObject resjson = JSONObject.fromObject(loginJson);
		if (resjson.getInt("code") == 200) {
			JSONObject jsondata = resjson.getJSONObject("result");
			JSONObject rqjson = new JSONObject();
			rqjson.put("mtoken", jsondata.getString("mtoken"));
			rqjson.put("pwdType", pwdType);
			rqjson.put("deviceId", deviceId);
			rqjson.put("beginTime", beginTime);
			rqjson.put("endTime", endTime);
			rqjson.put("password", passWord);
			rqjson.put("token", resjson.getString("token"));
			rqjson.put("host", resjson.getString("host"));
			rqjson.put("userName", resjson.getString("userName"));
			String result = danbayapi.addLockPwdWithDate(JsonUtil.objectToJson(rqjson));
			if (JSONObject.fromObject(result).getInt("code") == 200) {
				LockPwd lockPwd = new LockPwd();
				lockPwd.setDoorid(id);
				lockPwd.setPwd(passWord);
				lockPwd.setPwdID(JSONObject.fromObject(result).getString("pwdID"));
				lockPwd.setTelphone(telphone);
				// 新增密码
				boolean flag = lockPwdServiceImpl.insertLockPwd(lockPwd);
				if (flag) {
					map.put("code", "200");
					map.put("msg", "门锁新增密码成功");
					logger.error("------门锁新增密码------" + map);
					return JsonUtil.objectToJson(map);
				}
			}
		}
		map.put("code", "201");
		map.put("msg", "门锁新增密码失败");
		logger.error("------门锁新增密码------" + map);
		return JsonUtil.objectToJson(map);
	}

	/**
	 * 门锁修改密码
	 * 
	 * @param data
	 * @return
	 */
	@RequestMapping(value = "/editLockPwd", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String updateLockPwd(@RequestBody String data) {
		String result = null;
		JSONObject json = JSONObject.fromObject(data);
		int id = json.getInt("id");
		String password = json.getString("password");
		String pwdType = json.getString("pwdType");
		UnlockingKey unlockingKey = unlockingKeyServiceImpl.findUnlockingKeyByID(id);
		String deviceId = unlockingKey.getDoorNum();
		DanbayAPI danbayapi = new DanbayAPI();
		String loginJson = danbayapi.authorizationLogin(data, equipmentSysProviderServiceImpl);
		JSONObject resjson = JSONObject.fromObject(loginJson);
		if (resjson.getInt("code") == 200) {
			JSONObject jsondata = resjson.getJSONObject("result");
			JSONObject rqjson = new JSONObject();
			rqjson.put("mtoken", jsondata.getString("mtoken"));
			rqjson.put("deviceId", deviceId);
			rqjson.put("password", password);
			rqjson.put("pwdType", pwdType);
			rqjson.put("token", resjson.getString("token"));
			rqjson.put("host", resjson.getString("host"));
			rqjson.put("userName", resjson.getString("userName"));
			LockPwd lockPwd = lockPwdServiceImpl.queryLockPwdById(id);
			if (lockPwd != null) {
				rqjson.put("pwdID", lockPwd.getPwdID());
				result = danbayapi.editLockPwd(JsonUtil.objectToJson(rqjson));
				if (JSONObject.fromObject(result).getInt("code") == 200) {
					lockPwd.setPwd(password);
					boolean flag = lockPwdServiceImpl.updateLockPwd(lockPwd);
					if (flag) {
						map.put("code", "200");
						map.put("msg", "门锁密码修改成功");
						logger.error("------门锁修改密码------" + map);
						return JsonUtil.objectToJson(map);
					}
				}
			}
		}
		map.put("code", "201");
		map.put("msg", "门锁密码修改失败");
		logger.error("------门锁修改密码------" + map);
		return JsonUtil.objectToJson(map);
	}

	/**
	 * 门锁修改密码(指定时效)
	 * 
	 * @param data
	 * @return
	 */
	@RequestMapping(value = "editLockPwdWithDate", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String editPwd(@RequestBody String data) {
		String result = null;
		JSONObject json = JSONObject.fromObject(data);
		int id = json.getInt("id");
		String beginTime = json.getString("beginTime");
		String endTime = json.getString("endTime");
		String password = json.getString("password");
		String pwdType = json.getString("pwdType");
		UnlockingKey unlockingKey = unlockingKeyServiceImpl.findUnlockingKeyByID(id);
		String deviceId = unlockingKey.getDoorNum();
		DanbayAPI danbayapi = new DanbayAPI();
		String loginJson = danbayapi.authorizationLogin(data, equipmentSysProviderServiceImpl);
		JSONObject resjson = JSONObject.fromObject(loginJson);
		if (resjson.getInt("code") == 200) {
			JSONObject jsondata = resjson.getJSONObject("result");
			JSONObject rqjson = new JSONObject();
			rqjson.put("mtoken", jsondata.getString("mtoken"));
			rqjson.put("deviceId", deviceId);
			rqjson.put("password", password);
			rqjson.put("beginTime", beginTime);
			rqjson.put("endTime", endTime);
			rqjson.put("pwdType", pwdType);
			rqjson.put("token", resjson.getString("token"));
			rqjson.put("host", resjson.getString("host"));
			rqjson.put("userName", resjson.getString("userName"));
			LockPwd lockPwd = lockPwdServiceImpl.queryLockPwdById(id);
			if (lockPwd != null) {
				rqjson.put("pwdID", lockPwd.getPwdID());
				result = danbayapi.editLockPwdWithDate(JsonUtil.objectToJson(rqjson));
				if (JSONObject.fromObject(result).getInt("code") == 200) {
					lockPwd.setPwd(password);
					boolean flag = lockPwdServiceImpl.updateLockPwd(lockPwd);
					if (flag) {
						map.put("code", "200");
						map.put("msg", "门锁密码修改成功");
						logger.error("------门锁修改密码------" + map);
						return JsonUtil.objectToJson(map);
					}
				}
			}
		}
		map.put("code", "201");
		map.put("msg", "门锁密码修改失败");
		logger.error("------门锁修改密码------" + map);
		return JsonUtil.objectToJson(map);
	}

	/**
	 * 门锁删除密码
	 * 
	 * @param data
	 * @return
	 */
	@RequestMapping(value = "/delLockPwd", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String deleteLockPwd(@RequestBody String data) {
		String result = null;
		JSONObject json = JSONObject.fromObject(data);
		int doorid = json.getInt("doorid");
		UnlockingKey unlockingKey = unlockingKeyServiceImpl.findUnlockingKeyByID(doorid);
		String deviceId = unlockingKey.getDoorNum();
		DanbayAPI danbayapi = new DanbayAPI();
		String loginJson = danbayapi.authorizationLogin(data, equipmentSysProviderServiceImpl);
		JSONObject resjson = JSONObject.fromObject(loginJson);
		if (resjson.getInt("code") == 200) {
			JSONObject jsondata = resjson.getJSONObject("result");
			JSONObject rqjson = new JSONObject();
			LockPwd queryLockPwd = lockPwdServiceImpl.queryLockPwdById(doorid);
			if (queryLockPwd != null) {
				rqjson.put("mtoken", jsondata.getString("mtoken"));
				rqjson.put("deviceId", deviceId);
				rqjson.put("pwdID", queryLockPwd.getPwdID());
				rqjson.put("pwdType", queryLockPwd.getType());
				rqjson.put("token", resjson.getString("token"));
				rqjson.put("host", resjson.getString("host"));
				rqjson.put("userName", resjson.getString("userName"));
				result = danbayapi.delLockPwd(JsonUtil.objectToJson(rqjson));
				if (JSONObject.fromObject(result).getInt("code") == 200) {
					boolean flag = lockPwdServiceImpl.deleteLockPwd(doorid);
					if (flag) {
						map.put("code", "200");
						map.put("msg", "门锁删除密码成功");
						logger.error("------门锁删除密码------" + map);
						return JsonUtil.objectToJson(map);
					}
				}
			}
		}
		map.put("code", "201");
		map.put("msg", "门锁删除密码失败");
		logger.error("------门锁删除密码------" + map);
		return JsonUtil.objectToJson(map);
	}
}
