package shiro.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import core.base.service.impl.BaseServiceImpl;
import shiro.dao.sys.SysMenuMapper;
import shiro.model.sys.SysMenu;
import shiro.service.SysMenuIService;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;
import utils.JsonUtils;
import utils.base.Tree;

@Service
public class SysMenuServiceImpl extends BaseServiceImpl<SysMenu> implements SysMenuIService {

	@Autowired
	private SysMenuMapper sysMenuMapper;

	@Override
	public List<SysMenu> findFirstLevelMenuList(SysMenu sysMenu) {
		List<SysMenu> list = sysMenuMapper.select(sysMenu);
		return list;
	}

	@Override
	public void addEntity(SysMenu sysMenu) {
		sysMenuMapper.insert(sysMenu);
	}

	@Override
	public List<SysMenu> findChildrenMenuList(SysMenu sysMenu) {
		List<SysMenu> list = sysMenuMapper.select(sysMenu);
		return list;
	}

	@Override
	public String delMenuById(String menuId) {
		SysMenu menu=new SysMenu();
		menu.setParentId(menuId);
		List<SysMenu> childrenList = findByEntity(menu);
		if(childrenList!=null&&childrenList.size()>0){
			return JsonUtils.returnJsonError("该菜单下面存在子菜单不允许删除！");
		}
		deleteById(menuId);
		return JsonUtils.returnJsonSuccess();
	}

	@Override
	public List<Tree> findMenuTreeOfSelect(SysMenu sysMenu) {
		List<Tree> list = new ArrayList<Tree>();
		if(sysMenu==null){
			Tree tree = new Tree();
			tree.setId("00000000000000000000000000000000");
			SysMenu menuRoot=new SysMenu();
			menuRoot.setParentId("00000000000000000000000000000000");
			List<SysMenu> deptChildrenList = findByEntity(menuRoot);
			String state = "open";
			if (deptChildrenList != null && deptChildrenList.size() > 0) {
				state = "closed";
			}
			tree.setState(state);
			tree.setText("根节点");
			list.add(tree);
		}else{
			List<SysMenu> deptList = findByEntity(sysMenu);
			SysMenu menuNew=new SysMenu();
			for (SysMenu menu : deptList) {
				Tree tree = new Tree();
				tree.setId(menu.getId());
				menuNew.setParentId(menu.getId());
				List<SysMenu> deptChildrenList = findByEntity(menuNew);
				String state = "open";
				if (deptChildrenList != null && deptChildrenList.size() > 0) {
					state = "closed";
				}
				tree.setState(state);
				tree.setText(menu.getMenuName());
				list.add(tree);
			}
		}
		return list;
	}

	@Override
	public List<Tree> findMenuTree(SysMenu sysMenu) {
		List<Tree> list = new ArrayList<Tree>();
		List<SysMenu> menuList = findByEntity(sysMenu);
		SysMenu menuNew=new SysMenu();
		for (SysMenu menu : menuList) {
			Tree tree = new Tree();
			tree.setId(menu.getId());
			menuNew.setParentId(menu.getId());
			List<SysMenu> menuChildrenList = findByEntity(menuNew);
			String state = "open";
			if (menuChildrenList != null && menuChildrenList.size() > 0) {
				state = "closed";
			}
			tree.setState(state);
			tree.setText(menu.getMenuName());
			tree.setChecked(false);
			list.add(tree);
		}
		return list;
	}

	@Override
	public String save(SysMenu menu) {
		sysMenuMapper.saveMenu(menu);
		return JsonUtils.returnJsonSuccess(menu);
	}

	@Override
	public String updateMenu(SysMenu menu) {
		super.update(menu);
		return JsonUtils.returnJsonSuccess(menu);
	}

	@Override
	public List<SysMenu> findByParentId(String parentId, String order) {
		Example example = new Example(SysMenu.class);
		example.setOrderByClause(order);
		Criteria createCriteria = example.createCriteria();
		createCriteria.andEqualTo("parentId", parentId);
		List<SysMenu> list = sysMenuMapper.selectByExample(example);
		return list;
	}

	@Override
	public List<SysMenu> findALLFlatSysMenu() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SysMenu> getAllMenuListByParentId(String parentId) {
		Example example=new Example(SysMenu.class);
		Criteria createCriteria = example.createCriteria();
		createCriteria.andEqualTo("parentId", parentId);
		List<SysMenu> list = sysMenuMapper.selectByExample(example);
		return list;
	}
}
