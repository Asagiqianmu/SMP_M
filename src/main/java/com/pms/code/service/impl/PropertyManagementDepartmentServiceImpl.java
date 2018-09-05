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
import com.pms.code.entity.base.PropertyManagementDepartment;
import com.pms.code.exception.DAOException;
import com.pms.code.service.PropertyManagementDepartmentService;
import com.pms.code.util.MapperConst;
import com.pms.code.util.Page;


@Transactional
@Service("propertyManagementDepartmentServiceImpl")
public class PropertyManagementDepartmentServiceImpl  implements PropertyManagementDepartmentService{
	private Logger logger = LoggerFactory.getLogger(PropertyManagementDepartmentServiceImpl.class);
	
	@Autowired
	private BaseDao<PropertyManagementDepartment, Serializable> propertyManagementDepartmentBaseDaoImpl;
	@Autowired
	private BaseDao<Integer, Serializable> countpropertyManagementDepartmentBaseDaoImpl;

	/**
	 * 分页查询小区物业列表集合
	 */
	@Override
	public HashMap<String,Object> queryPropertyManagementDepartmentList(HashMap<String,Object> paramMap) {
		List<PropertyManagementDepartment> listPaging=null;
		Integer pageSize = (Integer) paramMap.get("pageSize");
		Integer pageIndex=(Integer) paramMap.get("pageIndex");
		String management_department_name=(String) paramMap.get("management_department_name");
		PropertyManagementDepartment department = new PropertyManagementDepartment();
		department.setManagement_department_name(management_department_name);
		Page<PropertyManagementDepartment> page=new Page<PropertyManagementDepartment>(pageSize,pageIndex);
		Integer pid=(Integer) paramMap.get("pid");
//		查询小区物业办事处总数
		int total = queryPropertyManagementDepartmentTotal(pid); 
		page.setRecord(total);
//		计算查询的起始位置
		int startCount=(pageIndex-1)*pageSize;
		paramMap.put("startCount",startCount);
		try {
			listPaging= propertyManagementDepartmentBaseDaoImpl.selectListPaging(paramMap,MapperConst.PropertyManagementDepartment_Mapper+".queryPropertyManagementDepartmentList");
			
			page.setHouseList(listPaging);
			paramMap.put("page",page);
			return paramMap;
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
			return null;
		} 
	}

	/**
	 * 保存小区物业办事处修改
	 */
	@Override
	public boolean saveEditPropertyManagementDepartment(PropertyManagementDepartment department) {
		int count=0;
		try {
			count = propertyManagementDepartmentBaseDaoImpl.update(department,MapperConst.PropertyManagementDepartment_Mapper+".saveEditPropertyManagementDepartment");
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
		}
		return count>0?true:false;
	}

	/**
	 * 删除物业办事处
	 */
	@Override 
	public boolean deletePropertyManagementDepartment(int []ids) {
		int count=0;
		try {
			count = propertyManagementDepartmentBaseDaoImpl.delete(ids,MapperConst.PropertyManagementDepartment_Mapper+".deletePropertyManagementDepartment");
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
		}
		return count>0?true:false;
	}

	/**
	 * 添加新物业办事处
	 */
	@Override
	public boolean insertPropertyManagementDepartment(PropertyManagementDepartment property) {
		int count=0;
		try {
			count = propertyManagementDepartmentBaseDaoImpl.insert(property,MapperConst.PropertyManagementDepartment_Mapper+".insertPropertyManagementDepartment");
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
		}
		return count>0?true:false;
	}

	/**
	 * 查询小区物业办事处列表总数
	 */
	@Override
	public int queryPropertyManagementDepartmentTotal(int pid) {
		int count=0; 
		try {
			count =countpropertyManagementDepartmentBaseDaoImpl.select(pid,MapperConst.PropertyManagementDepartment_Mapper+".queryPropertyManagementDepartmentTotal");
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
		}
		return count;
	}

	/**
	 * 查询小区物业办事处列表通过id
	 */
	@Override
	public PropertyManagementDepartment queryPropertyManagementDepartmentById(int id) {
		PropertyManagementDepartment property=null;
		try {
			property= propertyManagementDepartmentBaseDaoImpl.select(id,MapperConst.PropertyManagementDepartment_Mapper+".queryPropertyManagementDepartmentById");
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
		}
		return property;
	}

	/**
	 * 查询所有物业管理
	 */
	@Override
	public List<PropertyManagementDepartment> queryAllPropertyManagementDepartmentList(int pid) {
		try {
			HashMap<String,Object> hMap=new HashMap<String,Object>();
			hMap.put("pid",pid);
			return propertyManagementDepartmentBaseDaoImpl.selectListPaging(hMap,MapperConst.PropertyManagementDepartment_Mapper+".queryAllPropertyManagementDepartmentList");
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
			return null;
		} 
	}

	/**
	 * 查询物业管理信息
	 */
	@Override
	public PropertyManagementDepartment queryPropertyManagementDepartment(
			PropertyManagementDepartment propertyManagementDepartment) {
		try {
			return  propertyManagementDepartmentBaseDaoImpl.select(propertyManagementDepartment,MapperConst.PropertyManagementDepartment_Mapper+".queryPropertyManagementDepartment");
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
			return null;
		}
	}

	/**
	 * 查询物业管理处id
	 */
	@Override
	public PropertyManagementDepartment queryPropertyManagementDepartmentByName(HashMap<String, Object> map) {
		 try {
			return propertyManagementDepartmentBaseDaoImpl.select(map,MapperConst.PropertyManagementDepartment_Mapper+".queryPropertyManagementDepartmentByName");
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
			return null;
		}
	}

	/**
	 * 查询小区名称
	 */
	@Override
	public List<PropertyManagementDepartment> queryPropertyManagementDepartmentName(HashMap<String, Object> map) {
		List<PropertyManagementDepartment> pmds=null;
		try {
			pmds=propertyManagementDepartmentBaseDaoImpl.selectListPaging(map,MapperConst.PropertyManagementDepartment_Mapper+".queryPropertyManagementDepartmentName");
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
			e.printStackTrace();
		}
		return  pmds;
	}
}
