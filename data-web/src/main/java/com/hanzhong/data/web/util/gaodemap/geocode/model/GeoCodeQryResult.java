package com.hanzhong.data.web.util.gaodemap.geocode.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 *    
 *  @Description 地理编码查询结果
 *  @Author   luqs   
 *  @Date 2018/8/20 15:33 
 *  @Version  V1.0   
 */
public class GeoCodeQryResult extends BaseResult {
    /**
     * 结果数目
     */
    private String count;
    /**
     * 地理编码信息列表
     */
    @JSONField(name = "geocodes")
    private List<GeoCodeInfo> geoCodeInfoList;

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public List<GeoCodeInfo> getGeoCodeInfoList() {
        return geoCodeInfoList;
    }

    public void setGeoCodeInfoList(List<GeoCodeInfo> geoCodeInfoList) {
        this.geoCodeInfoList = geoCodeInfoList;
    }

    @Override
    public String toString() {
        return "GeoCodeQryResult{" +
                "status='" + super.getStatus() + '\'' +
                ", infoCode='" + super.getInfoCode() + '\'' +
                ", info='" + super.getInfo() + '\'' +
                ", count='" + count + '\'' +
                ", geoCodeInfoList=" + geoCodeInfoList +
                '}';
    }
}
