package shiro.model.sys;

import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "t_sys_company")
public class SysCompany {
	/**
	 * 主键ID
	 */
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "UUID")
	private String id;

	/**
	 * 公司父ID
	 */
	@Column(name = "PARENT_ID")
	private String parentId;

	/**
	 * 公司名称
	 */
	@Column(name = "COMPANY_NAME")
	private String companyName;

	/**
	 * 地址
	 */
	@Column(name = "ADDRESS")
	private String address;

	/**
	 * 描述信息
	 */
	@Column(name = "DESCRIPTION")
	private String description;

	/**
	 * 电话
	 */
	@Column(name = "TEL")
	private String tel;

	/**
	 * 传真
	 */
	@Column(name = "FAX")
	private String fax;

	/**
	 * 创建人
	 */
	@Column(name = "ADD_USER")
	private String addUser;

	/**
	 * 创建时间
	 */
	@Column(name = "ADD_TIME")
	private Date addTime;

	/**
	 * 更新时间
	 */
	@Column(name = "EDIT_TIME")
	private Date editTime;

	/**
	 * 是否删除
	 */
	@Column(name = "DEL")
	private String del;

	/**
	 * 旧系统ID
	 */
	@Column(name = "OLD_ID")
	private String oldId;

	/**
	 * 旧的系统父ID
	 */
	@Column(name = "OLD_PARENT_ID")
	private String oldParentId;

	/**
	 * 获取主键ID
	 *
	 * @return ID - 主键ID
	 */
	public String getId() {
		return id;
	}

	/**
	 * 设置主键ID
	 *
	 * @param id
	 *            主键ID
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 获取公司父ID
	 *
	 * @return PARENT_ID - 公司父ID
	 */
	public String getParentId() {
		return parentId;
	}

	/**
	 * 设置公司父ID
	 *
	 * @param parentId
	 *            公司父ID
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	/**
	 * 获取公司名称
	 *
	 * @return COMPANY_NAME - 公司名称
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * 设置公司名称
	 *
	 * @param companyName
	 *            公司名称
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * 获取地址
	 *
	 * @return ADDRESS - 地址
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * 设置地址
	 *
	 * @param address
	 *            地址
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * 获取描述信息
	 *
	 * @return DESCRIPTION - 描述信息
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 设置描述信息
	 *
	 * @param description
	 *            描述信息
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 获取电话
	 *
	 * @return TEL - 电话
	 */
	public String getTel() {
		return tel;
	}

	/**
	 * 设置电话
	 *
	 * @param tel
	 *            电话
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}

	/**
	 * 获取传真
	 *
	 * @return FAX - 传真
	 */
	public String getFax() {
		return fax;
	}

	/**
	 * 设置传真
	 *
	 * @param fax
	 *            传真
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}

	/**
	 * 获取创建人
	 *
	 * @return ADD_USER - 创建人
	 */
	public String getAddUser() {
		return addUser;
	}

	/**
	 * 设置创建人
	 *
	 * @param addUser
	 *            创建人
	 */
	public void setAddUser(String addUser) {
		this.addUser = addUser;
	}

	/**
	 * 获取创建时间
	 *
	 * @return ADD_TIME - 创建时间
	 */
	public Date getAddTime() {
		return addTime;
	}

	/**
	 * 设置创建时间
	 *
	 * @param addTime
	 *            创建时间
	 */
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	/**
	 * 获取更新时间
	 *
	 * @return EDIT_TIME - 更新时间
	 */
	public Date getEditTime() {
		return editTime;
	}

	/**
	 * 设置更新时间
	 *
	 * @param editTime
	 *            更新时间
	 */
	public void setEditTime(Date editTime) {
		this.editTime = editTime;
	}

	/**
	 * 获取是否删除
	 *
	 * @return DEL - 是否删除
	 */
	public String getDel() {
		return del;
	}

	/**
	 * 设置是否删除
	 *
	 * @param del
	 *            是否删除
	 */
	public void setDel(String del) {
		this.del = del;
	}

	/**
	 * 获取旧系统ID
	 *
	 * @return OLD_ID - 旧系统ID
	 */
	public String getOldId() {
		return oldId;
	}

	/**
	 * 设置旧系统ID
	 *
	 * @param oldId
	 *            旧系统ID
	 */
	public void setOldId(String oldId) {
		this.oldId = oldId;
	}

	/**
	 * 获取旧的系统父ID
	 *
	 * @return OLD_PARENT_ID - 旧的系统父ID
	 */
	public String getOldParentId() {
		return oldParentId;
	}

	/**
	 * 设置旧的系统父ID
	 *
	 * @param oldParentId
	 *            旧的系统父ID
	 */
	public void setOldParentId(String oldParentId) {
		this.oldParentId = oldParentId;
	}
}