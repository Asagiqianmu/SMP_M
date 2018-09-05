package com.pms.code.service;

import java.util.List;

import com.pms.code.entity.base.AuthorizationRecord;

public interface AuthorizationRecordService {

	public boolean saveAuthorizationRecord(AuthorizationRecord authorizationRecord);
	
	public List<AuthorizationRecord> findAuthorizationRecordByDevice(String deviceId);
}
