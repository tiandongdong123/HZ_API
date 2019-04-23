package com.hanzhong.data.web.model.entity.master;

import java.util.Date;

/**
 * 医药保护品种
 *
 * @author yifei
 * @date 2019/4/14
 */
public class TblDrugPrtcVarietyEntity {
    /**
     * id
     */
    private Long id;
    /**
     * 主体身份代码
     */
    private String pripId;
    /**
     * 保护品种编号
     */
    private String varietyId;
    /**
     * 药品名称
     */
    private String drugName;
    /**
     * 公告号
     */
    private String pubNum;
    /**
     * 药品批准文号
     */
    private String aprvNum;
    /**
     * 保护级别
     */
    private Integer level;
    /**
     * 规格
     */
    private String spec;
    /**
     * 保护起始日
     */
    private Date startDate;
    /**
     * 保护终止日
     */
    private Date endDate;
    /**
     * 生产企业
     */
    private String mfrsEnt;
    /**
     * 剂型
     */
    private String dosageForm;
    /**
     * 保护期限
     */
    private String limitTime;

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

    public String getVarietyId() {
        return varietyId;
    }

    public void setVarietyId(String varietyId) {
        this.varietyId = varietyId;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public String getPubNum() {
        return pubNum;
    }

    public void setPubNum(String pubNum) {
        this.pubNum = pubNum;
    }

    public String getAprvNum() {
        return aprvNum;
    }

    public void setAprvNum(String aprvNum) {
        this.aprvNum = aprvNum;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getMfrsEnt() {
        return mfrsEnt;
    }

    public void setMfrsEnt(String mfrsEnt) {
        this.mfrsEnt = mfrsEnt;
    }

    public String getDosageForm() {
        return dosageForm;
    }

    public void setDosageForm(String dosageForm) {
        this.dosageForm = dosageForm;
    }

    public String getLimitTime() {
        return limitTime;
    }

    public void setLimitTime(String limitTime) {
        this.limitTime = limitTime;
    }

    @Override
    public String toString() {
        return "TblDrugPrtcVarietyEntity{" +
                "id=" + id +
                ", pripId='" + pripId + '\'' +
                ", varietyId='" + varietyId + '\'' +
                ", drugName='" + drugName + '\'' +
                ", pubNum='" + pubNum + '\'' +
                ", aprvNum='" + aprvNum + '\'' +
                ", level=" + level +
                ", spec='" + spec + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", mfrsEnt='" + mfrsEnt + '\'' +
                ", dosageForm='" + dosageForm + '\'' +
                ", limitTime='" + limitTime + '\'' +
                '}';
    }
}
