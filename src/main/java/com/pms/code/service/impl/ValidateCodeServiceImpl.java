package com.pms.code.service.impl;

import java.io.Serializable;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.code.dao.BaseDao;
import com.pms.code.entity.base.ValidateCode;
import com.pms.code.exception.DAOException;
import com.pms.code.service.ValidateCodeService;
import com.pms.code.util.MapperConst;

@Service("validateCodeServiceImpl")
public class ValidateCodeServiceImpl  implements ValidateCodeService{

	private Logger logger = LoggerFactory.getLogger(ValidateCodeServiceImpl.class);
	
	@Autowired
	private BaseDao<ValidateCode, Serializable>validateCodeServiceImpl;
	
	@Autowired
	private BaseDao<HashMap<String,Object>, Serializable> mapValidateCodeServiceImpl;
	
	/**
	 * 插入验证码
	 */
	@Override
	public boolean insertValidateCode(HashMap<String, Object> map) {
		int count=0;
		try { 
			count=mapValidateCodeServiceImpl.insert(map,MapperConst.validateCode_Mapper+".insertValidateCode");
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
		}
		return count>0?true:false;
	}

	/**
	 * 查询验证码信息
	 */
	@Override
	public ValidateCode queryValidateCodeInfo(String userName,String createtime) {
		try {
			HashMap<String,Object> map=new HashMap<String,Object>();
			map.put("user_name",userName);
			map.put("createtime",createtime);
			return validateCodeServiceImpl.select(map,MapperConst.validateCode_Mapper+".queryValidateCodeInfo");
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
			return null;
		}
	}

}
