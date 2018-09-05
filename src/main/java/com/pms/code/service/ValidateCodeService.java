package com.pms.code.service;

import java.util.HashMap;

import com.pms.code.entity.base.ValidateCode;

public interface ValidateCodeService {
	
	boolean insertValidateCode(HashMap<String,Object> map);
	
	ValidateCode queryValidateCodeInfo(String userName,String createtime);
}
