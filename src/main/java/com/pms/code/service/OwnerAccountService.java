package com.pms.code.service;

import java.util.HashMap;

import com.pms.code.entity.base.OwnerAccount;
import com.pms.code.entity.base.OwnerPaycat;

/**
 * 账户接口
 * @author Dell
 *
 */
public interface OwnerAccountService {
	
    /**
     * 查询业主通过id
     * @param id
     * @return
     */
    OwnerAccount queryOwnerAccountById(int id);
    
	
	/**
	 * 增加账户信息
	 * @param ownerAccount
	 * @return
	 */
	boolean addOwnerAccount(OwnerAccount ownerAccount);

	/**
	 * 根据手机号查询业主信息
	 * @param telphone
	 * @return
	 */
	OwnerAccount queryOwnerAccountByTelphone(HashMap<String,Object> map);
}
