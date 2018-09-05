package com.pms.code.service;

import java.util.HashMap;

import com.pms.code.entity.base.User;

/**
 * 管理员接口
 * @author Dell
 *
 */
public interface UserService {
	 
	/**
	 * 修改密码
	 * @param map
	 * @return
	 */
	boolean updatePassWord(HashMap<String,Object> map);
	
	/**
	 * 根据用户名查找用户信息
	 * @author dengfei E-mail:dengfei200857@163.com
	 * @time 2018年3月29日 下午5:26:12
	 * @param userName
	 * @return
	 */
	User queryUserInfo(String userName);
	
	/**
	 *添加小区管理员 
	 * @param user
	 * @return
	 */
	boolean addUser(User user);
	
	/**
	 * 查询用户--物业办事处信息通过Id
	 * @return
	 */
	User queryUserPmdById(int id);
	
	/**
	 * 查询用户--公司信息通过id
	 * @param id
	 * @return
	 */
	User queryUserPropertyById(int id);
}
