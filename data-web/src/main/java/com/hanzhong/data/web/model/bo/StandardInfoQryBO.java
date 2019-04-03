package com.hanzhong.data.web.model.bo;

/**
 * 标准信息查询参数
 *
 * @author yifei
 * @date 2019/3/27
 */
public class StandardInfoQryBO {
    /**
     * 标准号
     */
    private String standardNum;
    /**
     * 起始索引
     */
    private Integer startIndex;
    /**
     * 每页数量
     */
    private Integer pageSize;

    public String getStandardNum() {
        return standardNum;
    }

    public void setStandardNum(String standardNum) {
        this.standardNum = standardNum;
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
        return "StandardInfoQryBO{" +
                "standardNum='" + standardNum + '\'' +
                ", startIndex=" + startIndex +
                ", pageSize=" + pageSize +
                '}';
    }
}
