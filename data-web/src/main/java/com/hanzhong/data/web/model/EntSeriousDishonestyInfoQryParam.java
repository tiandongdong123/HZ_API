package com.hanzhong.data.web.model;

/**
 * @author yifei
 * @date 2019/6/10
 */
public class EntSeriousDishonestyInfoQryParam {
    /**
     * 企业名称
     */
    private String entName;
    /**
     * 页码
     */
    private int pageNum;
    /**
     * 每页条数
     */
    private int pageSize;

    public String getEntName() {
        return entName;
    }

    public void setEntName(String entName) {
        this.entName = entName;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "SeriousDishonestyInfoQryParam{" +
                "entName='" + entName + '\'' +
                ", pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                '}';
    }
}
