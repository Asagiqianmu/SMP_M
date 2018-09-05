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
import com.pms.code.entity.base.WaterElectricityPrice;
import com.pms.code.exception.DAOException;
import com.pms.code.service.WaterElectricityPriceService;
import com.pms.code.util.MapperConst;
import com.pms.code.util.Page;
import com.thoughtworks.xstream.mapper.Mapper;

@Service(value="waterElectricityPriceServiceImpl")
@Transactional
public class WaterElectricityPriceServiceImpl implements WaterElectricityPriceService{
	private Logger logger = LoggerFactory.getLogger(ValidateCodeServiceImpl.class);
	
	@Autowired
	private BaseDao<WaterElectricityPrice, Serializable> waterElectricityPriceBaseDaoImpl;
	
	@Autowired
	private BaseDao<HashMap<String,Object>, Serializable> mapWaterElectricityPriceBaseDaoImpl;
	
	@Override
	public List<WaterElectricityPrice> queryWaterElectricityPrice(HashMap<String, Object> map) {
		try {
			return waterElectricityPriceBaseDaoImpl.selectListPaging(map,MapperConst.WaterElectricityPrice_Mapper+".queryWaterElectricityPrice");
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
			return null;
		}
	}

	@Override
	public boolean updateWaterElectricityPrice(HashMap<String, Object> map) {
		int count=0;
		try {
			count=mapWaterElectricityPriceBaseDaoImpl.update(map,MapperConst.WaterElectricityPrice_Mapper+".updateWaterElectricityPrice");
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
		}
		return count>0?true:false;
	}

	@Override
	public boolean insertWaterElectricityPrice(WaterElectricityPrice waterElectricityPrice) {
		int count=0;
		 try {
			count=waterElectricityPriceBaseDaoImpl.insert(waterElectricityPrice,MapperConst.WaterElectricityPrice_Mapper+".insertWaterElectricityPrice");
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
		}
		return count>0?true:false;
	}

	@Override
	public WaterElectricityPrice queryWaterElectricityPriceByWep(WaterElectricityPrice waterElectricityPrice) {
		try {
			return waterElectricityPriceBaseDaoImpl.select(waterElectricityPrice,MapperConst.WaterElectricityPrice_Mapper+".queryWaterElectricityPriceByWep");
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
			return null;
		}
	}

	@Override
	public boolean updateWaterElectricityPriceByWep(HashMap<String, Object> map) {
		int count=0;
		try {
			count= mapWaterElectricityPriceBaseDaoImpl.update(map,MapperConst.WaterElectricityPrice_Mapper+".updateWaterElectricityPriceByWep");
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
		}
		return count>0?true:false;
	} 
}
