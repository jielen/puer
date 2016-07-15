package com.ufgov.zc.common.system.dto;

import java.io.Serializable;
import java.util.Date;

import com.ufgov.zc.common.sf.model.SfEntrust;
import com.ufgov.zc.common.sf.model.SfEntrustor;
import com.ufgov.zc.common.sf.model.SfMajor;
import com.ufgov.zc.common.zc.model.ZcBaseBill;

public class SfElementConditionDto extends ZcBaseBill implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = -4261374270993307965L;

  /*
    private BigDecimal entrustId;

    private String entrustCode;

    private String majorCode;

    private String majorName;

    private String jdFzr;

    private String jdFzrName;

    private String jdFhr;

    private String jdFhrName;*/

  private String isPay;

  private Date wtBeginTime;

  private Date wtEndTime;

  private Date payBeginTime;

  private Date payEndTime;

  private SfEntrustor entrustor = new SfEntrustor();

  private SfEntrust entrust = new SfEntrust();

  private SfMajor major = new SfMajor();

  /*public BigDecimal getEntrustId() {
    return entrustId;
  }

  public void setEntrustId(BigDecimal entrustId) {
    this.entrustId = entrustId;
  }

  public String getEntrustCode() {
    return entrustCode;
  }

  public void setEntrustCode(String entrustCode) {
    this.entrustCode = entrustCode;
  }

  public String getMajorCode() {
    return majorCode;
  }

  public void setMajorCode(String majorCode) {
    this.majorCode = majorCode;
  }

  public String getMajorName() {
    return majorName;
  }

  public void setMajorName(String majorName) {
    this.majorName = majorName;
  }

  public String getJdFzr() {
    return jdFzr;
  }

  public void setJdFzr(String jdFzr) {
    this.jdFzr = jdFzr;
  }

  public String getJdFzrName() {
    return jdFzrName;
  }

  public void setJdFzrName(String jdFzrName) {
    this.jdFzrName = jdFzrName;
  }

  public String getJdFhr() {
    return jdFhr;
  }

  public void setJdFhr(String jdFhr) {
    this.jdFhr = jdFhr;
  }

  public String getJdFhrName() {
    return jdFhrName;
  }

  public void setJdFhrName(String jdFhrName) {
    this.jdFhrName = jdFhrName;
  }*/

  public String getIsPay() {
    return isPay;
  }

  public void setIsPay(String isPay) {
    this.isPay = isPay;
  }

  public Date getWtBeginTime() {
    return wtBeginTime;
  }

  public void setWtBeginTime(Date wtBeginTime) {
    this.wtBeginTime = wtBeginTime;
  }

  public Date getWtEndTime() {
    return wtEndTime;
  }

  public void setWtEndTime(Date wtEndTime) {
    this.wtEndTime = wtEndTime;
  }

  public Date getPayBeginTime() {
    return payBeginTime;
  }

  public void setPayBeginTime(Date payBeginTime) {
    this.payBeginTime = payBeginTime;
  }

  public Date getPayEndTime() {
    return payEndTime;
  }

  public void setPayEndTime(Date payEndTime) {
    this.payEndTime = payEndTime;
  }

  public SfEntrustor getEntrustor() {
    return entrustor;
  }

  public void setEntrustor(SfEntrustor entrustor) {
    this.entrustor = entrustor;
  }

  public SfEntrust getEntrust() {
    return entrust;
  }

  public void setEntrust(SfEntrust entrust) {
    this.entrust = entrust;
  }

  public SfMajor getMajor() {
    return major;
  }

  public void setMajor(SfMajor major) {
    this.major = major;
  }

}
