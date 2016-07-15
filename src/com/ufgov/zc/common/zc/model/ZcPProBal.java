/**   
* @(#) project: zcxa
* @(#) file: ZcPProBal.java
* 
* Copyright 2010 UFGOV, Inc. All rights reserved.
* UFGOV PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
* 
*/
package com.ufgov.zc.common.zc.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
* @ClassName: ZcPProBal
* @Description: TCJLODO(这里用一句话描述这个类的作用)
* @date: 2010-8-2 下午02:41:46
* @version: V1.0 
* @since: 1.0
* @author: Administrator
* @modify: 
*/
public class ZcPProBal extends ZcBaseBill implements Serializable {
  /**
   * 
   */
  private static final long serialVersionUID = -4384738337392446915L;

  private String zcBalId;

  private String zcSuAccCode;

  private String zcSuAccName;

  private String zcSuBankCode;

  private String zcBalStatus;

  private BigDecimal zcBalSum;

  private String zcInputCode;

  private Date zcBalAppDate;

  private Date zcInputDate;

  private String zcInputName;

  private Date zcBalAutDate;

  private String ZcBalApp;

  private String zcBalAut;

  private String zcCoName;

  private String zcIsRit;

  private String zcBidCode;

  private String zcYsjgNa;

  private String zcMakeCode;

  private String zcMakeName;

  private String zcReqCode;

  private String zcSuCode;

  private String zcSuName;

  private String zcBalType;

  private String zcDiyuDatma;

  private String zcHtCode;

  private String zcHtName;

  private String zcSuTel;

  private String zcSuLinkMan;

  private String zcHangyeCtg;

  private String zcInvoiceCode;

  private String zcSkRen;

  private String zcZbName;

  private String zcZbCode;

  private String zcCgLeixing;

  private String zcReqLinkMan;

  private String zcReqTel;

  private String zcConText;

  private String zcConTextBlobId;

  private String zcImpFile;

  private String zcImpFileBlobId;

  private String zcIsImp;

  private String zcCoType;

  private String zcCoTrade;

  private String zcBiNo;

  private BigDecimal zcBiSum;

  private String outLayCode;

  private String payTypeCode;

  private String zcCoAccCode;

  private String zcCoAccName;

  private String zcCoBankCode;

  private String zcCoBankName;

  private String payOrder;

  private String isLastPay;
  //待支付金额
  private BigDecimal canPaySum;

  public String getIsLastPay() {
	return isLastPay;
  }

  public void setIsLastPay(String isLastPay) {
	this.isLastPay = isLastPay;
	}

	public String getZcBalApfNo() {
    return zcBalApfNo;
  }

  public void setZcBalApfNo(String zcBalApfNo) {
    this.zcBalApfNo = zcBalApfNo;
  }

  private String zcBalApfNo;

  public String getZcRemark() {
    return zcRemark;
  }

  public void setZcRemark(String zcRemark) {
    this.zcRemark = zcRemark;
  }

  private String zcRemark;

  private String zcBalFlag;

  public String getZcSuBankName() {
    return zcSuBankName;
  }

  public void setZcSuBankName(String zcSuBankName) {
    this.zcSuBankName = zcSuBankName;
  }

  private String zcSuBankName;

  public String getZcCoName() {
    return zcCoName;
  }

  public void setZcCoName(String zcCoName) {
    this.zcCoName = zcCoName;
  }

  public BigDecimal getZcMoneyBiSum() {
    return zcMoneyBiSum;
  }

  public void setZcMoneyBiSum(BigDecimal zcMoneyBiSum) {
    this.zcMoneyBiSum = zcMoneyBiSum;
  }

  private BigDecimal zcMoneyBiSum;

  public BigDecimal getZcHtNum() {
    return zcHtNum;
  }

  public void setZcHtNum(BigDecimal zcHtNum) {
    this.zcHtNum = zcHtNum;
  }

  private BigDecimal zcHtNum;

  public String getZcDiyuDaima() {
    return zcDiyuDaima;
  }

  public void setZcDiyuDaima(String zcDiyuDaima) {
    this.zcDiyuDaima = zcDiyuDaima;
  }

  private String zcDiyuDaima;

  public String getZcIsRin() {
    return zcIsRin;
  }

  public void setZcIsRin(String zcIsRin) {
    this.zcIsRin = zcIsRin;
  }

  private String zcIsRin;

  public String getZcBalId() {
    return zcBalId;
  }

  public void setZcBalId(String zcBalId) {
    this.zcBalId = zcBalId;
  }

  public String getZcSuAccCode() {
    return zcSuAccCode;
  }

  public void setZcSuAccCode(String zcSuAccCode) {
    this.zcSuAccCode = zcSuAccCode;
  }

  public String getZcSuBankCode() {
    return zcSuBankCode;
  }

  public void setZcSuBankCode(String zcSuBankCode) {
    this.zcSuBankCode = zcSuBankCode;
  }

  public String getZcBalStatus() {
    return zcBalStatus;
  }

