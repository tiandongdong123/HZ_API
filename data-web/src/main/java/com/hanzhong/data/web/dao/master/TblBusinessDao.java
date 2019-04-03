package com.hanzhong.data.web.dao.master;

import com.hanzhong.data.web.model.bo.EntUniqueInfoBO;
import com.hanzhong.data.web.model.bo.EntUniqueInfoQryBO;
import com.hanzhong.data.web.model.bo.EnterpriseInfoBO;
import com.hanzhong.data.web.model.bo.EnterpriseInfoQryBO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *  
 *  @Description
 *  @Author   luqs   
 *  @Date 2018/11/7 19:19 
 *  @Version  V1.0   
 */
@Repository("tblBusinessDao")
public interface TblBusinessDao {

    /**
     * 获取企业信息
     *
     * @param qryBO 查询参数
     * @return List<EnterpriseInfoBO>
     */
    List<EnterpriseInfoBO> getEnterpriseInfoListByQryParam(EnterpriseInfoQryBO qryBO);

    /**
     * 获取企业唯一标识信息
     *
     * @param qryBO 查询参数
     * @return List<EntUniqueInfoBO>
     */
    List<EntUniqueInfoBO> getEntUniqueInfoList(EntUniqueInfoQryBO qryBO);
}
