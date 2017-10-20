package com.titan.yhsw;

import java.io.Serializable;

/**
 * Created by 32 on 2017/10/20.
 * 查询结果
 */

public class Biology implements Serializable {

    private String PICTURE; // 图片

    private String CNAME; // 中文名

    private String ENAME; // 英文名

    private String ALIAS; // 别名

    private String DOOR; // 生物门

    private String COLLECT; // 采集地址

    private String DISTRIBUTION; // 分布地区

    private String HOST; // 寄主

    private String FEATURE; // 特征 feature

    private String CONTROL; // 防治措施

    public String getPICTURE() {
        return PICTURE;
    }

    public void setPICTURE(String PICTURE) {
        this.PICTURE = PICTURE;
    }

    public String getCNAME() {
        return CNAME;
    }

    public void setCNAME(String CNAME) {
        this.CNAME = CNAME;
    }

    public String getENAME() {
        return ENAME;
    }

    public void setENAME(String ENAME) {
        this.ENAME = ENAME;
    }

    public String getALIAS() {
        return ALIAS;
    }

    public void setALIAS(String ALIAS) {
        this.ALIAS = ALIAS;
    }

    public String getDOOR() {
        return DOOR;
    }

    public void setDOOR(String DOOR) {
        this.DOOR = DOOR;
    }

    public String getCOLLECT() {
        return COLLECT;
    }

    public void setCOLLECT(String COLLECT) {
        this.COLLECT = COLLECT;
    }

    public String getDISTRIBUTION() {
        return DISTRIBUTION;
    }

    public void setDISTRIBUTION(String DISTRIBUTION) {
        this.DISTRIBUTION = DISTRIBUTION;
    }

    public String getHOST() {
        return HOST;
    }

    public void setHOST(String HOST) {
        this.HOST = HOST;
    }

    public String getFEATURE() {
        return FEATURE;
    }

    public void setFEATURE(String FEATURE) {
        this.FEATURE = FEATURE;
    }

    public String getCONTROL() {
        return CONTROL;
    }

    public void setCONTROL(String CONTROL) {
        this.CONTROL = CONTROL;
    }
}
