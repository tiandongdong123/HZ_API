package com.hanzhong.data.web.config.interceptor;

import com.hanzhong.data.web.interceptor.AuthInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 *  
 *  @Description
 *  @Author   luqs   
 *  @Date 2018/11/5 23:25 
 *  @Version  V1.0   
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {

    @Autowired
    private AuthInterceptor authInterceptor;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        // 接口访问权限拦截器
        registry.addInterceptor(authInterceptor).addPathPatterns("/productOutInterface/**");
        super.addInterceptors(registry);
    }
}
