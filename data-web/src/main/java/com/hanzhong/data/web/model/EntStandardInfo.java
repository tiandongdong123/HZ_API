package com.hanzhong.data.web.model;

/**
 * 企业标准信息
 *
 * @author yifei
 * @date 2019/3/27
 */
public class EntStandardInfo {
    /**
     * 标准编号
     */
    private String standardNum;
    /**
     * 新标准号
     */
    private String newStandardNum;
    /**
     * 标准名称
     */
    private String standardName;
    /**
     * 标准类型
     */
    private String standardType;
    /**
     * 标准水平
     */
    private String standardLevel;
    /**
     * 标准状态
     */
    private String standardStatus;
    /**
     * 中文关键词
     */
    private String keywords;
    /**
     * 起草单位
     */
    private String draftUnit;
    /**
     * 发布日期，格式：yyyy-MM-dd
     */
    private String issueDate;
    /**
     * 实施日期，格式：yyyy-MM-dd
     */
    private String forceDate;
    /**
     * 废止日期
     */
    private String avoidDate;

    public String getStandardNum() {
        return standardNum;
    }

    public void setStandardNum(String standardNum) {
        this.standardNum = standardNum;
    }

    public String getNewStandardNum() {
        return newStandardNum;
    }

    public void setNewStandardNum(String newStandardNum) {
        this.newStandardNum = newStandardNum;
    }

    public String getStandardName() {
        return standardName;
    }

    public void setStandardName(String standardName) {
        this.standardName = standardName;
    }

    public String getStandardType() {
        return standardType;
    }

    public void setStandardType(String standardType) {
        this.standardType = standardType;
    }

    public String getStandardLevel() {
        return standardLevel;
    }

    public void setStandardLevel(String standardLevel) {
        this.standardLevel = standardLevel;
    }

    public String getStandardStatus() {
        return standardStatus;
    }

    public void setStandardStatus(String standardStatus) {
        this.standardStatus = standardStatus;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getDraftUnit() {
        return draftUnit;
    }

    public void setDraftUnit(String draftUnit) {
        this.draftUnit = draftUnit;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public String getForceDate() {
        return forceDate;
    }

    public void setForceDate(String forceDate) {
        this.forceDate = forceDate;
    }

    public String getAvoidDate() {
        return avoidDate;
    }

    public void setAvoidDate(String avoidDate) {
        this.avoidDate = avoidDate;
    }

    @Override
    public String toString() {
        return "EntStandardInfo{" +
                "standardNum='" + standardNum + '\'' +
                ", newStandardNum='" + newStandardNum + '\'' +
                ", standardName='" + standardName + '\'' +
                ", standardType='" + standardType + '\'' +
                ", standardLevel='" + standardLevel + '\'' +
                ", standardStatus='" + standardStatus + '\'' +
                ", keywords='" + keywords + '\'' +
                ", draftUnit='" + draftUnit + '\'' +
                ", issueDate='" + issueDate + '\'' +
                ", forceDate='" + forceDate + '\'' +
                ", avoidDate='" + avoidDate + '\'' +
                '}';
    }
}
