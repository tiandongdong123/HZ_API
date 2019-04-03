package com.hanzhong.data.web.util.longdun.base.model;

/**
 * @author yifei
 * @date 2019/1/7
 */
public class ApiParam {
    /**
     * 用户id
     */
    private String uid;
    /**
     * 服务名
     */
    private String service;
    /**
     * 参数
     */
    private Object params;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public Object getParams() {
        return params;
    }

    public void setParams(Object params) {
        this.params = params;
    }

    @Override
    public String toString() {
        return "ApiParam{" +
                "uid='" + uid + '\'' +
                ", service='" + service + '\'' +
                ", params=" + params +
                '}';
    }
}
