package com.hanzhong.data.web.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 医疗器械信息结果
 *
 * @author yifei
 * @date 2019/3/27
 */
public class MedicalDeviceInfoResult extends BasePage {
    /**
     * 医疗器械信息集
     */
    private List<MedicalDeviceInfo> medicalDeviceInfoList;

    /**
     * 初始化
     *
     * @param currentPageNum  当前页码
     * @param currentPageSize 当前每页数量
     * @return EntPatentInfoResult
     */
    public static MedicalDeviceInfoResult init(int currentPageNum, int currentPageSize) {
        MedicalDeviceInfoResult devicesInfoResult = new MedicalDeviceInfoResult();
        devicesInfoResult.setTotal(0);
        devicesInfoResult.setPageTotal(0);
        devicesInfoResult.setCurrentPageNum(currentPageNum);
        devicesInfoResult.setCurrentPageSize(currentPageSize);
        devicesInfoResult.setNoMore(true);
        devicesInfoResult.setMedicalDeviceInfoList(new ArrayList<>());
        return devicesInfoResult;
    }

    public List<MedicalDeviceInfo> getMedicalDeviceInfoList() {
        return medicalDeviceInfoList;
    }

    public void setMedicalDeviceInfoList(List<MedicalDeviceInfo> medicalDeviceInfoList) {
        this.medicalDeviceInfoList = medicalDeviceInfoList;
    }

    @Override
    public String toString() {
        return "MedicalDevicesInfoResult{" +
                "medicalDevicesInfoList=" + medicalDeviceInfoList +
                '}';
    }
}
