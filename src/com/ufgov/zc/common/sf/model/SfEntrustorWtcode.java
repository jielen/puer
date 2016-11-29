package com.ufgov.zc.common.sf.model;

import java.math.BigDecimal;

import com.ufgov.zc.common.zc.model.ZcBaseBill;

public class SfEntrustorWtcode extends ZcBaseBill {
  
    /**
   * 
   */
  private static final long serialVersionUID = -3574515888044279231L;

  public static final String SEQ_SF_ENTRUSTOR_WTCODE_ID = "SEQ_SF_ENTRUSTOR_WTCODE_ID";

    private BigDecimal wtcodeId; 

    private String numNo;

    private BigDecimal entrustorId;

    private String jdCompany;

    public BigDecimal getWtcodeId() {
        return wtcodeId;
    }

    public void setWtcodeId(BigDecimal wtcodeId) {
        this.wtcodeId = wtcodeId;
    }
 

    public String getNumNo() {
        return numNo;
    }

    public void setNumNo(String numNo) {
        this.numNo = numNo;
    }

    public BigDecimal getEntrustorId() {
        return entrustorId;
    }

    public void setEntrustorId(BigDecimal entrustorId) {
        this.entrustorId = entrustorId;
    }

    public String getJdCompany() {
        return jdCompany;
    }

    public void setJdCompany(String jdCompany) {
        this.jdCompany = jdCompany;
    }

    public String getWeiTuoCode(String entrustorName) {
      StringBuffer sb=new StringBuffer();
      sb.append(entrustorName);
      sb.append("委[").append(nd).append("]第").append(numNo).append("号");
      return sb.toString();
    }
}