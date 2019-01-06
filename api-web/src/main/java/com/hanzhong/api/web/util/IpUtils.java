package com.hanzhong.api.web.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.regex.Pattern;

/**
 *    
 *  @Description
 *  @Author   luqs   
 *  @Date 2018/6/1 12:17 
 *  @Version  V1.0   
 */
public class IpUtils {

    private static final Logger logger = LoggerFactory.getLogger(IpUtils.class);

    private static final String UNKNOWN = "unknown";
    private static final String HOST_127 = "127.0.0.1";
    private static final String HOST_BASE = "0:0:0:0:0:0:0:1";
    private static final Pattern IP_PATTERN = Pattern.compile("^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$");

    private IpUtils() {
    }

    /**
     * 获取ip
     *
     * @param request 请求
     * @return String
     */
    public static String getIp(HttpServletRequest request) {
        String ip = "";

        // 获取ipAddresses
        String ipAddresses = getIpAddress(request);

        // 有些网络通过多层代理，那么获取到的ip就会有多个，一般都是通过逗号（,）分割开来，并且第一个ip为客户端的真实IP
        if (StringUtils.isNotBlank(ipAddresses)) {
            ip = ipAddresses.split(",")[0];
        }

        // 还是不能获取到，最后再通过request.getRemoteAddr();获取
        if (StringUtils.isBlank(ip) || UNKNOWN.equalsIgnoreCase(ipAddresses)) {
            ip = request.getRemoteAddr();

            if (HOST_127.equals(ip) || HOST_BASE.equals(ip)) {
                // 根据网卡取本机配置的IP
                InetAddress inetAddress = null;
                try {
                    inetAddress = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    logger.error("getLocalHost()出现异常：", e);
                }
                ip = inetAddress == null ? ip : inetAddress.getHostAddress();
            }
        }
        return ip;
    }

    /**
     * 检测ip格式
     *
     * @param ip ip
     * @return true: 符合ip格式
     */
    public static boolean checkIpFormat(String ip) {
        return IP_PATTERN.matcher(ip).matches();
    }

    /**
     * 获取ipAddress
     *
     * @param request 请求
     * @return String
     */
    private static String getIpAddress(HttpServletRequest request) {
        // X-Forwarded-For：Squid 服务代理
        String ipAddresses = request.getHeader("X-Forwarded-For");

        if (StringUtils.isBlank(ipAddresses) || UNKNOWN.equalsIgnoreCase(ipAddresses)) {
            //Proxy-Client-IP：apache 服务代理
            ipAddresses = request.getHeader("Proxy-Client-IP");
        }

        if (StringUtils.isBlank(ipAddresses) || UNKNOWN.equalsIgnoreCase(ipAddresses)) {
            // WL-Proxy-Client-IP：weblogic 服务代理
            ipAddresses = request.getHeader("WL-Proxy-Client-IP");
        }

        if (StringUtils.isBlank(ipAddresses) || UNKNOWN.equalsIgnoreCase(ipAddresses)) {
            // HTTP_CLIENT_IP：有些代理服务器
            ipAddresses = request.getHeader("HTTP_CLIENT_IP");
        }

        if (StringUtils.isBlank(ipAddresses) || UNKNOWN.equalsIgnoreCase(ipAddresses)) {
            // X-Real-IP：nginx服务代理
            ipAddresses = request.getHeader("X-Real-IP");
        }

        return ipAddresses;
    }
}
