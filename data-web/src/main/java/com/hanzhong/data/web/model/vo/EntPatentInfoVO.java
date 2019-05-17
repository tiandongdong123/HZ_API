package com.hanzhong.data.web.model.vo;

import java.io.Serializable;

/**
 * 企业专利信息
 *
 * @author yifei
 * @date 2019/3/27
 */
public class EntPatentInfoVO implements Serializable {
    private static final long serialVersionUID = -4963266899668807475L;
    /**
     * 申请号
     */
    private String appNum;
    /**
     * 申请日，格式：yyyy-MM-dd
     */
    private String appDate;
    /**
     * 申请（专利权）人
     */
    private String appName;
    /**
     * 专利号
     */
    private String patentNum;
    /**
     * 专利名
     */
    private String patentTitle;
    /**
     * 专利类型
     */
    private String patentType;
    /**
     * 摘要
     */
    private String summary;
    /**
     * 公开（公告）号
     */
    private String pubNum;
    /**
     * 公开（公告）日，格式：yyyy-MM-dd
     */
    private String pubDate;
    /**
     * 颁证日，格式：yyyy-MM-dd
     */
    private String awardDate;
    /**
     * PCT公开
     */
    private String pctPub;
    /**
     * PCT申请
     */
    private String pctApp;
    /**
     * PCT信息
     */
    private String pctInfo;

    public String getAppNum() {
        return appNum;
    }

    public void setAppNum(String appNum) {
        this.appNum = appNum;
    }

    public String getAppDate() {
        return appDate;
    }

    public void setAppDate(String appDate) {
        this.appDate = appDate;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getPatentNum() {
        return patentNum;
    }

    public void setPatentNum(String patentNum) {
        this.patentNum = patentNum;
    }

    public String getPatentTitle() {
        return patentTitle;
    }

    public void setPatentTitle(String patentTitle) {
        this.patentTitle = patentTitle;
    }

    public String getPatentType() {
        return patentType;
    }

    public void setPatentType(String patentType) {
        this.patentType = patentType;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getPubNum() {
        return pubNum;
    }

    public void setPubNum(String pubNum) {
        this.pubNum = pubNum;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getAwardDate() {
        return awardDate;
    }

    public void setAwardDate(String awardDate) {
        this.awardDate = awardDate;
    }

    public String getPctPub() {
        return pctPub;
    }

    public void setPctPub(String pctPub) {
        this.pctPub = pctPub;
    }

    public String getPctApp() {
        return pctApp;
    }

    public void setPctApp(String pctApp) {
        this.pctApp = pctApp;
    }

    public String getPctInfo() {
        return pctInfo;
    }

    public void setPctInfo(String pctInfo) {
        this.pctInfo = pctInfo;
    }

    @Override
    public String toString() {
        return "EntPatentInfoVO{" +
                "appNum='" + appNum + '\'' +
                ", appDate='" + appDate + '\'' +
                ", appName='" + appName + '\'' +
                ", patentNum='" + patentNum + '\'' +
                ", patentTitle='" + patentTitle + '\'' +
                ", patentType='" + patentType + '\'' +
                ", summary='" + summary + '\'' +
                ", pubNum='" + pubNum + '\'' +
                ", pubDate='" + pubDate + '\'' +
                ", awardDate='" + awardDate + '\'' +
                ", pctPub='" + pctPub + '\'' +
                ", pctApp='" + pctApp + '\'' +
                ", pctInfo='" + pctInfo + '\'' +
                '}';
    }
}
