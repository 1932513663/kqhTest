package shiro.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import core.base.service.impl.BaseServiceImpl;
import shiro.dao.sys.SysDepartmentMapper;
import shiro.model.sys.SysDepartment;
import shiro.service.SysDepartmentIService;
import utils.Constants;
import utils.JsonUtils;
import utils.base.Tree;

@Service
public class SysDepartmentServiceImpl extends BaseServiceImpl<SysDepartment> implements SysDepartmentIService {

	@Autowired
	private SysDepartmentMapper sysDepartmentMapper;

	@Override
	public List<Tree> findDeptTree(SysDepartment sysDepartment) {
		List<SysDepartment> deptList = findByEntity(sysDepartment);
		List<Tree> list = new ArrayList<Tree>();
		SysDepartment deptNew = new SysDepartment();
		for (SysDepartment dept : deptList) {
			Tree tree = new Tree();
			tree.setId(dept.getId());
			deptNew.setParentId(dept.getId());
			List<SysDepartment> companyChildrenList = findByEntity(deptNew);
			String state = "open";
			if (companyChildrenList != null && companyChildrenList.size() > 0) {
				state = "closed";
			}
			tree.setState(state);
			tree.setText(dept.getDeptName());
			list.add(tree);
		}
		return list;
	}

	@Override
	public List<Tree> findDeptTreeOfSelect(String companyId, SysDepartment sysDepartment) {
		List<Tree> list = new ArrayList<Tree>();
		if (sysDepartment == null) {
			Tree tree = new Tree();
			tree.setId("00000000000000000000000000000000");
			SysDepartment deptRoot = new SysDepartment();
			deptRoot.setParentId("00000000000000000000000000000000");
			deptRoot.setCompanyId(companyId);
			List<SysDepartment> deptChildrenList = findByEntity(deptRoot);
			String state = "open";
			if (deptChildrenList != null && deptChildrenList.size() > 0) {
				state = "closed";
			}
			tree.setState(state);
			tree.setText("根节点");
			list.add(tree);
		} else {
			sysDepartment.setCompanyId(companyId);
			List<SysDepartment> deptList = findByEntity(sysDepartment);
			SysDepartment deptNew = new SysDepartment();
			for (SysDepartment dept : deptList) {
				Tree tree = new Tree();
				tree.setId(dept.getId());
				deptNew.setParentId(dept.getId());
				List<SysDepartment> deptChildrenList = findByEntity(deptNew);
				String state = "open";
				if (deptChildrenList != null && deptChildrenList.size() > 0) {
					state = "closed";
				}
				tree.setState(state);
				tree.setText(dept.getDeptName());
				list.add(tree);
			}
		}
		return list;
	}

	@Override
	public List<Tree> selectDept(SysDepartment sysDepartment) {
		List<Tree> list = new ArrayList<Tree>();
		List<SysDepartment> deptList = findByEntity(sysDepartment);
		SysDepartment deptNew = new SysDepartment();
		for (SysDepartment dept : deptList) {
			Tree tree = new Tree();
			tree.setId(dept.getId());
			deptNew.setParentId(dept.getId());
			List<SysDepartment> deptChildrenList = findByEntity(deptNew);
			String state = "open";
			if (deptChildrenList != null && deptChildrenList.size() > 0) {
				state = "closed";
			}
			tree.setState(state);
			tree.setText(dept.getDeptName());
			list.add(tree);
		}
		return list;
	}

	@Override
	public List<Tree> findDeptTreeParents(SysDepartment sysDepartment) {
		// sysDepartment：为构造的查找选中节点所有子节点的条件
		// 查找当前节点下面的所有子节点
		List<SysDepartment> deptList = findByEntity(sysDepartment);
		// 递归时用来存放每次的查询结果
		List<Tree> listCurrent = new ArrayList<Tree>();
		// 选择节点，如果有子节点则关闭，无子节点则展开
		String selectNodeState = "open";
		if (deptList != null && deptList.size() > 0) {
			selectNodeState = "closed";
		}
		// 查找当前选中节点及所有兄弟节点及所有父节点及父节点的兄弟节点
		List<Tree> list = findDeptParents(listCurrent, sysDepartment, selectNodeState);
		return list;
	}

	public List<Tree> findDeptParents(List<Tree> deptList, SysDepartment sysDepartment, String selectNodeState) {
		List<Tree> listParent = new ArrayList<Tree>();
		String parentId = sysDepartment.getParentId();
		String companyId = sysDepartment.getCompanyId();
		SysDepartment deptParent = new SysDepartment();
		deptParent.setId(parentId);
		// findDeptParent:当前需要选择的节点
		SysDepartment findDeptParent = findById(parentId);
		// 根据业务需求findDeptParent不可能为空
		if (findDeptParent != null) {
			// 当前选择节点信息
			Tree treeParent = new Tree();
			treeParent.setId(parentId);
			treeParent.setText(findDeptParent.getDeptName());
			treeParent.setChildren(deptList);
			treeParent.setState(selectNodeState);
			// 查询父节点同级节点条件
			SysDepartment deptParentCurrent = new SysDepartment();
			String parentIdCurrent = findDeptParent.getParentId();
			deptParentCurrent.setParentId(parentIdCurrent);
			deptParentCurrent.setCompanyId(companyId);
			// 查询当前父节点同级所有节点
			List<SysDepartment> deptListCurrent = findByEntity(deptParentCurrent);
			SysDepartment deptParentNew = new SysDepartment();
			for (SysDepartment dept : deptListCurrent) {
				String id = dept.getId();
				if (!id.equals(parentId)) {
					Tree tree = new Tree();
					tree.setId(id);
					deptParentNew.setParentId(id);
					deptParentNew.setCompanyId(companyId);
					List<SysDepartment> deptChildrenList = findByEntity(deptParentNew);
					String state = "open";
					if (deptChildrenList != null && deptChildrenList.size() > 0) {
						state = "closed";
					}
					tree.setState(state);
					tree.setText(dept.getDeptName());
					listParent.add(tree);
				} else {
					listParent.add(treeParent);
				}
			}
			List<Tree> findDeptParents = findDeptParents(listParent, findDeptParent, "open");
			if (findDeptParents != null && findDeptParents.size() > 0) {
				listParent = findDeptParents;
			}
		}
		return listParent;
	}

	@Override
	public String delDeptById(String id) {
		SysDepartment deptOld = findById(id);
		String companyId = deptOld.getCompanyId();
		SysDepartment dept = new SysDepartment();
		dept.setParentId(id);
		dept.setCompanyId(companyId);
		List<SysDepartment> childrenList = findByEntity(dept);
		if (childrenList != null && childrenList.size() > 0) {
			return JsonUtils.returnJsonError("该公司部门下面存在子部门不允许删除！");
		}
		deptOld.setDel(Constants.DEL_YES);
		update(deptOld);
		return JsonUtils.returnJsonSuccess();
	}

	@Override
	public List<SysDepartment> findByEntity(SysDepartment dept) {
		dept.setDel(Constants.DEL_NO);
		return super.findByEntity(dept);
	}

	@Override
	public String save(SysDepartment dept) {
		sysDepartmentMapper.saveDept(dept);
		return JsonUtils.returnJsonSuccess(dept);
	}

	@Override
	public String updateDept(SysDepartment dept) {
		super.update(dept);
		return JsonUtils.returnJsonSuccess(dept);
	}

	@Override
	public List<SysDepartment> findAreaByParentId(String businessDepartmentId) {
		// TODO Auto-generated method stub
		return null;
	}
}
