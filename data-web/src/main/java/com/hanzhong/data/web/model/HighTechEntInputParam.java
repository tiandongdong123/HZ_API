package com.hanzhong.data.web.model;

/**
 * 高新企业录入参数
 *
 * @author yifei
 * @date 2019/4/13
 */
public class HighTechEntInputParam {

    /**
     * 企业名称
     */
    private String entName;
    /**
     * 统一社会信用代码
     */
    private String usCreditCode;

    public String getEntName() {
        return entName;
    }

    public void setEntName(String entName) {
        this.entName = entName;
    }

    public String getUsCreditCode() {
        return usCreditCode;
    }

    public void setUsCreditCode(String usCreditCode) {
        this.usCreditCode = usCreditCode;
    }

    @Override
    public String toString() {
        return "HighTechEntInputParam{" +
                "entName='" + entName + '\'' +
                ", usCreditCode='" + usCreditCode + '\'' +
                '}';
    }
}
