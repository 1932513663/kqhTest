package shiro.service;

import java.util.List;

import core.base.service.BaseIService;
import shiro.model.sys.SysDepartment;
import utils.base.Tree;

public interface SysDepartmentIService extends BaseIService<SysDepartment> {
	
	/**
	 * 
	 * @Title: findDeptTree
	 * @Description: 根据条件查找职位集合
	 * @param @param sysDepartment
	 * @param @return
	 * @return List<Tree>
	 *
	 * @author kang
	 * @date 2017年1月27日 下午3:17:31
	 */
	public List<Tree> findDeptTree(SysDepartment sysDepartment);
	
	/**
	 * sysDepartment中companyId值必为null
	 * @Title: findDeptTreeOfSelect
	 * @Description: 根据条件选择该部门下的一级子部门
	 * @param @param companyId
	 * @param @param sysDepartment
	 * @param @return
	 * @return List<Tree>
	 *
	 * @author kang
	 * @date 2017年1月24日 下午3:39:41
	 */
	public List<Tree> findDeptTreeOfSelect(String companyId,SysDepartment sysDepartment);
	
	/**
	 * 
	 * @Title: selectDept
	 * @Description: 选择部门
	 * @param @param sysDepartment
	 * @param @return
	 * @return List<Tree>
	 *
	 * @author kang
	 * @date 2017年2月6日 下午2:10:52
	 */
	public List<Tree> selectDept(SysDepartment sysDepartment);
	
	/**
	 * 
	 * @Title: findDeptTreeParents
	 * @Description: 根据已知的子部门查找他的所有父节点及同级兄弟节点
	 * @param @param sysDepartment:为构造的查找选中节点所有子节点的条件
	 * @param @return
	 * @return List<Tree>
	 *
	 * @author kang
	 * @date 2017年1月27日 下午3:17:08
	 */
	public List<Tree> findDeptTreeParents(SysDepartment sysDepartment);

	/**
	 * 
	 * @Title: delDeptById
	 * @Description: 根据id逻辑删除
	 * @param @param id
	 * @param @return
	 * @return String
	 *
	 * @author kang
	 * @date 2017年1月27日 下午3:17:00
	 */
	public String delDeptById(String id);

	/**
	 * 
	 * @Title: save
	 * @Description: 保存
	 * @param @param dept
	 * @param @return
	 * @return String
	 *
	 * @author kang
	 * @date 2017年2月10日 下午12:57:28
	 */
	public String save(SysDepartment dept);

	/**
	 * 
	 * @Title: updateDept
	 * @Description: 修改
	 * @param @param dept
	 * @param @return
	 * @return String
	 *
	 * @author kang
	 * @date 2017年2月10日 下午12:57:38
	 */
	public String updateDept(SysDepartment dept);

	/**
	 * 
	 * @Title: findAreaByParentId
	 * @Description: TODO
	 * @param @param businessDepartmentId
	 * @param @return
	 * @return List<SysDepartment>
	 *
	 * @author kang
	 * @date 2017年2月10日 下午3:23:33
	 */
	public List<SysDepartment> findAreaByParentId(String businessDepartmentId);

}
