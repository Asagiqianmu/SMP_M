package com.pms.code.service;

import java.util.HashMap;

import com.pms.code.entity.base.OwnerPmd;


/**
 * 业主与小区物业管理处关联关系接口
 * @author dengfei E-mail:dengfei200857@163.com
 * @time 2018年3月30日 下午1:47:05
 */
public interface OwnerPmdService {
	
	/**
	 * 删除关联
	 * @author dengfei E-mail:dengfei200857@163.com
	 * @time 2018年3月30日 下午1:48:42
	 * @param map
	 * @return
	 */
	boolean deleteOwnerPmd(HashMap<String,Object> map);
	
	/**
	 * 增加关联
	 * @author dengfei E-mail:dengfei200857@163.com
	 * @time 2018年3月30日 下午1:49:09
	 * @param map
	 * @return
	 */
	boolean addOwnerPmd(OwnerPmd ownerPmd);
	
	
	/**
	 * 查询关联
	 * @author dengfei E-mail:dengfei200857@163.com
	 * @time 2018年3月30日 下午1:49:44
	 * @param map
	 * @return
	 */
	OwnerPmd queryOwnerPmd(HashMap<String,Object> map);
    
}
