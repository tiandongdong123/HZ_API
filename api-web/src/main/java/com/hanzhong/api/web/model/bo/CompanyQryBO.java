package com.hanzhong.api.web.model.bo;

/**
 *  
 *  @Description
 *  @Author   luqs   
 *  @Date 2018/11/7 23:13 
 *  @Version  V1.0   
 */
public class CompanyQryBO {
    /**
     * 公司名称
     */
    private String companyName;
    /**
     * 统一社会信用代码
     */
    private String usCreditCode;
    /**
     * 组织结构代码
     */
    private String orgCode;
    /**
     * 企业状态
     */
    private String entStatus;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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

    public String getEntStatus() {
        return entStatus;
    }

    public void setEntStatus(String entStatus) {
        this.entStatus = entStatus;
    }

    @Override
    public String toString() {
        return "CompanyQryBO{" +
                "companyName='" + companyName + '\'' +
                ", usCreditCode='" + usCreditCode + '\'' +
                ", orgCode='" + orgCode + '\'' +
                ", entStatus='" + entStatus + '\'' +
                '}';
    }
}
