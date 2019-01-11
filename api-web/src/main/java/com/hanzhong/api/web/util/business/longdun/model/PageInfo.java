package com.hanzhong.api.web.util.business.longdun.model;

/**
 *  
 *  @Description
 *  @Author   luqs   
 *  @Date 2019/1/10 19:54 
 *  @Version  V1.0   
 */
public class PageInfo {
    /**
     * 总数
     */
    private int totalConut;
    /**
     * 总页数
     */
    private int totalPage;
    /**
     * 当前页码
     */
    private int currentPage;

    public int getTotalConut() {
        return totalConut;
    }

    public void setTotalConut(int totalConut) {
        this.totalConut = totalConut;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    @Override
    public String toString() {
        return "PageInfo{" +
                "totalConut=" + totalConut +
                ", totalPage=" + totalPage +
                ", currentPage=" + currentPage +
                '}';
    }
}
