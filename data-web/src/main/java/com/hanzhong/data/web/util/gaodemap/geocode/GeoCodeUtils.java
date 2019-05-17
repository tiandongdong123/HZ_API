package com.hanzhong.data.web.util.gaodemap.geocode;

import com.alibaba.fastjson.JSON;
import com.hanzhong.data.web.util.HttpUtils;
import com.hanzhong.data.web.util.PropertiesUtils;
import com.hanzhong.data.web.util.gaodemap.base.GdMapUtils;
import com.hanzhong.data.web.util.gaodemap.base.constant.RequestStatusEnum;
import com.hanzhong.data.web.util.gaodemap.geocode.model.GeoCodeQryParam;
import com.hanzhong.data.web.util.gaodemap.geocode.model.GeoCodeQryResult;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 *    
 *  @Description 高德地图地理编码工具类
 *  @Author   luqs   
 *  @Date 2018/8/20 12:10 
 *  @Version  V1.0   
 */
public class GeoCodeUtils {

    private static final Logger logger = LoggerFactory.getLogger(GeoCodeUtils.class);

    /**
     * 地理编码url
     */
    private static final String GD_MAP_GEO_CODE_URL = PropertiesUtils.getValue("gdMap_geoCode_url");
    /**
     * 地理编码sig
     */
    private static final String GD_MAP_GEO_CODE_SIG = PropertiesUtils.getValue("gdMap_geoCode_sig");

    private GeoCodeUtils() {
    }

    /**
     * 获取地理编码信息
     *
     * @param address 地址
     * @param city    地址所在的城市(城市的中文（如北京）、指定城市的中文全拼（beijing）、citycode（010）、adcode（110000）)
     * @return GcQryResult 若出现异常，则返回null
     */
    public static GeoCodeQryResult getGeoCodeInfo(String address, String city) {
        GeoCodeQryParam qryParam = new GeoCodeQryParam();
        try {
            qryParam.setKey(GdMapUtils.GD_MAP_KEY);
            qryParam.setAddress(GdMapUtils.handleSpecialCharInAddress(address));
            qryParam.setCity(StringUtils.defaultString(city));
            qryParam.setSig(GD_MAP_GEO_CODE_SIG);
            return GeoCodeUtils.getGeoCodeInfo(qryParam);
        } catch (Exception e) {
            logger.error("address：【{}】，qryParam：【{}】,getGeoCodeInfo()出现异常：", address, qryParam, e);
            return null;
        }
    }

    /**
     * 获取地理编码信息
     *
     * @param qryParam 查询参数
     * @return GeoCoderQryResult
     */
    public static GeoCodeQryResult getGeoCodeInfo(GeoCodeQryParam qryParam) throws IOException {
        StringBuilder urlStrBuilder = new StringBuilder(GD_MAP_GEO_CODE_URL + "?")
                .append("key=").append(qryParam.getKey())
                .append("&address=").append(qryParam.getAddress())
                .append("&city=").append(StringUtils.defaultString(qryParam.getCity()))
                .append("&batch=").append(StringUtils.defaultString(qryParam.getBatch()))
                .append("&sig=").append(qryParam.getSig())
                .append("&output=").append(StringUtils.defaultString(qryParam.getOutput()))
                .append("&callback=").append(StringUtils.defaultString(qryParam.getCallback()));
        logger.debug("获取地理编码信息的url：【{}】", urlStrBuilder);
        String resultStr = HttpUtils.requestByGet(urlStrBuilder.toString());
        logger.debug("获取地理编码信息的结果json：【{}】", resultStr);
        GeoCodeQryResult qryResult = JSON.parseObject(resultStr, GeoCodeQryResult.class);
        logger.debug("获取地理编码信息的json转对象结果：【{}】", qryResult);
        return qryResult;
    }

    /**
     * 查询是否成功
     *
     * @param geoCodeQryResult 查询结果
     * @return boolean true:成功
     */
    public static boolean isSuccess(GeoCodeQryResult geoCodeQryResult) {
        return geoCodeQryResult != null && RequestStatusEnum.SUCCESS.getValue().equals(geoCodeQryResult.getStatus());
    }

}
