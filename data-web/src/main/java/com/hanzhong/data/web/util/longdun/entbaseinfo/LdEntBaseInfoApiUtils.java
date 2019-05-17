package com.hanzhong.data.web.util.longdun.entbaseinfo;

import com.hanzhong.data.web.util.ObjectUtils;
import com.hanzhong.data.web.util.longdun.base.LdApiUtils;
import com.hanzhong.data.web.util.longdun.base.model.ApiParam;
import com.hanzhong.data.web.util.longdun.base.model.ApiResult;
import com.hanzhong.data.web.util.longdun.entbaseinfo.constant.ServiceNameConstant;
import com.hanzhong.data.web.util.longdun.entbaseinfo.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  
 *  @Description 企业基本信息API工具类
 *  @Author   luqs   
 *  @Date 2019/4/2 19:51 
 *  @Version  V1.0   
 */
public class LdEntBaseInfoApiUtils {

    private static final Logger logger = LoggerFactory.getLogger(LdEntBaseInfoApiUtils.class);

    private LdEntBaseInfoApiUtils() {
    }

    /**
     * 根据关键字获取企业列表信息
     *
     * @param keyWordQryParam 关键字查询参数
     * @return String
     */
    public static String getEntByKeyword(EntKeyWordQryParam keyWordQryParam) {
        Map<String, String> paramMap = new HashMap<>(2);
        paramMap.put("keyword", keyWordQryParam.getKeyword());
        paramMap.put("type", keyWordQryParam.getKeyWordTypeEnum().getValue());

        ApiParam apiParam = LdApiUtils.initApiParam(LdApiUtils.U_ID, ServiceNameConstant.GET_ENT_BY_KEY_WORD_METHOD);
        apiParam.setParams(paramMap);
        logger.info("根据关键字获取企业列表信息LdApiUtils.getResultStrByApiParam()的参数值：【{}】", apiParam);
        String resultStr = LdApiUtils.getResultStrByApiParam(apiParam);
        logger.info("根据关键字获取企业列表信息LdApiUtils.getResultStrByApiParam()的返回值：【{}】", resultStr);
        return resultStr;
    }

    /**
     * 根据关键字获取企业列表信息
     *
     * @param keyWordQryParam 关键字查询参数
     * @return ApiResult
     */
    public static ApiResult getEntByKeywordApiResult(EntKeyWordQryParam keyWordQryParam) {
        // 根据关键字获取企业列表信息
        String resultJsonStr = getEntByKeyword(keyWordQryParam);
        // 转换成ApiResult
        ApiResult apiResult = LdApiUtils.convertToApiResult(resultJsonStr);
        // 是否有数据
        if (!LdApiUtils.matchData(apiResult)) {
            return apiResult;
        }

        List<Map<String, Object>> resultDataMapList = (List<Map<String, Object>>) apiResult.getResultData();
        List<EntInfo> infoList = new ArrayList<>();
        for (Map<String, Object> map : resultDataMapList) {
            EntInfo info = new EntInfo();
            info.setEntName(ObjectUtils.defaultString(map.get("ENTNAME")));
            info.setOldName(ObjectUtils.defaultString(map.get("OLDNAME")));
            info.setShxydm(ObjectUtils.defaultString(map.get("SHXYDM")));
            info.setFrdb(ObjectUtils.defaultString(map.get("FRDB")));
            info.setEntStatus(ObjectUtils.defaultString(map.get("ENTSTATUS")));
            info.setRegCap(ObjectUtils.defaultString(map.get("REGCAP")));
            info.setDom(ObjectUtils.defaultString(map.get("DOM")));
            info.setEsDate(ObjectUtils.defaultString(map.get("ESDATE")));
            info.setEmail(ObjectUtils.defaultString(map.get("EMAIL")));
            info.setTel(ObjectUtils.defaultString(map.get("ENDDATE")));
            info.setWebUrl(ObjectUtils.defaultString(map.get("WEBURL")));
            info.setIsJyzxgs(ObjectUtils.defaultString(map.get("IS_JYZXGS")));
            infoList.add(info);
        }
        apiResult.setResultData(infoList);
        return apiResult;
    }

