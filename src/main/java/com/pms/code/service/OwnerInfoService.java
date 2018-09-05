package com.pms.code.service;

import java.util.HashMap;
import java.util.List;

import com.aliyun.oss.model.Owner;
import com.pms.code.entity.base.OwnerInfo;

/**
 * 所有者接口
 * @author Dell
 *
 */
public interface OwnerInfoService {
	
	/**
	 * 批量给业主退房处理
	 * @param ids
	 * @return
	 */
	boolean deleteOwnerInfoByIds(int [] ids);
	
	/**
	 * 增加所有者信息
	 * @param ownerInfo
	 * @return
	 */
	boolean addOwnerInfo(OwnerInfo ownerInfo);
	
	
	/**
	 * 根据业主全信息查询
	 * @param ownerInfo
	 * @return
	 */
	OwnerInfo queryOwnerInfoByOwnerInfo(OwnerInfo ownerInfo);
	
	/**
	 * 修改业主信息
	 * @param map
	 * @return
	 */
    boolean updateOwnerInfo(HashMap<String,Object> map);
    
    /**
	 * 查询业主信息:用户
	 * @param map
	 * @return
	 */
    HashMap<String,Object>	queryOwnerInfo(HashMap<String,Object> map);
    
    /**
     * 查询业主信息:公司
     * @param map
     * @return
     */
    HashMap<String,Object> queryOwnerInfoCompany(HashMap<String,Object> map);
    
    /**
     * 查询业主总数:公司
     * @param map
     * @return
     */
    int queryOwnerInfoTotalCompany(HashMap<String,Object> map);
    
    /**
     * 查询业主信息总数:用户
     * @param map
     * @return
     */
    int queryOwnerInfoTotal(HashMap<String,Object> map);
    /**
     * 查询业主通过id
     * @param id
     * @return
     */
    OwnerInfo queryOwnerInfoById(int id);
    
    /**
	 * 给业主办理入住、退房处理 
	 * @param map
	 * @return
	 */
    boolean updateOwnerInfoIsliving(HashMap<String,Object> map);
    
    /**
     * 查询业主信息通过物业id
     * @param pmid
     * @return
     */
    List<OwnerInfo> queryOwnerInfoByPidPmid(HashMap<String,Object> map);
}
