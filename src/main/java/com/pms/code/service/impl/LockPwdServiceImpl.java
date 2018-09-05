package com.pms.code.service.impl;

import java.io.Serializable;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pms.code.dao.BaseDao;
import com.pms.code.entity.base.LockPwd;
import com.pms.code.exception.DAOException;
import com.pms.code.service.LockPwdService;
import com.pms.code.util.MapperConst;

@Service(value="lockPwdServiceImpl")
@Transactional
public class LockPwdServiceImpl implements LockPwdService{
	private Logger logger=LoggerFactory.getLogger(LockPwdServiceImpl.class);
	
	@Autowired
	private BaseDao<LockPwd, Serializable> lockPwdBaseDaoImpl;

	/**
	 * 新增门锁密码
	 */
	@Override
	public boolean insertLockPwd(LockPwd lockPwd) {
		int count=0;
		try {
			count=lockPwdBaseDaoImpl.insert(lockPwd,MapperConst.LockPwd_Mapper+".insertLockPwd");
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
		}
		return count>0?true:false;
	}

	/**
	 * 修改门锁密码
	 */
	@Override
	public boolean updateLockPwd(LockPwd lockPwd) {
		int count=0;
		try {
			count=lockPwdBaseDaoImpl.update(lockPwd,MapperConst.LockPwd_Mapper+".updateLockPwd");
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
		}
		return count>0?true:false;
	}

	@Override
	public LockPwd queryLockPwdById(int id) {
		try {
			HashMap<String,Object> map=new HashMap<String,Object>();
			map.put("id",id);
			return lockPwdBaseDaoImpl.selectOne(MapperConst.LockPwd_Mapper+".queryLockPwdById",map);
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
			return null;
		} 
	}

	@Override
	public boolean deleteLockPwd(int id) {
		int count=0;
		try {
			 HashMap<String,Object> map=new HashMap<String,Object>();
			 map.put("doorid",id);
			 count=lockPwdBaseDaoImpl.delete(map,MapperConst.LockPwd_Mapper+".deleteLockPwd");
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
		}
		return count>0?true:false;
	}
}
