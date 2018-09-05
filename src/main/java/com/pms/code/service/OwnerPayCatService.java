package com.pms.code.service;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.aliyun.oss.model.Owner;
import com.pms.code.entity.base.OwnerAccount;
import com.pms.code.entity.base.OwnerPaycat;

/**
 * 缴费接口
 * @author Dell
 *
 */
public interface OwnerPayCatService {
	/**
	 * 根据uid删除用户支付信息
	 * @param ids
	 * @return
	 */
	boolean deleteOwnerPaycatByUIds(int []ids);
	
	/**
	 * 已经缴费列表:用户
	 */
	HashMap<String,Object> queryOwnerPayCatList(HashMap<String,Object> map);
	
	/**
	 * 已经缴费列表:公司
	 * @param map
	 * @return
	 */
	HashMap<String,Object> queryOwnerPayCatListCompany(HashMap<String,Object> map);
	
	/**
	 * 查询缴费总数:用户
	 */
	int queryOwnerPayCatTotal(HashMap<String,Object> map);
	
	/**
	 * 查询缴费总数:公司
	 * @param map
	 * @return
	 */
	int queryOwnerPayCatTotalCompany(HashMap<String,Object> map);
	
	/**
	 * 未缴费列表:用户
	 */
	HashMap<String,Object> queryUnOwnerPayCatList(HashMap<String,Object> map);
	
	/**
	 * 未缴费列表:公司
	 * @param map
	 * @return
	 */
	HashMap<String,Object> queryUnOwnerPayCatListCompany(HashMap<String,Object> map);
	
	/**
	 * 未缴费总数:用户
	 */
	int queryUnOwnerPayCatTotal(HashMap<String,Object> map);
	
	/**
	 * 未缴费总数:公司
	 * @param map
	 * @return
	 */
	int queryUnOwnerPayCatTotalCompany(HashMap<String,Object> map);
	
	/**
	 * 查询缴费信息通过id
	 * @param id
	 * @return
	 */
	OwnerPaycat queryPayCatById(int id);
	
	/**
	 * 修改未缴费信息
	 * @param ownerPaycat
	 * @return
	 */
	boolean updateUnpayCatInfo(OwnerPaycat ownerPaycat);
	
	/**
	 * 录入缴费信息
	 */
	boolean OwnerPayCatEnter(OwnerPaycat ownerPaycat);
	
	/**
	 * 查询月度流水
	 */
	HashMap<String,Object> qureyMonthCost(HashMap<String,Object> hashMap);
	
	/**
	 * 查询月度流水总数
	 */
	int queryMonthCostTotal();
	
	/**
	 * 删除缴费信息
	 * @param id
	 * @return
	 */
	boolean deleteUnPayCatInfo(int id);
	
	/**
	 * 查询业主每月账单信息
	 * @param map
	 * @return
	 */
	HashMap<String,Object> queryOwnerPayCat(HashMap<String,Object> map);
} 
