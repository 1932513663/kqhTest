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

import shiro.model.sys.SysCompany;
import shiro.service.SysCompanyIService;
import utils.Constants;
import utils.base.StringUtils;
import utils.base.Tree;

/**
 * 
 * @ClassName: CompanyController
 * @Title: CompanyController.java
 * @Description: 公司
 *
 * @author kang
 * @version V1.0 
 * @company 麦田
 * @date 2017年2月7日 上午11:43:48
 */
@RequestMapping("/manage/sys/company")
@Controller
public class CompanyController {
	@Autowired
	private SysCompanyIService sysCompanyIService;

	/**
	 * 
	 * @Title: main
	 * @Description: 公司主页
	 * @param @param companyId
	 * @param @return
	 * @return ModelAndView
	 *
	 * @author kang
	 * @date 2017年1月19日 下午1:46:29
	 */
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public ModelAndView main(@RequestParam(value = "companyId", required = false) String companyId) {
		ModelAndView modelAndView = new ModelAndView("manage/sys/company/main");
		modelAndView.addObject("companyId", companyId);
		return modelAndView;
	}
	
	/**
	 * 
	 * @Title: index
	 * @Description: 跳转公司首页
	 * @param @return
	 * @return ModelAndView
	 *
	 * @author kang
	 * @date 2017年1月19日 下午1:46:10
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView("manage/sys/company/index");
		return modelAndView;
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
		ModelAndView modelAndView = new ModelAndView("manage/sys/company/add");
		return modelAndView;
	}
	
	/**
	 * 
	 * @Title: save
	 * @Description: 新增新公司
	 * @param @param company
	 * @param @return
	 * @return String
	 *
	 * @author kang
	 * @date 2017年1月22日 下午6:48:40
	 */
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(SysCompany company) {
		company.setDel(Constants.DEL_NO);
		String str = sysCompanyIService.save(company);
		return str;
	}
	
	/**
	 * 
	 * @Title: toEdit
	 * @Description: 跳转到修改页
	 * @param @param companyId
	 * @param @return
	 * @return ModelAndView
	 *
	 * @author kang
	 * @date 2017年1月30日 上午10:38:06
	 */
	@RequestMapping(value = "/toEdit", method = RequestMethod.GET)
	public ModelAndView toEdit(@RequestParam(value = "companyId", required = true) String companyId) {
		ModelAndView modelAndView = new ModelAndView("manage/sys/company/edit");
		SysCompany company = sysCompanyIService.findById(companyId);
		String parentId = company.getParentId();
		String parentName="";
		if(Constants.ROOT_NODE.equals(parentId)){
			parentName=Constants.ROOT_NODE_NAME;
		}else{
			SysCompany companyParent = sysCompanyIService.findById(parentId);
			parentName = companyParent.getCompanyName();
		}
		modelAndView.addObject("company", company);
		modelAndView.addObject("parentName", parentName);
		return modelAndView;
	}
	
	/**
	 * 
	 * @Title: edit
	 * @Description: 修改保存
	 * @param @param company
	 * @param @return
	 * @return Object
	 *
	 * @author kang
	 * @date 2017年1月30日 上午11:34:17
	 */
	@ResponseBody
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String edit(SysCompany company) {
		company.setDel(Constants.DEL_NO);
		String str = sysCompanyIService.updateCompany(company);
		return str;
	}
	
	/**
	 * 
	 * @Title: del
	 * @Description: 逻辑删除
	 * @param @param companyId
	 * @param @return
	 * @return String
	 *
	 * @author kang
	 * @date 2017年1月23日 上午10:45:35
	 */
	@ResponseBody
	@RequestMapping(value = "/del", method = RequestMethod.POST)
	public String del(@RequestParam(value = "companyId", required = true) String companyId) {
		String res = sysCompanyIService.delCompanyById(companyId);
		return res;
	}
	
