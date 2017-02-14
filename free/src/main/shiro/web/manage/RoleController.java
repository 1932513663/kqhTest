package shiro.web.manage;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import shiro.model.sys.SysRole;
import shiro.service.SysRoleIService;
import shiro.service.SysRoleMenuIService;
import utils.Constants;
import utils.base.StringUtils;
import utils.base.Tree;

/**
 * 
 * @ClassName: RoleController
 * @Title: RoleController.java
 * @Description: 角色管理
 *
 * @author kang
 * @version V1.0 
 * @company 麦田
 * @date 2017年2月7日 上午11:42:59
 */
@RequestMapping("/manage/sys/role")
@Controller
public class RoleController {
	@Autowired
	private SysRoleIService sysRoleIService;
	@Autowired
	private SysRoleMenuIService sysRoleMenuIService;
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView("manage/sys/role/index");
		return modelAndView;
	}
	
	/**
	 * 
	 * @Title: roleTree
	 * @Description: 跳转到角色页面
	 * @param @return
	 * @return ModelAndView
	 *
	 * @author kang
	 * @date 2017年2月1日 下午4:29:08
	 */
	@RequestMapping(value = "/roleTree", method = RequestMethod.GET)
	public ModelAndView roleTree() {
		ModelAndView modelAndView = new ModelAndView("manage/sys/role/roleTree");
		return modelAndView;
	}

	/**
	 * 
	 * @Title: findCompanyTree
	 * @Description: 查找角色树节点（异步加载）
	 * @param @param id
	 * @param @param roleId（选中节点id）
	 * @param @return
	 * @return List<Tree>
	 *
	 * @author kang
	 * @date 2017年2月1日 下午4:07:42
	 */
	@RequestMapping(value = "findRoleTree", method = RequestMethod.POST)
	@ResponseBody
	public List<Tree> findRoleTree(@RequestParam(value = "id", required = false) String id,@RequestParam(value = "roleId", required = true) String roleId) {
		SysRole sysRole = new SysRole();
		List<Tree> list=new ArrayList<Tree>();
		if (StringUtils.isEmpty(id)) {
			if(StringUtils.isEmpty(roleId)){
				id = "00000000000000000000000000000000";
				sysRole.setParentId(id);
				list = sysRoleIService.findRoleTree(sysRole);
			}else{
				sysRole.setParentId(roleId);
				list = sysRoleIService.findRoleTreeParents(sysRole);
			}
		}else{
			sysRole.setParentId(id);
			list = sysRoleIService.findRoleTree(sysRole);
		}
		
		return list;
	}
	
	/**
	 * 
	 * @Title: findMenuTree
	 * @Description: (不能)异步加载树形菜单
	 * @param @param id
	 * @param @return
	 * @return List<Tree>
	 *
	 * @author kang
	 * @date 2017年2月9日 下午4:05:34
	 */
	@RequestMapping(value = "findMenuTreeOfRole", method = RequestMethod.POST)
	@ResponseBody
	public List<Tree> findMenuTreeOfRole(@RequestParam(value = "roleId", required = false) String roleId) {
		List<Tree> list = sysRoleMenuIService.findMenuTreeOfRole(roleId);
		return list;
	}
	
	/**
	 * 
	 * @Title: role
	 * @Description: 选中角色详情
	 * @param @param role
	 * @param @return
	 * @return ModelAndView
	 *
	 * @author kang
	 * @date 2017年1月19日 下午1:44:30
	 */
	@RequestMapping(value = "/role", method = RequestMethod.GET)
	public ModelAndView role(@RequestParam(value = "roleId", required = false) String roleId) {
		ModelAndView modelAndView = new ModelAndView("manage/sys/role/role");
		SysRole role = sysRoleIService.findById(roleId);
		modelAndView.addObject("roleId", roleId);
		modelAndView.addObject("role", role);
		return modelAndView;
	}
	
	/**
	 * 
	 * @Title: findRoleTreeOfSelect
	 * @Description: 新增角色时选择父角色
	 * @param @param id
	 * @param @return
	 * @return List<Tree>
	 *
	 * @author kang
	 * @date 2017年2月1日 下午5:20:23
	 */
	@RequestMapping(value = "findRoleTreeOfSelect", method = RequestMethod.POST)
	@ResponseBody
	public List<Tree> findRoleTreeOfSelect(@RequestParam(value = "id", required = false) String id) {
		SysRole sysRole = null;
		if (!StringUtils.isEmpty(id)) {
			sysRole=new SysRole();
			sysRole.setParentId(id);
		}
		List<Tree> list = sysRoleIService.findRoleTreeOfSelect(sysRole);
		return list;
	}
	
	/**
	 * 
	 * @Title: toAdd
	 * @Description: 跳转company新增页面
	 * @param @return
	 * @return ModelAndView
	 *
	 * @author kang
	 * @date 2017年1月22日 下午12:07:07
	 */
	@RequestMapping(value = "/toAdd", method = RequestMethod.GET)
	public ModelAndView toAdd() {
		ModelAndView modelAndView = new ModelAndView("manage/sys/role/add");
		return modelAndView;
	}
	
	/**
	 * 
	 * @Title: save
	 * @Description: 新增角色
	 * @param @param role
	 * @param @return
	 * @return String
	 *
	 * @author kang
	 * @date 2017年1月22日 下午6:48:40
	 */
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(SysRole role,String roleMenusStr) {
		String str = sysRoleIService.save(role,roleMenusStr);
		return str;
	}
	
	/**
	 * 
	 * @Title: toEdit
	 * @Description: 跳转到修改页
	 * @param @param roleId
	 * @param @return
	 * @return ModelAndView
	 *
	 * @author kang
	 * @date 2017年1月30日 上午10:38:06
	 */
	@RequestMapping(value = "/toEdit", method = RequestMethod.GET)
	public ModelAndView toEdit(@RequestParam(value = "roleId", required = true) String roleId) {
		ModelAndView modelAndView = new ModelAndView("manage/sys/role/edit");
		SysRole role = sysRoleIService.findById(roleId);
		String parentId = role.getParentId();
		String parentName="";
		if(Constants.ROOT_NODE.equals(parentId)){
			parentName=Constants.ROOT_NODE_NAME;
		}else{
			SysRole roleParent = sysRoleIService.findById(parentId);
			parentName = roleParent.getRoleName();
		}
		modelAndView.addObject("role", role);
		modelAndView.addObject("parentName", parentName);
		return modelAndView;
	}
	
	/**
	 * 
	 * @Title: edit
	 * @Description: 修改保存
	 * @param @param role
	 * @param @return
	 * @return Object
	 *
	 * @author kang
	 * @date 2017年1月30日 上午11:34:17
	 */
	@ResponseBody
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String edit(SysRole role,String roleMenusStr) {
		String str=sysRoleIService.update(role,roleMenusStr);
		return str;
	}
	
	/**
	 * 
	 * @Title: del
	 * @Description: 物理删除
	 * @param @param roleId
	 * @param @return
	 * @return String
	 *
	 * @author kang
	 * @date 2017年2月2日 下午12:01:35
	 */
	@ResponseBody
	@RequestMapping(value = "/del", method = RequestMethod.POST)
	public String del(@RequestParam(value = "roleId", required = true) String roleId) {
		String res = sysRoleIService.delRoleById(roleId);
		return res;
	}
}
