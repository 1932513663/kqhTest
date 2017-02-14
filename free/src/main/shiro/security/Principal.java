package shiro.security;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import shiro.model.sys.MenuRepository;
import shiro.model.sys.SysUser;

/**
 * 
 * @ClassName: Principal
 * @Title: Principal.java
 * @Description: 授权用户信息
 *
 * @author kang
 * @version V1.0
 * @company 麦田
 * @date 2017年2月13日 上午9:35:49
 */
public class Principal extends SysUser implements Serializable {
	private static final long serialVersionUID = 1L;
	// 用户ID
	private String id;

	// 用户旧系统ID
	private String oldUserId;

	// 登录名称
	private String loginName;

	// 编码后的登陆名
	private String encodeLoginName;

	// 编码后gb2312的登陆名
	private String encodeGb2312LoginName;

	// 密码
	private String password;

	// 用户姓名
	private String name;

	// 职位ID
	private String postId;

	// 职位名称
	private String postName;

	// 部门类型 1:大区 2：小区 3：其他
	private String deptType;

	// 所属大片区ID
	private String belongBusinessId;

	// 所属大片区旧系统ID
	private String belongBusinessOldId;

	// 所属大片区名称
	private String belongBusinessName;

	// 所属大区ID
	private String belongBigAreaId;

	// 所属大区名称
	private String belongBigAreaName;

	// 所属大区旧系统ID
	private String belongBigAreaOldId;

	// 所属小区ID
	private String belongAreaId;

	// 所属小区名称
	private String belongAreaName;

	// 所属小区旧系统ID
	private String belongAreaOldId;

	// 部门ID
	private String deptId;

	// 部门名称
	private String deptName;

	// 总部人员所属中心ID
	private String belongZongBuCenterId;

	// 总部人员所属中心名称
	private String belongZongBuCenterName;

	// 部门旧系统ID
	private String deptOldId;

	// 门店ID
	private String shopId;

	// 门店名称
	private String shopName;

	// 门店旧系统ID
	private String shopOldId;

	// 员工类型 0：总部 1：业务线
	private Integer memberType;

	// 菜单
	private MenuRepository menuRepository;

	private Map<String, Object> cacheMap;

	/** 帐号类型 0：非切换 1:切换的 **/
	private String accountType = "0";

	/** 由哪个帐号切换过来的 **/
	private String accountFrom = "";

	/** 编码后gb2312的切换的登陆名 **/
	private String encodeGb2312AccountFrom;

	/** 由哪个帐号切换过来的帐号密码 **/
	private String accountFromPwd = "";

	public Principal(SysUser user) {
		this.id = user.getId();
		this.loginName = user.getUserLoginName();
		this.name = user.getUserName();

		try {
			this.encodeLoginName = java.net.URLEncoder.encode(user.getUserLoginName(), "UTF-8");
			this.encodeGb2312LoginName = java.net.URLEncoder.encode(user.getUserLoginName(), "gb2312");
		} catch (UnsupportedEncodingException e) {
			this.encodeLoginName = "";
			this.encodeGb2312LoginName = "";
		}

		this.password = user.getPassword();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOldUserId() {
		return oldUserId;
	}

	public void setOldUserId(String oldUserId) {
		this.oldUserId = oldUserId;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getEncodeLoginName() {
		return encodeLoginName;
	}

	public void setEncodeLoginName(String encodeLoginName) {
		this.encodeLoginName = encodeLoginName;
	}

	public String getEncodeGb2312LoginName() {
		return encodeGb2312LoginName;
	}

	public void setEncodeGb2312LoginName(String encodeGb2312LoginName) {
		this.encodeGb2312LoginName = encodeGb2312LoginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

	public String getPostName() {
		return postName;
	}

	public void setPostName(String postName) {
		this.postName = postName;
	}

	public String getDeptType() {
		return deptType;
	}

	public void setDeptType(String deptType) {
		this.deptType = deptType;
	}

	public String getBelongBusinessId() {
		return belongBusinessId;
	}

	public void setBelongBusinessId(String belongBusinessId) {
		this.belongBusinessId = belongBusinessId;
	}

	public String getBelongBusinessOldId() {
		return belongBusinessOldId;
	}

	public void setBelongBusinessOldId(String belongBusinessOldId) {
		this.belongBusinessOldId = belongBusinessOldId;
	}

	public String getBelongBusinessName() {
		return belongBusinessName;
	}

	public void setBelongBusinessName(String belongBusinessName) {
		this.belongBusinessName = belongBusinessName;
	}

	public String getBelongBigAreaId() {
		return belongBigAreaId;
	}

	public void setBelongBigAreaId(String belongBigAreaId) {
		this.belongBigAreaId = belongBigAreaId;
	}

	public String getBelongBigAreaName() {
		return belongBigAreaName;
	}

	public void setBelongBigAreaName(String belongBigAreaName) {
		this.belongBigAreaName = belongBigAreaName;
	}

	public String getBelongBigAreaOldId() {
		return belongBigAreaOldId;
	}

	public void setBelongBigAreaOldId(String belongBigAreaOldId) {
		this.belongBigAreaOldId = belongBigAreaOldId;
	}

	public String getBelongAreaId() {
		return belongAreaId;
	}

	public void setBelongAreaId(String belongAreaId) {
		this.belongAreaId = belongAreaId;
	}

	public String getBelongAreaName() {
		return belongAreaName;
	}

	public void setBelongAreaName(String belongAreaName) {
		this.belongAreaName = belongAreaName;
	}

	public String getBelongAreaOldId() {
		return belongAreaOldId;
	}

	public void setBelongAreaOldId(String belongAreaOldId) {
		this.belongAreaOldId = belongAreaOldId;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getBelongZongBuCenterId() {
		return belongZongBuCenterId;
	}

	public void setBelongZongBuCenterId(String belongZongBuCenterId) {
		this.belongZongBuCenterId = belongZongBuCenterId;
	}

	public String getBelongZongBuCenterName() {
		return belongZongBuCenterName;
	}

	public void setBelongZongBuCenterName(String belongZongBuCenterName) {
		this.belongZongBuCenterName = belongZongBuCenterName;
	}

	public String getDeptOldId() {
		return deptOldId;
	}

	public void setDeptOldId(String deptOldId) {
		this.deptOldId = deptOldId;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getShopOldId() {
		return shopOldId;
	}

	public void setShopOldId(String shopOldId) {
		this.shopOldId = shopOldId;
	}

	public Integer getMemberType() {
		return memberType;
	}

	public void setMemberType(Integer memberType) {
		this.memberType = memberType;
	}

	public MenuRepository getMenuRepository() {
		return menuRepository;
	}

	public void setMenuRepository(MenuRepository menuRepository) {
		this.menuRepository = menuRepository;
	}

	public Map<String, Object> getCacheMap() {
		return cacheMap;
	}

	public void setCacheMap(Map<String, Object> cacheMap) {
		this.cacheMap = cacheMap;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getAccountFrom() {
		return accountFrom;
	}

	public void setAccountFrom(String accountFrom) {
		this.accountFrom = accountFrom;
	}

	public String getEncodeGb2312AccountFrom() {
		return encodeGb2312AccountFrom;
	}

	public void setEncodeGb2312AccountFrom(String encodeGb2312AccountFrom) {
		this.encodeGb2312AccountFrom = encodeGb2312AccountFrom;
	}

	public String getAccountFromPwd() {
		return accountFromPwd;
	}

	public void setAccountFromPwd(String accountFromPwd) {
		this.accountFromPwd = accountFromPwd;
	}
}
