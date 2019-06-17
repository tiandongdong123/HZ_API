package com.hanzhong.data.web.util.longdun.entpenaltiesinfo.model;

/**
 * @author yifei
 * @date 2019/6/9
 */
public class LawAdminInfo {
    /**
     * 行政处罚决定书文号
     */
    private String penDecNo;
    /**
     * 违法行为类型
     */
    private String illegActType;
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
    private String penDecIssDate;
    /**
     * 公示时间
     */
    private String pubDate;

    public String getPenDecNo() {
        return penDecNo;
    }

    public void setPenDecNo(String penDecNo) {
        this.penDecNo = penDecNo;
    }

    public String getIllegActType() {
        return illegActType;
    }

    public void setIllegActType(String illegActType) {
        this.illegActType = illegActType;
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
        return "LawAdminInfo{" +
                "penDecNo='" + penDecNo + '\'' +
                ", illegActType='" + illegActType + '\'' +
                ", penResult='" + penResult + '\'' +
                ", caseDep='" + caseDep + '\'' +
                ", penDecIssDate='" + penDecIssDate + '\'' +
                ", pubDate='" + pubDate + '\'' +
                '}';
    }
}
