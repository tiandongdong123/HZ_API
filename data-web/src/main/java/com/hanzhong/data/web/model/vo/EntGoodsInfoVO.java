package com.hanzhong.data.web.model.vo;

import java.io.Serializable;

/**
 * 企业商品信息
 *
 * @author yifei
 * @date 2019/3/21
 */
public class EntGoodsInfoVO implements Serializable {
    private static final long serialVersionUID = -4963266899668807475L;
    /**
     * 商品条码
     */
    private String code;
    /**
     * 名称
     */
    private String name;
    /**
     * 规格型号
     */
    private String model;
    /**
     * 描述
     */
    private String desc;
    /**
     * 商标
     */
    private String brand;
    /**
     * 发布厂家
     */
    private String entName;
    /**
     * 条码状态
     */
    private String status;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getEntName() {
        return entName;
    }

    public void setEntName(String entName) {
        this.entName = entName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "EntGoodsInfoVO{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", model='" + model + '\'' +
                ", desc='" + desc + '\'' +
                ", brand='" + brand + '\'' +
                ", entName='" + entName + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
