package com.hanzhong.data.web.service.impl;

import com.hanzhong.data.web.model.*;
import com.hanzhong.data.web.service.EntPenaltyService;
import com.hanzhong.data.web.util.BusinessHandlingUtils;
import com.hanzhong.data.web.util.DateUtils;
import com.hanzhong.data.web.util.longdun.entpenaltiesinfo.LdPenaltiesInfoApiUtils;
import com.hanzhong.data.web.util.longdun.entpenaltiesinfo.constant.MoveFlagEnum;
import com.hanzhong.data.web.util.longdun.entpenaltiesinfo.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *  
 *  @Description
 *  @Author   luqs   
 *  @Date 2019/6/15 14:48 
 *  @Version  V1.0   
 */
@Service("entPenaltyService")
public class EntPenaltyServiceImpl implements EntPenaltyService {

    private static final Logger logger = LoggerFactory.getLogger(EntPenaltyServiceImpl.class);

    /**
     * 获取企业行政处罚信息
     *
     * @param qryParam 查询参数
     * @return List<EntAdminPenaltyInfo>
     */
    @Override
    public List<EntAdminPenaltyInfo> getEntAdminPenaltyInfoList(EntAdminPenaltyInfoQryParam qryParam) {
        // 创建企业行政处罚信息查询参数
        LawAdminInfoQryParam adminInfoQryParam = createLawAdminInfoQryParam(qryParam);

        // 获取企业行政处罚信息
        logger.info("LdPenaltiesInfoApiUtils.getLawAdminInfoList()的参数值：【{}】", adminInfoQryParam);
        List<LawAdminInfo> lawAdminInfoList = LdPenaltiesInfoApiUtils.getLawAdminInfoList(adminInfoQryParam);
        logger.info("LdPenaltiesInfoApiUtils.getLawAdminInfoList()的返回值：【{}】", lawAdminInfoList);

        // 转换成List<EntAdminPenaltyInfo>
        List<EntAdminPenaltyInfo> adminPenaltyInfoList = convertToEntAdminPenaltyInfoList(lawAdminInfoList);
        logger.debug("List<LawAdminInfo>：【{}】，转换成List<EntAdminPenaltyInfo>的结果值：【{}】", lawAdminInfoList, adminPenaltyInfoList);
        return adminPenaltyInfoList;
    }

    /**
     * 获取企业经营异常信息
     *
     * @param qryParam 查询参数
     * @return List<EntOptExceptionInfo>
     */
    @Override
    public List<EntOptExceptionInfo> getEntOptExceptionInfoList(EntOptExceptionInfoQryParam qryParam) {
        // 创建企业经营异常信息查询参数
        OperatExcepRotaQryParam rotaQryParam = createOperatExcepRotaQryParam(qryParam);

        // 获取企业经营异常信息
        logger.info("LdPenaltiesInfoApiUtils.getOperatingExceptionRotaList()的参数值：【{}】", rotaQryParam);
        List<OperatExcepRotaInfo> exceptionRotaList = LdPenaltiesInfoApiUtils.getOperatingExceptionRotaList(rotaQryParam);
        logger.info("LdPenaltiesInfoApiUtils.getOperatingExceptionRotaList()的返回值：【{}】", exceptionRotaList);

        // 转换成List<EntOptExceptionInfo>
        List<EntOptExceptionInfo> optExceptionInfoList = convertToEntOptExceptionInfoList(exceptionRotaList);
        logger.debug("List<OperatExcepRotaInfo>：【{}】，转换成List<EntOptExceptionInfo>的结果值：【{}】", exceptionRotaList, optExceptionInfoList);
        return optExceptionInfoList;
    }

