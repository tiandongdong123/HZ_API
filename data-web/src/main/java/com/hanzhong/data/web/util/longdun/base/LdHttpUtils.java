package com.hanzhong.data.web.util.longdun.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.zip.GZIPInputStream;

/**
 *    
 *  @Description
 *  @Author   龙盾提供 
 *  @Date 2019/1/12 18:04 
 *  @Version  V1.0   
 */
public class LdHttpUtils {

    private static final Logger logger = LoggerFactory.getLogger(LdHttpUtils.class);

    private LdHttpUtils() {
    }

    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url   发送请求的URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return String 远程资源的响应结果
     */
    public static String sendGet(String url, String param) {
        StringBuilder resultStrBuilder = new StringBuilder();
        String urlNameString = url + "?" + param;

        try {
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            try (
                    // 定义 BufferedReader输入流来读取URL的响应
                    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))
            ) {
                String line;
                while ((line = in.readLine()) != null) {
                    resultStrBuilder.append(line);
                }
            }
        } catch (IOException e) {
            logger.error("url：【{}】，param：【{}】，发送GET请求出现异常：", url, param, e);
        }
        return resultStrBuilder.toString();
    }

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url   发送请求的 URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 远程资源的响应结果
     */
    public static String sendPost(String url, String param) {
        String result = "";

        try (
                ByteArrayOutputStream out = new ByteArrayOutputStream()
        ) {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            PrintWriter outPrintWriter = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            outPrintWriter.print(param);
            // flush输出流的缓冲
            outPrintWriter.flush();
            try (
                    // 解压数据
                    GZIPInputStream gzipInputStream = new GZIPInputStream(conn.getInputStream())
            ) {
                byte[] buf = new byte[1024];
                int num;
                while ((num = gzipInputStream.read(buf, 0, buf.length)) != -1) {
                    out.write(buf, 0, num);
                }
                // 用户UTF-8编码格式转换
                result = out.toString("UTF-8");
            }
        } catch (IOException e) {
            logger.error("url：【{}】，param：【{}】，发送POST请求出现异常：", url, param, e);
        }
        return result;
    }
}