package com.hanzhong.api.web.util.business.longdun.model;

/**
 *  
 *  @Description
 *  @Author   luqs   
 *  @Date 2019/1/10 15:08 
 *  @Version  V1.0   
 */
public class EntAnnReportSocialSecurityInfoQryParam {
    /**
     * 企业名称
     */
    private String entName;
    /**
     * 年报年份
     */
    private String reportDate;

    public String getEntName() {
        return entName;
    }

    public void setEntName(String entName) {
        this.entName = entName;
    }

    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    @Override
    public String toString() {
        return "EntAnnReportSocialSecurityInfoQryParam{" +
                "entName='" + entName + '\'' +
                ", reportDate='" + reportDate + '\'' +
                '}';
    }
}
