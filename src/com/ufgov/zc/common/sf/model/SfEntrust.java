package com.ufgov.zc.common.sf.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ufgov.zc.common.util.EmpMeta;
import com.ufgov.zc.common.zc.model.ZcBaseBill;

public class SfEntrust extends ZcBaseBill {

  public static final String SEQ_SF_ENTRUST_ID = "SEQ_SF_ENTRUST_ID";

  /**
   * 
   */
  private static final long serialVersionUID = 5048538438314822623L;

  /**
   * 司法鉴定委托页签
   */
  public static final String TAB_ID = "SfEntrust_Tab";

  /**
   * 司法鉴定委托搜索条件
   */
  public static final String SEARCH_ID = "SfEntrust_search";

  public static final String SF_VS_ENTRUST_STATUS = "SF_VS_ENTRUST_STATUS";

  public static final String SF_VS_ENTRUST_DOC_SEND_TYPE = "SF_VS_ENTRUST_DOC_SEND_TYPE";

  /**
   * 自取
   */
  public static final String SF_VS_ENTRUST_DOC_SEND_TYPE_ZIQU = "ziqu";

  /**
   * 邮寄
   */
  public static final String SF_VS_ENTRUST_DOC_SEND_TYPE_YOUJI = "youji";

  /**
   * 其他方式
   */
  public static final String SF_VS_ENTRUST_DOC_SEND_TYPE_QITA = "qita";

  public static final String COL_ACCEPTOR = "SF_ENTRUST_ACCEPTOR"; // 受理人

  public static final String COL_ACCEPT_DATE = "SF_ENTRUST_ACCEPT_DATE"; // 受理时间

  public static final String COL_BRIEF = "SF_ENTRUST_BRIEF"; // 检案摘要

  public static final String COL_CODE = "SF_ENTRUST_CODE"; // 委托编号

  public static final String COL_ENTRUSTOR_ID = "SF_ENTRUST_ENTRUSTOR_ID"; // 委托方ID

  public static final String COL_ENTRUST_ID = "SF_ENTRUST_ENTRUST_ID"; // 委托ID

  public static final String COL_INPUTOR = "SF_ENTRUST_INPUTOR"; // 录入人

  public static final String COL_INPUT_DATE = "SF_ENTRUST_INPUT_DATE"; // 录入时间

  public static final String COL_IS_ACCEPT = "SF_ENTRUST_IS_ACCEPT"; // 是否受理

  public static final String COL_IS_CXJD = "SF_ENTRUST_IS_CXJD"; // 是否属于重新鉴定

  public static final String COL_JD_COMPANY = "SF_ENTRUST_JD_COMPANY"; // 鉴定机构

  public static final String COL_JD_FZR = "SF_ENTRUST_JD_FZR"; // 鉴定负责人

  public static final String COL_JD_FHR = "SF_ENTRUST_JD_FHR"; // 鉴定复核人

  public static final String COL_JD_HISTORY = "SF_ENTRUST_JD_HISTORY"; // 原鉴定情况

  public static final String COL_JD_ORG = "SF_ENTRUST_JD_ORG"; // 鉴定科室

  public static final String COL_JD_REQUIRE = "SF_ENTRUST_JD_REQUIRE"; // 鉴定要求、目的

  public static final String COL_JD_TARGET_ID = "SF_ENTRUST_JD_TARGET_ID"; // 鉴定对象ID

  public static final String COL_MAJOR_CODE = "SF_ENTRUST_MAJOR_CODE"; // 专业编号

  public static final String COL_NAME = "SF_ENTRUST_NAME"; // 委托名称、案/事件名称

  public static final String COL_ND = "SF_ENTRUST_ND"; // 年度

  public static final String COL_NOT_ACCEPT_REASON = "SF_ENTRUST_NOT_ACCEPT_REASON"; // 不受理原因

  public static final String COL_PROCESS_INST_ID = "SF_ENTRUST_PROCESS_INST_ID"; // 工作流实例号

