package com.pms.code.util.listener;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;
import com.pms.code.controller.danbay.DanbayAPI;
import com.pms.code.entity.base.EnergyConsumptionDevice;
import com.pms.code.entity.base.HouseID;
import com.pms.code.entity.base.LockInfo;
import com.pms.code.entity.base.PropertyManagementDepartment;
import com.pms.code.entity.base.UnlockingKey;
import com.pms.code.entity.base.UseWaterElectricity;
import com.pms.code.service.EnergyConsumptionDeviceService;
import com.pms.code.service.EquipmentSysProviderService;
import com.pms.code.service.HouseIDService;
import com.pms.code.service.LockInfoService;
import com.pms.code.service.PropertyManagementDepartmentService;
import com.pms.code.service.UnlockingKeyService;
import com.pms.code.service.UseWaterElectricityService;
import com.pms.code.util.DateUtil;
import com.pms.code.util.DateUtils;
import com.pms.code.util.JsonUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 这个监听器用于项目启动时启动定时器进行数据更新
 * 
 * @author dengfei E-mail:dengfei200857@163.com
 * @time 2018年4月11日 下午3:01:29
 */
@Service
public class DataUpdateCenter implements ApplicationListener<ContextRefreshedEvent> {

	private static Logger logger = LoggerFactory.getLogger(DataUpdateCenter.class);
	@Autowired
	private EquipmentSysProviderService equipmentSysProviderServiceImpl;

	@Autowired
	private UnlockingKeyService unlockingKeyServiceImpl;

	@Autowired
	private EnergyConsumptionDeviceService energyConsumptionDeviceServiceImpl;

	@Autowired
	private HouseIDService houseIDServiceImpl;

	@Autowired
	private UseWaterElectricityService useWaterElectricityServiceImpl;

	@Autowired
	private PropertyManagementDepartmentService propertyManagementDepartmentServiceImpl;

	@Autowired
	private LockInfoService lockInfoServiceImpl;

	// 创建一个容量为6的定长线程池，支持定时及周期性任务执行,作为蛋贝系统拿取数据的线程池
	ScheduledExecutorService danbayScheduledPool = Executors.newScheduledThreadPool(6);
	// 创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
	ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

