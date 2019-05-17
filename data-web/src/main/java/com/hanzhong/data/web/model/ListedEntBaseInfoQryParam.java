package com.hanzhong.data.web.model;

/**
 * 上市企业基本信息查询参数
 *
 * @author yifei
 * @date 2019/3/21
 */
public class ListedEntBaseInfoQryParam {
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
        return "ListedEntBaseInfoQryParam{" +
                "entName='" + entName + '\'' +
                '}';
    }
}
