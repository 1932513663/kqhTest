package shiro.model.sys;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.map.LinkedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 
 * @ClassName: MenuRepository
 * @Title: MenuRepository.java
 * @Description: TODO
 *
 * @author kang
 * @version V1.0 
 * @company 麦田
 * @date 2017年2月10日 下午2:19:19
 */
public class MenuRepository implements Serializable {

	private static final long serialVersionUID = 4395504995152413387L;

	private static Logger log = LoggerFactory.getLogger(MenuRepository.class);

	protected LinkedMap menus = new LinkedMap();

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List getTopMenus() {
		List topMenus = new ArrayList();
		if (this.menus == null) {
			log.warn("No menus found in repository!");
			return topMenus;
		}

		for (Iterator it = this.menus.keySet().iterator(); it.hasNext();) {
			String name = (String) it.next();
			MenuComponent menu = getMenu(name);
			if (menu.getParent() == null) {
				topMenus.add(menu);
			}
		}
		return topMenus;
	}

	public MenuComponent getMenu(String menuName) {
		return (MenuComponent) this.menus.get(menuName);
	}

	@SuppressWarnings("rawtypes")
	public void addMenu(MenuComponent menu) {
		Iterator it;
		if (this.menus.containsKey(menu.getName())) {
			if (log.isDebugEnabled()) {
				log.warn("Menu '" + menu.getName() + "' already exists in repository");
			}
			List children = getMenu(menu.getName()).getComponents();
			if ((children != null) && (menu.getComponents() != null)) {
				for (it = children.iterator(); it.hasNext();) {
					MenuComponent child = (MenuComponent) it.next();
					menu.addMenuComponent(child);
				}
			}
		}
		this.menus.put(menu.getName(), menu);
	}

	public void removeMenu(String name) {
		this.menus.remove(name);
	}

	public void removeAllMenus() {
		this.menus.clear();
	}
}
