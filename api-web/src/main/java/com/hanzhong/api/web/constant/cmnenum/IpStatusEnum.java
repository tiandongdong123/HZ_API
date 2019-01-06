package com.hanzhong.api.web.constant.cmnenum;

/**
 *  
 *  @Description ip状态枚举
 *  @Author   luqs   
 *  @Date 2018/11/8 9:38 
 *  @Version  V1.0   
 */
public enum IpStatusEnum {

    /**
     * 0：冻结
     */
    FROZEN(0),
    /**
     * 1：正常
     */
    NORMAL(1);

    private int key;

    IpStatusEnum(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

}
