package com.hanzhong.data.web.model;

import com.hanzhong.data.web.constant.StandardQryFieldEnum;

/**
 * 标准信息查询参数
 *
 * @author yifei
 * @date 2019/3/30
 */
public class EntStandardInfoQryParam {
    /**
     * 企业名称
     */
    private String entName;
    /**
     * 统一社会信用代码
     */
    private String usCreditCode;
    /**
     * 标准号
     */
    private String standardNum;
    /**
     * 标准查询字段枚举
     */
    private StandardQryFieldEnum standardQryFieldEnum;
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

    public String getStandardNum() {
        return standardNum;
    }

    public void setStandardNum(String standardNum) {
        this.standardNum = standardNum;
    }

    public StandardQryFieldEnum getStandardQryFieldEnum() {
        return standardQryFieldEnum;
    }

    public void setStandardQryFieldEnum(StandardQryFieldEnum standardQryFieldEnum) {
        this.standardQryFieldEnum = standardQryFieldEnum;
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
        return "EntStandardInfoQryParam{" +
                "entName='" + entName + '\'' +
                ", usCreditCode='" + usCreditCode + '\'' +
                ", standardNum='" + standardNum + '\'' +
                ", standardQryFieldEnum=" + standardQryFieldEnum +
                ", pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                '}';
    }
}
