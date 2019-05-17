package com.hanzhong.data.web.constant;

/**
 * 医药药品检索字段枚举
 *
 * @author yifei
 * @date 2019/3/28
 */
public enum DrugQryFieldEnum {
    /**
     * entName:企业名称
     */
    ENT_NAME("entName"),
    /**
     * usCreditCode:统一社会信用代码
     */
    UNIFIED_SOCIAL_CREDIT_CODE("usCreditCode"),
    /**
     * apprNum:批准文号
     */
    APPR_NUM("apprNum");

    private final String value;

    DrugQryFieldEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
