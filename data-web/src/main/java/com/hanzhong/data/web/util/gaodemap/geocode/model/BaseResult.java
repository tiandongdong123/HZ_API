package com.hanzhong.data.web.util.gaodemap.geocode.model;

import com.alibaba.fastjson.annotation.JSONField;

/**
 *  
 *  @Description 基础结果
 *  @Author   luqs   
 *  @Date 2019/5/17 10:53 
 *  @Version  V1.0   
 */
public class BaseResult {
    /**
     * 结果状态值，返回值为 0 或 1，0 表示请求失败；1 表示请求成功。
     */
    private String status;
    /**
     * 状态code
     */
    @JSONField(name = "infocode")
    private String infoCode;
    /**
     * 状态说明
     */
    private String info;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInfoCode() {
        return infoCode;
    }

    public void setInfoCode(String infoCode) {
        this.infoCode = infoCode;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "BaseResult{" +
                "status='" + status + '\'' +
                ", infoCode='" + infoCode + '\'' +
                ", info='" + info + '\'' +
                '}';
    }
}
