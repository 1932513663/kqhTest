package shiro.web.manage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;

import shiro.model.sys.SysCompany;
import shiro.model.sys.SysDepartment;
import shiro.model.sys.SysPost;
import shiro.model.sys.SysUser;
import shiro.service.SysCompanyIService;
import shiro.service.SysDepartmentIService;
import shiro.service.SysPostIService;
import shiro.service.SysUserIService;
import shiro.service.SysUserRoleIService;
import shiro.utils.MD5Util;
import utils.Constants;
import utils.base.Tree;

/**
 * 
 * @ClassName: UserController
 * @Title: UserController.java
 * @Description: 系统用户管理
 *
 * @author kang
 * @version V1.0 
 * @company 麦田
 * @date 2017年2月7日 上午11:42:36
 */
@RequestMapping("/manage/sys/user")
@Controller
public class UserController {
	
	private static final Logger LOGGER = Logger.getLogger(UserController.class);

	@Autowired
	private SysUserIService sysUserIService;
	@Autowired
	private SysCompanyIService sysCompanyIService;
	@Autowired
	private SysDepartmentIService sysDepartmentIService;
	@Autowired
	private SysPostIService sysPostIService;
	@Autowired
	private SysUserRoleIService sysUserRoleIService;
	
	/**
	 * 
	 * @Title: toIndex
	 * @Description: 跳转系统用户首页
	 * @param @return
	 * @return ModelAndView
	 *
	 * @author kang
	 * @date 2017年2月2日 下午4:50:43
	 */
	@RequestMapping(value = "/toIndex", method = RequestMethod.GET)
	public ModelAndView toIndex() {
		LOGGER.info("跳转系统用户首页");
		ModelAndView modelAndView = new ModelAndView("manage/sys/user/index");
		return modelAndView;
	}
	
	/**
	 * 
	 * @Title: toList
	 * @Description: 跳转系统用户列表页
	 * @param @return
	 * @return ModelAndView
	 *
	 * @author kang
	 * @date 2017年2月2日 下午4:43:56
	 */
	@RequestMapping(value = "/toList", method = RequestMethod.GET)
	public ModelAndView toList() {
		LOGGER.info("跳转系统用户列表页");
		ModelAndView modelAndView = new ModelAndView("manage/sys/user/list");
		return modelAndView;
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
		LOGGER.info("跳转新增页");
		ModelAndView modelAndView = new ModelAndView("manage/sys/user/add");
		return modelAndView;
	}
	
	/**
	 * 
	 * @Title: save
	 * @Description: 新增保存
	 * @param @param user
	 * @param @return
	 * @return Object
	 *
	 * @author kang
	 * @date 2017年2月3日 下午4:20:50
	 */
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(SysUser user) {
		LOGGER.info("新增保存");
		String password = user.getPassword();
		if(!StringUtils.isEmpty(password)){
			String pwdMd5 = MD5Util.MD5(password.toString()).substring(8, 24);
			user.setPassword(pwdMd5);
		}
		String str = sysUserIService.save(user);
		return str;
	}
	
	/**
	 * 
	 * @Title: toEdit
	 * @Description: 跳转修改页
	 * @param @param userId
	 * @param @return
	 * @return ModelAndView
	 *
	 * @author kang
	 * @date 2017年2月3日 下午4:20:32
	 */
	@RequestMapping(value = "/toEdit", method = RequestMethod.GET)
	public ModelAndView toEdit(@RequestParam(value = "userId", required = true) String userId) {
		LOGGER.info("跳转修改页");
		ModelAndView modelAndView = new ModelAndView("manage/sys/user/edit");
		SysUser user = sysUserIService.findById(userId);
		modelAndView.addObject("user", user);
		return modelAndView;
	}
	
	/**
	 * 
	 * @Title: edit
	 * @Description: 修改保存
	 * @param @param user
	 * @param @return
	 * @return Object
	 *
	 * @author kang
	 * @date 2017年2月3日 下午4:20:14
	 */
	@ResponseBody
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String edit(SysUser user) {
		LOGGER.info("修改保存");
		String str = sysUserIService.updateUser(user);
		return str;
	}
	
	/**
	 * 
	 * @Title: del
	 * @Description: 根据id删除系统用户
	 * @param @param userId
	 * @param @return
	 * @return String
	 *
	 * @author kang
	 * @date 2017年2月3日 下午4:19:41
	 */
	@ResponseBody
	@RequestMapping(value = "/del", method = RequestMethod.POST)
	public String del(@RequestParam(value = "userId", required = true) String userId) {
		LOGGER.info("根据userId删除系统用户");
		String res = sysUserIService.delUserById(userId);
		return res;
	}
	
