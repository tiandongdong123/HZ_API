package com.hanzhong.data.web.model;

/**
 * 分页信息
 *
 * @author yifei
 * @date 2019/3/27
 */
public class BasePage {
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
        return "BasePage{" +
                "total=" + total +
                ", pageTotal=" + pageTotal +
                ", currentPageNum=" + currentPageNum +
                ", currentPageSize=" + currentPageSize +
                ", noMore=" + noMore +
                '}';
    }
}
