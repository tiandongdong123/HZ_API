package com.hanzhong.data.web.util.gaodemap.geocode.model;

/**
 *    
 *  @Description 逆地理编码查询参数
 *  @Author   luqs   
 *  @Date 2018/8/20 12:23 
 *  @Version  V1.0   
 */
public class ReGeoCodeQryParam {
    /**
     * 高德Key
     */
    private String key;
    /**
     * 经纬度坐标<br/>
     * 传入内容规则：经度在前，纬度在后，经纬度间以“,”分割，经纬度小数点后不要超过 6 位。<br/>
     * 如果需要解析多个经纬度的话，请用"|"进行间隔，并且将 batch 参数设置为 true，最多支持传入 20 对坐标点。每对点坐标之间用"|"分割。
     */
    private String location;
    /**
     * 返回附近POI类型<br/>
     * 以下内容需要 extensions 参数为 all 时才生效。<br/>
     * 逆地理编码在进行坐标解析之后不仅可以返回地址描述，也可以返回经纬度附近符合限定要求的POI内容（在 extensions 字段值为 all 时才会返回POI内容）。设置 POI 类型参数相当于为上述操作限定要求。参数仅支持传入POI TYPECODE，可以传入多个POI TYPECODE，相互之间用“|”分隔。该参数在 batch 取值为 true 时不生效。获取 POI TYPECODE 可以参考POI分类码表
     */
    private String poiType;
    /**
     * 搜索半径<br/>
     * radius取值范围在0~3000，默认是1000。单位：米
     */
    private String radius;
    /**
     * 返回结果控制<br/>
     * extensions 参数默认取值是 base，也就是返回基本地址信息；<br/>
     * extensions 参数取值为 all 时会返回基本地址信息、附近 POI 内容、道路信息以及道路交叉口信息
     */
    private String extensions;
    /**
     * 批量查询控制<br/>
     * batch 参数设置为 true 时进行批量查询操作，最多支持 20 个经纬度点进行批量地址查询操作；<br/>
     * batch 参数设置为 false 时进行单点查询，此时即使传入多个经纬度也只返回第一个经纬度的地址解析查询结果。
     */
    private boolean batch;
    /**
     * 道路等级<br/>
     * 以下内容需要 extensions 参数为 all 时才生效。<br/>
     * 可选值：0，1；<br/>
     * 当roadlevel=0时，显示所有道路；<br/>
     * 当roadlevel=1时，过滤非主干道路，仅输出主干道路数据
     */
    private String roadLevel;
    /**
     * 数字签名
     */
    private String sig;
    /**
     * 输出格式<br/>
     * 可选输入内容包括：JSON，XML。设置 JSON 返回结果数据将会以JSON结构构成；如果设置 XML 返回结果数据将以 XML 结构构成。
     */
    private String output;
    /**
     * 回调函数<br/>
     * callback 值是用户定义的函数名称，此参数只在 output 参数设置为 JSON 时有效。
     */
    private String callback;
    /**
     * 是否优化POI返回顺序<br/>
     * 以下内容需要 extensions 参数为 all 时才生效。<br/>
     * homeorcorp 参数的设置可以影响召回 POI 内容的排序策略，目前提供三个可选参数：<br/>
     * 0：不对召回的排序策略进行干扰。<br/>
     * 1：综合大数据分析将居家相关的 POI 内容优先返回，即优化返回结果中 pois 字段的poi顺序。<br/>
     * 2：综合大数据分析将公司相关的 POI 内容优先返回，即优化返回结果中 pois 字段的poi顺序。
     */
    private String homeOrcOrp;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPoiType() {
        return poiType;
    }

    public void setPoiType(String poiType) {
        this.poiType = poiType;
    }

    public String getRadius() {
        return radius;
    }

    public void setRadius(String radius) {
        this.radius = radius;
    }

    public String getExtensions() {
        return extensions;
    }

    public void setExtensions(String extensions) {
        this.extensions = extensions;
    }

    public boolean isBatch() {
        return batch;
    }

    public void setBatch(boolean batch) {
        this.batch = batch;
    }

    public String getRoadLevel() {
        return roadLevel;
    }

    public void setRoadLevel(String roadLevel) {
        this.roadLevel = roadLevel;
    }

    public String getSig() {
        return sig;
    }

    public void setSig(String sig) {
        this.sig = sig;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getCallback() {
        return callback;
    }

    public void setCallback(String callback) {
        this.callback = callback;
    }

    public String getHomeOrcOrp() {
        return homeOrcOrp;
    }

    public void setHomeOrcOrp(String homeOrcOrp) {
        this.homeOrcOrp = homeOrcOrp;
    }

    @Override
    public String toString() {
        return "ReGeoCodeQryParam{" +
                "key='" + key + '\'' +
                ", location='" + location + '\'' +
                ", poiType='" + poiType + '\'' +
                ", radius='" + radius + '\'' +
                ", extensions='" + extensions + '\'' +
                ", batch=" + batch +
                ", roadLevel='" + roadLevel + '\'' +
                ", sig='" + sig + '\'' +
                ", output='" + output + '\'' +
                ", callback='" + callback + '\'' +
                ", homeOrcOrp='" + homeOrcOrp + '\'' +
                '}';
    }
}
