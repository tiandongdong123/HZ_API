package com.hanzhong.data.web.model.vo;

import java.util.List;

/**
 * 医药保护品种信息结果
 *
 * @author yifei
 * @date 2019/3/27
 */
public class DrugProtVarietyInfoResultVO extends BasePageVO {
    /**
     * 医药保护品种信息集
     */
    private List<DrugProtVarietyInfoVO> drugProtVarietyInfoVOList;

    public List<DrugProtVarietyInfoVO> getDrugProtVarietyInfoVOList() {
        return drugProtVarietyInfoVOList;
    }

    public void setDrugProtVarietyInfoVOList(List<DrugProtVarietyInfoVO> drugProtVarietyInfoVOList) {
        this.drugProtVarietyInfoVOList = drugProtVarietyInfoVOList;
    }

    @Override
    public String toString() {
        return "DrugProtVarietyInfoResultVO{" +
                "drugProtVarietyInfoVOList=" + drugProtVarietyInfoVOList +
                '}';
    }
}
