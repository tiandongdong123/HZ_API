package com.hanzhong.data.web.service.impl;

import com.hanzhong.data.web.constant.DataPackageStatusEnum;
import com.hanzhong.data.web.model.ChangeDataPackageInfo;
import com.hanzhong.data.web.model.ChangeDataPackageInfoQryParam;
import com.hanzhong.data.web.service.LdDataService;
import com.hanzhong.data.web.util.BusinessHandlingUtils;
import com.hanzhong.data.web.util.longdun.datapackageinfo.LdDataPackageInfoApiUtils;
import com.hanzhong.data.web.util.longdun.datapackageinfo.constant.DataPkgActionTypeEnum;
import com.hanzhong.data.web.util.longdun.datapackageinfo.model.ChangeDataPkgInfo;
import com.hanzhong.data.web.util.longdun.datapackageinfo.model.ChangeDataPkgListQryParam;
import com.hanzhong.data.web.util.longdun.datapackageinfo.model.DataPackageFinishParam;
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
        logger.info("LdDataPackageInfoApiUtils.getChangeDataPkgInfoList()的参数值：【{}】", listQryParam);
        List<ChangeDataPkgInfo> pkgInfoList = LdDataPackageInfoApiUtils.getChangeDataPkgInfoList(listQryParam);
        logger.info("LdDataPackageInfoApiUtils.getChangeDataPkgInfoList()的返回值：【{}】", pkgInfoList);

        // 转换成List<ChangeDataPackageInfo>
        List<ChangeDataPackageInfo> packageInfoList = convertToChangeDataPackageInfoList(pkgInfoList);
        logger.debug("List<ChangeDataPkgInfo>：【{}】，转换成List<ChangeDataPackageInfo>的结果值：【{}】", pkgInfoList, packageInfoList);
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

        logger.info("LdDataPackageInfoApiUtils.monitorDataPackageFinishFlag()的参数值：【{}】", finishParam);
        boolean monitorFlag = LdDataPackageInfoApiUtils.monitorDataPackageFinishFlag(finishParam);
        logger.info("LdDataPackageInfoApiUtils.monitorDataPackageFinishFlag()的返回值：【{}】", monitorFlag);
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
        // 数据包回馈状态
        listQryParam.setActionTypeEnum(convertToDataPkgActionTypeEnum(qryParam.getStatusEnum()));
        // 起始时间，格式：yyyy-MM-dd HH:mm:ss
        listQryParam.setStartTime(qryParam.getStartTime());
        // 结束时间，格式：yyyy-MM-dd HH:mm:ss
        listQryParam.setEndTime(qryParam.getEndTime());
        // 页码
        listQryParam.setPageNo(BusinessHandlingUtils.getDefaultPageNum(qryParam.getPageNum()));
        // 每页数量
        listQryParam.setPageSize(BusinessHandlingUtils.getDefaultPageSize(qryParam.getPageSize()));
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
            // 数据包id
            packageInfo.setDataPackId(pkgInfo.getDataPackId());
            // 数据包名
            packageInfo.setDataPackName(pkgInfo.getDataPackName());
            // 数据包时间戳
            packageInfo.setDataPackTime(pkgInfo.getDataPackTime());
            // 文件大小
            packageInfo.setFileSize(pkgInfo.getFileSize());
            // 记录数据
            packageInfo.setRecordCount(pkgInfo.getRecordCount());
            // 业务编码
            packageInfo.setCode(pkgInfo.getCode());
            // 密码
            packageInfo.setPassword(pkgInfo.getPassword());
            packageInfoList.add(packageInfo);
        }
        return packageInfoList;
    }

}
