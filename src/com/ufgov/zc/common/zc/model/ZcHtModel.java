package com.ufgov.zc.common.zc.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.ufgov.zc.common.system.model.AsFile;

public class ZcHtModel implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = -8247047201792765685L;

  private String zcHtCode;

  private String zcSgnDate;

  private String packName;

  private String packCode;
  
  private String packDesc;

  private String zcHtNum;

  private String coName;

  private String zcSuName;

  private String entrustCode;

  private String zcHtNumUpp;

  private String zcDeliveryAddr;

  private String zcSuAddr;

  private String zipCode;

  private String linkManPhone;

  private String linkMan;

  private String bankName;

  private String accCode;

  private String bzjPrence;

  private String zcProLimitStartDate;

  private String zcProLimitEndDate;

  private String zbFileCode;

  private String zcDeliveryDate;

  private String openBidStatus;

  private String failReason;

  private List item;

  private AsFile file;
  
  private BigDecimal winBidSum;

  public String getOpenBidStatus() {
    return openBidStatus;
  }

  public void setOpenBidStatus(String openBidStatus) {
    this.openBidStatus = openBidStatus;
  }

  public String getFailReason() {
    return failReason;
  }

  public void setFailReason(String failReason) {
    this.failReason = failReason;
  }

  public String getZcDeliveryDate() {
    return zcDeliveryDate;
  }

  public void setZcDeliveryDate(String zcDeliveryDate) {
    this.zcDeliveryDate = zcDeliveryDate;
  }

  public String getZbFileCode() {
    return zbFileCode;
  }

  public void setZbFileCode(String zbFileCode) {
    this.zbFileCode = zbFileCode;
  }

  public String getZcProLimitStartDate() {
    return zcProLimitStartDate;
  }

  public void setZcProLimitStartDate(String zcProLimitStartDate) {
    this.zcProLimitStartDate = zcProLimitStartDate;
  }

  public String getZcProLimitEndDate() {
    return zcProLimitEndDate;
  }

  public void setZcProLimitEndDate(String zcProLimitEndDate) {
    this.zcProLimitEndDate = zcProLimitEndDate;
  }

  public String getBzjPrence() {
    return bzjPrence;
  }

  public void setBzjPrence(String bzjPrence) {
    this.bzjPrence = bzjPrence;
  }

  public String getZcHtCode() {
    return zcHtCode;
  }

  public void setZcHtCode(String zcHtCode) {
    this.zcHtCode = zcHtCode;
  }

  public String getZcSgnDate() {
    return zcSgnDate;
  }

  public void setZcSgnDate(String zcSgnDate) {
    this.zcSgnDate = zcSgnDate;
  }

  public String getZcSuAddr() {
    return zcSuAddr;
  }

  public void setZcSuAddr(String zcSuAddr) {
    this.zcSuAddr = zcSuAddr;
  }

  public String getPackName() {
    return packName;
  }

  public void setPackName(String packName) {
    this.packName = packName;
  }

  public String getPackCode() {
    return packCode;
  }

  public void setPackCode(String packCode) {
    this.packCode = packCode;
  }

  public String getZcHtNum() {
    return zcHtNum;
  }

  public void setZcHtNum(String zcHtNum) {
    this.zcHtNum = zcHtNum;
  }

  public String getCoName() {
    return coName;
  }

  public void setCoName(String coName) {
    this.coName = coName;
  }

  public String getZcSuName() {
    return zcSuName;
  }

  public void setZcSuName(String zcSuName) {
    this.zcSuName = zcSuName;
  }

  public String getEntrustCode() {
    return entrustCode;
  }

  public void setEntrustCode(String entrustCode) {
    this.entrustCode = entrustCode;
  }

  public String getZcHtNumUpp() {
    return zcHtNumUpp;
  }

  public void setZcHtNumUpp(String zcHtNumUpp) {
    this.zcHtNumUpp = zcHtNumUpp;
  }

  public String getZcDeliveryAddr() {
    return zcDeliveryAddr;
  }

  public void setZcDeliveryAddr(String zcDeliveryAddr) {
    this.zcDeliveryAddr = zcDeliveryAddr;
  }

  public String getZipCode() {
    return zipCode;
  }

  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
  }

  public String getLinkManPhone() {
    return linkManPhone;
  }

  public void setLinkManPhone(String linkManPhone) {
    this.linkManPhone = linkManPhone;
  }

  public String getLinkMan() {
    return linkMan;
  }

  public void setLinkMan(String linkMan) {
    this.linkMan = linkMan;
  }

  public String getBankName() {
    return bankName;
  }

  public void setBankName(String bankName) {
    this.bankName = bankName;
  }

  public String getAccCode() {
    return accCode;
  }

  public void setAccCode(String accCode) {
    this.accCode = accCode;
  }

  public List getItem() {
    return item;
  }

  public void setItem(List item) {
    this.item = item;
  }

  public AsFile getFile() {
    return file;
  }

  public void setFile(AsFile file) {
    this.file = file;
  }

  public BigDecimal getWinBidSum() {
    return winBidSum;
  }

  public void setWinBidSum(BigDecimal winBidSum) {
    this.winBidSum = winBidSum;
  }

  public String getPackDesc() {
    return packDesc;
  }

  public void setPackDesc(String packDesc) {
    this.packDesc = packDesc;
  }

}
