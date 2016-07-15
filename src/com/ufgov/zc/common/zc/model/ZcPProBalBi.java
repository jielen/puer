/**   
* @(#) project: zcxa
* @(#) file: ZcPProBalBi.java
* 
* Copyright 2010 UFGOV, Inc. All rights reserved.
* UFGOV PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
* 
*/
package com.ufgov.zc.common.zc.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
* @ClassName: ZcPProBalBi
* @Description: TCJLODO(这里用一句话描述这个类的作用)
* @date: 2010-8-3 下午02:02:17
* @version: V1.0 
* @since: 1.0
* @author: Administrator
* @modify: 
*/
public class ZcPProBalBi implements Serializable {
  private String zcBalBiId;

  private String zcProBiSeq;

  private String zcBalId;

  private String zcBiNo;

  private String zcHtBiNo;

  private BigDecimal zcBiBcjsSum;

  private String zcCode;

  private String zcAmBillNo;

  private String zcAmStatus;

  private String zcHtCode;

  private BigDecimal zcBiYjjsSum;

  private BigDecimal ZcBiChbaSum;

  private String balModeCode;
//经济科目
  private String outLayCode;
  private String outLayName;
  //经济科目是否末级
  private String outLayIsLeaf;
  

  private String zcFundCode;

  private String zcFundName;

  private String zcOriginCode;

  private String zcOriginName;

  private String zcPaytypeCode;

  private String zcPaytypeName;

  private String tempId;

  private String payoutCode;

  private String sendDocName;//指标文号

  private String orgCode;

  private BigDecimal zcBiSum;//

  private BigDecimal zcBiBcsySum;//合同使用金额

  private String zcCatalogueCode;

  private String zcCatalogueName;

  private String payStatus;

  private BigDecimal zcBiBal;//指标剩余金额

  private String isLastPay;
  //预算项目代码
  private String zcBisCode;
  //预算项目名称
  private String zcBisName;
  //功能科目代码
  private String zcBAccCode;
  //功能科目名称
  private String zcBAccName;


  public BigDecimal getZcBiBcsySum() {
    return zcBiBcsySum;
  }

  public void setZcBiBcsySum(BigDecimal zcBiBcsySum) {
    this.zcBiBcsySum = zcBiBcsySum;
  }

  public String getPayoutCode() {
    return payoutCode;
  }

  public void setPayoutCode(String payoutCode) {
    this.payoutCode = payoutCode;
  }

  public String getOrgCode() {
    return orgCode;
  }

  public void setOrgCode(String orgCode) {
    this.orgCode = orgCode;
  }

  public BigDecimal getZcBiSum() {
    return zcBiSum;
  }

  public void setZcBiSum(BigDecimal zcBiSum) {
    this.zcBiSum = zcBiSum;
  }

  public BigDecimal getZcBiYjbaSum() {
    return zcBiYjbaSum;
  }

  public void setZcBiYjbaSum(BigDecimal zcBiYjbaSum) {
    this.zcBiYjbaSum = zcBiYjbaSum;
  }

  public BigDecimal getZcBiDoSum() {
    return zcBiDoSum;
  }

  public void setZcBiDoSum(BigDecimal zcBiDoSum) {
    this.zcBiDoSum = zcBiDoSum;
  }

  private BigDecimal zcBiYjbaSum;

  private BigDecimal zcBiDoSum;

  public String getTempId() {
    return tempId;
  }

  public void setTempId(String tempId) {
    this.tempId = tempId;
  }

  /*
   * 本次项目预算金额
   */
  public String getZcBalId() {
    return zcBalId;
  }

  public void setZcBalId(String zcBalId) {
    this.zcBalId = zcBalId;
  }

  public String getZcBiNo() {
    return zcBiNo;
  }

  public void setZcBiNo(String zcBiNo) {
    this.zcBiNo = zcBiNo;
  }

  public BigDecimal getZcBiBcjsSum() {
    return zcBiBcjsSum;
  }

  public void setZcBiBcjsSum(BigDecimal zcBiBcjsSum) {
    this.zcBiBcjsSum = zcBiBcjsSum;
  }

  public String getZcCode() {
    return zcCode;
  }

  public void setZcCode(String zcCode) {
    this.zcCode = zcCode;
  }

  public String getZcAmBillNo() {
    return zcAmBillNo;
  }

  public void setZcAmBillNo(String zcAmBillNo) {
    this.zcAmBillNo = zcAmBillNo;
  }

  public String getZcAmStatus() {
    return zcAmStatus;
  }

  public void setZcAmStatus(String zcAmStatus) {
    this.zcAmStatus = zcAmStatus;
  }

  public BigDecimal getZcBiYjjsSum() {
    return zcBiYjjsSum;
  }

  public void setZcBiYjjsSum(BigDecimal zcBiYjjsSum) {
    this.zcBiYjjsSum = zcBiYjjsSum;
  }

