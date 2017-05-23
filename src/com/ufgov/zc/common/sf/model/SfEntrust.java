package com.ufgov.zc.common.sf.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.ufgov.zc.common.util.EmpMeta;
import com.ufgov.zc.common.zc.model.ZcBaseBill;

public class SfEntrust extends ZcBaseBill {
  /**
   * 草稿
   */
  public static final String STATUS_DRAFT ="0";
  /**
   * 核准委托信息
   */
  public static final String STATUS_HE_ZHUN ="2";
  /**
   * 业务科室确认
   */
  public static final String STATUS_QUE_REN ="3";
  /**
   * 接收检材
   */
  public static final String STATUS_JIE_SHOU_JIANCAI ="4";
  /**
   * 业务科室受理
   */
  public static final String STATUS_KE_SHI_SHOULI ="5";
  /**
   * 鉴定完成
   */
  public static final String STATUS_COMPLETE ="complete";
  /**
   * 鉴定中
   */
  public static final String STATUS_DOING ="doing";
  /**
   * 终审
   */
  public static final String STATUS_EXEC ="exec";
  /**
   * 鉴定逾期
   */
  public static final String STATUS_EXPIRE ="expire";
  /**
   * 鉴定暂停
   */
  public static final String STATUS_PAUSE ="pause";
  /**
   * 鉴定终止
   */
  public static final String STATUS_STOP ="stop";
  /**
   * 鉴定延期
   */
  public static final String STATUS_DELAY ="delay";
  /**
   * 不受理
   */
  public static final String STATUS_UNACCEPT ="unaccept";
  /**
   * 审核退回
   */
  public static final String STATUS_UNTREAT ="untreat";
  /**
   * 转送其他鉴定中心
   */
  public static final String STATUS_ZHUANSONG ="zhuansong";
  /**
   * 鉴定退回不做了，退回委托方
   */
  public static final String STATUS_BACK ="back";
  
  

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

	public static final String SF_VS_ENTRUST_URGENT_LEVEL = "SF_VS_ENTRUST_URGENT_LEVEL";
	
	public static final String SF_VS_ZHENGJIAN = "SF_VS_ZHENGJIAN";
	 
	public static final String SF_VS_ENTRUST_URGENT_LEVEL_normal = "1";//普通

	public static final String SF_VS_ENTRUST_URGENT_LEVEL_jinji = "2";//紧急

	public static final String SF_VS_ENTRUST_URGENT_LEVEL_teji = "3";//特急

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

	public static final String COL_JD_ASSISTOR = "SF_ENTRUST_JD_ASSISTOR"; // 鉴定协助人

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

	public static final String COL_SJR2 = "SF_ENTRUST_SJR2"; // 送检人

	public static final String COL_SJR2_TEL = "SF_ENTRUST_SJR2_TEL"; // 送检人电话

	public static final String COL_SJR2_ZJ_CODE = "SF_ENTRUST_SJR2_ZJ_CODE"; // 送检人证件号码

	public static final String COL_SJR2_ZJ_TYPE = "SF_ENTRUST_SJR2_ZJ_TYPE"; // 送检人证件类型

	public static final String COL_SJR_ADDRESS = "SF_ENTRUST_SJR_ADDRESS"; // 送检人地址
	
	public static final String COL_SJR_ZIP = "SF_ENTRUST_SJR_ZIP"; // 送检人邮编
	
	public static final String COL_SJR_FAX = "SF_ENTRUST_SJR_FAX"; // 送检人传真

	public static final String COL_STATUS = "SF_ENTRUST_STATUS"; // 状态

	public static final String COL_WT_DATE = "SF_ENTRUST_WT_DATE"; // 委托时间

	public static final String COL_WT_ID_PARENT = "SF_ENTRUST_WT_ID_PARENT"; // 上次鉴定委托ID

	public static final String COL_ENTRUSTOR_NAME = "SF_ENTRUST_ENTRUSTOR_NAME";// 委托方

	public static final String COL_MAJOR_NAME = "SF_ENTRUST_MAJOR_NAME";// 鉴定专业

	public static final String COL_JD_DOC_SEND_TYPE = "SF_ENTRUST_JD_DOC_SEND_TYPE";// 鉴定文书发送方式

	public static final String COL_JD_DOC_SEND_TYPE_FZ = "SF_ENTRUST_JD_DOC_SEND_TYPE_FZ";// 鉴定文书发送方式附注

