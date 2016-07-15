package com.ufgov.zc.common.zc.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 采购年终结转模型类
 * @author wangwenhui
 *
 */
public class ZcYearPlan extends ZcBaseBill {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  /**
   * 项目编号
   */
  private String zcMakeCode;

  /**
   * 项目名称
   */
  private String zcMakeName;

  /**
   * 计划状态
   */
  private String zcMakeStatus;

  /**
   * 采购方式
   */
  private String zcCgType;

  /**
     * 处理标记
     */
  private String zcYepFlag;

  /**
   * 临时编号
   */
  private String zcTempMakeCode;

  /**
   * 采购计划金额
   */
  private BigDecimal zcMakeSum = new BigDecimal("0.00");

  /**
   * 已录合同备案金额
   */
  private BigDecimal zcHtSum = new BigDecimal("0.00");

  /**
   * 已录验收结算金额
   */
  private BigDecimal zcBalSum = new BigDecimal("0.00");

  /**
   * 计划可结转(结项)总金额
   */
  private BigDecimal zcMakeJzSum = new BigDecimal("0.00");

  /**
   * 合同可结转总金额
   */
  private BigDecimal zcHtJzSum = new BigDecimal("0.00");

  /**
   * 验收结算可结转总金额
   */
  private BigDecimal zcBalJzSum = new BigDecimal("0.00");

  private BigDecimal zcBlHtSum = new BigDecimal("0.00");

  private BigDecimal zcBlBalSum = new BigDecimal("0.0");

  private BigDecimal zcBlHtJzSum = new BigDecimal("0.0");

  private List makeBiList;

  private List htBiList;

  public String getZcMakeCode() {
    return zcMakeCode;
  }

  public void setZcMakeCode(String zcMakeCode) {
    this.zcMakeCode = zcMakeCode;
  }

  public String getZcMakeName() {
    return zcMakeName;
  }

  public String getZcCgType() {
    return zcCgType;
  }

  public void setZcCgType(String zcCgType) {
    this.zcCgType = zcCgType;
  }

  public void setZcMakeName(String zcMakeName) {
    this.zcMakeName = zcMakeName;
  }

  public String getZcMakeStatus() {
    return zcMakeStatus;
  }

  public void setZcMakeStatus(String zcMakeStatus) {
    this.zcMakeStatus = zcMakeStatus;
  }

  public String getZcTempMakeCode() {
    return zcTempMakeCode;
  }

  public void setZcTempMakeCode(String zcTempMakeCode) {
    this.zcTempMakeCode = zcTempMakeCode;
  }

  public BigDecimal getZcMakeSum() {
    return zcMakeSum;
  }

  public void setZcMakeSum(BigDecimal zcMakeSum) {
    this.zcMakeSum = zcMakeSum;
  }

  public BigDecimal getZcHtSum() {
    return zcHtSum;
  }

  public void setZcHtSum(BigDecimal zcHtSum) {
    this.zcHtSum = zcHtSum;
  }

  public BigDecimal getZcBalSum() {
    return zcBalSum;
  }

  public void setZcBalSum(BigDecimal zcBalSum) {
    this.zcBalSum = zcBalSum;
  }

  public String getZcYepFlag() {
    return zcYepFlag;
  }

  public void setZcYepFlag(String zcYepFlag) {
    this.zcYepFlag = zcYepFlag;
  }

  public BigDecimal getZcMakeJzSum() {
    return zcMakeJzSum;
  }

  public void setZcMakeJzSum(BigDecimal zcMakeJzSum) {
    this.zcMakeJzSum = zcMakeJzSum;
  }

  public BigDecimal getZcHtJzSum() {
    return zcHtJzSum;
  }

  public void setZcHtJzSum(BigDecimal zcHtJzSum) {
    this.zcHtJzSum = zcHtJzSum;
  }

  public BigDecimal getZcBalJzSum() {
    return zcBalJzSum;
  }

  public void setZcBalJzSum(BigDecimal zcBalJzSum) {
    this.zcBalJzSum = zcBalJzSum;
  }

  public BigDecimal getZcBlHtSum() {
    return zcBlHtSum;
  }

  public void setZcBlHtSum(BigDecimal zcBlHtSum) {
    this.zcBlHtSum = zcBlHtSum;
  }

  public BigDecimal getZcBlBalSum() {
    return zcBlBalSum;
  }

  public void setZcBlBalSum(BigDecimal zcBlBalSum) {
    this.zcBlBalSum = zcBlBalSum;
  }

  public BigDecimal getZcBlHtJzSum() {
    return zcBlHtJzSum;
  }

  public void setZcBlHtJzSum(BigDecimal zcBlHtJzSum) {
    this.zcBlHtJzSum = zcBlHtJzSum;
  }

  public List getMakeBiList() {
    if (null == makeBiList) {
      makeBiList = new ArrayList();
    }
    return makeBiList;
  }

  public void setMakeBiList(List makeBiList) {
    this.makeBiList = makeBiList;
  }

  public List getHtBiList() {
    if (null == htBiList) {
      htBiList = new ArrayList();
    }
    return htBiList;
  }

  public void setHtBiList(List htBiList) {
    this.htBiList = htBiList;
  }
}
