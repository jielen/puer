package com.ufgov.zc.common.zc.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ZcDdcgHt extends ZcBaseBill implements Serializable {

  private String zcHtCode;

  private String zcHtName;

  private String zcReqCode;

  private String zcMakeCode;

  private String zcMakeName;

  private Date zcSgnDate;

  private String zcSuName;

  private String zcConText;

  private String zcConTextBlobid;

  private String zcHtStatus;

  private String zcZgCsCode;

  private String zcSuCode;

  private String zcSuTel;

  private String zcSuLinkman;

  private String zcZgCsName;

  private String zcMemo;

  private List itemList;

  private String zcPifuCgfs;

  private String zcBidCode;

  private String zcBidContent;

  private String tbylbFileId;//供应商上传的报价一览表的id

  private String tbylbFileName;

  private String moldName;

  public List getItemList() {
    return itemList;
  }

  public void setItemList(List itemList) {
    this.itemList = itemList;
  }

  public String getZcHtCode() {
    return zcHtCode;
  }

  public void setZcHtCode(String zcHtCode) {
    this.zcHtCode = zcHtCode;
  }

  public String getZcHtName() {
    return zcHtName;
  }

  public void setZcHtName(String zcHtName) {
    this.zcHtName = zcHtName;
  }

  public String getZcReqCode() {
    return zcReqCode;
  }

  public void setZcReqCode(String zcReqCode) {
    this.zcReqCode = zcReqCode;
  }

  public String getZcMakeCode() {
    return zcMakeCode;
  }

  public void setZcMakeCode(String zcMakeCode) {
    this.zcMakeCode = zcMakeCode;
  }

  /**
   * @return the zcMakeName
   */
  public String getZcMakeName() {
    return zcMakeName;
  }

  /**
   * @param zcMakeName the zcMakeName to set
   */
  public void setZcMakeName(String zcMakeName) {
    this.zcMakeName = zcMakeName;
  }

  public String getCoCode() {
    return coCode;
  }

  public void setCoCode(String coCode) {
    this.coCode = coCode;
  }

  public Date getZcSgnDate() {
    return zcSgnDate;
  }

  public void setZcSgnDate(Date zcSgnDate) {
    this.zcSgnDate = zcSgnDate;
  }

  public String getZcSuName() {
    return zcSuName;
  }

  public void setZcSuName(String zcSuName) {
    this.zcSuName = zcSuName;
  }

  public String getZcConText() {
    return zcConText;
  }

  public void setZcConText(String zcConText) {
    this.zcConText = zcConText;
  }

  public String getZcConTextBlobid() {
    return zcConTextBlobid;
  }

  public void setZcConTextBlobid(String zcConTextBlobid) {
    this.zcConTextBlobid = zcConTextBlobid;
  }

  public String getZcHtStatus() {
    return zcHtStatus;
  }

  public void setZcHtStatus(String zcHtStatus) {
    this.zcHtStatus = zcHtStatus;
  }

  public String getZcZgCsCode() {
    return zcZgCsCode;
  }

  public void setZcZgCsCode(String zcZgCsCode) {
    this.zcZgCsCode = zcZgCsCode;
  }

  public String getExecutor() {
    return executor;
  }

  public void setExecutor(String executor) {
    this.executor = executor;
  }

  public Date getExecuteDate() {
    return executeDate;
  }

  public void setExecuteDate(Date executeDate) {
    this.executeDate = executeDate;
  }

  public String getZcSuCode() {
    return zcSuCode;
  }

  public void setZcSuCode(String zcSuCode) {
    this.zcSuCode = zcSuCode;
  }

  public String getZcSuTel() {
    return zcSuTel;
  }

  public void setZcSuTel(String zcSuTel) {
    this.zcSuTel = zcSuTel;
  }

  public String getZcSuLinkman() {
    return zcSuLinkman;
  }

  public void setZcSuLinkman(String zcSuLinkman) {
    this.zcSuLinkman = zcSuLinkman;
  }

  public String getZcZgCsName() {
    return zcZgCsName;
  }

  public void setZcZgCsName(String zcZgCsName) {
    this.zcZgCsName = zcZgCsName;
  }

  public String getZcMemo() {
    return zcMemo;
  }

  public void setZcMemo(String zcMemo) {
    this.zcMemo = zcMemo;
  }

  public String getAgency() {
    return agency;
  }

  public void setAgency(String agency) {
    this.agency = agency;
  }

  public String getOrgCode() {
    return orgCode;
  }

  public void setOrgCode(String orgCode) {
    this.orgCode = orgCode;
  }

  /**
   * @return the zcPifuCgfs
   */
  public String getZcPifuCgfs() {
    return zcPifuCgfs;
  }

  /**
   * @param zcPifuCgfs the zcPifuCgfs to set
   */
  public void setZcPifuCgfs(String zcPifuCgfs) {
    this.zcPifuCgfs = zcPifuCgfs;
  }

  /**
   * @return the zcBidCode
   */
  public String getZcBidCode() {
    return zcBidCode;
  }

  /**
   * @param zcBidCode the zcBidCode to set
   */
  public void setZcBidCode(String zcBidCode) {
    this.zcBidCode = zcBidCode;
  }

  /**
   * @return the zcBidContent
   */
  public String getZcBidContent() {
    return zcBidContent;
  }

  /**
   * @param zcBidContent the zcBidContent to set
   */
  public void setZcBidContent(String zcBidContent) {
    this.zcBidContent = zcBidContent;
  }

  /**
   * @return the tbylbFileId
   */
  public String getTbylbFileId() {
    return tbylbFileId;
  }

  /**
   * @param tbylbFileId the tbylbFileId to set
   */
  public void setTbylbFileId(String tbylbFileId) {
    this.tbylbFileId = tbylbFileId;
  }

  /**
   * @return the tbylbFileName
   */
  public String getTbylbFileName() {
    return tbylbFileName;
  }

  /**
   * @param tbylbFileName the tbylbFileName to set
   */
  public void setTbylbFileName(String tbylbFileName) {
    this.tbylbFileName = tbylbFileName;
  }

  /**
   * @return the moldName
   */
  public String getMoldName() {
    return moldName;
  }

  /**
   * @param moldName the moldName to set
   */
  public void setMoldName(String moldName) {
    this.moldName = moldName;
  }

}