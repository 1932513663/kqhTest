package shiro.dao.sys;

import shiro.model.sys.SysRole;
import tk.mybatis.mapper.common.Mapper;

public interface SysRoleMapper extends Mapper<SysRole> {

	void saveRole(SysRole role);
}