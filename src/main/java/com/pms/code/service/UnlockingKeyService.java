package com.pms.code.service;

import java.util.HashMap;
import java.util.List;

import com.pms.code.entity.base.UnlockingKey;

public interface UnlockingKeyService {

	UnlockingKey findUnlockingKeyByID(int id);
	
	boolean insertUnlockingKey(UnlockingKey unlockingKey);
	
	HashMap<String,Object> queryUnlockingKey(HashMap<String,Object> map);
	
	int queryUnlockingKeyTotal(HashMap<String,Object> map);
	
	List<UnlockingKey> queryUnlockingKeyByHouseId(HashMap<String,Object> map);
	
	List<UnlockingKey> queryAllUnlockingKey();
}
