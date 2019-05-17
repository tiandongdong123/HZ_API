package com.hanzhong.data.web.model.vo;

import java.io.Serializable;

/**
 * 分页信息
 *
 * @author yifei
 * @date 2019/3/27
 */
public class BasePageVO implements Serializable {
    private static final long serialVersionUID = -4963266899668807475L;
    /**
     * 总数
     */
    private Integer total;
    /**
     * 总页数
     */
    private Integer pageTotal;
    /**
     * 当前页码
     */
    private Integer currentPageNum;
    /**
     * 当前每页数量
     */
    private Integer currentPageSize;
    /**
     * 是否有更多
     */
    private Boolean noMore;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }

    public Integer getCurrentPageNum() {
        return currentPageNum;
    }

    public void setCurrentPageNum(Integer currentPageNum) {
        this.currentPageNum = currentPageNum;
    }

    public Integer getCurrentPageSize() {
        return currentPageSize;
    }

    public void setCurrentPageSize(Integer currentPageSize) {
        this.currentPageSize = currentPageSize;
    }

    public Boolean getNoMore() {
        return noMore;
    }

    public void setNoMore(Boolean noMore) {
        this.noMore = noMore;
    }

    @Override
    public String toString() {
        return "BasePageVO{" +
                "total=" + total +
                ", pageTotal=" + pageTotal +
                ", currentPageNum=" + currentPageNum +
                ", currentPageSize=" + currentPageSize +
                ", noMore=" + noMore +
                '}';
    }
}
