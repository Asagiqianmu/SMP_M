package com.pms.code.service.impl;

import java.io.Serializable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.pms.code.dao.BaseDao;
import com.pms.code.entity.base.LockInfo;
import com.pms.code.exception.DAOException;
import com.pms.code.service.LockInfoService;
import com.pms.code.util.MapperConst;

@Service
@Transactional(value="lockInfoServiceImpl")
public class LockInfoServiceImpl implements LockInfoService{
	
	private Logger logger=LoggerFactory.getLogger(LockPwdServiceImpl.class);
	
	@Autowired
	private BaseDao<LockInfo, Serializable> lockInfoBaseDaoImpl;
	
	/**
	 * 插入门锁信息
	 */
	@Override
	public boolean insertLockInfo(LockInfo lockInfo) {
		int count=0;
		try {
			count=lockInfoBaseDaoImpl.insert(lockInfo,MapperConst.LockInfo_Mapper+".insertLockInfo");
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
		}
		return count>0?true:false;
	}
}
