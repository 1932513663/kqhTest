package shiro.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Sets;

import shiro.model.sys.SysMenu;
import shiro.model.sys.SysRole;
import shiro.model.sys.SysUser;
import shiro.service.SysUserIService;
import shiro.utils.MD5Util;

/**
 * 
 * @ClassName: SystemAuthorizingRealm
 * @Title: SystemAuthorizingRealm.java
 * @Description: TODO
 *
 * @author kang
 * @version V1.0
 * @company 麦田
 * @date 2017年2月10日 下午2:47:50
 */
public class SystemAuthorizingRealm extends AuthorizingRealm {
	private static Logger log = LoggerFactory.getLogger(SystemAuthorizingRealm.class);

	@Autowired
	private SysUserIService sysUserIService;

	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		log.info("进入授权！");
		Principal shiroUser = (Principal) principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		Set<String> rolesSet = Sets.newLinkedHashSet();
		Set<String> persSet = Sets.newLinkedHashSet();
		String userId = shiroUser.getId();
		log.info("userId:"+userId);
		// List<SysRole> roleList=sysIService.loadUserRoles(userId);
		// List<SysMenu> menuList=sysIService.loadUserMenus(userId);
		List<SysRole> roleList = new ArrayList<SysRole>();//角色列表
		List<SysMenu> menuList = new ArrayList<SysMenu>();//菜单列表
		for(SysRole role:roleList){
            rolesSet.add(role.getRoleEnglishName());
        }
		for(SysMenu menu:menuList){
            if (StringUtils.isNotEmpty(menu.getPermissionCode())) {
                persSet.add(menu.getPermissionCode());
            }
        }
		info.setRoles(rolesSet);
        info.setStringPermissions(persSet);
        return info;
	}

	/**
	 * 认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		log.info("进入认证：doGetAuthenticationInfo");
		UsernamePasswordToken uptoken = (UsernamePasswordToken) token;
		boolean isFormLogin = true;
		// 获取登录名,密码
		String loginName = uptoken.getUsername();
		String pwd = getLoginPwd(uptoken.getPassword(), isFormLogin);
		// 通过登录信息查询用户
		SysUser sysUser = sysUserIService.findSysUserByLoginNamePwd(loginName, pwd);
		if (sysUser != null) {
			// 此方法暂无考虑盐值加密
			return new SimpleAuthenticationInfo(createUserPrincipal(sysUser), uptoken.getPassword(), getName());
		}

		return null;
	}

	/**
	 * 取得登陆密码
	 * 
	 * @param pwd
	 * @return
	 */
	private String getLoginPwd(char[] pwd, boolean isFormLogin) {
		StringBuffer sb = new StringBuffer();
		for (char ch : pwd) {
			sb.append(ch);
		}

		if (!isFormLogin) {
			return sb.toString();
		}

		// MD5加密,并从第8位开始截取16位
		String pwdMd5 = MD5Util.MD5(sb.toString()).substring(8, 24);
		return pwdMd5;
	}

	/**
	 * 创建认证用户
	 * 
	 * @param user
	 * @return
	 */
	private Principal createUserPrincipal(SysUser user) {
		Principal shiroUser = new Principal(user);
		return shiroUser;
	}
	
	/**
     * 更新用户授权信息缓存.
     */
    public void clearCachedAuthorizationInfo(Object principal) {
        SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());
        clearCachedAuthorizationInfo(principals);
    }

    /**
     * 清除所有用户授权信息缓存.
     */
    public void clearAllCachedAuthorizationInfo() {
        Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
        if (cache != null) {
            for (Object key : cache.keys()) {
                cache.remove(key);
            }
        }
    }

    public static void main(String[] args) {
    	String pwdMd5 = MD5Util.MD5("111111").substring(8, 24);
    	System.out.println(pwdMd5);
	}
}
