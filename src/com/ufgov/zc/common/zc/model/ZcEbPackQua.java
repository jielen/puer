package com.ufgov.zc.common.zc.model;

import java.io.Serializable;

public class ZcEbPackQua implements Serializable {

  private String projCode;

  private String packCode;

  private String packName;

  private String qualifId;

  private String qualifName;

  private String tempId;

  public String getProjCode() {
    return projCode;
  }

  public void setProjCode(String projCode) {
    this.projCode = projCode;
  }

  public String getTempId() {
    return tempId;
  }

  public void setTempId(String tempId) {
    this.tempId = tempId;
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

  public String getQualifId() {
    return qualifId;
  }

  public void setQualifId(String qualifId) {
    this.qualifId = qualifId;
  }

  public String getQualifName() {
    return qualifName;
  }

  public void setQualifName(String qualifName) {
    this.qualifName = qualifName;
  }

}
