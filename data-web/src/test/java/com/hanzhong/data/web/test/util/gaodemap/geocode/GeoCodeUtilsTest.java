package com.hanzhong.data.web.test.util.gaodemap.geocode;

import com.alibaba.fastjson.JSON;
import com.hanzhong.data.web.util.gaodemap.base.GdMapUtils;
import com.hanzhong.data.web.util.gaodemap.geocode.GeoCodeUtils;
import com.hanzhong.data.web.util.gaodemap.geocode.model.GeoCodeQryParam;
import com.hanzhong.data.web.util.gaodemap.geocode.model.GeoCodeQryResult;
import org.apache.commons.lang3.StringUtils;
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
public class GeoCodeUtilsTest {

    private static final Logger logger = LoggerFactory.getLogger(ReGeoCodeUtilsTest.class);

    /**
     * 通过地址获取地理编码信息
     */
    @Test
    public void getGeoCodeInfoByAddressTest() {
        // 地址
        String address = "长江路经济开发区长江路58号银座D栋2单元411室";
        // 市
        String city = "长春市";
        logger.info("通过地址获取地理编码信息的参数值：地址：【{}】，市：【{}】", address, city);
        GeoCodeQryResult qryResult = GeoCodeUtils.getGeoCodeInfo(address, city);
        logger.info("通过地址获取地理编码信息的返回值：【{}】", JSON.toJSONString(qryResult));
    }

    /**
     * 获取地理编码信息
     */
    @Test
    public void getGeoCodeInfoTest() {
        // 地址
        String address = "长江路经济开发区长江路58号银座D栋2单元411室";
        // 市
        String city = "长春市";
        GeoCodeQryParam qryParam = new GeoCodeQryParam();
        try {
            qryParam.setKey(GdMapUtils.GD_MAP_KEY);
            qryParam.setAddress(GdMapUtils.handleSpecialCharInAddress(address));
            qryParam.setCity(StringUtils.defaultString(city));
            qryParam.setSig("");
            logger.info("获取地理编码信息的参数值：【{}】", JSON.toJSONString(qryParam));
            GeoCodeQryResult qryResult = GeoCodeUtils.getGeoCodeInfo(qryParam);
            logger.info("获取地理编码信息的返回值：【{}】", JSON.toJSONString(qryResult));
        } catch (Exception e) {
            logger.error("参数：【{}】，获取地理编码信息出现异常：【{}】", qryParam, e);
        }
    }
}