  public static final String COL_REMARK = "SF_ENTRUST_REMARK"; // 备注

  public static final String COL_SJR = "SF_ENTRUST_SJR"; // 送检人

  public static final String COL_SJR_TEL = "SF_ENTRUST_SJR_TEL"; // 送检人电话

  public static final String COL_SJR_ZJ_CODE = "SF_ENTRUST_SJR_ZJ_CODE"; // 送检人证件号码

  public static final String COL_SJR_ZJ_TYPE = "SF_ENTRUST_SJR_ZJ_TYPE"; // 送检人证件类型

  public static final String COL_SJR_ADDRESS = "SF_ENTRUST_SJR_ADDRESS"; // 送检人地址

  public static final String COL_STATUS = "SF_ENTRUST_STATUS"; // 状态

  public static final String COL_WT_DATE = "SF_ENTRUST_WT_DATE"; // 委托时间

  public static final String COL_WT_ID_PARENT = "SF_ENTRUST_WT_ID_PARENT"; // 上次鉴定委托ID

  public static final String COL_ENTRUSTOR_NAME = "SF_ENTRUST_ENTRUSTOR_NAME";//委托方

  public static final String COL_MAJOR_NAME = "SF_ENTRUST_MAJOR_NAME";//鉴定专业

  public static final String COL_JD_DOC_SEND_TYPE = "SF_ENTRUST_JD_DOC_SEND_TYPE";//鉴定文书发送方式

  public static final String COL_JD_DOC_SEND_TYPE_FZ = "SF_ENTRUST_JD_DOC_SEND_TYPE_FZ";//鉴定文书发送方式附注

  public static final String COL_JD_CHARGE = "SF_ENTRUST_JD_CHARGE";//鉴定费用
  
  public static final String BKMK_MATERIALS="BKMK_MATERIALS";//检材样板

  private String inputorName;

  private String acceptorName;

  private String jdFzrName;

  private String jdFhr;

  private String jdFhrName;

  private String sjrAddress;

  private String jdTargetName;

  /**
   * 委托方
   */
  private SfEntrustor entrustor = new SfEntrustor();

  /**
   * 鉴定专业
   */
  private SfMajor major = new SfMajor();

  /**
   * 鉴定对象
   */
  private SfJdTarget jdTarget = new SfJdTarget();

  /**
   * 鉴定材料集合
   */
  private List materials = new ArrayList();

  /**
   * 协议事项
   */
  private List xysxLst = new ArrayList();

  /**
   * 当属于重新鉴定时，这个属性存放上次的鉴定
   */
  private SfEntrust oldEntrust = null;

  /**
   * 历史鉴定负责人
   */
  private String lsJdFzr;

  private String lsJdFzrName;

  /**
   * 历史鉴定复核人
   */
  private String lsJdFhr;

  private String lsJdFhrName;

  private String jdDocSendType;

  private String jdDocSendTypeFz;

  private BigDecimal jdCharge;

  private List jdChargeDetaillst = new ArrayList();

  public String getLsJdFzrName() {
    if (getOldEntrust() != null)
      return getOldEntrust().getJdFzrName();
    return lsJdFzrName;
  }

  public void setLsJdFzrName(String lsJdFzrName) {
    this.lsJdFzrName = lsJdFzrName;
  }

  public String getLsJdFhrName() {
    if (getOldEntrust() != null)
      return getOldEntrust().getJdFhrName();
    return lsJdFhrName;
  }

  public void setLsJdFhrName(String lsJdFhrName) {
    this.lsJdFhrName = lsJdFhrName;
  }

  public String getLsJdFzr() {
    if (getOldEntrust() != null)
      return getOldEntrust().getJdFzr();
    return lsJdFzr;
  }

  public void setLsJdFzr(String lsJdFzr) {
    this.lsJdFzr = lsJdFzr;
  }

  public String getLsJdFhr() {
    if (getOldEntrust() != null)
      return getOldEntrust().getJdFhr();
    return lsJdFhr;
  }

