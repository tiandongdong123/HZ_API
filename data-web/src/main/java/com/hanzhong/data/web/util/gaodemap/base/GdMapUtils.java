package com.hanzhong.data.web.util.gaodemap.base;

import com.hanzhong.data.web.util.PropertiesUtils;
import com.hanzhong.data.web.util.gaodemap.base.constant.RequestStatusEnum;
import com.hanzhong.data.web.util.gaodemap.geocode.model.BaseResult;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 *  
 *  @Description 高德地图工具类
 *  @Author   luqs   
 *  @Date 2019/5/15 20:24 
 *  @Version  V1.0   
 */
public class GdMapUtils {

    /**
     * key
     */
    public static final String GD_MAP_KEY = PropertiesUtils.getValue("gdMap_key");

    private GdMapUtils() {
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


    /**
     * 判断请求是否成功
     *
     * @param baseResult 请求结果
     * @return boolean true:成功
     */
    public static boolean isSuccess(BaseResult baseResult) {
        return baseResult != null && RequestStatusEnum.SUCCESS.getValue().equals(baseResult.getStatus());
    }
}
