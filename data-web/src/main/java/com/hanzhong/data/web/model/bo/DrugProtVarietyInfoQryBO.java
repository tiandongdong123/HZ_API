package com.hanzhong.data.web.model.bo;

/**
 * 医药保护品种信息查询参数
 *
 * @author yifei
 * @date 2019/3/27
 */
public class DrugProtVarietyInfoQryBO {
    /**
     * 保护品种编号
     */
    private String varietyId;
    /**
     * 起始索引
     */
    private Integer startIndex;
    /**
     * 每页数量
     */
    private Integer pageSize;

    public String getVarietyId() {
        return varietyId;
    }

    public void setVarietyId(String varietyId) {
        this.varietyId = varietyId;
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
        return "DrugProtVarietyInfoQryBO{" +
                "varietyId='" + varietyId + '\'' +
                ", startIndex=" + startIndex +
                ", pageSize=" + pageSize +
                '}';
    }
}
