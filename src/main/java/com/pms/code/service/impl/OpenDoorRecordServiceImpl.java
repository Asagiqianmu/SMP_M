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
import com.pms.code.entity.base.OpenDoorRecord;
import com.pms.code.exception.DAOException;
import com.pms.code.service.OpenDoorRecoedService;
import com.pms.code.util.MapperConst;
import com.pms.code.util.Page;

/**
 * 开门记录实现类
 * @author Dell
 *
 */
@Service("openDoorRecordServiceImpl")
@Transactional
public class OpenDoorRecordServiceImpl  implements OpenDoorRecoedService{
	private Logger logger = LoggerFactory.getLogger(OpenDoorRecordServiceImpl.class);
	
	@Autowired
	private BaseDao<OpenDoorRecord, Serializable> openDoorRecordServiceImpl;
	
	@Autowired
	private BaseDao<Integer, Serializable> countOpenDoorRecordServiceImpl;
	/**
	 * 查询开门记录:用户
	 */
	@Override
	public HashMap<String, Object> queryOpenDoorRecord(HashMap<String, Object> paramMap) {
		Integer  pageIndex=(Integer) paramMap.get("pageIndex");
		Integer pageSize=(Integer) paramMap.get("pageSize");
//		开门总数
		int openDoorRecordTotal= queryOpenDoorRecordTotal(paramMap);
		int startCount=(pageIndex-1)*pageSize;
		paramMap.put("startCount",startCount);
		
		Page<OpenDoorRecord> page=new Page(pageSize,pageIndex);
		page.setRecord(openDoorRecordTotal);
		List<OpenDoorRecord> list=null;
		try {
			list=openDoorRecordServiceImpl.selectListPaging(paramMap,MapperConst.OpenDoorRecord_Mapper+".queryOpenDoorRecord");
			page.setHouseList(list);
			paramMap.put("page",page);
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
		}
		return paramMap;
	}
	
	/**
	 * 查询开门总数:用户
	 */
	@Override
	public int queryOpenDoorRecordTotal(HashMap<String, Object> paramMap) {
		int count=0;
		try {
			count=countOpenDoorRecordServiceImpl.select(paramMap,MapperConst.OpenDoorRecord_Mapper+".queryOpenDoorRecordTotal");
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
		}
		
		return count;
	}

	/**
	 * 查看开门记录:公司
	 */
	@Override
	public HashMap<String, Object> queryOpenDoorRecordCompany(HashMap<String, Object> paramMap) {
		Integer  pageIndex=(Integer) paramMap.get("pageIndex");
		Integer pageSize=(Integer) paramMap.get("pageSize");
//		开门总数
		int openDoorRecordTotal= queryOpenDoorRecordTotalCompany(paramMap);
		int startCount=(pageIndex-1)*pageSize;
		paramMap.put("startCount",startCount);
		
		Page<OpenDoorRecord> page=new Page(pageSize,pageIndex);
		page.setRecord(openDoorRecordTotal);
		List<OpenDoorRecord> list=null;
		try {
			list=openDoorRecordServiceImpl.selectListPaging(paramMap,MapperConst.OpenDoorRecord_Mapper+".queryOpenDoorRecordCompany");
			page.setHouseList(list);
			paramMap.put("page",page);
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
		}
		return paramMap;
	}

	/**
	 * 查看开门记录总数:公司
	 */
	@Override
	public int queryOpenDoorRecordTotalCompany(HashMap<String, Object> paramMap) {
		int count=0;
		try {
			count=countOpenDoorRecordServiceImpl.select(paramMap,MapperConst.OpenDoorRecord_Mapper+".queryOpenDoorRecordTotalCompany");
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
		}
		
		return count;
	} 
}
