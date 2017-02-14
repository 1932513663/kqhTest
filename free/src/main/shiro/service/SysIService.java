package shiro.service;

import java.util.List;

import shiro.model.sys.SysMenu;
import shiro.model.sys.SysRole;


public interface SysIService {
	/**
	 * 
	 * @Title: loadUserRoles
	 * @Description: 通过用户ID查找所有关联的角色
	 * @param @param userId
	 * @param @return
	 * @return List<SysRole>
	 *
	 * @author kang
	 * @date 2017年2月13日 下午5:22:22
	 */
	public List<SysRole> loadUserRoles(String userId);

	/**
	 * 
	 * @Title: loadUserMenus
	 * @Description: 通过用户id查找所有关联的菜单(如果只勾选了子，没勾选父，则默认把父加上)
	 * @param @param userId
	 * @param @return
	 * @return List<SysMenu>
	 *
	 * @author kang
	 * @date 2017年2月13日 下午5:23:01
	 */
	public List<SysMenu> loadUserMenus(String userId);

	/**
	 * 一级菜单列表
	 * @param userId
	 * @return
	 */
	public List<SysMenu> getLevelMenuList(String parentId,String userId);
}
