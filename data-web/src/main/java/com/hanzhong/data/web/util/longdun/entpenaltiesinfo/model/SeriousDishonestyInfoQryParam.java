package com.hanzhong.data.web.util.longdun.entpenaltiesinfo.model;

/**
 * @author yifei
 * @date 2019/6/10
 */
public class SeriousDishonestyInfoQryParam {
    /**
     * 企业名称
     */
    private String entName;
    /**
     * 页码
     */
    private int pageNo;
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

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
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
                ", pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                '}';
    }
}
