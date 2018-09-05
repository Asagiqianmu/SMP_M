package com.pms.code.service.impl;

import java.io.Serializable;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pms.code.dao.BaseDao;
import com.pms.code.entity.base.User;
import com.pms.code.exception.DAOException;
import com.pms.code.service.UserService;
import com.pms.code.util.MapperConst;
 
@Transactional
@Service("userServiceBaseDaoImpl")
public class UserServiceImpl implements UserService{
	private Logger logger = LoggerFactory.getLogger(SysNoticeServiceImpl.class);
	
	@Autowired
	private BaseDao<User, Serializable> userServiceBaseDaoImpl;
	
	@Autowired
	private BaseDao<HashMap<String,Object>, Serializable> mapUserServiceBaseDaoImpl;

	/**
	 * 修改密码
	 */
	@Override
	public boolean updatePassWord(HashMap<String, Object> map) {
		int count=0;
		try {
			count=mapUserServiceBaseDaoImpl.update(map,MapperConst.User_Mapper+".updatePassWord");
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
		}
		return count>0?true:false;
	}
	
	/**
	 * 根据用户名查找用户信息
	 */
	@Override
	public User queryUserInfo(String userName) {
		// TODO Auto-generated method stub
		User user = new User();
		user.setUserName(userName);
		try {
			user = userServiceBaseDaoImpl.select(user, MapperConst.User_Mapper+".selUserByName");
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
			return null;
		}
		return user;
	}

	/**
	 * 添加小区管理员
	 */
	@Override
	public boolean addUser(User user) {
		int count=0;
		try {
			count=userServiceBaseDaoImpl.insert(user,MapperConst.User_Mapper+".addUser");
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
		}
		return count>0?true:false;
	}

	/**
	 * 查询用户信息通过Id
	 */
	@Override
	public User queryUserPmdById(int id) {
		try {
			HashMap<String,Object> map=new HashMap<String,Object>();
			map.put("id",id);
			return userServiceBaseDaoImpl.select(map,MapperConst.User_Mapper+".queryUserPmdById");
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
			return null;
		}
	}

	@Override
	public User queryUserPropertyById(int id) {
		try {
			HashMap<String,Object> map=new HashMap<String,Object>();
			map.put("id",id);
			return userServiceBaseDaoImpl.select(map,MapperConst.User_Mapper+".queryUserPropertyById");
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
			return null;
		}
	} 
}























