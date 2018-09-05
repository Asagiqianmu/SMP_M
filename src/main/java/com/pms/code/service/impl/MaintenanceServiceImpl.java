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
import com.pms.code.entity.base.Maintenance;
import com.pms.code.exception.DAOException;
import com.pms.code.service.MaintenanceService;
import com.pms.code.util.MapperConst;
import com.pms.code.util.Page;

@Service("maintenanceServiceImpl")
@Transactional
public class MaintenanceServiceImpl implements MaintenanceService{
	
	private Logger logger = LoggerFactory.getLogger(MaintenanceServiceImpl.class);
	
	@Autowired
	private BaseDao<Maintenance,Serializable> maintenanceBaseDaoImpl;

	@Autowired
	private BaseDao<Integer,Serializable> countMaintenanceBaseDaoImpl;
	/**
	 * 查询维修服务列表
	 */
	@Override
	public HashMap<String, Object> queryMaintanance(HashMap<String, Object> paramMap) {
		Integer pageIndex=(Integer) paramMap.get("pageIndex");
		Integer pageSize=(Integer) paramMap.get("pageSize");
		int maintenanceTotal = queryMaintenanceTotal(paramMap);
		int startCount=(pageIndex-1)*pageSize;
		paramMap.put("startCount",startCount);
		Page<Maintenance> page=new Page(pageSize,pageIndex);
		page.setRecord(maintenanceTotal);
		List<Maintenance> list=null;
		try {
			list=maintenanceBaseDaoImpl.selectListPaging(paramMap,MapperConst.Maintenance_Mpper+".queryMaintenance");
			page.setHouseList(list);
			paramMap.put("page",page);
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
		}
		return paramMap;
	}

	/**
	 * 查询维修服务列表总数
	 */
	@Override
	public int queryMaintenanceTotal(HashMap<String,Object> map) {
		int count=0;
		try {
			countMaintenanceBaseDaoImpl.select(map,MapperConst.Maintenance_Mpper+".queryMaintenanceTotal");
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
		}
		return count;
	}

	/**
	 * 删除维修列表
	 */
	@Override
	public boolean deleteMainTenance(int[] ids) {
		int count=0;
		try {
			count= maintenanceBaseDaoImpl.delete(ids,MapperConst.Maintenance_Mpper+".deleteMainTenance");
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
		}
		return count>0?true:false;
	}

	/**
	 * 查询维修列表通过id
	 */
	@Override
	public Maintenance queryMaintenanceById(int id) {
		Maintenance maintenance=null;
		try {
			 maintenance=maintenanceBaseDaoImpl.select(id,MapperConst.Maintenance_Mpper+".queryMaintenanceById");
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
		}
		return maintenance;
	}

	/**
	 * 维修服务信息
	 * @param maintenance
	 * @return
	 */
	@Override
	public boolean saveEditMaintenance(Maintenance maintenance) {
		int count=0;
		try {
			count=maintenanceBaseDaoImpl.update(maintenance,MapperConst.Maintenance_Mpper+".updateMaintenance");
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
		}
		return count>0?true:false;
	}

	/**
	 * 增加维修项目
	 */
	@Override
	public boolean insertMaintenance(Maintenance maintenance) {
		int count=0;
		try {
			count=maintenanceBaseDaoImpl.insert(maintenance,MapperConst.Maintenance_Mpper+".insertMaintenance");
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
		}
		return count>0?true:false;
	}

	/**
	 * 查询维修列表:公司登录
	 */
	@Override
	public HashMap<String, Object> queryMaintenanceCompany(HashMap<String, Object> paramMap) {
		Integer pageIndex=(Integer) paramMap.get("pageIndex");
		Integer pageSize=(Integer) paramMap.get("pageSize");
		int maintenanceTotal = queryMaintenanceTotalComapny(paramMap);
		int startCount=(pageIndex-1)*pageSize;
		paramMap.put("startCount",startCount);
		Page<Maintenance> page=new Page(pageSize,pageIndex);
		page.setRecord(maintenanceTotal);
		List<Maintenance> list=null;
		try {
			list=maintenanceBaseDaoImpl.selectListPaging(paramMap,MapperConst.Maintenance_Mpper+".queryMaintenanceCompany");
			page.setHouseList(list);
			paramMap.put("page",page);
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
		}
		return paramMap;
	}
	
	/**
	 * 查询维修总数:公司登录
	 */
	@Override
	public int queryMaintenanceTotalComapny(HashMap<String, Object> map) {
		int count=0;
		try {
			countMaintenanceBaseDaoImpl.select(map,MapperConst.Maintenance_Mpper+".queryMaintenanceTotalComapny");
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
		}
		return count;
	}
}












