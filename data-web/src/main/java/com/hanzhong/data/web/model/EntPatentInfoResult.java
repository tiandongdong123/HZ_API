package com.hanzhong.data.web.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 企业专利信息结果
 *
 * @author yifei
 * @date 2019/3/27
 */
public class EntPatentInfoResult extends BasePage {
    /**
     * 企业专利信息集
     */
    private List<EntPatentInfo> entPatentInfoList;

    /**
     * 初始化
     *
     * @param currentPageNum  当前页码
     * @param currentPageSize 当前每页数量
     * @return EntPatentInfoResult
     */
    public static EntPatentInfoResult init(int currentPageNum, int currentPageSize) {
        EntPatentInfoResult patentInfoResult = new EntPatentInfoResult();
        patentInfoResult.setTotal(0);
        patentInfoResult.setPageTotal(0);
        patentInfoResult.setCurrentPageNum(currentPageNum);
        patentInfoResult.setCurrentPageSize(currentPageSize);
        patentInfoResult.setNoMore(true);
        patentInfoResult.setEntPatentInfoList(new ArrayList<>());
        return patentInfoResult;
    }

    public List<EntPatentInfo> getEntPatentInfoList() {
        return entPatentInfoList;
    }

    public void setEntPatentInfoList(List<EntPatentInfo> entPatentInfoList) {
        this.entPatentInfoList = entPatentInfoList;
    }

    @Override
    public String toString() {
        return "EntPatentInfoResult{" +
                "entPatentInfoList=" + entPatentInfoList +
                '}';
    }
}