  public void setZcBalStatus(String zcBalStatus) {
    this.zcBalStatus = zcBalStatus;
  }

  public BigDecimal getZcBalSum() {
    return zcBalSum;
  }

  public void setZcBalSum(BigDecimal zcBalSum) {
    this.zcBalSum = zcBalSum;
  }

  public String getZcInputCode() {
    return zcInputCode;
  }

  public void setZcInputCode(String zcInputCode) {
    this.zcInputCode = zcInputCode;
  }

  public Date getZcBalAppDate() {
    return zcBalAppDate;
  }

  public void setZcBalAppDate(Date zcBalAppDate) {
    this.zcBalAppDate = zcBalAppDate;
  }

  public Date getZcInputDate() {
    return zcInputDate;
  }

  public void setZcInputDate(Date zcInputDate) {
    this.zcInputDate = zcInputDate;
  }

  public Date getZcBalAutDate() {
    return zcBalAutDate;
  }

  public void setZcBalAutDate(Date zcBalAutDate) {
    this.zcBalAutDate = zcBalAutDate;
  }

  public String getZcBalApp() {
    return ZcBalApp;
  }

  public void setZcBalApp(String zcBalApp) {
    ZcBalApp = zcBalApp;
  }

  public String getZcBalAut() {
    return zcBalAut;
  }

  public void setZcBalAut(String zcBalAut) {
    this.zcBalAut = zcBalAut;
  }

  public String getZcBalFlag() {
    return zcBalFlag;
  }

  public void setZcBalFlag(String zcBalFlag) {
    this.zcBalFlag = zcBalFlag;
  }

  public String getZcIsRit() {
    return zcIsRit;
  }

  public void setZcIsRit(String zcIsRit) {
    this.zcIsRit = zcIsRit;
  }

  public String getZcBidCode() {
    return zcBidCode;
  }

  public void setZcBidCode(String zcBidCode) {
    this.zcBidCode = zcBidCode;
  }

  public String getZcYsjgNa() {
    return zcYsjgNa;
  }

  public void setZcYsjgNa(String zcYsjgNa) {
    this.zcYsjgNa = zcYsjgNa;
  }

  public String getZcMakeCode() {
    return zcMakeCode;
  }

  public void setZcMakeCode(String zcMakeCode) {
    this.zcMakeCode = zcMakeCode;
  }

  public String getZcReqCode() {
    return zcReqCode;
  }

  public void setZcReqCode(String zcReqCode) {
    this.zcReqCode = zcReqCode;
  }

  public String getZcSuCode() {
    return zcSuCode;
  }

  public void setZcSuCode(String zcSuCode) {
    this.zcSuCode = zcSuCode;
  }

  public String getZcSuName() {
    return zcSuName;
  }

  public void setZcSuName(String zcSuName) {
    this.zcSuName = zcSuName;
  }

  public String getZcBalType() {
    return zcBalType;
  }

  public void setZcBalType(String zcBalType) {
    this.zcBalType = zcBalType;
  }

  public String getZcDiyuDatma() {
    return zcDiyuDatma;
  }

  public void setZcDiyuDatma(String zcDiyuDatma) {
    this.zcDiyuDatma = zcDiyuDatma;
  }

  public String getZcHtCode() {
    return zcHtCode;
  }

  public void setZcHtCode(String zcHtCode) {
    this.zcHtCode = zcHtCode;
  }

  public String getZcSuTel() {
    return zcSuTel;
  }

  public void setZcSuTel(String zcSuTel) {
    this.zcSuTel = zcSuTel;
  }

  public String getZcSuLinkMan() {
    return zcSuLinkMan;
  }

  public void setZcSuLinkMan(String zcSuLinkMan) {
    this.zcSuLinkMan = zcSuLinkMan;
  }

  public String getZcHangyeCtg() {
    return zcHangyeCtg;
  }

  public void setZcHangyeCtg(String zcHangyeCtg) {
    this.zcHangyeCtg = zcHangyeCtg;
  }

  public String getZcInvoiceCode() {
    return zcInvoiceCode;
  }

  public void setZcInvoiceCode(String zcInvoiceCode) {
    this.zcInvoiceCode = zcInvoiceCode;
  }

  public String getZcSkRen() {
    return zcSkRen;
  }

  public void setZcSkRen(String zcSkRen) {
    this.zcSkRen = zcSkRen;
  }

  public String getZcZbName() {
    return zcZbName;
  }

  public void setZcZbName(String zcZbName) {
    this.zcZbName = zcZbName;
  }

  public String getZcZbCode() {
    return zcZbCode;
  }

  public void setZcZbCode(String zcZbCode) {
    this.zcZbCode = zcZbCode;
  }

  public String getZcCgLeixing() {
    return zcCgLeixing;
  }

  public void setZcCgLeixing(String zcCgLeixing) {
    this.zcCgLeixing = zcCgLeixing;
  }

  public String getZcReqLinkMan() {
    return zcReqLinkMan;
  }

  public void setZcReqLinkMan(String zcReqLinkMan) {
    this.zcReqLinkMan = zcReqLinkMan;
  }

