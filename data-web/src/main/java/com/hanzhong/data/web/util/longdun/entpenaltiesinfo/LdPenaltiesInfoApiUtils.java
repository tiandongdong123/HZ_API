package com.hanzhong.data.web.util.longdun.entpenaltiesinfo;

import com.hanzhong.data.web.util.ObjectUtils;
import com.hanzhong.data.web.util.longdun.base.LdApiUtils;
import com.hanzhong.data.web.util.longdun.base.model.ApiParam;
import com.hanzhong.data.web.util.longdun.base.model.ApiResult;
import com.hanzhong.data.web.util.longdun.entpenaltiesinfo.constant.ServiceNameConstant;
import com.hanzhong.data.web.util.longdun.entpenaltiesinfo.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 *  
 *  @Description 企业工商处罚信息API工具类
 *  @Author   luqs   
 *  @Date 2019/4/2 20:21 
 *  @Version  V1.0   
 */
public class LdPenaltiesInfoApiUtils {

    private static final Logger logger = LoggerFactory.getLogger(LdPenaltiesInfoApiUtils.class);

    private LdPenaltiesInfoApiUtils() {
    }

    /**
     * 获取企业行政处罚信息
     *
     * @param infoQryParam 行政处罚信息查询参数
     * @return String
     */
    public static String getLawAdminInfo(LawAdminInfoQryParam infoQryParam) {
        Map<String, Object> paramMap = new HashMap<>(1);
        paramMap.put("entName", infoQryParam.getEntName());

        ApiParam apiParam = LdApiUtils.initApiParam(LdApiUtils.U_ID, ServiceNameConstant.GET_LAW_ADMIN_INFO_METHOD);
        apiParam.setParams(paramMap);

        logger.info("获取企业行政处罚信息LdApiUtils.getResultStrByApiParam()的参数值：【{}】", apiParam);
        String resultStr = LdApiUtils.getResultStrByApiParam(apiParam);
        logger.info("获取企业行政处罚信息LdApiUtils.getResultStrByApiParam()的返回值：【{}】", resultStr);
        return resultStr;
    }

    /**
     * 获取企业行政处罚信息
     *
     * @param infoQryParam 行政处罚信息查询参数
     * @return ApiResult
     */
    public static ApiResult getLawAdminInfoApiResult(LawAdminInfoQryParam infoQryParam) {
        // 获取企业行政处罚信息
        String resultJsonStr = getLawAdminInfo(infoQryParam);
        // 转换成ApiResult
        ApiResult apiResult = LdApiUtils.convertToApiResult(resultJsonStr);
        // 是否有数据
        if (!LdApiUtils.matchData(apiResult)) {
            return apiResult;
        }

        List<Map<String, Object>> resultDataMapList = (List<Map<String, Object>>) apiResult.getResultData();
        List<LawAdminInfo> infoList = new ArrayList<>();
        for (Map<String, Object> map : resultDataMapList) {
            LawAdminInfo info = new LawAdminInfo();
            info.setPenDecNo(ObjectUtils.defaultString(map.get("PENDECNO")));
            info.setIllegActType(ObjectUtils.defaultString(map.get("ILLEGACTTYPE")));
            info.setPenResult(ObjectUtils.defaultString(map.get("PENRESULT")));
            info.setCaseDep(ObjectUtils.defaultString(map.get("CASEDEP")));
            info.setPenDecIssDate(ObjectUtils.defaultString(map.get("PENDECISSDATE")));
            info.setPubDate(ObjectUtils.defaultString(map.get("PUBDATE")));
            infoList.add(info);
        }
        apiResult.setResultData(infoList);
        logger.debug("获取企业行政处罚信息ApiResult值：【{}】", apiResult);
        return apiResult;
    }

    /**
     * 获取企业行政处罚信息
     *
     * @param infoQryParam 行政处罚信息查询参数
     * @return List<LawAdminInfo>
     */
    public static List<LawAdminInfo> getLawAdminInfoList(LawAdminInfoQryParam infoQryParam) {
        // 获取企业行政处罚信息
        ApiResult apiResult = getLawAdminInfoApiResult(infoQryParam);
        return LdApiUtils.matchData(apiResult) ? (List<LawAdminInfo>) apiResult.getResultData() : Collections.emptyList();
    }

    /**
     * 获取企业经营异常信息
     *
     * @param infoQryParam 经营异常信息查询参数
     * @return String
     */
    public static String getOperatingExceptionRota(OperatExcepRotaQryParam infoQryParam) {
        Map<String, Object> paramMap = new HashMap<>(1);
        paramMap.put("entName", infoQryParam.getEntName());

        ApiParam apiParam = LdApiUtils.initApiParam(LdApiUtils.U_ID, ServiceNameConstant.GET_OPERATING_EXCEPTION_ROTA_METHOD);
        apiParam.setParams(paramMap);

        logger.info("获取企业经营异常信息LdApiUtils.getResultStrByApiParam()的参数值：【{}】", apiParam);
        String resultStr = LdApiUtils.getResultStrByApiParam(apiParam);
        logger.info("获取企业经营异常信息LdApiUtils.getResultStrByApiParam()的返回值：【{}】", resultStr);
        return resultStr;
    }

