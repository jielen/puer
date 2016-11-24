package com.ufgov.zc.common.sf.model;

import java.math.BigDecimal;

import com.ufgov.zc.common.zc.model.ZcBaseBill;

public class SfReportCode extends ZcBaseBill {

  /**
   * 
   */
  private static final long serialVersionUID = 6029752448881059606L;

  private BigDecimal entrustId;

  private String reportCode;

  public BigDecimal getEntrustId() {
    return entrustId;
  }

  public void setEntrustId(BigDecimal entrustId) {
    this.entrustId = entrustId;
  }

  public String getReportCode() {
    return reportCode;
  }

  public void setReportCode(String reportCode) {
    this.reportCode = reportCode;
  }

}
