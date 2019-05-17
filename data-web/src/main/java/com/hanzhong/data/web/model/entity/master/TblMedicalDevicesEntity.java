package com.hanzhong.data.web.model.entity.master;

import java.util.Date;

/**
 * 医疗器械
 *
 * @author yifei
 * @date 2019/4/14
 */
public class TblMedicalDevicesEntity {
    /**
     * id
     */
    private Long id;
    /**
     * 主体身份代码
     */
    private String pripId;
    /**
     * 注册证编号
     */
    private String regCertNum;
    /**
     * 注册人名称
     */
    private String entName;
    /**
     * 注册人住所
     */
    private String dom;
    /**
     * 生产地址
     */
    private String opLoc;
    /**
     * 代理人名称
     */
    private String agyName;
    /**
     * 代理人住所
     */
    private String agyDom;
    /**
     * 产品名称
     */
    private String prodName;
    /**
     * 规格
     */
    private String spec;
    /**
     * 结构及组成
     */
    private String struc;
    /**
     * 适用范围
     */
    private String appScope;
    /**
     * 其他内容
     */
    private String othDesc;
    /**
     * 备注
     */
    private String remark;
    /**
     * 批准日期
     */
    private String aprvDate;
    /**
     * 有效期至
     */
    private String expDate;
    /**
     * 产品标准
     */
    private String prodStd;
    /**
     * 变更日期
     */
    private String revDate;
    /**
     * 邮编
     */
    private String postalCode;
    /**
     * 主要组成成分
     */
    private String majorConst;
    /**
     * 预期用途
     */
    private String intendUse;
    /**
     * 产品储存条件及有效期
     */
    private String stgCond;
    /**
     * 审批部门
     */
    private String aprvDept;
    /**
     * 变更情况
     */
    private String alteration;
    /**
     * 详情页链接
     */
    private String detlLink;
    /**
     * 更新时间
     */
    private Date updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPripId() {
        return pripId;
    }

    public void setPripId(String pripId) {
        this.pripId = pripId;
    }

    public String getRegCertNum() {
        return regCertNum;
    }

    public void setRegCertNum(String regCertNum) {
        this.regCertNum = regCertNum;
    }

    public String getEntName() {
        return entName;
    }

    public void setEntName(String entName) {
        this.entName = entName;
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

    public String getAgyName() {
        return agyName;
    }

    public void setAgyName(String agyName) {
        this.agyName = agyName;
    }

    public String getAgyDom() {
        return agyDom;
    }

    public void setAgyDom(String agyDom) {
        this.agyDom = agyDom;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getStruc() {
        return struc;
    }

    public void setStruc(String struc) {
        this.struc = struc;
    }

    public String getAppScope() {
        return appScope;
    }

    public void setAppScope(String appScope) {
        this.appScope = appScope;
    }

    public String getOthDesc() {
        return othDesc;
    }

    public void setOthDesc(String othDesc) {
        this.othDesc = othDesc;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAprvDate() {
        return aprvDate;
    }

    public void setAprvDate(String aprvDate) {
        this.aprvDate = aprvDate;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public String getProdStd() {
        return prodStd;
    }

    public void setProdStd(String prodStd) {
        this.prodStd = prodStd;
    }

    public String getRevDate() {
        return revDate;
    }

    public void setRevDate(String revDate) {
        this.revDate = revDate;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getMajorConst() {
        return majorConst;
    }

    public void setMajorConst(String majorConst) {
        this.majorConst = majorConst;
    }

    public String getIntendUse() {
        return intendUse;
    }

    public void setIntendUse(String intendUse) {
        this.intendUse = intendUse;
    }

    public String getStgCond() {
        return stgCond;
    }

    public void setStgCond(String stgCond) {
        this.stgCond = stgCond;
    }

    public String getAprvDept() {
        return aprvDept;
    }

    public void setAprvDept(String aprvDept) {
        this.aprvDept = aprvDept;
    }

    public String getAlteration() {
        return alteration;
    }

    public void setAlteration(String alteration) {
        this.alteration = alteration;
    }

    public String getDetlLink() {
        return detlLink;
    }

    public void setDetlLink(String detlLink) {
        this.detlLink = detlLink;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "TblMedicalDevicesEntity{" +
                "id=" + id +
                ", pripId='" + pripId + '\'' +
                ", regCertNum='" + regCertNum + '\'' +
                ", entName='" + entName + '\'' +
                ", dom='" + dom + '\'' +
                ", opLoc='" + opLoc + '\'' +
                ", agyName='" + agyName + '\'' +
                ", agyDom='" + agyDom + '\'' +
                ", prodName='" + prodName + '\'' +
                ", spec='" + spec + '\'' +
                ", struc='" + struc + '\'' +
                ", appScope='" + appScope + '\'' +
                ", othDesc='" + othDesc + '\'' +
                ", remark='" + remark + '\'' +
                ", aprvDate='" + aprvDate + '\'' +
                ", expDate='" + expDate + '\'' +
                ", prodStd='" + prodStd + '\'' +
                ", revDate='" + revDate + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", majorConst='" + majorConst + '\'' +
                ", intendUse='" + intendUse + '\'' +
                ", stgCond='" + stgCond + '\'' +
                ", aprvDept='" + aprvDept + '\'' +
                ", alteration='" + alteration + '\'' +
                ", detlLink='" + detlLink + '\'' +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