	@RequestMapping(value = "findUserDatagrid", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> findUserDatagrid(@RequestParam(value = "userName",required=false) String userName,@RequestParam(value = "mobile",required=false) String mobile,@RequestParam(value = "page") int currentPage, @RequestParam("rows") int rows,
	        @RequestParam(value = "sort") String sort, @RequestParam("order") String order) {
		Map<String, Object> map = new HashMap<String, Object>();
		SysUser user = new SysUser();
		if(!StringUtils.isEmpty(userName)){
			user.setUserName(userName);
		}
		if(!StringUtils.isEmpty(mobile)){
			user.setMobile(mobile);
		}
		PageInfo<SysUser> findPage = sysUserIService.findPage(currentPage, rows, user);
		map.put("total", findPage.getTotal());
		map.put("rows", findPage.getList());
		return map;
	}
	
	/**
	 * 
	 * @Title: selectCompany
	 * @Description: 查找公司
	 * @param @param id
	 * @param @return
	 * @return List<Tree>
	 *
	 * @author kang
	 * @date 2017年2月6日 上午11:33:56
	 */
	@RequestMapping(value = "selectCompany", method = RequestMethod.POST)
	@ResponseBody
	public List<Tree> selectCompany(@RequestParam(value = "id", required = false) String id,@RequestParam(value = "selectId", required = false) String selectId) {
		List<Tree> list=null;
		SysCompany company = new SysCompany();
		if (StringUtils.isEmpty(id)) {
			if (StringUtils.isEmpty(selectId)) {
				id=Constants.ROOT_NODE;
				company.setParentId(id);
				list = sysCompanyIService.selectCompany(company);
			}else{
				company.setParentId(selectId);
				list = sysCompanyIService.findCompanyTreeParents(company);
			}
		}else{
			company.setParentId(id);
			list = sysCompanyIService.selectCompany(company);
		}
		return list;
	}
	
	/**
	 * 
	 * @Title: selectDept
	 * @Description: 查找部门
	 * @param @param companyId
	 * @param @param id
	 * @param @return
	 * @return List<Tree>
	 *
	 * @author kang
	 * @date 2017年2月6日 上午11:36:54
	 */
	@RequestMapping(value = "selectDept", method = RequestMethod.POST)
	@ResponseBody
	public List<Tree> selectDept(@RequestParam(value = "companyId", required = true) String companyId,@RequestParam(value = "id", required = false) String id,@RequestParam(value = "selectId", required = false) String selectId) {
		List<Tree> list=null;
		SysDepartment dept = new SysDepartment();
		dept.setCompanyId(companyId);
		if (StringUtils.isEmpty(id)) {
			if (StringUtils.isEmpty(selectId)) {
				id=Constants.ROOT_NODE;
				dept.setParentId(id);
				list = sysDepartmentIService.selectDept(dept);
			}else{
				dept.setParentId(selectId);
				list = sysDepartmentIService.findDeptTreeParents(dept);
			}
		}else{
			dept.setParentId(id);
			list = sysDepartmentIService.selectDept(dept);
		}
		return list;
	}
	
	/**
	 * 
	 * @Title: selectPost
	 * @Description: 查找职位
	 * @param @param companyId
	 * @param @param deptId
	 * @param @param id
	 * @param @return
	 * @return List<Tree>
	 *
	 * @author kang
	 * @date 2017年2月6日 上午11:38:32
	 */
	@RequestMapping(value = "selectPost", method = RequestMethod.POST)
	@ResponseBody
	public List<Tree> selectPost(@RequestParam(value = "companyId", required = true) String companyId,@RequestParam(value = "deptId", required = true) String deptId,@RequestParam(value = "id", required = false) String id,@RequestParam(value = "selectId", required = false) String selectId) {
		List<Tree> list=null;
		SysPost post = new SysPost();
		post.setCompanyId(companyId);
		post.setDeptId(deptId);
		if (StringUtils.isEmpty(id)) {
			if (StringUtils.isEmpty(selectId)) {
				id=Constants.ROOT_NODE;
				post.setParentId(id);
				list = sysPostIService.selectPost(post);
			}else{
				post.setParentId(selectId);
				list = sysPostIService.findPostTreeParents(post);
			}
		}else{
			post.setParentId(id);
			list = sysPostIService.selectPost(post);
		}
		return list;
	}
	
	/**
	 * 
	 * @Title: findRoleTreeOfUser
	 * @Description: 通过用户id返回角色树，且已已勾选的配置标识
	 * @param @param userId
	 * @param @return
	 * @return List<Tree>
	 *
	 * @author kang
	 * @date 2017年2月7日 下午3:52:21
	 */
	@RequestMapping(value = "findRoleTreeOfUser", method = RequestMethod.POST)
	@ResponseBody
	public List<Tree> findRoleTreeOfUser(@RequestParam(value = "userId", required = true) String userId) {
		LOGGER.info("通过用户id返回角色树，且已已勾选的配置标识");
		List<Tree> list = sysUserRoleIService.findRoleTreeOfUser(userId);
		return list;
	}
	
	@ResponseBody
	@RequestMapping(value = "/saveRoleUsers", method = RequestMethod.POST)
	public String saveRoleUsers(String userRolesStr) {
		LOGGER.info("新增保存角色权限");
		String str=sysUserRoleIService.saveRoleUsers(userRolesStr);
		return str;
	}
}


