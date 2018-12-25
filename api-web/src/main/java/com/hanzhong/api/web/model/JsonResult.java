package com.hanzhong.api.web.model;

import java.io.Serializable;

/**
 *  
 *  @Description
 *  @Author   luqs   
 *  @Date 2018/11/5 16:33 
 *  @Version  V1.0   
 */
public class JsonResult implements Serializable {

    private static final long serialVersionUID = -4963266899668807475L;

    /**
     * 状态码
     */
    private String resultCode;
    /**
     * 提示信息
     */
    private String message;
    /**
     * 返回数据
     */
    private Object data;

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "JsonResult{" +
                "resultCode='" + resultCode + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
