package com.hanzhong.data.web.model.entity.master;

import java.util.Date;

/**
 * 医药药品
 *
 * @author yifei
 * @date 2019/4/13
 */
public class TblDrugEntity {
    /**
     * id
     */
    private Long id;
    /**
     * 主体身份代码
     */
    private String pripId;
    /**
     * 批准文号
     */
    private String aprvNum;
    /**
     * 产品名称
     */
    private String prodName;
    /**
     * 英文名称
     */
    private String engName;
    /**
     * 剂型
     */
    private String dosageForm;
    /**
     * 规格
     */
    private String spec;
    /**
     * 生产单位
     */
    private String entName;
    /**
     * 生产地址
     */
    private String opLoc;
    /**
     * 产品类别
     */
    private String prodCatg;
    /**
     * 批准日期
     */
    private String aprvDate;
    /**
     * 原批准文号
     */
    private String origAprvNum;
    /**
     * 药品本位码
     */
    private String stdCode;
    /**
     * 药品本位码备注
     */
    private String stdDesc;
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

    public String getAprvNum() {
        return aprvNum;
    }

    public void setAprvNum(String aprvNum) {
        this.aprvNum = aprvNum;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getEngName() {
        return engName;
    }

    public void setEngName(String engName) {
        this.engName = engName;
    }

    public String getDosageForm() {
        return dosageForm;
    }

    public void setDosageForm(String dosageForm) {
        this.dosageForm = dosageForm;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getEntName() {
        return entName;
    }

    public void setEntName(String entName) {
        this.entName = entName;
    }

    public String getOpLoc() {
        return opLoc;
    }

    public void setOpLoc(String opLoc) {
        this.opLoc = opLoc;
    }

    public String getProdCatg() {
        return prodCatg;
    }

    public void setProdCatg(String prodCatg) {
        this.prodCatg = prodCatg;
    }

    public String getAprvDate() {
        return aprvDate;
    }

    public void setAprvDate(String aprvDate) {
        this.aprvDate = aprvDate;
    }

    public String getOrigAprvNum() {
        return origAprvNum;
    }

    public void setOrigAprvNum(String origAprvNum) {
        this.origAprvNum = origAprvNum;
    }

    public String getStdCode() {
        return stdCode;
    }

    public void setStdCode(String stdCode) {
        this.stdCode = stdCode;
    }

    public String getStdDesc() {
        return stdDesc;
    }

    public void setStdDesc(String stdDesc) {
        this.stdDesc = stdDesc;
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
        return "TblDrugEntity{" +
                "id=" + id +
                ", pripId='" + pripId + '\'' +
                ", aprvNum='" + aprvNum + '\'' +
                ", prodName='" + prodName + '\'' +
                ", engName='" + engName + '\'' +
                ", dosageForm='" + dosageForm + '\'' +
                ", spec='" + spec + '\'' +
                ", entName='" + entName + '\'' +
                ", opLoc='" + opLoc + '\'' +
                ", prodCatg='" + prodCatg + '\'' +
                ", aprvDate='" + aprvDate + '\'' +
                ", origAprvNum='" + origAprvNum + '\'' +
                ", stdCode='" + stdCode + '\'' +
                ", stdDesc='" + stdDesc + '\'' +
                ", detlLink='" + detlLink + '\'' +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
