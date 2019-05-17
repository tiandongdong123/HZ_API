package com.hanzhong.data.web.model.bo;

/**
 * @author yifei
 * @date 2019/3/24
 */
public class ListedEntBaseInfoQryBO {
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
        return "ListedEntBaseInfoQryBO{" +
                "entName='" + entName + '\'' +
                '}';
    }
}
