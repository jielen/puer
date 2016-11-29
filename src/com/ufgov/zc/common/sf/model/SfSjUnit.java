package com.ufgov.zc.common.sf.model;

import java.math.BigDecimal;

import com.ufgov.zc.common.zc.model.ZcBaseBill;

public class SfSjUnit extends ZcBaseBill {
  
  /**
   * 
   */
  private static final long serialVersionUID = 6099816960218621378L;
  
  public static final String SEQ_SF_SJ_UNIT_ID="SEQ_SF_SJ_UNIT_ID";
  
  public static final String COL_CO_CODE="SF_SJ_UNIT_CO_CODE"; // 鉴定中心
  public static final String COL_UNIT_ID="SF_SJ_UNIT_UNIT_ID"; // 试剂单位ID
  public static final String COL_UNIT_NAME="SF_SJ_UNIT_UNIT_NAME"; // 试剂单位名称
  
  public static final String TAB_ID="SfSjUnit_Tab";

  
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
