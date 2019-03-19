package com.hanzhong.data.web.util.longdun.constant;

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
     * 方法-获取企业对外投资信息
     */
    public static final String GET_INV_ABROAD_INFO_METHOD = "getInvestmentAbroadInfo";
    /**
     * 方法-获取企业分支机构信息
     */
    public static final String GET_BRANCH_INFO_METHOD = "getBranchInfo";
    /**
     * 方法-获取企业变更信息
     */
    public static final String GET_REGISTER_CHANGE_INFO_METHOD = "getRegisterChangeInfo";
    /**
     * 方法-获取企业主要管理人员信息
     */
    public static final String GET_MAIN_MANAGER_INFO_METHOD = "getMainManagerInfo";
    /**
     * 方法-获取商品信息
     */
    public static final String GET_GOODS_INFO_METHOD = "getgoodsInfo";
    /**
     * 方法-获取未下载的变更数据包列表
     */
    public static final String CHANGE_DATA_PACKAGE_LIST_METHOD = "changeDataPackageList";
    /**
     * 方法-变更数据包回馈状态
     */
    public static final String MONITOR_DATA_PACKAGE_FINISH_METHOD = "monitorDataPackageFinish";
    /**
     * 方法-获取企业年报社保信息
     */
    public static final String GET_ENT_ANN_REPORT_SOCIAL_SECURITY_INFO_METHOD = "getEntAnnReportSocialSecurityInfo";
    /**
     * 方法-获取上市公司基本信息
     */
    public static final String GET_LISTED_COMPANY_BASE_MESSAGE_INFO_V2_METHOD = "getListedCompanyBaseMessageInfoV2";
    /**
     * 方法-获取企业实际控制人信息
     */
    public static final String GET_ENT_ACTUAL_CONTROLLER_METHOD = "getEntActualContoller";
}
