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
import com.pms.code.entity.base.ConvenienceInfo;
import com.pms.code.exception.DAOException;
import com.pms.code.service.ConvenienceInfoService;
import com.pms.code.util.MapperConst;
import com.pms.code.util.Page;
import com.thoughtworks.xstream.mapper.Mapper;

@Transactional
@Service("ConvenienceInfoServciceImpl")
public class ConvenienceInfoServciceImpl implements ConvenienceInfoService{
	private Logger logger = LoggerFactory.getLogger(ConvenienceInfoServciceImpl.class);
	
	@Autowired
	private BaseDao<ConvenienceInfo,Serializable> convenienceInfoBaseDaoImpl; 
	
	@Autowired
	private BaseDao<Integer,Serializable> countConvenienceInfoBaseDaoImpl; 
	
	/**
	 * 查询便民信息
	 */
	@Override
	public HashMap<String, Object> queryConvenienceInfo(HashMap<String, Object> paramMap) {
		Integer pageIndex=(Integer) paramMap.get("pageIndex");
		Integer pageSize=(Integer) paramMap.get("pageSize");
		int convenienceInfoTotal = queryConvenienceInfoTotal(paramMap);
		int startCount=(pageIndex-1)*pageSize;
		paramMap.put("startCount",startCount);
		Page<ConvenienceInfo> page=new Page<ConvenienceInfo>(pageSize,pageIndex);
		page.setRecord(convenienceInfoTotal);
		List<ConvenienceInfo> list=null;
		 try {
			list=convenienceInfoBaseDaoImpl.selectListPaging(paramMap,MapperConst.ConvenienceInfo_Mpper+".queryConvenienceInfo");
			page.setHouseList(list);
			paramMap.put("page",page);
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
		}
		return paramMap;
	}

	/**
	 * 查询便民信息总数
	 */
	@Override
	public int queryConvenienceInfoTotal(HashMap<String,Object> map) {
		int count=0;
		try {
			count=countConvenienceInfoBaseDaoImpl.select(map,MapperConst.ConvenienceInfo_Mpper+".queryConvenienceInfoTotal");
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
		}
		return count;
	}

	/**
	 * 增加便民信息
	 */
	@Override
	public boolean insertConvenienceInfo(ConvenienceInfo convenienceInfo) {
		int count=0;
		 try {
			count=convenienceInfoBaseDaoImpl.insert(convenienceInfo,MapperConst.ConvenienceInfo_Mpper+".insertConvenienceInfo");
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
		}
		return count>0?true:false;
	}

	/**
	 * 查询便民信息通过id
	 */
	@Override
	public ConvenienceInfo queryConvenienceInfoById(int id) {
		ConvenienceInfo convenienceInfo=null;
		try {
			convenienceInfo=convenienceInfoBaseDaoImpl.select(id,MapperConst.ConvenienceInfo_Mpper+".queryConvenienceInfoById");
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
		}
		return convenienceInfo;
	}

	/**
	 * 修改便民信息
	 */
	@Override
	public boolean updateConvenienceInfo(ConvenienceInfo convenienceInfo) {
		int count=0;
		try {
			count=convenienceInfoBaseDaoImpl.update(convenienceInfo,MapperConst.ConvenienceInfo_Mpper+".updateConvenienceInfo");
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
		}
		return count>0?true:false;
	}

	/**
	 * 删除便民信息
	 */
	@Override
	public boolean deleteConvenienceInfo(int[] id) {
		int count=0;
		try {
			count=convenienceInfoBaseDaoImpl.delete(id,MapperConst.ConvenienceInfo_Mpper+".deleteConvenienceInfo");
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
		}
		return count>0?true:false;
	}
 
	/**
	 * 查询便民信息:公司登录
	 */
	@Override
	public HashMap<String, Object> queryConvenienceInfoCompany(HashMap<String, Object> paramMap) {
		Integer pageIndex=(Integer) paramMap.get("pageIndex");
		Integer pageSize=(Integer) paramMap.get("pageSize");
		int convenienceInfoTotal = queryConvenienceInfoTotalCompany(paramMap);
		int startCount=(pageIndex-1)*pageSize;
		paramMap.put("startCount",startCount);
		Page<ConvenienceInfo> page=new Page<ConvenienceInfo>(pageSize,pageIndex);
		page.setRecord(convenienceInfoTotal);
		List<ConvenienceInfo> list=null;
		 try {
			list=convenienceInfoBaseDaoImpl.selectListPaging(paramMap,MapperConst.ConvenienceInfo_Mpper+".queryConvenienceInfoCompany");
			page.setHouseList(list);
			paramMap.put("page",page);
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
		}
		return paramMap;
	}

	/**
	 * 查询便民信息总数:公司登录
	 */
	@Override
	public int queryConvenienceInfoTotalCompany(HashMap<String, Object> map) {
		int count=0;
		try {
			count=countConvenienceInfoBaseDaoImpl.select(map,MapperConst.ConvenienceInfo_Mpper+".queryConvenienceInfoTotalCompany");
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
		}
		return count;
	}
}
