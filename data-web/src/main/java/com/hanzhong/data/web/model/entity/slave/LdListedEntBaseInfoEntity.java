package com.hanzhong.data.web.model.entity.slave;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author yifei
 * @date 2019/3/24
 */
public class LdListedEntBaseInfoEntity {
    /**
     * 证券代码
     */
    private String stockCode;
    /**
     * 股票简称
     */
    private String stockShortName;
    /**
     * 企业中文全称
     */
    private String cnName;
    /**
     * 企业中文简称
     */
    private String cnShortName;
    /**
     * 企业英文全称
     */
    private String enName;
    /**
     * 企业英文简称
     */
    private String enShortName;
    /**
     * 成立日期，格式：yyyy-MM-dd
     */
    private Date esDate;
    /**
     * 注册资金
     */
    private BigDecimal regCap;
    /**
     * 币种
     */
    private String currency;
    /**
     * 注册地址
     */
    private String regAddress;
    /**
     * 办公地址
     */
    private String officeAddress;
    /**
     * 公司网址
     */
    private String webSite;
    /**
     * 公司简介
     */
    private String comBrief;
    /**
     * 主营业务
     */
    private String priBusiness;
    /**
     * 员工人数
     */
    private Integer staffNum;
    /**
     * 信息发布日期，格式：yyyy-MM-dd
     */
    private Date declareDate;
    /**
     * 上市日期，格式：yyyy-MM-dd
     */
    private Date listDate;
    /**
     * 上市状态
     */
    private String listStatus;
    /**
     * 证券交易所
     */
    private String stockExchange;
    /**
     * 证券类别
     */
    private String stockType;
    /**
     * 退市日期
     */
    private Date delistingDate;
    /**
     * ISIN代码
     */
    private String isin;
    /**
     * 更新时间（10位时间戳）
     */
    private Long updateTime;

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public String getStockShortName() {
        return stockShortName;
    }

    public void setStockShortName(String stockShortName) {
        this.stockShortName = stockShortName;
    }

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }

    public String getCnShortName() {
        return cnShortName;
    }

    public void setCnShortName(String cnShortName) {
        this.cnShortName = cnShortName;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getEnShortName() {
        return enShortName;
    }

    public void setEnShortName(String enShortName) {
        this.enShortName = enShortName;
    }

    public Date getEsDate() {
        return esDate;
    }

    public void setEsDate(Date esDate) {
        this.esDate = esDate;
    }

    public BigDecimal getRegCap() {
        return regCap;
    }

    public void setRegCap(BigDecimal regCap) {
        this.regCap = regCap;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getRegAddress() {
        return regAddress;
    }

    public void setRegAddress(String regAddress) {
        this.regAddress = regAddress;
    }

    public String getOfficeAddress() {
        return officeAddress;
    }

    public void setOfficeAddress(String officeAddress) {
        this.officeAddress = officeAddress;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public String getComBrief() {
        return comBrief;
    }

    public void setComBrief(String comBrief) {
        this.comBrief = comBrief;
    }

    public String getPriBusiness() {
        return priBusiness;
    }

    public void setPriBusiness(String priBusiness) {
        this.priBusiness = priBusiness;
    }

    public Integer getStaffNum() {
        return staffNum;
    }

    public void setStaffNum(Integer staffNum) {
        this.staffNum = staffNum;
    }

    public Date getDeclareDate() {
        return declareDate;
    }

    public void setDeclareDate(Date declareDate) {
        this.declareDate = declareDate;
    }

    public Date getListDate() {
        return listDate;
    }

    public void setListDate(Date listDate) {
        this.listDate = listDate;
    }

    public String getListStatus() {
        return listStatus;
    }

    public void setListStatus(String listStatus) {
        this.listStatus = listStatus;
    }

    public String getStockExchange() {
        return stockExchange;
    }

    public void setStockExchange(String stockExchange) {
        this.stockExchange = stockExchange;
    }

    public String getStockType() {
        return stockType;
    }

    public void setStockType(String stockType) {
        this.stockType = stockType;
    }

    public Date getDelistingDate() {
        return delistingDate;
    }

    public void setDelistingDate(Date delistingDate) {
        this.delistingDate = delistingDate;
    }

    public String getIsin() {
        return isin;
    }

    public void setIsin(String isin) {
        this.isin = isin;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "LdListedEntBaseInfoEntity{" +
                "stockCode='" + stockCode + '\'' +
                ", stockShortName='" + stockShortName + '\'' +
                ", cnName='" + cnName + '\'' +
                ", cnShortName='" + cnShortName + '\'' +
                ", enName='" + enName + '\'' +
                ", enShortName='" + enShortName + '\'' +
                ", esDate='" + esDate + '\'' +
                ", regCap=" + regCap +
                ", currency='" + currency + '\'' +
                ", regAddress='" + regAddress + '\'' +
                ", officeAddress='" + officeAddress + '\'' +
                ", webSite='" + webSite + '\'' +
                ", comBrief='" + comBrief + '\'' +
                ", priBusiness='" + priBusiness + '\'' +
                ", staffNum=" + staffNum +
                ", declareDate='" + declareDate + '\'' +
                ", listDate='" + listDate + '\'' +
                ", listStatus='" + listStatus + '\'' +
                ", stockExchange='" + stockExchange + '\'' +
                ", stockType='" + stockType + '\'' +
                ", delistingDate='" + delistingDate + '\'' +
                ", isin='" + isin + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }
}
