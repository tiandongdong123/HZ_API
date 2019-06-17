package com.hanzhong.data.web.model;

import java.util.Date;

/**
 * @author yifei
 * @date 2019/6/10
 */
public class EntSeriousDishonestyInfo {
    /**
     * 类别
     */
    private String type;
    /**
     * 列入严重违法失信企业名单（黑名单）原因
     */
    private String inReason;
    /**
     * 列入日期
     */
    private Date inDate;
    /**
     * 作出决定机关（列入）
     */
    private String inRegOrg;
    /**
     * 移出严重违法失信企业名单（黑名单）原因
     */
    private String outReason;
    /**
     * 移出日期
     */
    private Date outDate;
    /**
     * 作出决定机关（移出）
     */
    private String outRegOrg;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getInReason() {
        return inReason;
    }

    public void setInReason(String inReason) {
        this.inReason = inReason;
    }

    public Date getInDate() {
        return inDate;
    }

    public void setInDate(Date inDate) {
        this.inDate = inDate;
    }

    public String getInRegOrg() {
        return inRegOrg;
    }

    public void setInRegOrg(String inRegOrg) {
        this.inRegOrg = inRegOrg;
    }

    public String getOutReason() {
        return outReason;
    }

    public void setOutReason(String outReason) {
        this.outReason = outReason;
    }

    public Date getOutDate() {
        return outDate;
    }

    public void setOutDate(Date outDate) {
        this.outDate = outDate;
    }

    public String getOutRegOrg() {
        return outRegOrg;
    }

    public void setOutRegOrg(String outRegOrg) {
        this.outRegOrg = outRegOrg;
    }

    @Override
    public String toString() {
        return "EntSeriousDishonestyInfo{" +
                "type='" + type + '\'' +
                ", inReason='" + inReason + '\'' +
                ", inDate=" + inDate +
                ", inRegOrg='" + inRegOrg + '\'' +
                ", outReason='" + outReason + '\'' +
                ", outDate=" + outDate +
                ", outRegOrg='" + outRegOrg + '\'' +
                '}';
    }
}
