package com.hanzhong.data.web.util.longdun.datapackageinfo.model;

/**
 * @author yifei
 * @date 2019/3/11
 */
public class ChangeDataPkgInfo {
    /**
     * 数据包id
     */
    private String dataPackId;
    /**
     * 数据包名称
     */
    private String dataPackName;
    /**
     * 时间戳（毫秒）
     */
    private Long dataPackTime;
    /**
     * 记录数据
     */
    private Integer recordCount;
    /**
     * 文件大小
     */
    private Integer fileSize;
    /**
     * 业务编码
     */
    private String code;
    /**
     * 密码
     */
    private String password;

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

    public Long getDataPackTime() {
        return dataPackTime;
    }

    public void setDataPackTime(Long dataPackTime) {
        this.dataPackTime = dataPackTime;
    }

    public Integer getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(Integer recordCount) {
        this.recordCount = recordCount;
    }

    public Integer getFileSize() {
        return fileSize;
    }

    public void setFileSize(Integer fileSize) {
        this.fileSize = fileSize;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "ChangeDataPkgInfo{" +
                "dataPackId='" + dataPackId + '\'' +
                ", dataPackName='" + dataPackName + '\'' +
                ", dataPackTime='" + dataPackTime + '\'' +
                ", recordCount=" + recordCount +
                ", fileSize=" + fileSize +
                ", code='" + code + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
