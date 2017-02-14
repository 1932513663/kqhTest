package shiro.model.sys;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_sys_user")
public class SysUser {
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "UUID")
	private String id;

	/**
	 * 用户登陆名
	 */
	@Column(name = "USER_LOGIN_NAME")
	private String userLoginName;

	/**
	 * 用户密码
	 */
	@Column(name = "PASSWORD")
	private String password;

	/**
	 * 用户姓名
	 */
	@Column(name = "USER_NAME")
	private String userName;

	/**
	 * 用户状态,1：在职，0：离职，2：删除 3：停薪留职
	 */
	@Column(name = "STATUS")
	private String status;

	/**
	 * 锁定状态,0:正常 1：账号锁定 2：离职锁定 3：考勤锁定 4：评级异动锁定
	 */
	@Column(name = "LOCK_STATUS")
	private String lockStatus;

	/**
	 * 电话
	 */
	@Column(name = "TEL")
	private String tel;

	/**
	 * 手机
	 */
	@Column(name = "MOBILE")
	private String mobile;

	/**
	 * 入职时间
	 */
	@Column(name = "HIRE_TIME")
	private Date hireTime;

	/**
	 * 电子邮件
	 */
	@Column(name = "EMAIL")
	private String email;

	/**
	 * 性别：0：男 1：女
	 */
	@Column(name = "SEX")
	private String sex;

	/**
	 * 身份证号
	 */
	@Column(name = "ID_CARD")
	private String idCard;

	/**
	 * 出生日期
	 */
	@Column(name = "BIRTHDAY")
	private Date birthday;

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
	 * 密码更新时间
	 */
	@Column(name = "PWD_EDIT_TIME")
	private Date pwdEditTime;

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
	 * 公司ID
	 */
	@Column(name = "COMPANY_ID")
	private String companyId;

	/**
	 * 部门ID
	 */
	@Column(name = "DEPT_ID")
	private String deptId;

	/**
	 * 职位ID
	 */
	@Column(name = "POST_ID")
	private String postId;

	/**
	 * 门店ID
	 */
	@Column(name = "SHOP_ID")
	private String shopId;

	/**
	 * 旧系统ID
	 */
	@Column(name = "OLD_ID")
	private String oldId;

	/**
	 * 旧公司ID
	 */
	@Column(name = "OLD_COM_ID")
	private String oldComId;

	/**
	 * 旧部门ID
	 */
	@Column(name = "OLD_DEPT_ID")
	private String oldDeptId;

	/**
	 * 旧职位ID
	 */
	@Column(name = "OLD_POST_ID")
	private String oldPostId;

	/**
	 * 旧门店ID
	 */
	@Column(name = "OLD_SHOP_ID")
	private String oldShopId;

	/**
	 * 旧的人事ID
	 */
	@Column(name = "OLD_USER_ID")
	private String oldUserId;

	/**
	 * 复职时间
	 */
	@Column(name = "REINSTATEMENT_TIME")
	private Date reinstatementTime;

	/**
	 * 员工编号
	 */
	@Column(name = "STAFF_NO")
	private String staffNo;

	/**
	 * 离职时间
	 */
	@Column(name = "RESIGNATE_TIME")
	private Date resignateTime;

	/**
	 * 职级名称
	 */
	@Column(name = "POST_LEVEL_NAME")
	private String postLevelName;

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
	 * 获取用户登陆名
	 *
	 * @return USER_LOGIN_NAME - 用户登陆名
	 */
	public String getUserLoginName() {
		return userLoginName;
	}

	/**
	 * 设置用户登陆名
	 *
	 * @param userLoginName
	 *            用户登陆名
	 */
	public void setUserLoginName(String userLoginName) {
		this.userLoginName = userLoginName;
	}

	/**
	 * 获取用户密码
	 *
	 * @return PASSWORD - 用户密码
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 设置用户密码
	 *
	 * @param password
	 *            用户密码
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 获取用户姓名
	 *
	 * @return USER_NAME - 用户姓名
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * 设置用户姓名
	 *
	 * @param userName
	 *            用户姓名
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * 获取用户状态,1：在职，0：离职，2：删除 3：停薪留职
	 *
	 * @return STATUS - 用户状态,1：在职，0：离职，2：删除 3：停薪留职
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * 设置用户状态,1：在职，0：离职，2：删除 3：停薪留职
	 *
	 * @param status
	 *            用户状态,1：在职，0：离职，2：删除 3：停薪留职
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * 获取锁定状态,0:正常 1：账号锁定 2：离职锁定 3：考勤锁定 4：评级异动锁定
	 *
	 * @return LOCK_STATUS - 锁定状态,0:正常 1：账号锁定 2：离职锁定 3：考勤锁定 4：评级异动锁定
	 */
	public String getLockStatus() {
		return lockStatus;
	}

	/**
	 * 设置锁定状态,0:正常 1：账号锁定 2：离职锁定 3：考勤锁定 4：评级异动锁定
	 *
	 * @param lockStatus
	 *            锁定状态,0:正常 1：账号锁定 2：离职锁定 3：考勤锁定 4：评级异动锁定
	 */
	public void setLockStatus(String lockStatus) {
		this.lockStatus = lockStatus;
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
	 * 获取手机
	 *
	 * @return MOBILE - 手机
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * 设置手机
	 *
	 * @param mobile
	 *            手机
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * 获取入职时间
	 *
	 * @return HIRE_TIME - 入职时间
	 */
	public Date getHireTime() {
		return hireTime;
	}

	/**
	 * 设置入职时间
	 *
	 * @param hireTime
	 *            入职时间
	 */
	public void setHireTime(Date hireTime) {
		this.hireTime = hireTime;
	}

	/**
	 * 获取电子邮件
	 *
	 * @return EMAIL - 电子邮件
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * 设置电子邮件
	 *
	 * @param email
	 *            电子邮件
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * 获取性别：0：男 1：女
	 *
	 * @return SEX - 性别：0：男 1：女
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * 设置性别：0：男 1：女
	 *
	 * @param sex
	 *            性别：0：男 1：女
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * 获取身份证号
	 *
	 * @return ID_CARD - 身份证号
	 */
	public String getIdCard() {
		return idCard;
	}

	/**
	 * 设置身份证号
	 *
	 * @param idCard
	 *            身份证号
	 */
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	/**
	 * 获取出生日期
	 *
	 * @return BIRTHDAY - 出生日期
	 */
	public Date getBirthday() {
		return birthday;
	}

	/**
	 * 设置出生日期
	 *
	 * @param birthday
	 *            出生日期
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
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
	 * 获取密码更新时间
	 *
	 * @return PWD_EDIT_TIME - 密码更新时间
	 */
	public Date getPwdEditTime() {
		return pwdEditTime;
	}

	/**
	 * 设置密码更新时间
	 *
	 * @param pwdEditTime
	 *            密码更新时间
	 */
	public void setPwdEditTime(Date pwdEditTime) {
		this.pwdEditTime = pwdEditTime;
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
	 * 获取职位ID
	 *
	 * @return POST_ID - 职位ID
	 */
	public String getPostId() {
		return postId;
	}

	/**
	 * 设置职位ID
	 *
	 * @param postId
	 *            职位ID
	 */
	public void setPostId(String postId) {
		this.postId = postId;
	}

	/**
	 * 获取门店ID
	 *
	 * @return SHOP_ID - 门店ID
	 */
	public String getShopId() {
		return shopId;
	}

	/**
	 * 设置门店ID
	 *
	 * @param shopId
	 *            门店ID
	 */
	public void setShopId(String shopId) {
		this.shopId = shopId;
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
	 * 获取旧公司ID
	 *
	 * @return OLD_COM_ID - 旧公司ID
	 */
	public String getOldComId() {
		return oldComId;
	}

	/**
	 * 设置旧公司ID
	 *
	 * @param oldComId
	 *            旧公司ID
	 */
	public void setOldComId(String oldComId) {
		this.oldComId = oldComId;
	}

	/**
	 * 获取旧部门ID
	 *
	 * @return OLD_DEPT_ID - 旧部门ID
	 */
	public String getOldDeptId() {
		return oldDeptId;
	}

	/**
	 * 设置旧部门ID
	 *
	 * @param oldDeptId
	 *            旧部门ID
	 */
	public void setOldDeptId(String oldDeptId) {
		this.oldDeptId = oldDeptId;
	}

	/**
	 * 获取旧职位ID
	 *
	 * @return OLD_POST_ID - 旧职位ID
	 */
	public String getOldPostId() {
		return oldPostId;
	}

	/**
	 * 设置旧职位ID
	 *
	 * @param oldPostId
	 *            旧职位ID
	 */
	public void setOldPostId(String oldPostId) {
		this.oldPostId = oldPostId;
	}

	/**
	 * 获取旧门店ID
	 *
	 * @return OLD_SHOP_ID - 旧门店ID
	 */
	public String getOldShopId() {
		return oldShopId;
	}

	/**
	 * 设置旧门店ID
	 *
	 * @param oldShopId
	 *            旧门店ID
	 */
	public void setOldShopId(String oldShopId) {
		this.oldShopId = oldShopId;
	}

	/**
	 * 获取旧的人事ID
	 *
	 * @return OLD_USER_ID - 旧的人事ID
	 */
	public String getOldUserId() {
		return oldUserId;
	}

	/**
	 * 设置旧的人事ID
	 *
	 * @param oldUserId
	 *            旧的人事ID
	 */
	public void setOldUserId(String oldUserId) {
		this.oldUserId = oldUserId;
	}

	/**
	 * 获取复职时间
	 *
	 * @return REINSTATEMENT_TIME - 复职时间
	 */
	public Date getReinstatementTime() {
		return reinstatementTime;
	}

	/**
	 * 设置复职时间
	 *
	 * @param reinstatementTime
	 *            复职时间
	 */
	public void setReinstatementTime(Date reinstatementTime) {
		this.reinstatementTime = reinstatementTime;
	}

	/**
	 * 获取员工编号
	 *
	 * @return STAFF_NO - 员工编号
	 */
	public String getStaffNo() {
		return staffNo;
	}

	/**
	 * 设置员工编号
	 *
	 * @param staffNo
	 *            员工编号
	 */
	public void setStaffNo(String staffNo) {
		this.staffNo = staffNo;
	}

	/**
	 * 获取离职时间
	 *
	 * @return RESIGNATE_TIME - 离职时间
	 */
	public Date getResignateTime() {
		return resignateTime;
	}

	/**
	 * 设置离职时间
	 *
	 * @param resignateTime
	 *            离职时间
	 */
	public void setResignateTime(Date resignateTime) {
		this.resignateTime = resignateTime;
	}

	/**
	 * 获取职级名称
	 *
	 * @return POST_LEVEL_NAME - 职级名称
	 */
	public String getPostLevelName() {
		return postLevelName;
	}

	/**
	 * 设置职级名称
	 *
	 * @param postLevelName
	 *            职级名称
	 */
	public void setPostLevelName(String postLevelName) {
		this.postLevelName = postLevelName;
	}
}