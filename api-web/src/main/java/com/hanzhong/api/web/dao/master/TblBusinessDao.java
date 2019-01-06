package com.hanzhong.api.web.dao.master;

import com.hanzhong.api.web.model.bo.CompanyInfoBO;
import com.hanzhong.api.web.model.bo.CompanyQryBO;

import java.util.List;

/**
 *  
 *  @Description
 *  @Author   luqs   
 *  @Date 2018/11/7 19:19 
 *  @Version  V1.0   
 */
public interface TblBusinessDao {
    /**
     * 获取企业信息
     *
     * @param companyQryBO 企业信息查询参数
     * @return List<CompanyInfoBO>
     */
    List<CompanyInfoBO> getCompanyInfoList(CompanyQryBO companyQryBO);
}
