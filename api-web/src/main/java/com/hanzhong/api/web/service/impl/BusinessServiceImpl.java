package com.hanzhong.api.web.service.impl;

import com.hanzhong.api.web.dao.master.TblBusinessDao;
import com.hanzhong.api.web.model.bo.CompanyInfoBO;
import com.hanzhong.api.web.model.bo.CompanyQryBO;
import com.hanzhong.api.web.service.BusinessService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 *  
 *  @Description
 *  @Author   luqs   
 *  @Date 2018/11/8 9:14 
 *  @Version  V1.0   
 */
@Service("businessService")
public class BusinessServiceImpl implements BusinessService {

    @Resource
    private TblBusinessDao tblBusinessDao;

    @Override
    public List<CompanyInfoBO> getCompanyInfoList(CompanyQryBO companyQryBO) {
        return tblBusinessDao.getCompanyInfoList(companyQryBO);
    }
}
