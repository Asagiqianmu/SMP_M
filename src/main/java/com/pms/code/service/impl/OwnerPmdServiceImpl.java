package com.pms.code.service.impl;

import java.io.Serializable;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pms.code.dao.BaseDao;
import com.pms.code.entity.base.OwnerPmd;
import com.pms.code.exception.DAOException;
import com.pms.code.service.OwnerPmdService;
import com.pms.code.util.MapperConst;

@Transactional
@Service("ownerPmdServiceImpl")
public class OwnerPmdServiceImpl implements OwnerPmdService {

	private Logger logger = LoggerFactory.getLogger(OwnerPmdServiceImpl.class);

	@Autowired
	private BaseDao<OwnerPmd, Serializable> ownerPmdBaseDaoImpl;

	@Autowired
	private BaseDao<HashMap<String, Object>, Serializable> mapOwnerPmdBaseDaoImpl;

	@Override
	public boolean deleteOwnerPmd(HashMap<String, Object> map) {
		try {
			return mapOwnerPmdBaseDaoImpl.batchDelete(map, MapperConst.OwnerPmd_Mapper + ".deleteOwnerPmd");
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
			return false;
		}
	}

	@Override
	public boolean addOwnerPmd(OwnerPmd ownerPmd) {
		int count = 0;
		try {
			count = ownerPmdBaseDaoImpl.insert(ownerPmd, MapperConst.OwnerPmd_Mapper + ".addOwnerPmd");
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
		}
		return count > 0 ? true : false;
	}

	@Override
	public OwnerPmd queryOwnerPmd(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		OwnerPmd ownerPmd = null;
		try {
			ownerPmd = ownerPmdBaseDaoImpl.selectOne(MapperConst.OwnerPmd_Mapper + ".qureyOwnerPmd", map);
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
		}
		return ownerPmd;
	}
	
}
