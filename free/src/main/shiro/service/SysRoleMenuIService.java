package shiro.service;

import java.util.List;
import java.util.Map;

import core.base.service.BaseIService;
import shiro.model.sys.SysMenu;
import shiro.model.sys.SysRoleMenu;
import utils.base.Tree;

public interface SysRoleMenuIService extends BaseIService<SysRoleMenu> {
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
	List<Tree> findMenuTreeOfRole(String roleId);
	
	/**
	 * 
	 * @Title: findMenuTreeAll
	 * @Description: 查找整棵角色树
	 * @param @param map:存放已选定的菜单
	 * @param @return
	 * @return List<Tree>
	 *
	 * @author kang
	 * @date 2017年2月7日 下午2:46:13
	 */
	List<Tree> findMenuTreeAll(String parentId,Map<String, SysRoleMenu> map);

	/**
	 * 
	 * @Title: saveRoleMenus
	 * @Description: TODO
	 * @param @param roleMenusStr
	 * @param @return
	 * @return String
	 *
	 * @author kang
	 * @date 2017年2月7日 下午5:13:47
	 */
	String saveRoleMenus(String roleId,String roleMenusStr);
	
	/**
	 * 
	 * @Title: deleteByRoleId
	 * @Description: 删除role的所有菜单
	 * @param @param roleId
	 * @return void
	 *
	 * @author kang
	 * @date 2017年2月9日 下午1:57:04
	 */
	void deleteByRoleId(String roleId);
	
	/**
	 * 
	 * @Title: insertRoleMenuOfBatch
	 * @Description: 批量插入
	 * @param @param roleMenuList
	 * @param @return
	 *
	 * @author kang
	 * @date 2017年2月9日 下午2:13:02
	 */
	void insertRoleMenuOfBatch(List<SysRoleMenu> roleMenuList);

	List<SysRoleMenu> findByRoleId(String roleId);

	List<SysMenu> findMenuListByRoleId(String roleId);
}
