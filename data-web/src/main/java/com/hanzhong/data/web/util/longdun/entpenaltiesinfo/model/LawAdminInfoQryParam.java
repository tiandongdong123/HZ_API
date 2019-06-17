package com.hanzhong.data.web.util.longdun.entpenaltiesinfo.model;

/**
 * @author yifei
 * @date 2019/6/9
 */
public class LawAdminInfoQryParam {
    /**
     * 企业名称
     */
    private String entName;

    public String getEntName() {
        return entName;
    }

    public void setEntName(String entName) {
        this.entName = entName;
    }

    @Override
    public String toString() {
        return "LawAdminInfoQryParam{" +
                "entName='" + entName + '\'' +
                '}';
    }
}
