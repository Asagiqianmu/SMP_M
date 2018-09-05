package com.pms.code.service;

import com.pms.code.entity.base.LockPwd;

public interface LockPwdService {
	boolean insertLockPwd(LockPwd lockpwd);
	
	boolean updateLockPwd(LockPwd lockPwd);
	
	LockPwd queryLockPwdById(int id);
	
//	门锁删除密码
	boolean deleteLockPwd(int id);
}
