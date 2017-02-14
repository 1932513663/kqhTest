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

import shiro.model.sys.SysPost;
import shiro.service.SysPostIService;
import utils.Constants;
import utils.base.StringUtils;
import utils.base.Tree;

/**
 * 
 * @ClassName: PostController
 * @Title: PostController.java
 * @Description: 职位
 *
 * @author kang
 * @version V1.0 
 * @company 麦田
 * @date 2017年2月7日 上午11:43:12
 */
@RequestMapping("/manage/sys/post")
@Controller
public class PostController {
	@Autowired
	private SysPostIService sysPostIService;
	
	/**
	 * 
	 * @Title: index
	 * @Description: 职位首页
	 * @param @return
	 * @return ModelAndView
	 *
	 * @author kang
	 * @date 2017年1月19日 下午2:05:32
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index(@RequestParam(value = "companyId", required = true) String companyId) {
		ModelAndView modelAndView = new ModelAndView("manage/sys/post/index");
		modelAndView.addObject("companyId", companyId);
		return modelAndView;
	}
	
	/**
	 * 
	 * @Title: deptTree
	 * @Description: 职位页面部门树形表
	 * @param @param companyId
	 * @param @return
	 * @return ModelAndView
	 *
	 * @author kang
	 * @date 2017年1月20日 下午4:27:10
	 */
	@RequestMapping(value = "/deptTree", method = RequestMethod.GET)
	public ModelAndView deptTree(@RequestParam(value = "companyId", required = true) String companyId) {
		ModelAndView modelAndView = new ModelAndView("manage/sys/post/deptTree");
		modelAndView.addObject("companyId", companyId);
		return modelAndView;
	}
	
	/**
	 * 
	 * @Title: dept
	 * @Description: 选中职位详细信息
	 * @param @param deptId
	 * @param @return
	 * @return ModelAndView
	 *
	 * @author kang
	 * @date 2017年1月19日 下午2:04:09
	 */
	@RequestMapping(value = "/post", method = RequestMethod.GET)
	public ModelAndView post(@RequestParam(value = "postId", required = true) String postId,@RequestParam(value = "deptId", required = true) String deptId) {
		ModelAndView modelAndView = new ModelAndView("manage/sys/post/post");
		SysPost post = sysPostIService.findById(postId);
		if(post!=null){
			String companyId = post.getCompanyId();
			modelAndView.addObject("companyId", companyId);
		}
		modelAndView.addObject("post", post);
		modelAndView.addObject("postId", postId);
		modelAndView.addObject("deptId", deptId);
		return modelAndView;
	}
	
	/**
	 * 
	 * @Title: deptTree
	 * @Description: 跳转到职位树页面
	 * @param @return
	 * @return ModelAndView
	 *
	 * @author kang
	 * @date 2017年1月19日 下午2:04:29
	 */
	@RequestMapping(value = "/postTree", method = RequestMethod.GET)
	public ModelAndView postTree(@RequestParam(value = "companyId", required = true) String companyId,@RequestParam(value = "deptId", required = true) String deptId) {
		ModelAndView modelAndView = new ModelAndView("manage/sys/post/postTree");
		modelAndView.addObject("companyId", companyId);
		modelAndView.addObject("deptId", deptId);
		return modelAndView;
	}
	
	/**
	 * 
	 * @Title: mainIndex
	 * @Description: 职位mainIndex页
	 * @param @param deptId
	 * @param @return
	 * @return ModelAndView
	 *
	 * @author kang
	 * @date 2017年1月19日 下午2:04:55
	 */
	@RequestMapping(value = "/mainIndex", method = RequestMethod.GET)
	public ModelAndView mainIndex(@RequestParam(value = "companyId", required = true) String companyId,@RequestParam(value = "deptId", required = false) String deptId) {
		ModelAndView modelAndView = new ModelAndView("manage/sys/post/mainIndex");
		modelAndView.addObject("companyId", companyId);
		modelAndView.addObject("deptId", deptId);
		return modelAndView;
	}
	
	/**
	 * 
	 * @Title: main
	 * @Description: 职位主页面
	 * @param @param deptId
	 * @param @return
	 * @return ModelAndView
	 *
	 * @author kang
	 * @date 2017年1月19日 下午2:04:55
	 */
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public ModelAndView main(@RequestParam(value = "companyId", required = false) String companyId,@RequestParam(value = "deptId", required = false) String deptId,@RequestParam(value = "postId", required = false) String postId) {
		ModelAndView modelAndView = new ModelAndView("manage/sys/post/main");
		modelAndView.addObject("companyId", companyId);
		modelAndView.addObject("deptId", deptId);
		modelAndView.addObject("postId", postId);
		return modelAndView;
	}
	
