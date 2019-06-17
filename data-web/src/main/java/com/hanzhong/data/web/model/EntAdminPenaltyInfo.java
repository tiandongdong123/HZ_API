package com.hanzhong.data.web.model;

import java.util.Date;

/**
 * @author yifei
 * @date 2019/6/9
 */
public class EntAdminPenaltyInfo {
    /**
     * 行政处罚决定书文号
     */
    private String penDecNo;
    /**
     * 违法行为类型
     */
    private String illegalActType;
    /**
     * 行政处罚内容
     */
    private String penResult;
    /**
     * 决定机关名称
     */
    private String caseDep;
    /**
     * 处罚决定日期
     */
    private Date penDecIssDate;
    /**
     * 公示时间
     */
    private Date pubDate;

    public String getPenDecNo() {
        return penDecNo;
    }

    public void setPenDecNo(String penDecNo) {
        this.penDecNo = penDecNo;
    }

    public String getIllegalActType() {
        return illegalActType;
    }

    public void setIllegalActType(String illegalActType) {
        this.illegalActType = illegalActType;
    }

    public String getPenResult() {
        return penResult;
    }

    public void setPenResult(String penResult) {
        this.penResult = penResult;
    }

    public String getCaseDep() {
        return caseDep;
    }

    public void setCaseDep(String caseDep) {
        this.caseDep = caseDep;
    }

    public Date getPenDecIssDate() {
        return penDecIssDate;
    }

    public void setPenDecIssDate(Date penDecIssDate) {
        this.penDecIssDate = penDecIssDate;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    @Override
    public String toString() {
        return "EntAdminPenaltyInfo{" +
                "penDecNo='" + penDecNo + '\'' +
                ", illegalActType='" + illegalActType + '\'' +
                ", penResult='" + penResult + '\'' +
                ", caseDep='" + caseDep + '\'' +
                ", penDecIssDate=" + penDecIssDate +
                ", pubDate=" + pubDate +
                '}';
    }
}
