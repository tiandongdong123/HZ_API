package com.hanzhong.data.web.util.longdun.entbaseinfo.model;

/**
 *  
 *  @Description
 *  @Author   luqs   
 *  @Date 2019/1/10 20:59 
 *  @Version  V1.0   
 */
public class EntInfo {

    /**
     * 企业名称
     */
    private String entName;
    /**
     * 曾用名
     */
    private String oldName;
    /**
     * 统一社会信用代码
     */
    private String shxydm;
    /**
     * 法定代表人
     */
    private String frdb;
    /**
     * 成立日期
     */
    private String esDate;
    /**
     * 经营状态
     */
    private String entStatus;
    /**
     * 注册资本
     */
    private String regCap;
    /**
     * 地址
     */
    private String dom;
    /**
     * 电话
     */
    private String tel;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 网址
     */
    private String webUrl;
    /**
     * 是否正在进行简易注销公示
     */
    private String isJyzxgs;

    public String getEntName() {
        return entName;
    }

    public void setEntName(String entName) {
        this.entName = entName;
    }

    public String getOldName() {
        return oldName;
    }

    public void setOldName(String oldName) {
        this.oldName = oldName;
    }

    public String getShxydm() {
        return shxydm;
    }

    public void setShxydm(String shxydm) {
        this.shxydm = shxydm;
    }

    public String getFrdb() {
        return frdb;
    }

    public void setFrdb(String frdb) {
        this.frdb = frdb;
    }

    public String getEsDate() {
        return esDate;
    }

    public void setEsDate(String esDate) {
        this.esDate = esDate;
    }

    public String getEntStatus() {
        return entStatus;
    }

    public void setEntStatus(String entStatus) {
        this.entStatus = entStatus;
    }

    public String getRegCap() {
        return regCap;
    }

    public void setRegCap(String regCap) {
        this.regCap = regCap;
    }

    public String getDom() {
        return dom;
    }

    public void setDom(String dom) {
        this.dom = dom;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public String getIsJyzxgs() {
        return isJyzxgs;
    }

    public void setIsJyzxgs(String isJyzxgs) {
        this.isJyzxgs = isJyzxgs;
    }

    @Override
    public String toString() {
        return "EntInfo{" +
                "entName='" + entName + '\'' +
                ", oldName='" + oldName + '\'' +
                ", shxydm='" + shxydm + '\'' +
                ", frdb='" + frdb + '\'' +
                ", esDate='" + esDate + '\'' +
                ", entStatus='" + entStatus + '\'' +
                ", regCap='" + regCap + '\'' +
                ", dom='" + dom + '\'' +
                ", tel='" + tel + '\'' +
                ", email='" + email + '\'' +
                ", webUrl='" + webUrl + '\'' +
                ", isJyzxgs='" + isJyzxgs + '\'' +
                '}';
    }
}
