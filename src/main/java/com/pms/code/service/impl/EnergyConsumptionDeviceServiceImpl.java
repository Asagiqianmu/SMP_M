package com.pms.code.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.pms.code.dao.BaseDao;
import com.pms.code.entity.base.EnergyConsumptionDevice;
import com.pms.code.entity.base.Maintenance;
import com.pms.code.exception.DAOException;
import com.pms.code.service.EnergyConsumptionDeviceService;
import com.pms.code.util.MapperConst;
import com.pms.code.util.Page;

@Service(value = "energyConsumptionDeviceServiceImpl")
@Transactional
public class EnergyConsumptionDeviceServiceImpl implements EnergyConsumptionDeviceService {
	private Logger logger = LoggerFactory.getLogger(EnergyConsumptionDeviceServiceImpl.class);

	@Autowired
	private BaseDao<EnergyConsumptionDevice, Serializable> energyConsumptionDeviceBaseDaoImpl;

	@Autowired
	private BaseDao<HashMap<String, Object>, Serializable> hmapEnergyConsumptionDeviceBaseDaoImpl;
	
	@Autowired
	private BaseDao<Integer, Serializable> countEnergyConsumptionDeviceBaseDaoImpl;

	/**
	 * 插入能耗设备
	 */
	@Override
	public boolean insertEnergyConsumptionDevice(EnergyConsumptionDevice energyConsumptionDevice) {
		int count = 0;
		try {
			count = energyConsumptionDeviceBaseDaoImpl.insert(energyConsumptionDevice,
					MapperConst.EnergyConsumptionDevice_Mapper + ".insertEnergyConsumptionDevice");
		} catch (DAOException e) {
			e.printStackTrace();
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
		}
		return count > 0 ? true : false;
	}

	/**
	 * 查询单个能耗设备信息（根据设备ID）
	 */
	@Override
	public EnergyConsumptionDevice queryEnergyConsumptionDeviceByDeviceId(String deviceId) {
		try {
			HashMap<String, Object> map = new HashMap<String, Object>();
			return energyConsumptionDeviceBaseDaoImpl
					.selectOne(MapperConst.EnergyConsumptionDevice_Mapper + ".queryEnergyConsumptionDeviceById", map);
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
			return null;
		}
	}

	/**
	 * 修改能耗设备表示数
	 */
	@Override
	public boolean updateEnergyConsumptionDevice(HashMap<String, Object> map) {
		int count = 0;
		try {
			count = hmapEnergyConsumptionDeviceBaseDaoImpl.update(map,
					MapperConst.EnergyConsumptionDevice_Mapper + ".updateEnergyConsumptionDevice");
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
		}
		return count > 0 ? true : false;
	}

	/**
	 * 查询
	 */
	@Override
	public List<EnergyConsumptionDevice> queryEnergyConsumptionDeviceByDevice() {
		try {
			return energyConsumptionDeviceBaseDaoImpl.selectListPaging(null,
					MapperConst.EnergyConsumptionDevice_Mapper + ".queryEnergyConsumptionDeviceByDevice");
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
			return null;
		}
	}

	/**
	 * 查询能耗设备
	 */
	@Override
	public List<EnergyConsumptionDevice> queryEnergyConsumptionDeviceByHouseId(HashMap<String, Object> map) {
		try {
			return  energyConsumptionDeviceBaseDaoImpl.selectListPaging(map,MapperConst.EnergyConsumptionDevice_Mapper+".queryEnergyConsumptionDeviceByHouseId");
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
			return null;
		} 
	}

	/**
	 * 通过房间id查询
	 */
	@Override
	public HashMap<String,Object> queryEnergyConsumptionDeviceByHouseIds(HashMap<String,Object> paramMap) {
		Integer pageIndex=(Integer) paramMap.get("pageIndex");
		Integer pageSize=(Integer) paramMap.get("pageSize");
		int energyConsumptionDeviceTotal = queryEnergyConsumptionDeviceTotal(paramMap);
		int startCount=(pageIndex-1)*pageSize;
		paramMap.put("startCount",startCount);
		Page<EnergyConsumptionDevice> page=new Page(pageSize,pageIndex);
		page.setRecord(energyConsumptionDeviceTotal);
		List<EnergyConsumptionDevice> list=null;
		try {
			list=energyConsumptionDeviceBaseDaoImpl.selectListPaging(paramMap,MapperConst.EnergyConsumptionDevice_Mapper+".queryEnergyConsumptionDeviceByHouseIds");
			page.setHouseList(list);
			paramMap.put("page",page);
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
		}
		return paramMap; 
	}

	@Override
	public int queryEnergyConsumptionDeviceTotal(HashMap<String, Object> map) {
		int count=0;
		try { 
			count= countEnergyConsumptionDeviceBaseDaoImpl.select(map,MapperConst.EnergyConsumptionDevice_Mapper+".queryEnergyConsumptionDeviceTotal");
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
		}
		return count;
	}
}
