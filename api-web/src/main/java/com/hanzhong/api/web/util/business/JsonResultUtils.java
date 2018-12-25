package com.hanzhong.api.web.util.business;

import com.hanzhong.api.web.constant.cmnenum.ResultCodeEnum;
import com.hanzhong.api.web.model.JsonResult;

/**
 *  
 *  @Description
 *  @Author   luqs   
 *  @Date 2018/11/5 17:09 
 *  @Version  V1.0   
 */
public class JsonResultUtils {

    private JsonResultUtils() {
    }

    /**
     * 构建JsonResult
     *
     * @param codeEnum 结果码枚举
     * @param data     数据
     * @return JsonResult
     */
    public static JsonResult build(ResultCodeEnum codeEnum, Object data) {
        return build(codeEnum.getKey(), codeEnum.getValue(), data);
    }

    /**
     * 构建JsonResult
     *
     * @param resultCode 结果码
     * @param message    消息
     * @param data       数据
     * @return JsonResult
     */
    public static JsonResult build(String resultCode, String message, Object data) {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setResultCode(resultCode);
        jsonResult.setMessage(message);
        jsonResult.setData(data);

        return jsonResult;
    }
}
