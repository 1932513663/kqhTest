package shiro.service;

import java.util.List;
import java.util.Map;

import core.base.service.BaseIService;
import shiro.model.sys.SysUserRole;
import utils.base.Tree;

public interface SysUserRoleIService extends BaseIService<SysUserRole> {
	/**
	 * 
	 * @Title: findRoleTreeOfUser
	 * @Description: TODO
	 * @param @param userId
	 * @param @return
	 * @return List<Tree>
	 *
	 * @author kang
	 * @date 2017年2月7日 下午2:36:22
	 */
	List<Tree> findRoleTreeOfUser(String userId);
	
	/**
	 * 
	 * @Title: findRoleTreeAll
	 * @Description: 查找整棵角色树
	 * @param @param map:存放已选定的角色
	 * @param @return
	 * @return List<Tree>
	 *
	 * @author kang
	 * @date 2017年2月7日 下午2:46:13
	 */
	List<Tree> findRoleTreeAll(String parentId,Map<String, SysUserRole> map);

	/**
	 * 
	 * @Title: saveRoleUsers
	 * @Description: TODO
	 * @param @param userRolesStr
	 * @param @return
	 * @return String
	 *
	 * @author kang
	 * @date 2017年2月7日 下午5:13:47
	 */
	String saveRoleUsers(String userRolesStr);
	
	/**
	 * 
	 * @Title: deleteByUserId
	 * @Description: 删除user的所有角色
	 * @param @param userId
	 * @return void
	 *
	 * @author kang
	 * @date 2017年2月9日 下午1:57:04
	 */
	void deleteByUserId(String userId);
	
	/**
	 * 
	 * @Title: insertUserRoleOfBatch
	 * @Description: 批量插入
	 * @param @param userRoleList
	 * @param @return
	 *
	 * @author kang
	 * @date 2017年2月9日 下午2:13:02
	 */
	void insertUserRoleOfBatch(List<SysUserRole> userRoleList);
}
