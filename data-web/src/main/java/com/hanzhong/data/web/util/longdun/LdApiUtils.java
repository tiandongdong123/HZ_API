package com.hanzhong.data.web.util.longdun;

import com.alibaba.fastjson.JSON;
import com.hanzhong.data.web.util.ObjectUtils;
import com.hanzhong.data.web.util.PropertiesUtils;
import com.hanzhong.data.web.util.longdun.constant.CmnConstant;
import com.hanzhong.data.web.util.longdun.constant.ServiceNameConstant;
import com.hanzhong.data.web.util.longdun.model.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ObjectInputStream;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yifei
 * @date 2019/1/8
 */
public class LdApiUtils {

    private static final Logger logger = LoggerFactory.getLogger(LdApiUtils.class);

    /**
     * 服务基础url
     */
    private static final String BASE_SERVICE_URL = PropertiesUtils.getValueBySpecifiedFile(CmnConstant.LD_PROPERTIES_FILE, "base.service.url");
    /**
     * uid
     */
    public static final String U_ID = PropertiesUtils.getValueBySpecifiedFile(CmnConstant.LD_PROPERTIES_FILE, "uid");
    /**
     * 公钥key文件
     */
    private static final String PUBLIC_KEY_FILE_PATH = PropertiesUtils.getValueBySpecifiedFile(CmnConstant.LD_PROPERTIES_FILE, "publicKey.file.path");
    /**
     * 公钥key
     */
    private static PublicKey publicKey;
    /**
     * 更新数据包下载url
     */
    public static final String CHANGE_DATA_PACKAGE_DOWNLOAD_URL = PropertiesUtils.getValueBySpecifiedFile(CmnConstant.LD_PROPERTIES_FILE, "changeDataPackage.download.url");

    static {
        // 初始化公钥key
        publicKey = initPublicKey();
    }

    private LdApiUtils() {
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

        ApiParam apiParam = initApiParam(LdApiUtils.U_ID, ServiceNameConstant.GET_ENT_BY_KEY_WORD_METHOD);
        apiParam.setParams(paramMap);

        logger.info("getEntByKeyword()参数值：【{}】", apiParam);
        String resultStr = getResultStrByApiParam(apiParam);
        logger.info("getEntByKeyword()返回值：【{}】", resultStr);
        return resultStr;
    }