	/**
	 * 
	 * @Title: findDeptTree
	 * @Description: 查找职位树（异步加载）
	 * @param @param id
	 * @param @param companyId
	 * @param @return
	 * @return List<Tree>
	 *
	 * @author kang
	 * @date 2017年1月19日 下午2:05:08
	 */
	@RequestMapping(value = "findPostTree", method = RequestMethod.POST)
	@ResponseBody
	public List<Tree> findPostTree(@RequestParam(value = "id", required = false) String id,@RequestParam(value = "companyId", required = true) String companyId,@RequestParam(value = "deptId", required = true) String deptId,@RequestParam(value = "postId", required = true) String postId) {
		List<Tree> list=new ArrayList<Tree>();
		SysPost post = new SysPost();
		if (StringUtils.isEmpty(id)) {
			if(StringUtils.isEmpty(postId)){
				id = "00000000000000000000000000000000";
				post.setParentId(id);
				post.setCompanyId(companyId);
				post.setDeptId(deptId);
				list = sysPostIService.findPostTree(post);
			}else{
				post.setParentId(postId);
				post.setCompanyId(companyId);
				post.setDeptId(deptId);
				list = sysPostIService.findPostTreeParents(post);
			}
		}else{
			post.setParentId(id);
			post.setCompanyId(companyId);
			post.setDeptId(deptId);
			list = sysPostIService.findPostTree(post);
		}
		return list;
	}
	
	/**
	 * 
	 * @Title: toAdd
	 * @Description: 跳转职位新增页面
	 * @param @param companyId
	 * @param @param deptId
	 * @param @return
	 * @return ModelAndView
	 *
	 * @author kang
	 * @date 2017年1月27日 下午2:24:50
	 */
	@RequestMapping(value = "/toAdd", method = RequestMethod.GET)
	public ModelAndView toAdd(@RequestParam(value = "companyId", required = true) String companyId,@RequestParam(value = "deptId", required = true) String deptId) {
		ModelAndView modelAndView = new ModelAndView("manage/sys/post/add");
		modelAndView.addObject("companyId", companyId);
		modelAndView.addObject("deptId", deptId);
		return modelAndView;
	}
	
	/**
	 * 
	 * @Title: save
	 * @Description: 新增职位
	 * @param @param post
	 * @param @return
	 * @return String
	 *
	 * @author kang
	 * @date 2017年1月27日 下午2:30:47
	 */
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(SysPost post) {
		post.setDel(Constants.DEL_NO);
		String str = sysPostIService.save(post);
		return str;
	}
	
	/**
	 * 
	 * @Title: toEdit
	 * @Description: 跳转到修改页
	 * @param @param postId
	 * @param @return
	 * @return ModelAndView
	 *
	 * @author kang
	 * @date 2017年1月30日 上午10:38:06
	 */
	@RequestMapping(value = "/toEdit", method = RequestMethod.GET)
	public ModelAndView toEdit(@RequestParam(value = "postId", required = true) String postId) {
		ModelAndView modelAndView = new ModelAndView("manage/sys/post/edit");
		SysPost post = sysPostIService.findById(postId);
		String parentId = post.getParentId();
		String parentName="";
		if(Constants.ROOT_NODE.equals(parentId)){
			parentName=Constants.ROOT_NODE_NAME;
		}else{
			SysPost postParent = sysPostIService.findById(parentId);
			parentName = postParent.getPostName();
		}
		modelAndView.addObject("post", post);
		modelAndView.addObject("parentName", parentName);
		return modelAndView;
	}
	
	/**
	 * 
	 * @Title: edit
	 * @Description: 修改保存
	 * @param @param post
	 * @param @return
	 * @return Object
	 *
	 * @author kang
	 * @date 2017年1月30日 上午11:34:17
	 */
	@ResponseBody
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String edit(SysPost post) {
		String str = sysPostIService.updatePost(post);
		return str;
	}
	
	/**
	 * 
	 * @Title: findPostTreeOfSelect
	 * @Description: 新增职位时选择父节点
	 * @param @param companyId
	 * @param @param deptId
	 * @param @param id
	 * @param @return
	 * @return List<Tree>
	 *
	 * @author kang
	 * @date 2017年1月27日 下午2:48:59
	 */
	@RequestMapping(value = "findPostTreeOfSelect", method = RequestMethod.POST)
	@ResponseBody
	public List<Tree> findPostTreeOfSelect(@RequestParam(value = "companyId", required = false) String companyId,@RequestParam(value = "deptId", required = false) String deptId,@RequestParam(value = "id", required = false) String id) {
		SysPost post = null;
		if (!StringUtils.isEmpty(id)) {
			post=new SysPost();
			post.setParentId(id);
		}
		List<Tree> list = sysPostIService.findPostTreeOfSelect(companyId, deptId, post);
		return list;
	}
	
	/**
	 * 
	 * @Title: del
	 * @Description: 逻辑删除
	 * @param @param postId
	 * @param @return
	 * @return String
	 *
	 * @author kang
	 * @date 2017年1月28日 上午9:24:13
	 */
	@ResponseBody
	@RequestMapping(value = "/del", method = RequestMethod.POST)
	public String del(@RequestParam(value = "postId", required = true) String postId) {
		String res = sysPostIService.delDeptById(postId);
		return res;
	}
}
