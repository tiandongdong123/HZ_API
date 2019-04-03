package com.hanzhong.data.web.model.entity.master;

/**
 * @author yifei
 * @date 2019/3/27
 */
public class TblPatentEntity {
    /**
     * id
     */
    private Long id;
    /**
     * 公开（公告）号
     */
    private String pubNum;
    /**
     * 公开（公告）日
     */
    private String pubDate;
    /**
     * 申请号
     */
    private String patentId;
    /**
     * 申请日
     */
    private String appDate;
    /**
     * 专利号
     */
    private String patentNum;
    /**
     * 专利名
     */
    private String patentTitle;
    /**
     * 主分类号_ys
     */
    private String ysMainClassCode;
    /**
     * 分类号_ys
     */
    private String ysClassCode;
    /**
     * 分案原申请号
     */
    private String oldAppNum;
    /**
     * 优先权
     */
    private String priority;
    /**
     * 申请（专利权）人
     */
    private String proposerName;
    /**
     * 发明（设计）人
     */
    private String invName;
    /**
     * 地址
     */
    private String appAddress;
    /**
     * 颁证日
     */
    private String awardDate;
    /**
     * 国际申请
     */
    private String intApp;
    /**
     * 国际公布
     */
    private String intPub;
    /**
     * 进入国家日期
     */
    private String nationDate;
    /**
     * 专利代理机构
     */
    private String agyOrgName;
    /**
     * 代理人
     */
    private String agyPerName;
    /**
     * 摘要
     */
    private String summary;
    /**
     * 主权项
     */
    private String signory;
    /**
     * 参考文献
     */
    private String refDoc;
    /**
     * 审查员
     */
    private String examiner;
    /**
     * 光盘号
     */
    private String diskNum;
    /**
     * PCT公开
     */
    private String pctPub;
    /**
     * PCT申请
     */
    private String pctApp;
    /**
     * PCT信息
     */
    private String pctInfo;
    /**
     * 范畴分类
     */
    private String category;
    /**
     * 服务器地址
     */
    private String serverUrl;
    /**
     * 发布路径
     */
    private String issueDir;
    /**
     * 页数
     */
    private String pageCount;
    /**
     * 国省代码
     */
    private String appAreaCode;
    /**
     * 数据库标识
     */
    private String sourceDb;
    /**
     * 入库批次
     */
    private String batchId;
    /**
     * 申请号wjy
     */
    private String patentWjy;
    /**
     * 专利类型
     */
    private String patentType;
    /**
     * F_ClassSort
     */
    private String fClassSort;
    /**
     * 主分类号
     */
    private String mainClassCode;
    /**
     * 主分类号显示数
     */
    private Integer mainClassCodeNum;
    /**
     * 分类号
     */
    private String classCode;
    /**
     * 分类号显示数
     */
    private Integer classCodeNum;
    /**
     * F_LawStatus
     */
    private String fLawStatus;
    /**
     * WFInstitutionName
     */
    private String wfInstitutionName;
    /**
     * pdf
     */
    private String pdf;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPubNum() {
        return pubNum;
    }

