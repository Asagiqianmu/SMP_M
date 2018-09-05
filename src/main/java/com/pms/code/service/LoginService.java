package com.pms.code.service;


import java.util.HashMap;

import com.pms.code.entity.base.User;


public interface LoginService {


	boolean registerUser(String userName,String passWord);
	
	User findUser(HashMap<String,Object> map);
	 
}
