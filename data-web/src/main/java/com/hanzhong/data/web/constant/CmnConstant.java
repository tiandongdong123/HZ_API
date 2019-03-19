package com.hanzhong.data.web.constant;

import com.hanzhong.data.web.util.PropertiesUtils;

/**
 *  
 *  @Description
 *  @Author   luqs   
 *  @Date 2018/11/5 17:19 
 *  @Version  V1.0   
 */
public class CmnConstant {

    /**
     * 长期
     */
    public static final String LONG_TERM_WORD = "长期";
    /**
     * 长期默认日期4999-12-31
     */
    public static final String LONG_TERM_DATE = "4999-12-31";
    /**
     * 第三方数据更新间隔
     */
    public static final Long THIRD_PARTY_DATA_UPDATE_INTERVAL = Long.parseLong(PropertiesUtils.getValue("thirdPartyData.update.interval"));
}
