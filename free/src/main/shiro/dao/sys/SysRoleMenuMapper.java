package shiro.dao.sys;

import java.util.List;

import shiro.model.sys.SysRoleMenu;
import tk.mybatis.mapper.common.Mapper;

public interface SysRoleMenuMapper extends Mapper<SysRoleMenu> {

	void insertRoleMenuOfBatch(List<SysRoleMenu> roleMenuList);

	void deleteByRoleId(String roleId);
}