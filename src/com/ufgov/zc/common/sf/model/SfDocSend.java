package com.ufgov.zc.common.sf.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ufgov.zc.common.zc.model.ZcBaseBill;

public class SfDocSend extends ZcBaseBill {
  
  /**
   * 
   */
  private static final long serialVersionUID = 3844202944365796916L;

  public static final String SEQ_SF_DOC_SEND_ID = "SEQ_SF_DOC_SEND_ID";

  /**
   * 回执页签
   */
  public static final String TAB_ID = "SfDocSend_Tab";

  /**
   * 回执搜索条件
   */
  public static final String SEARCH_ID = "SfDocSend_search";

  public static final String COL_CO_CODE="SF_DOC_SEND_CO_CODE"; // 鉴定机构
  public static final String COL_ENTRUST_CODE="SF_DOC_SEND_ENTRUST_CODE"; // 委托编号
  public static final String COL_ENTRUST_ID="SF_DOC_SEND_ENTRUST_ID"; // 委托ID
  public static final String COL_JD_DOC_AUDIT_ID="SF_DOC_SEND_JD_DOC_AUDIT_ID"; // 鉴定文书审批单ID
  public static final String COL_ND="SF_DOC_SEND_ND"; // 年度
  public static final String COL_RECIEVOR="SF_DOC_SEND_RECIEVOR"; // 领取人
  public static final String COL_RECIEVOR_TEL="SF_DOC_SEND_RECIEVOR_TEL"; // 领取人电话
  public static final String COL_REMARK="SF_DOC_SEND_REMARK"; // 备注
  public static final String COL_SENDOR="SF_DOC_SEND_SENDOR"; // 发放人
  public static final String COL_SEND_DATE="SF_DOC_SEND_SEND_DATE"; // 领取时间
  public static final String COL_SEND_ID="SF_DOC_SEND_SEND_ID"; // 文书发放登记ID
  public static final String COL_SEND_TYPE="SF_DOC_SEND_SEND_TYPE"; // 领取方式
  public static final String COL_NAME="SF_DOC_SEND_NAME"; // 名称
  public static final String COL_WTF="SF_DOC_SEND_WTF_NAME"; // 委托方
  
  public static final String VS_SF_DOC_SEND_SEND_TYPE="VS_SF_DOC_SEND_SEND_TYPE";

  private SfEntrust entrust=new SfEntrust();
  private List docDetailLst=new ArrayList();
  private List materialLst=new ArrayList(); 
  
    private BigDecimal sendId;
    
    private String name;
    private String wtfName;

    private String recievor;

    private String recievorTel;

    private Date sendDate;

    private String sendor; 

    private String remark;

    private BigDecimal entrustId;

    private String entrustCode;

    private String sendType;

    private BigDecimal jdDocAuditId;

    public BigDecimal getSendId() {
        return sendId;
    }

    public void setSendId(BigDecimal sendId) {
        this.sendId = sendId;
    }

    public String getRecievor() {
        return recievor;
    }

    public void setRecievor(String recievor) {
        this.recievor = recievor;
    }

    public String getRecievorTel() {
        return recievorTel;
    }

    public void setRecievorTel(String recievorTel) {
        this.recievorTel = recievorTel;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public String getSendor() {
        return sendor;
    }

    public void setSendor(String sendor) {
        this.sendor = sendor;
    }

    
 
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public BigDecimal getEntrustId() {
        return getEntrust().getEntrustId();
    }

    public void setEntrustId(BigDecimal entrustId) {
        this.entrustId = entrustId;
    }

    public String getEntrustCode() {
        return getEntrust().getCode();
    }

    public void setEntrustCode(String entrustCode) {
        this.entrustCode = entrustCode;
    }

    public String getSendType() {
        return sendType;
    }

    public void setSendType(String sendType) {
        this.sendType = sendType;
    }

    public BigDecimal getJdDocAuditId() {
        return jdDocAuditId;
    }

    public void setJdDocAuditId(BigDecimal jdDocAuditId) {
        this.jdDocAuditId = jdDocAuditId;
    }

    public List getDocDetailLst() {
      return docDetailLst;
    }

    public void setDocDetailLst(List docDetailLst) {
      this.docDetailLst = docDetailLst;
    }

    public SfEntrust getEntrust() {
      return entrust;
    }

    public void setEntrust(SfEntrust entrust) {
      this.entrust = entrust;
    }

    public List getMaterialLst() {
      return materialLst;
    }

    public void setMaterialLst(List materialLst) {
      this.materialLst = materialLst;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getWtfName() {
      return wtfName;
    }

    public void setWtfName(String wtfName) {
      this.wtfName = wtfName;
    }
}