	public static final long delayed_30 = 30;
	public static final long delayed_60 = 60;
	public static final long Interval_3600 = 3600;
	public static final long Interval_oneDay = 24 * 60 * 60 * 1000;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		if (arg0.getApplicationContext().getParent() == null) {
			logger.warn("=============DataUpdateCenter===启动定时器进行数据更新=================");
			// 第一个参数位Runable现成对象，第二个参数为首次执行的延时时间，第三个参数为定时执行的间隔时间 ,第四个参数为时间单位
			// scheduleAtFixedRate 函数是基于固定时间间隔进行任务调度
			// 启动蛋贝系统关联线程池定时器,2个小时进行一次数据刷新
			danbayScheduledPool.scheduleAtFixedRate(danbayRunable1, delayed_30, Interval_3600, TimeUnit.SECONDS);

			// 计算当前时间距离明天02:00:00还剩多少毫秒
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = null;
			try {
				date = sdf.parse(sdf.format(new Date()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//例如指定时间为2018-04-20 02:00:00
			long zdtime = DateUtil.getTimeMillis(date,"02:00:00");
			long val = zdtime - System.currentTimeMillis();
			//假设当前时间为2018-04-20 01:00:00,指定时间就大于当前时间,则时间差将作为定时器的延迟启动时间
			//假设当前时间为2018-04-20 03:00:00,指定时间就小于当前时间,则需要在时间差上加一天将作为定时器的延迟启动时间
			long delayed_02_00_00 = val > 0 ? val + Interval_oneDay : val;

			// 启动蛋贝系统关联线程池，每天凌晨2点钟执行，进行统计昨天的水、电表使用量
			/*danbayScheduledPool.scheduleAtFixedRate(danbayRunable2, delayed_02_00_00, Interval_oneDay,
					TimeUnit.MILLISECONDS);*/
			
			danbayScheduledPool.scheduleAtFixedRate(danbayRunable2, delayed_30, Interval_oneDay,
					TimeUnit.MILLISECONDS);

			//启动蛋贝系统关联线程池,当日能耗设备示数
//			danbayScheduledPool.scheduleAtFixedRate(danbayRunable3, delayed_02_00_00, Interval_oneDay,
//					TimeUnit.MILLISECONDS);

			//启动蛋贝系统关联线程池,同步能耗设备信息
//			danbayScheduledPool.scheduleAtFixedRate(danbayRunable4, delayed_02_00_00, Interval_oneDay,
//					TimeUnit.MILLISECONDS);
			
			//启动蛋贝系统关联线程池,同步门锁设备信息
//			danbayScheduledPool.scheduleAtFixedRate(danbayRunable5, delayed_30, Interval_3600, TimeUnit.SECONDS);

			//启动蛋贝系统关联线程池,单个门锁设备信息
//			danbayScheduledPool.scheduleAtFixedRate(danbayRunable6, delayed_30, Interval_3600, TimeUnit.SECONDS);
		}
	}

	/**
	 * 蛋贝系统Runable线程，作为多线程的主线程1 主要处理在蛋贝平台同步智能设备信息
	 */
	Runnable danbayRunable1 = new Runnable() {
		public void run() {
			// 开始取数据分析
			logger.warn("=====danbayRunable=====1、登录授权===============================");
			DanbayAPI danbayapi = new DanbayAPI();
			JSONObject data = new JSONObject();
			data.put("dlc_id", 2);// 默认提供服务提供商编号
			String loginJson = danbayapi.authorizationLogin(data.toString(), equipmentSysProviderServiceImpl);
			JSONObject resjson = JSONObject.fromObject(loginJson);
			if (resjson.getInt("code") == 200) {
				JSONObject jsondata = resjson.getJSONObject("result");
				String mtoken = jsondata.getString("mtoken");
				String host = resjson.getString("host");
				JSONObject rqjson = new JSONObject();
				rqjson.put("mtoken", mtoken);
				rqjson.put("host", host);
				logger.warn("=====danbayRunable=====2、查询所有设备列表===============================");
				String resStr = danbayapi.getDeviceListAll(JsonUtil.objectToJson(rqjson));
				if (JSONObject.fromObject(resStr).getInt("code") == 200) {
					JSONArray devicelist = JSONObject.fromObject(resStr).getJSONArray("result");
					SyncDanbayAllDevThread allDevThread = new SyncDanbayAllDevThread(devicelist,mtoken,host);
					cachedThreadPool.execute(allDevThread);
				}
			}
		}
	};

	/**
	 * 蛋贝系统Runable线程，作为多线程的主线程2 主要处理定时完成同步前一天的水、电表记录数据
	 */
	Runnable danbayRunable2 = new Runnable() {
		public void run() {
			// 开始取数据分析
			logger.warn("=====danbayRunable=====1、登录授权===============================");
			DanbayAPI danbayapi = new DanbayAPI();
			JSONObject data = new JSONObject();
			data.put("dlc_id", 2);// 默认提供服务提供商编号
			String loginJson = danbayapi.authorizationLogin(data.toString(), equipmentSysProviderServiceImpl);
			JSONObject resjson = JSONObject.fromObject(loginJson);
			if (resjson.getInt("code") == 200) {
				JSONObject jsondata = resjson.getJSONObject("result");
				String mtoken = jsondata.getString("mtoken");
				String host = resjson.getString("host");
				JSONObject rqjson = new JSONObject();
				rqjson.put("mtoken", mtoken);
				rqjson.put("host", host);
				logger.warn("=====danbayRunable=====2、根据水、电表设备ID遍历查询示数，并保存到数据库表===============================");
				/**
				 * 代码块
				 */
				String startTime = new SimpleDateFormat("yyyyMMdd").format(DateUtils.changeDate(new Date(), -50))+"00";
				String endTime = new SimpleDateFormat("yyyyMMdd").format(DateUtils.changeDate(new Date(), -50))+"24";
				List<UseWaterElectricity> queryUseWaterElectricity = useWaterElectricityServiceImpl
						.queryUseWaterElectricity();
				for (UseWaterElectricity ue : queryUseWaterElectricity) {
					rqjson.put("startTime", startTime);
					rqjson.put("endTime", endTime);
					rqjson.put("deviceId", ue.getDevice_id());
					logger.error("===============获取能耗设备区间用量================");
					String energySectionConsumption = danbayapi
							.getEnergySectionConsumption(JsonUtil.objectToJson(rqjson));
					System.out.println(startTime+energySectionConsumption+"----------------------------------------------");
					System.out.println(endTime+energySectionConsumption+"----------------------------------------------");
					if (JSONObject.fromObject(energySectionConsumption).getInt("code") == 200) {
						JSONObject object = JSONObject.fromObject(energySectionConsumption).getJSONObject("result");
						SyncDanbayEveryDevThread syncDanbayEveryDevThread = new SyncDanbayEveryDevThread(
								Double.parseDouble(object.getString("use_count")), rqjson.getString("deviceId"));
						cachedThreadPool.execute(syncDanbayEveryDevThread);
					}
				}
			}
		}
	};

	/**
	 * 蛋贝系统Runable线程，作为多线程的主线程3 主要处理在蛋贝平台同步智能设备信息
	 */
//	Runnable danbayRunable3 = new Runnable() {
//		public void run() {
//			// 开始取数据分析
//			logger.warn("=====danbayRunable=====1、登录授权===============");
//			DanbayAPI danbayapi = new DanbayAPI();
//			JSONObject data = new JSONObject();
//			data.put("dlc_id", 2);// 默认提供服务提供商编号
//			String loginJson = danbayapi.authorizationLogin(data.toString(), equipmentSysProviderServiceImpl);
//			JSONObject resjson = JSONObject.fromObject(loginJson);
//			if (resjson.getInt("code") == 200) {
//				JSONObject jsondata = resjson.getJSONObject("result");
//				String mtoken = jsondata.getString("mtoken");
//				String host = resjson.getString("host");
//				JSONObject rqjson = new JSONObject();
//				rqjson.put("mtoken", mtoken);
//				rqjson.put("host", host);
//				logger.warn("=====danbayRunable=====2、根据能耗设备ID遍历查询示数，并保存到数据库表=================");
//				/**
//				 * 代码块
//				 */
//				String readTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
//				List<EnergyConsumptionDevice> energyConsumptionDevices = energyConsumptionDeviceServiceImpl
//						.queryEnergyConsumptionDeviceByDevice();
//				for (EnergyConsumptionDevice eDevice : energyConsumptionDevices) {
//					rqjson.put("deviceId", eDevice.getDevice_id());
//					rqjson.put("readTime", readTime);
//					logger.error("===============获取能耗当日能耗设备示数================");
//					String energyDailyReading = danbayapi.getEnergyDailyReading(JsonUtil.objectToJson(rqjson));
//					if (JSONObject.fromObject(energyDailyReading).getInt("code") == 200) {
//						JSONObject object = JSONObject.fromObject(energyDailyReading).getJSONObject("result");
//						SyncDanbayEveryEnergyThread syncDanbayEveryEnergyThread = new SyncDanbayEveryEnergyThread(
//								Double.parseDouble(object.getString("electricQuantity")), rqjson.getString("deviceId"));
//						cachedThreadPool.execute(syncDanbayEveryEnergyThread);
//					}
//				}
//			}
//		}
//	};

	/**
	 * 蛋贝系统Runable线程，作为多线程的主线程4 主要处理在蛋贝平台同步智能设备信息
	 */
//	Runnable danbayRunable4 = new Runnable() {
//		public void run() {
//			// 开始取数据分析
//			logger.warn("=====danbayRunable=====1、登录授权==================");
//			DanbayAPI danbayapi = new DanbayAPI();
//			JSONObject data = new JSONObject();
//			data.put("dlc_id", 2);// 默认提供服务提供商编号
//			String loginJson = danbayapi.authorizationLogin(data.toString(), equipmentSysProviderServiceImpl);
//			JSONObject resjson = JSONObject.fromObject(loginJson);
//			if (resjson.getInt("code") == 200) {
//				JSONObject jsondata = resjson.getJSONObject("result");
//				String mtoken = jsondata.getString("mtoken");
//				String host = resjson.getString("host");
//				JSONObject rqjson = new JSONObject();
//				rqjson.put("mtoken", mtoken);
//				rqjson.put("host", host);
//				logger.warn("=====danbayRunable=====2、根据能耗设备ID遍历查询示数，并保存到数据库表=================");
//				/**
//				 * 代码块
//				 */
//				String energyDeviceList = danbayapi.getEnergyDeviceList(JsonUtil.objectToJson(rqjson));
//				if (JSONObject.fromObject(energyDeviceList).getInt("code") == 200) {
//					JSONArray energyDeviceArr = JSONObject.fromObject(energyDeviceList).getJSONArray("result");
//					SyncDanbayALLEnergyThread aLLEnergy = new SyncDanbayALLEnergyThread(energyDeviceArr);
//					cachedThreadPool.execute(aLLEnergy);
//				}
//			}
//		}
//	};
	
	/**
	 * 蛋贝系统Runable线程，作为多线程的主线程5 主要处理定时完成门锁设备列表
	 */
//	Runnable danbayRunable5 = new Runnable() {
//		public void run() {
//			// 开始取数据分析
//			logger.warn("=====danbayRunable=====1、登录授权===============================");
//			DanbayAPI danbayapi = new DanbayAPI();
//			JSONObject data = new JSONObject();
//			data.put("dlc_id", 2);// 默认提供服务提供商编号
//			String loginJson = danbayapi.authorizationLogin(data.toString(), equipmentSysProviderServiceImpl);
//			JSONObject resjson = JSONObject.fromObject(loginJson);
//			if (resjson.getInt("code") == 200) {
//				JSONObject jsondata = resjson.getJSONObject("result");
//				String mtoken = jsondata.getString("mtoken");
//				String host = resjson.getString("host");
//				JSONObject rqjson = new JSONObject();
//				rqjson.put("mtoken", mtoken);
//				rqjson.put("host", host);
//				logger.warn("=====danbayRunable=====2、查询门锁设备，并保存到数据库表===============================");
//				/**
//				 * 代码块
//				 */
//				logger.error("===============获取能耗设备区间用量================");
//				String lock = danbayapi.getLockList(JsonUtil.objectToJson(rqjson));
//				if (JSONObject.fromObject(lock).getInt("code") == 200) {
//					JSONArray lockList = JSONObject.fromObject(lock).getJSONArray("result");
//					SyncDanbayALLLockThread syncDanbayALLLockThread = new SyncDanbayALLLockThread(lockList);
//					cachedThreadPool.execute(syncDanbayALLLockThread);
//				}
//			}
//		}
//	};

	/**
	 * 蛋贝系统Runable线程，作为多线程的主线程6 主要处理查询单个门锁设备信息
	 */
//	Runnable danbayRunable6 = new Runnable() {
//		public void run() {
//			// 开始取数据分析
//			logger.warn("=====danbayRunable=====1、登录授权===============================");
//			DanbayAPI danbayapi = new DanbayAPI();
//			JSONObject data = new JSONObject();
//			data.put("dlc_id", 2);// 默认提供服务提供商编号
//			String loginJson = danbayapi.authorizationLogin(data.toString(), equipmentSysProviderServiceImpl);
//			JSONObject resjson = JSONObject.fromObject(loginJson);
//			if (resjson.getInt("code") == 200) {
//				JSONObject jsondata = resjson.getJSONObject("result");
//				String mtoken = jsondata.getString("mtoken");
//				String host = resjson.getString("host");
//				JSONObject rqjson = new JSONObject();
//				rqjson.put("mtoken", mtoken);
//				rqjson.put("host", host);
//				logger.warn("=====danbayRunable=====2、根据查询单个门锁设备信息，并保存到数据库表===============================");
//				/**
//				 * 代码块
//				 */
//				logger.error("===============获取单个门锁设备信息================");
//				List<UnlockingKey> unlockingKeys = unlockingKeyServiceImpl.queryAllUnlockingKey();
//				for (UnlockingKey uk : unlockingKeys) {
//					rqjson.put("deviceId", uk.getDoorNum());
//					String lock = danbayapi.getLockInfo(JsonUtil.objectToJson(rqjson));
//					if (JSONObject.fromObject(lock).getInt("code") == 200) {
//						JSONObject lockList = JSONObject.fromObject(lock).getJSONObject("result");
//						String deviceId = JSONObject.fromObject(lock).getString("deviceId");
//						SyncDanbayALLLockInfoThread syncDanbayALLLockThread = new SyncDanbayALLLockInfoThread(lockList,
//								deviceId);
//						cachedThreadPool.execute(syncDanbayALLLockThread);
//					}
//				}
//			}
//		}
//	};

	/**
	 * 判断当前线程是否存在
	 * 
	 * @author dengfei E-mail:dengfei200857@163.com
	 * @time 2017年6月8日 下午5:53:37
	 * @param threadName
	 * @return
	 */
	public synchronized boolean isExistThread(String threadName) {
		Map<Thread, StackTraceElement[]> threads = Thread.getAllStackTraces();
		for (Map.Entry<Thread, StackTraceElement[]> entry : threads.entrySet()) {
			// Map.entry<Integer,String> 映射项（键-值对） 有几个方法：用上面的名字entry
			// entry.getKey() ;entry.getValue(); entry.setValue();
			// map.entrySet() 返回此映射中包含的映射关系的 Set视图。
			Thread thread = entry.getKey();
			thread.getState();
			String name = thread.getName();
			if (name.equals(threadName)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 检测表名是否存在
	 * 
	 * @return
	 */
	public synchronized boolean isExistTable(String tablename) {
		// String tableName = dataRecordServiceImpl.selectTable(tablename);
		// if (tableName != null && !tableName.equals("")) {
		// return true;
		// } else {
		return false;
		// }
	}

	// 线程____同步所有设备到数据库中
	class SyncDanbayAllDevThread implements Runnable {
		private String mtoken;
		private String host;
		private JSONArray devicelist;

		public SyncDanbayAllDevThread(JSONArray devicelist,String mtoken,String host) {
			// TODO Auto-generated constructor stub
			this.devicelist = devicelist;
			this.mtoken=mtoken;
			this.host=host;
		}

		@Override
		public void run() {
			for (int i = 0; i < devicelist.size(); i++) {
				JSONObject job = devicelist.getJSONObject(i);
				String deviceId = job.getString("deviceId");
				String houseId = job.getString("houseId");
				System.out.println("---------------------------------------------------"+houseId.length());
				int deviceType = job.getInt("deviceType");
				if (!"".equals(houseId) && houseId != null && houseId.length() > 3) {
					if (deviceType == 0) { // 门锁设备
						// 通过houseId去houseid表中匹配
						// 匹配houseid,插入UnlockingKey表.houseID不存在不操作,houseID存在则插入
						HouseID houseID = houseIDServiceImpl.queryHouseID(houseId);
						if (houseID != null) {
							UnlockingKey unlockingKey = new UnlockingKey();
							unlockingKey.setType(1);
							unlockingKey.setDlc_id(2);
							unlockingKey.setDoorNum(deviceId);
							unlockingKey.setCreateTime(new Timestamp(new Date().getTime()));
							unlockingKey.setP_m_id(houseID.getP_m_id());
							logger.error("===============插入门锁信息到设备表================");
							unlockingKey.setKeyname(houseId);
							unlockingKeyServiceImpl.insertUnlockingKey(unlockingKey);
						}
					} else if (deviceType == 1 || deviceType == 2) {// 水、电表设备
						if (!"".equals(houseId) && houseId != null && houseId.length() > 3) {
							DanbayAPI danbayAPI=new DanbayAPI();
							JSONObject paramJson=new JSONObject();
							paramJson.put("deviceId",deviceId);
							paramJson.put("mtoken",mtoken);
							paramJson.put("host",host);
							String energyDeviceInfo = danbayAPI.getEnergyDeviceInfo(paramJson.toString());
							if(!"".equals(energyDeviceInfo)&&energyDeviceInfo!=null){
								JSONObject resltenergyDeviceInfo = JSONObject.fromObject(energyDeviceInfo);
								JSONObject jsonObject = resltenergyDeviceInfo.containsKey("result")?resltenergyDeviceInfo.getJSONObject("result"):null;
								if(jsonObject != null && !jsonObject.isEmpty()){
									EnergyConsumptionDevice energyConsumptionDevice = new EnergyConsumptionDevice();
									energyConsumptionDevice.setDevice_id(deviceId);
									energyConsumptionDevice.setHouse_id(houseId);
									energyConsumptionDevice.setEnergy_type(jsonObject.getInt("energyType"));
									energyConsumptionDevice.setSub_type(jsonObject.getInt("subType")); 
									energyConsumptionDevice.setDeviceModel(jsonObject.getString("deviceModel"));
									energyConsumptionDevice.setMeter_value(jsonObject.getDouble("electricQuantity"));
									logger.error("=============插入信息到能耗表==================");
									energyConsumptionDeviceServiceImpl.insertEnergyConsumptionDevice(energyConsumptionDevice);
								}
							}  
						}
					}
				}
			}
		}
	}

	// 线程____同步每一个水、电表示数到数据库表中
	class SyncDanbayEveryDevThread implements Runnable {
		private String deviceId;
		private double count;

		public SyncDanbayEveryDevThread(double count, String deviceId) {
			// TODO Auto-generated constructor stub
			this.deviceId = deviceId;
			this.count = count;
		}

		@Override
		public void run() {
			HashMap<String, Object> hmap = new HashMap<String, Object>();
			hmap.put("deviceId", deviceId);
			hmap.put("count", count);
			logger.error("===============同步每一个水、电表示数到数据库表================");
			useWaterElectricityServiceImpl.updateUseWaterElectricity(hmap);
		}
	}

//	// 线程____同步当日能耗设备数值到数据库表中
//	class SyncDanbayEveryEnergyThread implements Runnable {
//		private String deviceId;
//		private double meterValue;
//
//		public SyncDanbayEveryEnergyThread(double meterValue, String deviceId) {
//			// TODO Auto-generated constructor stub
//			this.deviceId = deviceId;
//			this.meterValue = meterValue;
//		}
//
//		@Override
//		public void run() {
//			HashMap<String, Object> hmap = new HashMap<String, Object>();
//			hmap.put("deviceId", deviceId);
//			hmap.put("meterValue", meterValue);
//			logger.error("===============同步当日能耗设备数值到数据库表================");
//			useWaterElectricityServiceImpl.updateUseWaterElectricity(hmap);
//		}
//	}

	// 线程____查询能耗设备列表 同步到数据库表中
//	class SyncDanbayALLEnergyThread implements Runnable {
//
//		private JSONArray energyList;
//
//		public SyncDanbayALLEnergyThread(JSONArray energyList) {
//			// TODO Auto-generated constructor stub
//			this.energyList = energyList;
//		}
//
//		@Override
//		public void run() {
//			for (int i = 0; i < energyList.size(); i++) {
//				JSONObject job = energyList.getJSONObject(i);
//				EnergyConsumptionDevice energyConsumptionDevice = new EnergyConsumptionDevice();
//				energyConsumptionDevice.setDevice_id(job.getString("deviceId"));
//				energyConsumptionDevice.setDevice_status(job.getInt("deviceStatus"));
//				energyConsumptionDevice.setHouse_id(job.getString("houseId"));
//				energyConsumptionDevice.setEnergy_type(job.getInt("energyType"));
//				energyConsumptionDevice.setSub_type(job.getInt("subType"));
//				energyConsumptionDevice.setMeter_value(job.getDouble("electricQuantity"));
//				logger.error("===============同步当日能耗设备数值到数据库表================");
//				energyConsumptionDeviceServiceImpl.insertEnergyConsumptionDevice(energyConsumptionDevice);
//			}
//		}
//	}

	// 线程____查询门锁设备列表 同步到数据库表中
//	class SyncDanbayALLLockThread implements Runnable {
//		private JSONArray lockList;
//
//		public SyncDanbayALLLockThread(JSONArray lockList) {
//			// TODO Auto-generated constructor stub
//			this.lockList = lockList;
//		}
//
//		@Override
//		public void run() {
//			for (int i = 0; i < lockList.size(); i++) {
//				JSONObject job = lockList.getJSONObject(i);
//				String houseId = job.getString("houseId");
//				if (!"".equals(houseId) && houseId != null && houseId.length() > 3) {
//					// 通过houseId去houseid表中匹配
//					// 匹配houseid,插入UnlockingKey表.houseID不存在不操作,houseID存在则插入
//					HouseID houseID = houseIDServiceImpl.queryHouseID(houseId);
//					if(houseID!=null){
//						UnlockingKey unlockingKey = new UnlockingKey();
//						unlockingKey.setDoorNum(job.getString("deviceId"));
//						unlockingKey.setType(job.getInt("lockType"));
//						unlockingKey.setCreateTime(new Timestamp(new Date().getTime()));
//						unlockingKey.setP_m_id(houseID.getP_m_id());
//						logger.error("===============门锁设备列表 同步到数据库表================");
//						unlockingKey.setKeyname(houseId);
//						unlockingKeyServiceImpl.insertUnlockingKey(unlockingKey);
//					} 
//				}
//			}
//		}
//	}

	// 线程____查询门锁设备信息 同步到数据库表中
//	class SyncDanbayALLLockInfoThread implements Runnable {
//		private String deviceId;
//		private JSONObject lockInfoJson;;
//
//		public SyncDanbayALLLockInfoThread(JSONObject lockInfoJson, String deviceId) {
//			// TODO Auto-generated constructor stub
//			this.deviceId = deviceId;
//			this.lockInfoJson = lockInfoJson;
//		}
//
//		@Override
//		public void run() {
//			LockInfo lockInfo = new LockInfo();
//			lockInfo.setDeviceId(deviceId);
//			lockInfo.setDeviceModel(lockInfoJson.getString("deviceModel"));
//			lockInfo.setDeviceStatus(lockInfoJson.getInt("deviceStatus"));
//			lockInfo.setPowerStatus(lockInfoJson.getInt("powerStatus"));
//			lockInfo.setLockType(lockInfoJson.getInt("lockType"));
//			lockInfo.setCreatetime(new Timestamp(new Date().getTime()));
//			logger.error("===============门锁设备信息 同步到数据库表================");
//			lockInfoServiceImpl.insertLockInfo(lockInfo);
//		}
//	}

	public static void main(String[] args) {
//		String startTime = new SimpleDateFormat("yyyyMMddHH").format(new Date());
//		System.out.println(startTime);
	System.out.println(new SimpleDateFormat("yyyyMMddHH").format(DateUtils.changeDate(new Date(), 1)));
/*		System.out.println(new SimpleDateFormat("yyyyMMdd").format(DateUtils.changeDate(new Date(), -1))+"00");
		System.out.println(new SimpleDateFormat("yyyyMMdd").format(new Date())+"00");
		
		System.out.println(new SimpleDateFormat("yyyyMMdd").format(new Date())+"00");*/
		
		  System.out.println(DateFormatUtils.format(new Date(), "yyyyMMdd00"));  
	        System.out.println(DateFormatUtils.format(new Date(), "yyyyMMdd24")); 
	}
}
