package com.hanzhong.data.web.model.bo;

/**
 * 高新企业查询参数
 *
 * @author yifei
 * @date 2019/4/13
 */
public class HighTechEntQryBO {
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
        return "HighTechEntQryBO{" +
                "entName='" + entName + '\'' +
                ", usCreditCode='" + usCreditCode + '\'' +
                '}';
    }
}
