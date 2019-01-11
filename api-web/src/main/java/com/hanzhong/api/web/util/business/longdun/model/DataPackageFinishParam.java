package com.hanzhong.api.web.util.business.longdun.model;

/**
 *  
 *  @Description
 *  @Author   luqs   
 *  @Date 2019/1/10 15:31 
 *  @Version  V1.0   
 */
public class DataPackageFinishParam {
    /**
     * 数据包id
     */
    private String dataPackId;

    public String getDataPackId() {
        return dataPackId;
    }

    public void setDataPackId(String dataPackId) {
        this.dataPackId = dataPackId;
    }

    @Override
    public String toString() {
        return "DataPackageFinishParam{" +
                "dataPackId='" + dataPackId + '\'' +
                '}';
    }
}
