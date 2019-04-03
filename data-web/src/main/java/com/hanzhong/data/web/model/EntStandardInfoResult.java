package com.hanzhong.data.web.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 企业标准信息结果
 *
 * @author yifei
 * @date 2019/3/27
 */
public class EntStandardInfoResult extends BasePage {
    /**
     * 企业标准信息集
     */
    private List<EntStandardInfo> entStandardInfoList;

    /**
     * 初始化
     *
     * @param currentPageNum  当前页码
     * @param currentPageSize 当前每页数量
     * @return EntPatentInfoResult
     */
    public static EntStandardInfoResult init(int currentPageNum, int currentPageSize) {
        EntStandardInfoResult standardInfoResult = new EntStandardInfoResult();
        standardInfoResult.setTotal(0);
        standardInfoResult.setPageTotal(0);
        standardInfoResult.setCurrentPageNum(currentPageNum);
        standardInfoResult.setCurrentPageSize(currentPageSize);
        standardInfoResult.setNoMore(true);
        standardInfoResult.setEntStandardInfoList(new ArrayList<>());
        return standardInfoResult;
    }

    public List<EntStandardInfo> getEntStandardInfoList() {
        return entStandardInfoList;
    }

    public void setEntStandardInfoList(List<EntStandardInfo> entStandardInfoList) {
        this.entStandardInfoList = entStandardInfoList;
    }

    @Override
    public String toString() {
        return "EntStandardInfoResult{" +
                "entStandardInfoList=" + entStandardInfoList +
                '}';
    }
}
