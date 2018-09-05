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
import com.pms.code.entity.base.FeedBack;
import com.pms.code.exception.DAOException;
import com.pms.code.service.FeedBackService;
import com.pms.code.util.MapperConst;
import com.pms.code.util.Page;

@Transactional
@Service("FeedBacServiceImpl")
public class FeedBackServiceImpl implements FeedBackService{
	private Logger logger = LoggerFactory.getLogger(SysNoticeServiceImpl.class);
	
	@Autowired
	private BaseDao<FeedBack, Serializable> feedBackBaseDaoImpl;
	
	@Autowired
	private BaseDao<Integer, Serializable> countFeedBackBaseDaoImpl;
	
	/**
	 * 投诉管理列表
	 */
	@Override
	public HashMap<String,Object> queryFeedBackInfo(HashMap<String,Object> paramMap) {
		Integer pageIndex=(Integer) paramMap.get("pageIndex");
		Integer pageSize=(Integer) paramMap.get("pageSize");
//		投诉总数
		int feedBackInfoTotal = queryFeedBackInfoTotal();
		int startCount=(pageIndex-1)*pageSize;
		Page<FeedBack> page = new Page(pageSize, pageIndex);
		page.setRecord(feedBackInfoTotal);
		paramMap.put("startCount",startCount);
		List<FeedBack> list=null;
		try {
			list=feedBackBaseDaoImpl.selectListPaging(paramMap,MapperConst.FeedBack_Mapper+".queryFeedBackInfo");
			page.setHouseList(list);
			paramMap.put("page",page);
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
		}
		return paramMap;
	}

	/**
	 * 查询投诉总数
	 */
	@Override
	public int queryFeedBackInfoTotal() {
		int count=0;
		try {
			count=countFeedBackBaseDaoImpl.count(0,MapperConst.FeedBack_Mapper+".queryFeedBackInfoTotal");
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
		}
		return count;
	}

	/**
	 * 根据id查询投诉信息
	 */
	@Override
	public FeedBack queryFeedBackInfoById(int id) {
		FeedBack feedBack=null;
		try {
			feedBack=feedBackBaseDaoImpl.select(id,MapperConst.FeedBack_Mapper+".queryFeedBackInfoById");
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
		}
		return feedBack;
	}

	/**
	 * 删除投诉信息
	 */
	@Override
	public boolean deleteFeedBack(int id) {
		int count=0;
		try {
		 	 count=feedBackBaseDaoImpl.delete(id,MapperConst.FeedBack_Mapper+".deleteFeedBack");
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
		}
		return count>0?true:false;
	} 
}
