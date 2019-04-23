package com.hanzhong.data.web.service.impl;

import com.hanzhong.data.web.dao.master.HighTechInfoDao;
import com.hanzhong.data.web.model.HighTechEntInputParam;
import com.hanzhong.data.web.model.bo.HighTechEntQryBO;
import com.hanzhong.data.web.model.entity.master.HighTechEntInfoEntity;
import com.hanzhong.data.web.service.HighTechEntService;
import com.hanzhong.data.web.util.BusinessHandlingUtils;
import com.hanzhong.data.web.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author yifei
 * @date 2019/4/13
 */
@Service("highTechEntService")
public class HighTechEntServiceImpl implements HighTechEntService {

    private static final Logger logger = LoggerFactory.getLogger(HighTechEntServiceImpl.class);

    @Resource
    private HighTechInfoDao highTechInfoDao;

    /**
     * 录入高新企业信息
     *
     * @param inputParam 录入参数
     * @return boolean true：录入成功
     */
    @Override
    public boolean inputHighTechEntInfo(HighTechEntInputParam inputParam) {
        logger.info("录入高新企业信息的参数值：【{}】", inputParam);
        boolean existFlag = existHighTechEntInfo(inputParam);
        logger.info("录入的高新企业信息是否存在：【{}】", existFlag);
        HighTechEntInfoEntity highTechEntInfoEntity = createHighTechEntInfoEntity(inputParam);
        boolean inputFlag;
        if (existFlag) {
            inputFlag = updateHighTechEntInfo(highTechEntInfoEntity);
        } else {
            inputFlag = insertHighTechEntInfo(highTechEntInfoEntity);
        }
        logger.info("录入高新企业信息的返回值：【{}】", inputFlag);
        return inputFlag;
    }

    /**
     * 是否存在高新企业
     *
     * @param inputParam 录入参数
     * @return boolean true：存在
     */
    private boolean existHighTechEntInfo(HighTechEntInputParam inputParam) {
        HighTechEntQryBO qryBO = new HighTechEntQryBO();
        qryBO.setUsCreditCode(inputParam.getUsCreditCode());
        logger.debug("HighTechInfoDao.getHighTechInfoList()的参数值：【{}】", qryBO);
        List<HighTechEntInfoEntity> entInfoEntityList = highTechInfoDao.getHighTechInfoList(qryBO);
        logger.debug("HighTechInfoDao.getHighTechInfoList()的返回值：【{}】", entInfoEntityList);
        return BusinessHandlingUtils.isNotBlankCollection(entInfoEntityList);
    }

    /**
     * 创建高新企业信息
     *
     * @param inputParam 录入参数
     * @return HighTechEntInfoEntity
     */
    private HighTechEntInfoEntity createHighTechEntInfoEntity(HighTechEntInputParam inputParam) {
        HighTechEntInfoEntity highTechEntInfoEntity = new HighTechEntInfoEntity();
        highTechEntInfoEntity.setEntName(inputParam.getEntName());
        highTechEntInfoEntity.setUsCreditCode(inputParam.getUsCreditCode());
        highTechEntInfoEntity.setUpdateTime(DateUtils.getCurrentTimeStamp());
        return highTechEntInfoEntity;
    }

    /**
     * 更新高新企业信息
     *
     * @param highTechEntInfoEntity 高新企业信息
     * @return boolean true：更新成功
     */
    private boolean updateHighTechEntInfo(HighTechEntInfoEntity highTechEntInfoEntity) {
        logger.debug("HighTechInfoDao.updateHighTechInfo()的参数值：【{}】", highTechEntInfoEntity);
        int updateCount = highTechInfoDao.updateHighTechInfo(highTechEntInfoEntity);
        logger.debug("HighTechInfoDao.updateHighTechInfo()的返回值：【{}】", updateCount);
        return updateCount > 0;
    }

    /**
     * 插入高新企业信息
     *
     * @param highTechEntInfoEntity 高新企业信息
     * @return boolean true：参入成功
     */
    private boolean insertHighTechEntInfo(HighTechEntInfoEntity highTechEntInfoEntity) {
        logger.debug("HighTechInfoDao.insertHighTechInfo()的参数值：【{}】", highTechEntInfoEntity);
        int updateCount = highTechInfoDao.insertHighTechInfo(highTechEntInfoEntity);
        logger.debug("HighTechInfoDao.insertHighTechInfo()的返回值：【{}】", updateCount);
        return updateCount > 0;
    }

}
