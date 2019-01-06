package com.hanzhong.api.web.util.business.auth;

import com.hanzhong.api.web.util.business.auth.model.IpWhiteList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 *  
 *  @Description
 *  @Author   luqs   
 *  @Date 2018/11/6 10:30 
 *  @Version  V1.0   
 */
public class IpAuthUtils {

    private static final Logger logger = LoggerFactory.getLogger(IpAuthUtils.class);

    /**
     * ip白名单集合(正常)
     */
    private static List<IpWhiteList> normalIpWhiteListSet = new ArrayList<>();

    private IpAuthUtils() {
    }

    /**
     * ip是否授权
     *
     * @param ip ip
     * @return boolean
     */
    public static boolean isAuthIp(String ip) {
        if (normalIpWhiteListSet == null || normalIpWhiteListSet.isEmpty()) {
            logger.warn("当前缓存的ip白名单为空！");
            return false;
        }

        for (IpWhiteList ipWhiteList : normalIpWhiteListSet) {
            if (compare(ipWhiteList.getStartIp(), ip) <= 0 && compare(ipWhiteList.getEndIp(), ip) >= 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 缓存ip白名单
     *
     * @param ipWhiteListSet ip白名单集合
     */
    public static void cacheIpWhiteList(List<IpWhiteList> ipWhiteListSet) {
        normalIpWhiteListSet = ipWhiteListSet;
        if (ipWhiteListSet == null || ipWhiteListSet.isEmpty()) {
            logger.warn("需要缓存的ip白名单为空！");
        }
    }

    /**
     * 比较ip大小
     *
     * @param ip1 ip1
     * @param ip2 ip2
     * @return -1：ip1&lt;ip2；0：ip1== ip2；1：ip1&gt;ip2
     */
    private static int compare(String ip1, String ip2) {
        String[] ip1Array = ip1.split("\\.");
        String[] ip2Array = ip2.split("\\.");
        for (int i = 0; i < ip1Array.length; i++) {
            if (Integer.parseInt(ip1Array[i]) > Integer.parseInt(ip2Array[i])) {
                return 1;
            }

            if (Integer.parseInt(ip1Array[i]) < Integer.parseInt(ip2Array[i])) {
                return -1;
            }
        }

        return 0;
    }
}
