package com.pms.code.service.impl;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.pms.code.dao.BaseDao;
import com.pms.code.entity.base.HouseType;
import com.pms.code.exception.DAOException;
import com.pms.code.service.HouseTypeService;
import com.pms.code.util.MapperConst;

@Service
@Transactional
public class HouseTypeServiceImpl implements HouseTypeService{
	private Logger logger = LoggerFactory.getLogger(HouseTypeServiceImpl.class);
	
	@Autowired
	private BaseDao<HouseType,Serializable> houseTypeBaseDaoImpl;

	@Override
	public List<HouseType> houseTypeList() {
		List<HouseType> houseTypes=null;
		try {
			houseTypes=houseTypeBaseDaoImpl.selectListPaging(null,MapperConst.HouseType_Mapper+".houseTypeList");
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
			e.printStackTrace();
		}
		return houseTypes;
	}  
}
