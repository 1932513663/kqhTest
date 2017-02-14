package shiro.model.sys;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_sys_role")
public class SysRole {
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "UUID")
	private String id;

	@Column(name = "PARENT_ID")
	private String parentId;

	@Column(name = "ROLE_NAME")
	private String roleName;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "ADD_TIME")
	private Date addTime;

	@Column(name = "EDIT_TIME")
	private Date editTime;

	@Column(name = "ROLE_ENGLISH_NAME")
	private String roleEnglishName;

	@Column(name = "IS_AVAILABLE")
	private String isAvailable;

	/**
	 * @return ID
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return PARENT_ID
	 */
	public String getParentId() {
		return parentId;
	}

	/**
	 * @param parentId
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	/**
	 * @return ROLE_NAME
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 * @param roleName
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	/**
	 * @return DESCRIPTION
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return ADD_TIME
	 */
	public Date getAddTime() {
		return addTime;
	}

	/**
	 * @param addTime
	 */
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	/**
	 * @return EDIT_TIME
	 */
	public Date getEditTime() {
		return editTime;
	}

	/**
	 * @param editTime
	 */
	public void setEditTime(Date editTime) {
		this.editTime = editTime;
	}

	/**
	 * @return ROLE_ENGLISH_NAME
	 */
	public String getRoleEnglishName() {
		return roleEnglishName;
	}

	/**
	 * @param roleEnglishName
	 */
	public void setRoleEnglishName(String roleEnglishName) {
		this.roleEnglishName = roleEnglishName;
	}

	/**
	 * @return IS_AVAILABLE
	 */
	public String getIsAvailable() {
		return isAvailable;
	}

	/**
	 * @param isAvailable
	 */
	public void setIsAvailable(String isAvailable) {
		this.isAvailable = isAvailable;
	}
}