package shiro.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import core.base.service.impl.BaseServiceImpl;
import shiro.dao.sys.SysUserMapper;
import shiro.model.sys.SysUser;
import shiro.service.SysUserIService;
import utils.JsonUtils;

@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUser> implements SysUserIService {
	@Autowired
	private SysUserMapper sysUserMapper;

	@Override
	public String delUserById(String userId) {
		deleteById(userId);
		return JsonUtils.returnJsonSuccess();
	}

	@Override
	public String save(SysUser user) {
		sysUserMapper.saveUser(user);
		return JsonUtils.returnJsonSuccess(user);
	}

	@Override
	public String updateUser(SysUser user) {
		super.update(user);
		return JsonUtils.returnJsonSuccess(user);
	}

	@Override
	public SysUser findSysUserByLoginNamePwd(String loginName, String pwd) {
		SysUser user=new SysUser();
		user.setUserLoginName(loginName);
		user.setPassword(pwd);
		SysUser selectOne = sysUserMapper.selectOne(user);
		return selectOne;
	}
}
