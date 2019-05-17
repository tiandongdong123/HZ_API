package com.hanzhong.data.web.util.gaodemap.geocode.model;

import com.alibaba.fastjson.annotation.JSONField;

/**
 *    
 *  @Description 逆地理编码查询结果
 *  @Author   luqs   
 *  @Date 2018/8/20 15:33 
 *  @Version  V1.0   
 */
public class ReGeoCodeQryResult extends BaseResult {

    /**
     * 地理编码信息列表
     */
    @JSONField(name = "regeocode")
    private ReGeoCodeInfo reGeoCodeInfo;

    public ReGeoCodeInfo getReGeoCodeInfo() {
        return reGeoCodeInfo;
    }

    public void setReGeoCodeInfo(ReGeoCodeInfo reGeoCodeInfo) {
        this.reGeoCodeInfo = reGeoCodeInfo;
    }

    @Override
    public String toString() {
        return "ReGeoCodeQryResult{" +
                "status='" + super.getStatus() + '\'' +
                ", infoCode='" + super.getInfoCode() + '\'' +
                ", info='" + super.getInfo() + '\'' +
                ", reGeoCodeInfo=" + reGeoCodeInfo +
                '}';
    }
}