    /**
     * 根据关键字获取企业列表信息
     *
     * @param keyWordQryParam 关键字查询参数
     * @return EntInfo
     */
    public static EntInfo getEntInfoByKeyword(EntKeyWordQryParam keyWordQryParam) {
        // 根据关键字获取企业列表信息
        ApiResult apiResult = getEntByKeywordApiResult(keyWordQryParam);
        // 是否有数据
        if (!LdApiUtils.matchData(apiResult)) {
            return null;
        }

        // 判断结果数据是否为空
        List<EntInfo> entInfoList = (List<EntInfo>) apiResult.getResultData();
        if (entInfoList == null || entInfoList.isEmpty()) {
            return null;
        }
        return entInfoList.get(0);
    }

    /**
     * 获取企业登记信息
     *
     * @param registerInfoQryParam 企业查询参数
     * @return String
     */
    public static String getRegisterInfo(RegisterInfoQryParam registerInfoQryParam) {
        Map<String, String> paramMap = new HashMap<>(1);
        paramMap.put("entName", registerInfoQryParam.getEntName());

        ApiParam apiParam = LdApiUtils.initApiParam(LdApiUtils.U_ID, ServiceNameConstant.GET_REGISTER_INFO_METHOD);
        apiParam.setParams(paramMap);

        logger.info("获取企业登记信息LdApiUtils.getResultStrByApiParam()的参数值：【{}】", apiParam);
        String resultStr = LdApiUtils.getResultStrByApiParam(apiParam);
        logger.info("获取企业登记信息LdApiUtils.getResultStrByApiParam()的返回值：【{}】", resultStr);
        return resultStr;
    }

    /**
     * 获取企业登记信息
     *
     * @param registerInfoQryParam 企业查询参数
     * @return QryResult
     */
    public static ApiResult getRegisterInfoApiResult(RegisterInfoQryParam registerInfoQryParam) {
        // 获取企业登记信息
        String resultJsonStr = getRegisterInfo(registerInfoQryParam);
        // 转换成ApiResult
        ApiResult apiResult = LdApiUtils.convertToApiResult(resultJsonStr);
        // 是否有数据
        if (!LdApiUtils.matchData(apiResult)) {
            return apiResult;
        }

        List<Map<String, Object>> resultDataMapList = (List<Map<String, Object>>) apiResult.getResultData();
        List<RegisterInfo> infoList = new ArrayList<>();
        for (Map<String, Object> map : resultDataMapList) {
            RegisterInfo info = new RegisterInfo();
            info.setEntName(ObjectUtils.defaultString(map.get("ENTNAME")));
            info.setOldName(ObjectUtils.defaultString(map.get("OLDNAME")));
            info.setEngName(ObjectUtils.defaultString(map.get("ENGNAME")));
            info.setShxydm(ObjectUtils.defaultString(map.get("SHXYDM")));
            info.setOrgId(ObjectUtils.defaultString(map.get("ORGID")));
            info.setFrdb(ObjectUtils.defaultString(map.get("FRDB")));
            info.setEntType(ObjectUtils.defaultString(map.get("ENTTYPE")));
            info.setEntStatus(ObjectUtils.defaultString(map.get("ENTSTATUS")));
            info.setRegCap(BigDecimal.valueOf(ObjectUtils.defaultDouble(map.get("REGCAP"))));
            info.setRegCapCur(ObjectUtils.defaultString(map.get("REGCAPCUR")));
            info.setDom(ObjectUtils.defaultString(map.get("DOM")));
            info.setJwd(ObjectUtils.defaultString(map.get("JWD")));
            info.setRegOrg(ObjectUtils.defaultString(map.get("REGORG")));
            info.setProvince(ObjectUtils.defaultString(map.get("PROVINCE")));
            info.setIndustryCode(ObjectUtils.defaultString(map.get("INDUSTRY_CODE")));
            info.setIndustry(ObjectUtils.defaultString(map.get("INDUSTRY")));
            info.setOpScope(ObjectUtils.defaultString(map.get("OPSCOPE")));
            info.setOpFrom(ObjectUtils.defaultString(map.get("OPFROM")));
            info.setOpTo(ObjectUtils.defaultString(map.get("OPTO")));
            info.setEsDate(ObjectUtils.defaultString(map.get("ESDATE")));
            info.setApprDate(ObjectUtils.defaultString(map.get("APPRDATE")));
            info.setRevDate(ObjectUtils.defaultString(map.get("REVDATE")));
            info.setCanDate(ObjectUtils.defaultString(map.get("CANDATE")));
            info.setEndDate(ObjectUtils.defaultString(map.get("ENDDATE")));
            info.setWebSite(ObjectUtils.defaultString(map.get("WEBSITE")));
            infoList.add(info);
        }
        apiResult.setResultData(infoList);
        return apiResult;
    }

