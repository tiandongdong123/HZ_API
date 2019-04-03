package com.hanzhong.data.web.model.bo;

/**
 * 企业唯一标识信息
 *
 * @author yifei
 * @date 2019/3/30
 */
public class EntUniqueInfoBO {
    /**
     * 主体身份代码
     */
    private String pripId;
    /**
     * 统一社会信用代码
     */
    private String usCreditCode;
    /**
     * 组织结构代码
     */
    private String orgCode;
    /**
     * 企业(机构)名称
     */
    private String entName;
    /**
     * 企业状态
     */
    private String entStatus;

    public String getPripId() {
        return pripId;
    }

    public void setPripId(String pripId) {
        this.pripId = pripId;
    }

    public String getUsCreditCode() {
        return usCreditCode;
    }

    public void setUsCreditCode(String usCreditCode) {
        this.usCreditCode = usCreditCode;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getEntName() {
        return entName;
    }

    public void setEntName(String entName) {
        this.entName = entName;
    }

    public String getEntStatus() {
        return entStatus;
    }

    public void setEntStatus(String entStatus) {
        this.entStatus = entStatus;
    }

    @Override
    public String toString() {
        return "EntUniqueInfoBO{" +
                "pripId='" + pripId + '\'' +
                ", usCreditCode='" + usCreditCode + '\'' +
                ", orgCode='" + orgCode + '\'' +
                ", entName='" + entName + '\'' +
                ", entStatus='" + entStatus + '\'' +
                '}';
    }
}
