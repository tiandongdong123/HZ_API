package com.hanzhong.data.web.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 医药保护品种信息结果
 *
 * @author yifei
 * @date 2019/3/27
 */
public class DrugProtVarietyInfoResult extends BasePage {
    /**
     * 医药保护品种信息集
     */
    private List<DrugProtVarietyInfo> drugProtVarietyInfoList;

    /**
     * 初始化
     *
     * @param currentPageNum  当前页码
     * @param currentPageSize 当前每页数量
     * @return DrugInfoResult
     */
    public static DrugProtVarietyInfoResult init(int currentPageNum, int currentPageSize) {
        DrugProtVarietyInfoResult varietyInfoResult = new DrugProtVarietyInfoResult();
        varietyInfoResult.setTotal(0);
        varietyInfoResult.setPageTotal(0);
        varietyInfoResult.setCurrentPageNum(currentPageNum);
        varietyInfoResult.setCurrentPageSize(currentPageSize);
        varietyInfoResult.setNoMore(true);
        varietyInfoResult.setDrugProtVarietyInfoList(new ArrayList<>());
        return varietyInfoResult;
    }

    public List<DrugProtVarietyInfo> getDrugProtVarietyInfoList() {
        return drugProtVarietyInfoList;
    }

    public void setDrugProtVarietyInfoList(List<DrugProtVarietyInfo> drugProtVarietyInfoList) {
        this.drugProtVarietyInfoList = drugProtVarietyInfoList;
    }

    @Override
    public String toString() {
        return "DrugProtVarietyInfoResult{" +
                "drugPrtcVarietyInfoList=" + drugProtVarietyInfoList +
                '}';
    }
}
