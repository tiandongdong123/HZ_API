package com.hanzhong.api.web.util.business;

import com.hanzhong.api.web.constant.cmnenum.LoggerEnum;
import com.hanzhong.api.web.util.LoggerUtils;
import com.hanzhong.api.web.util.PropertiesUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 *  
 *  @Description
 *  @Author   luqs   
 *  @Date 2018/11/6 10:30 
 *  @Version  V1.0   
 */
public class AuthUtils {

    private static final Logger logger = LoggerFactory.getLogger(LoggerEnum.ABNORMAL_CODE_DATA.getValue());

    /**
     * ip白名单文件路径
     */
    private static final String IP_WHITE_LIST_FILE_PATH = PropertiesUtils.getValue("ipWhiteList.file.path");
    private static Map<String, String> ipWhiteListMap = new HashMap<>(16);

    private AuthUtils() {
    }

    /**
     * ip是否授权
     *
     * @param ip
     * @return boolean
     */
    public static boolean isAuthIp(String ip) {

        if (ipWhiteListMap == null || ipWhiteListMap.isEmpty()) {
            ipWhiteListMap = getIpWhiteListMap(IP_WHITE_LIST_FILE_PATH);
        }

        if (ipWhiteListMap != null && ipWhiteListMap.containsKey(ip)) {
            return true;
        }
        return false;
    }

    /**
     * 获取ip白名单
     *
     * @param ipWhiteListFilePath ip白名单文件
     * @return Map
     */
    private static Map<String, String> getIpWhiteListMap(String ipWhiteListFilePath) {

        Map<String, String> ipMap = new HashMap<>(16);
        BufferedReader br = null;

        try {
            br = new BufferedReader(new InputStreamReader(AuthUtils.class.getClassLoader().getResourceAsStream(ipWhiteListFilePath), StandardCharsets.UTF_8));

            String lineStr;
            while ((lineStr = br.readLine()) != null) {
                // 过滤掉空行和注释
                if (StringUtils.isNotBlank(lineStr) && !lineStr.trim().startsWith("#")) {
                    ipMap.put(lineStr, null);
                }
            }
        } catch (FileNotFoundException e) {
            LoggerUtils.appendErrorLog(logger, "【{}】文件找不到：", ipWhiteListFilePath, e);
        } catch (IOException e) {
            LoggerUtils.appendErrorLog(logger, "【{}】文件读取异常：", ipWhiteListFilePath, e);
        } catch (Exception e) {
            LoggerUtils.appendErrorLog(logger, "【{}】文件异常：", ipWhiteListFilePath, e);
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                LoggerUtils.appendErrorLog(logger, "【{}】文件流关闭异常：", ipWhiteListFilePath, e);
            }
        }

        return ipMap;
    }
}
