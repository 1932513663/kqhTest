package shiro.dao.sys;

import shiro.model.sys.SysMenu;
import tk.mybatis.mapper.common.Mapper;

public interface SysMenuMapper extends Mapper<SysMenu> {
	void saveMenu(SysMenu menu);
}