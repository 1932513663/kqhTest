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

import shiro.model.sys.SysDepartment;
import shiro.service.SysDepartmentIService;
import utils.Constants;
import utils.base.StringUtils;
import utils.base.Tree;

/**
 * 
 * @ClassName: DepartmentController
 * @Title: DepartmentController.java
 * @Description: 部门
 *
 * @author kang
 * @version V1.0 
 * @company 麦田
 * @date 2017年2月7日 上午11:43:37
 */
@RequestMapping("/manage/sys/department")
@Controller
public class DepartmentController {
	@Autowired
	private SysDepartmentIService sysDepartmentIService;
	
	/**
	 * 
	 * @Title: index
	 * @Description: 部门首页
	 * @param @return
	 * @return ModelAndView
	 *
	 * @author kang
	 * @date 2017年1月19日 下午2:05:32
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index(@RequestParam(value = "companyId", required = true) String companyId) {
		ModelAndView modelAndView = new ModelAndView("manage/sys/dept/index");
		modelAndView.addObject("companyId", companyId);
		return modelAndView;
	}
	
	/**
	 * 
	 * @Title: toAdd
	 * @Description: 跳转到部门新增页面
	 * @param @param companyId
	 * @param @return
	 * @return ModelAndView
	 *
	 * @author kang
	 * @date 2017年1月24日 下午2:56:22
	 */
	@RequestMapping(value = "/toAdd", method = RequestMethod.GET)
	public ModelAndView toAdd(@RequestParam(value = "companyId", required = true) String companyId) {
		ModelAndView modelAndView = new ModelAndView("manage/sys/dept/add");
		modelAndView.addObject("companyId", companyId);
		return modelAndView;
	}
	
	/**
	 * 
	 * @Title: save
	 * @Description: 单一公司下部门新增
	 * @param @param dept
	 * @param @return
	 * @return Object
	 *
	 * @author kang
	 * @date 2017年1月24日 下午3:50:16
	 */
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(SysDepartment dept) {
		dept.setDel(Constants.DEL_NO);
		String str = sysDepartmentIService.save(dept);
		return str;
	}
	
	/**
	 * 
	 * @Title: toEdit
	 * @Description: 跳转到修改页
	 * @param @param deptId
	 * @param @return
	 * @return ModelAndView
	 *
	 * @author kang
	 * @date 2017年1月30日 上午10:38:06
	 */
	@RequestMapping(value = "/toEdit", method = RequestMethod.GET)
	public ModelAndView toEdit(@RequestParam(value = "deptId", required = true) String deptId) {
		ModelAndView modelAndView = new ModelAndView("manage/sys/dept/edit");
		SysDepartment dept = sysDepartmentIService.findById(deptId);
		String parentId = dept.getParentId();
		String parentName="";
		if(Constants.ROOT_NODE.equals(parentId)){
			parentName=Constants.ROOT_NODE_NAME;
		}else{
			SysDepartment deptParent = sysDepartmentIService.findById(parentId);
			parentName = deptParent.getDeptName();
		}
		modelAndView.addObject("dept", dept);
		modelAndView.addObject("parentName", parentName);
		return modelAndView;
	}
	
	/**
	 * 
	 * @Title: edit
	 * @Description: 修改保存
	 * @param @param dept
	 * @param @return
	 * @return Object
	 *
	 * @author kang
	 * @date 2017年1月30日 上午11:34:17
	 */
	@ResponseBody
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String edit(SysDepartment dept) {
		String str = sysDepartmentIService.updateDept(dept);
		return str;
	}
	
	/**
	 * 
	 * @Title: dept
	 * @Description: 选中部门详细信息
	 * @param @param deptId
	 * @param @return
	 * @return ModelAndView
	 *
	 * @author kang
	 * @date 2017年1月19日 下午2:04:09
	 */
	@RequestMapping(value = "/dept", method = RequestMethod.GET)
	public ModelAndView dept(@RequestParam(value = "deptId", required = true) String deptId) {
		ModelAndView modelAndView = new ModelAndView("manage/sys/dept/dept");
		SysDepartment dept = sysDepartmentIService.findById(deptId);
		if(dept!=null){
			String companyId = dept.getCompanyId();
			modelAndView.addObject("companyId", companyId);
		}
		modelAndView.addObject("dept", dept);
		modelAndView.addObject("deptId", deptId);
		return modelAndView;
	}
	
