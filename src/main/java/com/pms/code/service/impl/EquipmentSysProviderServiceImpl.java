package com.pms.code.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pms.code.dao.BaseDao;
import com.pms.code.entity.base.EquipmentSysProvider;
import com.pms.code.exception.DAOException;
import com.pms.code.service.EquipmentSysProviderService;
import com.pms.code.util.MapperConst;
import com.pms.code.util.Page;

@Transactional
@Service("equipmentSysProviderServiceImpl")
public class EquipmentSysProviderServiceImpl implements EquipmentSysProviderService {

	private Logger logger = LoggerFactory.getLogger(EquipmentSysProviderServiceImpl.class);

	@Autowired
	private BaseDao<EquipmentSysProvider, Serializable> equipmentSysProviderBaseDaoImpl;

	@Autowired
	private BaseDao<Integer, Serializable> countEquipmentSysProviderBaseDaoImpl;

	@Override
	public EquipmentSysProvider findEquipmentSysProviderByID(int id) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", id);
			return (EquipmentSysProvider) equipmentSysProviderBaseDaoImpl
					.selectOne(MapperConst.EquipmentSysProvider_Mapper + ".findEquipmentSysProviderInfoByID", map);
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
			return null;
		}
	}

	@Override
	public HashMap<String, Object> queryEquipmentSysProviderInfo(HashMap<String, Object> paramMap) {
		List<EquipmentSysProvider> listPaging = null;
		Integer pageSize = (Integer) paramMap.get("pageSize");
		Integer pageIndex = (Integer) paramMap.get("pageIndex");

		Page<EquipmentSysProvider> page = new Page<EquipmentSysProvider>(pageSize, pageIndex);
		// 查询公告总数
		int total = queryEquipmentSysProviderInfoTotal();
		page.setRecord(total);
		// 计算查询的起始位置
		int startCount = (pageIndex - 1) * pageSize;
		paramMap.put("startCount", startCount);
		try {
			listPaging = equipmentSysProviderBaseDaoImpl.selectListPaging(paramMap,
					MapperConst.EquipmentSysProvider_Mapper + ".queryEquipmentSysProviderInfo");
			page.setHouseList(listPaging);
			paramMap.put("page", page);
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
		}
		return paramMap;
	}

	@Override
	public int queryEquipmentSysProviderInfoTotal() {
		int count = 0;
		try {
			count = countEquipmentSysProviderBaseDaoImpl.count(0,
					MapperConst.EquipmentSysProvider_Mapper + ".queryEquipmentSysProviderInfoTotal");
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
		}
		return count;
	}

	/**
	 * 修改服务商信息
	 */
	@Override
	public boolean updateEquipmentSysProviderInfo(EquipmentSysProvider equipmentSysProvider) {
		int count = 0;
		try {
			count = equipmentSysProviderBaseDaoImpl.update(equipmentSysProvider,
					MapperConst.EquipmentSysProvider_Mapper + ".updateEquipmentSysProviderInfo");
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
		}
		return count > 0 ? true : false;
	}

	/**
	 * 插入服务商信息
	 */
	@Override
	public boolean insertEquipmentSysProviderInfo(EquipmentSysProvider equipmentSysProvider) {
		int count = 0;
		try {
			count = equipmentSysProviderBaseDaoImpl.insert(equipmentSysProvider,
					MapperConst.EquipmentSysProvider_Mapper + ".insertEquipmentSysProviderInfo");
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
		}
		return count > 0 ? true : false;
	}

	/**
	 * 删除服务商信息
	 */
	@Override
	public boolean deleteEquipmentSysProviderInfo(int[] ids) {
		int count = 0;
		try {
			count = equipmentSysProviderBaseDaoImpl.delete(ids,
					MapperConst.EquipmentSysProvider_Mapper + ".deleteEquipmentSysProviderInfo");
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
		}
		return count > 0 ? true : false;
	}

}
