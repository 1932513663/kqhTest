package shiro.model.sys;

import javax.persistence.*;

@Table(name = "t_sys_role_post")
public class SysRolePost {
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "UUID")
	private String id;

	@Column(name = "ROLE_ID")
	private String roleId;

	@Column(name = "POST_ID")
	private String postId;

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
	 * @return POST_ID
	 */
	public String getPostId() {
		return postId;
	}

	/**
	 * @param postId
	 */
	public void setPostId(String postId) {
		this.postId = postId;
	}
}