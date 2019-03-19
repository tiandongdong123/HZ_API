package com.hanzhong.data.web.constant;

/**
 *  
 *  @Description 企业状态枚举
 *  @Author   luqs   
 *  @Date 2018/11/8 9:38 
 *  @Version  V1.0   
 */
public enum EntStatusEnum {

    /**
     * 1：在营（开业）企业
     */
    DOING_BUSINESS("1", "在营（开业）企业"),
    /**
     * 2：吊销企业
     */
    REVOCATION("2", "吊销企业"),
    /**
     * 3：注销企业
     */
    CANCEL("3", "注销企业"),
    /**
     * 4：迁出
     */
    MOVING_OUT("4", "迁出");

    private String key;
    private String value;

    EntStatusEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }

    /**
     * 通过key获取值
     *
     * @param key
     * @return 若无此key, 则返回null
     */
    public static String getName(String key) {
        for (EntStatusEnum c : EntStatusEnum.values()) {
            if (c.getKey().equals(key)) {
                return c.getValue();
            }
        }
        return null;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
