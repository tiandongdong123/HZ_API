package com.hanzhong.data.web.util.longdun.datapackageinfo.constant;

/**
 *  
 *  @Description 变更数据包回馈状态
 *  @Author   luqs   
 *  @Date 2019/1/10 15:23 
 *  @Version  V1.0   
 */
public enum DataPkgActionTypeEnum {
    /**
     * 未读
     */
    NO_READ("1"),
    /**
     * 已读
     */
    READED("2");

    private final String value;

    DataPkgActionTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
