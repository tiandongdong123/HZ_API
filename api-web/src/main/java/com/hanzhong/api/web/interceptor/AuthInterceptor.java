package com.hanzhong.api.web.interceptor;

import com.alibaba.fastjson.JSON;
import com.hanzhong.api.web.constant.cmnenum.LoggerEnum;
import com.hanzhong.api.web.constant.cmnenum.ResultCodeEnum;
import com.hanzhong.api.web.util.IpUtils;
import com.hanzhong.api.web.util.business.JsonResultUtils;
import com.hanzhong.api.web.util.business.auth.IpAuthUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 *  
 *  @Description
 *  @Author   luqs   
 *  @Date 2018/11/5 23:06 
 *  @Version  V1.0   
 */
@Component
public class AuthInterceptor extends HandlerInterceptorAdapter {

    private static final Logger ipVisitLogger = LoggerFactory.getLogger(LoggerEnum.IP_VISIT.getValue());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // ip
        String ip = IpUtils.getIp(request);

        if (IpAuthUtils.isAuthIp(ip)) {
            ipVisitLogger.info("ip：【{}】正常访问", ip);
            return true;
        } else {
            ipVisitLogger.warn("ip：【{}】不在ip白名单内，拒绝访问", ip);
            response.setContentType("text/plain;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            PrintWriter printWriter = response.getWriter();
            printWriter.write(JSON.toJSONString(JsonResultUtils.build(ResultCodeEnum.IP_NOT_BIND, null)));
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }

    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        super.afterConcurrentHandlingStarted(request, response, handler);
    }
}
