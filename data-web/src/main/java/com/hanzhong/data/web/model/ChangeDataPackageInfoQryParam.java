package com.hanzhong.data.web.model;

import com.hanzhong.data.web.constant.DataPackageStatusEnum;

/**
 *  
 *  @Description 更新数据包信息查询参数
 *  @Author   luqs   
 *  @Date 2019/3/11 18:30 
 *  @Version  V1.0   
 */
public class ChangeDataPackageInfoQryParam {

    /**
     * 数据包回馈状态
     */
    private DataPackageStatusEnum statusEnum;
    /**
     * 起始时间，格式：yyyy-MM-dd HH:mm:ss
     */
    private String startTime;
    /**
     * 结束时间，格式：yyyy-MM-dd HH:mm:ss
     */
    private String endTime;
    /**
     * 页码
     */
    private Integer pageNum;
    /**
     * 每页数量
     */
    private Integer pageSize;

    public DataPackageStatusEnum getStatusEnum() {
        return statusEnum;
    }

    public void setStatusEnum(DataPackageStatusEnum statusEnum) {
        this.statusEnum = statusEnum;
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
        return "ChangeDataPackageInfoQryParam{" +
                "statusEnum=" + statusEnum +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                '}';
    }
}
