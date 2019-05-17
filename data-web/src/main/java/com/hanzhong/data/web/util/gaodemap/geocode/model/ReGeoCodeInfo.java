package com.hanzhong.data.web.util.gaodemap.geocode.model;

import com.alibaba.fastjson.annotation.JSONField;

/**
 *    
 *  @Description 逆地理编码信息
 *  @Author   luqs   
 *  @Date 2018/8/20 12:35 
 *  @Version  V1.0   
 */
public class ReGeoCodeInfo {

    /**
     * 结构化地址信息<br/>
     * 省份＋城市＋区县＋城镇＋乡村＋街道＋门牌号码<br/>
     * 如果坐标点处于海域范围内，则结构化地址信息为：省份＋城市＋区县＋海域信息
     */
    @JSONField(name = "formatted_address")
    private String formattedAddress;
    /**
     * 地址元素
     */
    private AddressComponent addressComponent;

    public String getFormattedAddress() {
        return formattedAddress;
    }

    public void setFormattedAddress(String formattedAddress) {
        this.formattedAddress = formattedAddress;
    }

    public AddressComponent getAddressComponent() {
        return addressComponent;
    }

    public void setAddressComponent(AddressComponent addressComponent) {
        this.addressComponent = addressComponent;
    }

    @Override
    public String toString() {
        return "ReGeoCodeInfo{" +
                "formattedAddress='" + formattedAddress + '\'' +
                ", addressComponent=" + addressComponent +
                '}';
    }
}