  public void setLsJdFhr(String lsJdFhr) {
    this.lsJdFhr = lsJdFhr;
  }

  public String getJdDocSendType() {
    return jdDocSendType;
  }

  public void setJdDocSendType(String jdDocSendType) {
    this.jdDocSendType = jdDocSendType;
  }

  public String getJdDocSendTypeFz() {
    return jdDocSendTypeFz;
  }

  public void setJdDocSendTypeFz(String jdDocSendTypeFz) {
    this.jdDocSendTypeFz = jdDocSendTypeFz;
  }

  public String getJdFhr() {
    return jdFhr;
  }

  public void setJdFhr(String jdFhr) {
    this.jdFhr = jdFhr;
  }

  public String getJdFhrName() {
    return EmpMeta.getEmpName(getJdFhr());
  }

  public void setJdFhrName(String jdFhrName) {
    this.jdFhrName = jdFhrName;
  }

  public String getInputorName() {
    return EmpMeta.getEmpName(getInputor());
  }

  public void setInputorName(String inputorName) {
    this.inputorName = inputorName;
  }

  public SfEntrustor getEntrustor() {
    return entrustor;
  }

  public void setEntrustor(SfEntrustor entrustor) {
    this.entrustor = entrustor;
  }

  public SfMajor getMajor() {
    return major;
  }

  public void setMajor(SfMajor major) {
    this.major = major;
  }

  public SfJdTarget getJdTarget() {
    return jdTarget;
  }

  public void setJdTarget(SfJdTarget jdTarget) {
    this.jdTarget = jdTarget;
  }

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column SF_ENTRUST.ENTRUST_ID
   * @mbggenerated  Wed Jan 07 11:38:37 CST 2015
   */
  private BigDecimal entrustId;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column SF_ENTRUST.CODE
   * @mbggenerated  Wed Jan 07 11:38:37 CST 2015
   */
  private String code;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column SF_ENTRUST.NAME
   * @mbggenerated  Wed Jan 07 11:38:37 CST 2015
   */
  private String name;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column SF_ENTRUST.STATUS
   * @mbggenerated  Wed Jan 07 11:38:37 CST 2015
   */
  private String status;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column SF_ENTRUST.ENTRUSTOR_ID
   * @mbggenerated  Wed Jan 07 11:38:37 CST 2015
   */
  private BigDecimal entrustorId;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column SF_ENTRUST.WT_DATE
   * @mbggenerated  Wed Jan 07 11:38:37 CST 2015
   */
  private Date wtDate;

  private String wtDateStr;

  public String getWtDateStr() {
    return wtDateStr;
  }

  public void setWtDateStr(String wtDateStr) {
    this.wtDateStr = wtDateStr;
  }

  public String getInputDateStr() {
    return inputDateStr;
  }

  public void setInputDateStr(String inputDateStr) {
    this.inputDateStr = inputDateStr;
  }

  public String getAcceptDateStr() {
    return acceptDateStr;
  }

