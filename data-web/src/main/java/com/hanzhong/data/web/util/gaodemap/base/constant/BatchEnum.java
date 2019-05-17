package com.hanzhong.data.web.util.gaodemap.base.constant;

/**
 * 批量查询控制
 *
 * @author yifei
 * @date 2019/3/13
 */
public enum BatchEnum {
    /**
     * true ：批量查询
     */
    TRUE("true "),
    /**
     * false：单条查询
     */
    FALSE("false");

    private final String value;

    BatchEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
