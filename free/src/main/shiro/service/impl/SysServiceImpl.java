package shiro.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import shiro.dao.sys.SysRoleMapper;
import shiro.model.sys.SysMenu;
import shiro.model.sys.SysRole;
import shiro.model.sys.SysUserRole;
import shiro.service.SysIService;
import shiro.service.SysMenuIService;
import shiro.service.SysRoleMenuIService;
import shiro.service.SysUserRoleIService;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class SysServiceImpl implements SysIService{
	
	@Autowired
	private SysMenuIService sysMenuIService;
	@Autowired
	private SysUserRoleIService sysUserRoleIService;
	@Autowired
	private SysRoleMapper sysRoleMapper;
	@Autowired
	private SysRoleMenuIService sysRoleMenuIService;
	
	@Override
	public List<SysRole> loadUserRoles(String userId) {
		SysUserRole sysUserRole = new SysUserRole();
		sysUserRole.setUserId(userId);
		List<SysUserRole> userRoleList = sysUserRoleIService.findByEntity(sysUserRole);
		
		List<String> rolesList=new ArrayList<String>();
		for (SysUserRole userRole : userRoleList) {
			String roleId = userRole.getRoleId();
			rolesList.add(roleId);
		}
		
		Map<String, Object> roleMap = new HashMap<String, Object>();
		List<SysRole> roleList = Lists.newArrayList();

		if(rolesList.size()>0){
			Example example=new Example(SysRole.class);
			Criteria createCriteria = example.createCriteria();
			createCriteria.andIn("id", rolesList);
			List<SysRole> sysRoleList = sysRoleMapper.selectByExample(example);
			if(sysRoleList!=null){
				// 添加用户关联的角色
				for (SysRole role : sysRoleList) {
					String id = role.getId();
					roleMap.put(id, role);
					roleList.add(role);
				}
			}
		}
		return roleList;
	}

	@Override
	public List<SysMenu> loadUserMenus(String userId) {
		List<SysRole> roleList = loadUserRoles(userId);
		Map<String, Object> menuMap = new HashMap<String, Object>();
		List<SysMenu> menuList = Lists.newArrayList();

		for (SysRole role : roleList) {
			String roleId=role.getId();
			List<SysMenu> sysMenuList=sysRoleMenuIService.findMenuListByRoleId(roleId);
			if(sysMenuList!=null){
				for (SysMenu menu : sysMenuList) {
					if (!menuMap.containsKey(menu.getId())) {
						
						String parentId = menu.getParentId();
						SysMenu menuParent = sysMenuIService.findById(parentId);
						if(menuParent!=null){
							Object object = menuMap.get("parentId");
							if(object==null){
								menuMap.put(parentId, menuParent);
								menuList.add(menuParent);
							}
						}
						menuMap.put(menu.getId(), menu.getId());
						menuList.add(menu);
					}
				}
			}
		}
		return menuList;
	}

	@Override
	public List<SysMenu> getLevelMenuList(String parentId,String userId) {
		List<SysMenu> menuList = loadUserMenus(userId);
		List<String> menuIdList = Lists.newArrayList();
		for (SysMenu menu : menuList) {
			menuIdList.add(menu.getId());
		}

		List<SysMenu> rtnMenuList = Lists.newArrayList();
		
		SysMenu sysMenu = new SysMenu();
		sysMenu.setParentId(parentId);
		//查询出所有的1级菜单
		List<SysMenu> firstLevelMenuList = sysMenuIService.findFirstLevelMenuList(sysMenu);

		if (firstLevelMenuList != null && firstLevelMenuList.size() > 0) {
			for (SysMenu menu : firstLevelMenuList) {
				if (menuIdList.contains(menu.getId()) && "1".equals(menu.getIsShow())) {
					rtnMenuList.add(menu);
				}
			}
		}
		return rtnMenuList;
	}
}