    /**
     * 获取企业严重违法失信企业（黑名单）信息
     *
     * @param qryParam 查询参数
     * @return List<EntSeriousDishonestyInfo>
     */
    @Override
    public List<EntSeriousDishonestyInfo> getEntSeriousDishonestyInfoList(EntSeriousDishonestyInfoQryParam qryParam) {
        // 创建企业严重违法失信企业（黑名单）信息查询参数
        SeriousDishonestyInfoQryParam dishonestyInfoQryParam = createSeriousDishonestyInfoQryParam(qryParam);

        // 获取企业严重违法失信企业（黑名单）信息
        logger.info("LdPenaltiesInfoApiUtils.getSeriousDishonestyInfoList()的参数值：【{}】", dishonestyInfoQryParam);
        List<SeriousDishonestyInfo> dishonestyInfoList = LdPenaltiesInfoApiUtils.getSeriousDishonestyInfoList(dishonestyInfoQryParam);
        logger.info("LdPenaltiesInfoApiUtils.getSeriousDishonestyInfoList()的返回值：【{}】", dishonestyInfoList);

        // 转换成List<EntSeriousDishonestyInfo>
        List<EntSeriousDishonestyInfo> entSeriousDishonestyInfoList = convertToEntSeriousDishonestyInfoList(dishonestyInfoList);
        logger.debug("List<SeriousDishonestyInfo>：【{}】，转换成List<EntSeriousDishonestyInfo>的结果值：【{}】", dishonestyInfoList, entSeriousDishonestyInfoList);
        return entSeriousDishonestyInfoList;
    }

    /**
     * 创建企业行政处罚信息查询参数
     *
     * @param qryParam 企业行政处罚信息查询参数
     * @return LawAdminInfoQryParam
     */
    private LawAdminInfoQryParam createLawAdminInfoQryParam(EntAdminPenaltyInfoQryParam qryParam) {
        LawAdminInfoQryParam infoQryParam = new LawAdminInfoQryParam();
        infoQryParam.setEntName(qryParam.getEntName());
        return infoQryParam;
    }

    /**
     * 转换成List<EntAdminPenaltyInfo>
     *
     * @param lawAdminInfoList 企业行政处罚信息集
     * @return List<EntAdminPenaltyInfo>
     */
    private List<EntAdminPenaltyInfo> convertToEntAdminPenaltyInfoList(List<LawAdminInfo> lawAdminInfoList) {
        if (lawAdminInfoList == null || lawAdminInfoList.isEmpty()) {
            return Collections.emptyList();
        }

        List<EntAdminPenaltyInfo> adminPenaltyInfoList = new ArrayList<>();
        for (LawAdminInfo adminInfo : lawAdminInfoList) {
            EntAdminPenaltyInfo adminPenaltyInfo = new EntAdminPenaltyInfo();
            // 行政处罚决定书文号
            adminPenaltyInfo.setPenDecNo(adminInfo.getPenDecNo());
            // 违法行为类型
            adminPenaltyInfo.setIllegalActType(adminInfo.getIllegActType());
            // 行政处罚内容
            adminPenaltyInfo.setPenResult(adminInfo.getPenResult());
            // 决定机关名称
            adminPenaltyInfo.setCaseDep(adminInfo.getCaseDep());
            // 处罚决定日期
            adminPenaltyInfo.setPenDecIssDate(DateUtils.parse(adminInfo.getPenDecIssDate()));
            // 公示时间
            adminPenaltyInfo.setPubDate(DateUtils.parse(adminInfo.getPubDate()));
            adminPenaltyInfoList.add(adminPenaltyInfo);
        }
        return adminPenaltyInfoList;
    }

    /**
     * 创建企业经营异常信息查询参数
     *
     * @param qryParam 企业经营异常信息查询参数
     * @return OperatExcepRotaQryParam
     */
    private OperatExcepRotaQryParam createOperatExcepRotaQryParam(EntOptExceptionInfoQryParam qryParam) {
        OperatExcepRotaQryParam infoQryParam = new OperatExcepRotaQryParam();
        infoQryParam.setEntName(qryParam.getEntName());
        return infoQryParam;
    }

