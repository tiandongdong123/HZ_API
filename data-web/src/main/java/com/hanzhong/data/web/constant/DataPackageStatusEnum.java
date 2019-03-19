package com.hanzhong.data.web.constant;

/**
 *  
 *  @Description
 *  @Author   luqs   
 *  @Date 2019/1/10 15:23 
 *  @Version  V1.0   
 */
public enum DataPackageStatusEnum {
    /**
     * 未读
     */
    NO_READ("1"),
    /**
     * 已读
     */
    READED("2");

    private final String value;

    DataPackageStatusEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
