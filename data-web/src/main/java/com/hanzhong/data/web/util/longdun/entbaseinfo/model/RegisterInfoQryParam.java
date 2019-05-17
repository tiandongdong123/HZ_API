package com.hanzhong.data.web.util.longdun.entbaseinfo.model;

/**
 *  
 *  @Description
 *  @Author   luqs   
 *  @Date 2019/1/10 14:56 
 *  @Version  V1.0   
 */
public class RegisterInfoQryParam {
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
        return "RegisterInfoQryParam{" +
                "entName='" + entName + '\'' +
                '}';
    }
}
