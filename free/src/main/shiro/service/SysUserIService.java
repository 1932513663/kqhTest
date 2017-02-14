package shiro.service;

import core.base.service.BaseIService;
import shiro.model.sys.SysUser;

public interface SysUserIService extends BaseIService<SysUser> {

	public String delUserById(String userId);

	public String save(SysUser user);

	public String updateUser(SysUser user);

	public SysUser findSysUserByLoginNamePwd(String loginName, String pwd);
	
}
