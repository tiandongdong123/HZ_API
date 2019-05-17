package com.hanzhong.data.web.service;

import com.hanzhong.data.web.model.HighTechEntInputParam;

/**
 * 高新企业
 *
 * @author yifei
 * @date 2019/4/13
 */
public interface HighTechEntService {

    /**
     * 录入高新企业信息
     *
     * @param inputParam 录入参数
     * @return boolean true：录入成功
     */
    boolean inputHighTechEntInfo(HighTechEntInputParam inputParam);
}
