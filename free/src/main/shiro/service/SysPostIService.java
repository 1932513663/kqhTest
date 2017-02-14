package shiro.service;

import java.util.List;

import core.base.service.BaseIService;
import shiro.model.sys.SysPost;
import utils.base.Tree;

public interface SysPostIService extends BaseIService<SysPost> {
	
	/**
	 * 
	 * @Title: findPostTree
	 * @Description: 根据条件查找职位集合
	 * @param @param sysPost
	 * @param @return
	 * @return List<Tree>
	 *
	 * @author kang
	 * @date 2017年1月27日 下午3:14:14
	 */
	public List<Tree> findPostTree(SysPost sysPost);

	/**
	 * 
	 * @Title: findPostTreeOfSelect
	 * @Description: 根据条件选择该职位下的一级子职位
	 * @param @param companyId
	 * @param @param deptId
	 * @param @param sysPost
	 * @param @return
	 * @return List<Tree>
	 *
	 * @author kang
	 * @date 2017年1月27日 下午3:14:50
	 */
	public List<Tree> findPostTreeOfSelect(String companyId, String deptId, SysPost sysPost);

	/**
	 * 
	 * @Title: selectPost
	 * @Description: 选择职位
	 * @param @param companyId
	 * @param @param deptId
	 * @param @param sysPost
	 * @param @return
	 * @return List<Tree>
	 *
	 * @author kang
	 * @date 2017年2月6日 下午2:14:26
	 */
	public List<Tree> selectPost(SysPost sysPost);
	
	/**
	 * 
	 * @Title: findPostTreeParents
	 * @Description: 根据已知的子职位查找他的所有父节点及同级兄弟节点
	 * @param @param sysPost
	 * @param @return
	 * @return List<Tree>
	 *
	 * @author kang
	 * @date 2017年1月27日 下午3:15:47
	 */
	public List<Tree> findPostTreeParents(SysPost sysPost);

	/**
	 * 
	 * @Title: delDeptById
	 * @Description: 根据id逻辑删除
	 * @param @param id
	 * @param @return
	 * @return String
	 *
	 * @author kang
	 * @date 2017年1月27日 下午3:16:29
	 */
	public String delDeptById(String id);

	/**
	 * 
	 * @Title: save
	 * @Description: 保存
	 * @param @param post
	 * @param @return
	 * @return String
	 *
	 * @author kang
	 * @date 2017年2月10日 下午1:08:19
	 */
	public String save(SysPost post);

	/**
	 * 
	 * @Title: updatePost
	 * @Description: 修改
	 * @param @param post
	 * @param @return
	 * @return String
	 *
	 * @author kang
	 * @date 2017年2月10日 下午1:08:30
	 */
	public String updatePost(SysPost post);

}
