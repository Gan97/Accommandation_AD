package com.xujin.ad_sender.entity;

import java.util.Comparator;
import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* Description:
 * \* User: xujin
 * \* Date: 2018/3/24
 * \* Time: 18:20
 * \
 */
public class ADInfoEntity {
    private Integer ADID;
    private String ADTitle;
    private String SelectCrowd;
    private String SelectGender;
    private String UploadPicture;
    private String ADClasses;
    private String ADDescribe;
    private Integer RTBPrice;

    public Integer getADID() {
        return ADID;
    }

    public void setADID(Integer ADID) {
        this.ADID = ADID;
    }

    public String getADTitle() {
        return ADTitle;
    }

    public void setADTitle(String ADTitle) {
        this.ADTitle = ADTitle;
    }

    public String getSelectCrowd() {
        return SelectCrowd;
    }


    public void setSelectCrowd(String selectCrowd) {
        SelectCrowd = selectCrowd;
    }

    public String getSelectGender() {
        return SelectGender;
    }

    public void setSelectGender(String selectGender) {
        SelectGender = selectGender;
    }

    public String getUploadPicture() {
        return UploadPicture;
    }

    public void setUploadPicture(String uploadPicture) {
        UploadPicture = uploadPicture;
    }

    public String getADClasses() {
        return ADClasses;
    }

    public void setADClasses(String ADClasses) {
        this.ADClasses = ADClasses;
    }

    public String getADDescribe() {
        return ADDescribe;
    }

    public void setADDescribe(String ADDescribe) {
        this.ADDescribe = ADDescribe;
    }

    public Integer getRTBPrice() {
        return RTBPrice;
    }

    public void setRTBPrice(Integer RTBPrice) {
        this.RTBPrice = RTBPrice;
    }

    @Override
    public String toString() {
        return "ADInfoEntity{" +
                "ADID=" + ADID +
                ", ADTitle='" + ADTitle + '\'' +
                ", SelectCrowd='" + SelectCrowd + '\'' +
                ", SelectGender='" + SelectGender + '\'' +
                ", UploadPicture='" + UploadPicture + '\'' +
                ", ADClasses='" + ADClasses + '\'' +
                ", ADDescribe='" + ADDescribe + '\'' +
                ", RTBPrice=" + RTBPrice +
                '}';
    }
}