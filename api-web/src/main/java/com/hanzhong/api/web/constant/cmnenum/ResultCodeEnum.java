package com.hanzhong.api.web.constant.cmnenum;

/**
 *  
 *  @Description 结果码枚举
 *  @Author   luqs   
 *  @Date 2018/11/5 17:23 
 *  @Version  V1.0   
 */
public enum ResultCodeEnum {
    /**
     * 0：未查到数据
     */
    NO_DATA("0", "未查询到相关数据"),
    /**
     * 1：成功
     */
    SUCCESS("1", "成功"),
    /**
     * -1：查询失败
     */
    FAIL("-1", "查询失败"),

    /**
     * D001：没有权限访问该服务
     */
    NO_PERMISSION("D001", "没有权限访问该服务"),
    /**
     * D002：用户信息错误
     */
    USER_INFO_ERROR("D002", "用户信息错误"),
    /**
     * D003：账户余额不足，不能正常访问该服务
     */
    BALANCE_NOT_ENOUGH("D003", "账户余额不足，不能正常访问该服务"),
    /**
     * D004：参数数据加密错误
     */
    PARAM_ENCRYPTION_ERROR("D004", "参数数据加密错误"),
    /**
     * D005：数据参数不正确（输入参数过长）
     */
    PARAM_OVER_LENGTH("D005", "数据参数不正确（输入参数过长）"),
    /**
     * D006：参数数据不正确（部分参数为空）
     */
    PARAM_EMPTY("D006", "参数数据不正确（部分参数为空）"),
    /**
     * D007：参数数据不正确（参数格式不正确）
     */
    PARAM_FORMAT_ERROR("D007", "参数数据不正确（参数格式不正确）"),
    /**
     * D008：参数数据不正确（参数输入项不合法）
     */
    PARAM_NOT_LEGAL("D008", "参数数据不正确（参数输入项不合法）"),
    /**
     * D009：系统错误，结果返回超时
     */
    OVER_TIME("D009", "系统错误，结果返回超时"),
    /**
     * D010：无权查询数据（IP地址未绑定）
     */
    IP_NOT_BIND("D010", "无权查询数据（IP地址未绑定）"),
    /**
     * D011：无权查询数据（测试量已用完）
     */
    TEST_RUN_OUT("D011", "无权查询数据（测试量已用完）"),
    /**
     * D012 ：产品已下架
     */
    PRODUCT_OFF("D012 ", "产品已下架");

    private String key;
    private String value;

    ResultCodeEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }

    /**
     * 通过key获取值
     *
     * @param key
     * @return 若无此key, 则返回null
     */
    public static String getValue(String key) {
        for (ResultCodeEnum c : ResultCodeEnum.values()) {
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
