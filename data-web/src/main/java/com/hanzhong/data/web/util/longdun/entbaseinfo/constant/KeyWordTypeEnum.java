package com.hanzhong.data.web.util.longdun.entbaseinfo.constant;

/**
 *  
 *  @Description 关键字类型枚举
 *  @Author   luqs   
 *  @Date 2019/1/10 14:45 
 *  @Version  V1.0   
 */
public enum KeyWordTypeEnum {
    /**
     * 企业名称
     */
    ENT_NAME("0"),
    /**
     * 注册号或统一社会信用代码
     */
    REG_NUM_OR_USCC_NUM("1");

    private final String value;

    KeyWordTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
