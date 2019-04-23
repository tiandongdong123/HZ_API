package com.hanzhong.data.web.model;

/**
 * 医药保护品种
 *
 * @author yifei
 * @date 2019/4/14
 */
public class DrugProtVarietyInfo {
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
    private String apprNum;
    /**
     * 保护级别
     */
    private Integer level;
    /**
     * 规格
     */
    private String spec;
    /**
     * 保护起始日，格式：yyyy-MM-dd
     */
    private String startDate;
    /**
     * 保护终止日，格式：yyyy-MM-dd
     */
    private String endDate;
    /**
     * 生产企业
     */
    private String manuEnt;
    /**
     * 剂型
     */
    private String dosageForm;
    /**
     * 保护期限
     */
    private String protDuration;

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

    public String getApprNum() {
        return apprNum;
    }

    public void setApprNum(String apprNum) {
        this.apprNum = apprNum;
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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getManuEnt() {
        return manuEnt;
    }

    public void setManuEnt(String manuEnt) {
        this.manuEnt = manuEnt;
    }

    public String getDosageForm() {
        return dosageForm;
    }

    public void setDosageForm(String dosageForm) {
        this.dosageForm = dosageForm;
    }

    public String getProtDuration() {
        return protDuration;
    }

    public void setProtDuration(String protDuration) {
        this.protDuration = protDuration;
    }

    @Override
    public String toString() {
        return "DrugProtVarietyInfo{" +
                "varietyId='" + varietyId + '\'' +
                ", drugName='" + drugName + '\'' +
                ", pubNum='" + pubNum + '\'' +
                ", apprNum='" + apprNum + '\'' +
                ", level=" + level +
                ", spec='" + spec + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", manuEnt='" + manuEnt + '\'' +
                ", dosageForm='" + dosageForm + '\'' +
                ", protDuration='" + protDuration + '\'' +
                '}';
    }
}
