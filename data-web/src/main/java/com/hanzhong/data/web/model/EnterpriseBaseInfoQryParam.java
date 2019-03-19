package com.hanzhong.data.web.model;

/**
 *  
 *  @Description
 *  @Author   luqs   
 *  @Date 2018/11/7 23:13 
 *  @Version  V1.0   
 */
public class EnterpriseBaseInfoQryParam {
    /**
     * 企业名称
     */
    private String entName;
    /**
     * 统一社会信用代码
     */
    private String usCreditCode;
    /**
     * 组织结构代码
     */
    private String orgCode;

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

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    @Override
    public String toString() {
        return "EnterpriseBaseInfoQryParam{" +
                "entName='" + entName + '\'' +
                ", usCreditCode='" + usCreditCode + '\'' +
                ", orgCode='" + orgCode + '\'' +
                '}';
    }
}
