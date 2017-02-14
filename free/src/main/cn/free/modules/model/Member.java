package cn.free.modules.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Repository;

@Repository
@Table(name = "t_member")
public class Member {
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY,generator="UUID")
	private String id;

	@Column(name = "LOGIN_NAME")
	private String loginName;

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
	 * @return LOGIN_NAME
	 */
	public String getLoginName() {
		return loginName;
	}

	/**
	 * @param loginName
	 */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
}