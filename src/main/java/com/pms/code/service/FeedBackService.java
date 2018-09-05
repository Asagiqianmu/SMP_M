package com.pms.code.service;

import java.util.HashMap;

import com.pms.code.entity.base.FeedBack;

/**
 * 投诉管理接口
 * @author Dell
 *
 */
public interface FeedBackService {
	
	/**
	 * 投诉管理列表
	 * @param map
	 * @return
	 */
	HashMap<String,Object> queryFeedBackInfo(HashMap<String,Object> map);
	
	/**
	 * 投诉总数
	 */
	int queryFeedBackInfoTotal();
	
	/**
	 * 根据id查询投诉信息
	 * @param id
	 * @return
	 */
	FeedBack queryFeedBackInfoById(int id);
	
	/**
	 * 删除投诉信息
	 * @param id
	 * @return
	 */
	boolean deleteFeedBack(int id);
}
