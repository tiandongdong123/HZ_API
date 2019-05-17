package com.hanzhong.data.web.model.bo;

/**
 * 医疗器械信息查询参数
 *
 * @author yifei
 * @date 2019/3/27
 */
public class MedicalDeviceInfoQryBO {
    /**
     * 注册证编号
     */
    private String regCertNum;
    /**
     * 起始索引
     */
    private Integer startIndex;
    /**
     * 每页数量
     */
    private Integer pageSize;

    public String getRegCertNum() {
        return regCertNum;
    }

    public void setRegCertNum(String regCertNum) {
        this.regCertNum = regCertNum;
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
        return "MedicalDeviceInfoQryBO{" +
                "regCertNum='" + regCertNum + '\'' +
                ", startIndex=" + startIndex +
                ", pageSize=" + pageSize +
                '}';
    }
}
