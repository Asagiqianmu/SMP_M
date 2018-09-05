package com.pms.code.service;

import java.util.HashMap;

import com.pms.code.entity.base.Maintenance; 

public interface MaintenanceService {
	
	/**
	 * 查询维修服务列表
	 * @param map
	 * @return
	 */
	HashMap<String,Object> queryMaintanance(HashMap<String,Object> map);
	
	/**
	 * 查询维修服务列表总数
	 * @return
	 */
	int	queryMaintenanceTotal(HashMap<String,Object> map);
	
	/**
	 * 删除维修信息
	 */
	boolean deleteMainTenance(int [] ids);
	
	/**
	 * 查询维修列表通过id
	 */
	Maintenance queryMaintenanceById(int id);
	
	/**
	 * 保存编辑维修服务信息
	 */
	boolean saveEditMaintenance(Maintenance maintenance);
	
	/**
	 * 增加维修项目
	 */
	boolean insertMaintenance(Maintenance maintenance);
	
	/**
	 * 查询维修列表:公司登录
	 * @param map
	 * @return
	 */
	HashMap<String,Object> queryMaintenanceCompany(HashMap<String,Object> map); 
	
	/**
	 * 查询维系列表总数:公司登录
	 * @param map
	 * @return
	 */
	int queryMaintenanceTotalComapny(HashMap<String,Object> map);
}
