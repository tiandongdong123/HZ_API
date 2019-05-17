package com.hanzhong.data.web.service;

import com.hanzhong.data.web.model.*;

/**
 * 企业医药
 *
 * @author yifei
 * @date 2019/4/21
 */
public interface EntDrugService {

    /**
     * 分页获取企业医药药品信息
     *
     * @param qryParam 查询参数
     * @return DrugInfoResult
     */
    DrugInfoResult getEntDrugInfoByPage(DrugInfoQryParam qryParam);

    /**
     * 分页获取企业医药保护品种信息
     *
     * @param qryParam 查询参数
     * @return DrugProtVarietyInfoResult
     */
    DrugProtVarietyInfoResult getEntDrugProtVarietyInfoByPage(DrugProtVarietyInfoQryParam qryParam);

    /**
     * 分页获取企业医疗器械信息
     *
     * @param qryParam 查询参数
     * @return MedicalDevicesInfoResult
     */
    MedicalDeviceInfoResult getEntMedicalDeviceInfoByPage(MedicalDeviceInfoQryParam qryParam);
}
