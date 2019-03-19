package com.hanzhong.data.web.service.impl;

import com.hanzhong.data.web.constant.DataPackageStatusEnum;
import com.hanzhong.data.web.model.ChangeDataPackageInfo;
import com.hanzhong.data.web.model.ChangeDataPackageInfoQryParam;
import com.hanzhong.data.web.service.LdDataService;
import com.hanzhong.data.web.util.longdun.LdApiUtils;
import com.hanzhong.data.web.util.longdun.constant.DataPkgActionTypeEnum;
import com.hanzhong.data.web.util.longdun.model.ApiResult;
import com.hanzhong.data.web.util.longdun.model.ChangeDataPkgInfo;
import com.hanzhong.data.web.util.longdun.model.ChangeDataPkgListQryParam;
import com.hanzhong.data.web.util.longdun.model.DataPackageFinishParam;
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
 *  @Date 2019/1/13 15:24 
 *  @Version  V1.0   
 */
@Service("ldDataService")
public class LdDataServiceImpl implements LdDataService {

    private static final Logger logger = LoggerFactory.getLogger(LdDataServiceImpl.class);

    /**
     * 获取变更数据包
     *
     * @param qryParam 查询参数
     * @return List<ChangeDataPackageInfo>
     */
    @Override
    public List<ChangeDataPackageInfo> getChangeDataPackageList(ChangeDataPackageInfoQryParam qryParam) {
        // 创建变更数据包集查询参数
        ChangeDataPkgListQryParam listQryParam = createChangeDataPkgListQryParam(qryParam);
        // 获取变更数据包列表
        logger.info("LdApiUtils.getChangeDataPackageListApiResult()的参数值：【{}】", listQryParam);
        ApiResult apiResult = LdApiUtils.getChangeDataPackageListApiResult(listQryParam);
        List<ChangeDataPkgInfo> pkgInfoList = LdApiUtils.getChangeDataPackageList(apiResult);
        logger.info("LdApiUtils.getChangeDataPackageListApiResult()的返回值：【{}】", pkgInfoList);
        // 转换成List<ChangeDataPackageInfo>
        List<ChangeDataPackageInfo> packageInfoList = convertToChangeDataPackageInfoList(pkgInfoList);
        logger.debug("转换成List<ChangeDataPackageInfo>的结果值：【{}】", packageInfoList);
        return packageInfoList;
    }

    /**
     * 变更数据包回馈状态
     *
     * @param dataPkgId 数据包id
     * @return boolean true: 变更成功
     */
    @Override
    public boolean monitorDataPackageFinish(String dataPkgId) {
        DataPackageFinishParam finishParam = new DataPackageFinishParam();
        finishParam.setDataPackId(dataPkgId);

        ApiResult apiResult = LdApiUtils.monitorDataPackageFinishApiResult(finishParam);
        boolean monitorFlag = LdApiUtils.monitorDataPackageFinish(apiResult);

        return monitorFlag;
    }

    /**
     * 创建变更数据包集查询参数
     *
     * @param qryParam 查询参数
     * @return ChangeDataPkgListQryParam
     */
    private ChangeDataPkgListQryParam createChangeDataPkgListQryParam(ChangeDataPackageInfoQryParam qryParam) {
        ChangeDataPkgListQryParam listQryParam = new ChangeDataPkgListQryParam();
        listQryParam.setActionTypeEnum(convertToDataPkgActionTypeEnum(qryParam.getStatusEnum()));
        listQryParam.setStartTime(qryParam.getStartTime());
        listQryParam.setEndTime(qryParam.getEndTime());
        listQryParam.setPageNo(qryParam.getPageNum());
        listQryParam.setPageSize(qryParam.getPageSize());
        return listQryParam;
    }

    /**
     * 转换成DataPackageActionTypeEnum
     *
     * @param statusEnum 变更数据包回馈状态枚举
     * @return DataPackageActionTypeEnum 若匹配不上，则返回null
     */
    private DataPkgActionTypeEnum convertToDataPkgActionTypeEnum(DataPackageStatusEnum statusEnum) {
        if (statusEnum == null) {
            return null;
        }

        switch (statusEnum) {
            case NO_READ:
                return DataPkgActionTypeEnum.NO_READ;
            case READED:
                return DataPkgActionTypeEnum.READED;
            default:
                logger.warn("变更数据包的回馈状态枚举(DataPackageStatusEnum【{}】)匹配不上相应数据", statusEnum.getValue());
                return null;
        }
    }

    /**
     * 转换成List<ChangeDataPackageInfo>
     *
     * @param pkgInfoList 变更数据包信息集
     * @return List<ChangeDataPackageInfo>
     */
    private List<ChangeDataPackageInfo> convertToChangeDataPackageInfoList(List<ChangeDataPkgInfo> pkgInfoList) {
        if (pkgInfoList == null || pkgInfoList.isEmpty()) {
            return Collections.emptyList();
        }

        List<ChangeDataPackageInfo> packageInfoList = new ArrayList<>();
        for (ChangeDataPkgInfo pkgInfo : pkgInfoList) {
            ChangeDataPackageInfo packageInfo = new ChangeDataPackageInfo();
            packageInfo.setDataPackId(pkgInfo.getDataPackId());
            packageInfo.setDataPackName(pkgInfo.getDataPackName());
            packageInfo.setDataPackTime(pkgInfo.getDataPackTime());
            packageInfo.setFileSize(pkgInfo.getFileSize());
            packageInfo.setRecordCount(pkgInfo.getRecordCount());
            packageInfo.setCode(pkgInfo.getCode());
            packageInfo.setPassword(pkgInfo.getPassword());
            packageInfoList.add(packageInfo);
        }

        return packageInfoList;
    }

}