  public void setAcceptDateStr(String acceptDateStr) {
    this.acceptDateStr = acceptDateStr;
  }

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column SF_ENTRUST.SJR
   * @mbggenerated  Wed Jan 07 11:38:37 CST 2015
   */
  private String sjr;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column SF_ENTRUST.SJR_TEL
   * @mbggenerated  Wed Jan 07 11:38:37 CST 2015
   */
  private String sjrTel;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column SF_ENTRUST.SJR_ZJ_TYPE
   * @mbggenerated  Wed Jan 07 11:38:37 CST 2015
   */
  private String sjrZjType;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column SF_ENTRUST.SJR_ZJ_CODE
   * @mbggenerated  Wed Jan 07 11:38:37 CST 2015
   */
  private String sjrZjCode;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column SF_ENTRUST.MAJOR_CODE
   * @mbggenerated  Wed Jan 07 11:38:37 CST 2015
   */
  private String majorCode;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column SF_ENTRUST.JD_ORG
   * @mbggenerated  Wed Jan 07 11:38:37 CST 2015
   */
  private String jdOrg;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column SF_ENTRUST.JD_FZR
   * @mbggenerated  Wed Jan 07 11:38:37 CST 2015
   */
  private String jdFzr;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column SF_ENTRUST.JD_HISTORY
   * @mbggenerated  Wed Jan 07 11:38:37 CST 2015
   */
  private String jdHistory;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column SF_ENTRUST.JD_REQUIRE
   * @mbggenerated  Wed Jan 07 11:38:37 CST 2015
   */
  private String jdRequire;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column SF_ENTRUST.JD_COMPANY
   * @mbggenerated  Wed Jan 07 11:38:37 CST 2015
   */
  private String jdCompany;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column SF_ENTRUST.REMARK
   * @mbggenerated  Wed Jan 07 11:38:37 CST 2015
   */
  private String remark;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column SF_ENTRUST.IS_CXJD
   * @mbggenerated  Wed Jan 07 11:38:37 CST 2015
   */
  private String isCxjd;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column SF_ENTRUST.WT_ID_PARENT
   * @mbggenerated  Wed Jan 07 11:38:37 CST 2015
   */
  private BigDecimal wtIdParent;

  private String wtCodeParent;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column SF_ENTRUST.BRIEF
   * @mbggenerated  Wed Jan 07 11:38:37 CST 2015
   */
  private String brief;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column SF_ENTRUST.INPUTOR
   * @mbggenerated  Wed Jan 07 11:38:37 CST 2015
   */
  private String inputor;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column SF_ENTRUST.INPUT_DATE
   * @mbggenerated  Wed Jan 07 11:38:37 CST 2015
   */
  private Date inputDate;

  private String inputDateStr;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column SF_ENTRUST.ACCEPTOR
   * @mbggenerated  Wed Jan 07 11:38:37 CST 2015
   */
  private String acceptor;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column SF_ENTRUST.ACCEPT_DATE
   * @mbggenerated  Wed Jan 07 11:38:37 CST 2015
   */
  private Date acceptDate;