    /**
     * 获取企业经营异常信息
     *
     * @param infoQryParam 经营异常信息查询参数
     * @return ApiResult
     */
    public static ApiResult getOperatingExceptionRotaApiResult(OperatExcepRotaQryParam infoQryParam) {
        // 获取企业经营异常信息
        String resultJsonStr = getOperatingExceptionRota(infoQryParam);
        // 转换成ApiResult
        ApiResult apiResult = LdApiUtils.convertToApiResult(resultJsonStr);
        // 是否有数据
        if (!LdApiUtils.matchData(apiResult)) {
            return apiResult;
        }

        List<Map<String, Object>> resultDataMapList = (List<Map<String, Object>>) apiResult.getResultData();
        List<OperatExcepRotaInfo> infoList = new ArrayList<>();
        for (Map<String, Object> map : resultDataMapList) {
            OperatExcepRotaInfo info = new OperatExcepRotaInfo();
            info.setInReason(ObjectUtils.defaultString(map.get("REASONIN")));
            info.setInDate(ObjectUtils.defaultString(map.get("DATEIN")));
            info.setInRegOrg(ObjectUtils.defaultString(map.get("REGORGIN")));
            info.setOutReason(ObjectUtils.defaultString(map.get("REASONOUT")));
            info.setOutDate(ObjectUtils.defaultString(map.get("DATEOUT")));
            info.setOutRegOrg(ObjectUtils.defaultString(map.get("REGORGOUT")));
            info.setIsMove(ObjectUtils.defaultString(map.get("ISMOVE")));
            infoList.add(info);
        }
        apiResult.setResultData(infoList);
        logger.debug("获取企业经营异常信息ApiResult值：【{}】", apiResult);
        return apiResult;
    }

    /**
     * 获取企业经营异常信息
     *
     * @param infoQryParam 经营异常信息查询参数
     * @return List<OperatExcepRotaInfo>
     */
    public static List<OperatExcepRotaInfo> getOperatingExceptionRotaList(OperatExcepRotaQryParam infoQryParam) {
        // 获取企业经营异常信息
        ApiResult apiResult = getOperatingExceptionRotaApiResult(infoQryParam);
        return LdApiUtils.matchData(apiResult) ? (List<OperatExcepRotaInfo>) apiResult.getResultData() : Collections.emptyList();
    }

    /**
     * 获取企业严重违法失信企业（黑名单）信息
     *
     * @param infoQryParam 严重违法失信企业（黑名单）信息查询参数
     * @return String
     */
    public static String getSeriousDishonestyInfo(SeriousDishonestyInfoQryParam infoQryParam) {
        Map<String, Object> paramMap = new HashMap<>(3);
        paramMap.put("entName", infoQryParam.getEntName());
        paramMap.put("pageNo", infoQryParam.getPageNo());
        paramMap.put("pageSize", infoQryParam.getPageSize());

        ApiParam apiParam = LdApiUtils.initApiParam(LdApiUtils.U_ID, ServiceNameConstant.GET_SERIOUS_DISHONESTY_INFO_METHOD);
        apiParam.setParams(paramMap);

        logger.info("获取企业严重违法失信企业（黑名单）信息LdApiUtils.getResultStrByApiParam()的参数值：【{}】", apiParam);
        String resultStr = LdApiUtils.getResultStrByApiParam(apiParam);
        logger.info("获取企业严重违法失信企业（黑名单）信息LdApiUtils.getResultStrByApiParam()的返回值：【{}】", resultStr);
        return resultStr;
    }

    /**
     * 获取企业严重违法失信企业（黑名单）信息
     *
     * @param infoQryParam 严重违法失信企业（黑名单）信息查询参数
     * @return ApiResult
     */
    public static ApiResult getSeriousDishonestyInfoApiResult(SeriousDishonestyInfoQryParam infoQryParam) {
        // 获取企业严重违法失信企业（黑名单）信息
        String resultJsonStr = getSeriousDishonestyInfo(infoQryParam);
        // 转换成ApiResult
        ApiResult apiResult = LdApiUtils.convertToApiResult(resultJsonStr);
        // 是否有数据
        if (!LdApiUtils.matchData(apiResult)) {
            return apiResult;
        }

        List<Map<String, Object>> resultDataMapList = (List<Map<String, Object>>) apiResult.getResultData();
        List<SeriousDishonestyInfo> infoList = new ArrayList<>();
        for (Map<String, Object> map : resultDataMapList) {
            SeriousDishonestyInfo info = new SeriousDishonestyInfo();
            info.setType(ObjectUtils.defaultString(map.get("TYPE")));
            info.setInReason(ObjectUtils.defaultString(map.get("LRYZWFYY")));
            info.setInDate(ObjectUtils.defaultString(map.get("LRRQ")));
            info.setInRegOrg(ObjectUtils.defaultString(map.get("ZCJDJG")));
            info.setOutReason(ObjectUtils.defaultString(map.get("YCYAWFYY")));
            info.setOutDate(ObjectUtils.defaultString(map.get("YCRQ")));
            info.setOutRegOrg(ObjectUtils.defaultString(map.get("YCYZWFJG")));
            infoList.add(info);
        }
        apiResult.setResultData(infoList);
        logger.debug("获取企业严重违法失信企业（黑名单）信息ApiResult值：【{}】", apiResult);
        return apiResult;
    }

    /**
     * 获取企业严重违法失信企业（黑名单）信息
     *
     * @param infoQryParam 严重违法失信企业（黑名单）信息查询参数
     * @return List<SeriousDishonestyInfo>
     */
    public static List<SeriousDishonestyInfo> getSeriousDishonestyInfoList(SeriousDishonestyInfoQryParam infoQryParam) {
        // 获取企业严重违法失信企业（黑名单）信息
        ApiResult apiResult = getSeriousDishonestyInfoApiResult(infoQryParam);
        return LdApiUtils.matchData(apiResult) ? (List<SeriousDishonestyInfo>) apiResult.getResultData() : Collections.emptyList();
    }

}
