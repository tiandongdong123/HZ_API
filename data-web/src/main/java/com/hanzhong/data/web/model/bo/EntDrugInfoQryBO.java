package com.hanzhong.data.web.model.bo;

/**
 * 医药药品信息查询参数
 *
 * @author yifei
 * @date 2019/3/27
 */
public class EntDrugInfoQryBO {
    /**
     * 主体身份代码
     */
    private String pripId;
    /**
     * 企业名称
     */
    private String entName;
    /**
     * 起始索引
     */
    private Integer startIndex;
    /**
     * 每页数量
     */
    private Integer pageSize;

    public String getPripId() {
        return pripId;
    }

    public void setPripId(String pripId) {
        this.pripId = pripId;
    }

    public String getEntName() {
        return entName;
    }

    public void setEntName(String entName) {
        this.entName = entName;
    }

    public Integer getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "EntDrugInfoQryBO{" +
                "pripId='" + pripId + '\'' +
                ", entName='" + entName + '\'' +
                ", startIndex=" + startIndex +
                ", pageSize=" + pageSize +
                '}';
    }
}