	/**
	 * 
	 * @Title: deptTree
	 * @Description: 跳转到部门树页面
	 * @param @return
	 * @return ModelAndView
	 *
	 * @author kang
	 * @date 2017年1月19日 下午2:04:29
	 */
	@RequestMapping(value = "/deptTree", method = RequestMethod.GET)
	public ModelAndView deptTree(@RequestParam(value = "companyId", required = true) String companyId) {
		ModelAndView modelAndView = new ModelAndView("manage/sys/dept/deptTree");
		modelAndView.addObject("companyId", companyId);
		return modelAndView;
	}
	
	/**
	 * 
	 * @Title: main
	 * @Description: 部门主页面
	 * @param @param deptId
	 * @param @return
	 * @return ModelAndView
	 *
	 * @author kang
	 * @date 2017年1月19日 下午2:04:55
	 */
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public ModelAndView main(@RequestParam(value = "companyId", required = false) String companyId,@RequestParam(value = "deptId", required = false) String deptId) {
		ModelAndView modelAndView = new ModelAndView("manage/sys/dept/main");
		modelAndView.addObject("companyId", companyId);
		modelAndView.addObject("deptId", deptId);
		return modelAndView;
	}
	
	/**
	 * 
	 * @Title: findDeptTree
	 * @Description: 查找部门树（异步加载）
	 * @param @param id
	 * @param @param companyId
	 * @param @return
	 * @return List<Tree>
	 *
	 * @author kang
	 * @date 2017年1月19日 下午2:05:08
	 */
	@RequestMapping(value = "findDeptTree", method = RequestMethod.POST)
	@ResponseBody
	public List<Tree> findDeptTree(@RequestParam(value = "id", required = false) String id,@RequestParam(value = "companyId", required = true) String companyId,@RequestParam(value = "deptId", required = true) String deptId) {
		List<Tree> list=new ArrayList<Tree>();
		SysDepartment dept = new SysDepartment();
		if (StringUtils.isEmpty(id)) {
			if(StringUtils.isEmpty(deptId)){
				id = "00000000000000000000000000000000";
				dept.setParentId(id);
				dept.setCompanyId(companyId);
				list = sysDepartmentIService.findDeptTree(dept);
			}else{
				dept.setParentId(deptId);
				dept.setCompanyId(companyId);
				list = sysDepartmentIService.findDeptTreeParents(dept);
			}
		}else{
			dept.setParentId(id);
			dept.setCompanyId(companyId);
			list = sysDepartmentIService.findDeptTree(dept);
		}
		return list;
	}
	
	/**
	 * 
	 * @Title: findDeptTreeOfSelect
	 * @Description: 新增根据条件选择该部门下的一级子部门
	 * @param @param companyId
	 * @param @param id
	 * @param @return
	 * @return List<Tree>
	 *
	 * @author kang
	 * @date 2017年1月28日 上午9:27:27
	 */
	@RequestMapping(value = "findDeptTreeOfSelect", method = RequestMethod.POST)
	@ResponseBody
	public List<Tree> findDeptTreeOfSelect(@RequestParam(value = "companyId", required = false) String companyId,@RequestParam(value = "id", required = false) String id) {
		SysDepartment dept = null;
		if (!StringUtils.isEmpty(id)) {
			dept=new SysDepartment();
			dept.setParentId(id);
		}
		List<Tree> list = sysDepartmentIService.findDeptTreeOfSelect(companyId,dept);
		return list;
	}
	
	/**
	 *
	 * @Title: del
	 * @Description:  逻辑删除（判断此部门下面有木有部门）
	 * @param @param deptId
	 * @param @return
	 * @return String
	 *
	 * @author kang
	 * @date 2017年1月24日 下午4:36:46
	 */
	@ResponseBody
	@RequestMapping(value = "/del", method = RequestMethod.POST)
	public String del(@RequestParam(value = "deptId", required = true) String deptId) {
		String res = sysDepartmentIService.delDeptById(deptId);
		return res;
	}
}
