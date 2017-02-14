package shiro.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import core.base.service.impl.BaseServiceImpl;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import shiro.dao.sys.SysMenuMapper;
import shiro.dao.sys.SysRoleMenuMapper;
import shiro.model.sys.SysMenu;
import shiro.model.sys.SysRoleMenu;
import shiro.service.SysMenuIService;
import shiro.service.SysRoleMenuIService;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;
import utils.Constants;
import utils.JsonUtils;
import utils.base.Tree;

@Service
public class SysRoleMenuServiceImpl extends BaseServiceImpl<SysRoleMenu> implements SysRoleMenuIService {

	@Autowired
	private SysRoleMenuMapper sysRoleMenuMapper;
	@Autowired
	private SysMenuIService sysMenuIService;
	@Autowired
	private SysMenuMapper sysMenuMapper;
	
	@Override
	public List<Tree> findMenuTreeOfRole(String roleId) {
		Map<String, SysRoleMenu> map=new HashMap<String, SysRoleMenu>();
		SysRoleMenu roleMenu = new SysRoleMenu();
		if(!StringUtils.isEmpty(roleId)){
			roleMenu.setRoleId(roleId);
		}
		List<SysRoleMenu> roleMenuList = findByEntity(roleMenu);
		for (SysRoleMenu sysRoleMenu : roleMenuList) {
			String menuId=sysRoleMenu.getMenuId();
			map.put(menuId, sysRoleMenu);
		}
		List<Tree> menuList = findMenuTreeAll(Constants.ROOT_NODE,map);
		return menuList;
	}

	@Override
	public List<Tree> findMenuTreeAll(String parentId, Map<String, SysRoleMenu> map) {
		List<Tree> list = new ArrayList<Tree>();
		SysMenu menu = new SysMenu();
		menu.setParentId(parentId);
		List<SysMenu> menuParentList = sysMenuIService.findByEntity(menu);
		SysMenu menuParentSearch = new SysMenu();
		for (SysMenu sysMenu : menuParentList) {
			Tree tree = new Tree();
			String id = sysMenu.getId();
			menuParentSearch.setParentId(id);
			List<SysMenu> menuList = sysMenuIService.findByEntity(menuParentSearch);
			if(menuList!=null&&menuList.size()>0){
				//有子节点
				List<Tree> children = findMenuTreeAll(id, map);
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
			tree.setText(sysMenu.getMenuName());
			boolean checked=false;
			SysRoleMenu sysRoleMenu = map.get(id);
			if(sysRoleMenu!=null){
				checked=true;
			}
			tree.setChecked(checked);
			list.add(tree);
		}
		return list;
	}

	@Override
	public String saveRoleMenus(String roleId,String roleMenusStr) {
		JSONObject userRolesJSON = JSONObject.fromObject(roleMenusStr);
		Object object = userRolesJSON.get("menuIds");
		if (object!=null) {
			JSONArray menuIdsArray = JSONArray.fromObject(object);
			List<SysRoleMenu> list = new ArrayList<SysRoleMenu>();
			for (Object menuIdObj : menuIdsArray) {
				String menuId = menuIdObj.toString();
				SysRoleMenu sysRoleMenu = new SysRoleMenu();
				sysRoleMenu.setMenuId(menuId);
				sysRoleMenu.setRoleId(roleId);
				list.add(sysRoleMenu);
			}
			//删除所有的
			deleteByRoleId(roleId);
			//批量保存
			if(list.size()>0){
				insertRoleMenuOfBatch(list);
			}
		}
		return JsonUtils.returnJsonSuccess();
	}

	@Override
	public void deleteByRoleId(String roleId) {
		sysRoleMenuMapper.deleteByRoleId(roleId);
	}

	@Override
	public void insertRoleMenuOfBatch(List<SysRoleMenu> roleMenuList) {
		sysRoleMenuMapper.insertRoleMenuOfBatch(roleMenuList);
	}

	@Override
	public List<SysRoleMenu> findByRoleId(String roleId) {
		Example example = new Example(SysRoleMenu.class);
		Criteria createCriteria = example.createCriteria();
		createCriteria.andEqualTo("roleId", roleId);
		List<SysRoleMenu> list = sysRoleMenuMapper.selectByExample(example);
		return list;
		
	}

	@Override
	public List<SysMenu> findMenuListByRoleId(String roleId) {
		List<String> list = new ArrayList<String>();
		List<SysRoleMenu> findByRoleId = findByRoleId(roleId);
		
		for (SysRoleMenu sysRoleMenu : findByRoleId) {
			String menuId = sysRoleMenu.getMenuId();
			list.add(menuId);
		}
		List<SysMenu> sysRoleList = null;
		if(list.size()>0){
			Example example=new Example(SysMenu.class);
			Criteria createCriteria = example.createCriteria();
			createCriteria.andIn("id", list);
			sysRoleList=sysMenuMapper.selectByExample(example);
		}
		return sysRoleList;
	}

}