  public BigDecimal getZcBiChbaSum() {
    return ZcBiChbaSum;
  }

  public void setZcBiChbaSum(BigDecimal zcBiChbaSum) {
    ZcBiChbaSum = zcBiChbaSum;
  }

  public String getBalModeCode() {
    return balModeCode;
  }

  public void setBalModeCode(String balModeCode) {
    this.balModeCode = balModeCode;
  }

  public String getOutLayCode() {
    return outLayCode;
  }

  public void setOutLayCode(String outLayCode) {
    this.outLayCode = outLayCode;
  }

  public String getZcHtCode() {
    return zcHtCode;
  }

  public void setZcHtCode(String zcHtCode) {
    this.zcHtCode = zcHtCode;
  }

  public String getZcFundCode() {
    return zcFundCode;
  }

  public void setZcFundCode(String zcFundCode) {
    this.zcFundCode = zcFundCode;
  }

  public String getZcFundName() {
    return zcFundName;
  }

  public void setZcFundName(String zcFundName) {
    this.zcFundName = zcFundName;
  }

  public String getZcOriginCode() {
    return zcOriginCode;
  }

  public void setZcOriginCode(String zcOriginCode) {
    this.zcOriginCode = zcOriginCode;
  }

  public String getZcOriginName() {
    return zcOriginName;
  }

  public void setZcOriginName(String zcOriginName) {
    this.zcOriginName = zcOriginName;
  }

  public String getZcPaytypeCode() {
    return zcPaytypeCode;
  }

  public void setZcPaytypeCode(String zcPaytypeCode) {
    this.zcPaytypeCode = zcPaytypeCode;
  }

  public String getZcPaytypeName() {
    return zcPaytypeName;
  }

  public void setZcPaytypeName(String zcPaytypeName) {
    this.zcPaytypeName = zcPaytypeName;
  }

  public String getZcCatalogueCode() {
    return zcCatalogueCode;
  }

  public void setZcCatalogueCode(String zcCatalogueCode) {
    this.zcCatalogueCode = zcCatalogueCode;
  }

  public String getZcCatalogueName() {
    return zcCatalogueName;
  }

  public void setZcCatalogueName(String zcCatalogueName) {
    this.zcCatalogueName = zcCatalogueName;
  }

  public String getZcBalBiId() {
    return zcBalBiId;
  }

  public void setZcBalBiId(String zcBalBiId) {
    this.zcBalBiId = zcBalBiId;
  }

  public String getZcProBiSeq() {
    return zcProBiSeq;
  }

  public void setZcProBiSeq(String zcProBiSeq) {
    this.zcProBiSeq = zcProBiSeq;
  }

  public String getPayStatus() {
    return payStatus;
  }

  public void setPayStatus(String payStatus) {
    this.payStatus = payStatus;
  }

  public String getZcHtBiNo() {
    return zcHtBiNo;
  }

  public void setZcHtBiNo(String zcHtBiNo) {
    this.zcHtBiNo = zcHtBiNo;
  }

  /**
   * @return the zcBiBal
   */
  public BigDecimal getZcBiBal() {
    return zcBiBal;
  }

  /**
   * @param zcBiBal the zcBiBal to set
   */
  public void setZcBiBal(BigDecimal zcBiBal) {
    this.zcBiBal = zcBiBal;
  }

  /**
   * @return the sendDocName
   */
  public String getSendDocName() {
    return sendDocName;
  }

  /**
   * @param sendDocName the sendDocName to set
   */
  public void setSendDocName(String sendDocName) {
    this.sendDocName = sendDocName;
  }

  /**
   * @return the isLastPay
   */
  public String getIsLastPay() {
    return isLastPay;
  }

  /**
   * @param isLastPay the isLastPay to set
   */
  public void setIsLastPay(String isLastPay) {
    this.isLastPay = isLastPay;
  }

  public String getZcBisCode() {
    return zcBisCode;
  }

  public void setZcBisCode(String zcBisCode) {
    this.zcBisCode = zcBisCode;
  }

  public String getZcBisName() {
    return zcBisName;
  }

  public void setZcBisName(String zcBisName) {
    this.zcBisName = zcBisName;
  }

  public String getZcBAccCode() {
    return zcBAccCode;
  }

  public void setZcBAccCode(String zcBAccCode) {
    this.zcBAccCode = zcBAccCode;
  }

  public String getZcBAccName() {
    return zcBAccName;
  }

  public void setZcBAccName(String zcBAccName) {
    this.zcBAccName = zcBAccName;
  }

  public String getOutLayName() {
    return outLayName;
  }

  public void setOutLayName(String outLayName) {
    this.outLayName = outLayName;
  }

  public String getOutLayIsLeaf() {
    return outLayIsLeaf;
  }

  public void setOutLayIsLeaf(String outLayIsLeaf) {
    this.outLayIsLeaf = outLayIsLeaf;
  }
  

}
