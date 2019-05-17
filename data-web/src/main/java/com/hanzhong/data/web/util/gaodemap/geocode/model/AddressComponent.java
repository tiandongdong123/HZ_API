package com.hanzhong.data.web.util.gaodemap.geocode.model;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 地址元素信息
 *
 * @author yifei
 * @date 2019/5/16
 */
public class AddressComponent {
    /**
     * 国家名称，如：中国
     */
    private String country;
    /**
     * 省名称，如：北京市
     */
    private String province;
    /**
     * 城市名称<br/>
     * 当所在城市为北京、上海、天津、重庆四个直辖市时，该字段返回为空；<br/>
     * 当所在城市属于县级市的时候，此字段为空
     */
    private String city;
    /**
     * 城市编码，如：010
     */
    @JSONField(name = "citycode")
    private String cityCode;
    /**
     * 区，如：海淀区
     */
    private String district;
    /**
     * 行政区编码，如：110108
     */
    @JSONField(name = "adcode")
    private String adCode;
    /**
     * 坐标点所在乡镇/街道（此街道为社区街道，不是道路信息），如：燕园街道
     */
    private String township;
    /**
     * 乡镇街道编码，如：110101001000
     */
    @JSONField(name = "towncode")
    private String townCode;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAdCode() {
        return adCode;
    }

    public void setAdCode(String adCode) {
        this.adCode = adCode;
    }

    public String getTownship() {
        return township;
    }

    public void setTownship(String township) {
        this.township = township;
    }

    public String getTownCode() {
        return townCode;
    }

    public void setTownCode(String townCode) {
        this.townCode = townCode;
    }

    @Override
    public String toString() {
        return "AddressComponent{" +
                "country='" + country + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", cityCode='" + cityCode + '\'' +
                ", district='" + district + '\'' +
                ", adCode='" + adCode + '\'' +
                ", township='" + township + '\'' +
                ", townCode='" + townCode + '\'' +
                '}';
    }
}
