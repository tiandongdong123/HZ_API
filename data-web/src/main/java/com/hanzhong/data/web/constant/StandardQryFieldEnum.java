package com.hanzhong.data.web.constant;

/**
 * 标准检索字段枚举
 *
 * @author yifei
 * @date 2019/3/28
 */
public enum StandardQryFieldEnum {
    /**
     * entName:企业名称
     */
    ENT_NAME("entName"),
    /**
     * usCreditCode:统一社会信用代码
     */
    UNIFIED_SOCIAL_CREDIT_CODE("usCreditCode"),
    /**
     * standardNum:标准号
     */
    STANDARD_NUM("standardNum");

    private final String value;

    StandardQryFieldEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
