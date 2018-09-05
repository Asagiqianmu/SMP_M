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
import com.pms.code.entity.base.Property;
import com.pms.code.exception.DAOException;
import com.pms.code.service.PropertyService;
import com.pms.code.util.MapperConst;
import com.pms.code.util.Page;

@Transactional
@Service("propertyServiceImpl")
public class PropertyServiceImpl implements PropertyService {
	private Logger logger = LoggerFactory.getLogger(PropertyServiceImpl.class);

	@Autowired
	private BaseDao<Property, Serializable> propertyBaseDaoImpl;
	@Autowired
	private BaseDao<Integer, Serializable> countpropertyBaseDaoImpl;
	@Autowired
	private BaseDao<HashMap<String,Object>, Serializable> mapPropertyBaseDaoImpl;

	/**
	 * 分页查询物业公司列表集合
	 */
	@Override
	public HashMap<String, Object> queryPropertyList(HashMap<String, Object> paramMap) {
		List<Property> listPaging = null;
		Integer pageSize = (Integer) paramMap.get("pageSize");
		Integer pageIndex = (Integer) paramMap.get("pageIndex");
		String company_name = (String) paramMap.get("company_name");
		Property property = new Property();
		property.setCompany_name(company_name);
		Page<Property> page = new Page<Property>(pageSize, pageIndex);
		// 查询物业公司总数
		int total = queryPropertyTotal(property);
		page.setRecord(total);
		// 计算查询的起始位置
		int startCount = (pageIndex - 1) * pageSize;
		paramMap.put("startCount", startCount);
		try {
			listPaging = propertyBaseDaoImpl.selectListPaging(paramMap,
					MapperConst.Property_Mapper + ".queryPropertyList");

			page.setHouseList(listPaging);
			paramMap.put("page", page);
			return paramMap;
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
			return null;
		}
	}

	/**
	 * 保存物业公司修改
	 */
	@Override
	public boolean saveEditProperty(Property property) {
		int count = 0;
		try {
			count = propertyBaseDaoImpl.update(property, MapperConst.Property_Mapper + ".saveEditProperty");
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
		}
		return count > 0 ? true : false;
	}

	/**
	 * 删除物业公司
	 */
	@Override
	public boolean deleteProperty(int[] ids) {
		int count = 0;
		try {
			count = propertyBaseDaoImpl.delete(ids, MapperConst.Property_Mapper + ".deleteProperty");
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
		}
		return count > 0 ? true : false;
	}

	/**
	 * 添加新物业公司
	 */
	@Override
	public boolean insertProperty(Property property) {
		int count = 0;
		try {
			count = propertyBaseDaoImpl.insert(property, MapperConst.Property_Mapper + ".insertProperty");
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
		}
		return count > 0 ? true : false;
	}

	/**
	 * 查询物业公司列表总数
	 */
	@Override
	public int queryPropertyTotal(Property property) {
		int count = 0;
		try {
			count = countpropertyBaseDaoImpl.selectOne(MapperConst.Property_Mapper + ".queryPropertyTotal", property);
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
		}
		return count;
	}

	/**
	 * 查询物业公司列表通过id
	 */
	@Override
	public Property queryPropertyById(int id) {
		Property property = null;
		try {
			property = propertyBaseDaoImpl.select(id, MapperConst.Property_Mapper + ".queryPropertyById");
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
		}
		return property;
	}

	/**
	 * 通过pid查询物业停息
	 */
	@Override
	public Property queryPropertyByPid(int pid) {
		Property property=null;
		try {
			property=propertyBaseDaoImpl.select(pid,MapperConst.Property_Mapper+".queryPropertyByPid");
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
		}
		return property;
	}

	/**
	 * 修改公司信息url
	 */
	@Override
	public boolean updatePropertyUrl(HashMap<String, Object> map) {
		int count=0;
		try {
			count=mapPropertyBaseDaoImpl.update(map,MapperConst.Property_Mapper+".updatePropertyUrl");
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
		}
		return count>0?true:false;
	}
}
