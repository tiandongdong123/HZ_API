package com.hanzhong.data.web.model.entity.slave;

/**
 * @author yifei
 * @date 2019/3/24
 */
public class LdEntGoodsInfoEntity {
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
    /**
     * 更新时间（10位时间戳）
     */
    private Long updateTime;

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

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "LdEntGoodsInfoEntity{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", model='" + model + '\'' +
                ", desc='" + desc + '\'' +
                ", brand='" + brand + '\'' +
                ", entName='" + entName + '\'' +
                ", status='" + status + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }
}
