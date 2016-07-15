package com.ufgov.zc.common.zc.model;import java.io.Serializable;

public class EmExpertTypeJoin implements Serializable{  private static final long serialVersionUID = -139311492716813356L;
  private String emExpertCode;  private String emTypeCode;
  private String emTypeName;    private String tempId;
  public String getEmTypeName() {    return emTypeName;  }  public void setEmTypeName(String emTypeName) {    this.emTypeName = emTypeName;  }  public String getEmExpertCode() {
    return emExpertCode;
  }  public void setEmExpertCode(String emExpertCode) {
    this.emExpertCode = emExpertCode;
  }
  public String getEmTypeCode() {
    return emTypeCode;
  }
  public void setEmTypeCode(String emTypeCode) {
    this.emTypeCode = emTypeCode;
  }  public String getTempId() {    return tempId;  }  public void setTempId(String tempId) {    this.tempId = tempId;  }
}
