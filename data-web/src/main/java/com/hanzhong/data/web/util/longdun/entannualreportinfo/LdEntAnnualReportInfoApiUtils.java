package com.hanzhong.data.web.util.longdun.entannualreportinfo;

import com.hanzhong.data.web.util.longdun.base.LdApiUtils;
import com.hanzhong.data.web.util.longdun.base.model.ApiParam;
import com.hanzhong.data.web.util.longdun.entannualreportinfo.constant.ServiceNameConstant;
import com.hanzhong.data.web.util.longdun.entannualreportinfo.model.EntAnnReportSocialSecurityInfoQryParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 *  
 *  @Description 企业年报信息API工具类
 *  @Author   luqs   
 *  @Date 2019/4/2 20:34 
 *  @Version  V1.0   
 */
public class LdEntAnnualReportInfoApiUtils {

    private static final Logger logger = LoggerFactory.getLogger(LdEntAnnualReportInfoApiUtils.class);

    private LdEntAnnualReportInfoApiUtils() {
    }

    /**
     * 获取企业年报社保信息
     *
     * @param infoQryParam 年报社保查询参数
     * @return String
     */
    public static String getEntAnnReportSocialSecurityInfo(EntAnnReportSocialSecurityInfoQryParam infoQryParam) {
        Map<String, Object> paramMap = new HashMap<>(2);
        paramMap.put("entName", infoQryParam.getEntName());
        paramMap.put("reportDate", infoQryParam.getReportDate());

        ApiParam apiParam = LdApiUtils.initApiParam(LdApiUtils.U_ID, ServiceNameConstant.GET_ENT_ANN_REPORT_SOCIAL_SECURITY_INFO_METHOD);
        apiParam.setParams(paramMap);

        logger.info("获取企业年报社保信息LdApiUtils.getResultStrByApiParam()的参数值：【{}】", apiParam);
        String resultStr = LdApiUtils.getResultStrByApiParam(apiParam);
        logger.info("获取企业年报社保信息LdApiUtils.getResultStrByApiParam()的返回值：【{}】", resultStr);
        return resultStr;
    }

}
