package shiro.dao.sys;

import shiro.model.sys.SysCompany;
import tk.mybatis.mapper.common.Mapper;

public interface SysCompanyMapper extends Mapper<SysCompany> {
	void saveCompany(SysCompany company);
}