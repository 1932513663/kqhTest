package shiro.model.sys;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @ClassName: MenuComponent
 * @Title: MenuComponent.java
 * @Description: TODO
 *
 * @author kang
 * @version V1.0 
 * @company 麦田
 * @date 2017年2月10日 下午2:21:10
 */
public class MenuComponent implements Serializable {

	private static final long serialVersionUID = 24997852718699039L;

	@SuppressWarnings("unused")
	private static Logger log = LoggerFactory.getLogger(MenuComponent.class);
	
	protected static MenuComponent[] _menuComponent = new MenuComponent[0];

	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected List menuComponents = Collections.synchronizedList(new ArrayList());

	protected MenuComponent parentMenu;

	@SuppressWarnings("unchecked")
	public void addMenuComponent(MenuComponent menuComponent) {
		if ((menuComponent.getName() == null) || (menuComponent.getName().equals(""))) {
			menuComponent.setName(this.name + this.menuComponents.size());
		}

		if (!this.menuComponents.contains(menuComponent)) {
			this.menuComponents.add(menuComponent);
			menuComponent.setParent(this);
		}
	}

	public void setParent(MenuComponent parentMenu) {
		if (parentMenu != null) {
			if (!parentMenu.getComponents().contains(this)) {
				parentMenu.addMenuComponent(this);
			}
		}
		this.parentMenu = parentMenu;
	}

	public MenuComponent getParent() {
		return this.parentMenu;
	}

	@SuppressWarnings("rawtypes")
	public List getComponents() {
		return this.menuComponents;
	}

	@SuppressWarnings("unchecked")
	public MenuComponent[] getMenuComponents() {
		return (MenuComponent[]) this.menuComponents.toArray(_menuComponent);
	}

	@SuppressWarnings("unchecked")
	public void setMenuComponents(MenuComponent[] menuComponents) {
		for (int i = 0; i < menuComponents.length; i++) {
			MenuComponent component = menuComponents[i];
			this.menuComponents.add(component);
		}
	}

	// ===============================================//

	protected String action;
	protected String align;
	protected String altImage;
	protected String description;
	protected String forward;
	protected String height;
	protected String image;
	protected String location;
	protected String name;
	protected String onclick;
	protected String ondblclick;
	protected String onmouseout;
	protected String onmouseover;
	protected String page;
	protected String roles;
	protected String target;
	protected String title;
	protected String toolTip;
	protected String width;
	private String url;
	protected String onContextMenu;
	protected String module;

	public void setAction(String action) {
		this.action = action;
	}

	public String getAction() {
		return this.action;
	}

	public String getAlign() {
		return this.align;
	}

	public void setAlign(String align) {
		this.align = align;
	}

	public void setAltImage(String altImage) {
		this.altImage = altImage;
	}

	public String getAltImage() {
		return this.altImage;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return this.description;
	}

	public void setForward(String forward) {
		this.forward = forward;
	}

	public String getForward() {
		return this.forward;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getHeight() {
		return this.height;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getImage() {
		return this.image;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getLocation() {
		return this.location;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setOnclick(String onclick) {
		this.onclick = onclick;
	}

	public String getOnclick() {
		return this.onclick;
	}

	public void setOnmouseout(String onmouseout) {
		this.onmouseout = onmouseout;
	}

	public String getOnmouseout() {
		return this.onmouseout;
	}

	public void setOnmouseover(String onmouseover) {
		this.onmouseover = onmouseover;
	}

	public String getOnmouseover() {
		return this.onmouseover;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getPage() {
		return this.page;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public String getRoles() {
		return this.roles;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getTarget() {
		return this.target;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return this.title;
	}

	public void setToolTip(String toolTip) {
		this.toolTip = toolTip;
	}

	public String getToolTip() {
		return this.toolTip;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrl() {
		return this.url;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getWidth() {
		return this.width;
	}

	public String getOnContextMenu() {
		return this.onContextMenu;
	}

	public void setOnContextMenu(String string) {
		this.onContextMenu = string;
	}

	public String getOndblclick() {
		return this.ondblclick;
	}

	public void setOndblclick(String ondblclick) {
		this.ondblclick = ondblclick;
	}

	public String getModule() {
		return this.module;
	}

	public void setModule(String module) {
		this.module = module;
	}
}
