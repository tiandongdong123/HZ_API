package com.hanzhong.data.web.model;

import com.hanzhong.data.web.constant.MedicalDeviceQryFieldEnum;

/**
 * 医疗器械信息查询参数
 *
 * @author yifei
 * @date 2019/3/30
 */
public class MedicalDeviceInfoQryParam {
    /**
     * 企业名称
     */
    private String entName;
    /**
     * 统一社会信用代码
     */
    private String usCreditCode;
    /**
     * 注册证编号
     */
    private String regCertNum;
    /**
     * 医疗器械查询字段枚举
     */
    private MedicalDeviceQryFieldEnum medicalDeviceQryFieldEnum;
    /**
     * 页码
     */
    private Integer pageNum;
    /**
     * 每页数量
     */
    private Integer pageSize;

    public String getEntName() {
        return entName;
    }

    public void setEntName(String entName) {
        this.entName = entName;
    }

    public String getUsCreditCode() {
        return usCreditCode;
    }

    public void setUsCreditCode(String usCreditCode) {
        this.usCreditCode = usCreditCode;
    }

    public String getRegCertNum() {
        return regCertNum;
    }

    public void setRegCertNum(String regCertNum) {
        this.regCertNum = regCertNum;
    }

    public MedicalDeviceQryFieldEnum getMedicalDeviceQryFieldEnum() {
        return medicalDeviceQryFieldEnum;
    }

    public void setMedicalDeviceQryFieldEnum(MedicalDeviceQryFieldEnum medicalDeviceQryFieldEnum) {
        this.medicalDeviceQryFieldEnum = medicalDeviceQryFieldEnum;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "MedicalDeviceInfoQryParam{" +
                "entName='" + entName + '\'' +
                ", usCreditCode='" + usCreditCode + '\'' +
                ", regCertNum='" + regCertNum + '\'' +
                ", medicalDeviceQryFieldEnum=" + medicalDeviceQryFieldEnum +
                ", pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                '}';
    }
}
