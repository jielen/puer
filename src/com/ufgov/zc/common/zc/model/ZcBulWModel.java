package com.ufgov.zc.common.zc.model;

import java.io.Serializable;

import com.ufgov.zc.common.system.model.AsFile;

public class ZcBulWModel implements Serializable {

  private String coName;

  private String zcMakeName;

  private String content;

  private String gonghDate;

  private String projCode;

  private String bulDate;

  private String openBidTime;

  private String packName;

  private String zcSuName;

  private String money;

  private String zcMakeLinkMan;

  private String attnName;

  private String attnTel;

  private String now;

  private String modCode;

  private String modName;

  private AsFile file;

  public String getModCode() {
    return modCode;
  }

  public void setModCode(String modCode) {
    this.modCode = modCode;
  }

  public String getModName() {
    return modName;
  }

  public void setModName(String modName) {
    this.modName = modName;
  }

  public String getCoName() {
    return coName;
  }

  public void setCoName(String coName) {
    this.coName = coName;
  }

  public String getZcMakeName() {
    return zcMakeName;
  }

  public void setZcMakeName(String zcMakeName) {
    this.zcMakeName = zcMakeName;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getGonghDate() {
    return gonghDate;
  }

  public void setGonghDate(String gonghDate) {
    this.gonghDate = gonghDate;
  }

  public String getProjCode() {
    return projCode;
  }

  public void setProjCode(String projCode) {
    this.projCode = projCode;
  }

  public String getBulDate() {
    return bulDate;
  }

  public void setBulDate(String bulDate) {
    this.bulDate = bulDate;
  }

  public String getOpenBidTime() {
    return openBidTime;
  }

  public void setOpenBidTime(String openBidTime) {
    this.openBidTime = openBidTime;
  }

  public String getPackName() {
    return packName;
  }

  public void setPackName(String packName) {
    this.packName = packName;
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

  public String getZcMakeLinkMan() {
    return zcMakeLinkMan;
  }

  public void setZcMakeLinkMan(String zcMakeLinkMan) {
    this.zcMakeLinkMan = zcMakeLinkMan;
  }

  public String getAttnName() {
    return attnName;
  }

  public void setAttnName(String attnName) {
    this.attnName = attnName;
  }

  public String getAttnTel() {
    return attnTel;
  }

  public void setAttnTel(String attnTel) {
    this.attnTel = attnTel;
  }

  public String getNow() {
    return now;
  }

  public void setNow(String now) {
    this.now = now;
  }

  public AsFile getFile() {
    return file;
  }

  public void setFile(AsFile file) {
    this.file = file;
  }

}
