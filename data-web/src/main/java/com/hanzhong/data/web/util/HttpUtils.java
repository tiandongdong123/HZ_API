package com.hanzhong.data.web.util;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 *    
 *  @Description http请求工具类
 *  @Author   luqs   
 *  @Date 2017/11/7 10:13 
 *  @Version  V1.0   
 */
public class HttpUtils {

    private static final Logger logger = LoggerFactory.getLogger(HttpUtils.class);

    private static PoolingHttpClientConnectionManager cm;

    /**
     * 静态块
     */
    static {
        init();
    }

    /**
     * 构造私有的无参函数，禁止实例化对象,实现单例
     */
    private HttpUtils() {
    }

    /**
     * 获取HttpServletRequest的参数json串
     *
     * @param request 请求
     * @return String
     */
    public static String getRequestParamJsonStr(HttpServletRequest request) {
        return JSON.toJSONString(request.getParameterMap());
    }

    /**
     * 通过“get”方式发送请求
     *
     * @param url 请求url
     * @return String（若请求出现异常，则返回null）
     */
    public static String requestByGet(String url) throws IOException {

        long startTime = System.currentTimeMillis();

        String resultStr = null;

///        CloseableHttpClient httpclient = HttpClients.createDefault();//默认连接池
        CloseableHttpClient httpclient = getHttpClient();
        try {
            // 创建httpget.
            HttpGet httpGet = new HttpGet(url);
            logger.debug("executing request: 【{}】", httpGet.getURI());
            // 执行get请求.
            CloseableHttpResponse response = httpclient.execute(httpGet);
            try {
                // 获取响应实体
                HttpEntity entity = response.getEntity();
                // 打印响应状态
                logger.debug("Response statusLine: 【{}】", response.getStatusLine());
                if (entity != null) {
                    // 打印响应内容长度
                    logger.debug("Response content length: 【{}】", entity.getContentLength());
                    // 打印响应内容
                    resultStr = EntityUtils.toString(entity, "UTF-8");
                    logger.debug("Response content: 【{}】", resultStr);
                }
            } finally {
                response.close();
            }
        } finally {
            // 关闭连接,释放资源（若没有采用连接池方式，需关闭连接）
///            try {
///                httpclient.close();
///            } catch (IOException e) {
///                e.printStackTrace();
///                logger.error("httpclient关闭出现异常！", e);
///            }
        }

        long endTime = System.currentTimeMillis();
        logger.debug("requestByGet（）接口耗时：【{}】ms", endTime - startTime);

        return resultStr;
    }

    /**
     * 初始化连接池
     */
    private static void init() {
        try {
            if (cm == null) {
                cm = new PoolingHttpClientConnectionManager();
                // 整个连接池最大连接数
                cm.setMaxTotal(200);
                // 每路由最大连接数，默认值是2
                cm.setDefaultMaxPerRoute(10);
            }
        } catch (Exception e) {
            logger.error("初始化http连接池（init（））出现异常：", e);
        }
    }

    /**
     * 通过连接池获取HttpClient
     *
     * @return CloseableHttpClient
     */
    private static CloseableHttpClient getHttpClient() {
        init();
        return HttpClients.custom().setConnectionManager(cm).build();
    }
}
