package com.hanzhong.data.web.test.controller;

import com.hanzhong.data.web.test.constant.CmnConstant;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *  
 *  @Description
 *  @Author   luqs   
 *  @Date 2019/1/3 16:27 
 *  @Version  V1.0   
 */
public class EntBaseInfoControllerTest {

    private static final Logger logger = LoggerFactory.getLogger(EntBaseInfoControllerTest.class);

    @Test
    public void getEntBaseInfoTest() {
        // httpClient
        HttpClient httpClient = HttpClients.createDefault();
        // post method
        HttpPost httpPost = new HttpPost(CmnConstant.PROD_API_BASE_URL + "/productservice/productOutInterface/product_companyInfoOut");
        // set params
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("companyName", "北京维利和商贸有限公司"));
        params.add(new BasicNameValuePair("corCodeTy", "91110228MA001X694F"));
        params.add(new BasicNameValuePair("corCode", "550400025"));
        params.add(new BasicNameValuePair("productpara", "companyName"));
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(params, Consts.UTF_8));
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            logger.info("getCompanyInfo()返回值：【{}】", EntityUtils.toString(entity, CmnConstant.CHARSET_UTF8));
        } catch (ClientProtocolException e) {
            logger.error("getCompanyInfo()出现ClientProtocolException异常：", e);
        } catch (IOException e) {
            logger.error("getCompanyInfo()出现IOException异常：", e);
        }
    }
}
