package shiro.web.manage;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import core.base.web.BaseController;
import shiro.model.sys.SysMenu;
import shiro.security.Principal;
import shiro.service.SysIService;
import shiro.service.SysMenuIService;
import shiro.utils.SysUtils;
import utils.Constants;
import utils.base.Tree;

/**
 * 
 * @ClassName: MenuController
 * @Title: MenuController.java
 * @Description: 菜单管理
 *
 * @author kang
 * @version V1.0 
 * @company 麦田
 * @date 2017年2月7日 上午11:43:25
 */
@RequestMapping("/manage/sys/menu")
@Controller
public class MenuController extends BaseController{
	@Autowired
	private SysMenuIService sysMenuIService;
	@Autowired
	private SysIService sysIService;

	/**
	 * @Title: topMenu
	 * @Description: 顶部导航栏
	 * @param @return
	 * @return String
	 * @author WangXy
	 * @date 2016年10月19日 下午2:03:13
	 */
	@RequestMapping(value = "/topMenu", method = RequestMethod.GET)
	public ModelAndView topMenu() {
		Principal principal=SysUtils.getPrincipal();
		ModelAndView modelAndView = new ModelAndView("manage/main/topMenu");
		List<SysMenu> menus = sysIService.getLevelMenuList(Constants.ROOT_NODE,principal.getId()) ;//principal.getId()=userId
		modelAndView.addObject("topMenu", menus);
		modelAndView.addObject("userName", principal.getName());
		return modelAndView;
	}
	
	/**
	 * 
	 * @Title: leftMenu
	 * @Description: 加载子菜单
	 * @param @param parentId
	 * @param @return
	 * @return ModelAndView
	 *
	 * @author kang
	 * @date 2017年2月14日 上午11:27:49
	 */
	@RequestMapping(value = "/leftMenu", method = RequestMethod.GET)
	public ModelAndView leftMenu(String parentId) {
		ModelAndView modelAndView = new ModelAndView("manage/main/leftMenu");
		Principal principal=SysUtils.getPrincipal();		
		if(!StringUtils.isEmpty(parentId)){
			SysMenu sysMenu = new SysMenu();
			sysMenu.setParentId(parentId);
			SysMenu menu = sysMenuIService.findById(parentId);
			List<SysMenu> leftMenuList = sysIService.getLevelMenuList(parentId, principal.getId()) ;
			modelAndView.addObject("leftMenuList", leftMenuList);
			modelAndView.addObject("menu", menu);
			modelAndView.addObject("subMenu", 1);
		}else{
			modelAndView.addObject("subMenu", 0);
		}
		return modelAndView;
	}
	/**
	 * 
	 * @Title: welcome
	 * @Description: 进入首页
	 * @param @return
	 * @return ModelAndView
	 *
	 * @author kang
	 * @date 2017年1月17日 下午1:11:18
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView welcome() {
		ModelAndView modelAndView = new ModelAndView("manage/main/index");
		return modelAndView;
	}
	
	/**
	 * 
	 * @Title: toIndex
	 * @Description: 跳转菜单首页
	 * @param @return
	 * @return ModelAndView
	 *
	 * @author kang
	 * @date 2017年2月2日 下午4:50:43
	 */
	@RequestMapping(value = "/toIndex", method = RequestMethod.GET)
	public ModelAndView toIndex() {
		ModelAndView modelAndView = new ModelAndView("manage/sys/menu/index");
		return modelAndView;
	}
	
	/**
	 * 
	 * @Title: toList
	 * @Description: 跳转菜单列表页
	 * @param @return
	 * @return ModelAndView
	 *
	 * @author kang
	 * @date 2017年2月2日 下午4:43:56
	 */
	@RequestMapping(value = "/toList", method = RequestMethod.GET)
	public ModelAndView toList() {
		ModelAndView modelAndView = new ModelAndView("manage/sys/menu/list");
		return modelAndView;
	}
	
