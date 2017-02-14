package shiro.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import core.base.service.impl.BaseServiceImpl;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import shiro.dao.sys.SysUserRoleMapper;
import shiro.model.sys.SysRole;
import shiro.model.sys.SysUserRole;
import shiro.service.SysRoleIService;
import shiro.service.SysUserRoleIService;
import utils.Constants;
import utils.JsonUtils;
import utils.base.Tree;

@Service
public class SysUserRoleServiceImpl extends BaseServiceImpl<SysUserRole> implements SysUserRoleIService {

	@Autowired
	private SysUserRoleMapper sysUserRoleMapper;
	@Autowired
	private SysRoleIService sysRoleIService;
	
	@Override
	public List<Tree> findRoleTreeOfUser(String userId) {
		Map<String, SysUserRole> map=new HashMap<String, SysUserRole>();
		SysUserRole userRole = new SysUserRole();
		userRole.setUserId(userId);
		List<SysUserRole> userRoleList = findByEntity(userRole);
		for (SysUserRole sysUserRole : userRoleList) {
			String roleId=sysUserRole.getRoleId();
			map.put(roleId, sysUserRole);
		}
		List<Tree> roleList = findRoleTreeAll(Constants.ROOT_NODE,map);
		return roleList;
	}
	
	@Override
	public List<Tree> findRoleTreeAll(String parentId,Map<String, SysUserRole> map) {
		List<Tree> list = new ArrayList<Tree>();
		SysRole role = new SysRole();
		role.setParentId(parentId);
		List<SysRole> roleParentList = sysRoleIService.findByEntity(role);
		SysRole roleParentSearch = new SysRole();
		for (SysRole sysRole : roleParentList) {
			Tree tree = new Tree();
			String id = sysRole.getId();
			roleParentSearch.setParentId(id);
			List<SysRole> roleList = sysRoleIService.findByEntity(roleParentSearch);
			if(roleList!=null&&roleList.size()>0){
				//有子节点
				List<Tree> children = findRoleTreeAll(id, map);
				if(children!=null&&children.size()>0){
					tree.setChildren(children);
				}else{
					return list;
				}
			}
			String state = "open";
			tree.setState(state);
			//公共部分
			tree.setId(id);
			tree.setText(sysRole.getRoleName());
			boolean checked=false;
			SysUserRole sysUserRole = map.get(id);
			if(sysUserRole!=null){
				checked=true;
			}
			tree.setChecked(checked);
			list.add(tree);
		}
		return list;
	}

	@Override
	public String saveRoleUsers(String userRolesStr) {
		JSONObject userRolesJSON = JSONObject.fromObject(userRolesStr);
		String userId=userRolesJSON.get("userId").toString();
		Object object = userRolesJSON.get("roleIds");
		if (object!=null) {
			JSONArray roleIdsArray = JSONArray.fromObject(object);
			List<SysUserRole> list = new ArrayList<SysUserRole>();
			for (Object roleIdObj : roleIdsArray) {
				String roleId = roleIdObj.toString();
				SysUserRole sysUserRole = new SysUserRole();
				sysUserRole.setUserId(userId);
				sysUserRole.setRoleId(roleId);
				list.add(sysUserRole);
			}
			//删除所有的
			deleteByUserId(userId);
			if(list.size()>0){
				insertUserRoleOfBatch(list);
			}
		}
		return JsonUtils.returnJsonSuccess();
	}

	@Override
	public void deleteByUserId(String userId) {
		sysUserRoleMapper.deleteByUserId(userId);
	}

	@Override
	public void insertUserRoleOfBatch(List<SysUserRole> userRoleList) {
		sysUserRoleMapper.insertUserRoleOfBatch(userRoleList);
	}
}
