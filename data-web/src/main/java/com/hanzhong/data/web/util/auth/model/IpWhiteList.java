package com.hanzhong.data.web.util.auth.model;

/**
 *  
 *  @Description
 *  @Author   luqs   
 *  @Date 2019/1/4 22:41 
 *  @Version  V1.0   
 */
public class IpWhiteList {
    /**
     * 名称
     */
    private String name;
    /**
     * 起始ip
     */
    private String startIp;
    /**
     * 结束ip
     */
    private String endIp;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartIp() {
        return startIp;
    }

    public void setStartIp(String startIp) {
        this.startIp = startIp;
    }

    public String getEndIp() {
        return endIp;
    }

    public void setEndIp(String endIp) {
        this.endIp = endIp;
    }

    @Override
    public String toString() {
        return "IpWhiteList{" +
                "name='" + name + '\'' +
                ", startIp='" + startIp + '\'' +
                ", endIp='" + endIp + '\'' +
                '}';
    }
}
