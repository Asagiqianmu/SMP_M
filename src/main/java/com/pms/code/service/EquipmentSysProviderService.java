package com.pms.code.service;

import java.util.HashMap;

import com.pms.code.entity.base.EquipmentSysProvider;

public interface EquipmentSysProviderService {

	public EquipmentSysProvider findEquipmentSysProviderByID(int id);
	
	HashMap<String,Object> queryEquipmentSysProviderInfo(HashMap<String,Object> map);
	
	int queryEquipmentSysProviderInfoTotal();
	
	boolean updateEquipmentSysProviderInfo(EquipmentSysProvider equipmentSysProvider);
	
	boolean insertEquipmentSysProviderInfo(EquipmentSysProvider equipmentSysProvider);
	
	boolean deleteEquipmentSysProviderInfo(int [] ids);
}
