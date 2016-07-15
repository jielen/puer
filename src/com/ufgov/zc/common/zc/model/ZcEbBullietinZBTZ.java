package com.ufgov.zc.common.zc.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: qianmingjin
 * Date: 13-5-20
 * Time: 上午9:56
 * To change this template use File | Settings | File Templates.
 */
public class ZcEbBullietinZBTZ implements Serializable {
    private String providerName;//供应商名称
    private String coName;
    private String projName;
    private String projCode;
    private float bidSum;
    private String spBrand;
    private float spNum;
    private Date noticeDate;
    private String dateFormat="yyyy年MM月dd日";

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getCoName() {
        return coName;
    }

    public void setCoName(String coName) {
        this.coName = coName;
    }

    public String getProjName() {
        return projName;
    }

    public void setProjName(String projName) {
        this.projName = projName;
    }

    public String getProjCode() {
        return projCode;
    }

    public void setProjCode(String projCode) {
        this.projCode = projCode;
    }

    public float getBidSum() {
        return bidSum;
    }

    public void setBidSum(float bidSum) {
        this.bidSum = bidSum;
    }

    public String getSpBrand() {
        return spBrand;
    }

    public void setSpBrand(String spBrand) {
        this.spBrand = spBrand;
    }


    public float getSpNum() {
        return spNum;
    }

    public void setSpNum(float spNum) {
        this.spNum = spNum;
    }


    public String getStringNoticeDate() {
        DateFormat dateFormat1=new SimpleDateFormat(dateFormat);
        return  dateFormat1.format(noticeDate);
    }

    public Date getNoticeDate() {
        return noticeDate;
    }

    public void setNoticeDate(Date noticeDate) {
        this.noticeDate = noticeDate;
    }
}
