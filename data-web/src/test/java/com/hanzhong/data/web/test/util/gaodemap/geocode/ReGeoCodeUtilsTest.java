package com.hanzhong.data.web.test.util.gaodemap.geocode;

import com.alibaba.fastjson.JSON;
import com.hanzhong.data.web.util.gaodemap.base.GdMapUtils;
import com.hanzhong.data.web.util.gaodemap.geocode.ReGeoCodeUtils;
import com.hanzhong.data.web.util.gaodemap.geocode.model.ReGeoCodeQryParam;
import com.hanzhong.data.web.util.gaodemap.geocode.model.ReGeoCodeQryResult;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *  
 *  @Description
 *  @Author   luqs   
 *  @Date 2019/5/17 12:25 
 *  @Version  V1.0   
 */
public class ReGeoCodeUtilsTest {

    private static final Logger logger = LoggerFactory.getLogger(ReGeoCodeUtilsTest.class);

    /**
     * 通过经纬度获取逆地理编码信息
     */
    @Test
    public void getReGeoCodeInfoByLonLatTest() {
        // 经度
        String longitude = "106.647919";
        // 纬度
        String latitude = "26.622350";
        logger.info("通过经纬度获取逆地理编码信息的参数值：经纬度：【{},{}】", longitude, latitude);
        ReGeoCodeQryResult qryResult = ReGeoCodeUtils.getReGeoCodeInfo(longitude, latitude);
        logger.info("通过经纬度获取逆地理编码信息的返回值：【{}】", JSON.toJSONString(qryResult));
    }

    /**
     * 获取逆地理编码信息
     */
    @Test
    public void getReGeoCodeInfoTest() {
        // 经度
        String longitude = "106.647919";
        // 纬度
        String latitude = "26.622350";
        ReGeoCodeQryParam qryParam = new ReGeoCodeQryParam();
        qryParam.setKey(GdMapUtils.GD_MAP_KEY);
        qryParam.setLocation(longitude + "," + latitude);
        qryParam.setSig("");
        try {
            logger.info("获取逆地理编码信息的参数值：【{}】", JSON.toJSONString(qryParam));
            ReGeoCodeQryResult qryResult = ReGeoCodeUtils.getReGeoCodeInfo(qryParam);
            logger.info("获取逆地理编码信息的返回值：【{}】", JSON.toJSONString(qryResult));
        } catch (Exception e) {
            logger.error("参数：【{}】，获取逆地理编码信息出现异常：【{}】", qryParam, e);
        }
    }
}
