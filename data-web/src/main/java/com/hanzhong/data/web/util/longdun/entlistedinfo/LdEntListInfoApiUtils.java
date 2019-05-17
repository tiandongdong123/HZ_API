package com.hanzhong.data.web.util.longdun.entlistedinfo;

import com.hanzhong.data.web.util.ObjectUtils;
import com.hanzhong.data.web.util.longdun.base.LdApiUtils;
import com.hanzhong.data.web.util.longdun.base.model.ApiParam;
import com.hanzhong.data.web.util.longdun.base.model.ApiResult;
import com.hanzhong.data.web.util.longdun.entlistedinfo.constant.ServiceNameConstant;
import com.hanzhong.data.web.util.longdun.entlistedinfo.model.ListedCompanyBaseInfo;
import com.hanzhong.data.web.util.longdun.entlistedinfo.model.ListedCompanyBaseInfoQryParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  
 *  @Description
 *  @Author   luqs   
 *  @Date 2019/4/2 20:02 
 *  @Version  V1.0   
 */
public class LdEntListInfoApiUtils {

    private static final Logger logger = LoggerFactory.getLogger(LdEntListInfoApiUtils.class);

    private LdEntListInfoApiUtils() {
    }

    /**
     * 获取上市公司基本信息
     *
     * @param infoV2QryParam 上市公司基本信息查询参数
     * @return String
     */
    public static String getListedCompanyBaseMessageInfoV2(ListedCompanyBaseInfoQryParam infoV2QryParam) {
        Map<String, Object> paramMap = new HashMap<>(1);
        paramMap.put("entName", infoV2QryParam.getEntName());

        ApiParam apiParam = LdApiUtils.initApiParam(LdApiUtils.U_ID, ServiceNameConstant.GET_LISTED_COMPANY_BASE_MESSAGE_INFO_V2_METHOD);
        apiParam.setParams(paramMap);

        logger.info("获取上市公司基本信息LdApiUtils.getResultStrByApiParam()的参数值：【{}】", apiParam);
        String resultStr = LdApiUtils.getResultStrByApiParam(apiParam);
        logger.info("获取上市公司基本信息LdApiUtils.getResultStrByApiParam()的返回值：【{}】", resultStr);
        return resultStr;
    }

    /**
     * 获取上市公司基本信息
     *
     * @param infoV2QryParam 上市公司基本信息查询参数
     * @return String
     */
    public static ApiResult getListedCompanyBaseMessageInfoV2ApiResult(ListedCompanyBaseInfoQryParam infoV2QryParam) {
        // 获取上市公司基本信息
        String resultJsonStr = getListedCompanyBaseMessageInfoV2(infoV2QryParam);
        // 转换成ApiResult
        ApiResult apiResult = LdApiUtils.convertToApiResult(resultJsonStr);
        // 是否有数据
        if (!LdApiUtils.matchData(apiResult)) {
            return apiResult;
        }

        List<Map<String, Object>> resultDataMapList = (List<Map<String, Object>>) apiResult.getResultData();
        List<ListedCompanyBaseInfo> infoList = new ArrayList<>();
        for (Map<String, Object> map : resultDataMapList) {
            ListedCompanyBaseInfo info = new ListedCompanyBaseInfo();
            info.setStockCode(ObjectUtils.defaultString(map.get("STOCKCODE")));
            info.setStockShortName(ObjectUtils.defaultString(map.get("STOCKSNAME")));
            info.setCnName(ObjectUtils.defaultString(map.get("CNAME")));
            info.setCnShortName(ObjectUtils.defaultString(map.get("CSNAME")));
            info.setEnName(ObjectUtils.defaultString(map.get("ENAME")));
            info.setEnShortName(ObjectUtils.defaultString(map.get("ESNAME")));
            info.setEsDate(ObjectUtils.defaultString(map.get("BUILD_DATE")));
            info.setRegCap(BigDecimal.valueOf(ObjectUtils.defaultDouble(map.get("REGI_CAP"))));
            info.setCurrency(ObjectUtils.defaultString(map.get("CURNCY")));
            info.setRegAddress(ObjectUtils.defaultString(map.get("REGI_ADDR")));
            info.setOfficeAddress(ObjectUtils.defaultString(map.get("OFFICE_ADDR")));
            info.setWebSite(ObjectUtils.defaultString(map.get("WEB_SITE")));
            info.setComBrief(ObjectUtils.defaultString(map.get("COM_BRIEF")));
            info.setPriBusiness(ObjectUtils.defaultString(map.get("PRI_BIZ")));
            info.setStaffNum(ObjectUtils.defaultInt(map.get("STAFF_NUM")));
            info.setDeclareDate(ObjectUtils.defaultString(map.get("DECLAREDATE")));
            info.setListDate(ObjectUtils.defaultString(map.get("LIST_DATE")));
            info.setListStatus(ObjectUtils.defaultString(map.get("STATUS_TYPE_REF")));
            info.setStockExchange(ObjectUtils.defaultString(map.get("TRADE_MKT_REF")));
            info.setStockType(ObjectUtils.defaultString(map.get("STK_TYPE_REF")));
            info.setDelistingDate(ObjectUtils.defaultString(map.get("LIST_ENDDATE")));
            info.setIsin(ObjectUtils.defaultString(map.get("ISIN")));
            infoList.add(info);
        }
        apiResult.setResultData(infoList);
        return apiResult;
    }

    /**
     * 获取上市公司基本信息
     *
     * @param infoV2QryParam 上市公司基本信息查询参数
     * @return ListedCompanyBaseInfo
     */
    public static ListedCompanyBaseInfo getListedCompanyBaseInfo(ListedCompanyBaseInfoQryParam infoV2QryParam) {
        // 获取上市公司基本信息
        ApiResult apiResult = getListedCompanyBaseMessageInfoV2ApiResult(infoV2QryParam);
        // 是否有数据
        if (!LdApiUtils.matchData(apiResult)) {
            return null;
        }

        // 判断结果数据是否为空
        List<ListedCompanyBaseInfo> companyBaseInfoList = (List<ListedCompanyBaseInfo>) apiResult.getResultData();
        if (companyBaseInfoList == null || companyBaseInfoList.isEmpty()) {
            return null;
        }
        return companyBaseInfoList.get(0);
    }
}
