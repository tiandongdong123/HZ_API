package com.hanzhong.data.web.util.gaodemap.base.constant;

/**
 * 数据格式
 *
 * @author yifei
 * @date 2019/3/13
 */
public enum ResponseDataFormatEnum {
    /**
     * JSON：JSON格式
     */
    JSON("JSON"),
    /**
     * XML：XML格式
     */
    XML("XML");

    private final String value;

    ResponseDataFormatEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
