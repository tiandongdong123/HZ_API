package com.hanzhong.api.web.util.business.longdun.model;

import com.hanzhong.api.web.util.business.longdun.constant.DataPackageActionTypeEnum;

/**
 *  
 *  @Description
 *  @Author   luqs   
 *  @Date 2019/1/10 15:08 
 *  @Version  V1.0   
 */
public class ChangeDataPackageListQryParam {
    /**
     * 数据包触发类型枚举
     */
    private DataPackageActionTypeEnum dataPackageActionTypeEnum;
    /**
     * 开始时间(yyyy-MM-dd HH:mm:ss)
     */
    private String startTime;
    /**
     * 结束时间(yyyy-MM-dd HH:mm:ss)
     */
    private String endTime;
    /**
     * 页码
     */
    private int pageNo;
    /**
     * 每页条数
     */
    private int pageSize;

    public DataPackageActionTypeEnum getDataPackageActionTypeEnum() {
        return dataPackageActionTypeEnum;
    }

    public void setDataPackageActionTypeEnum(DataPackageActionTypeEnum dataPackageActionTypeEnum) {
        this.dataPackageActionTypeEnum = dataPackageActionTypeEnum;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
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
        return "ChangeDataPackageListQryParam{" +
                "dataPackageActionTypeEnum=" + dataPackageActionTypeEnum +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                '}';
    }
}
