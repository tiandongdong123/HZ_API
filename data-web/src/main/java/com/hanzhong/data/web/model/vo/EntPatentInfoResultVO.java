package com.hanzhong.data.web.model.vo;

import java.util.List;

/**
 * 企业专利信息结果
 *
 * @author yifei
 * @date 2019/3/27
 */
public class EntPatentInfoResultVO extends BasePageVO {
    /**
     * 企业专利信息集
     */
    private List<EntPatentInfoVO> entPatentInfoVOList;

    public List<EntPatentInfoVO> getEntPatentInfoVOList() {
        return entPatentInfoVOList;
    }

    public void setEntPatentInfoVOList(List<EntPatentInfoVO> entPatentInfoVOList) {
        this.entPatentInfoVOList = entPatentInfoVOList;
    }

    @Override
    public String toString() {
        return "EntPatentInfoResultVO{" +
                "entPatentInfoVOList=" + entPatentInfoVOList +
                '}';
    }
}
