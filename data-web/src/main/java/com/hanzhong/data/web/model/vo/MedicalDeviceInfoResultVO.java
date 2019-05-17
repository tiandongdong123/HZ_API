package com.hanzhong.data.web.model.vo;

import java.util.List;

/**
 * 医疗器械信息结果
 *
 * @author yifei
 * @date 2019/3/27
 */
public class MedicalDeviceInfoResultVO extends BasePageVO {
    /**
     * 医疗器械信息集
     */
    private List<MedicalDeviceInfoVO> medicalDeviceInfoVOList;

    public List<MedicalDeviceInfoVO> getMedicalDeviceInfoVOList() {
        return medicalDeviceInfoVOList;
    }

    public void setMedicalDeviceInfoVOList(List<MedicalDeviceInfoVO> medicalDeviceInfoVOList) {
        this.medicalDeviceInfoVOList = medicalDeviceInfoVOList;
    }

    @Override
    public String toString() {
        return "MedicalDevicesInfoResultVO{" +
                "medicalDevicesInfoVOList=" + medicalDeviceInfoVOList +
                '}';
    }
}
