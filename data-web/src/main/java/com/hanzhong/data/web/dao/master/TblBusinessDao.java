package com.hanzhong.data.web.dao.master;

import com.hanzhong.data.web.model.bo.EnterpriseInfoBO;
import com.hanzhong.data.web.model.bo.EnterpriseInfoQryParamBO;

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
     * @param qryParamBO 查询参数
     * @return List<EnterpriseInfoBO>
     */
    List<EnterpriseInfoBO> getEnterpriseInfoListByQryParam(EnterpriseInfoQryParamBO qryParamBO);
}
