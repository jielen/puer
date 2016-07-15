package com.ufgov.zc.common.zc.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class ZcQxItem extends ZcQxQbItem {

  /**
   * 
   */
  private static final long serialVersionUID = 1664594461046356710L;
  
  private String qxCode;
  private String qxItemCode;
  private String chePai;
  private String itemType;
  private String itemContent;
  private String itemSpec;
  private BigDecimal itemNum;//数量
  private BigDecimal itemPrice;//单价
  private BigDecimal itemTotalSum;//总价
  private String itemUnit;//单价
  
  public String getQxCode() {
    return qxCode;
  }
  public void setQxCode(String qxCode) {
    this.qxCode = qxCode;
  }
  public String getQxItemCode() {
    return qxItemCode;
  }
  public void setQxItemCode(String qxItemCode) {
    this.qxItemCode = qxItemCode;
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
  public String getItemContent() {
    return itemContent;
  }
  public void setItemContent(String itemContent) {
    this.itemContent = itemContent;
  }
  public String getItemSpec() {
    return itemSpec;
  }
  public void setItemSpec(String itemSpec) {
    this.itemSpec = itemSpec;
  }
  public BigDecimal getItemNum() {
    return itemNum;
  }
  public void setItemNum(BigDecimal itemNum) {
    this.itemNum = itemNum;
  }
  public BigDecimal getItemPrice() {
    return itemPrice;
  }
  public void setItemPrice(BigDecimal itemPrice) {
    this.itemPrice = itemPrice;
  }
  public BigDecimal getItemTotalSum() {
    return itemTotalSum;
  }
  public void setItemTotalSum(BigDecimal itemTotalSum) {
    this.itemTotalSum = itemTotalSum;
  }
  public String getItemUnit() {
    return itemUnit;
  }
  public void setItemUnit(String itemUnit) {
    this.itemUnit = itemUnit;
  }

}
