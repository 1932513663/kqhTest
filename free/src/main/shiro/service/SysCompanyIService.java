package shiro.service;

import java.util.List;

import core.base.service.BaseIService;
import shiro.model.sys.SysCompany;
import utils.base.Tree;

public interface SysCompanyIService extends BaseIService<SysCompany> {
	
	/**
	 * 
	 * @Title: findCompanyTree
	 * @Description: 根据条件查找职位集合
	 * @param @param sysCompany
	 * @param @return
	 * @return List<Tree>
	 *
	 * @author kang
	 * @date 2017年1月27日 下午3:17:55
	 */
	public List<Tree> findCompanyTree(SysCompany sysCompany);
	
	/**
	 * 
	 * @Title: findCompanyTreeOfSelect
	 * @Description: 根据条件选择该公司下的一级子公司
	 * @param @param sysCompany
	 * @param @return
	 * @return List<Tree>
	 *
	 * @author kang
	 * @date 2017年1月27日 下午3:19:25
	 */
	public List<Tree> findCompanyTreeOfSelect(SysCompany sysCompany);
	
	/**
	 * 
	 * @Title: selectCompany
	 * @Description: 选则已存在的公司
	 * @param @param sysCompany
	 * @param @return
	 * @return List<Tree>
	 *
	 * @author kang
	 * @date 2017年2月6日 下午2:06:14
	 */
	List<Tree> selectCompany(SysCompany sysCompany);

	/**
	 * 
	 * @Title: findCompanyTreeParents
	 * @Description: 根据已知的子公司查找他的所有父节点及同级兄弟节点
	 * @param @param sysCompany
	 * @param @return
	 * @return List<Tree>
	 *
	 * @author kang
	 * @date 2017年1月27日 下午3:18:33
	 */
	public List<Tree> findCompanyTreeParents(SysCompany sysCompany);
	
	/**
	 * 
	 * @Title: delCompanyById
	 * @Description: 根据id逻辑删除
	 * @param @param id
	 * @param @return
	 * @return String
	 *
	 * @author kang
	 * @date 2017年1月27日 下午3:18:10
	 */
	public String delCompanyById(String id);

	/**
	 * 
	 * @Title: save
	 * @Description: 保存
	 * @param @param company
	 * @param @return
	 * @return String
	 *
	 * @author kang
	 * @date 2017年2月10日 下午12:46:43
	 */
	public String save(SysCompany company);

	/**
	 * 
	 * @Title: updateCompany
	 * @Description: 修改
	 * @param @param company
	 * @param @return
	 * @return String
	 *
	 * @author kang
	 * @date 2017年2月10日 下午12:53:37
	 */
	public String updateCompany(SysCompany company);
}
