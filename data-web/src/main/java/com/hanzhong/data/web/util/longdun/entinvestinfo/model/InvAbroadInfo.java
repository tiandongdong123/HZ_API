package com.hanzhong.data.web.util.longdun.entinvestinfo.model;

import java.math.BigDecimal;

/**
 * @author yifei
 * @date 2019/3/24
 */
public class InvAbroadInfo {
    /**
     * 企业名称
     */
    private String entName;
    /**
     * 统一社会信用代码
     */
    private String usCreditCode;
    /**
     * 成立日期，格式：yyyy-MM-dd
     */
    private String esDate;
    /**
     * 企业状态
     */
    private String entStatus;
    /**
     * 注册资金
     */
    private BigDecimal regCap;
    /**
     * 认缴出资额
     */
    private BigDecimal subConAmount;
    /**
     * 出资币种
     */
    private String conCur;
    /**
     * 出资比例
     */
    private BigDecimal conRatIo;
    /**
     * 出资时间，格式：yyyy-MM-dd
     */
    private String conDate;

    public String getEntName() {
        return entName;
    }

    public void setEntName(String entName) {
        this.entName = entName;
    }

    public String getUsCreditCode() {
        return usCreditCode;
    }

    public void setUsCreditCode(String usCreditCode) {
        this.usCreditCode = usCreditCode;
    }

    public String getEsDate() {
        return esDate;
    }

    public void setEsDate(String esDate) {
        this.esDate = esDate;
    }

    public String getEntStatus() {
        return entStatus;
    }

    public void setEntStatus(String entStatus) {
        this.entStatus = entStatus;
    }

    public BigDecimal getRegCap() {
        return regCap;
    }

    public void setRegCap(BigDecimal regCap) {
        this.regCap = regCap;
    }

    public BigDecimal getSubConAmount() {
        return subConAmount;
    }

    public void setSubConAmount(BigDecimal subConAmount) {
        this.subConAmount = subConAmount;
    }

    public String getConCur() {
        return conCur;
    }

    public void setConCur(String conCur) {
        this.conCur = conCur;
    }

    public BigDecimal getConRatIo() {
        return conRatIo;
    }

    public void setConRatIo(BigDecimal conRatIo) {
        this.conRatIo = conRatIo;
    }

    public String getConDate() {
        return conDate;
    }

    public void setConDate(String conDate) {
        this.conDate = conDate;
    }

    @Override
    public String toString() {
        return "InvAbroadInfo{" +
                "entName='" + entName + '\'' +
                ", usCreditCode='" + usCreditCode + '\'' +
                ", esDate='" + esDate + '\'' +
                ", entStatus='" + entStatus + '\'' +
                ", regCap=" + regCap +
                ", subConAmount=" + subConAmount +
                ", conCur='" + conCur + '\'' +
                ", conRatIo=" + conRatIo +
                ", conDate='" + conDate + '\'' +
                '}';
    }
}
