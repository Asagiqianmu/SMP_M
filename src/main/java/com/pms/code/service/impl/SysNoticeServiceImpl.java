package com.pms.code.service.impl;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import com.pms.code.dao.BaseDao;
import com.pms.code.entity.base.SysNotice;
import com.pms.code.exception.DAOException;
import com.pms.code.service.SysNoticeService;
import com.pms.code.util.DateUtil;
import com.pms.code.util.MapperConst;
import com.pms.code.util.Page;


@Transactional
@Service("sysNoticeServiceImpl")
public class SysNoticeServiceImpl  implements SysNoticeService{
	private Logger logger = LoggerFactory.getLogger(SysNoticeServiceImpl.class);
	
	@Autowired
	private BaseDao<SysNotice, Serializable> sysNoticeBaseDaoImpl;
	@Autowired
	private BaseDao<Integer, Serializable> countsysNoticeBaseDaoImpl;
	
	@Autowired
	private BaseDao<HashMap<String,Object>, Serializable> mapSysNoticeBaseDaoImpl;

	/**
	 * 分页查询公告列表:用户
	 */
	@Override
	public HashMap<String,Object> querySysNoticeList(HashMap<String,Object> paramMap) {
		List<SysNotice> listPaging=null;
		Integer pageSize = (Integer) paramMap.get("pageSize");
		Integer pageIndex=(Integer) paramMap.get("pageIndex");
		Page<SysNotice> page=new Page<SysNotice>(pageSize,pageIndex);
//		查询公告总数
		int total = querySysNoticeTotal(paramMap); 
		page.setRecord(total);
//		计算查询的起始位置
		int startCount=(pageIndex-1)*pageSize;
		paramMap.put("startCount",startCount);
		try {
			listPaging= sysNoticeBaseDaoImpl.selectListPaging(paramMap,MapperConst.SysNotice_Mapper+".querySysNoticeList");
			page.setHouseList(listPaging);
			paramMap.put("page",page); 
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
		} 
		return paramMap;
	}

	/**
	 * 保存公告修改
	 */
	@Override
	public boolean saveEditSysNotice(SysNotice sysNotice) {
		int count=0;
		try {
			count = sysNoticeBaseDaoImpl.update(sysNotice,MapperConst.SysNotice_Mapper+".saveEditSysNotice");
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
		}
		return count>0?true:false;
	}

	/**
	 * 删除公告
	 */
	@Override 
	public boolean deleteSysNotice(int []ids) {
		int count=0;
		try {
			count = sysNoticeBaseDaoImpl.delete(ids,MapperConst.SysNotice_Mapper+".deleteSysNotice");
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
		}
		return count>0?true:false;
	}

	/**
	 * 添加新公告
	 */
	@Override
	public boolean insertSysNotice(SysNotice sysNotice) {
		int count=0;
		try {
			count = sysNoticeBaseDaoImpl.insert(sysNotice,MapperConst.SysNotice_Mapper+".insertSysNotice");
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
		}
		return count>0?true:false;
	}

	/**
	 * 查询公告列表总数
	 */
	@Override
	public int querySysNoticeTotal(HashMap<String,Object> map) {
		int count=0;
		try {
			count =countsysNoticeBaseDaoImpl.select(map,MapperConst.SysNotice_Mapper+".querySysNoticeTotal");
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
		}
		return count;
	}

	/**
	 * 查询公告列表通过id
	 */
	@Override
	public SysNotice querySysNoticeById(int id) {
		SysNotice sysNotice=null;
		try {
			sysNotice= sysNoticeBaseDaoImpl.select(id,MapperConst.SysNotice_Mapper+".querySysNoticeById");
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
		}
		return sysNotice;
	}

	/**
	 * 查询公告列表:公司
	 */
	@Override
	public HashMap<String, Object> querySysNoticeListCompany(HashMap<String, Object> paramMap) {
		List<SysNotice> listPaging=null;
		Integer pageSize = (Integer) paramMap.get("pageSize");
		Integer pageIndex=(Integer) paramMap.get("pageIndex");
		Page<SysNotice> page=new Page<SysNotice>(pageSize,pageIndex);
//		查询公告总数
		int total = querySysNoticeTotal(paramMap); 
		page.setRecord(total);
//		计算查询的起始位置
		int startCount=(pageIndex-1)*pageSize;
		paramMap.put("startCount",startCount);
		try {
			listPaging= sysNoticeBaseDaoImpl.selectListPaging(paramMap,MapperConst.SysNotice_Mapper+".querySysNoticeListCompany");
			page.setHouseList(listPaging);
			paramMap.put("page",page); 
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
		} 
		return paramMap;
	}

	/**
	 * 查询总数:公司
	 */
	@Override
	public int querySysNoticeTotalCompany(HashMap<String, Object> map) {
		int count=0;
		try {
			count=countsysNoticeBaseDaoImpl.select(map,MapperConst.SysNotice_Mapper+".querySysNoticeTotalCompany");
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
		}
		return count;
	}

	/**
	 * 查询公告通过物业id
	 */
/*	@Override
	public HashMap<String,Object> querySysNoticeByPmid(HashMap<String,Object> paramMap) {
		List<SysNotice> listPaging=null;
		Integer pageSize = (Integer) paramMap.get("pageSize");
		Integer pageIndex=(Integer) paramMap.get("pageIndex");
		Integer pmid=(Integer) paramMap.get("pmid");
		Page<SysNotice> page=new Page<SysNotice>(pageSize,pageIndex);
 		查询公告总数
		int total = querySysNoticeTotalPmid(pmid); 
		page.setRecord(total);
 		计算查询的起始位置
		int startCount=(pageIndex-1)*pageSize;
		paramMap.put("startCount",startCount);
		try {
			listPaging= sysNoticeBaseDaoImpl.selectListPaging(paramMap,MapperConst.SysNotice_Mapper+".querySysNoticeByPmid");
			page.setHouseList(listPaging);
			paramMap.put("page",page); 
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
		} 
		return paramMap;
	}*/

	/**
	 * 查询公告总数通过pmid
	 */
/*	@Override
	public int querySysNoticeTotalPmid(int id) {
		try {
			return  countsysNoticeBaseDaoImpl.select(id,MapperConst.SysNotice_Mapper+".querySysNoticeTotalPmid");
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
			return 0;
		} 
	}*/
}
