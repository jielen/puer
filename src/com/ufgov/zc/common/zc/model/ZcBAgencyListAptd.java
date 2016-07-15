package com.ufgov.zc.common.zc.model;

import java.io.Serializable;

public class ZcBAgencyListAptd extends ZcBaseBill implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 8204124086003941696L;

  private String tempId;

  private String zcAgeyCode;

  private String zcSeriCode;

  private String zcAptdCode;

  private String zcAptdName;

  private String zcDueto;

  private String zcDiyuDaima;

  public String getTempId() {
    return tempId;
  }

  public void setTempId(String tempId) {
    this.tempId = tempId;
  }

  public String getZcAgeyCode() {
    return zcAgeyCode;
  }

  public void setZcAgeyCode(String zcAgeyCode) {
    this.zcAgeyCode = zcAgeyCode;
  }

  public String getZcSeriCode() {
    return zcSeriCode;
  }

  public void setZcSeriCode(String zcSeriCode) {
    this.zcSeriCode = zcSeriCode;
  }

  public String getZcAptdCode() {
    return zcAptdCode;
  }

  public void setZcAptdCode(String zcAptdCode) {
    this.zcAptdCode = zcAptdCode;
  }

  public String getZcAptdName() {
    return zcAptdName;
  }

  public void setZcAptdName(String zcAptdName) {
    this.zcAptdName = zcAptdName;
  }

  public String getZcDueto() {
    return zcDueto;
  }

  public void setZcDueto(String zcDueto) {
    this.zcDueto = zcDueto;
  }

  public String getZcDiyuDaima() {
    return zcDiyuDaima;
  }

  public void setZcDiyuDaima(String zcDiyuDaima) {
    this.zcDiyuDaima = zcDiyuDaima;
  }

}
