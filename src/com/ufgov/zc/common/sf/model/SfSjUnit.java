package com.ufgov.zc.common.sf.model;

import java.math.BigDecimal;

import com.ufgov.zc.common.zc.model.ZcBaseBill;

public class SfSjUnit extends ZcBaseBill {
  
  /**
   * 
   */
  private static final long serialVersionUID = 6099816960218621378L;
  private BigDecimal unitId;
  private String unitName;
  public BigDecimal getUnitId() {
    return unitId;
  }
  public void setUnitId(BigDecimal unitId) {
    this.unitId = unitId;
  }
  public String getUnitName() {
    return unitName;
  }
  public void setUnitName(String unitName) {
    this.unitName = unitName;
  }
  

}
