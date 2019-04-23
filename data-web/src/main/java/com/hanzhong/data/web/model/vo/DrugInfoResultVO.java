package com.hanzhong.data.web.model.vo;

import java.util.List;

/**
 * 医药药品信息结果
 *
 * @author yifei
 * @date 2019/3/27
 */
public class DrugInfoResultVO extends BasePageVO {
    /**
     * 医药药品信息集
     */
    private List<DrugInfoVO> drugInfoVOList;

    public List<DrugInfoVO> getDrugInfoVOList() {
        return drugInfoVOList;
    }

    public void setDrugInfoVOList(List<DrugInfoVO> drugInfoVOList) {
        this.drugInfoVOList = drugInfoVOList;
    }

    @Override
    public String toString() {
        return "DrugInfoResultVO{" +
                "drugInfoVOList=" + drugInfoVOList +
                '}';
    }
}
