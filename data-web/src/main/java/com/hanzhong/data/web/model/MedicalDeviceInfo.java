package com.hanzhong.data.web.model;

/**
 * 医疗器械
 *
 * @author yifei
 * @date 2019/4/14
 */
public class MedicalDeviceInfo {
    /**
     * 注册证编号
     */
    private String regCertNum;
    /**
     * 注册人名称
     */
    private String regName;
    /**
     * 注册人住所
     */
    private String dom;
    /**
     * 生产地址
     */
    private String prodAddress;
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
    private String structure;
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
    private String apprDate;
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
    private String apprDepart;
    /**
     * 变更情况
     */
    private String alteration;
    /**
     * 详情页链接
     */
    private String detailLink;

    public String getRegCertNum() {
        return regCertNum;
    }

    public void setRegCertNum(String regCertNum) {
        this.regCertNum = regCertNum;
    }

    public String getRegName() {
        return regName;
    }

    public void setRegName(String regName) {
        this.regName = regName;
    }

    public String getDom() {
        return dom;
    }

    public void setDom(String dom) {
        this.dom = dom;
    }

    public String getProdAddress() {
        return prodAddress;
    }

    public void setProdAddress(String prodAddress) {
        this.prodAddress = prodAddress;
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

    public String getStructure() {
        return structure;
    }

    public void setStructure(String structure) {
        this.structure = structure;
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

    public String getApprDate() {
        return apprDate;
    }

    public void setApprDate(String apprDate) {
        this.apprDate = apprDate;
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

    public String getApprDepart() {
        return apprDepart;
    }

    public void setApprDepart(String apprDepart) {
        this.apprDepart = apprDepart;
    }

    public String getAlteration() {
        return alteration;
    }

    public void setAlteration(String alteration) {
        this.alteration = alteration;
    }

    public String getDetailLink() {
        return detailLink;
    }

    public void setDetailLink(String detailLink) {
        this.detailLink = detailLink;
    }

    @Override
    public String toString() {
        return "MedicalDevicesInfo{" +
                "regCertNum='" + regCertNum + '\'' +
                ", regName='" + regName + '\'' +
                ", dom='" + dom + '\'' +
                ", prodAddress='" + prodAddress + '\'' +
                ", agyName='" + agyName + '\'' +
                ", agyDom='" + agyDom + '\'' +
                ", prodName='" + prodName + '\'' +
                ", spec='" + spec + '\'' +
                ", structure='" + structure + '\'' +
                ", appScope='" + appScope + '\'' +
                ", othDesc='" + othDesc + '\'' +
                ", remark='" + remark + '\'' +
                ", apprDate='" + apprDate + '\'' +
                ", expDate='" + expDate + '\'' +
                ", prodStd='" + prodStd + '\'' +
                ", revDate='" + revDate + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", majorConst='" + majorConst + '\'' +
                ", intendUse='" + intendUse + '\'' +
                ", stgCond='" + stgCond + '\'' +
                ", apprDepart='" + apprDepart + '\'' +
                ", alteration='" + alteration + '\'' +
                ", detailLink='" + detailLink + '\'' +
                '}';
    }
}