    /**
     * 转换成List<EntOptExceptionInfo>
     *
     * @param excepRotaInfoList 企业经营异常信息集
     * @return List<EntOptExceptionInfo>
     */
    private List<EntOptExceptionInfo> convertToEntOptExceptionInfoList(List<OperatExcepRotaInfo> excepRotaInfoList) {
        if (excepRotaInfoList == null || excepRotaInfoList.isEmpty()) {
            return Collections.emptyList();
        }

        List<EntOptExceptionInfo> optExceptionInfoList = new ArrayList<>();
        for (OperatExcepRotaInfo rotaInfo : excepRotaInfoList) {
            EntOptExceptionInfo exceptionInfo = new EntOptExceptionInfo();
            // 列入经营异常名录原因
            exceptionInfo.setInReason(rotaInfo.getInReason());
            // 列入日期
            exceptionInfo.setInDate(DateUtils.parse(rotaInfo.getInDate()));
            // 作出决定机关（列入）
            exceptionInfo.setInRegOrg(rotaInfo.getInRegOrg());
            // 移出经营异常名录原因
            exceptionInfo.setOutReason(rotaInfo.getOutReason());
            // 移出日期，格式：yyyy-MM-dd
            exceptionInfo.setOutDate(DateUtils.parse(rotaInfo.getOutDate()));
            // 作出决定机关（移出）
            exceptionInfo.setOutRegOrg(rotaInfo.getOutRegOrg());
            // 是否移除
            exceptionInfo.setMoveFlag(MoveFlagEnum.YES.getValue().equals(rotaInfo.getIsMove()));
            optExceptionInfoList.add(exceptionInfo);
        }
        return optExceptionInfoList;
    }

    /**
     * 创建企业严重违法失信企业（黑名单）信息查询参数
     *
     * @param qryParam 企业严重违法失信企业（黑名单）信息查询参数
     * @return SeriousDishonestyInfoQryParam
     */
    private SeriousDishonestyInfoQryParam createSeriousDishonestyInfoQryParam(EntSeriousDishonestyInfoQryParam qryParam) {
        SeriousDishonestyInfoQryParam infoQryParam = new SeriousDishonestyInfoQryParam();
        infoQryParam.setEntName(qryParam.getEntName());
        infoQryParam.setPageNo(BusinessHandlingUtils.getDefaultPageNum(qryParam.getPageNum()));
        infoQryParam.setPageSize(BusinessHandlingUtils.getDefaultPageSize(qryParam.getPageSize()));
        return infoQryParam;
    }

    /**
     * 转换成List<EntSeriousDishonestyInfo>
     *
     * @param dishonestyInfoList 企业严重违法失信企业（黑名单）信息集
     * @return List<EntSeriousDishonestyInfo>
     */
    private List<EntSeriousDishonestyInfo> convertToEntSeriousDishonestyInfoList(List<SeriousDishonestyInfo> dishonestyInfoList) {
        if (dishonestyInfoList == null || dishonestyInfoList.isEmpty()) {
            return Collections.emptyList();
        }

        List<EntSeriousDishonestyInfo> entSeriousDishonestyInfoList = new ArrayList<>();
        for (SeriousDishonestyInfo dishonestyInfo : dishonestyInfoList) {
            EntSeriousDishonestyInfo entSeriousDishonestyInfo = new EntSeriousDishonestyInfo();
            // 类别
            entSeriousDishonestyInfo.setType(dishonestyInfo.getType());
            // 列入严重违法失信企业名单（黑名单）原因
            entSeriousDishonestyInfo.setInReason(dishonestyInfo.getInReason());
            // 列入日期
            entSeriousDishonestyInfo.setInDate(DateUtils.parse(dishonestyInfo.getInDate()));
            // 作出决定机关（列入）
            entSeriousDishonestyInfo.setInRegOrg(dishonestyInfo.getInRegOrg());
            // 移出严重违法失信企业名单（黑名单）原因
            entSeriousDishonestyInfo.setOutReason(dishonestyInfo.getOutReason());
            // 移出日期，格式：yyyy-MM-dd
            entSeriousDishonestyInfo.setOutDate(DateUtils.parse(dishonestyInfo.getOutDate()));
            // 作出决定机关（移出）
            entSeriousDishonestyInfo.setOutRegOrg(dishonestyInfo.getOutRegOrg());
            entSeriousDishonestyInfoList.add(entSeriousDishonestyInfo);
        }
        return entSeriousDishonestyInfoList;
    }
}
