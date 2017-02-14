package shiro.service;

import java.util.List;

import core.base.service.BaseIService;
import shiro.model.sys.SysMenu;
import utils.base.Tree;

public interface SysMenuIService extends BaseIService<SysMenu>{
	/**
	 * 
	 * @Title: findFirstLevelMenuList
	 * @Description: 查询菜单一级有效节点
	 * @param @param sysMenu
	 * @param @return
	 * @return List<SysMenu>
	 *
	 * @author kang
	 * @date 2017年1月16日 下午4:43:48
	 */
	public List<SysMenu> findFirstLevelMenuList(SysMenu sysMenu);
	
	public void addEntity(SysMenu sysMenu);
	
	public List<SysMenu> findChildrenMenuList(SysMenu sysMenu);

	public String delMenuById(String menuId);

	public List<Tree> findMenuTreeOfSelect(SysMenu menu);

	public List<Tree> findMenuTree(SysMenu menu);

	/**
	 * 
	 * @Title: save
	 * @Description: 保存
	 * @param @param menu
	 * @param @return
	 * @return String
	 *
	 * @author kang
	 * @date 2017年2月10日 下午1:03:33
	 */
	public String save(SysMenu menu);

	/**
	 * 
	 * @Title: updateMenu
	 * @Description: 修改
	 * @param @param menu
	 * @param @return
	 * @return String
	 *
	 * @author kang
	 * @date 2017年2月10日 下午1:03:45
	 */
	public String updateMenu(SysMenu menu);

	/**
	 * 
	 * @Title: findByParentId
	 * @Description: 根据父Id查询子节点，且根据排序号排序
	 * @param @param parentId
	 * @param @param order
	 * @param @return
	 * @return List<SysMenu>
	 *
	 * @author kang
	 * @date 2017年2月10日 下午1:45:44
	 */
	public List<SysMenu> findByParentId(String parentId, String order);

	public List<SysMenu> findALLFlatSysMenu();

	public List<SysMenu> getAllMenuListByParentId(String parentId);
}
