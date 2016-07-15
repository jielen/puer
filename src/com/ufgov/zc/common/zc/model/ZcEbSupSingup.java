package com.ufgov.zc.common.zc.model;

import java.io.Serializable;
import java.util.Date;

public class ZcEbSupSingup implements Serializable {
  private String snCode;//任务单号

  private String zcMakeName;//任务名称

  private String projCode;//项目编号

  private String projName;//项目名称

  private String packDesc;//分包名称

  private String packName;//分包编号

  private Date singUpDate;//投标时间

  private String isSite;//是否到现场投标

  private String isWinBid;//是否中标

  /**
   * @return the snCode
   */
  public String getSnCode() {
    return snCode;
  }

  /**
   * @param snCode the snCode to set
   */
  public void setSnCode(String snCode) {
    this.snCode = snCode;
  }

  /**
   * @return the zcMakeName
   */
  public String getZcMakeName() {
    return zcMakeName;
  }

  /**
   * @param zcMakeName the zcMakeName to set
   */
  public void setZcMakeName(String zcMakeName) {
    this.zcMakeName = zcMakeName;
  }

  /**
   * @return the projCode
   */
  public String getProjCode() {
    return projCode;
  }

  /**
   * @param projCode the projCode to set
   */
  public void setProjCode(String projCode) {
    this.projCode = projCode;
  }

  /**
   * @return the projName
   */
  public String getProjName() {
    return projName;
  }

  /**
   * @param projName the projName to set
   */
  public void setProjName(String projName) {
    this.projName = projName;
  }

  /**
   * @return the packDesc
   */
  public String getPackDesc() {
    return packDesc;
  }

  /**
   * @param packDesc the packDesc to set
   */
  public void setPackDesc(String packDesc) {
    this.packDesc = packDesc;
  }

  /**
   * @return the packName
   */
  public String getPackName() {
    return packName;
  }

  /**
   * @param packName the packName to set
   */
  public void setPackName(String packName) {
    this.packName = packName;
  }

  /**
   * @return the singUpDate
   */
  public Date getSingUpDate() {
    return singUpDate;
  }

  /**
   * @param singUpDate the singUpDate to set
   */
  public void setSingUpDate(Date singUpDate) {
    this.singUpDate = singUpDate;
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

  /**
   * @return the isWinBid
   */
  public String getIsWinBid() {
    return isWinBid;
  }

  /**
   * @param isWinBid the isWinBid to set
   */
  public void setIsWinBid(String isWinBid) {
    this.isWinBid = isWinBid;
  }

}
