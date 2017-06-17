package com.ufgov.zc.common.sf.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ufgov.zc.common.util.EmpMeta;
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

  public static final String SF_VS_DOC_SEND_STATUS = "SF_VS_DOC_SEND_STATUS";

  public static final String SF_DOC_SEND_STATUS_draft = "0";//草稿
  public static final String SF_DOC_SEND_STATUS_waiting_jieshou = "5";//综合接收
  public static final String SF_DOC_SEND_STATUS_waiting_send = "10";//待发放
  public static final String SF_DOC_SEND_STATUS_sended = "exec";//已发放
  

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
  public static final String COL_STATUS="SF_DOC_SEND_STATUS"; // 状态
  public static final String COL_PROCESS_INST_ID="SF_DOC_SEND_PROCESS_INST_ID"; // 工作流实例号
  public static final String COL_TI_JIAO_REN="SF_DOC_SEND_TI_JIAO_REN"; // 移交人
  public static final String COL_TI_JIAO_DATE="SF_DOC_SEND_TI_JIAO_DATE"; // 移交时间
  public static final String COL_JIE_SHOU_REN="SF_ENTRUST_JIE_SHOU_REN"; // 接收人
  public static final String COL_JIE_SHOU_DATE="SF_ENTRUST_JIE_SHOU_DATE"; // 接收时间
  public static final String COL_TI_JIAO_REMARK="SF_ENTRUST_TI_JIAO_REMARK"; // 移交说明
  public static final String COL_JIE_SHOU_REMARK="SF_ENTRUST_JIE_SHOU_REMARK"; // 接收备注


  
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
    private String sendorName; 

    private String remark;

    private BigDecimal entrustId;

    private String entrustCode;

    private String sendType;

    private BigDecimal jdDocAuditId;
    
    private String tiJiaoRen;
    private String tiJiaoRenName;
    private Date tiJiaoDate;
    private String jieShouRen;
    private String jieShouRenName;
    private Date jieShouDate;
    private String tiJiaoRemark; 
    private String jieShouRemark;
    private String status;
    
    public String getTiJiaoRen() {
      return tiJiaoRen;
    }

    public void setTiJiaoRen(String tiJiaoRen) {
      this.tiJiaoRen = tiJiaoRen;
    }

    public String getTiJiaoRenName() { 
      return EmpMeta.getEmpName(tiJiaoRen);
    }

    public void setTiJiaoRenName(String tiJiaoRenName) {
      this.tiJiaoRenName = tiJiaoRenName;
    }

    public Date getTiJiaoDate() {
      return tiJiaoDate;
    }

    public void setTiJiaoDate(Date tiJiaoDate) {
      this.tiJiaoDate = tiJiaoDate;
    }

    public String getJieShouRen() {
      return jieShouRen;
    }

    public void setJieShouRen(String jieShouRen) {
      this.jieShouRen = jieShouRen;
    }

    public String getJieShouRenName() {
      return EmpMeta.getEmpName(jieShouRen); 
    }

    public void setJieShouRenName(String jieShouRenName) {
      this.jieShouRenName = jieShouRenName;
    }

    public Date getJieShouDate() {
      return jieShouDate;
    }

    public void setJieShouDate(Date jieShouDate) {
      this.jieShouDate = jieShouDate;
    }

    public String getTiJiaoRemark() {
      return tiJiaoRemark;
    }

    public void setTiJiaoRemark(String tiJiaoRemark) {
      this.tiJiaoRemark = tiJiaoRemark;
    }


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

    public String getJieShouRemark() {
      return jieShouRemark;
    }

    public void setJieShouRemark(String jieShouRemark) {
      this.jieShouRemark = jieShouRemark;
    }

    public String getStatus() {
      return status;
    }

    public void setStatus(String status) {
      this.status = status;
    }

    public String getSendorName() {        
        return EmpMeta.getEmpName(sendor);
    }

    public void setSendorName(String sendorName) {
      this.sendorName = sendorName;
    }
}