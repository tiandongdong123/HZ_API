package com.hanzhong.api.web.model.bo;

import java.math.BigDecimal;
import java.util.Date;

/**
 *  
 *  @Description
 *  @Author   luqs   
 *  @Date 2018/11/7 19:27 
 *  @Version  V1.0   
 */
public class CompanyInfoBO {

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
     * 所属行业
     */
    private String industry;
    /**
     * 注册资金
     */
    private BigDecimal regCap;
    /**
     * 注册资本(金)币种
     */
    private String regCapCur;
    /**
     * 企业(机构)类型
     */
    private String entType;
    /**
     * 企业性质
     */
    private String sExtEntProperty;
    /**
     * 企业状态
     */
    private String entStatus;
    /**
     * 经营(业务)范围
     */
    private String opScope;
    /**
     * 成立日期
     */
    private Date esDate;
    /**
     * 核准日期
     */
    private Date apprDate;
    /**
     * 经营(驻在)期限自
     */
    private Date opFrom;
    /**
     * 经营(驻在)期限至
     */
    private Date opTo;
    /**
     * 法定代表人
     */
    private String name;
    /**
     * 登记机关
     */
    private String regOrg;
    /**
     * 住所
     */
    private String dom;
    /**
     * 经营场所
     */
    private String opLoc;
    /**
     * 邮政编码
     */
    private String postalCode;
    /**
     * 住所所在行政区划
     */
    private String domDistrict;
    /**
     * 住所所在经济开发区
     */
    private String ecoTecDevZone;
    /**
     * 省份
     */
    private Integer province;
    /**
     * 市
     */
    private Integer city;
    /**
     * 区
     */
    private Integer area;

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

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
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

    public String getEntType() {
        return entType;
    }

    public void setEntType(String entType) {
        this.entType = entType;
    }

    public String getsExtEntProperty() {
        return sExtEntProperty;
    }

    public void setsExtEntProperty(String sExtEntProperty) {
        this.sExtEntProperty = sExtEntProperty;
    }

    public String getEntStatus() {
        return entStatus;
    }

    public void setEntStatus(String entStatus) {
        this.entStatus = entStatus;
    }

    public String getOpScope() {
        return opScope;
    }

    public void setOpScope(String opScope) {
        this.opScope = opScope;
    }

    public Date getEsDate() {
        return esDate;
    }

    public void setEsDate(Date esDate) {
        this.esDate = esDate;
    }

    public Date getApprDate() {
        return apprDate;
    }

    public void setApprDate(Date apprDate) {
        this.apprDate = apprDate;
    }

    public Date getOpFrom() {
        return opFrom;
    }

    public void setOpFrom(Date opFrom) {
        this.opFrom = opFrom;
    }

    public Date getOpTo() {
        return opTo;
    }

    public void setOpTo(Date opTo) {
        this.opTo = opTo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegOrg() {
        return regOrg;
    }

    public void setRegOrg(String regOrg) {
        this.regOrg = regOrg;
    }

    public String getDom() {
        return dom;
    }

    public void setDom(String dom) {
        this.dom = dom;
    }

    public String getOpLoc() {
        return opLoc;
    }

    public void setOpLoc(String opLoc) {
        this.opLoc = opLoc;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getDomDistrict() {
        return domDistrict;
    }

    public void setDomDistrict(String domDistrict) {
        this.domDistrict = domDistrict;
    }

    public String getEcoTecDevZone() {
        return ecoTecDevZone;
    }

    public void setEcoTecDevZone(String ecoTecDevZone) {
        this.ecoTecDevZone = ecoTecDevZone;
    }

    public Integer getProvince() {
        return province;
    }

    public void setProvince(Integer province) {
        this.province = province;
    }

    public Integer getCity() {
        return city;
    }

    public void setCity(Integer city) {
        this.city = city;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    @Override
    public String toString() {
        return "CompanyInfoBO{" +
                "pripId='" + pripId + '\'' +
                ", usCreditCode='" + usCreditCode + '\'' +
                ", orgCode='" + orgCode + '\'' +
                ", entName='" + entName + '\'' +
                ", industry='" + industry + '\'' +
                ", regCap=" + regCap +
                ", regCapCur='" + regCapCur + '\'' +
                ", entType='" + entType + '\'' +
                ", sExtEntProperty='" + sExtEntProperty + '\'' +
                ", entStatus='" + entStatus + '\'' +
                ", opScope='" + opScope + '\'' +
                ", esDate=" + esDate +
                ", apprDate=" + apprDate +
                ", opFrom=" + opFrom +
                ", opTo=" + opTo +
                ", name='" + name + '\'' +
                ", regOrg='" + regOrg + '\'' +
                ", dom='" + dom + '\'' +
                ", opLoc='" + opLoc + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", domDistrict='" + domDistrict + '\'' +
                ", ecoTecDevZone='" + ecoTecDevZone + '\'' +
                ", province=" + province +
                ", city=" + city +
                ", area=" + area +
                '}';
    }
}
