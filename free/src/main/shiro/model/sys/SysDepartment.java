package shiro.model.sys;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_sys_department")
public class SysDepartment {
    /**
     * 部门ID
     */
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator="UUID")
    private String id;

    /**
     * 部门父ID
     */
    @Column(name = "PARENT_ID")
    private String parentId;

    /**
     * 部门名称
     */
    @Column(name = "DEPT_NAME")
    private String deptName;

    /**
     * 描述
     */
    @Column(name = "DESCRIPTION")
    private String description;

    /**
     * 部门介绍
     */
    @Column(name = "DEPT_INTRO")
    private String deptIntro;

    /**
     * 部门类型
     */
    @Column(name = "DEPT_TYPE")
    private String deptType;

    /**
     * 公司ID
     */
    @Column(name = "COMPANY_ID")
    private String companyId;

    /**
     * 部门拼写
     */
    @Column(name = "DEPT_SPELL")
    private String deptSpell;

    /**
     * 父ID路径：以 "/" 进行分隔
     */
    @Column(name = "PARENT_PATH")
    private String parentPath;

    /**
     * 父部门名称：以 "/" 进行分隔'
     */
    @Column(name = "PARENT_NAME")
    private String parentName;

    /**
     * 地址
     */
    @Column(name = "ADDRESS")
    private String address;

    /**
     * 联系电话
     */
    @Column(name = "TEL")
    private String tel;

    /**
     * 深度
     */
    @Column(name = "DEEP")
    private Integer deep;

    /**
     * 是否删除 0：未删除  1：删除
     */
    @Column(name = "DEL")
    private String del;

    /**
     * 创建时间
     */
    @Column(name = "ADD_TIME")
    private Date addTime;

    /**
     * 编辑时间
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
     * 删除人
     */
    @Column(name = "DEL_USER")
    private String delUser;

    /**
     * 编辑人
     */
    @Column(name = "EDIT_USER")
    private String editUser;

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
     * 旧的公司ID
     */
    @Column(name = "OLD_COM_ID")
    private String oldComId;

    /**
     * 部门经理ID
     */
    @Column(name = "MANAGER_ID")
    private String managerId;

    /**
     * 旧系统部门经理ID
     */
    @Column(name = "OLD_MANAGER_ID")
    private String oldManagerId;

    /**
     * 部门经理的就职时间
     */
    @Column(name = "TITLE_TIME")
    private Date titleTime;

    /**
     * 获取部门ID
     *
     * @return ID - 部门ID
     */
    public String getId() {
        return id;
    }

    /**
     * 设置部门ID
     *
     * @param id 部门ID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取部门父ID
     *
     * @return PARENT_ID - 部门父ID
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * 设置部门父ID
     *
     * @param parentId 部门父ID
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取部门名称
     *
     * @return DEPT_NAME - 部门名称
     */
    public String getDeptName() {
        return deptName;
    }

    /**
     * 设置部门名称
     *
     * @param deptName 部门名称
     */
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    /**
     * 获取描述
     *
     * @return DESCRIPTION - 描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置描述
     *
     * @param description 描述
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取部门介绍
     *
     * @return DEPT_INTRO - 部门介绍
     */
    public String getDeptIntro() {
        return deptIntro;
    }

    /**
     * 设置部门介绍
     *
     * @param deptIntro 部门介绍
     */
    public void setDeptIntro(String deptIntro) {
        this.deptIntro = deptIntro;
    }

    /**
     * 获取部门类型
     *
     * @return DEPT_TYPE - 部门类型
     */
    public String getDeptType() {
        return deptType;
    }

    /**
     * 设置部门类型
     *
     * @param deptType 部门类型
     */
    public void setDeptType(String deptType) {
        this.deptType = deptType;
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
     * @param companyId 公司ID
     */
    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    /**
     * 获取部门拼写
     *
     * @return DEPT_SPELL - 部门拼写
     */
    public String getDeptSpell() {
        return deptSpell;
    }

    /**
     * 设置部门拼写
     *
     * @param deptSpell 部门拼写
     */
    public void setDeptSpell(String deptSpell) {
        this.deptSpell = deptSpell;
    }

    /**
     * 获取父ID路径：以 "/" 进行分隔
     *
     * @return PARENT_PATH - 父ID路径：以 "/" 进行分隔
     */
    public String getParentPath() {
        return parentPath;
    }

    /**
     * 设置父ID路径：以 "/" 进行分隔
     *
     * @param parentPath 父ID路径：以 "/" 进行分隔
     */
    public void setParentPath(String parentPath) {
        this.parentPath = parentPath;
    }

    /**
     * 获取父部门名称：以 "/" 进行分隔'
     *
     * @return PARENT_NAME - 父部门名称：以 "/" 进行分隔'
     */
    public String getParentName() {
        return parentName;
    }

    /**
     * 设置父部门名称：以 "/" 进行分隔'
     *
     * @param parentName 父部门名称：以 "/" 进行分隔'
     */
    public void setParentName(String parentName) {
        this.parentName = parentName;
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
     * @param address 地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取联系电话
     *
     * @return TEL - 联系电话
     */
    public String getTel() {
        return tel;
    }

    /**
     * 设置联系电话
     *
     * @param tel 联系电话
     */
    public void setTel(String tel) {
        this.tel = tel;
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
     * @param deep 深度
     */
    public void setDeep(Integer deep) {
        this.deep = deep;
    }

    /**
     * 获取是否删除 0：未删除  1：删除
     *
     * @return DEL - 是否删除 0：未删除  1：删除
     */
    public String getDel() {
        return del;
    }

    /**
     * 设置是否删除 0：未删除  1：删除
     *
     * @param del 是否删除 0：未删除  1：删除
     */
    public void setDel(String del) {
        this.del = del;
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
     * @param addTime 创建时间
     */
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    /**
     * 获取编辑时间
     *
     * @return EDIT_TIME - 编辑时间
     */
    public Date getEditTime() {
        return editTime;
    }

    /**
     * 设置编辑时间
     *
     * @param editTime 编辑时间
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
     * @param delTime 删除时间
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
     * @param addUser 创建人
     */
    public void setAddUser(String addUser) {
        this.addUser = addUser;
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
     * @param delUser 删除人
     */
    public void setDelUser(String delUser) {
        this.delUser = delUser;
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
     * @param editUser 编辑人
     */
    public void setEditUser(String editUser) {
        this.editUser = editUser;
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
     * @param oldId 旧系统ID
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
     * @param oldParentId 旧的父ID
     */
    public void setOldParentId(String oldParentId) {
        this.oldParentId = oldParentId;
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
     * @param oldComId 旧的公司ID
     */
    public void setOldComId(String oldComId) {
        this.oldComId = oldComId;
    }

    /**
     * 获取部门经理ID
     *
     * @return MANAGER_ID - 部门经理ID
     */
    public String getManagerId() {
        return managerId;
    }

    /**
     * 设置部门经理ID
     *
     * @param managerId 部门经理ID
     */
    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    /**
     * 获取旧系统部门经理ID
     *
     * @return OLD_MANAGER_ID - 旧系统部门经理ID
     */
    public String getOldManagerId() {
        return oldManagerId;
    }

    /**
     * 设置旧系统部门经理ID
     *
     * @param oldManagerId 旧系统部门经理ID
     */
    public void setOldManagerId(String oldManagerId) {
        this.oldManagerId = oldManagerId;
    }

    /**
     * 获取部门经理的就职时间
     *
     * @return TITLE_TIME - 部门经理的就职时间
     */
    public Date getTitleTime() {
        return titleTime;
    }

    /**
     * 设置部门经理的就职时间
     *
     * @param titleTime 部门经理的就职时间
     */
    public void setTitleTime(Date titleTime) {
        this.titleTime = titleTime;
    }
}