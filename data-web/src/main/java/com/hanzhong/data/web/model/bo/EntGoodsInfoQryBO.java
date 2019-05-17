package com.hanzhong.data.web.model.bo;

/**
 * @author yifei
 * @date 2019/3/24
 */
public class EntGoodsInfoQryBO {
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
        return "EntGoodsInfoQryBO{" +
                "entName='" + entName + '\'' +
                ", startIndex=" + startIndex +
                ", pageSize=" + pageSize +
                '}';
    }
}
