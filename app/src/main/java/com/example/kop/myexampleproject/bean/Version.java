package com.example.kop.myexampleproject.bean;

/**
 * 功    能: 版本控制
 * 创 建 人: KOP
 * 创建日期: 2018/10/10 14:18
 */
public class Version {

    /**
     * id : 1050566100712468480
     * versionNum : 1
     * versionName : 1.0.0
     * description : 初次写入
     * url : www.baidu.com
     * type : 0
     * isForce : 0
     * createTime : 1539309452000
     */

    private String id;
    private String versionNum;
    private String versionName;
    private String description;
    private String url;
    private String type;
    private String isForce;
    private long createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVersionNum() {
        return versionNum;
    }

    public void setVersionNum(String versionNum) {
        this.versionNum = versionNum;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIsForce() {
        return isForce;
    }

    public void setIsForce(String isForce) {
        this.isForce = isForce;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    @Override public String toString() {
        return "Version{" +
                "id='" + id + '\'' +
                ", versionNum='" + versionNum + '\'' +
                ", versionName='" + versionName + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", type='" + type + '\'' +
                ", isForce='" + isForce + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
