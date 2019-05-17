package com.hanzhong.data.web.util.longdun.entinvestinfo;

import com.hanzhong.data.web.util.ObjectUtils;
import com.hanzhong.data.web.util.longdun.base.LdApiUtils;
import com.hanzhong.data.web.util.longdun.base.model.ApiParam;
import com.hanzhong.data.web.util.longdun.base.model.ApiResult;
import com.hanzhong.data.web.util.longdun.entinvestinfo.constant.ServiceNameConstant;
import com.hanzhong.data.web.util.longdun.entinvestinfo.model.BranchInfoQryParam;
import com.hanzhong.data.web.util.longdun.entinvestinfo.model.EntActualControllerQryParam;
import com.hanzhong.data.web.util.longdun.entinvestinfo.model.InvAbroadInfo;
import com.hanzhong.data.web.util.longdun.entinvestinfo.model.InvAbroadInfoQryParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.*;

/**
 *  
 *  @Description 企业投资信息API工具类
 *  @Author   luqs   
 *  @Date 2019/4/2 20:21 
 *  @Version  V1.0   
 */
public class LdEntInvestInfoApiUtils {

    private static final Logger logger = LoggerFactory.getLogger(LdEntInvestInfoApiUtils.class);

    private LdEntInvestInfoApiUtils() {
    }

    /**
     * 获取企业对外投资信息
     *
     * @param invAbroadInfoQryParam 对外投资信息查询参数
     * @return String
     */
    public static String getInvestmentAbroadInfo(InvAbroadInfoQryParam invAbroadInfoQryParam) {
        Map<String, Object> paramMap = new HashMap<>(3);
        paramMap.put("entName", invAbroadInfoQryParam.getEntName());
        paramMap.put("pageNo", invAbroadInfoQryParam.getPageNo());
        paramMap.put("pageSize", invAbroadInfoQryParam.getPageSize());

        ApiParam apiParam = LdApiUtils.initApiParam(LdApiUtils.U_ID, ServiceNameConstant.GET_INV_ABROAD_INFO_METHOD);
        apiParam.setParams(paramMap);

        logger.info("获取企业对外投资信息LdApiUtils.getInvestmentAbroadInfo()的参数值：【{}】", apiParam);
        String resultStr = LdApiUtils.getResultStrByApiParam(apiParam);
        logger.info("获取企业对外投资信息LdApiUtils.getInvestmentAbroadInfo()的返回值：【{}】", resultStr);
        return resultStr;
    }

    /**
     * 获取企业对外投资信息
     *
     * @param invAbroadInfoQryParam 对外投资信息查询参数
     * @return ApiResult
     */
    public static ApiResult getInvestmentAbroadInfoApiResult(InvAbroadInfoQryParam invAbroadInfoQryParam) {
        // 获取企业对外投资信息
        String resultJsonStr = getInvestmentAbroadInfo(invAbroadInfoQryParam);
        // 转换成ApiResult
        ApiResult apiResult = LdApiUtils.convertToApiResult(resultJsonStr);
        // 是否有数据
        if (!LdApiUtils.matchData(apiResult)) {
            return apiResult;
        }

        List<Map<String, Object>> resultDataMapList = (List<Map<String, Object>>) apiResult.getResultData();
        List<InvAbroadInfo> infoList = new ArrayList<>();
        for (Map<String, Object> map : resultDataMapList) {
            InvAbroadInfo info = new InvAbroadInfo();
            info.setEntName(ObjectUtils.defaultString(map.get("ENTNAME")));
            info.setUsCreditCode(ObjectUtils.defaultString(map.get("SHXYDM")));
            info.setEsDate(ObjectUtils.defaultString(map.get("ESDATE")));
            info.setEntStatus(ObjectUtils.defaultString(map.get("ENTSTATUS")));
            info.setRegCap(BigDecimal.valueOf(ObjectUtils.defaultDouble(map.get("REGCAP"))));
            info.setSubConAmount(BigDecimal.valueOf(ObjectUtils.defaultDouble(map.get("SUBCONAM"))));
            info.setConCur(ObjectUtils.defaultString(map.get("CONCUR")));
            info.setConRatIo(BigDecimal.valueOf(ObjectUtils.defaultDouble(map.get("CONRATIO"))));
            info.setConDate(ObjectUtils.defaultString(map.get("CONDATE")));
            infoList.add(info);
        }
        apiResult.setResultData(infoList);
        logger.debug("获取企业对外投资信息ApiResult值：【{}】", apiResult);
        return apiResult;
    }

    /**
     * 获取企业对外投资信息
     *
     * @param invAbroadInfoQryParam 对外投资信息查询参数
     * @return List<InvAbroadInfo>
     */
    public static List<InvAbroadInfo> getInvestmentAbroadInfoList(InvAbroadInfoQryParam invAbroadInfoQryParam) {
        // 获取企业对外投资信息
        ApiResult apiResult = getInvestmentAbroadInfoApiResult(invAbroadInfoQryParam);
        return LdApiUtils.matchData(apiResult) ? (List<InvAbroadInfo>) apiResult.getResultData() : Collections.emptyList();
    }

    /**
     * 获取企业分支机构信息
     *
     * @param branchInfoQryParam 分支机构信息查询参数
     * @return String
     */
    public static String getBranchInfo(BranchInfoQryParam branchInfoQryParam) {
        Map<String, Object> paramMap = new HashMap<>(3);
        paramMap.put("entName", branchInfoQryParam.getEntName());
        paramMap.put("pageNo", branchInfoQryParam.getPageNo());
        paramMap.put("pageSize", branchInfoQryParam.getPageSize());

        ApiParam apiParam = LdApiUtils.initApiParam(LdApiUtils.U_ID, ServiceNameConstant.GET_BRANCH_INFO_METHOD);
        apiParam.setParams(paramMap);

        logger.info("获取企业分支机构信息LdApiUtils.getInvestmentAbroadInfo()的参数值：【{}】", apiParam);
        String resultStr = LdApiUtils.getResultStrByApiParam(apiParam);
        logger.info("获取企业分支机构信息LdApiUtils.getInvestmentAbroadInfo()的返回值：【{}】", resultStr);
        return resultStr;
    }

    /**
     * 获取企业实际控制人信息
     *
     * @param controllerQryParam 实际控制人信息查询参数
     * @return String
     */
    public static String getEntActualController(EntActualControllerQryParam controllerQryParam) {
        Map<String, Object> paramMap = new HashMap<>(3);
        paramMap.put("entName", controllerQryParam.getEntName());
        paramMap.put("attIds", controllerQryParam.getAttIds());
        paramMap.put("level", controllerQryParam.getLevel());

        ApiParam apiParam = LdApiUtils.initApiParam(LdApiUtils.U_ID, ServiceNameConstant.GET_ENT_ACTUAL_CONTROLLER_METHOD);
        apiParam.setParams(paramMap);

        logger.info("获取企业实际控制人信息LdApiUtils.getInvestmentAbroadInfo()的参数值：【{}】", apiParam);
        String resultStr = LdApiUtils.getResultStrByApiParam(apiParam);
        logger.info("获取企业实际控制人信息LdApiUtils.getInvestmentAbroadInfo()的返回值：【{}】", resultStr);
        return resultStr;
    }
}