	public static final String COL_JD_CHARGE = "SF_ENTRUST_JD_CHARGE";// 鉴定费用
	
	public static final String COL_BAR_CODE="SF_ENTRUST_BAR_CODE"; // 条码
	public static final String COL_URGENT_LEVEL="SF_ENTRUST_URGENT_LEVEL"; // 紧急程度
	public static final String COL_EXPECTED_TIME="SF_ENTRUST_EXPECTED_TIME"; // 预计耗时(天)
	public static final String COL_COMPLETE_TIME="SF_ENTRUST_COMPLETE_TIME"; // 完成日期

	public static final String COL_ACCEPT_CODE="SF_ENTRUST_ACCEPT_CODE"; // 受理编号
	public static final String COL_ANJIAN_CODE="SF_ENTRUST_ANJIAN_CODE"; // 案件编号
  public static final String COL_CO_CODE="SF_ENTRUST_CO_CODE"; // 鉴定中心

	public static final String BKMK_MATERIALS = "BKMK_MATERIALS";// 检材样本

  public static final String BKMK_MATERIALS_SIMPLE = "BKMK_MATERIALS_SIMPLE";// 检材样本简版
  
	public static final String BKMK_SF_ENTRUST_JD_FHR_ZSBH = "SF_ENTRUST_JD_FHR_ZSBH";// 鉴定复核人证书编号
	public static final String BKMK_SF_ENTRUST_JD_FZR_ZSBH = "SF_ENTRUST_JD_FZR_ZSBH";// 鉴定负责人证书编号
	
  public static final String BKMK_SF_ENTRUST_JD_FHR_ZC = "SF_ENTRUST_JD_FHR_ZC";// 鉴定复核人职称
  public static final String BKMK_SF_ENTRUST_JD_FZR_ZC = "SF_ENTRUST_JD_FZR_ZC";// 鉴定负责人职称
	
//鉴定文书号
  private String reportCode;
  //文书领取人
  private String recievor;
  //文书领取人电话
  private String recievorTel;
  //文书领取时间
  private Date sendDate;

  private String acceptCode;
	
	private String anjianCode;
	
	private String inputorName;

	private String acceptorName;

	private String jdFzrName;

	private String jdFhr;

	private String jdFhrName;

	private String jdAssistor;

	private String jdAssistorName;

	private String sjrAddress;
	
	private String sjrZip;

	private String jdTargetName;

	private String urgentLevel;

	private BigDecimal expectedTime;

	private Date completeTime;
	
	private String barCode;

	/**
	 * 状态变更记录 SfEntrustManage
	 */
	private List manageLst = new ArrayList();

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
	 * 鉴定机构，系统支持多家鉴定机构
	 */
	private SfJdjg jdjg = new SfJdjg();

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
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column SF_ENTRUST.ENTRUST_ID
	 * 
	 * @mbggenerated Wed Jan 07 11:38:37 CST 2015
	 */
	private BigDecimal entrustId;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column SF_ENTRUST.CODE
	 * 
	 * @mbggenerated Wed Jan 07 11:38:37 CST 2015
	 */
	private String code;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column SF_ENTRUST.NAME
	 * 
	 * @mbggenerated Wed Jan 07 11:38:37 CST 2015
	 */
	private String name;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column SF_ENTRUST.STATUS
	 * 
	 * @mbggenerated Wed Jan 07 11:38:37 CST 2015
	 */
	private String status;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column SF_ENTRUST.ENTRUSTOR_ID
	 * 
	 * @mbggenerated Wed Jan 07 11:38:37 CST 2015
	 */
	private BigDecimal entrustorId;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column SF_ENTRUST.WT_DATE
	 * 
	 * @mbggenerated Wed Jan 07 11:38:37 CST 2015
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
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column SF_ENTRUST.SJR
	 * 
	 * @mbggenerated Wed Jan 07 11:38:37 CST 2015
	 */
	private String sjr;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column SF_ENTRUST.SJR_TEL
	 * 
	 * @mbggenerated Wed Jan 07 11:38:37 CST 2015
	 */
	private String sjrTel;
	
