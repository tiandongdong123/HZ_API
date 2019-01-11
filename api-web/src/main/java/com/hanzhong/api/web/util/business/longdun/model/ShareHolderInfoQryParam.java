package com.hanzhong.api.web.util.business.longdun.model;

/**
 *  
 *  @Description
 *  @Author   luqs   
 *  @Date 2019/1/10 15:02 
 *  @Version  V1.0   
 */
public class ShareHolderInfoQryParam {
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
        return "ShareHolderInfoQryParam{" +
                "entName='" + entName + '\'' +
                ", pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                '}';
    }
}
