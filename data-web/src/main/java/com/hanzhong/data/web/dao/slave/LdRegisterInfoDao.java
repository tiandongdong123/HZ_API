package com.hanzhong.data.web.dao.slave;

import com.hanzhong.data.web.model.bo.LdRegInfoQryParamBO;
import com.hanzhong.data.web.model.entity.slave.LdRegisterInfoEntity;

import java.util.List;

/**
 *  
 *  @Description
 *  @Author   luqs   
 *  @Date 2019/1/13 15:19 
 *  @Version  V1.0   
 */
public interface LdRegisterInfoDao {

    /**
     * 获取企业登记信息
     *
     * @param qryParamBO 查询参数
     * @return List<LdRegisterInfoEntity>
     */
    List<LdRegisterInfoEntity> getRegisterInfoListByQryParam(LdRegInfoQryParamBO qryParamBO);

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
}
