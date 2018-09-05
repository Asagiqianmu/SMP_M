package com.pms.code.service;

import java.util.HashMap;
import java.util.List;

import com.pms.code.entity.base.UseWaterElectricity;

public interface UseWaterElectricityService {

	public List<UseWaterElectricity> findUseWaterElectricityByUserIdAndMounth(int userId, int type, String mounth);
	
	boolean insertUseWaterElectricityInfo(UseWaterElectricity useWaterElectricity);
	
	List<UseWaterElectricity> queryUseWaterElectricity();
	
	boolean updateUseWaterElectricity(HashMap<String,Object> map);
}
