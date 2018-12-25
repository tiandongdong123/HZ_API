package com.hanzhong.api.web.constant.cmnenum;

/**
 * @author yifei
 * @date 2018/12/1
 */
public enum LoggerEnum {

    /**
     * abnormalCodeDataLogger:异常代码数据
     */
    ABNORMAL_CODE_DATA("abnormalCodeDataLogger"),
    /**
     * ipVisitLogger:ip访问
     */
    IP_VISIT("ipVisitLogger");

    private final String value;

    LoggerEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
