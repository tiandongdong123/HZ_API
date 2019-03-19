package com.hanzhong.data.web.util.longdun.model;

import com.hanzhong.data.web.util.longdun.constant.KeyWordTypeEnum;

/**
 *  
 *  @Description
 *  @Author   luqs   
 *  @Date 2019/1/10 14:44 
 *  @Version  V1.0   
 */
public class EntKeyWordQryParam {
    /**
     * 企业名称/注册号/社会信用代码
     */
    private String keyword;
    /**
     * 关键字类型枚举
     */
    private KeyWordTypeEnum keyWordTypeEnum;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public KeyWordTypeEnum getKeyWordTypeEnum() {
        return keyWordTypeEnum;
    }

    public void setKeyWordTypeEnum(KeyWordTypeEnum keyWordTypeEnum) {
        this.keyWordTypeEnum = keyWordTypeEnum;
    }

    @Override
    public String toString() {
        return "EntKeyWordQryParam{" +
                "keyword='" + keyword + '\'' +
                ", keyWordTypeEnum=" + keyWordTypeEnum +
                '}';
    }
}
