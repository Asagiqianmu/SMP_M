package com.pms.code.service.impl;

import java.io.Serializable;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pms.code.dao.BaseDao;
import com.pms.code.entity.base.OwnerHouseRelation;
import com.pms.code.exception.DAOException;
import com.pms.code.service.OwnerHouseRelationService;
import com.pms.code.util.MapperConst;

@Transactional
@Service("ownerHouseRelationServiceImpl")
public class OwnerHouseRelationServiceImpl implements OwnerHouseRelationService {

	private Logger logger = LoggerFactory.getLogger(OwnerHouseRelationServiceImpl.class);

	@Autowired
	private BaseDao<OwnerHouseRelation, Serializable> ownerHouseRelationBaseDaoImpl;

	@Autowired
	private BaseDao<HashMap<String, Object>, Serializable> mapOwnerHouseRelationBaseDaoImpl;

	@Override
	public boolean deleteOwnerHouseRelation(HashMap<String, Object> map) {
		try {
			return mapOwnerHouseRelationBaseDaoImpl.batchDelete(map, MapperConst.OwnerHouseRelation_Mapper + ".deleteOwnerHouseRelation");
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
			return false;
		}
	}

	@Override
	public boolean addOwnerHouseRelation(OwnerHouseRelation ownerHouseRelation) {
		int count = 0;
		try {
			count = ownerHouseRelationBaseDaoImpl.insert(ownerHouseRelation, MapperConst.OwnerHouseRelation_Mapper + ".addOwnerHouseRelation");
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
		}
		return count > 0 ? true : false;
	}

	@Override
	public OwnerHouseRelation queryOwnerHouseRelation(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		OwnerHouseRelation ownerHouseRelation = null;
		try {
			ownerHouseRelation = ownerHouseRelationBaseDaoImpl.selectOne(MapperConst.OwnerHouseRelation_Mapper + ".qureyOwnerHouseRelation", map);
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
		}
		return ownerHouseRelation;
	}
	
}
