package com.pms.code.service.impl;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pms.code.dao.BaseDao;
import com.pms.code.entity.base.CostType;
import com.pms.code.exception.DAOException;
import com.pms.code.service.CostTypeService;
import com.pms.code.util.MapperConst;

@Service("costTypeServiceImpl")
@Transactional
public class CostTypeServiceImpl implements CostTypeService{
	
	private Logger logger = LoggerFactory.getLogger(CostTypeServiceImpl.class);
	
	@Autowired
	private BaseDao<CostType,Serializable> costTypeBaseDaoImpl; 

	/**
	 * 查询缴费类型
	 */
	@Override
	public List<CostType> costTypeList() {
		try {
			 return costTypeBaseDaoImpl.selectListPaging(null,MapperConst.costType_Mapper+".costTypeList");
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
			return null;
		} 
	}

}
