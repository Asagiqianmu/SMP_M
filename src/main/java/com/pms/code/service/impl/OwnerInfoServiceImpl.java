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
import com.pms.code.entity.base.OwnerInfo;
import com.pms.code.exception.DAOException;
import com.pms.code.service.OwnerInfoService;
import com.pms.code.util.MapperConst;
import com.pms.code.util.Page;

@Transactional
@Service("ownerInfoServiceImpl")
public class OwnerInfoServiceImpl implements OwnerInfoService {
	private Logger logger = LoggerFactory.getLogger(SysNoticeServiceImpl.class);

	@Autowired
	private BaseDao<OwnerInfo, Serializable> ownerInfoBaseDaoImpl;

	@Autowired
	private BaseDao<Integer, Serializable> countownerInfoBaseDaoImpl;

	@Autowired
	private BaseDao<HashMap<String, Object>, Serializable> mapOwnerInfoBaseDaoImpl;

	/**
	 * 删除所有者信息通过手机号
	 * 
	 * @param telphones
	 * @return
	 */
	@Override
	public boolean deleteOwnerInfoByIds(int[] ids) {
		int count = 0;
		try {
			count = ownerInfoBaseDaoImpl.delete(ids, MapperConst.OwnerInfo_Mapper + ".deleteOwnerInfoByIds");
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
		}
		return count > 0 ? true : false;
	}

	/**
	 * 增加业主信息
	 */
	@Override
	public boolean addOwnerInfo(OwnerInfo ownerInfo) {
		int count = 0;
		try {
			count = ownerInfoBaseDaoImpl.insert(ownerInfo, MapperConst.OwnerInfo_Mapper + ".addOwnerInfo");
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
		}
		return count > 0 ? true : false;
	}

	/**
	 * 根据业主全信息查询
	 */
	@Override
	public OwnerInfo queryOwnerInfoByOwnerInfo(OwnerInfo ownerInfo) {
		try {
			return ownerInfoBaseDaoImpl.select(ownerInfo, MapperConst.OwnerInfo_Mapper + ".queryOwnerInfoByInfo");
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
			return null;
		}
	}

	/**
	 * 修改业主信息
	 */
	@Override
	public boolean updateOwnerInfo(HashMap<String, Object> paramMap) {
		int count = 0;
		try {
			count = mapOwnerInfoBaseDaoImpl.update(paramMap, MapperConst.OwnerInfo_Mapper + ".updateOwnerInfo");
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
		}
		return count > 0 ? true : false;
	}

	/**
	 * 查询业主信息
	 */
	@Override
	public HashMap<String, Object> queryOwnerInfo(HashMap<String, Object> paramMap) {
		List<OwnerInfo> userList = null;
		Integer pageIndex = (Integer) paramMap.get("pageIndex");
		Integer pageSize = (Integer) paramMap.get("pageSize");
		// 用户总数
		int userTotal = queryOwnerInfoTotal(paramMap);
		Page<OwnerInfo> page = new Page<OwnerInfo>(pageSize, pageIndex);
		page.setRecord(userTotal);
		int startCount = (pageIndex - 1) * pageSize;
		paramMap.put("startCount", startCount);
		try {
			userList = ownerInfoBaseDaoImpl.selectListPaging(paramMap, MapperConst.OwnerInfo_Mapper + ".queryOwnerInfoList");
			page.setHouseList(userList);
			paramMap.put("page", page);
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
		}
		return paramMap;
	}

	/**
	 * 查询业主总数
	 */
	@Override
	public int queryOwnerInfoTotal(HashMap<String, Object> paramMap) {
		int count = 0;
		try {
			count = countownerInfoBaseDaoImpl.select(paramMap, MapperConst.OwnerInfo_Mapper + ".queryOwnerInfoTotal");
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
		}
		return count;
	}

	/**
	 * 查询业主信息:公司
	 */
	@Override
	public HashMap<String, Object> queryOwnerInfoCompany(HashMap<String, Object> paramMap) {
		List<OwnerInfo> listPaging = null;
		Integer pageSize = (Integer) paramMap.get("pageSize");
		Integer pageIndex = (Integer) paramMap.get("pageIndex");
		Page<OwnerInfo> page = new Page<OwnerInfo>(pageSize, pageIndex);
		// 查询公告总数
		int total = queryOwnerInfoTotalCompany(paramMap);
		page.setRecord(total);
		// 计算查询的起始位置
		int startCount = (pageIndex - 1) * pageSize;
		paramMap.put("startCount", startCount);
		try {
			listPaging = ownerInfoBaseDaoImpl.selectListPaging(paramMap, MapperConst.OwnerInfo_Mapper + ".queryOwnerInfoListCompany");
			page.setHouseList(listPaging);
			paramMap.put("page", page);
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
		}
		return paramMap;
	}

	/**
	 * 查询业主总数:公司
	 */
	@Override
	public int queryOwnerInfoTotalCompany(HashMap<String, Object> map) {
		int count = 0;
		try {
			count = countownerInfoBaseDaoImpl.select(map, MapperConst.OwnerInfo_Mapper + ".queryOwnerInfoTotalCompany");
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
		}
		return count;
	}
	

	/**
	 * 查询业主通过id
	 */
	@Override
	public OwnerInfo queryOwnerInfoById(int id) {
		OwnerInfo ownerInfo = null;
		try {
			ownerInfo = ownerInfoBaseDaoImpl.select(id, MapperConst.OwnerInfo_Mapper + ".queryOwnerInfoById");
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
		}
		return ownerInfo;
	}

	@Override
	public boolean updateOwnerInfoIsliving(HashMap<String, Object> map) {
		int count = 0;
		try {
			count = mapOwnerInfoBaseDaoImpl.update(map, MapperConst.OwnerInfo_Mapper + ".updateOwnerInfoIsliving");
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
		}
		return count > 0 ? true : false;
	}

	@Override
	public List<OwnerInfo> queryOwnerInfoByPidPmid(HashMap<String,Object> map) {
		try {
			return ownerInfoBaseDaoImpl.selectListPaging(map,MapperConst.OwnerInfo_Mapper+".queryOwnerInfoByPidPmid");
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
			return null;
		}
	}
}
