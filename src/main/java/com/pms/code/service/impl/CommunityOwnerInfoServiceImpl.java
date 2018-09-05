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
import com.pms.code.entity.base.CommunityOwnerInfo;
import com.pms.code.entity.base.OwnerInfo;
import com.pms.code.exception.DAOException;
import com.pms.code.service.CommunityOwnerInfoService;
import com.pms.code.util.MapperConst;
import com.pms.code.util.Page;

/**
 * 小区+业主接口
 * 
 * @author Dell
 *
 */
@Transactional
@Service("CommunityOwnerInfoServiceImpl")
public class CommunityOwnerInfoServiceImpl implements CommunityOwnerInfoService {
	private Logger logger = LoggerFactory.getLogger(CommunityOwnerInfoServiceImpl.class);

	@Autowired
	private BaseDao<CommunityOwnerInfo, Serializable> communityownerInfoBaseDaoImpl;

	@Autowired
	private BaseDao<Integer, Serializable> countcommunityownerInfoBaseDaoImpl;
	
	/**
	 * 查询业主信息
	 */
	@Override
	public HashMap<String, Object> queryCommunityOwnerInfo(HashMap<String, Object> paramMap) {
		List<CommunityOwnerInfo> userList = null;
		Integer pageIndex = (Integer) paramMap.get("pageIndex");
		Integer pageSize = (Integer) paramMap.get("pageSize");
		// 用户总数
		Page<CommunityOwnerInfo> page = new Page<CommunityOwnerInfo>(pageSize, pageIndex);
		int startCount = (pageIndex - 1) * pageSize;
		paramMap.put("startCount", startCount);
		int userTotal = queryCommunityOwnerInfoTotal(paramMap);
		page.setRecord(userTotal);
		paramMap.put("startCount", startCount);
		try {
			userList = communityownerInfoBaseDaoImpl.selectListPaging(paramMap, MapperConst.CommunityOwnerInfo_Mapper + ".queryCommunityOwnerInfoList");
			page.setHouseList(userList);
			paramMap.put("page", page);
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
		}
		return paramMap;
	}
	
	/**
	 * 查询业主信息
	 * 
	 * @param pmid
	 * @return
	 */
/*	public List<CommunityOwnerInfo> queryCommunityOwnerInfoList(HashMap<String, Object> map) {
		try {
			return communityownerInfoBaseDaoImpl.selectListPaging(map, MapperConst.CommunityOwnerInfo_Mapper + ".queryCommunityOwnerInfoList");
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
			return null;
		}
	}*/

	@Override
	public int queryCommunityOwnerInfoTotal(HashMap<String, Object> map) {
		int count = 0;
		try {
			count = countcommunityownerInfoBaseDaoImpl.select(map,MapperConst.CommunityOwnerInfo_Mapper + ".queryCommunityOwnerInfoCount");
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return count;
	}
}
