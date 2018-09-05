package com.pms.code.service;

import java.util.HashMap;

import com.pms.code.entity.base.ConvenienceInfo;

/**
 * 便民信息接口
 * @author Dell
 *
 */
public interface ConvenienceInfoService {
	/**
	 * 查询便民信息
	 * @param map
	 * @return     
	 */
	HashMap<String,Object> queryConvenienceInfo(HashMap<String,Object> map);
	
	/**
	 * 查询便民信息总数
	 */
	int queryConvenienceInfoTotal(HashMap<String,Object> map);
	
	/**
	 * 增加便民信息
	 * @param convenienceInfoService
	 * @return
	 */
	boolean insertConvenienceInfo(ConvenienceInfo convenienceInfo);
	
	/**
	 * 查询便民信息通过id
	 * @param id
	 * @return
	 */
    ConvenienceInfo	queryConvenienceInfoById(int id);
    
    /**
     * 修改便民信息
     * @param convenienceInfo
     * @return
     */
    boolean updateConvenienceInfo(ConvenienceInfo convenienceInfo);
    
    /**
     * 删除便民信息通过id
     * @param id
     * @return
     */
    boolean deleteConvenienceInfo(int[] id);
    
    /**
     * 查询便民信息:公司登录
     * @param map
     * @return
     */
    HashMap<String,Object> queryConvenienceInfoCompany(HashMap<String,Object> map);
    
    /*
     * 查询便民总数:公司登录
     */
    int queryConvenienceInfoTotalCompany(HashMap<String,Object> map);
}
