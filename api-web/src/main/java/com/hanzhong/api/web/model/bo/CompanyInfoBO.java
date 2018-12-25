package com.hanzhong.api.web.model.bo;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *  
 *  @Description
 *  @Author   luqs   
 *  @Date 2018/11/7 19:27 
 *  @Version  V1.0   
 */
public class CompanyInfoBO implements Serializable {

    private static final long serialVersionUID = -4963266899668807475L;

    /**
     * 主体身份代码
     */
    private String pripId;
    /**
     * 企业(机构)名称
     */
    private String entName;
    /**
     * 原注册号
     */
    private String oriRegNo;
    /**
     * 注册号
     */
    private String regNo;
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
     * 许可经营项目
     */
    private String abuItem;
    /**
     * 一般经营项目
     */
    private String cbuItem;
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
     * 法人
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
     * 属地监管工商所
     */
    private String localAdm;
    /**
     * 分类日期
     */
    private Date assDate;
    /**
     * 企业分类码
     */
    private String entCat;
    /**
     * 经营方式
     */
    private String opForm;
    /**
     * 经营范围及方式
     */
    private String opScoAndForm;
    /**
     * 兼营范围
     */
    private String ptBusScope;
    /**
     * 住所所在行政区划
     */
    private String domDistrict;
    /**
     * 住所所在经济开发区
     */
    private String ecoTecDevZone;
    /**
     * 住所产权
     */
    private String domProRight;
    /**
     * 经营场所所在行政区划
     */
    private String opLocDistrict;
    /**
     * 设立方式
     */
    private String insForm;
    /**
     * 合伙人数
     */
    private Long parNum;
    /**
     * 有限合伙人数
     */
    private Long limParNum;
    /**
     * 合伙方式
     */
    private String parForm;
    /**
     * 执行人数
     */
    private Long exeNum;
    /**
     * 从业人数
     */
    private Long empNum;

    public String getPripId() {
        return pripId;
    }

    public void setPripId(String pripId) {
        this.pripId = pripId;
    }

    public String getEntName() {
        return entName;
    }

    public void setEntName(String entName) {
        this.entName = entName;
    }

    public String getOriRegNo() {
        return oriRegNo;
    }

    public void setOriRegNo(String oriRegNo) {
        this.oriRegNo = oriRegNo;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
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

    public String getAbuItem() {
        return abuItem;
    }

    public void setAbuItem(String abuItem) {
        this.abuItem = abuItem;
    }

    public String getCbuItem() {
        return cbuItem;
    }

    public void setCbuItem(String cbuItem) {
        this.cbuItem = cbuItem;
    }

    public String getOpScope() {
        return opScope;
    }

    public void setOpScope(String opScope) {
        this.opScope = opScope;
    }

    @JsonFormat(pattern="yyyy-MM-dd")
    public Date getEsDate() {
        return esDate;
    }

    public void setEsDate(Date esDate) {
        this.esDate = esDate;
    }

    @JsonFormat(pattern="yyyy-MM-dd")
    public Date getApprDate() {
        return apprDate;
    }

    public void setApprDate(Date apprDate) {
        this.apprDate = apprDate;
    }

    @JsonFormat(pattern="yyyy-MM-dd")
    public Date getOpFrom() {
        return opFrom;
    }

    public void setOpFrom(Date opFrom) {
        this.opFrom = opFrom;
    }

    @JsonFormat(pattern="yyyy-MM-dd")
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

    public String getLocalAdm() {
        return localAdm;
    }

    public void setLocalAdm(String localAdm) {
        this.localAdm = localAdm;
    }

    @JsonFormat(pattern="yyyy-MM-dd")
    public Date getAssDate() {
        return assDate;
    }

    public void setAssDate(Date assDate) {
        this.assDate = assDate;
    }

    public String getEntCat() {
        return entCat;
    }

    public void setEntCat(String entCat) {
        this.entCat = entCat;
    }

    public String getOpForm() {
        return opForm;
    }

    public void setOpForm(String opForm) {
        this.opForm = opForm;
    }

    public String getOpScoAndForm() {
        return opScoAndForm;
    }

    public void setOpScoAndForm(String opScoAndForm) {
        this.opScoAndForm = opScoAndForm;
    }

    public String getPtBusScope() {
        return ptBusScope;
    }

    public void setPtBusScope(String ptBusScope) {
        this.ptBusScope = ptBusScope;
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

    public String getDomProRight() {
        return domProRight;
    }

    public void setDomProRight(String domProRight) {
        this.domProRight = domProRight;
    }

    public String getOpLocDistrict() {
        return opLocDistrict;
    }

    public void setOpLocDistrict(String opLocDistrict) {
        this.opLocDistrict = opLocDistrict;
    }

    public String getInsForm() {
        return insForm;
    }

    public void setInsForm(String insForm) {
        this.insForm = insForm;
    }

    public Long getParNum() {
        return parNum;
    }

    public void setParNum(Long parNum) {
        this.parNum = parNum;
    }

    public Long getLimParNum() {
        return limParNum;
    }

    public void setLimParNum(Long limParNum) {
        this.limParNum = limParNum;
    }

    public String getParForm() {
        return parForm;
    }

    public void setParForm(String parForm) {
        this.parForm = parForm;
    }

    public Long getExeNum() {
        return exeNum;
    }

    public void setExeNum(Long exeNum) {
        this.exeNum = exeNum;
    }

    public Long getEmpNum() {
        return empNum;
    }

    public void setEmpNum(Long empNum) {
        this.empNum = empNum;
    }

    @Override
    public String toString() {
        return "CompanyInfoBO{" +
                "pripId='" + pripId + '\'' +
                ", entName='" + entName + '\'' +
                ", oriRegNo='" + oriRegNo + '\'' +
                ", regNo='" + regNo + '\'' +
                ", regCap=" + regCap +
                ", regCapCur='" + regCapCur + '\'' +
                ", entType='" + entType + '\'' +
                ", sExtEntProperty='" + sExtEntProperty + '\'' +
                ", entStatus='" + entStatus + '\'' +
                ", abuItem='" + abuItem + '\'' +
                ", cbuItem='" + cbuItem + '\'' +
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
                ", localAdm='" + localAdm + '\'' +
                ", assDate=" + assDate +
                ", entCat='" + entCat + '\'' +
                ", opForm='" + opForm + '\'' +
                ", opScoAndForm='" + opScoAndForm + '\'' +
                ", ptBusScope='" + ptBusScope + '\'' +
                ", domDistrict='" + domDistrict + '\'' +
                ", ecoTecDevZone='" + ecoTecDevZone + '\'' +
                ", domProRight='" + domProRight + '\'' +
                ", opLocDistrict='" + opLocDistrict + '\'' +
                ", insForm='" + insForm + '\'' +
                ", parNum=" + parNum +
                ", limParNum=" + limParNum +
                ", parForm='" + parForm + '\'' +
                ", exeNum=" + exeNum +
                ", empNum=" + empNum +
                '}';
    }
}
