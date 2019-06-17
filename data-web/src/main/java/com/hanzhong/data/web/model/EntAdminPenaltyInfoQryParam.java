package com.hanzhong.data.web.model;

/**
 * 企业商品信息查询参数
 *
 * @author yifei
 * @date 2019/3/21
 */
public class EntAdminPenaltyInfoQryParam {
    /**
     * 企业名称
     */
    private String entName;
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
        return "EntGoodsInfoQryParam{" +
                "entName='" + entName + '\'' +
                ", pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                '}';
    }
}
