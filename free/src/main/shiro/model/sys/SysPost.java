package shiro.model.sys;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_sys_post")
public class SysPost {
	/**
	 * 职位ID
	 */
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "UUID")
	private String id;

	/**
	 * 父职位ID
	 */
	@Column(name = "PARENT_ID")
	private String parentId;

	/**
	 * 职位名称
	 */
	@Column(name = "POST_NAME")
	private String postName;

	/**
	 * 部门ID
	 */
	@Column(name = "DEPT_ID")
	private String deptId;

	/**
	 * 公司ID
	 */
	@Column(name = "COMPANY_ID")
	private String companyId;

	/**
	 * 深度
	 */
	@Column(name = "DEEP")
	private Integer deep;

	/**
	 * 父ID路径：以"/" 进行分隔
	 */
	@Column(name = "PARENT_PATH")
	private String parentPath;

	/**
	 * 父职位名称：以"/" 进行分隔
	 */
	@Column(name = "PARENT_NAME")
	private String parentName;

	/**
	 * 创建时间
	 */
	@Column(name = "ADD_TIME")
	private Date addTime;

	/**
	 * 修改时间
	 */
	@Column(name = "EDIT_TIME")
	private Date editTime;

	/**
	 * 删除时间
	 */
	@Column(name = "DEL_TIME")
	private Date delTime;

	/**
	 * 创建人
	 */
	@Column(name = "ADD_USER")
	private String addUser;

	/**
	 * 编辑人
	 */
	@Column(name = "EDIT_USER")
	private String editUser;

	/**
	 * 删除人
	 */
	@Column(name = "DEL_USER")
	private String delUser;

	/**
	 * 是否删除: 0:未删除（默认） 1：删除
	 */
	@Column(name = "DEL")
	private String del;

	/**
	 * 旧系统ID
	 */
	@Column(name = "OLD_ID")
	private String oldId;

	/**
	 * 旧的父ID
	 */
	@Column(name = "OLD_PARENT_ID")
	private String oldParentId;

	/**
	 * 旧的部门ID
	 */
	@Column(name = "OLD_DEPT_ID")
	private String oldDeptId;

	/**
	 * 旧的公司ID
	 */
	@Column(name = "OLD_COM_ID")
	private String oldComId;

	/**
	 * 获取职位ID
	 *
	 * @return ID - 职位ID
	 */
	public String getId() {
		return id;
	}

	/**
	 * 设置职位ID
	 *
	 * @param id
	 *            职位ID
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 获取父职位ID
	 *
	 * @return PARENT_ID - 父职位ID
	 */
	public String getParentId() {
		return parentId;
	}

	/**
	 * 设置父职位ID
	 *
	 * @param parentId
	 *            父职位ID
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	/**
	 * 获取职位名称
	 *
	 * @return POST_NAME - 职位名称
	 */
	public String getPostName() {
		return postName;
	}

	/**
	 * 设置职位名称
	 *
	 * @param postName
	 *            职位名称
	 */
	public void setPostName(String postName) {
		this.postName = postName;
	}

	/**
	 * 获取部门ID
	 *
	 * @return DEPT_ID - 部门ID
	 */
	public String getDeptId() {
		return deptId;
	}

	/**
	 * 设置部门ID
	 *
	 * @param deptId
	 *            部门ID
	 */
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	/**
	 * 获取公司ID
	 *
	 * @return COMPANY_ID - 公司ID
	 */
	public String getCompanyId() {
		return companyId;
	}

	/**
	 * 设置公司ID
	 *
	 * @param companyId
	 *            公司ID
	 */
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	/**
	 * 获取深度
	 *
	 * @return DEEP - 深度
	 */
	public Integer getDeep() {
		return deep;
	}

	/**
	 * 设置深度
	 *
	 * @param deep
	 *            深度
	 */
	public void setDeep(Integer deep) {
		this.deep = deep;
	}

	/**
	 * 获取父ID路径：以"/" 进行分隔
	 *
	 * @return PARENT_PATH - 父ID路径：以"/" 进行分隔
	 */
	public String getParentPath() {
		return parentPath;
	}

	/**
	 * 设置父ID路径：以"/" 进行分隔
	 *
	 * @param parentPath
	 *            父ID路径：以"/" 进行分隔
	 */
	public void setParentPath(String parentPath) {
		this.parentPath = parentPath;
	}

	/**
	 * 获取父职位名称：以"/" 进行分隔
	 *
	 * @return PARENT_NAME - 父职位名称：以"/" 进行分隔
	 */
	public String getParentName() {
		return parentName;
	}

	/**
	 * 设置父职位名称：以"/" 进行分隔
	 *
	 * @param parentName
	 *            父职位名称：以"/" 进行分隔
	 */
	public void setParentName(String parentName) {
		this.parentName = parentName;
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
	 * 获取修改时间
	 *
	 * @return EDIT_TIME - 修改时间
	 */
	public Date getEditTime() {
		return editTime;
	}

	/**
	 * 设置修改时间
	 *
	 * @param editTime
	 *            修改时间
	 */
	public void setEditTime(Date editTime) {
		this.editTime = editTime;
	}

	/**
	 * 获取删除时间
	 *
	 * @return DEL_TIME - 删除时间
	 */
	public Date getDelTime() {
		return delTime;
	}

	/**
	 * 设置删除时间
	 *
	 * @param delTime
	 *            删除时间
	 */
	public void setDelTime(Date delTime) {
		this.delTime = delTime;
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
	 * 获取编辑人
	 *
	 * @return EDIT_USER - 编辑人
	 */
	public String getEditUser() {
		return editUser;
	}

	/**
	 * 设置编辑人
	 *
	 * @param editUser
	 *            编辑人
	 */
	public void setEditUser(String editUser) {
		this.editUser = editUser;
	}

	/**
	 * 获取删除人
	 *
	 * @return DEL_USER - 删除人
	 */
	public String getDelUser() {
		return delUser;
	}

	/**
	 * 设置删除人
	 *
	 * @param delUser
	 *            删除人
	 */
	public void setDelUser(String delUser) {
		this.delUser = delUser;
	}

	/**
	 * 获取是否删除: 0:未删除（默认） 1：删除
	 *
	 * @return DEL - 是否删除: 0:未删除（默认） 1：删除
	 */
	public String getDel() {
		return del;
	}

	/**
	 * 设置是否删除: 0:未删除（默认） 1：删除
	 *
	 * @param del
	 *            是否删除: 0:未删除（默认） 1：删除
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
	 * 获取旧的父ID
	 *
	 * @return OLD_PARENT_ID - 旧的父ID
	 */
	public String getOldParentId() {
		return oldParentId;
	}

	/**
	 * 设置旧的父ID
	 *
	 * @param oldParentId
	 *            旧的父ID
	 */
	public void setOldParentId(String oldParentId) {
		this.oldParentId = oldParentId;
	}

	/**
	 * 获取旧的部门ID
	 *
	 * @return OLD_DEPT_ID - 旧的部门ID
	 */
	public String getOldDeptId() {
		return oldDeptId;
	}

	/**
	 * 设置旧的部门ID
	 *
	 * @param oldDeptId
	 *            旧的部门ID
	 */
	public void setOldDeptId(String oldDeptId) {
		this.oldDeptId = oldDeptId;
	}

	/**
	 * 获取旧的公司ID
	 *
	 * @return OLD_COM_ID - 旧的公司ID
	 */
	public String getOldComId() {
		return oldComId;
	}

	/**
	 * 设置旧的公司ID
	 *
	 * @param oldComId
	 *            旧的公司ID
	 */
	public void setOldComId(String oldComId) {
		this.oldComId = oldComId;
	}
}