package com.hanzhong.data.web.model;

import com.hanzhong.data.web.constant.DrugQryFieldEnum;

/**
 * 医药药品信息查询参数
 *
 * @author yifei
 * @date 2019/3/30
 */
public class DrugInfoQryParam {
    /**
     * 企业名称
     */
    private String entName;
    /**
     * 统一社会信用代码
     */
    private String usCreditCode;
    /**
     * 批准文号
     */
    private String apprNUm;
    /**
     * 医药药品查询字段枚举
     */
    private DrugQryFieldEnum drugQryFieldEnum;
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

    public String getApprNUm() {
        return apprNUm;
    }

    public void setApprNUm(String apprNUm) {
        this.apprNUm = apprNUm;
    }

    public DrugQryFieldEnum getDrugQryFieldEnum() {
        return drugQryFieldEnum;
    }

    public void setDrugQryFieldEnum(DrugQryFieldEnum drugQryFieldEnum) {
        this.drugQryFieldEnum = drugQryFieldEnum;
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
        return "DrugInfoQryParam{" +
                "entName='" + entName + '\'' +
                ", usCreditCode='" + usCreditCode + '\'' +
                ", apprNUm='" + apprNUm + '\'' +
                ", drugQryFieldEnum=" + drugQryFieldEnum +
                ", pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                '}';
    }
}
