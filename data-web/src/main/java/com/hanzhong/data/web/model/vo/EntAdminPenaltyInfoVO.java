package com.hanzhong.data.web.model.vo;

import java.io.Serializable;

/**
 * @author yifei
 * @date 2019/6/9
 */
public class EntAdminPenaltyInfoVO implements Serializable {
    private static final long serialVersionUID = -4963266899668807475L;
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
     * 处罚决定日期，格式：yyyy-MM-dd
     */
    private String penDecIssDate;
    /**
     * 公示时间，格式：yyyy-MM-dd
     */
    private String pubDate;

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

    public String getPenDecIssDate() {
        return penDecIssDate;
    }

    public void setPenDecIssDate(String penDecIssDate) {
        this.penDecIssDate = penDecIssDate;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    @Override
    public String toString() {
        return "EntAdminPenaltyInfoVO{" +
                "penDecNo='" + penDecNo + '\'' +
                ", illegalActType='" + illegalActType + '\'' +
                ", penResult='" + penResult + '\'' +
                ", caseDep='" + caseDep + '\'' +
                ", penDecIssDate='" + penDecIssDate + '\'' +
                ", pubDate='" + pubDate + '\'' +
                '}';
    }
}
