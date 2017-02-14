package shiro.model.sys;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_sys_menu")
public class SysMenu {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator="UUID")
    private String id;

    @Column(name = "PARENT_ID")
    private String parentId;

    @Column(name = "MENU_NAME")
    private String menuName;

    @Column(name = "FORWARD")
    private String forward;

    @Column(name = "TARGET")
    private String target;

    @Column(name = "IMAGE")
    private String image;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "SEQ")
    private Integer seq;

    @Column(name = "ADD_TIME")
    private Date addTime;

    @Column(name = "EDIT_TIME")
    private Date editTime;

    @Column(name = "PERMISSION_CODE")
    private String permissionCode;

    @Column(name = "IS_SHOW")
    private String isShow;

    @Column(name = "SYSTEM_ID")
    private String systemId;
    
    @Transient
    private String state;
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
     * @return PARENT_ID
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * @param parentId
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    /**
     * @return MENU_NAME
     */
    public String getMenuName() {
        return menuName;
    }

    /**
     * @param menuName
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    /**
     * @return FORWARD
     */
    public String getForward() {
        return forward;
    }

    /**
     * @param forward
     */
    public void setForward(String forward) {
        this.forward = forward;
    }

    /**
     * @return TARGET
     */
    public String getTarget() {
        return target;
    }

    /**
     * @param target
     */
    public void setTarget(String target) {
        this.target = target;
    }

    /**
     * @return IMAGE
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * @return DESCRIPTION
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return SEQ
     */
    public Integer getSeq() {
        return seq;
    }

    /**
     * @param seq
     */
    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    /**
     * @return ADD_TIME
     */
    public Date getAddTime() {
        return addTime;
    }

    /**
     * @param addTime
     */
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    /**
     * @return EDIT_TIME
     */
    public Date getEditTime() {
        return editTime;
    }

    /**
     * @param editTime
     */
    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }

    /**
     * @return PERMISSION_CODE
     */
    public String getPermissionCode() {
        return permissionCode;
    }

    /**
     * @param permissionCode
     */
    public void setPermissionCode(String permissionCode) {
        this.permissionCode = permissionCode;
    }

    /**
     * @return IS_SHOW
     */
    public String getIsShow() {
        return isShow;
    }

    /**
     * @param isShow
     */
    public void setIsShow(String isShow) {
        this.isShow = isShow;
    }

    /**
     * @return SYSTEM_ID
     */
    public String getSystemId() {
        return systemId;
    }

    /**
     * @param systemId
     */
    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}