	private String sjrFax;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column SF_ENTRUST.SJR_ZJ_TYPE
	 * 
	 * @mbggenerated Wed Jan 07 11:38:37 CST 2015
	 */
	private String sjrZjType;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column SF_ENTRUST.SJR_ZJ_CODE
	 * 
	 * @mbggenerated Wed Jan 07 11:38:37 CST 2015
	 */
	private String sjrZjCode
	;
	private String sjr2;

	
	private String sjr2Tel;

	 
	private String sjr2ZjType;

	 
	private String sjr2ZjCode;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column SF_ENTRUST.MAJOR_CODE
	 * 
	 * @mbggenerated Wed Jan 07 11:38:37 CST 2015
	 */
	private String majorCode;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column SF_ENTRUST.JD_ORG
	 * 
	 * @mbggenerated Wed Jan 07 11:38:37 CST 2015
	 */
	private String jdOrg;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column SF_ENTRUST.JD_FZR
	 * 
	 * @mbggenerated Wed Jan 07 11:38:37 CST 2015
	 */
	private String jdFzr;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column SF_ENTRUST.JD_HISTORY
	 * 
	 * @mbggenerated Wed Jan 07 11:38:37 CST 2015
	 */
	private String jdHistory;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column SF_ENTRUST.JD_REQUIRE
	 * 
	 * @mbggenerated Wed Jan 07 11:38:37 CST 2015
	 */
	private String jdRequire;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column SF_ENTRUST.JD_COMPANY
	 * 
	 * @mbggenerated Wed Jan 07 11:38:37 CST 2015
	 */
	private String jdCompany;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column SF_ENTRUST.REMARK
	 * 
	 * @mbggenerated Wed Jan 07 11:38:37 CST 2015
	 */
	private String remark;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column SF_ENTRUST.IS_CXJD
	 * 
	 * @mbggenerated Wed Jan 07 11:38:37 CST 2015
	 */
	private String isCxjd;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column SF_ENTRUST.WT_ID_PARENT
	 * 
	 * @mbggenerated Wed Jan 07 11:38:37 CST 2015
	 */
	private BigDecimal wtIdParent;

	private String wtCodeParent;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column SF_ENTRUST.BRIEF
	 * 
	 * @mbggenerated Wed Jan 07 11:38:37 CST 2015
	 */
	private String brief;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column SF_ENTRUST.INPUTOR
	 * 
	 * @mbggenerated Wed Jan 07 11:38:37 CST 2015
	 */
	private String inputor;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column SF_ENTRUST.INPUT_DATE
	 * 
	 * @mbggenerated Wed Jan 07 11:38:37 CST 2015
	 */
	private Date inputDate;

	private String inputDateStr;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column SF_ENTRUST.ACCEPTOR
	 * 
	 * @mbggenerated Wed Jan 07 11:38:37 CST 2015
	 */
	private String acceptor;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column SF_ENTRUST.ACCEPT_DATE
	 * 
	 * @mbggenerated Wed Jan 07 11:38:37 CST 2015
	 */
	private Date acceptDate;

