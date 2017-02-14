package shiro.dao.sys;

import shiro.model.sys.SysPost;
import tk.mybatis.mapper.common.Mapper;

public interface SysPostMapper extends Mapper<SysPost> {

	void savePost(SysPost post);
}