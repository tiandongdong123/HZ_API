package com.hanzhong.data.web.model.vo;

import java.util.List;

/**
 * 企业标准信息结果
 *
 * @author yifei
 * @date 2019/3/27
 */
public class EntStandardInfoResultVO extends BasePageVO {
    /**
     * 企业标准信息集
     */
    private List<EntStandardInfoVO> entStandardInfoVOList;

    public List<EntStandardInfoVO> getEntStandardInfoVOList() {
        return entStandardInfoVOList;
    }

    public void setEntStandardInfoVOList(List<EntStandardInfoVO> entStandardInfoVOList) {
        this.entStandardInfoVOList = entStandardInfoVOList;
    }

    @Override
    public String toString() {
        return "EntStandardInfoResultVO{" +
                "entStandardInfoVOList=" + entStandardInfoVOList +
                '}';
    }
}