	private String acceptDateStr;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column SF_ENTRUST.IS_ACCEPT
	 * 
	 * @mbggenerated Wed Jan 07 11:38:37 CST 2015
	 */
	private String isAccept;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column SF_ENTRUST.NOT_ACCEPT_REASON
	 * 
	 * @mbggenerated Wed Jan 07 11:38:37 CST 2015
	 */
	private String notAcceptReason;

	 
	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column SF_ENTRUST.ENTRUST_ID
	 * 
	 * @return the value of SF_ENTRUST.ENTRUST_ID
	 * @mbggenerated Wed Jan 07 11:38:37 CST 2015
	 */
	public BigDecimal getEntrustId() {
		return entrustId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column SF_ENTRUST.ENTRUST_ID
	 * 
	 * @param entrustId
	 *            the value for SF_ENTRUST.ENTRUST_ID
	 * @mbggenerated Wed Jan 07 11:38:37 CST 2015
	 */
	public void setEntrustId(BigDecimal entrustId) {
		this.entrustId = entrustId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column SF_ENTRUST.CODE
	 * 
	 * @return the value of SF_ENTRUST.CODE
	 * @mbggenerated Wed Jan 07 11:38:37 CST 2015
	 */
	public String getCode() {
		return code;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column SF_ENTRUST.CODE
	 * 
	 * @param code
	 *            the value for SF_ENTRUST.CODE
	 * @mbggenerated Wed Jan 07 11:38:37 CST 2015
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column SF_ENTRUST.NAME
	 * 
	 * @return the value of SF_ENTRUST.NAME
	 * @mbggenerated Wed Jan 07 11:38:37 CST 2015
	 */
	public String getName() {
		return name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column SF_ENTRUST.NAME
	 * 
	 * @param name
	 *            the value for SF_ENTRUST.NAME
	 * @mbggenerated Wed Jan 07 11:38:37 CST 2015
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column SF_ENTRUST.STATUS
	 * 
	 * @return the value of SF_ENTRUST.STATUS
	 * @mbggenerated Wed Jan 07 11:38:37 CST 2015
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column SF_ENTRUST.STATUS
	 * 
	 * @param status
	 *            the value for SF_ENTRUST.STATUS
	 * @mbggenerated Wed Jan 07 11:38:37 CST 2015
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column SF_ENTRUST.ENTRUSTOR_ID
	 * 
	 * @return the value of SF_ENTRUST.ENTRUSTOR_ID
	 * @mbggenerated Wed Jan 07 11:38:37 CST 2015
	 */
	public BigDecimal getEntrustorId() {
		return getEntrustor().getEntrustorId();
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column SF_ENTRUST.ENTRUSTOR_ID
	 * 
	 * @param entrustorId
	 *            the value for SF_ENTRUST.ENTRUSTOR_ID
	 * @mbggenerated Wed Jan 07 11:38:37 CST 2015
	 */
	public void setEntrustorId(BigDecimal entrustorId) {
		this.entrustorId = entrustorId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column SF_ENTRUST.WT_DATE
	 * 
	 * @return the value of SF_ENTRUST.WT_DATE
	 * @mbggenerated Wed Jan 07 11:38:37 CST 2015
	 */
	public Date getWtDate() {
		return wtDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column SF_ENTRUST.WT_DATE
	 * 
	 * @param wtDate
	 *            the value for SF_ENTRUST.WT_DATE
	 * @mbggenerated Wed Jan 07 11:38:37 CST 2015
	 */
	public void setWtDate(Date wtDate) {
		this.wtDate = wtDate;

	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column SF_ENTRUST.SJR
	 * 
	 * @return the value of SF_ENTRUST.SJR
	 * @mbggenerated Wed Jan 07 11:38:37 CST 2015
	 */
	public String getSjr() {
		return sjr;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column SF_ENTRUST.SJR
	 * 
	 * @param sjr
	 *            the value for SF_ENTRUST.SJR
	 * @mbggenerated Wed Jan 07 11:38:37 CST 2015
	 */
	public void setSjr(String sjr) {
		this.sjr = sjr;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column SF_ENTRUST.SJR_TEL
	 * 
	 * @return the value of SF_ENTRUST.SJR_TEL
	 * @mbggenerated Wed Jan 07 11:38:37 CST 2015
	 */
	public String getSjrTel() {
		return sjrTel;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column SF_ENTRUST.SJR_TEL
	 * 
	 * @param sjrTel
	 *            the value for SF_ENTRUST.SJR_TEL
	 * @mbggenerated Wed Jan 07 11:38:37 CST 2015
	 */
	public void setSjrTel(String sjrTel) {
		this.sjrTel = sjrTel;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column SF_ENTRUST.SJR_ZJ_TYPE
	 * 
	 * @return the value of SF_ENTRUST.SJR_ZJ_TYPE
	 * @mbggenerated Wed Jan 07 11:38:37 CST 2015
	 */
	public String getSjrZjType() {
		return sjrZjType;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column SF_ENTRUST.SJR_ZJ_TYPE
	 * 
	 * @param sjrZjType
	 *            the value for SF_ENTRUST.SJR_ZJ_TYPE
	 * @mbggenerated Wed Jan 07 11:38:37 CST 2015
	 */
	public void setSjrZjType(String sjrZjType) {
		this.sjrZjType = sjrZjType;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column SF_ENTRUST.SJR_ZJ_CODE
	 * 
	 * @return the value of SF_ENTRUST.SJR_ZJ_CODE
	 * @mbggenerated Wed Jan 07 11:38:37 CST 2015
	 */
	public String getSjrZjCode() {
		return sjrZjCode;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column SF_ENTRUST.SJR_ZJ_CODE
	 * 
	 * @param sjrZjCode
	 *            the value for SF_ENTRUST.SJR_ZJ_CODE
	 * @mbggenerated Wed Jan 07 11:38:37 CST 2015
	 */
	public void setSjrZjCode(String sjrZjCode) {
		this.sjrZjCode = sjrZjCode;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column SF_ENTRUST.MAJOR_CODE
	 * 
	 * @return the value of SF_ENTRUST.MAJOR_CODE
	 * @mbggenerated Wed Jan 07 11:38:37 CST 2015
	 */
	public String getMajorCode() {
		if (getMajor() != null)
			return getMajor().getMajorCode();
		return majorCode;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column SF_ENTRUST.MAJOR_CODE
	 * 
	 * @param majorCode
	 *            the value for SF_ENTRUST.MAJOR_CODE
	 * @mbggenerated Wed Jan 07 11:38:37 CST 2015
	 */
	public void setMajorCode(String majorCode) {
		this.majorCode = majorCode;
		SfMajor major = new SfMajor();
		major.setMajorCode(majorCode);
		setMajor(major);
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column SF_ENTRUST.JD_ORG
	 * 
	 * @return the value of SF_ENTRUST.JD_ORG
	 * @mbggenerated Wed Jan 07 11:38:37 CST 2015
	 */
	public String getJdOrg() {
		return jdOrg;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column SF_ENTRUST.JD_ORG
	 * 
	 * @param jdOrg
	 *            the value for SF_ENTRUST.JD_ORG
	 * @mbggenerated Wed Jan 07 11:38:37 CST 2015
	 */
	public void setJdOrg(String jdOrg) {
		this.jdOrg = jdOrg;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column SF_ENTRUST.JD_FZR
	 * 
	 * @return the value of SF_ENTRUST.JD_FZR
	 * @mbggenerated Wed Jan 07 11:38:37 CST 2015
	 */
	public String getJdFzr() {
		return jdFzr;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column SF_ENTRUST.JD_FZR
	 * 
	 * @param jdFzr
	 *            the value for SF_ENTRUST.JD_FZR
	 * @mbggenerated Wed Jan 07 11:38:37 CST 2015
	 */
	public void setJdFzr(String jdFzr) {
		this.jdFzr = jdFzr;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column SF_ENTRUST.JD_HISTORY
	 * 
	 * @return the value of SF_ENTRUST.JD_HISTORY
	 * @mbggenerated Wed Jan 07 11:38:37 CST 2015
	 */
	public String getJdHistory() {
		return jdHistory;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column SF_ENTRUST.JD_HISTORY
	 * 
	 * @param jdHistory
	 *            the value for SF_ENTRUST.JD_HISTORY
	 * @mbggenerated Wed Jan 07 11:38:37 CST 2015
	 */
	public void setJdHistory(String jdHistory) {
		this.jdHistory = jdHistory;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column SF_ENTRUST.JD_REQUIRE
	 * 
	 * @return the value of SF_ENTRUST.JD_REQUIRE
	 * @mbggenerated Wed Jan 07 11:38:37 CST 2015
	 */
	public String getJdRequire() {
		return jdRequire;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column SF_ENTRUST.JD_REQUIRE
	 * 
	 * @param jdRequire
	 *            the value for SF_ENTRUST.JD_REQUIRE
	 * @mbggenerated Wed Jan 07 11:38:37 CST 2015
	 */
	public void setJdRequire(String jdRequire) {
		this.jdRequire = jdRequire;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column SF_ENTRUST.JD_COMPANY
	 * 
	 * @return the value of SF_ENTRUST.JD_COMPANY
	 * @mbggenerated Wed Jan 07 11:38:37 CST 2015
	 */
	public String getJdCompany() {
		return jdCompany;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column SF_ENTRUST.JD_COMPANY
	 * 
	 * @param jdCompany
	 *            the value for SF_ENTRUST.JD_COMPANY
	 * @mbggenerated Wed Jan 07 11:38:37 CST 2015
	 */
	public void setJdCompany(String jdCompany) {
		this.jdCompany = jdCompany;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column SF_ENTRUST.REMARK
	 * 
	 * @return the value of SF_ENTRUST.REMARK
	 * @mbggenerated Wed Jan 07 11:38:37 CST 2015
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column SF_ENTRUST.REMARK
	 * 
	 * @param remark
	 *            the value for SF_ENTRUST.REMARK
	 * @mbggenerated Wed Jan 07 11:38:37 CST 2015
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column SF_ENTRUST.IS_CXJD
	 * 
	 * @return the value of SF_ENTRUST.IS_CXJD
	 * @mbggenerated Wed Jan 07 11:38:37 CST 2015
	 */
	public String getIsCxjd() {
		return isCxjd;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column SF_ENTRUST.IS_CXJD
	 * 
	 * @param isCxjd
	 *            the value for SF_ENTRUST.IS_CXJD
	 * @mbggenerated Wed Jan 07 11:38:37 CST 2015
	 */
	public void setIsCxjd(String isCxjd) {
		this.isCxjd = isCxjd;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column SF_ENTRUST.WT_ID_PARENT
	 * 
	 * @return the value of SF_ENTRUST.WT_ID_PARENT
	 * @mbggenerated Wed Jan 07 11:38:37 CST 2015
	 */
	public BigDecimal getWtIdParent() {
		if (oldEntrust != null) {
			return oldEntrust.getEntrustId();
		}
		return wtIdParent;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column SF_ENTRUST.WT_ID_PARENT
	 * 
	 * @param wtIdParent
	 *            the value for SF_ENTRUST.WT_ID_PARENT
	 * @mbggenerated Wed Jan 07 11:38:37 CST 2015
	 */
	public void setWtIdParent(BigDecimal wtIdParent) {
		this.wtIdParent = wtIdParent;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column SF_ENTRUST.BRIEF
	 * 
	 * @return the value of SF_ENTRUST.BRIEF
	 * @mbggenerated Wed Jan 07 11:38:37 CST 2015
	 */
	public String getBrief() {
		return brief;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column SF_ENTRUST.BRIEF
	 * 
	 * @param brief
	 *            the value for SF_ENTRUST.BRIEF
	 * @mbggenerated Wed Jan 07 11:38:37 CST 2015
	 */
	public void setBrief(String brief) {
		this.brief = brief;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column SF_ENTRUST.INPUTOR
	 * 
	 * @return the value of SF_ENTRUST.INPUTOR
	 * @mbggenerated Wed Jan 07 11:38:37 CST 2015
	 */
	public String getInputor() {
		return inputor;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column SF_ENTRUST.INPUTOR
	 * 
	 * @param inputor
	 *            the value for SF_ENTRUST.INPUTOR
	 * @mbggenerated Wed Jan 07 11:38:37 CST 2015
	 */
	public void setInputor(String inputor) {
		this.inputor = inputor;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column SF_ENTRUST.INPUT_DATE
	 * 
	 * @return the value of SF_ENTRUST.INPUT_DATE
	 * @mbggenerated Wed Jan 07 11:38:37 CST 2015
	 */
	public Date getInputDate() {
		return inputDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column SF_ENTRUST.INPUT_DATE
	 * 
	 * @param inputDate
	 *            the value for SF_ENTRUST.INPUT_DATE
	 * @mbggenerated Wed Jan 07 11:38:37 CST 2015
	 */
	public void setInputDate(Date inputDate) {
		this.inputDate = inputDate;

	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column SF_ENTRUST.ACCEPTOR
	 * 
	 * @return the value of SF_ENTRUST.ACCEPTOR
	 * @mbggenerated Wed Jan 07 11:38:37 CST 2015
	 */
	public String getAcceptor() {
		return acceptor;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column SF_ENTRUST.ACCEPTOR
	 * 
	 * @param acceptor
	 *            the value for SF_ENTRUST.ACCEPTOR
	 * @mbggenerated Wed Jan 07 11:38:37 CST 2015
	 */
	public void setAcceptor(String acceptor) {
		this.acceptor = acceptor;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column SF_ENTRUST.ACCEPT_DATE
	 * 
	 * @return the value of SF_ENTRUST.ACCEPT_DATE
	 * @mbggenerated Wed Jan 07 11:38:37 CST 2015
	 */
	public Date getAcceptDate() {
		return acceptDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column SF_ENTRUST.ACCEPT_DATE
	 * 
	 * @param acceptDate
	 *            the value for SF_ENTRUST.ACCEPT_DATE
	 * @mbggenerated Wed Jan 07 11:38:37 CST 2015
	 */
	public void setAcceptDate(Date acceptDate) {
		this.acceptDate = acceptDate;

	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column SF_ENTRUST.IS_ACCEPT
	 * 
	 * @return the value of SF_ENTRUST.IS_ACCEPT
	 * @mbggenerated Wed Jan 07 11:38:37 CST 2015
	 */
	public String getIsAccept() {
		return isAccept;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column SF_ENTRUST.IS_ACCEPT
	 * 
	 * @param isAccept
	 *            the value for SF_ENTRUST.IS_ACCEPT
	 * @mbggenerated Wed Jan 07 11:38:37 CST 2015
	 */
	public void setIsAccept(String isAccept) {
		this.isAccept = isAccept;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column SF_ENTRUST.NOT_ACCEPT_REASON
	 * 
	 * @return the value of SF_ENTRUST.NOT_ACCEPT_REASON
	 * @mbggenerated Wed Jan 07 11:38:37 CST 2015
	 */
	public String getNotAcceptReason() {
		return notAcceptReason;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column SF_ENTRUST.NOT_ACCEPT_REASON
	 * 
	 * @param notAcceptReason
	 *            the value for SF_ENTRUST.NOT_ACCEPT_REASON
	 * @mbggenerated Wed Jan 07 11:38:37 CST 2015
	 */
	public void setNotAcceptReason(String notAcceptReason) {
		this.notAcceptReason = notAcceptReason;
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

	public String getJdAssistor() {
		return jdAssistor;
	}

	public void setJdAssistor(String jdAssistor) {
		this.jdAssistor = jdAssistor;
	}

	public String getJdAssistorName() {
		return EmpMeta.getEmpName(getJdAssistor());
	}

	public void setJdAssistorName(String jdAssistorName) {
		this.jdAssistorName = jdAssistorName;
	}

	public SfJdjg getJdjg() {
		return jdjg;
	}

	public void setJdjg(SfJdjg jdjg) {
		this.jdjg = jdjg;
	}

	public String getUrgentLevel() {
		return urgentLevel;
	}

	public void setUrgentLevel(String urgentLevel) {
		this.urgentLevel = urgentLevel;
	}

	public BigDecimal getExpectedTime() {
		return expectedTime;
	}

	public void setExpectedTime(BigDecimal expectedTime) {
		this.expectedTime = expectedTime;
	}
	/**
	 * 预期完成日期
	 * @return
	 */
	public Date getExpetedCompleteDate(){ 
	  if(getAcceptDate()!=null){
	    Calendar rightNow = Calendar.getInstance();
	    rightNow.setTime(getAcceptDate());
	    int k=getExpectedTime()==null?0:getExpectedTime().intValue();
	    rightNow.add(Calendar.DAY_OF_YEAR,k);//
	    return rightNow.getTime();
	  }
	  return null;
	}
	/**
	 * 是否逾期
	 * @return
	 */
	public boolean isExpire(){
	  /*if(getExpetedCompleteDate()!=null){
	    Date sysDate=SfUtil.getSysDate();
	    if(sysDate.getTime()<getExecuteDate().getTime()){
	      return true;
	    }
	  }*/
	  return false;
	}
	public Date getCompleteTime() {
		return completeTime;
	}

	public void setCompleteTime(Date completeTime) {
		this.completeTime = completeTime;
	}

	public List getManageLst() {
		return manageLst;
	}

	public void setManageLst(List manageLst) {
		this.manageLst = manageLst;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}


	public String getSjr2() {
		return sjr2;
	}

	public void setSjr2(String sjr2) {
		this.sjr2 = sjr2;
	}

	public String getSjr2Tel() {
		return sjr2Tel;
	}

	public void setSjr2Tel(String sjr2Tel) {
		this.sjr2Tel = sjr2Tel;
	}

	public String getSjr2ZjType() {
		return sjr2ZjType;
	}

	public void setSjr2ZjType(String sjr2ZjType) {
		this.sjr2ZjType = sjr2ZjType;
	}

	public String getSjr2ZjCode() {
		return sjr2ZjCode;
	}

	public void setSjr2ZjCode(String sjr2ZjCode) {
		this.sjr2ZjCode = sjr2ZjCode;
	}

	public String getAcceptCode() {
		return acceptCode;
	}

	public void setAcceptCode(String acceptCode) {
		this.acceptCode = acceptCode;
	}

	public String getSjrZip() {
		return sjrZip;
	}

	public void setSjrZip(String sjrZip) {
		this.sjrZip = sjrZip;
	}

	public String getSjrFax() {
		return sjrFax;
	}

	public void setSjrFax(String sjrFax) {
		this.sjrFax = sjrFax;
	}

	public String getAnjianCode() {
		return anjianCode;
	}

	public void setAnjianCode(String anjianCode) {
		this.anjianCode = anjianCode;
	}

  
  public String getReportCode() {
    return reportCode;
  }

  public void setReportCode(String reportCode) {
    this.reportCode = reportCode;
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
}