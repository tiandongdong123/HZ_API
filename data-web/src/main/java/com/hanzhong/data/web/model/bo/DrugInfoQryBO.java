package com.hanzhong.data.web.model.bo;

/**
 * 医药药品信息查询参数
 *
 * @author yifei
 * @date 2019/3/27
 */
public class DrugInfoQryBO {
    /**
     * 批准文号
     */
    private String apprNum;
    /**
     * 起始索引
     */
    private Integer startIndex;
    /**
     * 每页数量
     */
    private Integer pageSize;

    public String getApprNum() {
        return apprNum;
    }

    public void setApprNum(String apprNum) {
        this.apprNum = apprNum;
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
        return "DrugInfoQryBO{" +
                "apprNum='" + apprNum + '\'' +
                ", startIndex=" + startIndex +
                ", pageSize=" + pageSize +
                '}';
    }
}
