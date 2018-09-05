package com.pms.code.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pms.code.entity.base.EnergyConsumptionDevice;
import com.pms.code.entity.base.HouseID;
import com.pms.code.entity.base.HouseType;
import com.pms.code.entity.base.OwnerAccount;
import com.pms.code.entity.base.OwnerHouseRelation;
import com.pms.code.entity.base.OwnerInfo;
import com.pms.code.entity.base.OwnerPmd;
import com.pms.code.entity.base.PropertyManagementDepartment;
import com.pms.code.entity.base.UnlockingKey;
import com.pms.code.entity.base.User;
import com.pms.code.service.CommunityOwnerInfoService;
import com.pms.code.service.EnergyConsumptionDeviceService;
import com.pms.code.service.HouseIDService;
import com.pms.code.service.HouseTypeService;
import com.pms.code.service.OwnerAccountService;
import com.pms.code.service.OwnerHouseRelationService;
import com.pms.code.service.OwnerInfoService;
import com.pms.code.service.OwnerPmdService;
import com.pms.code.service.PropertyManagementDepartmentService;
import com.pms.code.service.UnlockingKeyService;
import com.pms.code.util.BuildingUtil;
import com.pms.code.util.JsonUtil;
import com.pms.code.util.Page;

import net.sf.json.JSONObject;

/**
 * 业主信息
 * 
 * @author Dell
 *
 */
@Controller
public class OwnerAccountController extends BaseController {
	private Logger logger = LoggerFactory.getLogger(OwnerAccountController.class);

	@Autowired
	private OwnerInfoService ownerInfoServiceImpl;
	@Autowired
	private OwnerAccountService ownerAccountServiceImpl;
	@Autowired
	private OwnerPmdService ownerPmdServiceImpl;
	@Autowired
	private OwnerHouseRelationService ownerHouseRelationServiceImpl;
	@Autowired
	private HouseIDService houseIDServiceImpl;
	@Autowired
	private PropertyManagementDepartmentService propertyManagementDepartmentServiceImpl;
	@Autowired
	private EnergyConsumptionDeviceService energyConsumptionDeviceServiceImpl;
	@Autowired
	private UnlockingKeyService unlockingKeyServiceImpl;
	@Autowired
	private CommunityOwnerInfoService communityOwnerInfoServiceImpl;
	@Autowired
	private HouseTypeService houseTypeServiceImpl;

