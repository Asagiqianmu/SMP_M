package com.pms.code.service;

import java.util.HashMap;
import java.util.List;

import com.pms.code.entity.base.CommunityOwnerInfo;

/**
 * 小区+业主接口
 * @author Dell
 *
 */
public interface CommunityOwnerInfoService {
	
	/**
	 * 查询业主信息:小区+业主
	 * @param map
	 * @return
	 */
    HashMap<String,Object>	queryCommunityOwnerInfo(HashMap<String,Object> map);
    
    /**
     * 查询业主信息总数:小区+业主
     * @param map
     * @return
     */
    int queryCommunityOwnerInfoTotal(HashMap<String,Object> map);
}
