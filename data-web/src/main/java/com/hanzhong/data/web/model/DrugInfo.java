package com.hanzhong.data.web.model;

/**
 * 医药药品
 *
 * @author yifei
 * @date 2019/4/13
 */
public class DrugInfo {
    /**
     * 批准文号
     */
    private String apprNum;
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
    private String prodUnit;
    /**
     * 生产地址
     */
    private String prodAddress;
    /**
     * 产品类别
     */
    private String prodCategory;
    /**
     * 批准日期
     */
    private String apprDate;
    /**
     * 原批准文号
     */
    private String origApprNum;
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
    private String detailLink;

    public String getApprNum() {
        return apprNum;
    }

    public void setApprNum(String apprNum) {
        this.apprNum = apprNum;
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

    public String getProdUnit() {
        return prodUnit;
    }

    public void setProdUnit(String prodUnit) {
        this.prodUnit = prodUnit;
    }

    public String getProdAddress() {
        return prodAddress;
    }

    public void setProdAddress(String prodAddress) {
        this.prodAddress = prodAddress;
    }

    public String getProdCategory() {
        return prodCategory;
    }

    public void setProdCategory(String prodCategory) {
        this.prodCategory = prodCategory;
    }

    public String getApprDate() {
        return apprDate;
    }

    public void setApprDate(String apprDate) {
        this.apprDate = apprDate;
    }

    public String getOrigApprNum() {
        return origApprNum;
    }

    public void setOrigApprNum(String origApprNum) {
        this.origApprNum = origApprNum;
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

    public String getDetailLink() {
        return detailLink;
    }

    public void setDetailLink(String detailLink) {
        this.detailLink = detailLink;
    }

    @Override
    public String toString() {
        return "DrugInfo{" +
                "apprNum='" + apprNum + '\'' +
                ", prodName='" + prodName + '\'' +
                ", engName='" + engName + '\'' +
                ", dosageForm='" + dosageForm + '\'' +
                ", spec='" + spec + '\'' +
                ", prodUnit='" + prodUnit + '\'' +
                ", prodAddress='" + prodAddress + '\'' +
                ", prodCategory='" + prodCategory + '\'' +
                ", apprDate='" + apprDate + '\'' +
                ", origApprNum='" + origApprNum + '\'' +
                ", stdCode='" + stdCode + '\'' +
                ", stdDesc='" + stdDesc + '\'' +
                ", detailLink='" + detailLink + '\'' +
                '}';
    }
}
