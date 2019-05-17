package com.hanzhong.data.web.model;

import com.hanzhong.data.web.constant.DrugProtVarietyQryFieldEnum;

/**
 * 医药保护品种信息查询参数
 *
 * @author yifei
 * @date 2019/3/30
 */
public class DrugProtVarietyInfoQryParam {
    /**
     * 企业名称
     */
    private String entName;
    /**
     * 统一社会信用代码
     */
    private String usCreditCode;
    /**
     * 保护品种编号
     */
    private String varietyId;
    /**
     * 医药保护品种查询字段枚举
     */
    private DrugProtVarietyQryFieldEnum drugProtVarietyQryFieldEnum;
    /**
     * 页码
     */
    private Integer pageNum;
    /**
     * 每页数量
     */
    private Integer pageSize;

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

    public String getVarietyId() {
        return varietyId;
    }

    public void setVarietyId(String varietyId) {
        this.varietyId = varietyId;
    }

    public DrugProtVarietyQryFieldEnum getDrugProtVarietyQryFieldEnum() {
        return drugProtVarietyQryFieldEnum;
    }

    public void setDrugProtVarietyQryFieldEnum(DrugProtVarietyQryFieldEnum drugProtVarietyQryFieldEnum) {
        this.drugProtVarietyQryFieldEnum = drugProtVarietyQryFieldEnum;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "DrugProtVarietyInfoQryParam{" +
                "entName='" + entName + '\'' +
                ", usCreditCode='" + usCreditCode + '\'' +
                ", varietyId='" + varietyId + '\'' +
                ", drugProtVarietyQryFieldEnum=" + drugProtVarietyQryFieldEnum +
                ", pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                '}';
    }
}
