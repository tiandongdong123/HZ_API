package com.hanzhong.data.web.model.vo;

import java.io.Serializable;

/**
 * @author yifei
 * @date 2019/6/9
 */
public class EntOptExceptionInfoVO implements Serializable {
    private static final long serialVersionUID = -4963266899668807475L;
    /**
     * 列入经营异常名录原因
     */
    private String inReason;
    /**
     * 列入日期，格式：yyyy-MM-dd
     */
    private String inDate;
    /**
     * 作出决定机关（列入）
     */
    private String inRegOrg;
    /**
     * 移出经营异常名录原因
     */
    private String outReason;
    /**
     * 移出日期，格式：yyyy-MM-dd
     */
    private String outDate;
    /**
     * 作出决定机关（移出）
     */
    private String outRegOrg;
    /**
     * 是否移除
     */
    private Boolean moveFlag;

    public String getInReason() {
        return inReason;
    }

    public void setInReason(String inReason) {
        this.inReason = inReason;
    }

    public String getInDate() {
        return inDate;
    }

    public void setInDate(String inDate) {
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

    public String getOutDate() {
        return outDate;
    }

    public void setOutDate(String outDate) {
        this.outDate = outDate;
    }

    public String getOutRegOrg() {
        return outRegOrg;
    }

    public void setOutRegOrg(String outRegOrg) {
        this.outRegOrg = outRegOrg;
    }

    public Boolean getMoveFlag() {
        return moveFlag;
    }

    public void setMoveFlag(Boolean moveFlag) {
        this.moveFlag = moveFlag;
    }

    @Override
    public String toString() {
        return "EntOptExceptionInfoVO{" +
                "inReason='" + inReason + '\'' +
                ", inDate='" + inDate + '\'' +
                ", inRegOrg='" + inRegOrg + '\'' +
                ", outReason='" + outReason + '\'' +
                ", outDate='" + outDate + '\'' +
                ", outRegOrg='" + outRegOrg + '\'' +
                ", moveFlag=" + moveFlag +
                '}';
    }
}
