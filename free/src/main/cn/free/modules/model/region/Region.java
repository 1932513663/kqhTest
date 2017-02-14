package cn.free.modules.model.region;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_region")
public class Region {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "CODE")
    private String code;

    @Column(name = "NAME")
    private String name;

    @Column(name = "SORT_NUM")
    private Integer sortNum;

    @Column(name = "PARTENT_ID")
    private String partentId;

    @Column(name = "IS_DEL")
    private String isDel;

    @Column(name = "CREATED_DT")
    private Date createdDt;

    @Column(name = "LAST_UPDATED_DT")
    private Date lastUpdatedDt;

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
     * @return CODE
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return NAME
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return SORT_NUM
     */
    public Integer getSortNum() {
        return sortNum;
    }

    /**
     * @param sortNum
     */
    public void setSortNum(Integer sortNum) {
        this.sortNum = sortNum;
    }

    /**
     * @return PARTENT_ID
     */
    public String getPartentId() {
        return partentId;
    }

    /**
     * @param partentId
     */
    public void setPartentId(String partentId) {
        this.partentId = partentId;
    }

    /**
     * @return IS_DEL
     */
    public String getIsDel() {
        return isDel;
    }

    /**
     * @param isDel
     */
    public void setIsDel(String isDel) {
        this.isDel = isDel;
    }

    /**
     * @return CREATED_DT
     */
    public Date getCreatedDt() {
        return createdDt;
    }

    /**
     * @param createdDt
     */
    public void setCreatedDt(Date createdDt) {
        this.createdDt = createdDt;
    }

    /**
     * @return LAST_UPDATED_DT
     */
    public Date getLastUpdatedDt() {
        return lastUpdatedDt;
    }

    /**
     * @param lastUpdatedDt
     */
    public void setLastUpdatedDt(Date lastUpdatedDt) {
        this.lastUpdatedDt = lastUpdatedDt;
    }
}