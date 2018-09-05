package com.pms.code.service;


import org.apache.shiro.subject.PrincipalCollection;




public interface RealmService {
	public void doGetAuthorizationInfoByAdmin(PrincipalCollection principalCollection);
}
