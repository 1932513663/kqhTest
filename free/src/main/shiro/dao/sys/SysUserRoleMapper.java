package shiro.dao.sys;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import shiro.model.sys.SysUserRole;
import tk.mybatis.mapper.common.Mapper;

public interface SysUserRoleMapper extends Mapper<SysUserRole> {

	void deleteByUserId(@Param("userId") String userId);

	void insertUserRoleOfBatch(List<SysUserRole> userRoleList);
}