    /**
     * 根据关键字获取企业列表信息
     *
     * @param keyWordQryParam 关键字查询参数
     * @return ApiResult
     */
    public static ApiResult getEntApiResultByKeyword(EntKeyWordQryParam keyWordQryParam) {
        String resultJsonStr = getEntByKeyword(keyWordQryParam);
        if (StringUtils.isBlank(resultJsonStr)) {
            return null;
        }

        // 初始化ApiResult公共部分信息
        Map<String, Object> resultMap = JSON.parseObject(resultJsonStr, Map.class);
        ApiResult apiResult = initCmnInfoOfApiResult(resultMap);

        if (!CmnConstant.MATCH_DATA_FLAG.equals(apiResult.getIsMatch())) {
            logger.warn("keyWordQryParam：【{}】，resultJsonStr：【{}】，未查询到企业信息", keyWordQryParam, resultJsonStr);
            return apiResult;
        }

        // 结果数据
        List<Map<String, Object>> resultDataMapList = (List<Map<String, Object>>) resultMap.get("RESULTDATA");
        if (resultDataMapList == null || resultDataMapList.isEmpty()) {
            return apiResult;
        }

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
     * 获取企业登记信息
     *
     * @param registerInfoQryParam 企业查询参数
     * @return String
     */
    public static String getRegisterInfo(RegisterInfoQryParam registerInfoQryParam) {
        Map<String, String> paramMap = new HashMap<>(1);
        paramMap.put("entName", registerInfoQryParam.getEntName());

        ApiParam apiParam = initApiParam(LdApiUtils.U_ID, ServiceNameConstant.GET_REGISTER_INFO_METHOD);
        apiParam.setParams(paramMap);

        logger.info("getRegisterInfo()参数值：【{}】", apiParam);
        String resultStr = getResultStrByApiParam(apiParam);
        logger.info("getRegisterInfo()返回值：【{}】", resultStr);
        return resultStr;
    }

    /**
     * 获取企业登记信息
     *
     * @param registerInfoQryParam 企业查询参数
     * @return QryResult
     */
    public static ApiResult getRegisterInfoApiResult(RegisterInfoQryParam registerInfoQryParam) {
        String resultJsonStr = getRegisterInfo(registerInfoQryParam);
        if (StringUtils.isBlank(resultJsonStr)) {
            return null;
        }

        // 初始化ApiResult公共部分信息
        Map<String, Object> resultMap = JSON.parseObject(resultJsonStr, Map.class);
        ApiResult apiResult = initCmnInfoOfApiResult(resultMap);

        if (!CmnConstant.MATCH_DATA_FLAG.equals(apiResult.getIsMatch())) {
            logger.warn("registerInfoQryParam：【{}】，resultJsonStr：【{}】，未查询到企业信息", registerInfoQryParam, resultJsonStr);
            return apiResult;
        }

        // 结果数据
        List<Map<String, Object>> resultDataMapList = (List<Map<String, Object>>) resultMap.get("RESULTDATA");
        if (resultDataMapList == null || resultDataMapList.isEmpty()) {
            return apiResult;
        }

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

        ApiParam apiParam = initApiParam(LdApiUtils.U_ID, ServiceNameConstant.GET_SHARE_HOLDER_INFO_METHOD);
        apiParam.setParams(paramMap);

        logger.info("getShareHolderInfo()参数值：【{}】", apiParam);
        String resultStr = getResultStrByApiParam(apiParam);
        logger.info("getShareHolderInfo()返回值：【{}】", resultStr);
        return resultStr;
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

        ApiParam apiParam = initApiParam(LdApiUtils.U_ID, ServiceNameConstant.GET_INV_ABROAD_INFO_METHOD);
        apiParam.setParams(paramMap);

        logger.info("getInvestmentAbroadInfo()参数值：【{}】", apiParam);
        String resultStr = getResultStrByApiParam(apiParam);
        logger.info("getInvestmentAbroadInfo()返回值：【{}】", resultStr);
        return resultStr;
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

        ApiParam apiParam = initApiParam(LdApiUtils.U_ID, ServiceNameConstant.GET_BRANCH_INFO_METHOD);
        apiParam.setParams(paramMap);

        logger.info("getBranchInfo()参数值：【{}】", apiParam);
        String resultStr = getResultStrByApiParam(apiParam);
        logger.info("getBranchInfo()返回值：【{}】", resultStr);
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

        ApiParam apiParam = initApiParam(LdApiUtils.U_ID, ServiceNameConstant.GET_REGISTER_CHANGE_INFO_METHOD);
        apiParam.setParams(paramMap);

        logger.info("getRegisterChangeInfo()参数值：【{}】", apiParam);
        String resultStr = getResultStrByApiParam(apiParam);
        logger.info("getRegisterChangeInfo()返回值：【{}】", resultStr);
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

        ApiParam apiParam = initApiParam(LdApiUtils.U_ID, ServiceNameConstant.GET_MAIN_MANAGER_INFO_METHOD);
        apiParam.setParams(paramMap);

        logger.info("getMainManagerInfo()参数值：【{}】", apiParam);
        String resultStr = getResultStrByApiParam(apiParam);
        logger.info("getMainManagerInfo()返回值：【{}】", resultStr);
        return resultStr;
    }

    /**
     * 获取企业商品信息
     *
     * @param goodsInfoQryParam 商品信息查询参数
     * @return String
     */
    public static String getGoodsInfo(GoodsInfoQryParam goodsInfoQryParam) {
        Map<String, Object> paramMap = new HashMap<>(3);
        paramMap.put("entName", goodsInfoQryParam.getEntName());
        paramMap.put("pageNo", goodsInfoQryParam.getPageNo());
        paramMap.put("pageSize", goodsInfoQryParam.getPageSize());

        ApiParam apiParam = initApiParam(LdApiUtils.U_ID, ServiceNameConstant.GET_GOODS_INFO_METHOD);
        apiParam.setParams(paramMap);

        logger.info("getGoodsInfo()参数值：【{}】", apiParam);
        String resultStr = getResultStrByApiParam(apiParam);
        logger.info("getGoodsInfo()返回值：【{}】", resultStr);
        return resultStr;
    }

    /**
     * 获取变更数据包列表
     *
     * @param listQryParam 变更数据包查询参数
     * @return String
     */
    public static String getChangeDataPackageList(ChangeDataPkgListQryParam listQryParam) {
        Map<String, Object> paramMap = new HashMap<>(5);
        paramMap.put("actionType", listQryParam.getActionTypeEnum().getValue());
        paramMap.put("startTime", listQryParam.getStartTime());
        paramMap.put("endTime", listQryParam.getEndTime());
        paramMap.put("pageNo", listQryParam.getPageNo());
        paramMap.put("pageSize", listQryParam.getPageSize());

        ApiParam apiParam = initApiParam(LdApiUtils.U_ID, ServiceNameConstant.CHANGE_DATA_PACKAGE_LIST_METHOD);
        apiParam.setParams(paramMap);

        logger.info("changeDataPackageList()参数值：【{}】", apiParam);
        String resultStr = getResultStrByApiParam(apiParam);
        logger.info("changeDataPackageList()返回值：【{}】", resultStr);
        return resultStr;
    }

    /**
     * 获取变更数据包列表
     *
     * @param listQryParam 关键字查询参数
     * @return ApiResult
     */
    public static ApiResult getChangeDataPackageListApiResult(ChangeDataPkgListQryParam listQryParam) {
        // 获取变更数据包列表
        String resultJsonStr = getChangeDataPackageList(listQryParam);
        if (StringUtils.isBlank(resultJsonStr)) {
            return null;
        }

        // 初始化ApiResult公共部分信息
        Map<String, Object> resultMap = JSON.parseObject(resultJsonStr, Map.class);
        ApiResult apiResult = initCmnInfoOfApiResult(resultMap);

        if (!CmnConstant.MATCH_DATA_FLAG.equals(apiResult.getIsMatch())) {
            logger.warn("listQryParam：【{}】，resultJsonStr：【{}】，未查询到变更数据包列表", listQryParam, resultJsonStr);
            return apiResult;
        }

        // 结果数据
        List<Map<String, Object>> resultDataMapList = (List<Map<String, Object>>) resultMap.get("RESULTDATA");
        if (resultDataMapList == null || resultDataMapList.isEmpty()) {
            return apiResult;
        }

        List<ChangeDataPkgInfo> infoList = new ArrayList<>();
        for (Map<String, Object> map : resultDataMapList) {
            ChangeDataPkgInfo info = new ChangeDataPkgInfo();
            info.setDataPackId(ObjectUtils.defaultString(map.get("DATAPACKID")));
            info.setDataPackName(ObjectUtils.defaultString(map.get("DATAPACKNAME")));
            info.setDataPackTime(ObjectUtils.defaultLong(map.get("DATAPACKTIME")));
            info.setRecordCount(ObjectUtils.defaultInt(map.get("RECORDCOUNT")));
            info.setFileSize(ObjectUtils.defaultInt(map.get("FILESIZE")));
            info.setPassword(ObjectUtils.defaultString(map.get("PASSWORD")));
            infoList.add(info);
        }
        apiResult.setResultData(infoList);
        return apiResult;
    }

    /**
     * 获取变更数据包列表
     *
     * @param apiResult api结果
     * @return List<ChangeDataPkgInfo>
     */
    public static List<ChangeDataPkgInfo> getChangeDataPackageList(ApiResult apiResult) {
        return apiResult == null ? null : (List<ChangeDataPkgInfo>) apiResult.getResultData();
    }

    /**
     * 变更数据包回馈状态
     *
     * @param finishParam 回馈参数
     * @return String
     */
    public static String monitorDataPackageFinish(DataPackageFinishParam finishParam) {
        Map<String, Object> paramMap = new HashMap<>(1);
        paramMap.put("dataPackId", finishParam.getDataPackId());

        ApiParam apiParam = initApiParam(LdApiUtils.U_ID, ServiceNameConstant.MONITOR_DATA_PACKAGE_FINISH_METHOD);
        apiParam.setParams(paramMap);

        logger.info("monitorDataPackageFinish()参数值：【{}】", apiParam);
        String resultStr = getResultStrByApiParam(apiParam);
        logger.info("monitorDataPackageFinish()返回值：【{}】", resultStr);
        return resultStr;
    }

    /**
     * 变更数据包回馈状态
     *
     * @param finishParam 回馈参数
     * @return ApiResult
     */
    public static ApiResult monitorDataPackageFinishApiResult(DataPackageFinishParam finishParam) {
        // 变更数据包回馈状态
        String resultJsonStr = monitorDataPackageFinish(finishParam);
        if (StringUtils.isBlank(resultJsonStr)) {
            return null;
        }

        // 初始化ApiResult公共部分信息
        Map<String, Object> resultMap = JSON.parseObject(resultJsonStr, Map.class);
        ApiResult apiResult = initCmnInfoOfApiResult(resultMap);

        if (!CmnConstant.MATCH_DATA_FLAG.equals(apiResult.getIsMatch())) {
            logger.warn("finishParam：【{}】，resultJsonStr：【{}】，变更数据包回馈状态失败", finishParam, resultJsonStr);
            return apiResult;
        }

        // 结果数据
        List<Map<String, Object>> resultDataMapList = (List<Map<String, Object>>) resultMap.get("RESULTDATA");
        if (resultDataMapList == null || resultDataMapList.isEmpty()) {
            return apiResult;
        }

        boolean monitorFlag = false;
        for (Map<String, Object> map : resultDataMapList) {
            monitorFlag = Boolean.parseBoolean(ObjectUtils.defaultString(map.get("SUCCESS")));
        }
        apiResult.setResultData(monitorFlag);
        return apiResult;
    }

    /**
     * 变更数据包回馈状态
     *
     * @param apiResult api结果
     * @return List<ChangeDataPkgInfo>
     */
    public static boolean monitorDataPackageFinish(ApiResult apiResult) {
        return apiResult == null ? Boolean.FALSE : (boolean) apiResult.getResultData();
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

        ApiParam apiParam = initApiParam(LdApiUtils.U_ID, ServiceNameConstant.GET_ENT_ANN_REPORT_SOCIAL_SECURITY_INFO_METHOD);
        apiParam.setParams(paramMap);

        logger.info("getEntAnnReportSocialSecurityInfo()参数值：【{}】", apiParam);
        String resultStr = getResultStrByApiParam(apiParam);
        logger.info("getEntAnnReportSocialSecurityInfo()返回值：【{}】", resultStr);
        return resultStr;
    }

    /**
     * 获取上市公司基本信息
     *
     * @param infoV2QryParam 上市公司基本信息查询参数
     * @return String
     */
    public static String getListedCompanyBaseMessageInfoV2(ListedCompanyBaseMsgInfoV2QryParam infoV2QryParam) {
        Map<String, Object> paramMap = new HashMap<>(1);
        paramMap.put("entName", infoV2QryParam.getEntName());

        ApiParam apiParam = initApiParam(LdApiUtils.U_ID, ServiceNameConstant.GET_LISTED_COMPANY_BASE_MESSAGE_INFO_V2_METHOD);
        apiParam.setParams(paramMap);

        logger.info("getListedCompanyBaseMessageInfoV2()参数值：【{}】", apiParam);
        String resultStr = getResultStrByApiParam(apiParam);
        logger.info("getListedCompanyBaseMessageInfoV2()返回值：【{}】", resultStr);
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

        ApiParam apiParam = initApiParam(LdApiUtils.U_ID, ServiceNameConstant.GET_ENT_ACTUAL_CONTROLLER_METHOD);
        apiParam.setParams(paramMap);

        logger.info("getEntActualContoller()参数值：【{}】", apiParam);
        String resultStr = getResultStrByApiParam(apiParam);
        logger.info("getEntActualContoller()返回值：【{}】", resultStr);
        return resultStr;
    }

    /**
     * 通过api参数获取api返回值
     *
     * @param apiParam api参数
     * @return String json串 若出现异常，则返回null
     */
    private static String getResultStrByApiParam(ApiParam apiParam) {
        long startTimeMillis = System.currentTimeMillis();
        logger.debug("通过api参数获取api返回值的参数值：【{}】", apiParam);
        try {
            // 初始化公钥key
            PublicKey publicKey = initPublicKey();
            // 加密
            Map<String, Object> map = LdAuthUtils.encode(JSON.toJSONString(apiParam), publicKey);
            // url编码
            String data = URLEncoder.encode(JSON.toJSONString(map), LdAuthUtils.UTF_8);
            // 发送post请求
            String result = LdHttpUtils.sendPost(BASE_SERVICE_URL, "uid=" + U_ID + "&data=" + data);
            // url解码
            result = URLDecoder.decode(result, LdAuthUtils.UTF_8);
            // 反序列化
            Map<String, Object> resultMap = JSON.parseObject(result, Map.class);
            // 解密
            String resultValue = LdAuthUtils.decode(resultMap, publicKey);
            logger.debug("通过api参数获取api返回值的返回值：【{}】，耗时：【{}】ms", resultValue, System.currentTimeMillis() - startTimeMillis);
            return resultValue;
        } catch (Exception e) {
            logger.error("apiParam：【{}】，通过api参数获取api返回值发送POST请求出现异常：", apiParam, e);
            return null;
        }
    }

    /**
     * 初始化公钥key
     *
     * @return ObjectInputStream
     */
    private static PublicKey initPublicKey() {
        if (publicKey != null) {
            return publicKey;
        }

        try (
                ObjectInputStream objectInputStream = new ObjectInputStream(LdApiUtils.class.getClassLoader().getResourceAsStream(PUBLIC_KEY_FILE_PATH))
        ) {
            publicKey = (PublicKey) objectInputStream.readObject();
        } catch (Exception e) {
            logger.error("初始化公钥key出现异常：", e);
        }
        return publicKey;
    }

    /**
     * 初始化api参数
     *
     * @param userId      用户id
     * @param serviceName 服务名称
     * @return ApiParam
     */
    private static ApiParam initApiParam(String userId, String serviceName) {
        ApiParam apiParam = new ApiParam();
        apiParam.setUid(userId);
        apiParam.setService(serviceName);
        return apiParam;
    }

    /**
     * 初始化ApiResult公共部分信息
     *
     * @param resultMap 结果Map
     * @return ApiResult
     */
    private static ApiResult initCmnInfoOfApiResult(Map<String, Object> resultMap) {
        ApiResult apiResult = new ApiResult();
        // 用户id
        apiResult.setuId(ObjectUtils.defaultString(resultMap.get("UID")));
        // 订单是否正常
        apiResult.setIsUsual(ObjectUtils.defaultString(resultMap.get("ISUSUAL")));
        // 是否匹配到企业
        apiResult.setIsMatch(ObjectUtils.defaultString(resultMap.get("ISMATCH")));
        // 版本号
        apiResult.setVersion(ObjectUtils.defaultString(resultMap.get("VERSION")));
        // 订单信息
        Map<String, Object> orderInfoMap = (Map<String, Object>) resultMap.get("ORDERINFO");
        if (orderInfoMap != null && !orderInfoMap.isEmpty()) {
            OrderInfo orderInfo = new OrderInfo();
            orderInfo.setOrderNo(ObjectUtils.defaultString(orderInfoMap.get("ORDERNO")));
            orderInfo.setOrderDate(ObjectUtils.defaultString(orderInfoMap.get("ORDERDATE")));
            apiResult.setOrderInfo(orderInfo);
        }
        // 分页信息
        Map<String, Object> pageInfoMap = (Map<String, Object>) resultMap.get("PAGEINFO");
        if (pageInfoMap != null && !pageInfoMap.isEmpty()) {
            PageInfo pageInfo = new PageInfo();
            pageInfo.setTotalConut(ObjectUtils.defaultInt(pageInfoMap.get("TOTAL_COUNT")));
            pageInfo.setTotalPage(ObjectUtils.defaultInt(pageInfoMap.get("TOTAL_PAGE")));
            pageInfo.setCurrentPage(ObjectUtils.defaultInt(pageInfoMap.get("CURRENT_PAGE")));
            apiResult.setPageInfo(pageInfo);
        }
        return apiResult;
    }
}
