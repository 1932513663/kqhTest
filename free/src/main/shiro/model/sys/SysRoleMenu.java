package shiro.model.sys;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "t_sys_role_menu")
public class SysRoleMenu {
	@Id
    @Column(name = "ROLE_ID")
	private String roleId;

	@Id
	@Column(name = "MENU_ID")
	private String menuId;

	/**
	 * @return ROLE_ID
	 */
	public String getRoleId() {
		return roleId;
	}

	/**
	 * @param roleId
	 */
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	/**
	 * @return MENU_ID
	 */
	public String getMenuId() {
		return menuId;
	}

	/**
	 * @param menuId
	 */
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
}