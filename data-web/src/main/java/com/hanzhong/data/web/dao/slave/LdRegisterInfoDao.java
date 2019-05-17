package com.hanzhong.data.web.dao.slave;

import com.hanzhong.data.web.model.bo.EntUniqueInfoBO;
import com.hanzhong.data.web.model.bo.EntUniqueInfoQryBO;
import com.hanzhong.data.web.model.bo.LdRegInfoQryBO;
import com.hanzhong.data.web.model.entity.slave.LdRegisterInfoEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *  
 *  @Description
 *  @Author   luqs   
 *  @Date 2019/1/13 15:19 
 *  @Version  V1.0   
 */
@Repository("ldRegisterInfoDao")
public interface LdRegisterInfoDao {

    /**
     * 获取企业登记信息
     *
     * @param qryParamBO 查询参数
     * @return List<LdRegisterInfoEntity>
     */
    List<LdRegisterInfoEntity> getRegisterInfoListByQryParam(LdRegInfoQryBO qryParamBO);

    /**
     * 添加企业登记信息
     *
     * @param registerInfoEntity 企业登记信息
     * @return int
     */
    int addRegisterInfo(LdRegisterInfoEntity registerInfoEntity);

    /**
     * 更新企业登记信息
     *
     * @param registerInfoEntity 企业登记信息
     * @return int
     */
    int updateRegisterInfo(LdRegisterInfoEntity registerInfoEntity);

    /**
     * 获取企业唯一标识信息
     *
     * @param qryBO 查询参数
     * @return List<EntUniqueInfoBO>
     */
    List<EntUniqueInfoBO> getEntUniqueInfoList(EntUniqueInfoQryBO qryBO);
}
