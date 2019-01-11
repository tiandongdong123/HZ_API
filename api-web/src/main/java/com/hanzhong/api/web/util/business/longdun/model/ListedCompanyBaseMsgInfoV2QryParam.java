package com.hanzhong.api.web.util.business.longdun.model;

/**
 *  
 *  @Description
 *  @Author   luqs   
 *  @Date 2019/1/10 15:08 
 *  @Version  V1.0   
 */
public class ListedCompanyBaseMsgInfoV2QryParam {
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
        return "ListedCompanyBaseMsgInfoV2QryParam{" +
                "entName='" + entName + '\'' +
                '}';
    }
}
