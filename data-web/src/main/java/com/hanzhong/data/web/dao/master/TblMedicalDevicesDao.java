package com.hanzhong.data.web.dao.master;

import com.hanzhong.data.web.model.bo.EntMedicalDeviceInfoQryBO;
import com.hanzhong.data.web.model.bo.MedicalDeviceInfoQryBO;
import com.hanzhong.data.web.model.entity.master.TblMedicalDevicesEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 医疗器械信息
 *
 * @author yifei
 * @date 2019/3/27
 */
@Repository("tblMedicalDevicesDao")
public interface TblMedicalDevicesDao {
    /**
     * 获取医疗器械信息总数
     *
     * @param qryBO 查询参数
     * @return int
     */
    int getMedicalDeviceInfoCount(MedicalDeviceInfoQryBO qryBO);

    /**
     * 获取医疗器械信息
     *
     * @param qryBO 查询参数
     * @return List<TblMedicalDevicesEntity>
     */
    List<TblMedicalDevicesEntity> getMedicalDeviceInfoList(MedicalDeviceInfoQryBO qryBO);

    /**
     * 获取企业医疗器械信息总数
     *
     * @param qryBO 查询参数
     * @return int
     */
    int getEntMedicalDeviceInfoCount(EntMedicalDeviceInfoQryBO qryBO);

    /**
     * 获取企业医疗器械信息
     *
     * @param qryBO 查询参数
     * @return List<TblMedicalDevicesEntity>
     */
    List<TblMedicalDevicesEntity> getEntMedicalDeviceInfoList(EntMedicalDeviceInfoQryBO qryBO);

}