    /**
     * 获取企业登记信息
     *
     * @param registerInfoQryParam 企业查询参数
     * @return QryResult
     */
    public static RegisterInfo getRegInfo(RegisterInfoQryParam registerInfoQryParam) {
        // 根据关键字获取企业列表信息
        ApiResult apiResult = getRegisterInfoApiResult(registerInfoQryParam);
        // 是否有数据
        if (!LdApiUtils.matchData(apiResult)) {
            return null;
        }

        // 判断结果数据是否为空
        List<RegisterInfo> registerInfoList = (List<RegisterInfo>) apiResult.getResultData();
        if (registerInfoList == null || registerInfoList.isEmpty()) {
            return null;
        }
        return registerInfoList.get(0);
    }

    /**
     * 获取企业股东及出资信息
     *
     * @param shareHolderInfoQryParam 股东信息查询参数
     * @return String
     */
    public static String getShareHolderInfo(ShareHolderInfoQryParam shareHolderInfoQryParam) {
        Map<String, Object> paramMap = new HashMap<>(3);
        paramMap.put("entName", shareHolderInfoQryParam.getEntName());
        paramMap.put("pageNo", shareHolderInfoQryParam.getPageNo());
        paramMap.put("pageSize", shareHolderInfoQryParam.getPageSize());

        ApiParam apiParam = LdApiUtils.initApiParam(LdApiUtils.U_ID, ServiceNameConstant.GET_SHARE_HOLDER_INFO_METHOD);
        apiParam.setParams(paramMap);

        logger.info("获取企业股东及出资信息LdApiUtils.getResultStrByApiParam()的参数值：【{}】", apiParam);
        String resultStr = LdApiUtils.getResultStrByApiParam(apiParam);
        logger.info("获取企业股东及出资信息LdApiUtils.getResultStrByApiParam()的返回值：【{}】", resultStr);
        return resultStr;
    }

    /**
     * 获取企业变更信息
     *
     * @param regChangeInfoQryParam 变更信息查询参数
     * @return String
     */
    public static String getRegisterChangeInfo(RegChangeInfoQryParam regChangeInfoQryParam) {
        Map<String, Object> paramMap = new HashMap<>(3);
        paramMap.put("entName", regChangeInfoQryParam.getEntName());
        paramMap.put("pageNo", regChangeInfoQryParam.getPageNo());
        paramMap.put("pageSize", regChangeInfoQryParam.getPageSize());

        ApiParam apiParam = LdApiUtils.initApiParam(LdApiUtils.U_ID, ServiceNameConstant.GET_REGISTER_CHANGE_INFO_METHOD);
        apiParam.setParams(paramMap);

        logger.info("获取企业变更信息LdApiUtils.getResultStrByApiParam()的参数值：【{}】", apiParam);
        String resultStr = LdApiUtils.getResultStrByApiParam(apiParam);
        logger.info("获取企业变更信息LdApiUtils.getResultStrByApiParam()的返回值：【{}】", resultStr);
        return resultStr;
    }

    /**
     * 获取企业主要管理人员信息
     *
     * @param mainManagerInfoQryParam 主要管理人员信息查询参数
     * @return String
     */
    public static String getMainManagerInfo(MainManagerInfoQryParam mainManagerInfoQryParam) {
        Map<String, Object> paramMap = new HashMap<>(3);
        paramMap.put("entName", mainManagerInfoQryParam.getEntName());
        paramMap.put("pageNo", mainManagerInfoQryParam.getPageNo());
        paramMap.put("pageSize", mainManagerInfoQryParam.getPageSize());

        ApiParam apiParam = LdApiUtils.initApiParam(LdApiUtils.U_ID, ServiceNameConstant.GET_MAIN_MANAGER_INFO_METHOD);
        apiParam.setParams(paramMap);

        logger.info(" 获取企业主要管理人员信息LdApiUtils.getResultStrByApiParam()的参数值：【{}】", apiParam);
        String resultStr = LdApiUtils.getResultStrByApiParam(apiParam);
        logger.info(" 获取企业主要管理人员信息LdApiUtils.getResultStrByApiParam()的返回值：【{}】", resultStr);
        return resultStr;
    }
}
