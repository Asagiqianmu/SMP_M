package com.pms.code.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.pms.code.dao.BaseDao;
import com.pms.code.entity.base.HouseID;
import com.pms.code.exception.DAOException;
import com.pms.code.service.HouseIDService;
import com.pms.code.util.MapperConst;

@Service(value="houseIDServiceImpl")
@Transactional
public class HouseIDServiceImpl  implements HouseIDService{
	private Logger logger = LoggerFactory.getLogger(ConvenienceInfoServciceImpl.class);
	
	@Autowired
	private BaseDao<HouseID,Serializable> houseIDBaseDaoImpl; 
	 
	
	@Override
	public HouseID queryHouseID(String houseid) {
		try {
			HashMap<String,Object> map=new HashMap<String,Object>();
			map.put("houseid",houseid);
			return houseIDBaseDaoImpl.selectOne(MapperConst.HouseID_Mapper+".queryHouseID",map);
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
			return null;
		} 
	}

	@Override
	public boolean insertHouseID(HouseID houseID) {
		int count=0;
		try {
		 	count=houseIDBaseDaoImpl.insert(houseID,MapperConst.HouseID_Mapper+".insertHouseID");
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
		}
		return count>0?true:false;
	}

	@Override
	public HouseID queryHouseIDById(int infoid) {
		try {
			return houseIDBaseDaoImpl.select(infoid,MapperConst.HouseID_Mapper+".queryHouseIDById");
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
			return null;
		}
	}

}
