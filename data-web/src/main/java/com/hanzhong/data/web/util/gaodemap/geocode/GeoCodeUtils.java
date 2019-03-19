package com.hanzhong.data.web.util.gaodemap.geocode;

import com.alibaba.fastjson.JSON;
import com.hanzhong.data.web.util.gaodemap.constant.RequestStatusEnum;
import com.hanzhong.data.web.util.gaodemap.geocode.model.GcQryParam;
import com.hanzhong.data.web.util.gaodemap.geocode.model.GcQryResult;
import com.hanzhong.data.web.util.HttpUtils;
import com.hanzhong.data.web.util.PropertiesUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 *    
 *  @Description 高德地图工具类
 *  @Author   luqs   
 *  @Date 2018/8/20 12:10 
 *  @Version  V1.0   
 */
public class GeoCodeUtils {

    private static final Logger logger = LoggerFactory.getLogger(GeoCodeUtils.class);

    /**
     * key
     */
    private static final String GD_MAP_KEY = PropertiesUtils.getValue("gdMap_key");
    /**
     * 地理编码url
     */
    private static final String GEOCODE_URL = PropertiesUtils.getValue("gdMap_geocode_url");

    /**
     * 地理编码sig
     */
    private static final String GEOCODE_SIG = PropertiesUtils.getValue("gdMap_geocode_sig");

    private GeoCodeUtils() {
    }

    /**
     * 获取地理编码信息
     *
     * @param address 地址
     * @param city    地址所在的城市(城市的中文（如北京）、指定城市的中文全拼（beijing）、citycode（010）、adcode（110000）)
     * @return GcQryResult 若出现异常，则返回null
     */
    public static GcQryResult getGeoCodeInfo(String address, String city) {
        GcQryParam qryParam = new GcQryParam();
        try {
            qryParam.setKey(GD_MAP_KEY);
            qryParam.setAddress(GeoCodeUtils.handleSpecialCharInAddress(address));
            qryParam.setCity(StringUtils.defaultString(city));
            qryParam.setSig(GEOCODE_SIG);
            return GeoCodeUtils.getGeoCodeInfo(GEOCODE_URL, qryParam);
        } catch (Exception e) {
            logger.error("address：【{}】，qryParam：【{}】,getGeoCodeInfo()出现异常：", address, qryParam, e);
            return null;
        }
    }

    /**
     * 获取地理编码信息
     *
     * @param url      地理编码url
     * @param qryParam 查询参数
     * @return GeoCoderQryResult
     */
    public static GcQryResult getGeoCodeInfo(String url, GcQryParam qryParam) throws IOException {

        StringBuilder urlStrBuilder = new StringBuilder(url)
                .append("key=").append(qryParam.getKey())
                .append("&address=").append(qryParam.getAddress())
                .append("&city=").append(StringUtils.defaultString(qryParam.getCity()))
                .append("&batch=").append(StringUtils.defaultString(qryParam.getBatch()))
                .append("&sig=").append(qryParam.getSig())
                .append("&output=").append(StringUtils.defaultString(qryParam.getOutput()))
                .append("&callback=").append(StringUtils.defaultString(qryParam.getCallback()));

        String resultStr = HttpUtils.requestByGet(urlStrBuilder.toString());

        GcQryResult qryResult = JSON.parseObject(resultStr, GcQryResult.class);

        return qryResult;
    }

    /**
     * 查询是否成功
     *
     * @param gcQryResult 查询结果
     * @return boolean true:成功
     */
    public static boolean isSuccess(GcQryResult gcQryResult) {
        return gcQryResult != null && RequestStatusEnum.SUCCESS.getValue().equals(gcQryResult.getStatus());
    }

    /**
     * 处理地址中的特殊字符
     *
     * @param address 地址
     * @return String
     * @throws UnsupportedEncodingException
     */
    public static String handleSpecialCharInAddress(String address) throws UnsupportedEncodingException {
        address = StringUtils.deleteWhitespace(address);
        return URLEncoder.encode(address, "UTF-8");
    }
}
