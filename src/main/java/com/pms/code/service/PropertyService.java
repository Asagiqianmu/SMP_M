package com.pms.code.service; 
import java.util.HashMap;

import com.pms.code.entity.base.Property;
 
public interface PropertyService {

	/**
	 * 查询物业公司列表	 
	 * @return
	 */
	HashMap<String, Object> queryPropertyList(HashMap<String,Object> map);
	 
	/**
	 * 保存物业公司修改	
	 * @param id
	 * @return
	 */
	boolean saveEditProperty(Property property);
	
	/**
	 * 查询物业公司通过id
	 * @param id
	 * @return
	 */
	Property queryPropertyById(int id);
	
	/**
	 * 通过pid查询物业信息
	 * @param id
	 * @return
	 */
	Property queryPropertyByPid(int pid);
	
	/**
	 * 物业公司删除	
	 * @param property
	 * @return
	 */
	boolean deleteProperty(int []ids);
	
	/**
	 * 查询物业公司总数
	 */
	int queryPropertyTotal(Property property);
	
	/**
	 * 添加新物业公司
	 * @param property
	 * @return
	 */
	boolean insertProperty(Property property);
	
	/**
	 * 修改公司信息url
	 * @param map
	 * @return
	 */
	boolean updatePropertyUrl(HashMap<String,Object> map);
	
}