  public String getZcReqTel() {
    return zcReqTel;
  }

  public void setZcReqTel(String zcReqTel) {
    this.zcReqTel = zcReqTel;
  }

  public String getZcConText() {
    return zcConText;
  }

  public void setZcConText(String zcConText) {
    this.zcConText = zcConText;
  }

  public String getZcConTextBlobId() {
    return zcConTextBlobId;
  }

  public void setZcConTextBlobId(String zcConTextBlobId) {
    this.zcConTextBlobId = zcConTextBlobId;
  }

  public String getZcImpFile() {
    return zcImpFile;
  }

  public void setZcImpFile(String zcImpFile) {
    this.zcImpFile = zcImpFile;
  }

  public String getZcImpFileBlobId() {
    return zcImpFileBlobId;
  }

  public void setZcImpFileBlobId(String zcImpFileBlobId) {
    this.zcImpFileBlobId = zcImpFileBlobId;
  }

  public String getZcIsImp() {
    return zcIsImp;
  }

  public void setZcIsImp(String zcIsImp) {
    this.zcIsImp = zcIsImp;
  }

  public String getZcCoType() {
    return zcCoType;
  }

  public void setZcCoType(String zcCoType) {
    this.zcCoType = zcCoType;
  }

  public String getZcCoTrade() {
    return zcCoTrade;
  }

  public void setZcCoTrade(String zcCoTrade) {
    this.zcCoTrade = zcCoTrade;
  }

  public String getZcBiNo() {
    return zcBiNo;
  }

  public void setZcBiNo(String zcBiNo) {
    this.zcBiNo = zcBiNo;
  }

  public BigDecimal getZcBiSum() {
    return zcBiSum;
  }

  public void setZcBiSum(BigDecimal zcBiSum) {
    this.zcBiSum = zcBiSum;
  }

  public String getOutLayCode() {
    return outLayCode;
  }

  public void setOutLayCode(String outLayCode) {
    this.outLayCode = outLayCode;
  }

  public String getPayTypeCode() {
    return payTypeCode;
  }

  public void setPayTypeCode(String payTypeCode) {
    this.payTypeCode = payTypeCode;
  }

  public List getBiList() {
    return biList;
  }

  public void setBiList(List biList) {
    this.biList = biList;
  }

  private List biList;

  private List returnBiList;

  public String getZcSuAccName() {
    return zcSuAccName;
  }

  public void setZcSuAccName(String zcSuAccName) {
    this.zcSuAccName = zcSuAccName;
  }

  public String getZcInputName() {
    return zcInputName;
  }

  public void setZcInputName(String zcInputName) {
    this.zcInputName = zcInputName;
  }

  public String getZcHtName() {
    return zcHtName;
  }

  public void setZcHtName(String zcHtName) {
    this.zcHtName = zcHtName;
  }

  public String getZcMakeName() {
    return zcMakeName;
  }

  public void setZcMakeName(String zcMakeName) {
    this.zcMakeName = zcMakeName;
  }

  /**
   * @return the zcCoAccCode
   */
  public String getZcCoAccCode() {
    return zcCoAccCode;
  }

  /**
   * @param zcCoAccCode the zcCoAccCode to set
   */
  public void setZcCoAccCode(String zcCoAccCode) {
    this.zcCoAccCode = zcCoAccCode;
  }

  /**
   * @return the zcCoAccName
   */
  public String getZcCoAccName() {
    return zcCoAccName;
  }

  /**
   * @param zcCoAccName the zcCoAccName to set
   */
  public void setZcCoAccName(String zcCoAccName) {
    this.zcCoAccName = zcCoAccName;
  }

  /**
   * @return the zcCoBankCode
   */
  public String getZcCoBankCode() {
    return zcCoBankCode;
  }

  /**
   * @param zcCoBankCode the zcCoBankCode to set
   */
  public void setZcCoBankCode(String zcCoBankCode) {
    this.zcCoBankCode = zcCoBankCode;
  }

  /**
   * @return the zcCoBankName
   */
  public String getZcCoBankName() {
    return zcCoBankName;
  }

  /**
   * @param zcCoBankName the zcCoBankName to set
   */
  public void setZcCoBankName(String zcCoBankName) {
    this.zcCoBankName = zcCoBankName;
  }

  /**
   * @return the returnBiList
   */
  public List getReturnBiList() {
    return returnBiList;
  }

  /**
   * @param returnBiList the returnBiList to set
   */
  public void setReturnBiList(List returnBiList) {
    this.returnBiList = returnBiList;
  }

  /**
   * @return the payOrder
   */
  public String getPayOrder() {
    return payOrder;
  }

  /**
   * @param payOrder the payOrder to set
   */
  public void setPayOrder(String payOrder) {
    this.payOrder = payOrder;
  }

  public BigDecimal getCanPaySum() {
    return canPaySum;
  }

  public void setCanPaySum(BigDecimal canPaySum) {
    this.canPaySum = canPaySum;
  }


}
