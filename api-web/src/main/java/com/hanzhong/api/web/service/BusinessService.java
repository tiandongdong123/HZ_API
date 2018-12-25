package com.hanzhong.api.web.service;

import com.hanzhong.api.web.model.bo.CompanyInfoBO;
import com.hanzhong.api.web.model.bo.CompanyQryBO;

import java.util.List;

/**
 *  
 *  @Description
 *  @Author   luqs   
 *  @Date 2018/11/8 9:12 
 *  @Version  V1.0   
 */
public interface BusinessService {

    /**
     * 获取公司信息
     *
     * @param companyQryBO
     * @return List<CompanyInfoBO>
     */
    List<CompanyInfoBO> getCompanyInfoList(CompanyQryBO companyQryBO);

}
