package com.pms.code.service;

import java.util.HashMap;

/**
 * 开门记录接口
 * @author Dell
 *
 */
public interface OpenDoorRecoedService {
	
	/**
	 * 查询开门记录:用户
	 * @param map
	 * @return
	 */
	HashMap<String,Object> queryOpenDoorRecord(HashMap<String,Object> map);
	
	/**
	 * 查询开门记录总数:用户
	 * @param map
	 * @return
	 */
	int queryOpenDoorRecordTotal(HashMap<String,Object> map);
	
	/**
	 * 查看开门记录:公司
	 * @param map
	 * @return
	 */
	HashMap<String,Object> queryOpenDoorRecordCompany(HashMap<String,Object> map);
	
	/**
	 * 查看开门记录总数:公司
	 * @param map
	 * @return
	 */
	int queryOpenDoorRecordTotalCompany(HashMap<String,Object> map);
}
