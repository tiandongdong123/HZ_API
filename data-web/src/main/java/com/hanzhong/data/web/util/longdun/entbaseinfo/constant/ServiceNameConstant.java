package com.hanzhong.data.web.util.longdun.entbaseinfo.constant;

/**
 *  
 *  @Description 龙盾接口服务名称常量类
 *  @Author   luqs   
 *  @Date 2019/3/16 14:20 
 *  @Version  V1.0   
 */
public class ServiceNameConstant {

    private ServiceNameConstant() {
    }

    /**
     * 方法-企业名单检索
     */
    public static final String GET_ENT_BY_KEY_WORD_METHOD = "getEntByKeyword";
    /**
     * 方法-获取企业基本信息
     */
    public static final String GET_REGISTER_INFO_METHOD = "getRegisterInfo";
    /**
     * 方法-获取企业股东及出资信息
     */
    public static final String GET_SHARE_HOLDER_INFO_METHOD = "getShareHolderInfo";
    /**
     * 方法-获取企业变更信息
     */
    public static final String GET_REGISTER_CHANGE_INFO_METHOD = "getRegisterChangeInfo";
    /**
     * 方法-获取企业主要管理人员信息
     */
    public static final String GET_MAIN_MANAGER_INFO_METHOD = "getMainManagerInfo";
}
