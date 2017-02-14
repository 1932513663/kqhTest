package shiro.model;

import java.io.Serializable;
import java.util.Date;

/**
 * SysCompany
 * 
 * @author MaiTian
 * @version webx3.0
 */
public class SysUserTheme implements Serializable {
	
	private static final long serialVersionUID = -6868221075453737421L;

	/** 主键ID **/
	private String id;

	/** 添加时间 **/
	private Date addTime;

	/** 修改时间 **/
	private Date editTime;

	/** 模板名称 **/
	private String theme;

	/** 旧用户ID **/
	private Integer oldUserId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Date getEditTime() {
		return editTime;
	}

	public void setEditTime(Date editTime) {
		this.editTime = editTime;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public Integer getOldUserId() {
		return oldUserId;
	}

	public void setOldUserId(Integer oldUserId) {
		this.oldUserId = oldUserId;
	}

}