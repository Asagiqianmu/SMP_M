package com.pms.code.service;

import java.util.HashMap;
import java.util.List;

import com.pms.code.entity.base.EnergyConsumptionDevice;

public interface EnergyConsumptionDeviceService {
	boolean insertEnergyConsumptionDevice(EnergyConsumptionDevice energyConsumptionDevice);
	
	EnergyConsumptionDevice  queryEnergyConsumptionDeviceByDeviceId(String deviceId);
	
	boolean updateEnergyConsumptionDevice(HashMap<String,Object> map);
	
	List<EnergyConsumptionDevice> queryEnergyConsumptionDeviceByDevice();
	
	List<EnergyConsumptionDevice> queryEnergyConsumptionDeviceByHouseId(HashMap<String,Object> map);
	
	HashMap<String,Object> queryEnergyConsumptionDeviceByHouseIds(HashMap<String,Object> map);
	
	int queryEnergyConsumptionDeviceTotal(HashMap<String,Object> map);
}