	/**
	 * 
	 * @Title: companyTree
	 * @Description: 跳转公司树型分布页面
	 * @param @return
	 * @return ModelAndView
	 *
	 * @author kang
	 * @date 2017年1月19日 下午1:45:37
	 */
	@RequestMapping(value = "/companyTree", method = RequestMethod.GET)
	public ModelAndView companyTree() {
		ModelAndView modelAndView = new ModelAndView("manage/sys/company/companyTree");
		return modelAndView;
	}

	/**
	 * 
	 * @Title: findCompanyTree
	 * @Description: 查找公司树节点（异步加载）
	 * @param @param id
	 * @param @return
	 * @return List<Tree>
	 *
	 * @author kang
	 * @date 2017年1月19日 下午1:44:56
	 */
	@RequestMapping(value = "findCompanyTree", method = RequestMethod.POST)
	@ResponseBody
	public List<Tree> findCompanyTree(@RequestParam(value = "id", required = false) String id,@RequestParam(value = "companyId", required = true) String companyId) {
		SysCompany sysCompany = new SysCompany();
		List<Tree> list=new ArrayList<Tree>();
		if (StringUtils.isEmpty(id)) {
			if(StringUtils.isEmpty(companyId)){
				id = Constants.ROOT_NODE;
				sysCompany.setParentId(id);
				list = sysCompanyIService.findCompanyTree(sysCompany);
			}else{
				sysCompany.setParentId(companyId);
				list = sysCompanyIService.findCompanyTreeParents(sysCompany);
			}
		}else{
			sysCompany.setParentId(id);
			list = sysCompanyIService.findCompanyTree(sysCompany);
		}
		
		return list;
	}
	
	/**
	 * 
	 * @Title: findCompanyTreeParents
	 * @Description: 从根节点初始化整棵树
	 * @param @param companyId
	 * @param @return
	 * @return List<Tree>
	 *
	 * @author kang
	 * @date 2017年1月24日 上午11:03:40
	 */
	@RequestMapping(value = "findCompanyTreeParents", method = RequestMethod.POST)
	@ResponseBody
	public List<Tree> findCompanyTreeParents(@RequestParam(value = "companyId", required = true) String companyId) {
		SysCompany sysCompany = new SysCompany();
		sysCompany.setParentId(companyId);
		List<Tree> list = sysCompanyIService.findCompanyTreeParents(sysCompany);
		return list;
	}
	
	/**
	 * 
	 * @Title: findCompanyTreeOfSelect
	 * @Description: 新增公司时选择父公司
	 * @param @param id
	 * @param @return
	 * @return List<Tree>
	 *
	 * @author kang
	 * @date 2017年1月24日 上午10:12:51
	 */
	@RequestMapping(value = "findCompanyTreeOfSelect", method = RequestMethod.POST)
	@ResponseBody
	public List<Tree> findCompanyTreeOfSelect(@RequestParam(value = "id", required = false) String id) {
		SysCompany sysCompany = null;
		if (!StringUtils.isEmpty(id)) {
			sysCompany=new SysCompany();
			sysCompany.setParentId(id);
		}
		List<Tree> list = sysCompanyIService.findCompanyTreeOfSelect(sysCompany);
		return list;
	}
	
	/**
	 * 
	 * @Title: company
	 * @Description: 选中公司详情
	 * @param @param companyId
	 * @param @return
	 * @return ModelAndView
	 *
	 * @author kang
	 * @date 2017年1月19日 下午1:44:30
	 */
	@RequestMapping(value = "/company", method = RequestMethod.GET)
	public ModelAndView company(@RequestParam(value = "companyId", required = true) String companyId) {
		ModelAndView modelAndView = new ModelAndView("manage/sys/company/company");
		SysCompany company = sysCompanyIService.findById(companyId);
		modelAndView.addObject("companyId", companyId);
		modelAndView.addObject("company", company);
		return modelAndView;
	}
}
