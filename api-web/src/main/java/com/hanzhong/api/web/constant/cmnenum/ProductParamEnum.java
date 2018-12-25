package com.hanzhong.api.web.constant.cmnenum;

/**
 *  
 *  @Description 参数枚举
 *  @Author   luqs   
 *  @Date 2018/11/5 18:09 
 *  @Version  V1.0   
 */
public enum ProductParamEnum {

    /**
     * companyName：公司名称
     */
    COMPANY_NAME("companyName"),

    /**
     * corCodeTy：统一社会信用代码
     */
    COR_CODE_TY("corCodeTy"),

    /**
     * corCode：组织机构代码
     */
    COR_CODE("corCode");

    private final String value;

    ProductParamEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