    public void setPubNum(String pubNum) {
        this.pubNum = pubNum;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getPatentId() {
        return patentId;
    }

    public void setPatentId(String patentId) {
        this.patentId = patentId;
    }

    public String getAppDate() {
        return appDate;
    }

    public void setAppDate(String appDate) {
        this.appDate = appDate;
    }

    public String getPatentNum() {
        return patentNum;
    }

    public void setPatentNum(String patentNum) {
        this.patentNum = patentNum;
    }

    public String getPatentTitle() {
        return patentTitle;
    }

    public void setPatentTitle(String patentTitle) {
        this.patentTitle = patentTitle;
    }

    public String getYsMainClassCode() {
        return ysMainClassCode;
    }

    public void setYsMainClassCode(String ysMainClassCode) {
        this.ysMainClassCode = ysMainClassCode;
    }

    public String getYsClassCode() {
        return ysClassCode;
    }

    public void setYsClassCode(String ysClassCode) {
        this.ysClassCode = ysClassCode;
    }

    public String getOldAppNum() {
        return oldAppNum;
    }

    public void setOldAppNum(String oldAppNum) {
        this.oldAppNum = oldAppNum;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getProposerName() {
        return proposerName;
    }

    public void setProposerName(String proposerName) {
        this.proposerName = proposerName;
    }

    public String getInvName() {
        return invName;
    }

    public void setInvName(String invName) {
        this.invName = invName;
    }

    public String getAppAddress() {
        return appAddress;
    }

    public void setAppAddress(String appAddress) {
        this.appAddress = appAddress;
    }

    public String getAwardDate() {
        return awardDate;
    }

    public void setAwardDate(String awardDate) {
        this.awardDate = awardDate;
    }

    public String getIntApp() {
        return intApp;
    }

    public void setIntApp(String intApp) {
        this.intApp = intApp;
    }

    public String getIntPub() {
        return intPub;
    }

    public void setIntPub(String intPub) {
        this.intPub = intPub;
    }

    public String getNationDate() {
        return nationDate;
    }

    public void setNationDate(String nationDate) {
        this.nationDate = nationDate;
    }

    public String getAgyOrgName() {
        return agyOrgName;
    }

    public void setAgyOrgName(String agyOrgName) {
        this.agyOrgName = agyOrgName;
    }

    public String getAgyPerName() {
        return agyPerName;
    }

    public void setAgyPerName(String agyPerName) {
        this.agyPerName = agyPerName;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getSignory() {
        return signory;
    }

    public void setSignory(String signory) {
        this.signory = signory;
    }

    public String getRefDoc() {
        return refDoc;
    }

    public void setRefDoc(String refDoc) {
        this.refDoc = refDoc;
    }

    public String getExaminer() {
        return examiner;
    }

    public void setExaminer(String examiner) {
        this.examiner = examiner;
    }

    public String getDiskNum() {
        return diskNum;
    }

    public void setDiskNum(String diskNum) {
        this.diskNum = diskNum;
    }

    public String getPctPub() {
        return pctPub;
    }

    public void setPctPub(String pctPub) {
        this.pctPub = pctPub;
    }

    public String getPctApp() {
        return pctApp;
    }

    public void setPctApp(String pctApp) {
        this.pctApp = pctApp;
    }

    public String getPctInfo() {
        return pctInfo;
    }

    public void setPctInfo(String pctInfo) {
        this.pctInfo = pctInfo;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public String getIssueDir() {
        return issueDir;
    }

    public void setIssueDir(String issueDir) {
        this.issueDir = issueDir;
    }

    public String getPageCount() {
        return pageCount;
    }

    public void setPageCount(String pageCount) {
        this.pageCount = pageCount;
    }

    public String getAppAreaCode() {
        return appAreaCode;
    }

    public void setAppAreaCode(String appAreaCode) {
        this.appAreaCode = appAreaCode;
    }

    public String getSourceDb() {
        return sourceDb;
    }

    public void setSourceDb(String sourceDb) {
        this.sourceDb = sourceDb;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getPatentWjy() {
        return patentWjy;
    }

    public void setPatentWjy(String patentWjy) {
        this.patentWjy = patentWjy;
    }

    public String getPatentType() {
        return patentType;
    }

    public void setPatentType(String patentType) {
        this.patentType = patentType;
    }

    public String getfClassSort() {
        return fClassSort;
    }

    public void setfClassSort(String fClassSort) {
        this.fClassSort = fClassSort;
    }

    public String getMainClassCode() {
        return mainClassCode;
    }

    public void setMainClassCode(String mainClassCode) {
        this.mainClassCode = mainClassCode;
    }

    public Integer getMainClassCodeNum() {
        return mainClassCodeNum;
    }

    public void setMainClassCodeNum(Integer mainClassCodeNum) {
        this.mainClassCodeNum = mainClassCodeNum;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public Integer getClassCodeNum() {
        return classCodeNum;
    }

    public void setClassCodeNum(Integer classCodeNum) {
        this.classCodeNum = classCodeNum;
    }

    public String getfLawStatus() {
        return fLawStatus;
    }

    public void setfLawStatus(String fLawStatus) {
        this.fLawStatus = fLawStatus;
    }

    public String getWfInstitutionName() {
        return wfInstitutionName;
    }

    public void setWfInstitutionName(String wfInstitutionName) {
        this.wfInstitutionName = wfInstitutionName;
    }

    public String getPdf() {
        return pdf;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
    }

    @Override
    public String toString() {
        return "TblPatentEntity{" +
                "id=" + id +
                ", pubNum='" + pubNum + '\'' +
                ", pubDate='" + pubDate + '\'' +
                ", patentId='" + patentId + '\'' +
                ", appDate='" + appDate + '\'' +
                ", patentNum='" + patentNum + '\'' +
                ", patentTitle='" + patentTitle + '\'' +
                ", ysMainClassCode='" + ysMainClassCode + '\'' +
                ", ysClassCode='" + ysClassCode + '\'' +
                ", oldAppNum='" + oldAppNum + '\'' +
                ", priority='" + priority + '\'' +
                ", proposerName='" + proposerName + '\'' +
                ", invName='" + invName + '\'' +
                ", appAddress='" + appAddress + '\'' +
                ", awardDate='" + awardDate + '\'' +
                ", intApp='" + intApp + '\'' +
                ", intPub='" + intPub + '\'' +
                ", nationDate='" + nationDate + '\'' +
                ", agyOrgName='" + agyOrgName + '\'' +
                ", agyPerName='" + agyPerName + '\'' +
                ", summary='" + summary + '\'' +
                ", signory='" + signory + '\'' +
                ", refDoc='" + refDoc + '\'' +
                ", examiner='" + examiner + '\'' +
                ", diskNum='" + diskNum + '\'' +
                ", pctPub='" + pctPub + '\'' +
                ", pctApp='" + pctApp + '\'' +
                ", pctInfo='" + pctInfo + '\'' +
                ", category='" + category + '\'' +
                ", serverUrl='" + serverUrl + '\'' +
                ", issueDir='" + issueDir + '\'' +
                ", pageCount='" + pageCount + '\'' +
                ", appAreaCode='" + appAreaCode + '\'' +
                ", sourceDb='" + sourceDb + '\'' +
                ", batchId='" + batchId + '\'' +
                ", patentWjy='" + patentWjy + '\'' +
                ", patentType='" + patentType + '\'' +
                ", fClassSort='" + fClassSort + '\'' +
                ", mainClassCode='" + mainClassCode + '\'' +
                ", mainClassCodeNum=" + mainClassCodeNum +
                ", classCode='" + classCode + '\'' +
                ", classCodeNum=" + classCodeNum +
                ", fLawStatus='" + fLawStatus + '\'' +
                ", wfInstitutionName='" + wfInstitutionName + '\'' +
                ", pdf='" + pdf + '\'' +
                '}';
    }
}
