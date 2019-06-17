package com.hanzhong.data.web.util.longdun.entpenaltiesinfo.constant;

/**
 *  
 *  @Description 企业经营异常信息移除标识枚举
 *  @Author   luqs   
 *  @Date 2019/6/15 17:02 
 *  @Version  V1.0   
 */
public enum MoveFlagEnum {
    /**
     * YES:是，即移除
     */
    YES("是"),
    /**
     * NO:否，即未移除
     */
    NO("否");

    private final String value;

    MoveFlagEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
