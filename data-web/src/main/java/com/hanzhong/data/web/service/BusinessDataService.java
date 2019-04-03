package com.hanzhong.data.web.service;

import com.hanzhong.data.web.model.*;

import java.util.List;

/**
 *  
 *  @Description
 *  @Author   luqs   
 *  @Date 2018/11/8 9:12 
 *  @Version  V1.0   
 */
public interface BusinessDataService {

    /**
     * 获取企业基本信息
     *
     * @param qryParam 查询参数
     * @return EnterpriseBaseInfo
     */
    EnterpriseBaseInfo getEnterpriseBaseInfo(EnterpriseBaseInfoQryParam qryParam);

}
