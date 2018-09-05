package com.pms.code.service;

import com.pms.code.entity.base.HouseID;

public interface HouseIDService {
	HouseID queryHouseID(String houseid);
	
	boolean insertHouseID(HouseID houseID);
	
	HouseID queryHouseIDById(int infoid);
}
