package com.hanzhong.api.web.service.impl;

import com.hanzhong.api.web.dao.slave.LdRegisterInfoDao;
import com.hanzhong.api.web.model.entity.slave.LdRegisterInfoEntity;
import com.hanzhong.api.web.service.LdDataService;
import com.hanzhong.api.web.util.DateUtils;
import com.hanzhong.api.web.util.business.longdun.model.RegisterInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
 *  
 *  @Description
 *  @Author   luqs   
 *  @Date 2019/1/13 15:24 
 *  @Version  V1.0   
 */
@Service("ldDataService")
public class LdDataServiceImpl implements LdDataService {

    @Resource
    private LdRegisterInfoDao ldRegisterInfoDao;

    @Override
    public int recordRegisterInfo(RegisterInfo registerInfo) {
        // 查询企业信息
        List<LdRegisterInfoEntity> registerInfoEntityList = ldRegisterInfoDao.getRegisterInfoListByQryParam(registerInfo.getEntName());

        // 若不存在此企业，则添加，否则更新
        if (registerInfoEntityList.isEmpty()) {
            LdRegisterInfoEntity registerInfoEntity = convertToRegisterInfoEntity("", registerInfo);
            registerInfoEntity.setPripId(createRandomPripId());
            return ldRegisterInfoDao.addRegisterInfo(registerInfoEntity);
        }

        LdRegisterInfoEntity dbRegisterInfoEntity = registerInfoEntityList.get(0);
        LdRegisterInfoEntity registerInfoEntity = convertToRegisterInfoEntity(dbRegisterInfoEntity.getPripId(), registerInfo);
        registerInfoEntity.setId(dbRegisterInfoEntity.getId());
        return ldRegisterInfoDao.updateRegisterInfo(registerInfoEntity);
    }

    /**
     * 转换成LdRegisterInfoEntity
     *
     * @param pripId       主体身份代码
     * @param registerInfo 企业登记信息
     * @return LdRegisterInfoEntity
     */
    private LdRegisterInfoEntity convertToRegisterInfoEntity(String pripId, RegisterInfo registerInfo) {
        LdRegisterInfoEntity registerInfoEntity = new LdRegisterInfoEntity();
        registerInfoEntity.setPripId(pripId);
        registerInfoEntity.setEntName(registerInfo.getEntName());
        registerInfoEntity.setOldName(registerInfo.getOldName());
        registerInfoEntity.setEngName(registerInfo.getEngName());
        registerInfoEntity.setShxydm(registerInfo.getShxydm());
        registerInfoEntity.setOrgId(registerInfo.getOrgId());
        registerInfoEntity.setFrdb(registerInfo.getFrdb());
        registerInfoEntity.setEntType(registerInfo.getEntType());
        registerInfoEntity.setEntStatus(registerInfo.getEntStatus());
        registerInfoEntity.setRegCap(registerInfo.getRegCap());
        registerInfoEntity.setRegCapCur(registerInfo.getRegCapCur());
        registerInfoEntity.setIndustry(registerInfo.getIndustry());
        registerInfoEntity.setIndustryCode(registerInfo.getIndustryCode());
        registerInfoEntity.setOpScope(registerInfo.getOpScope());
        registerInfoEntity.setRegOrg(registerInfo.getRegOrg());
        registerInfoEntity.setDom(registerInfo.getDom());
        registerInfoEntity.setJwd(registerInfo.getJwd());
        registerInfoEntity.setProvince(registerInfo.getProvince());
        registerInfoEntity.setWebSite(registerInfo.getWebSite());
        registerInfoEntity.setEsDate(DateUtils.parse(registerInfo.getEsDate(), DateUtils.DEFAULT_DATE_FORMAT));
        registerInfoEntity.setOpFrom(DateUtils.parse(registerInfo.getOpFrom(), DateUtils.DEFAULT_DATE_FORMAT));
        registerInfoEntity.setOpTo(DateUtils.parse(registerInfo.getOpTo(), DateUtils.DEFAULT_DATE_FORMAT));
        registerInfoEntity.setApprDate(DateUtils.parse(registerInfo.getApprDate(), DateUtils.DEFAULT_DATE_FORMAT));
        registerInfoEntity.setEndDate(DateUtils.parse(registerInfo.getEndDate(), DateUtils.DEFAULT_DATE_FORMAT));
        registerInfoEntity.setRevDate(DateUtils.parse(registerInfo.getRevDate(), DateUtils.DEFAULT_DATE_FORMAT));
        registerInfoEntity.setCanDate(DateUtils.parse(registerInfo.getCanDate(), DateUtils.DEFAULT_DATE_FORMAT));
        registerInfoEntity.setUpdateTime(DateUtils.getCurrentTimeStamp());
        return registerInfoEntity;
    }

    /**
     * 生成随机主体身份代码
     *
     * @return String
     */
    private String createRandomPripId() {
        return "LD_" + UUID.randomUUID();
    }
}
