package com.pms.code.service.impl;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pms.code.dao.BaseDao;
import com.pms.code.entity.base.User;
import com.pms.code.exception.BusinessException;
import com.pms.code.exception.DAOException;
import com.pms.code.service.LoginService;
import com.pms.code.util.Constant;
import com.pms.code.util.MD5;
import com.pms.code.util.MapperConst;
import com.pms.code.util.SHA256;

@Transactional
@Service("loginServiceImpl")
public class LoginServiceImpl implements LoginService{
	
	private Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);
	
	@Autowired
	private BaseDao<User, Serializable> UserceBaseDaoImpl;
	 
	@Override
	public boolean registerUser(String userName, String passWord) {
		User user= new User();
		user.setUserName(userName);
		user.setPassWord(SHA256.getUserPassword(MD5.encode(passWord).toLowerCase()));
		user.setCreateTime(new Timestamp(new Date().getTime()));
		int i = 0;
		try {
			i = UserceBaseDaoImpl.insert(user,MapperConst.User_Mapper+".insertUser");
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i>0?true:false;
		
	}

	@Override
	public User findUser(HashMap<String,Object> paramMap) {
		try {
			return UserceBaseDaoImpl.select(paramMap, MapperConst.User_Mapper+".selUserByNamePwd");
			} catch (DAOException e) {
				logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
				throw new BusinessException(Constant.ERROR_STATE, Constant.ERROR_TIP);
			}
	}
	 
}
