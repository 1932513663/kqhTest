package shiro.model.sys;

import javax.persistence.*;

@Table(name = "t_sys_dept_post_relation")
public class SysDeptPostRelation {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator="UUID")
    private String id;

    @Column(name = "DEPT_ID")
    private String deptId;

    @Column(name = "POST_ID")
    private String postId;

    @Column(name = "STATE_SIGN")
    private Integer stateSign;

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
     * @return DEPT_ID
     */
    public String getDeptId() {
        return deptId;
    }

    /**
     * @param deptId
     */
    public void setDeptId(String deptId) {
        this.deptId = deptId;
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

    /**
     * @return STATE_SIGN
     */
    public Integer getStateSign() {
        return stateSign;
    }

    /**
     * @param stateSign
     */
    public void setStateSign(Integer stateSign) {
        this.stateSign = stateSign;
    }
}