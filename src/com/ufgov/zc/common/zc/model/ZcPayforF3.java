package com.ufgov.zc.common.zc.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ZcPayforF3 extends ZcBaseBill implements Serializable {

  private static final long serialVersionUID = 1585473096423591655L;

  private String payId;

  private String branId;

  private String planId;

  private String budgetId;

  private String branNo;

  private String payeeAccountName;

  private String payeeAccountNo;

  private String payeeAccountBank;

  private String dirCode;

  private String dirName;

  private BigDecimal payMoney = new BigDecimal("0.00");

  private String pkCode;

  private String pkName;

  private String mkCode;

  private String mkName;

  private Date lastVer;

  private String downloadStatus = "0";

  private String payStatus = "0";

  public String getPayId() {
    return payId;
  }

  public void setPayId(String payId) {
    this.payId = payId;
  }

  public String getBranId() {
    return branId;
  }

  public void setBranId(String branId) {
    this.branId = branId;
  }

  public String getPlanId() {
    return planId;
  }

  public void setPlanId(String planId) {
    this.planId = planId;
  }

  public String getBranNo() {
    return branNo;
  }

  public void setBranNo(String branNo) {
    this.branNo = branNo;
  }

  public String getPayeeAccountName() {
    return payeeAccountName;
  }

  public void setPayeeAccountName(String payeeAccountName) {
    this.payeeAccountName = payeeAccountName;
  }

  public String getPayeeAccountNo() {
    return payeeAccountNo;
  }

  public void setPayeeAccountNo(String payeeAccountNo) {
    this.payeeAccountNo = payeeAccountNo;
  }

  public String getPayeeAccountBank() {
    return payeeAccountBank;
  }

  public void setPayeeAccountBank(String payeeAccountBank) {
    this.payeeAccountBank = payeeAccountBank;
  }

  public String getDirCode() {
    return dirCode;
  }

  public void setDirCode(String dirCode) {
    this.dirCode = dirCode;
  }

  public String getDirName() {
    return dirName;
  }

  public void setDirName(String dirName) {
    this.dirName = dirName;
  }

  public BigDecimal getPayMoney() {
    return payMoney;
  }

  public void setPayMoney(BigDecimal payMoney) {
    this.payMoney = payMoney;
  }

  public String getPkCode() {
    return pkCode;
  }

  public void setPkCode(String pkCode) {
    this.pkCode = pkCode;
  }

  public String getPkName() {
    return pkName;
  }

  public void setPkName(String pkName) {
    this.pkName = pkName;
  }

  public String getMkCode() {
    return mkCode;
  }

  public void setMkCode(String mkCode) {
    this.mkCode = mkCode;
  }

  public String getMkName() {
    return mkName;
  }

  public void setMkName(String mkName) {
    this.mkName = mkName;
  }

  public Date getLastVer() {
    return lastVer;
  }

  public void setLastVer(Date lastVer) {
    this.lastVer = lastVer;
  }

  public String getDownloadStatus() {
    return downloadStatus;
  }

  public void setDownloadStatus(String downloadStatus) {
    this.downloadStatus = downloadStatus;
  }

  public String getPayStatus() {
    return payStatus;
  }

  public void setPayStatus(String payStatus) {
    this.payStatus = payStatus;
  }

  public String getBudgetId() {
    return budgetId;
  }

  public void setBudgetId(String budgetId) {
    this.budgetId = budgetId;
  }

}