	/**
	 * 
	 * @Title: findMenuTreegrid
	 * @Description: 查找菜单树形表
	 * @param @param id
	 * @param @return
	 * @return Object
	 *
	 * @author kang
	 * @date 2017年2月2日 下午4:34:36
	 */
	@RequestMapping(value = "findMenuTreegrid", method = RequestMethod.POST)
	@ResponseBody
	public Object findMenuTreegrid(@RequestParam(value = "id", required = false) String id) {
		SysMenu menu = new SysMenu();
		if(StringUtils.isEmpty(id)){
			id=Constants.ROOT_NODE;
		}
		menu.setParentId(id);
		String order="seq asc";
		List<SysMenu> list = sysMenuIService.findByParentId(id,order);
		SysMenu menuSearchChildren = new SysMenu();
		for (SysMenu sysMenu : list) {
			String parentId = sysMenu.getId();
			menuSearchChildren.setParentId(parentId);
			List<SysMenu> childrenList = sysMenuIService.findByEntity(menuSearchChildren);
			String state="open";
			if(childrenList!=null&&childrenList.size()>0){
				state="closed";
			}
			sysMenu.setState(state);
		}
		return list;
	}
	
	/**
	 * 
	 * @Title: toAdd
	 * @Description: 跳转新增页
	 * @param @return
	 * @return ModelAndView
	 *
	 * @author kang
	 * @date 2017年2月3日 下午4:21:01
	 */
	@RequestMapping(value = "/toAdd", method = RequestMethod.GET)
	public ModelAndView toAdd() {
		ModelAndView modelAndView = new ModelAndView("manage/sys/menu/add");
		return modelAndView;
	}
	
	/**
	 * 
	 * @Title: save
	 * @Description: 新增保存
	 * @param @param menu
	 * @param @return
	 * @return String
	 *
	 * @author kang
	 * @date 2017年2月3日 下午4:20:50
	 */
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(SysMenu menu) {
		String str = sysMenuIService.save(menu);
		return str;
	}
	
	/**
	 * 
	 * @Title: toEdit
	 * @Description: 跳转修改页
	 * @param @param menuId
	 * @param @return
	 * @return ModelAndView
	 *
	 * @author kang
	 * @date 2017年2月3日 下午4:20:32
	 */
	@RequestMapping(value = "/toEdit", method = RequestMethod.GET)
	public ModelAndView toEdit(@RequestParam(value = "menuId", required = true) String menuId) {
		ModelAndView modelAndView = new ModelAndView("manage/sys/menu/edit");
		SysMenu menu = sysMenuIService.findById(menuId);
		String parentId = menu.getParentId();
		String parentName="";
		if(Constants.ROOT_NODE.equals(parentId)){
			parentName=Constants.ROOT_NODE_NAME;
		}else{
			SysMenu menuParent = sysMenuIService.findById(parentId);
			parentName = menuParent.getMenuName();
		}
		modelAndView.addObject("menu", menu);
		modelAndView.addObject("parentName", parentName);
		return modelAndView;
	}
	
	/**
	 * 
	 * @Title: edit
	 * @Description: 修改保存
	 * @param @param menu
	 * @param @return
	 * @return Object
	 *
	 * @author kang
	 * @date 2017年2月3日 下午4:20:14
	 */
	@ResponseBody
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String edit(SysMenu menu) {
		String str = sysMenuIService.updateMenu(menu);
		return str;
	}
	
	/**
	 * 
	 * @Title: del
	 * @Description: 根据id删除菜单（有子菜单不允许删除）
	 * @param @param menuId
	 * @param @return
	 * @return String
	 *
	 * @author kang
	 * @date 2017年2月3日 下午4:19:41
	 */
	@ResponseBody
	@RequestMapping(value = "/del", method = RequestMethod.POST)
	public String del(@RequestParam(value = "menuId", required = true) String menuId) {
		String res = sysMenuIService.delMenuById(menuId);
		return res;
	}
	
	/**
	 * 
	 * @Title: findMenuTreeOfSelect
	 * @Description: 新增操作时，选择父节点菜单
	 * @param @param id
	 * @param @return
	 * @return List<Tree>
	 *
	 * @author kang
	 * @date 2017年2月4日 上午11:28:11
	 */
	@RequestMapping(value = "findMenuTreeOfSelect", method = RequestMethod.POST)
	@ResponseBody
	public List<Tree> findMenuTreeOfSelect(@RequestParam(value = "id", required = false) String id) {
		SysMenu menu = null;
		if (!StringUtils.isEmpty(id)) {
			menu=new SysMenu();
			menu.setParentId(id);
		}
		List<Tree> list = sysMenuIService.findMenuTreeOfSelect(menu);
		return list;
	}
}
