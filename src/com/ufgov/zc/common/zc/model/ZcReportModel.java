package com.ufgov.zc.common.zc.model;

import java.io.Serializable;

import com.ufgov.zc.common.system.model.AsFile;

public class ZcReportModel implements Serializable {

  private String snCode;

  private String zcMakeName;

  private String projCode;

  private String openBidDate;

  private String openBidAddr;

  private String attnName;

  private String bulDate;

  private String bidEndTime;

  private String zcSuName;

  private String money;

  private String packName;

  private String content;

  private AsFile file;

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getPackName() {
    return packName;
  }

  public void setPackName(String packName) {
    this.packName = packName;
  }

  public AsFile getFile() {
    return file;
  }

  public void setFile(AsFile file) {
    this.file = file;
  }

  public String getSnCode() {
    return snCode;
  }

  public void setSnCode(String snCode) {
    this.snCode = snCode;
  }

  public String getZcMakeName() {
    return zcMakeName;
  }

  public void setZcMakeName(String zcMakeName) {
    this.zcMakeName = zcMakeName;
  }

  public String getProjCode() {
    return projCode;
  }

  public void setProjCode(String projCode) {
    this.projCode = projCode;
  }

  public String getOpenBidDate() {
    return openBidDate;
  }

  public void setOpenBidDate(String openBidDate) {
    this.openBidDate = openBidDate;
  }

  public String getOpenBidAddr() {
    return openBidAddr;
  }

  public void setOpenBidAddr(String openBidAddr) {
    this.openBidAddr = openBidAddr;
  }

  public String getAttnName() {
    return attnName;
  }

  public void setAttnName(String attnName) {
    this.attnName = attnName;
  }

  public String getBulDate() {
    return bulDate;
  }

  public void setBulDate(String bulDate) {
    this.bulDate = bulDate;
  }

  public String getBidEndTime() {
    return bidEndTime;
  }

  public void setBidEndTime(String bidEndTime) {
    this.bidEndTime = bidEndTime;
  }

  public String getZcSuName() {
    return zcSuName;
  }

  public void setZcSuName(String zcSuName) {
    this.zcSuName = zcSuName;
  }

  public String getMoney() {
    return money;
  }

  public void setMoney(String money) {
    this.money = money;
  }
}
