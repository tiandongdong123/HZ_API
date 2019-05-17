package com.hanzhong.data.web.util.gaodemap.geocode;

import com.alibaba.fastjson.JSON;
import com.hanzhong.data.web.util.HttpUtils;
import com.hanzhong.data.web.util.PropertiesUtils;
import com.hanzhong.data.web.util.gaodemap.base.GdMapUtils;
import com.hanzhong.data.web.util.gaodemap.geocode.model.ReGeoCodeQryParam;
import com.hanzhong.data.web.util.gaodemap.geocode.model.ReGeoCodeQryResult;
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
public class ReGeoCodeUtils {

    private static final Logger logger = LoggerFactory.getLogger(ReGeoCodeUtils.class);

    /**
     * 逆地理编码url
     */
    private static final String GD_MAP_RE_GEO_CODE_URL = PropertiesUtils.getValue("gdMap_reGeoCode_url");
    /**
     * 逆地理编码sig
     */
    private static final String GD_MAP_RE_GEO_CODE_SIG = PropertiesUtils.getValue("gdMap_reGeoCode_sig");

    private ReGeoCodeUtils() {
    }

    /**
     * 获取逆地理编码信息
     *
     * @param longitude 经度
     * @param latitude  纬度
     * @return ReGeoCodeQryResult 若出现异常，则返回null
     */
    public static ReGeoCodeQryResult getReGeoCodeInfo(String longitude, String latitude) {
        ReGeoCodeQryParam qryParam = new ReGeoCodeQryParam();
        try {
            qryParam.setKey(GdMapUtils.GD_MAP_KEY);
            qryParam.setLocation(longitude + "," + latitude);
            qryParam.setSig(GD_MAP_RE_GEO_CODE_SIG);
            return getReGeoCodeInfo(qryParam);
        } catch (Exception e) {
            logger.error("ReGeoCodeQryParam：【{}】,获取逆地理编码信息出现异常：", qryParam, e);
            return null;
        }
    }

    /**
     * 获取地理编码信息
     *
     * @param qryParam 查询参数
     * @return GeoCoderQryResult
     */
    public static ReGeoCodeQryResult getReGeoCodeInfo(ReGeoCodeQryParam qryParam) throws IOException {
        StringBuilder urlStrBuilder = new StringBuilder(GD_MAP_RE_GEO_CODE_URL + "?")
                .append("key=").append(qryParam.getKey())
                .append("&location=").append(qryParam.getLocation())
                .append("&poitype=").append(StringUtils.defaultString(qryParam.getPoiType()))
                .append("&radius=").append(StringUtils.defaultString(qryParam.getRadius()))
                .append("&extensions=").append(qryParam.getExtensions())
                .append("&batch=").append(qryParam.isBatch())
                .append("&roadlevel=").append(qryParam.getRoadLevel())
                .append("&sig=").append(qryParam.getSig())
                .append("&output=").append(StringUtils.defaultString(qryParam.getOutput()))
                .append("&callback=").append(StringUtils.defaultString(qryParam.getCallback()))
                .append("&homeorcorp=").append(StringUtils.defaultString(qryParam.getHomeOrcOrp()));
        logger.debug("获取逆地理编码信息的url：【{}】", urlStrBuilder);
        String resultStr = HttpUtils.requestByGet(urlStrBuilder.toString());
        logger.debug("获取逆地理编码信息的结果json：【{}】", resultStr);
        ReGeoCodeQryResult qryResult = JSON.parseObject(resultStr, ReGeoCodeQryResult.class);
        logger.debug("获取逆地理编码信息的json转对象结果：【{}】", qryResult);
        return qryResult;
    }

}
