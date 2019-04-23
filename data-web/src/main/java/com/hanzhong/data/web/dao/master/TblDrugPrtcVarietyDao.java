package com.hanzhong.data.web.dao.master;

import com.hanzhong.data.web.model.bo.DrugProtVarietyInfoQryBO;
import com.hanzhong.data.web.model.bo.EntDrugProtVarietyInfoQryBO;
import com.hanzhong.data.web.model.entity.master.TblDrugPrtcVarietyEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 医药保护品种信息
 *
 * @author yifei
 * @date 2019/3/27
 */
@Repository("tblDrugPrtcVarietyDao")
public interface TblDrugPrtcVarietyDao {
    /**
     * 获取医药保护品种信息总数
     *
     * @param qryBO 查询参数
     * @return int
     */
    int getDrugPrtcVarietyInfoCount(DrugProtVarietyInfoQryBO qryBO);

    /**
     * 获取医药保护品种信息
     *
     * @param qryBO 查询参数
     * @return List<TblDrugPrtcVarietyEntity>
     */
    List<TblDrugPrtcVarietyEntity> getDrugPrtcVarietyInfoList(DrugProtVarietyInfoQryBO qryBO);

    /**
     * 获取企业医药保护品种信息总数
     *
     * @param qryBO 查询参数
     * @return int
     */
    int getEntDrugPrtcVarietyInfoCount(EntDrugProtVarietyInfoQryBO qryBO);

    /**
     * 获取企业医药保护品种信息
     *
     * @param qryBO 查询参数
     * @return List<TblDrugPrtcVarietyEntity>
     */
    List<TblDrugPrtcVarietyEntity> getEntDrugPrtcVarietyInfoList(EntDrugProtVarietyInfoQryBO qryBO);

}
