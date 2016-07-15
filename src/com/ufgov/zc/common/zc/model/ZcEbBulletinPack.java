package com.ufgov.zc.common.zc.model;

import java.io.Serializable;

public class ZcEbBulletinPack extends ZcBaseBill implements Serializable {

  private String bulletinId;

  private String packCode;

  private ZcEbPack pack;

  private String tempId;

  public String getTempId() {
    return tempId;
  }

  public void setTempId(String tempId) {
    this.tempId = tempId;
  }

  public String getBulletinId() {
    return bulletinId;
  }

  public void setBulletinId(String bulletinId) {
    this.bulletinId = bulletinId;
  }

  public String getPackCode() {
    return packCode;
  }

  public void setPackCode(String packCode) {
    this.packCode = packCode;
  }

  public ZcEbPack getPack() {
    return pack;
  }

  public void setPack(ZcEbPack pack) {
    this.pack = pack;
  }

}
