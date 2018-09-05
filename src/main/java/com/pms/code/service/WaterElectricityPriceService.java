package com.pms.code.service;

import java.util.HashMap;
import java.util.List;

import com.pms.code.entity.base.WaterElectricityPrice;

public interface WaterElectricityPriceService {
	
	List<WaterElectricityPrice> queryWaterElectricityPrice(HashMap<String,Object> map);
	
	boolean updateWaterElectricityPrice(HashMap<String,Object> map);
	
	boolean updateWaterElectricityPriceByWep(HashMap<String,Object> map);
	
	boolean insertWaterElectricityPrice(WaterElectricityPrice waterElectricityPrice);
	
	WaterElectricityPrice queryWaterElectricityPriceByWep(WaterElectricityPrice waterElectricityPrice);
	
	
	/*int queryWaterElectricityPriceTotal(HashMap<String,Object> map);*/
	
	/*
	boolean insertWaterElectricityPrice(WaterElectricityPrice waterElectricityPrice);*/
}
