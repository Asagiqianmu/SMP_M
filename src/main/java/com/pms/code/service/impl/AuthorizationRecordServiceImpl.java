package com.pms.code.service.impl;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pms.code.dao.BaseDao;
import com.pms.code.entity.base.AuthorizationRecord;
import com.pms.code.exception.DAOException;
import com.pms.code.service.AuthorizationRecordService;
import com.pms.code.util.MapperConst;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@Service("authorizationRecordServiceImpl")
public class AuthorizationRecordServiceImpl implements AuthorizationRecordService {

	private Logger logger = LoggerFactory.getLogger(AuthorizationRecordServiceImpl.class);

	@Autowired
	private BaseDao<AuthorizationRecord, Serializable> authorizationRecordBaseDaoImpl;

	@Override
	public boolean saveAuthorizationRecord(AuthorizationRecord authorizationRecord) {
		int i = 0;
		try {
			i = authorizationRecordBaseDaoImpl.insert(authorizationRecord, MapperConst.AuthorizationRecord_Mapper + ".insertAuthorizationRecord");
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i > 0 ? true : false;
	}

	@Override
	public List<AuthorizationRecord> findAuthorizationRecordByDevice(String deviceId) {
		// TODO Auto-generated method stub
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("device_id", deviceId);
			return (List<AuthorizationRecord>) authorizationRecordBaseDaoImpl.selectListPoJo(map, MapperConst.AuthorizationRecord_Mapper + ".findAuthorizationRecordInfoByDeviceID");
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
			return null;
		}
	}

}
