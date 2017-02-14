package shiro.dao.sys;

import shiro.model.sys.SysDepartment;
import tk.mybatis.mapper.common.Mapper;

public interface SysDepartmentMapper extends Mapper<SysDepartment> {
	
	void saveDept(SysDepartment dept);
}