package com.hanzhong.data.web.model.entity.master;

import java.util.Date;

/**
 *  
 *  @Description
 *  @Author   luqs   
 *  @Date 2019/1/4 19:32 
 *  @Version  V1.0   
 */
public class TblIpWhiteListEntity {
    /**
     * id
     */
    private String id;
    /**
     * 名称
     */
    private String name;
    /**
     * ip段
     */
    private String ipSegment;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 备注
     */
    private String remark;
    /**
     * 创建人
     */
    private String createPerson;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新人
     */
    private String updatePerson;
    /**
     * 更新时间
     */
    private Date updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIpSegment() {
        return ipSegment;
    }

    public void setIpSegment(String ipSegment) {
        this.ipSegment = ipSegment;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreatePerson() {
        return createPerson;
    }

    public void setCreatePerson(String createPerson) {
        this.createPerson = createPerson;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdatePerson() {
        return updatePerson;
    }

    public void setUpdatePerson(String updatePerson) {
        this.updatePerson = updatePerson;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "TblIpWhiteListEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", ipSegment='" + ipSegment + '\'' +
                ", status=" + status +
                ", remark='" + remark + '\'' +
                ", createPerson='" + createPerson + '\'' +
                ", createTime=" + createTime +
                ", updatePerson='" + updatePerson + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }
}
