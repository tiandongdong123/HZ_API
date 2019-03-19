package com.hanzhong.data.web.util.gaodemap.constant;

/**
 * 请求状态枚举
 *
 * @author yifei
 * @date 2019/3/12
 */
public enum RequestStatusEnum {
    /**
     * 0：请求失败
     */
    FAIL("0"),
    /**
     * 1：请求成功
     */
    SUCCESS("1");

    private final String value;

    RequestStatusEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
