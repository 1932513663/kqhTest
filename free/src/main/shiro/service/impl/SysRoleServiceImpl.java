package shiro.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import core.base.service.impl.BaseServiceImpl;
import shiro.dao.sys.SysRoleMapper;
import shiro.model.sys.SysRole;
import shiro.service.SysRoleIService;
import shiro.service.SysRoleMenuIService;
import utils.JsonUtils;
import utils.base.Tree;

@Service
public class SysRoleServiceImpl extends BaseServiceImpl<SysRole> implements SysRoleIService {
	@Autowired
	private SysRoleMenuIService sysRoleMenuIService;
	@Autowired
	private SysRoleMapper sysRoleMapper;
	
	@Override
	public List<Tree> findRoleTree(SysRole sysRole) {
		List<SysRole> roleList = findByEntity(sysRole);
		List<Tree> list = new ArrayList<Tree>();
		SysRole roleNew=new SysRole();
		for (SysRole role : roleList) {
			Tree tree = new Tree();
			tree.setId(role.getId());
			roleNew.setParentId(role.getId());
			List<SysRole> roleChildrenList = findByEntity(roleNew);
			String state = "open";
			if (roleChildrenList != null && roleChildrenList.size() > 0) {
				state = "closed";
			}
			tree.setState(state);
			tree.setText(role.getRoleName());
			list.add(tree);
		}
		return list;
	}

	@Override
	public List<Tree> findRoleTreeParents(SysRole sysRole) {
		List<SysRole> roleList = findByEntity(sysRole);
		//递归时用来存放每次的查询结果
		List<Tree> listCurrent = new ArrayList<Tree>();
		//选择节点，如果有子节点则关闭，无子节点则展开
		String selectNodeState="open";
		if(roleList!=null&&roleList.size()>0){
			selectNodeState="closed";
		}
		//查找当前选中节点及所有兄弟节点及所有父节点及父节点的兄弟节点
		List<Tree> list = findRoleParents(listCurrent, sysRole,selectNodeState);
		return list;
	}
	
	public List<Tree> findRoleParents(List<Tree> roleList,SysRole sysRole,String selectNodeState){
		List<Tree> listParent = new ArrayList<Tree>();
		String parentId = sysRole.getParentId();
		SysRole roleParent=new SysRole();
		roleParent.setId(parentId);
		//postParent:当前需要选择的节点
		SysRole findRoleParent = findById(parentId);
		//根据业务需求findPostParent不可能为空
		if(findRoleParent!=null){
			//当前选择节点信息
			Tree treeParent = new Tree();
			treeParent.setId(parentId);
			treeParent.setText(findRoleParent.getRoleName());
			treeParent.setChildren(roleList);
			treeParent.setState(selectNodeState);
			//查询父节点同级节点条件
			SysRole roleParentCurrent=new SysRole();
			String parentIdCurrent = findRoleParent.getParentId();
			roleParentCurrent.setParentId(parentIdCurrent);
			//查询当前父节点同级所有节点
			List<SysRole> roleListCurrent = findByEntity(roleParentCurrent);
			SysRole roleParentNew=new SysRole();
			for (SysRole role : roleListCurrent) {
				String id=role.getId();
				if(!id.equals(parentId)){
					Tree tree = new Tree();
					tree.setId(id);
					roleParentNew.setParentId(id);
					List<SysRole> roleChildrenList = findByEntity(roleParentNew);
					String state = "open";
					if (roleChildrenList != null && roleChildrenList.size() > 0) {
						state = "closed";
					}
					tree.setState(state);
					tree.setText(role.getRoleName());
					listParent.add(tree);
				}else{
					listParent.add(treeParent);
				}
			}
			List<Tree> findRoleParents = findRoleParents(listParent,findRoleParent,"open");
			if(findRoleParents!=null&&findRoleParents.size()>0){
				listParent=findRoleParents;
			}
		}
		return listParent;
	}

	@Override
	public List<Tree> findRoleTreeOfSelect(SysRole sysRole) {
		List<Tree> list = new ArrayList<Tree>();
		if(sysRole==null){
			Tree tree = new Tree();
			tree.setId("00000000000000000000000000000000");
			SysRole roleRoot=new SysRole();
			roleRoot.setParentId("00000000000000000000000000000000");
			List<SysRole> roleChildrenList = findByEntity(roleRoot);
			String state = "open";
			if (roleChildrenList != null && roleChildrenList.size() > 0) {
				state = "closed";
			}
			tree.setState(state);
			tree.setText("根节点");
			list.add(tree);
		}else{
			List<SysRole> roleList = findByEntity(sysRole);
			SysRole roleNew=new SysRole();
			for (SysRole role : roleList) {
				Tree tree = new Tree();
				tree.setId(role.getId());
				roleNew.setParentId(role.getId());
				List<SysRole> roleChildrenList = findByEntity(roleNew);
				String state = "open";
				if (roleChildrenList != null && roleChildrenList.size() > 0) {
					state = "closed";
				}
				tree.setState(state);
				tree.setText(role.getRoleName());
				list.add(tree);
			}
		}
		return list;
	}

	@Override
	public String delRoleById(String roleId) {
		SysRole role=new SysRole();
		role.setParentId(roleId);
		List<SysRole> childrenList = findByEntity(role);
		if(childrenList!=null&&childrenList.size()>0){
			return JsonUtils.returnJsonError("该角色下面存在子角色不允许删除！");
		}
		deleteById(roleId);
		return JsonUtils.returnJsonSuccess();
	}

	@Override
	public String save(SysRole role, String roleMenusStr) {
		sysRoleMapper.saveRole(role);
		String roleId = role.getId();
		sysRoleMenuIService.saveRoleMenus(roleId, roleMenusStr);
		return JsonUtils.returnJsonSuccess(role);
	}

	@Override
	public String update(SysRole role, String roleMenusStr) {
		super.update(role);
		String roleId=role.getId();
		sysRoleMenuIService.deleteByRoleId(roleId);
		sysRoleMenuIService.saveRoleMenus(roleId, roleMenusStr);
		return JsonUtils.returnJsonSuccess(role);
	}
}
