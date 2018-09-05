package com.pms.code.service; 
import java.util.HashMap;
import java.util.List;

import com.pms.code.entity.base.PropertyManagementDepartment;
 
public interface PropertyManagementDepartmentService {

	/**
	 * 查询物业办事处管理部门列表	 
	 * @return
	 */
	HashMap<String, Object> queryPropertyManagementDepartmentList(HashMap<String,Object> map);
	 
	/**
	 * 保存物业办事处管理部门修改	
	 * @param id
	 * @return
	 */
	boolean saveEditPropertyManagementDepartment(PropertyManagementDepartment department);
	
	/**
	 * 查询物业办事处管理部门通过id
	 * @param id
	 * @return
	 */
	PropertyManagementDepartment queryPropertyManagementDepartmentById(int id);
	
	/**
	 * 物业办事处管理部门删除	
	 * @param property
	 * @return
	 */
	boolean deletePropertyManagementDepartment(int []ids);
	
	/**
	 * 查询物业办事处管理部门总数
	 */
	int queryPropertyManagementDepartmentTotal(int pid);
	
	/**
	 * 添加新物业办事处管理部门
	 * @param property
	 * @return
	 */
	boolean insertPropertyManagementDepartment(PropertyManagementDepartment department);
	
	/**
	 * 查询所有办事处管理列表
	 * @return
	 */
	List<PropertyManagementDepartment> queryAllPropertyManagementDepartmentList(int pid);
	
	/**
	 * 查询业务办事处信息
	 * @param propertyManagementDepartment
	 */
	PropertyManagementDepartment queryPropertyManagementDepartment(PropertyManagementDepartment propertyManagementDepartment);
	
	PropertyManagementDepartment queryPropertyManagementDepartmentByName(HashMap<String,Object> map);
	
	/**
	 * 查询小区名称
	 * @param map
	 * @return
	 */
	List<PropertyManagementDepartment> queryPropertyManagementDepartmentName(HashMap<String,Object> map);
}