  private String acceptDateStr;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column SF_ENTRUST.IS_ACCEPT
   * @mbggenerated  Wed Jan 07 11:38:37 CST 2015
   */
  private String isAccept;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column SF_ENTRUST.NOT_ACCEPT_REASON
   * @mbggenerated  Wed Jan 07 11:38:37 CST 2015
   */
  private String notAcceptReason;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column SF_ENTRUST.JD_TARGET_ID
   * @mbggenerated  Wed Jan 07 11:38:37 CST 2015
   */
  private BigDecimal jdTargetId;

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database column SF_ENTRUST.ENTRUST_ID
   * @return  the value of SF_ENTRUST.ENTRUST_ID
   * @mbggenerated  Wed Jan 07 11:38:37 CST 2015
   */
  public BigDecimal getEntrustId() {
    return entrustId;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database column SF_ENTRUST.ENTRUST_ID
   * @param entrustId  the value for SF_ENTRUST.ENTRUST_ID
   * @mbggenerated  Wed Jan 07 11:38:37 CST 2015
   */
  public void setEntrustId(BigDecimal entrustId) {
    this.entrustId = entrustId;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database column SF_ENTRUST.CODE
   * @return  the value of SF_ENTRUST.CODE
   * @mbggenerated  Wed Jan 07 11:38:37 CST 2015
   */
  public String getCode() {
    return code;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database column SF_ENTRUST.CODE
   * @param code  the value for SF_ENTRUST.CODE
   * @mbggenerated  Wed Jan 07 11:38:37 CST 2015
   */
  public void setCode(String code) {
    this.code = code;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database column SF_ENTRUST.NAME
   * @return  the value of SF_ENTRUST.NAME
   * @mbggenerated  Wed Jan 07 11:38:37 CST 2015
   */
  public String getName() {
    return name;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database column SF_ENTRUST.NAME
   * @param name  the value for SF_ENTRUST.NAME
   * @mbggenerated  Wed Jan 07 11:38:37 CST 2015
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database column SF_ENTRUST.STATUS
   * @return  the value of SF_ENTRUST.STATUS
   * @mbggenerated  Wed Jan 07 11:38:37 CST 2015
   */
  public String getStatus() {
    return status;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database column SF_ENTRUST.STATUS
   * @param status  the value for SF_ENTRUST.STATUS
   * @mbggenerated  Wed Jan 07 11:38:37 CST 2015
   */
  public void setStatus(String status) {
    this.status = status;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database column SF_ENTRUST.ENTRUSTOR_ID
   * @return  the value of SF_ENTRUST.ENTRUSTOR_ID
   * @mbggenerated  Wed Jan 07 11:38:37 CST 2015
   */
  public BigDecimal getEntrustorId() {
    return getEntrustor().getEntrustorId();
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database column SF_ENTRUST.ENTRUSTOR_ID
   * @param entrustorId  the value for SF_ENTRUST.ENTRUSTOR_ID
   * @mbggenerated  Wed Jan 07 11:38:37 CST 2015
   */
  public void setEntrustorId(BigDecimal entrustorId) {
    this.entrustorId = entrustorId;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database column SF_ENTRUST.WT_DATE
   * @return  the value of SF_ENTRUST.WT_DATE
   * @mbggenerated  Wed Jan 07 11:38:37 CST 2015
   */
  public Date getWtDate() {
    return wtDate;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database column SF_ENTRUST.WT_DATE
   * @param wtDate  the value for SF_ENTRUST.WT_DATE
   * @mbggenerated  Wed Jan 07 11:38:37 CST 2015
   */
  public void setWtDate(Date wtDate) {
    this.wtDate = wtDate;

  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database column SF_ENTRUST.SJR
   * @return  the value of SF_ENTRUST.SJR
   * @mbggenerated  Wed Jan 07 11:38:37 CST 2015
   */
  public String getSjr() {
    return sjr;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database column SF_ENTRUST.SJR
   * @param sjr  the value for SF_ENTRUST.SJR
   * @mbggenerated  Wed Jan 07 11:38:37 CST 2015
   */
  public void setSjr(String sjr) {
    this.sjr = sjr;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database column SF_ENTRUST.SJR_TEL
   * @return  the value of SF_ENTRUST.SJR_TEL
   * @mbggenerated  Wed Jan 07 11:38:37 CST 2015
   */
  public String getSjrTel() {
    return sjrTel;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database column SF_ENTRUST.SJR_TEL
   * @param sjrTel  the value for SF_ENTRUST.SJR_TEL
   * @mbggenerated  Wed Jan 07 11:38:37 CST 2015
   */
  public void setSjrTel(String sjrTel) {
    this.sjrTel = sjrTel;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database column SF_ENTRUST.SJR_ZJ_TYPE
   * @return  the value of SF_ENTRUST.SJR_ZJ_TYPE
   * @mbggenerated  Wed Jan 07 11:38:37 CST 2015
   */
  public String getSjrZjType() {
    return sjrZjType;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database column SF_ENTRUST.SJR_ZJ_TYPE
   * @param sjrZjType  the value for SF_ENTRUST.SJR_ZJ_TYPE
   * @mbggenerated  Wed Jan 07 11:38:37 CST 2015
   */
  public void setSjrZjType(String sjrZjType) {
    this.sjrZjType = sjrZjType;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database column SF_ENTRUST.SJR_ZJ_CODE
   * @return  the value of SF_ENTRUST.SJR_ZJ_CODE
   * @mbggenerated  Wed Jan 07 11:38:37 CST 2015
   */
  public String getSjrZjCode() {
    return sjrZjCode;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database column SF_ENTRUST.SJR_ZJ_CODE
   * @param sjrZjCode  the value for SF_ENTRUST.SJR_ZJ_CODE
   * @mbggenerated  Wed Jan 07 11:38:37 CST 2015
   */
  public void setSjrZjCode(String sjrZjCode) {
    this.sjrZjCode = sjrZjCode;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database column SF_ENTRUST.MAJOR_CODE
   * @return  the value of SF_ENTRUST.MAJOR_CODE
   * @mbggenerated  Wed Jan 07 11:38:37 CST 2015
   */
  public String getMajorCode() {
    if (getMajor() != null)
      return getMajor().getMajorCode();
    return majorCode;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database column SF_ENTRUST.MAJOR_CODE
   * @param majorCode  the value for SF_ENTRUST.MAJOR_CODE
   * @mbggenerated  Wed Jan 07 11:38:37 CST 2015
   */
  public void setMajorCode(String majorCode) {
    this.majorCode = majorCode;
    SfMajor major = new SfMajor();
    major.setMajorCode(majorCode);
    setMajor(major);
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database column SF_ENTRUST.JD_ORG
   * @return  the value of SF_ENTRUST.JD_ORG
   * @mbggenerated  Wed Jan 07 11:38:37 CST 2015
   */
  public String getJdOrg() {
    return jdOrg;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database column SF_ENTRUST.JD_ORG
   * @param jdOrg  the value for SF_ENTRUST.JD_ORG
   * @mbggenerated  Wed Jan 07 11:38:37 CST 2015
   */
  public void setJdOrg(String jdOrg) {
    this.jdOrg = jdOrg;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database column SF_ENTRUST.JD_FZR
   * @return  the value of SF_ENTRUST.JD_FZR
   * @mbggenerated  Wed Jan 07 11:38:37 CST 2015
   */
  public String getJdFzr() {
    return jdFzr;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database column SF_ENTRUST.JD_FZR
   * @param jdFzr  the value for SF_ENTRUST.JD_FZR
   * @mbggenerated  Wed Jan 07 11:38:37 CST 2015
   */
  public void setJdFzr(String jdFzr) {
    this.jdFzr = jdFzr;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database column SF_ENTRUST.JD_HISTORY
   * @return  the value of SF_ENTRUST.JD_HISTORY
   * @mbggenerated  Wed Jan 07 11:38:37 CST 2015
   */
  public String getJdHistory() {
    return jdHistory;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database column SF_ENTRUST.JD_HISTORY
   * @param jdHistory  the value for SF_ENTRUST.JD_HISTORY
   * @mbggenerated  Wed Jan 07 11:38:37 CST 2015
   */
  public void setJdHistory(String jdHistory) {
    this.jdHistory = jdHistory;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database column SF_ENTRUST.JD_REQUIRE
   * @return  the value of SF_ENTRUST.JD_REQUIRE
   * @mbggenerated  Wed Jan 07 11:38:37 CST 2015
   */
  public String getJdRequire() {
    return jdRequire;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database column SF_ENTRUST.JD_REQUIRE
   * @param jdRequire  the value for SF_ENTRUST.JD_REQUIRE
   * @mbggenerated  Wed Jan 07 11:38:37 CST 2015
   */
  public void setJdRequire(String jdRequire) {
    this.jdRequire = jdRequire;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database column SF_ENTRUST.JD_COMPANY
   * @return  the value of SF_ENTRUST.JD_COMPANY
   * @mbggenerated  Wed Jan 07 11:38:37 CST 2015
   */
  public String getJdCompany() {
    return jdCompany;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database column SF_ENTRUST.JD_COMPANY
   * @param jdCompany  the value for SF_ENTRUST.JD_COMPANY
   * @mbggenerated  Wed Jan 07 11:38:37 CST 2015
   */
  public void setJdCompany(String jdCompany) {
    this.jdCompany = jdCompany;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database column SF_ENTRUST.REMARK
   * @return  the value of SF_ENTRUST.REMARK
   * @mbggenerated  Wed Jan 07 11:38:37 CST 2015
   */
  public String getRemark() {
    return remark;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database column SF_ENTRUST.REMARK
   * @param remark  the value for SF_ENTRUST.REMARK
   * @mbggenerated  Wed Jan 07 11:38:37 CST 2015
   */
  public void setRemark(String remark) {
    this.remark = remark;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database column SF_ENTRUST.IS_CXJD
   * @return  the value of SF_ENTRUST.IS_CXJD
   * @mbggenerated  Wed Jan 07 11:38:37 CST 2015
   */
  public String getIsCxjd() {
    return isCxjd;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database column SF_ENTRUST.IS_CXJD
   * @param isCxjd  the value for SF_ENTRUST.IS_CXJD
   * @mbggenerated  Wed Jan 07 11:38:37 CST 2015
   */
  public void setIsCxjd(String isCxjd) {
    this.isCxjd = isCxjd;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database column SF_ENTRUST.WT_ID_PARENT
   * @return  the value of SF_ENTRUST.WT_ID_PARENT
   * @mbggenerated  Wed Jan 07 11:38:37 CST 2015
   */
  public BigDecimal getWtIdParent() {
    if (oldEntrust != null) {
      return oldEntrust.getEntrustId();
    }
    return wtIdParent;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database column SF_ENTRUST.WT_ID_PARENT
   * @param wtIdParent  the value for SF_ENTRUST.WT_ID_PARENT
   * @mbggenerated  Wed Jan 07 11:38:37 CST 2015
   */
  public void setWtIdParent(BigDecimal wtIdParent) {
    this.wtIdParent = wtIdParent;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database column SF_ENTRUST.BRIEF
   * @return  the value of SF_ENTRUST.BRIEF
   * @mbggenerated  Wed Jan 07 11:38:37 CST 2015
   */
  public String getBrief() {
    return brief;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database column SF_ENTRUST.BRIEF
   * @param brief  the value for SF_ENTRUST.BRIEF
   * @mbggenerated  Wed Jan 07 11:38:37 CST 2015
   */
  public void setBrief(String brief) {
    this.brief = brief;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database column SF_ENTRUST.INPUTOR
   * @return  the value of SF_ENTRUST.INPUTOR
   * @mbggenerated  Wed Jan 07 11:38:37 CST 2015
   */
  public String getInputor() {
    return inputor;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database column SF_ENTRUST.INPUTOR
   * @param inputor  the value for SF_ENTRUST.INPUTOR
   * @mbggenerated  Wed Jan 07 11:38:37 CST 2015
   */
  public void setInputor(String inputor) {
    this.inputor = inputor;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database column SF_ENTRUST.INPUT_DATE
   * @return  the value of SF_ENTRUST.INPUT_DATE
   * @mbggenerated  Wed Jan 07 11:38:37 CST 2015
   */
  public Date getInputDate() {
    return inputDate;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database column SF_ENTRUST.INPUT_DATE
   * @param inputDate  the value for SF_ENTRUST.INPUT_DATE
   * @mbggenerated  Wed Jan 07 11:38:37 CST 2015
   */
  public void setInputDate(Date inputDate) {
    this.inputDate = inputDate;

  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database column SF_ENTRUST.ACCEPTOR
   * @return  the value of SF_ENTRUST.ACCEPTOR
   * @mbggenerated  Wed Jan 07 11:38:37 CST 2015
   */
  public String getAcceptor() {
    return acceptor;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database column SF_ENTRUST.ACCEPTOR
   * @param acceptor  the value for SF_ENTRUST.ACCEPTOR
   * @mbggenerated  Wed Jan 07 11:38:37 CST 2015
   */
  public void setAcceptor(String acceptor) {
    this.acceptor = acceptor;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database column SF_ENTRUST.ACCEPT_DATE
   * @return  the value of SF_ENTRUST.ACCEPT_DATE
   * @mbggenerated  Wed Jan 07 11:38:37 CST 2015
   */
  public Date getAcceptDate() {
    return acceptDate;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database column SF_ENTRUST.ACCEPT_DATE
   * @param acceptDate  the value for SF_ENTRUST.ACCEPT_DATE
   * @mbggenerated  Wed Jan 07 11:38:37 CST 2015
   */
  public void setAcceptDate(Date acceptDate) {
    this.acceptDate = acceptDate;

  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database column SF_ENTRUST.IS_ACCEPT
   * @return  the value of SF_ENTRUST.IS_ACCEPT
   * @mbggenerated  Wed Jan 07 11:38:37 CST 2015
   */
  public String getIsAccept() {
    return isAccept;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database column SF_ENTRUST.IS_ACCEPT
   * @param isAccept  the value for SF_ENTRUST.IS_ACCEPT
   * @mbggenerated  Wed Jan 07 11:38:37 CST 2015
   */
  public void setIsAccept(String isAccept) {
    this.isAccept = isAccept;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database column SF_ENTRUST.NOT_ACCEPT_REASON
   * @return  the value of SF_ENTRUST.NOT_ACCEPT_REASON
   * @mbggenerated  Wed Jan 07 11:38:37 CST 2015
   */
  public String getNotAcceptReason() {
    return notAcceptReason;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database column SF_ENTRUST.NOT_ACCEPT_REASON
   * @param notAcceptReason  the value for SF_ENTRUST.NOT_ACCEPT_REASON
   * @mbggenerated  Wed Jan 07 11:38:37 CST 2015
   */
  public void setNotAcceptReason(String notAcceptReason) {
    this.notAcceptReason = notAcceptReason;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database column SF_ENTRUST.JD_TARGET_ID
   * @return  the value of SF_ENTRUST.JD_TARGET_ID
   * @mbggenerated  Wed Jan 07 11:38:37 CST 2015
   */
  public BigDecimal getJdTargetId() {
    return getJdTarget().getJdTargetId();
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database column SF_ENTRUST.JD_TARGET_ID
   * @param jdTargetId  the value for SF_ENTRUST.JD_TARGET_ID
   * @mbggenerated  Wed Jan 07 11:38:37 CST 2015
   */
  public void setJdTargetId(BigDecimal jdTargetId) {
    this.jdTargetId = jdTargetId;
  }

  public List getMaterials() {
    return materials;
  }

  public void setMaterials(List materials) {
    this.materials = materials;
  }

  public String getAcceptorName() {
    return EmpMeta.getEmpName(getAcceptor());
  }

  public void setAcceptorName(String acceptorName) {
    this.acceptorName = acceptorName;
  }

  public String getJdFzrName() {
    return EmpMeta.getEmpName(getJdFzr());
  }

  public void setJdFzrName(String jdFzrName) {
    this.jdFzrName = jdFzrName;
  }

  public SfEntrust getOldEntrust() {
    return oldEntrust;
  }

  public void setOldEntrust(SfEntrust oldEntrust) {
    this.oldEntrust = oldEntrust;
  }

  public String getWtCodeParent() {
    if (oldEntrust != null)
      return oldEntrust.getCode();
    return wtCodeParent;
  }

  public void setWtCodeParent(String wtCodeParent) {
    this.wtCodeParent = wtCodeParent;
  }

  public String getSjrAddress() {
    return sjrAddress;
  }

  public void setSjrAddress(String sjrAddress) {
    this.sjrAddress = sjrAddress;
  }

  public List getXysxLst() {
    return xysxLst;
  }

  public void setXysxLst(List xysxLst) {
    this.xysxLst = xysxLst;
  }

  public BigDecimal getJdCharge() {
    return jdCharge;
  }

  public void setJdCharge(BigDecimal jdCharge) {
    this.jdCharge = jdCharge;
  }

  public List getJdChargeDetaillst() {
    return jdChargeDetaillst;
  }

  public void setJdChargeDetaillst(List jdChargeDetaillst) {
    this.jdChargeDetaillst = jdChargeDetaillst;
  }

  public String getJdTargetName() {
    return getJdTarget().getName();
  }

  public void setJdTargetName(String jdTargetName) {
    this.jdTargetName = jdTargetName;
  }
}