package com.ufgov.zc.common.sf.model;

import java.math.BigDecimal;

import com.ufgov.zc.common.zc.model.ZcBaseBill;

public class SfDocSendDetail extends ZcBaseBill {
  
  private SfJdDocAuditDetail docDetail=new SfJdDocAuditDetail();
  
  public static final String COL_DOC_NAME="SF_DOC_SEND_DETAIL_DOC_NAME"; // 名称
  public static final String COL_QUANTITY="SF_DOC_SEND_DETAIL_QUANTITY"; // 数量
  public static final String COL_REMARK="SF_DOC_SEND_DETAIL_REMARK"; // 备注
  public static final String COL_SEND_ID="SF_DOC_SEND_DETAIL_SEND_ID"; // 文书发放登记ID
  public static final String COL_UNIT="SF_DOC_SEND_DETAIL_UNIT"; // 单位

  
  private SfJdDocAuditDetail docAuditDetail=new SfJdDocAuditDetail();
    private BigDecimal sendId;

    private String docName;

    private String quantity;

    private String unit;

    private String remark;
    

    public BigDecimal getSendId() {
        return sendId;
    }

    public void setSendId(BigDecimal sendId) {
        this.sendId = sendId;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public SfJdDocAuditDetail getDocDetail() {
      return docDetail;
    }

    public void setDocDetail(SfJdDocAuditDetail docDetail) {
      this.docDetail = docDetail;
    }

    public SfJdDocAuditDetail getDocAuditDetail() {
      return docAuditDetail;
    }

    public void setDocAuditDetail(SfJdDocAuditDetail docAuditDetail) {
      this.docAuditDetail = docAuditDetail;
    }
}