package com.ufgov.zc.common.zc.model;

import java.util.Date;

public class ZcEbProjSupport extends ZcEbEntrustBull {

  private String projCode;

  private String projName;

  private String packCode;

  private String packName;

  private String supportCnt;

  private Date openBidTime;
  
  private Date bidEndTime;

  private String comment;

  private String openBidAddress;

  private String zcSuName;

  private Date signupDate;

  private String isPayGuarantee;

  //是否到现场
  private String isSite;

  private String remark;

  private String tempId;

  private ZcEbSupplier zcEbSupplier;

  private String isExtrac;

  public Date getBidEndTime() {
	  return bidEndTime;
  }
  
  public void setBidEndTime(Date bidEndTime) {
	  this.bidEndTime = bidEndTime;
  }
  
  public String getIsExtrac() {
	  return isExtrac;
  }

  public void setIsExtrac(String isExtrac) {
    this.isExtrac = isExtrac;
  }

  public ZcEbSupplier getZcEbSupplier() {
    return zcEbSupplier;
  }

  public void setZcEbSupplier(ZcEbSupplier zcEbSupplier) {
    this.zcEbSupplier = zcEbSupplier;
  }

  public String getTempId() {
    return tempId;
  }

  public void setTempId(String tempId) {
    this.tempId = tempId;
  }

  public String getZcSuName() {
    return zcSuName;
  }

  public void setZcSuName(String zcSuName) {
    this.zcSuName = zcSuName;
  }

  public Date getSignupDate() {
    return signupDate;
  }

  public void setSignupDate(Date signupDate) {
    this.signupDate = signupDate;
  }

  public String getIsPayGuarantee() {
    return isPayGuarantee;
  }

  public void setIsPayGuarantee(String isPayGuarantee) {
    this.isPayGuarantee = isPayGuarantee;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public String getOpenBidAddress() {
    return openBidAddress;
  }

  public void setOpenBidAddress(String openBidAddress) {
    this.openBidAddress = openBidAddress;
  }

  public String getProjCode() {
    return projCode;
  }

  public void setProjCode(String projCode) {
    this.projCode = projCode;
  }

  public String getProjName() {
    return projName;
  }

  public void setProjName(String projName) {
    this.projName = projName;
  }

  public String getPackCode() {
    return packCode;
  }

  public void setPackCode(String packCode) {
    this.packCode = packCode;
  }

  public String getPackName() {
    return packName;
  }

  public void setPackName(String packName) {
    this.packName = packName;
  }

  public String getSupportCnt() {
    return supportCnt;
  }

  public void setSupportCnt(String supportCnt) {
    this.supportCnt = supportCnt;
  }

  public Date getOpenBidTime() {
    return openBidTime;
  }

  public void setOpenBidTime(Date openBidTime) {
    this.openBidTime = openBidTime;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  /**
   * @return the isSite
   */
  public String getIsSite() {
    return isSite;
  }

  /**
   * @param isSite the isSite to set
   */
  public void setIsSite(String isSite) {
    this.isSite = isSite;
  }

}
