package com.hanzhong.data.web.constant;

/**
 * 医疗器械检索字段枚举
 *
 * @author yifei
 * @date 2019/3/28
 */
public enum MedicalDeviceQryFieldEnum {
    /**
     * entName:企业名称
     */
    ENT_NAME("entName"),
    /**
     * usCreditCode:统一社会信用代码
     */
    UNIFIED_SOCIAL_CREDIT_CODE("usCreditCode"),
    /**
     * regCertNum:注册证编号
     */
    REG_CERT_NUM("regCertNum");

    private final String value;

    MedicalDeviceQryFieldEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
