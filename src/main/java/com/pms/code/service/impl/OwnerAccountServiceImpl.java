package com.pms.code.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.pms.code.dao.BaseDao;
import com.pms.code.entity.base.OwnerAccount;
import com.pms.code.entity.base.OwnerPaycat;
import com.pms.code.exception.DAOException;
import com.pms.code.service.OwnerAccountService;
import com.pms.code.util.MapperConst;

@Transactional
@Service("ownerAccountServiceImpl")
public class OwnerAccountServiceImpl implements OwnerAccountService {

	private Logger logger = LoggerFactory.getLogger(OwnerAccountServiceImpl.class);

	@Autowired
	private BaseDao<OwnerAccount, Serializable> ownerAccountBaseDaoImpl;

	/**
	 * 增加业主信息
	 */
	@Override
	public boolean addOwnerAccount(OwnerAccount ownerAccount) {
		int count = 0;
		try {
			count = ownerAccountBaseDaoImpl.insert(ownerAccount, MapperConst.OwnerAccount_Mapper + ".addOwnerAccount");
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
		}
		return count > 0 ? true : false;
	}


	/**
	 * 查询业主通过id
	 */
	@Override
	public OwnerAccount queryOwnerAccountById(int id) {
		OwnerAccount ownerAccount = null;
		try {
			ownerAccount = ownerAccountBaseDaoImpl.select(id, MapperConst.OwnerAccount_Mapper + ".queryOwnerAccountById");
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
		}
		return ownerAccount;
	}

	/**
	 * 根据手机号查询业主信息
	 */
	@Override
	public OwnerAccount queryOwnerAccountByTelphone(HashMap<String, Object> paramMap) {
		OwnerAccount ownerAccount = null;
		try {
			ownerAccount = ownerAccountBaseDaoImpl.select(paramMap, MapperConst.OwnerAccount_Mapper + ".queryOwnerAccountByTelphone");
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
		}
		return ownerAccount;
	}

}
