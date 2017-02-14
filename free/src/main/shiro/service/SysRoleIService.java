package shiro.service;

import java.util.List;

import core.base.service.BaseIService;
import shiro.model.sys.SysRole;
import utils.base.Tree;

public interface SysRoleIService extends BaseIService<SysRole>{
	
	/**
	 * 
	 * @Title: findRoleTree
	 * @Description: 根据条件查找角色集合
	 * @param @param sysRole
	 * @param @return
	 * @return List<Tree>
	 *
	 * @author kang
	 * @date 2017年2月1日 下午4:24:59
	 */
	List<Tree> findRoleTree(SysRole sysRole);

	/**
	 * 
	 * @Title: findRoleTreeParents
	 * @Description: 根据已知的子角色查找他的所有父节点及同级兄弟节点
	 * @param @param sysRole
	 * @param @return
	 * @return List<Tree>
	 *
	 * @author kang
	 * @date 2017年2月1日 下午4:24:23
	 */
	List<Tree> findRoleTreeParents(SysRole sysRole);

	/**
	 * 
	 * @Title: findRoleTreeOfSelect
	 * @Description: 新增角色时选择父角色
	 * @param @param sysRole
	 * @param @return
	 * @return List<Tree>
	 *
	 * @author kang
	 * @date 2017年2月1日 下午5:21:00
	 */
	List<Tree> findRoleTreeOfSelect(SysRole sysRole);

	/**
	 * 
	 * @Title: delRoleById
	 * @Description: 物理删除
	 * @param @param roleId
	 * @param @return
	 * @return String
	 *
	 * @author kang
	 * @date 2017年2月2日 下午12:02:55
	 */
	String delRoleById(String roleId);

	/**
	 * 
	 * @Title: save
	 * @Description: 保存
	 * @param @param role
	 * @param @param roleMenusStr
	 * @param @return
	 * @return String
	 *
	 * @author kang
	 * @date 2017年2月9日 下午5:22:48
	 */
	String save(SysRole role, String roleMenusStr);

	/**
	 * 
	 * @Title: update
	 * @Description: 修改
	 * @param @param role
	 * @param @param roleMenusStr
	 * @param @return
	 * @return String
	 *
	 * @author kang
	 * @date 2017年2月10日 上午9:21:16
	 */
	String update(SysRole role, String roleMenusStr);

}
