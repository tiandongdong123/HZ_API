package com.hanzhong.data.web.model.vo;

import java.io.Serializable;

/**
 *  
 *  @Description
 *  @Author   luqs   
 *  @Date 2019/3/14 17:58 
 *  @Version  V1.0   
 */
public class ChangeDataPkgInfoVO implements Serializable {
    private static final long serialVersionUID = -4963266899668807475L;
    /**
     * 数据包id
     */
    private String dataPackId;
    /**
     * 数据包名称
     */
    private String dataPackName;
    /**
     * 密码
     */
    private String password;
    /**
     * 数据时间，格式：yyyy-MM-dd HH:mm:ss
     */
    private String dataTime;
    /**
     * 记录数据
     */
    private Integer recordCount;
    /**
     * 文件大小
     */
    private String fileSize;
    /**
     * 下载url
     */
    private String downloadUrl;

    public String getDataPackId() {
        return dataPackId;
    }

    public void setDataPackId(String dataPackId) {
        this.dataPackId = dataPackId;
    }

    public String getDataPackName() {
        return dataPackName;
    }

    public void setDataPackName(String dataPackName) {
        this.dataPackName = dataPackName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDataTime() {
        return dataTime;
    }

    public void setDataTime(String dataTime) {
        this.dataTime = dataTime;
    }

    public Integer getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(Integer recordCount) {
        this.recordCount = recordCount;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    @Override
    public String toString() {
        return "ChangeDataPkgInfoVO{" +
                "dataPackId='" + dataPackId + '\'' +
                ", dataPackName='" + dataPackName + '\'' +
                ", password='" + password + '\'' +
                ", dataTime='" + dataTime + '\'' +
                ", recordCount=" + recordCount +
                ", fileSize='" + fileSize + '\'' +
                ", downloadUrl='" + downloadUrl + '\'' +
                '}';
    }
}
