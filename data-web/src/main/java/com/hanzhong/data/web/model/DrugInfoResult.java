package com.hanzhong.data.web.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 医药药品信息结果
 *
 * @author yifei
 * @date 2019/3/27
 */
public class DrugInfoResult extends BasePage {
    /**
     * 医药药品信息集
     */
    private List<DrugInfo> drugInfoList;

    /**
     * 初始化
     *
     * @param currentPageNum  当前页码
     * @param currentPageSize 当前每页数量
     * @return DrugInfoResult
     */
    public static DrugInfoResult init(int currentPageNum, int currentPageSize) {
        DrugInfoResult drugInfoResult = new DrugInfoResult();
        drugInfoResult.setTotal(0);
        drugInfoResult.setPageTotal(0);
        drugInfoResult.setCurrentPageNum(currentPageNum);
        drugInfoResult.setCurrentPageSize(currentPageSize);
        drugInfoResult.setNoMore(true);
        drugInfoResult.setDrugInfoList(new ArrayList<>());
        return drugInfoResult;
    }

    public List<DrugInfo> getDrugInfoList() {
        return drugInfoList;
    }

    public void setDrugInfoList(List<DrugInfo> drugInfoList) {
        this.drugInfoList = drugInfoList;
    }

    @Override
    public String toString() {
        return "DrugInfoResult{" +
                "drugInfoList=" + drugInfoList +
                '}';
    }
}
