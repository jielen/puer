package com.ufgov.zc.common.sf.model;

import java.math.BigDecimal;

import com.ufgov.zc.common.zc.model.ZcBaseBill;

public class SfDistrict extends ZcBaseBill{
  
  private BigDecimal districtId;
  private String districtName;
  public BigDecimal getDistrictId() {
    return districtId;
  }
  public void setDistrictId(BigDecimal districtId) {
    this.districtId = districtId;
  }
  public String getDistrictName() {
    return districtName;
  }
  public void setDistrictName(String districtName) {
    this.districtName = districtName;
  }

}
