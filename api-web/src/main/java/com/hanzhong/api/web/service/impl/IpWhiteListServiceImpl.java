package com.hanzhong.api.web.service.impl;

import com.hanzhong.api.web.constant.cmnenum.IpStatusEnum;
import com.hanzhong.api.web.dao.master.TblIpWhiteListDao;
import com.hanzhong.api.web.model.bo.IpWhiteListQryBO;
import com.hanzhong.api.web.model.entity.master.TblIpWhiteListEntity;
import com.hanzhong.api.web.service.IpWhiteListService;
import com.hanzhong.api.web.util.IpUtils;
import com.hanzhong.api.web.util.business.auth.IpAuthUtils;
import com.hanzhong.api.web.util.business.auth.model.IpWhiteList;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 *  
 *  @Description
 *  @Author   luqs   
 *  @Date 2019/1/4 19:58 
 *  @Version  V1.0   
 */
@Service("ipWhiteListService")
public class IpWhiteListServiceImpl implements IpWhiteListService {

    private static final Logger logger = LoggerFactory.getLogger(IpWhiteListServiceImpl.class);
    private static final String SEPARATOR_WAVE = "~";

    @Autowired
    private TblIpWhiteListDao tblIpWhiteListDao;

    @Override
    public boolean cacheIpWhiteList() {
        IpAuthUtils.cacheIpWhiteList(getNormalIpWhiteList());
        return false;
    }

    /**
     * 获取正常状态的ip白名单
     *
     * @return List<IpWhiteList>
     */
    private List<IpWhiteList> getNormalIpWhiteList() {
        IpWhiteListQryBO listQryBO = new IpWhiteListQryBO();
        listQryBO.setStatus(IpStatusEnum.NORMAL.getKey());
        List<TblIpWhiteListEntity> ipWhiteListEntityList = tblIpWhiteListDao.getIpWhiteList(listQryBO);

        List<IpWhiteList> ipWhiteLists = new ArrayList<>();
        boolean rightIpSegmentFlag;
        for (TblIpWhiteListEntity entity : ipWhiteListEntityList) {
            String[] ipArray = StringUtils.split(entity.getIpSegment(), SEPARATOR_WAVE);
            rightIpSegmentFlag = ipArray != null && ipArray.length == 2 && IpUtils.checkIpFormat(ipArray[0]) && IpUtils.checkIpFormat(ipArray[1]);
            if (rightIpSegmentFlag) {
                IpWhiteList ipWhiteList = new IpWhiteList();
                ipWhiteList.setName(entity.getName());
                ipWhiteList.setStartIp(ipArray[0]);
                ipWhiteList.setEndIp(ipArray[1]);
                ipWhiteLists.add(ipWhiteList);
            } else {
                logger.warn("TblIpWhiteListEntity：【{}】，ip段不符合规范", entity);
            }
        }
        return ipWhiteLists;
    }
}
