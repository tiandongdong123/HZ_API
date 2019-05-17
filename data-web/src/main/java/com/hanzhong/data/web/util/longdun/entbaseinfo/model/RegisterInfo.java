package com.hanzhong.data.web.util.longdun.entbaseinfo.model;

import java.math.BigDecimal;

/**
 *  
 *  @Description
 *  @Author   luqs   
 *  @Date 2019/1/10 20:02 
 *  @Version  V1.0   
 */
public class RegisterInfo {
    /**
     * 企业名称
     */
    private String entName;
    /**
     * 曾用名
     */
    private String oldName;
    /**
     * 统一社会信用代码
     */
    private String shxydm;
    /**
     * 法定代表人
     */
    private String frdb;
    /**
     * 成立日期
     */
    private String esDate;
    /**
     * 经营状态
     */
    private String entStatus;
    /**
     * 注册资本
     */
    private BigDecimal regCap;
    /**
     * 注册资本币种
     */
    private String regCapCur;
    /**
     * 地址
     */
    private String dom;
    /**
     * 企业类型
     */
    private String entType;
    /**
     * 经营业务范围
     */
    private String opScope;
    /**
     * 登记机关
     */
    private String regOrg;
    /**
     * 经营期限自
     */
    private String opFrom;
    /**
     * 经营期限至
     */
    private String opTo;
    /**
     * 核准日期
     */
    private String apprDate;
    /**
     * 死亡日期
     */
    private String endDate;
    /**
     * 吊销日期
     */
    private String revDate;
    /**
     * 注销日期
     */
    private String canDate;
    /**
     * 地理坐标
     */
    private String jwd;
    /**
     * 行业领域
     */
    private String industry;
    /**
     * 行业领域代码
     */
    private String industryCode;
    /**
     * 登记地省份
     */
    private String province;
    /**
     * 组织机构代码
     */
    private String orgId;
    /**
     * 企业英文名
     */
    private String engName;
    /**
     * 企业官网
     */
    private String webSite;

    public String getEntName() {
        return entName;
    }

    public void setEntName(String entName) {
        this.entName = entName;
    }

    public String getOldName() {
        return oldName;
    }

    public void setOldName(String oldName) {
        this.oldName = oldName;
    }

    public String getShxydm() {
        return shxydm;
    }

    public void setShxydm(String shxydm) {
        this.shxydm = shxydm;
    }

    public String getFrdb() {
        return frdb;
    }

    public void setFrdb(String frdb) {
        this.frdb = frdb;
    }

    public String getEsDate() {
        return esDate;
    }

    public void setEsDate(String esDate) {
        this.esDate = esDate;
    }

    public String getEntStatus() {
        return entStatus;
    }

    public void setEntStatus(String entStatus) {
        this.entStatus = entStatus;
    }

    public BigDecimal getRegCap() {
        return regCap;
    }

    public void setRegCap(BigDecimal regCap) {
        this.regCap = regCap;
    }

    public String getRegCapCur() {
        return regCapCur;
    }

    public void setRegCapCur(String regCapCur) {
        this.regCapCur = regCapCur;
    }

    public String getDom() {
        return dom;
    }

    public void setDom(String dom) {
        this.dom = dom;
    }

    public String getEntType() {
        return entType;
    }

    public void setEntType(String entType) {
        this.entType = entType;
    }

    public String getOpScope() {
        return opScope;
    }

    public void setOpScope(String opScope) {
        this.opScope = opScope;
    }

    public String getRegOrg() {
        return regOrg;
    }

    public void setRegOrg(String regOrg) {
        this.regOrg = regOrg;
    }

    public String getOpFrom() {
        return opFrom;
    }

    public void setOpFrom(String opFrom) {
        this.opFrom = opFrom;
    }

    public String getOpTo() {
        return opTo;
    }

    public void setOpTo(String opTo) {
        this.opTo = opTo;
    }

    public String getApprDate() {
        return apprDate;
    }

    public void setApprDate(String apprDate) {
        this.apprDate = apprDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getRevDate() {
        return revDate;
    }

    public void setRevDate(String revDate) {
        this.revDate = revDate;
    }

    public String getCanDate() {
        return canDate;
    }

    public void setCanDate(String canDate) {
        this.canDate = canDate;
    }

    public String getJwd() {
        return jwd;
    }

    public void setJwd(String jwd) {
        this.jwd = jwd;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getIndustryCode() {
        return industryCode;
    }

    public void setIndustryCode(String industryCode) {
        this.industryCode = industryCode;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getEngName() {
        return engName;
    }

    public void setEngName(String engName) {
        this.engName = engName;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    @Override
    public String toString() {
        return "RegisterInfo{" +
                "entName='" + entName + '\'' +
                ", oldName='" + oldName + '\'' +
                ", shxydm='" + shxydm + '\'' +
                ", frdb='" + frdb + '\'' +
                ", esDate='" + esDate + '\'' +
                ", entStatus='" + entStatus + '\'' +
                ", regCap='" + regCap + '\'' +
                ", regCapCur='" + regCapCur + '\'' +
                ", dom='" + dom + '\'' +
                ", entType='" + entType + '\'' +
                ", opScope='" + opScope + '\'' +
                ", regOrg='" + regOrg + '\'' +
                ", opFrom='" + opFrom + '\'' +
                ", opTo='" + opTo + '\'' +
                ", apprDate='" + apprDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", revDate='" + revDate + '\'' +
                ", canDate='" + canDate + '\'' +
                ", jwd='" + jwd + '\'' +
                ", industry='" + industry + '\'' +
                ", industryCode='" + industryCode + '\'' +
                ", province='" + province + '\'' +
                ", orgId='" + orgId + '\'' +
                ", engName='" + engName + '\'' +
                ", webSite='" + webSite + '\'' +
                '}';
    }
}
