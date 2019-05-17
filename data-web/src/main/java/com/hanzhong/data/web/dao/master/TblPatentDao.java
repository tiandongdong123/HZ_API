package com.hanzhong.data.web.dao.master;

import com.hanzhong.data.web.model.bo.EntPatentInfoQryBO;
import com.hanzhong.data.web.model.bo.PatentInfoQryBO;
import com.hanzhong.data.web.model.entity.master.TblPatentEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 专利信息
 *
 * @author yifei
 * @date 2019/3/27
 */
@Repository("tblPatentDao")
public interface TblPatentDao {
    /**
     * 获取专利信息总数
     *
     * @param qryBO 查询参数
     * @return int
     */
    int getPatentInfoCount(PatentInfoQryBO qryBO);

    /**
     * 获取专利信息
     *
     * @param qryBO 查询参数
     * @return List<TblPatentEntity>
     */
    List<TblPatentEntity> getPatentInfoList(PatentInfoQryBO qryBO);

    /**
     * 获取企业专利信息总数
     *
     * @param qryBO 查询参数
     * @return int
     */
    int getEntPatentInfoCount(EntPatentInfoQryBO qryBO);

    /**
     * 获取企业专利信息
     *
     * @param qryBO 查询参数
     * @return List<TblPatentEntity>
     */
    List<TblPatentEntity> getEntPatentInfoList(EntPatentInfoQryBO qryBO);

}
