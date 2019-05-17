package com.hanzhong.data.web.model.entity.master;

/**
 * @author yifei
 * @date 2019/4/13
 */
public class HighTechEntInfoEntity {
    /**
     * id
     */
    private Long id;
    /**
     * 企业名称
     */
    private String entName;
    /**
     * 统一社会信用代码
     */
    private String usCreditCode;
    /**
     * 更新时间（10位时间戳）
     */
    private Long updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEntName() {
        return entName;
    }

    public void setEntName(String entName) {
        this.entName = entName;
    }

    public String getUsCreditCode() {
        return usCreditCode;
    }

    public void setUsCreditCode(String usCreditCode) {
        this.usCreditCode = usCreditCode;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "HighTechEntInfoEntity{" +
                "id=" + id +
                ", entName='" + entName + '\'' +
                ", usCreditCode='" + usCreditCode + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }
}
