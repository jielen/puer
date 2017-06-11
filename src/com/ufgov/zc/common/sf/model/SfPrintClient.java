package com.ufgov.zc.common.sf.model;

import java.math.BigDecimal;

import com.ufgov.zc.common.zc.model.ZcBaseBill;

public class SfPrintClient  extends ZcBaseBill{
    /**
   * 
   */
  private static final long serialVersionUID = -1146763482175090624L;

  public static final String SEQ_SF_BAR_PRINT_ID="SEQ_SF_BAR_PRINT_ID";
  
  /**
   * 条码设置时，其来源的按钮 是委托
   */
  public static final String TM_BTN_WT="WT";
  /**
   * 检材
   */
  public static final String TM_BTN_JC="JC";
    private BigDecimal printId;

    private String mac;

    private String printName;

    private String ip;
 

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getPrintName() {
        return printName;
    }

    public void setPrintName(String printName) {
        this.printName = printName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public BigDecimal getPrintId() {
      return printId;
    }

    public void setPrintId(BigDecimal printId) {
      this.printId = printId;
    }
}