	/**
	 * @author dengfei E-mail:dengfei200857@163.com
	 * @time 2018年3月30日 上午10:09:21
	 * @param data
	 * @return
	 */
	@RequestMapping(value = "/queryOwnerAccount", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String queryOwnerAccount(@RequestBody String data) {
		JSONObject json = JSONObject.fromObject(data);
		int pageIndex = json.getInt("pageIndex");
		int pageSize = json.getInt("pageSize");
		String isliving = json.getString("isliving");
		String token = json.getString("token");
		User user = (User) session.getAttribute(token);
		String telphone = json.containsKey("telphone") ? json.getString("telphone") : "";
		String userName = json.containsKey("userName") ? json.getString("userName") : "";
		if (pageIndex <= 0) {
			pageIndex = 1;
		}
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("pageIndex", pageIndex);
		paramMap.put("pageSize", pageSize);
		paramMap.put("isliving", isliving);
		paramMap.put("telphone", telphone);
		paramMap.put("userName", userName);
		paramMap.put("pid", user.getPid());
		if (user.getGm_type() == 3) {
			paramMap.put("pmid", user.getPmid());
			paramMap = communityOwnerInfoServiceImpl.queryCommunityOwnerInfo(paramMap);
		} else if (user.getGm_type() == 2) {
			paramMap.put("pmid", json.getString("pmid"));
			paramMap = communityOwnerInfoServiceImpl.queryCommunityOwnerInfo(paramMap);
		}
		Page<OwnerInfo> page = (Page<OwnerInfo>) paramMap.get("page");
		List<HouseType> houseTypes = houseTypeServiceImpl.houseTypeList();
		if (page == null) {
			map.put("code", "201");
			map.put("msg", "获取不到用户");
		}else{
			map.put("code", "200");
			map.put("data", page);
			if(houseTypes!=null){
				map.put("houseTypes",houseTypes);
			}
		}
		logger.error("------查询用户列表------" + map);
		System.out.println(map);
		return JsonUtil.objectToJson(map);
	}

	/**
	 * 编辑业主信息
	 * 
	 * @author dengfei E-mail:dengfei200857@163.com
	 * @time 2018年3月30日 下午12:13:29
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/editOwnerAccountInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String editOwnerAccount(int id) {
		OwnerInfo ownerAccount = ownerInfoServiceImpl.queryOwnerInfoById(id);
		if (ownerAccount == null) {
			map.put("code", "201");
			map.put("msg", "获取的用户为空");
		} else {
			map.put("data", ownerAccount);
			map.put("msg", "查询到用户");
		}
		logger.error("------编辑用信息------" + map);
		return JsonUtil.objectToJson(map);
	}

	/**
	 * 保存业主编辑信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/saveEditOwnerInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String saveEditUserInfo(@RequestBody String data) {
		JSONObject json = JSONObject.fromObject(data);
		int id = json.getInt("id");
		int pmid=json.getInt("pmid");
		String dong = json.getString("dong");
		String zuo = json.getString("zuo");
		String room = json.getString("room");
		String units = dong + "栋" + zuo + "座";
		String province=json.getString("province");
		String city=json.getString("city");
		String area=json.getString("area");
		String telphone=json.getString("telphone");
		String ownerName=json.getString("ownerName");
		double cash_pledge=json.getDouble("cashPledge");//押金
		double rental = json.getDouble("rental");//住进
		int houseType=json.getInt("houseType");
		String pmdName=json.getString("pmdName");//小区名称
		HashMap<String, Object> hmap = new HashMap<String, Object>();
		hmap.put("id", id);
		hmap.put("units", units);
		hmap.put("rooms", room);
		hmap.put("telphone",telphone);
		hmap.put("ownerName",ownerName);//业主姓名
		hmap.put("cashPledge",cash_pledge);//押金
		hmap.put("rental",rental);//房租
		hmap.put("houseType",houseType);
		
		PropertyManagementDepartment pmd=new PropertyManagementDepartment();
		pmd.setId(pmid);
		pmd.setArea(area);
		pmd.setProvince(province);
		pmd.setCity(city);
		pmd.setManagement_department_name(pmdName);
		boolean flag = propertyManagementDepartmentServiceImpl.saveEditPropertyManagementDepartment(pmd);
		if(flag){
			flag = ownerInfoServiceImpl.updateOwnerInfo(hmap);//修改用户房源信息
			if(flag){
				map.put("code", "200");
				map.put("msg", "修改成功");
			}else{
				map.put("code", "201");
				map.put("msg", "修改失败");
			}
		} else {
			map.put("code", "201");
			map.put("msg", "修改失败");
		}
		logger.error("------保存用户编辑------" + map);
		return JsonUtil.objectToJson(map);
	}

	/**
	 * 添加业主
	 * 
	 * @author dengfei E-mail:dengfei200857@163.com
	 * @time 2018年3月30日 下午12:12:49
	 * @param data
	 * @return
	 */
	@RequestMapping(value = "/addOwnerAccount", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String addOwnerAccount(@RequestBody String data) {
		JSONObject json = JSONObject.fromObject(data);
		String token = json.getString("token");
		String dong = json.getString("dong");
		String zuo = json.getString("zuo");
		String room = json.getString("room");
		String units = dong + "栋" + zuo + "座";
		String province = json.getString("province");
		String city = json.getString("city");
		String area = json.getString("area");
		String telphone = json.getString("telphone");//业主电话
		String ownerName=json.getString("userName");//业主姓名
		String districtName = json.getString("districtName");
		int houseType=json.getInt("houseType");
		double cash_pledge=json.getDouble("cashPledge");//押金
		double rental = json.getDouble("rental");//房租
		User user = (User) session.getAttribute(token);
		Timestamp createtime = new Timestamp(new Date().getTime());
		if (user.getGm_type() == 2) {// 物业公司
			System.out.println(user.toString());
			boolean flag = false;
			boolean flag1 = false;
			boolean flag2 = false;
			PropertyManagementDepartment pmd = new PropertyManagementDepartment();
			pmd.setArea(area);
			pmd.setCity(city);
			/*pmd.setContact_number(telphone);*/
			pmd.setManagement_department_name(districtName);
			pmd.setPid(user.getPid());
			pmd.setProvince(province);
			PropertyManagementDepartment pmd1 = propertyManagementDepartmentServiceImpl
					.queryPropertyManagementDepartment(pmd);// 查询物业管理信息
			if (pmd1 == null) {// 没有当前小区,则插入
				flag = propertyManagementDepartmentServiceImpl.insertPropertyManagementDepartment(pmd);
			} else {
				flag = true;
			}
			if (flag) {
				PropertyManagementDepartment pmd2 = propertyManagementDepartmentServiceImpl
						.queryPropertyManagementDepartment(pmd);
				if (null == pmd2) {
					map.put("code", "201");
					map.put("msg", "信息保存异常");
				} else {
					OwnerInfo ownerInfo = new OwnerInfo();
					ownerInfo.setUnits(units);
					ownerInfo.setRooms(room);
					ownerInfo.setPmid(pmd2.getId());
					ownerInfo.setOwnername(ownerName);
					ownerInfo.setTelphone(telphone);
					ownerInfo.setIsliving(1);
					ownerInfo.setHouseType(houseType);
					ownerInfo.setCashPledge(cash_pledge);
					ownerInfo.setRental(rental);
					ownerInfo.setCreatetime(createtime);
					// 查询该业主房屋信息是否存在
					OwnerInfo ownerInfo1 = ownerInfoServiceImpl.queryOwnerInfoByOwnerInfo(ownerInfo);
					if (ownerInfo1 != null) {
						if (ownerInfo1.getIsliving() == 0) {// 该业主之前退过该房屋,现在重新办理入住
							HashMap<String, Object> paramMap = new HashMap<String, Object>();
							paramMap.put("id", ownerInfo1.getId());
							paramMap.put("isliving", 1);
							flag = ownerInfoServiceImpl.updateOwnerInfoIsliving(paramMap);
						} else {// 该业正在使用该房屋
							map.put("code", "201");
							map.put("msg", "该信息已存在");
						}
					} else {
						// 2、添加业主信息
						flag = ownerInfoServiceImpl.addOwnerInfo(ownerInfo);
					}
					if (flag) {
						// 根据业主全信息查询，验证是否插入完成。
						OwnerInfo ownerInfo2 = ownerInfoServiceImpl.queryOwnerInfoByOwnerInfo(ownerInfo);
						if (null == ownerInfo2) {
							map.put("code", "201");
							map.put("msg", "信息保存异常");
						} else {
							HouseID houseID = new HouseID();
							houseID.setP_m_id(user.getPmid());
							houseID.setInfoid(ownerInfo2.getId());
							houseID.setHouseid(pmd2.getManagement_department_name() + ownerInfo2.getRooms());
							houseID.setCreatetime(new Timestamp(new Date().getTime()));
							houseIDServiceImpl.insertHouseID(houseID);
							// 4、查询、添加业主账号
							HashMap<String, Object> paramMap = new HashMap<String, Object>();
							paramMap.put("telphone", json.getString("telphone"));
							// 先查询该手机号账户是否已经存在
							OwnerAccount ownerAccount = ownerAccountServiceImpl.queryOwnerAccountByTelphone(paramMap);
							if (ownerAccount == null) {// 新增该业主账户
								ownerAccount = new OwnerAccount();
								ownerAccount.setTelphone(json.getString("telphone"));
								ownerAccount.setUserpwd("888888");
								ownerAccount.setBalance(0);
								ownerAccount.setCreatetime(createtime);
								flag = ownerAccountServiceImpl.addOwnerAccount(ownerAccount);
								if (flag) {
									ownerAccount = ownerAccountServiceImpl.queryOwnerAccountByTelphone(paramMap);
								}
							} else {
								flag = true;
							}
							if (flag) {
								// 加入物业管理ID关联
								HashMap<String, Object> opMap = new HashMap<String, Object>();
								opMap.put("ownerid", ownerAccount.getId());
								opMap.put("p_m_id", pmd2.getId());
								OwnerPmd ownerPmd = ownerPmdServiceImpl.queryOwnerPmd(opMap);
								if (ownerPmd == null) {
									ownerPmd = new OwnerPmd();
									ownerPmd.setOwnerid(ownerAccount.getId());
									ownerPmd.setP_m_id(pmd2.getId());
									ownerPmd.setCreatetime(createtime);
									flag1 = ownerPmdServiceImpl.addOwnerPmd(ownerPmd);
								} else {
									flag1 = true;
								}
								// 加入房屋ID关联
								HashMap<String, Object> ohMap = new HashMap<String, Object>();
								opMap.put("ownerid", ownerAccount.getId());
								opMap.put("infoid", ownerInfo2.getId());
								OwnerHouseRelation ownerHouseRelation = ownerHouseRelationServiceImpl
										.queryOwnerHouseRelation(ohMap);
								if (ownerHouseRelation == null) {
									ownerHouseRelation = new OwnerHouseRelation();
									ownerHouseRelation.setOwnerid(ownerAccount.getId());
									ownerHouseRelation.setInfoid(ownerInfo2.getId());
									ownerHouseRelation.setCreatetime(createtime);
									flag2 = ownerHouseRelationServiceImpl.addOwnerHouseRelation(ownerHouseRelation);
								}
							}
							if (flag && flag1 && flag2) {
								map.put("code", "200");
								map.put("msg", "添加成功");
							} else {
								map.put("code", "201");
								map.put("msg", "添加失败");
							}
						}
					}
				}
			}
		} else {
			map.put("code", "201");
			map.put("msg", "无此权限");
		}
		logger.error("------添加业主信息------" + map);
		return JsonUtil.objectToJson(map);
	}
	/*
	 * if (user.getPmid()>0) {//物业小区 System.out.println(user.toString()); int
	 * pmid = user.getPmid(); boolean flag = false; boolean flag1 = false;
	 * boolean flag2 = false; OwnerInfo ownerInfo = new OwnerInfo();
	 * ownerInfo.setUnits(units); ownerInfo.setRooms(room);
	 * ownerInfo.setPmid(pmid);
	 * ownerInfo.setOwnername(json.getString("userName"));
	 * ownerInfo.setTelphone(json.getString("telphone"));
	 * ownerInfo.setIsliving(1); ownerInfo.setCreatetime(createtime); //
	 * 1、查询该业主房屋信息是否存在 OwnerInfo ownerInfo1 =
	 * ownerInfoServiceImpl.queryOwnerInfoByOwnerInfo(ownerInfo); if (ownerInfo1
	 * != null) { if (ownerInfo1.getIsliving() == 0) {// 该业主之前退过该房屋,现在重新办理入住
	 * HashMap<String, Object> paramMap = new HashMap<String, Object>();
	 * paramMap.put("id", ownerInfo1.getId()); paramMap.put("isliving", 1); flag
	 * = ownerInfoServiceImpl.updateOwnerInfoIsliving(paramMap); } else {//
	 * 该业正在使用该房屋 map.put("code", "201"); map.put("msg", "该信息已存在"); } } else { //
	 * 2、添加业主信息 flag = ownerInfoServiceImpl.addOwnerInfo(ownerInfo); } if(flag){
	 * //添加物业小区信息 PropertyManagementDepartment pmd=new
	 * PropertyManagementDepartment(); pmd.setArea(area); pmd.setCity(city);
	 * pmd.setContact_number(telphone);
	 * pmd.setManagement_department_name(districtName);
	 * pmd.setPid(user.getPid()); pmd.setCreatetime(new Timestamp(new
	 * Date().getTime())); pmd.setProvince(province);
	 * flag=propertyManagementDepartmentServiceImpl.
	 * insertPropertyManagementDepartment(pmd); } if (flag) { //
	 * 3、根据业主全信息查询，验证是否插入完成。 OwnerInfo ownerInfo2 =
	 * ownerInfoServiceImpl.queryOwnerInfoByOwnerInfo(ownerInfo); if (null ==
	 * ownerInfo2) { map.put("code", "201"); map.put("msg", "信息保存异常"); } else {
	 * // 插入houseid表 PropertyManagementDepartment propertyManagementDepartment =
	 * propertyManagementDepartmentServiceImpl
	 * .queryPropertyManagementDepartmentById(user.getPmid()); HouseID houseID =
	 * new HouseID(); houseID.setP_m_id(user.getPmid());
	 * houseID.setInfoid(ownerInfo2.getId()); houseID.setHouseid(
	 * propertyManagementDepartment.getManagement_department_name() +
	 * ownerInfo2.getRooms()); houseID.setCreatetime(new Timestamp(new
	 * Date().getTime())); houseIDServiceImpl.insertHouseID(houseID); //
	 * 4、查询、添加业主账号 HashMap<String, Object> paramMap = new HashMap<String,
	 * Object>(); paramMap.put("telphone", json.getString("telphone")); //
	 * 先查询该手机号账户是否已经存在 OwnerAccount ownerAccount =
	 * ownerAccountServiceImpl.queryOwnerAccountByTelphone(paramMap); if
	 * (ownerAccount == null) {// 新增该业主账户 ownerAccount = new OwnerAccount();
	 * ownerAccount.setTelphone(json.getString("telphone"));
	 * ownerAccount.setUserpwd("888888"); ownerAccount.setBalance(0);
	 * ownerAccount.setCreatetime(createtime); flag =
	 * ownerAccountServiceImpl.addOwnerAccount(ownerAccount); if (flag) {
	 * ownerAccount =
	 * ownerAccountServiceImpl.queryOwnerAccountByTelphone(paramMap); } } else {
	 * flag = true; } if (flag) { // 加入物业管理ID关联 HashMap<String, Object> opMap =
	 * new HashMap<String, Object>(); opMap.put("ownerid",
	 * ownerAccount.getId()); opMap.put("p_m_id", pmid); OwnerPmd ownerPmd =
	 * ownerPmdServiceImpl.queryOwnerPmd(opMap); if (ownerPmd == null) {
	 * ownerPmd = new OwnerPmd(); ownerPmd.setOwnerid(ownerAccount.getId());
	 * ownerPmd.setP_m_id(pmid); ownerPmd.setCreatetime(createtime); flag1 =
	 * ownerPmdServiceImpl.addOwnerPmd(ownerPmd); }else { flag1=true; } //
	 * 加入房屋ID关联 HashMap<String, Object> ohMap = new HashMap<String, Object>();
	 * opMap.put("ownerid", ownerAccount.getId()); opMap.put("infoid",
	 * ownerInfo2.getId()); OwnerHouseRelation ownerHouseRelation =
	 * ownerHouseRelationServiceImpl .queryOwnerHouseRelation(ohMap); if
	 * (ownerHouseRelation == null) { ownerHouseRelation = new
	 * OwnerHouseRelation();
	 * ownerHouseRelation.setOwnerid(ownerAccount.getId());
	 * ownerHouseRelation.setInfoid(ownerInfo2.getId());
	 * ownerHouseRelation.setCreatetime(createtime); flag2 =
	 * ownerHouseRelationServiceImpl.addOwnerHouseRelation(ownerHouseRelation);
	 * } }
	 * 
	 * if (flag && flag1 && flag2) { map.put("code", "200"); map.put("msg",
	 * "添加成功"); } else { map.put("code", "201"); map.put("msg", "添加失败"); } } } }
	 * else { map.put("code", "201"); map.put("msg", "无此权限"); }
	 * logger.error("------保存用户编辑------" + map); return
	 * JsonUtil.objectToJson(map); }
	 */

	/**
	 * 批量给业主退房处理
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/deleteOwnerAccountInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String deleteOwnerAccount(HttpServletRequest request) {
		String[] str = request.getParameterValues("id");
		Timestamp leave_time = new Timestamp(new Date().getTime());
		int[] ids = new int[str.length];
		for (int i = 0; i < str.length; i++) {
			ids[i] = Integer.parseInt(str[i]);
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("id", ids[i]);
			paramMap.put("isliving", 0);
			paramMap.put("leave_time", leave_time);
			// 更新isliving字段为0
			boolean flag = ownerInfoServiceImpl.updateOwnerInfoIsliving(paramMap);
			if (flag) {// 更新成功，开始删除关联
				OwnerInfo ownerInfo = ownerInfoServiceImpl.queryOwnerInfoById(ids[i]);
				String telphone = ownerInfo.getTelphone();
				HashMap<String, Object> accountMap = new HashMap<String, Object>();
				accountMap.put("telphone", telphone);
				OwnerAccount ownerAccount = ownerAccountServiceImpl.queryOwnerAccountByTelphone(accountMap);
				HashMap<String, Object> opMap = new HashMap<String, Object>();
				opMap.put("ownerid", ownerAccount.getId());
				opMap.put("p_m_id", ownerInfo.getPmid());
				ownerPmdServiceImpl.deleteOwnerPmd(opMap);
				HashMap<String, Object> ohMap = new HashMap<String, Object>();
				opMap.put("ownerid", ownerAccount.getId());
				opMap.put("infoid", ownerInfo.getId());
				ownerHouseRelationServiceImpl.deleteOwnerHouseRelation(ohMap);
			}
		}
		map.put("code", "200");
		map.put("msg", "删除成功");
		logger.error("------批量给业主退房处理------" + map);
		return JsonUtil.objectToJson(map);
	}

	/**
	 * 查询设备信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryDeviceInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String queryDeviceInfo(int id) {
		OwnerInfo ownerInfo = ownerInfoServiceImpl.queryOwnerInfoById(id);
		if (ownerInfo != null) {
			PropertyManagementDepartment pmd = propertyManagementDepartmentServiceImpl
					.queryPropertyManagementDepartmentById(ownerInfo.getPmid());
			if (pmd != null) {
				HouseID houseID = houseIDServiceImpl.queryHouseIDById(ownerInfo.getId());
				String houseId = BuildingUtil.getHouseID(
						houseID.getPropertyManagementDepartment().getManagement_department_name(), ownerInfo.getUnits(),
						ownerInfo.getRooms());
				System.out.println("----------=================----------------------" + houseId);
				if (!"".equals(houseId) && houseId != null) {
					HashMap<String, Object> pMap = new HashMap<String, Object>();
					pMap.put("houseId", houseId);
					// 查询能耗信息
					List<EnergyConsumptionDevice> energyConsumptionDevices = energyConsumptionDeviceServiceImpl
							.queryEnergyConsumptionDeviceByHouseId(pMap);
					// 查询门锁信息
					List<UnlockingKey> unlockingKeyByHouses = unlockingKeyServiceImpl.queryUnlockingKeyByHouseId(pMap);
					if (energyConsumptionDevices != null || unlockingKeyByHouses != null) {
						pMap.put("energyConsumptionDevices", energyConsumptionDevices);
						pMap.put("unlockingKeyByHouses", unlockingKeyByHouses);

						map.put("code", "200");
						map.put("data", pMap);
						logger.error("------查询设备信息------" + map);
						return JsonUtil.objectToJson(map);
					}
				}
			}
		}
		map.put("code", "201");
		map.put("data", "查询异常");
		logger.error("------查询设备信息------" + map);
		return JsonUtil.objectToJson(map);
	}
}
