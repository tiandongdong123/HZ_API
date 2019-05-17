package com.hanzhong.data.web.model;

import com.hanzhong.data.web.constant.PatentQryFieldEnum;

/**
 * 专利信息查询参数
 *
 * @author yifei
 * @date 2019/3/30
 */
public class EntPatentInfoQryParam {
    /**
     * 企业名称
     */
    private String entName;
    /**
     * 统一社会信用代码
     */
    private String usCreditCode;
    /**
     * 申请号
     */
    private String patentId;
    /**
     * 专利查询字段枚举
     */
    private PatentQryFieldEnum patentQryFieldEnum;
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

    public String getPatentId() {
        return patentId;
    }

    public void setPatentId(String patentId) {
        this.patentId = patentId;
    }

    public PatentQryFieldEnum getPatentQryFieldEnum() {
        return patentQryFieldEnum;
    }

    public void setPatentQryFieldEnum(PatentQryFieldEnum patentQryFieldEnum) {
        this.patentQryFieldEnum = patentQryFieldEnum;
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
        return "EntPatentInfoQryParam{" +
                "entName='" + entName + '\'' +
                ", usCreditCode='" + usCreditCode + '\'' +
                ", patentId='" + patentId + '\'' +
                ", patentQryFieldEnum=" + patentQryFieldEnum +
                ", pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                '}';
    }
}
