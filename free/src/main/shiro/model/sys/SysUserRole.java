package shiro.model.sys;

import javax.persistence.*;

@Table(name = "t_sys_user_role")
public class SysUserRole {
    @Id
    @Column(name = "ROLE_ID")
    private String roleId;

    @Id
    @Column(name = "USER_ID")
    private String userId;

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
     * @return USER_ID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
}