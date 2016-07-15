package com.ufgov.zc.common.zc.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ZcBAgency extends ZcBaseBill implements Serializable {

  private String tempId;

  private String zcAgeyCode;

  private String zcDiyuDaima;

  private String zcAgeyName;

  private String zcAgeyJgdm;

  private String zcAgeyAddr;

  private String zcAgeyZip;

  private String zcAgeyLinkman;

  private String zcAgeyTel;

  private String zcAgeyType;

  private Short zcRandomId;

  private String zcInputEmpCode;

  private Date zcInputDate;

  private String zcInputEmpName;

  private String zcAuditEmpCode;

  private Date zcAuditDate;

  private String zcAuditEmpName;

  private String zcStatCode;

  private String zcDiyuName;

  private List ZcBAgencAptdList;

  private Date startTime;

  private Date endTime;

  public String getTempId() {
    return tempId;
  }

  public void setTempId(String tempId) {
    this.tempId = tempId;
  }

  private static final long serialVersionUID = 1L;

  public String getZcAgeyCode() {
    return zcAgeyCode;
  }

  public void setZcAgeyCode(String zcAgeyCode) {
    this.zcAgeyCode = zcAgeyCode;
  }

  public String getZcDiyuDaima() {
    return zcDiyuDaima;
  }

  public void setZcDiyuDaima(String zcDiyuDaima) {
    this.zcDiyuDaima = zcDiyuDaima;
  }

  public String getZcAgeyName() {
    return zcAgeyName;
  }

  public void setZcAgeyName(String zcAgeyName) {
    this.zcAgeyName = zcAgeyName;
  }

  public String getZcAgeyJgdm() {
    return zcAgeyJgdm;
  }

  public void setZcAgeyJgdm(String zcAgeyJgdm) {
    this.zcAgeyJgdm = zcAgeyJgdm;
  }

  public String getZcAgeyAddr() {
    return zcAgeyAddr;
  }

  public void setZcAgeyAddr(String zcAgeyAddr) {
    this.zcAgeyAddr = zcAgeyAddr;
  }

  public String getZcAgeyZip() {
    return zcAgeyZip;
  }

  public void setZcAgeyZip(String zcAgeyZip) {
    this.zcAgeyZip = zcAgeyZip;
  }

  public String getZcAgeyLinkman() {
    return zcAgeyLinkman;
  }

  public void setZcAgeyLinkman(String zcAgeyLinkman) {
    this.zcAgeyLinkman = zcAgeyLinkman;
  }

  public String getZcAgeyTel() {
    return zcAgeyTel;
  }

  public void setZcAgeyTel(String zcAgeyTel) {
    this.zcAgeyTel = zcAgeyTel;
  }

  public String getZcAgeyType() {
    return zcAgeyType;
  }

  public void setZcAgeyType(String zcAgeyType) {
    this.zcAgeyType = zcAgeyType;
  }

  public Short getZcRandomId() {
    return zcRandomId;
  }

  public void setZcRandomId(Short zcRandomId) {
    this.zcRandomId = zcRandomId;
  }

  public String getZcInputEmpCode() {
    return zcInputEmpCode;
  }

  public void setZcInputEmpCode(String zcInputEmpCode) {
    this.zcInputEmpCode = zcInputEmpCode;
  }

  public Date getZcInputDate() {
    return zcInputDate;
  }

  public void setZcInputDate(Date zcInputDate) {
    this.zcInputDate = zcInputDate;
  }

  public String getZcInputEmpName() {
    return zcInputEmpName;
  }

  public void setZcInputEmpName(String zcInputEmpName) {
    this.zcInputEmpName = zcInputEmpName;
  }

  public String getZcAuditEmpCode() {
    return zcAuditEmpCode;
  }

  public void setZcAuditEmpCode(String zcAuditEmpCode) {
    this.zcAuditEmpCode = zcAuditEmpCode;
  }

  public Date getZcAuditDate() {
    return zcAuditDate;
  }

  public void setZcAuditDate(Date zcAuditDate) {
    this.zcAuditDate = zcAuditDate;
  }

  public String getZcAuditEmpName() {
    return zcAuditEmpName;
  }

  public void setZcAuditEmpName(String zcAuditEmpName) {
    this.zcAuditEmpName = zcAuditEmpName;
  }

  public String getZcStatCode() {
    return zcStatCode;
  }

  public void setZcStatCode(String zcStatCode) {
    this.zcStatCode = zcStatCode;
  }

  public String getZcDiyuName() {
    return zcDiyuName;
  }

  public void setZcDiyuName(String zcDiyuName) {
    this.zcDiyuName = zcDiyuName;
  }

  public List getZcBAgencAptdList() {
    return ZcBAgencAptdList;
  }

  public void setZcBAgencAptdList(List zcBAgencAptdList) {
    ZcBAgencAptdList = zcBAgencAptdList;
  }

  /**
   * @return the startTime
   */
  public Date getStartTime() {
    return startTime;
  }

  /**
   * @param startTime the startTime to set
   */
  public void setStartTime(Date startTime) {
    this.startTime = startTime;
  }

  /**
   * @return the endTime
   */
  public Date getEndTime() {
    return endTime;
  }

  /**
   * @param endTime the endTime to set
   */
  public void setEndTime(Date endTime) {
    this.endTime = endTime;
  }

}