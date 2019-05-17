package com.hanzhong.data.web.constant;

/**
 * 医药保护品种检索字段枚举
 *
 * @author yifei
 * @date 2019/3/28
 */
public enum DrugProtVarietyQryFieldEnum {
    /**
     * entName:企业名称
     */
    ENT_NAME("entName"),
    /**
     * usCreditCode:统一社会信用代码
     */
    UNIFIED_SOCIAL_CREDIT_CODE("usCreditCode"),
    /**
     * varietyId:保护品种编号
     */
    VARIETY_ID("varietyId");

    private final String value;

    DrugProtVarietyQryFieldEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
