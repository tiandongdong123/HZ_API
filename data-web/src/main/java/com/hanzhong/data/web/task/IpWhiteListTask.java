package com.hanzhong.data.web.task;

import com.hanzhong.data.web.service.IpWhiteListService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 *  
 *  @Description
 *  @Author   luqs   
 *  @Date 2019/1/4 23:55 
 *  @Version  V1.0   
 */
@Component
@Configurable
@EnableScheduling
public class IpWhiteListTask {

    private static final Logger logger = LoggerFactory.getLogger(IpWhiteListTask.class);

    @Resource
    private IpWhiteListService ipWhiteListService;

    /**
     * 定时缓存ip白名单（正常状态）信息
     */
    @Scheduled(cron = "${IpWhiteListTask.cacheIpWhiteListInfoTiming.cron}")
    public void cacheIpWhiteListInfoTiming() {
        long startTimeMillis = System.currentTimeMillis();
        logger.info("cacheIpWhiteListInfoTiming（）定时任务执行开始");
        ipWhiteListService.cacheIpWhiteList();
        logger.info("cacheIpWhiteListInfoTiming（）定时任务执行结束,耗时：【{}】ms", System.currentTimeMillis() - startTimeMillis);
    }
}
