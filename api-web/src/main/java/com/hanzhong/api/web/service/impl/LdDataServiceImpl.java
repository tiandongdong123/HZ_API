package com.hanzhong.api.web.service.impl;

import com.hanzhong.api.web.constant.CmnConstant;
import com.hanzhong.api.web.dao.slave.LdRegisterInfoDao;
import com.hanzhong.api.web.model.bo.LdRegisterInfoQryBO;
import com.hanzhong.api.web.model.entity.slave.LdRegisterInfoEntity;
import com.hanzhong.api.web.service.LdDataService;
import com.hanzhong.api.web.util.DateUtils;
import com.hanzhong.api.web.util.business.longdun.model.RegisterInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
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
    public List<LdRegisterInfoEntity> getRegisterInfoListByQryParam(LdRegisterInfoQryBO qryBO) {
        return ldRegisterInfoDao.getRegisterInfoListByQryParam(qryBO);
    }

    @Override
    public int recordRegisterInfo(RegisterInfo registerInfo) {
        LdRegisterInfoQryBO qryBO = new LdRegisterInfoQryBO();
        qryBO.setEntName(registerInfo.getEntName());
        // 查询企业信息
        List<LdRegisterInfoEntity> registerInfoEntityList = ldRegisterInfoDao.getRegisterInfoListByQryParam(qryBO);

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
        registerInfoEntity.setEsDate(parseByDateFormat(registerInfo.getEsDate()));
        registerInfoEntity.setOpFrom(parseByDateFormat(registerInfo.getOpFrom()));
        registerInfoEntity.setOpTo(CmnConstant.LONG_TERM_WORD.equals(registerInfo.getOpTo()) ? null : parseByDateFormat(registerInfo.getOpTo()));
        registerInfoEntity.setApprDate(parseByDateFormat(registerInfo.getApprDate()));
        registerInfoEntity.setEndDate(parseByDateFormat(registerInfo.getEndDate()));
        registerInfoEntity.setRevDate(parseByDateFormat(registerInfo.getRevDate()));
        registerInfoEntity.setCanDate(parseByDateFormat(registerInfo.getCanDate()));
        registerInfoEntity.setUpdateTime(DateUtils.getCurrentTimeStamp());
        return registerInfoEntity;
    }

    /**
     * 解析成Date
     *
     * @param dateStr 时间字符串，格式：yyyy-MM-dd
     * @return Date
     */
    private Date parseByDateFormat(String dateStr) {
        if (StringUtils.isBlank(dateStr)) {
            return null;
        }

        return DateUtils.parse(dateStr, DateUtils.DEFAULT_DATE_FORMAT);
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
