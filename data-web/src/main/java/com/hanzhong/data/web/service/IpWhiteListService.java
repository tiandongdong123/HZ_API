package com.hanzhong.data.web.service;

/**
 *  
 *  @Description
 *  @Author   luqs   
 *  @Date 2019/1/4 19:51 
 *  @Version  V1.0   
 */
public interface IpWhiteListService {
    /**
     * 缓存ip白名单
     *
     * @return boolean true：缓存成功
     */
    boolean cacheIpWhiteList();
}
