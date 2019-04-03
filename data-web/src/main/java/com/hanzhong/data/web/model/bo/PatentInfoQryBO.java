package com.hanzhong.data.web.model.bo;

/**
 * 专利信息查询参数
 *
 * @author yifei
 * @date 2019/3/27
 */
public class PatentInfoQryBO {
    /**
     * 申请号
     */
    private String patentId;
    /**
     * 起始索引
     */
    private Integer startIndex;
    /**
     * 每页数量
     */
    private Integer pageSize;

    public String getPatentId() {
        return patentId;
    }

    public void setPatentId(String patentId) {
        this.patentId = patentId;
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
        return "PatentInfoQryBO{" +
                "patentId='" + patentId + '\'' +
                ", startIndex=" + startIndex +
                ", pageSize=" + pageSize +
                '}';
    }
}
