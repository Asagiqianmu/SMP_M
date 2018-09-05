package com.pms.code.service.impl;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pms.code.dao.BaseDao;
import com.pms.code.entity.base.SysNotice;
import com.pms.code.entity.base.UnlockingKey;
import com.pms.code.exception.DAOException;
import com.pms.code.service.UnlockingKeyService;
import com.pms.code.util.MapperConst;
import com.pms.code.util.Page;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@Service("unlockingKeyServiceImpl")
public class UnlockingKeyServiceImpl implements UnlockingKeyService{
	
	private Logger logger = LoggerFactory.getLogger(UnlockingKeyServiceImpl.class);
	
	@Autowired
	private BaseDao<UnlockingKey, Serializable> unlockingKeyBaseDaoImpl;
	
	@Autowired
	private BaseDao<Integer, Serializable> countUnlockingKeyBaseDaoImpl;
	
	/**
	 * 查找门锁信息,根据门锁id
	 */
	@Override
	public UnlockingKey findUnlockingKeyByID(int id) {
		try {
			Map<String,Object> map= new HashMap<String,Object>();
			map.put("id", id);
			return unlockingKeyBaseDaoImpl.selectOne(MapperConst.UnlockingKey_Mapper+".findKeyInfoByID", map);
			} catch (DAOException e) {
				logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
				return null;
			}
	}


	/**
	 * 插入门锁信息
	 */
	@Override
	public boolean insertUnlockingKey(UnlockingKey unlockingKey) {
		int count=0;
		try {
			count=unlockingKeyBaseDaoImpl.insert(unlockingKey,MapperConst.UnlockingKey_Mapper+".insertUnlockingKey");
		}catch (DAOException e) {
			e.printStackTrace();
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
		}
		return count>0?true:false;
	}


	/**
	 * 查询门锁信息
	 */
	@Override
	public HashMap<String,Object> queryUnlockingKey(HashMap<String,Object> paramMap) {
		List<UnlockingKey> listPaging=null;
		Integer pageSize = (Integer) paramMap.get("pageSize");
		Integer pageIndex=(Integer) paramMap.get("pageIndex");
		Page<UnlockingKey> page=new Page<UnlockingKey>(pageSize,pageIndex);
//		查询公告总数
		int total = queryUnlockingKeyTotal(paramMap); 
		page.setRecord(total);
//		计算查询的起始位置
		int startCount=(pageIndex-1)*pageSize;
		paramMap.put("startCount",startCount);
		try {
			listPaging= unlockingKeyBaseDaoImpl.selectListPaging(paramMap,MapperConst.UnlockingKey_Mapper+".queryUnlockingKey");
			page.setHouseList(listPaging);
			paramMap.put("page",page); 
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
		} 
		return paramMap; 
	}

	/**
	 * 查询门锁信息总数
	 */
	@Override
	public int queryUnlockingKeyTotal(HashMap<String, Object> map) {
		int count=0;
		try {
			count=countUnlockingKeyBaseDaoImpl.select(map,MapperConst.UnlockingKey_Mapper+".queryUnlockingKeyTotal");
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
		}
		return count;
	}

	/**
	 * 查询门锁信息通过houseid
	 */
	@Override
	public List<UnlockingKey> queryUnlockingKeyByHouseId(HashMap<String, Object> map) {
		 try {
			return unlockingKeyBaseDaoImpl.selectListPaging(map,MapperConst.UnlockingKey_Mapper+".queryUnlockingKeyByHouseId");
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
			return null;
		}
	}

	/**
	 * 查询所有门锁信息
	 */
	@Override
	public List<UnlockingKey> queryAllUnlockingKey() {
		try {
			return  unlockingKeyBaseDaoImpl.selectListPaging(null,MapperConst.UnlockingKey_Mapper+".queryAllUnlockingKey");
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
			return null;
		} 
	} 
}
