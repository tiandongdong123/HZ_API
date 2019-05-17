package com.hanzhong.data.web.dao.master;

import com.hanzhong.data.web.model.bo.DrugInfoQryBO;
import com.hanzhong.data.web.model.bo.EntDrugInfoQryBO;
import com.hanzhong.data.web.model.entity.master.TblDrugEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 药品信息
 *
 * @author yifei
 * @date 2019/3/27
 */
@Repository("tblDrugDao")
public interface TblDrugDao {
    /**
     * 获取药品信息总数
     *
     * @param qryBO 查询参数
     * @return int
     */
    int getDrugInfoCount(DrugInfoQryBO qryBO);

    /**
     * 获取药品信息
     *
     * @param qryBO 查询参数
     * @return List<TblDrugEntity>
     */
    List<TblDrugEntity> getDrugInfoList(DrugInfoQryBO qryBO);

    /**
     * 获取企业药品信息总数
     *
     * @param qryBO 查询参数
     * @return int
     */
    int getEntDrugInfoCount(EntDrugInfoQryBO qryBO);

    /**
     * 获取企业药品信息
     *
     * @param qryBO 查询参数
     * @return List<TblDrugEntity>
     */
    List<TblDrugEntity> getEntDrugInfoList(EntDrugInfoQryBO qryBO);

}
