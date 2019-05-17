package com.hanzhong.data.web.constant;

/**
 * 专利检索字段枚举
 *
 * @author yifei
 * @date 2019/3/28
 */
public enum PatentQryFieldEnum {
    /**
     * entName:企业名称
     */
    ENT_NAME("entName"),
    /**
     * usCreditCode:统一社会信用代码
     */
    UNIFIED_SOCIAL_CREDIT_CODE("usCreditCode"),
    /**
     * patentId:申请号
     */
    PATENT_ID("patentId");

    private final String value;

    PatentQryFieldEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
