package com.ufgov.zc.common.zc.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ZcPProMakeforF3 extends ZcBaseBill implements Serializable {

  private static final long serialVersionUID = 1585473096423591655L;

  private String planId;

  private String budgetId;

  private BigDecimal planMoney = new BigDecimal("0.00");;

  private Integer adjustWay;

  private String planNO;

  private Date lastVer;

  private int downloadStatus;

  public String getPlanId() {
    return planId;
  }

  public void setPlanId(String planId) {
    this.planId = planId;
  }

  public String getBudgetId() {
    return budgetId;
  }

  public void setBudgetId(String budgetId) {
    this.budgetId = budgetId;
  }

  public BigDecimal getPlanMoney() {
    return planMoney;
  }

  public void setPlanMoney(BigDecimal planMoney) {
    this.planMoney = planMoney;
  }

  public Integer getAdjustWay() {
    return adjustWay;
  }

  public void setAdjustWay(Integer adjustWay) {
    this.adjustWay = adjustWay;
  }

  public String getPlanNO() {
    return planNO;
  }

  public void setPlanNO(String planNO) {
    this.planNO = planNO;
  }

  public Date getLastVer() {
    return lastVer;
  }

  public void setLastVer(Date lastVer) {
    this.lastVer = lastVer;
  }

  public int getDownloadStatus() {
    return downloadStatus;
  }

  public void setDownloadStatus(int downloadStatus) {
    this.downloadStatus = downloadStatus;
  }

}