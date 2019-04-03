package com.hanzhong.data.web.util.longdun.base;

import com.alibaba.fastjson.JSON;
import com.hanzhong.data.web.util.ObjectUtils;
import com.hanzhong.data.web.util.PropertiesUtils;
import com.hanzhong.data.web.util.longdun.base.constant.CmnConstant;
import com.hanzhong.data.web.util.longdun.base.model.ApiParam;
import com.hanzhong.data.web.util.longdun.base.model.ApiResult;
import com.hanzhong.data.web.util.longdun.base.model.OrderInfo;
import com.hanzhong.data.web.util.longdun.base.model.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ObjectInputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.PublicKey;
import java.util.Map;

/**
 * 龙盾API工具类
 *
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

    static {
        // 初始化公钥key
        publicKey = initPublicKey();
    }

    private LdApiUtils() {
    }

    /**
     * 初始化api参数
     *
     * @param userId      用户id
     * @param serviceName 服务名称
     * @return ApiParam
     */
    public static ApiParam initApiParam(String userId, String serviceName) {
        ApiParam apiParam = new ApiParam();
        apiParam.setUid(userId);
        apiParam.setService(serviceName);
        return apiParam;
    }

    /**
     * 通过api参数获取api返回值
     *
     * @param apiParam api参数
     * @return String json串 若出现异常，则返回null
     */
    public static String getResultStrByApiParam(ApiParam apiParam) {
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
     * 转换成ApiResult
     *
     * @param apiResultJsonStr api结果json串
     * @return ApiResult 若apiResultJsonStr为空，则返回null
     */
    public static ApiResult convertToApiResult(String apiResultJsonStr) {
        if (StringUtils.isBlank(apiResultJsonStr)) {
            logger.warn("apiResultJsonStr：【{}】为空", apiResultJsonStr);
            return null;
        }

        // 初始化ApiResult公共部分信息
        Map<String, Object> resultMap = JSON.parseObject(apiResultJsonStr, Map.class);
        ApiResult apiResult = convertToApiResult(resultMap);
        logger.info("apiResultJsonStr：【{}】，转换成ApiResult的值：【{}】", apiResultJsonStr, apiResult);
        return apiResult;
    }

    /**
     * 是否有数据
     *
     * @param apiResult ApiResult
     * @return boolean true：有数据
     */
    public static boolean matchData(ApiResult apiResult) {
        if (apiResult == null) {
            logger.warn("apiResult为空");
            return false;
        }

        Object resultData = apiResult.getResultData();
        return CmnConstant.MATCH_DATA_FLAG.equals(apiResult.getIsMatch()) && resultData != null;
    }

    /**
     * 转换成ApiResult
     *
     * @param resultMap 结果Map
     * @return ApiResult
     */
    private static ApiResult convertToApiResult(Map<String, Object> resultMap) {
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
        apiResult.setResultData(resultMap.get("RESULTDATA"));
        return apiResult;
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
            logger.error("公钥文件：【{}】，初始化公钥key出现异常：", PUBLIC_KEY_FILE_PATH, e);
        }
        return publicKey;
    }
}
