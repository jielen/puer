/**
 * 
 */
package com.ufgov.zc.common.zc.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Administrator
 *
 */
public class ZcQxQbItem extends ZcBaseBill {
  
  /**
   * 
   */
  private static final long serialVersionUID = 4731852471867378374L;
  
  
  /**
   * 实际金额-由实际财政资金+实际其他资金得到
   */
  private BigDecimal itemVal;
  /**
   * 实际财政资金-由单位录入
   */
  private BigDecimal itemBi;
  /**
   * 实际其他资金-由单位录入
   */
  private BigDecimal itemOther;
  
  public BigDecimal getItemVal() {
    return itemVal;
  }
  public void setItemVal(BigDecimal itemVal) {
    this.itemVal = itemVal;
  }
  public BigDecimal getItemBi() {
    return itemBi;
  }
  public void setItemBi(BigDecimal itemBi) {
    this.itemBi = itemBi;
  }
  public BigDecimal getItemOther() {
    return itemOther;
  }
  public void setItemOther(BigDecimal itemOther) {
    this.itemOther = itemOther;
  }

}
