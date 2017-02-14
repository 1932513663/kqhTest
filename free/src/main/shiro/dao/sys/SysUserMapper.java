package shiro.dao.sys;

import shiro.model.sys.SysUser;
import tk.mybatis.mapper.common.Mapper;

public interface SysUserMapper extends Mapper<SysUser> {
	void saveUser(SysUser user);
}