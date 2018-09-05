package com.pms.code.service.impl;


import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pms.code.service.RealmService;

@Transactional
@Service
public class RealmServiceImpl extends AuthorizingRealm implements  RealmService {
//	private Logger logger = LoggerFactory.getLogger(RealmServiceImpl.class);
	
//	@Autowired
//	private BaseDao<User, Serializable> UserceBaseDaoImpl;
	
//	@Autowired
//	private BaseDao<String, Serializable> stringceBaseDaoImpl;
	


	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
//		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//		Object principal = null;
//		try {
//			principal = principalCollection.getPrimaryPrincipal();
//			//根据数据库数据，为自己增加角色 取到角色字符串，多个角色以半角','隔开
//			String rolesStr = stringceBaseDaoImpl.select(principal.toString(), MapperConst.User_mapper+".selUserRoleByIdentity");
//			//若该用户有权限
//			if(rolesStr != null){
//				//解析rolesStr
//				String[] rolesArr = rolesStr.split(",");
//				for (int i = 0; i < rolesArr.length; i++) {
//					info.addRole(rolesArr[i]);
//					//根据角色查询权限
//					List<String> permList = stringceBaseDaoImpl.paramSQL( MapperConst.User_mapper+".selUserPermByRole",rolesArr[i]);
//					for (int j = 0; j < permList.size(); j++) {
//						info.addStringPermission(permList.get(j));
//					}
//				}
//			}
//		} catch (DAOException e) {
//			logger.error("出现异常"+e);
//		}
//		return info;
		return null;
	}



	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
		AuthenticationToken token) throws AuthenticationException {
		//登录的主信息：从数据库中查询的结果，该结果和token中携带的必须一致
//		String principal = (String) token.getPrincipal();
//		//认证信息：从数据库中查询出来的信息。密码的比对交给shiro去进行比较
//		User user=null;
//		try {
//			user = UserceBaseDaoImpl.select(principal, MapperConst.User_mapper+".selUserByName");
//		} catch (DAOException e) {
//			logger.error("查询用户信息失败"+e);
//		}
//		//当前realm的name
//		String realmName = getName();
//		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal, user.getPassWord(), realmName);
//		return info;
		return null;
	}

	public void doGetAuthorizationInfoByAdmin(PrincipalCollection principalCollection) {
		this.doGetAuthorizationInfo(principalCollection);
	}

	
	
}
