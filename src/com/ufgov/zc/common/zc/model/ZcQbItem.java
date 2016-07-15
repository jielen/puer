package com.ufgov.zc.common.zc.model;

import java.math.BigDecimal;
import java.util.Date;

public class ZcQbItem  extends ZcQxQbItem {

  /**
   * 
   */
  private static final long serialVersionUID = 1664594461046356710L;
  
  private String qbCode;
  private String qbItemCode;
  private String chePai;
  
  private String cheFdj;
  private String cheCjh;

  private String itemType;
  private BigDecimal itemTotalSum;//总价
  
  private Date startDate;
  private Date endDate;
  
  public String getCheFdj() {
    return cheFdj;
  }
  public void setCheFdj(String cheFdj) {
    this.cheFdj = cheFdj;
  }
  public String getCheCjh() {
    return cheCjh;
  }
  public void setCheCjh(String cheCjh) {
    this.cheCjh = cheCjh;
  }
  public Date getStartDate() {
    return startDate;
  }
  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }
  public Date getEndDate() {
    return endDate;
  }
  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }
  
  public String getQbCode() {
    return qbCode;
  }
  public void setQbCode(String qbCode) {
    this.qbCode = qbCode;
  }
  public String getQbItemCode() {
    return qbItemCode;
  }
  public void setQbItemCode(String qbItemCode) {
    this.qbItemCode = qbItemCode;
  }
  public String getItemType() {
    return itemType;
  }
  public void setItemType(String itemType) {
    this.itemType = itemType;
  }
  

  public String getChePai() {
    return chePai;
  }
  public void setChePai(String chePai) {
    this.chePai = chePai;
  }
  public BigDecimal getItemTotalSum() {
    return itemTotalSum;
  }
  public void setItemTotalSum(BigDecimal itemTotalSum) {
    this.itemTotalSum = itemTotalSum;
  }

}
