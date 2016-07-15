package com.ufgov.zc.common.zc.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.ufgov.zc.common.system.Guid;
import com.ufgov.zc.common.system.IInitializeable;

public class SupplierPriceDetail implements Serializable, IInitializeable {

  /**
   * 
   */
  private static final long serialVersionUID = 1708695472339404889L;

  private String detailId;

  private String singupPackId;

  private String prodName;

  private String prodBrand;

  private String prodType;

  private String prodMainParam;

  private String prodAuxiliaryParam;

  private String prodDeviation;

  private String procureType;

  private String prodLocation;

  private String prodCompany;

  private BigDecimal prodNum = new BigDecimal(0);

  private String numUnit;

  private BigDecimal prodPrice = new BigDecimal(0);

  private String prodGuaranteePeriod;

  private String autoEmission;

  private String isEnergySaving;

  private String isWarterSaving;

  private String isEnvironmentProtection;

  private String isInnovative;

  private String statisticsIndicator;

  private String procureSize;

  private String procureArea;

  //供货期
  private Date gongHuoDate;

  public String getProdNumStr() {
    return prodNum == null ? null : prodNum.toString();
  }

  public String getSingupPackId() {
    return singupPackId;
  }

  public void setSingupPackId(String singupPackId) {
    this.singupPackId = singupPackId;
  }

  public String getDetailId() {
    return detailId;
  }

  public void setDetailId(String detailId) {
    this.detailId = detailId;
  }

  public String getProdName() {
    return prodName;
  }

  public void setProdName(String prodName) {
    this.prodName = prodName;
  }

  public String getProdBrand() {
    return prodBrand;
  }

  public void setProdBrand(String prodBrand) {
    this.prodBrand = prodBrand;
  }

  public String getProdType() {
    return prodType;
  }

  public void setProdType(String prodType) {
    this.prodType = prodType;
  }

  public String getProdMainParam() {
    return prodMainParam;
  }

  public void setProdMainParam(String prodMainParam) {
    this.prodMainParam = prodMainParam;
  }

  public String getProdAuxiliaryParam() {
    return prodAuxiliaryParam;
  }

  public void setProdAuxiliaryParam(String prodAuxiliaryParam) {
    this.prodAuxiliaryParam = prodAuxiliaryParam;
  }

  public String getProdDeviation() {
    return prodDeviation;
  }

  public void setProdDeviation(String prodDeviation) {
    this.prodDeviation = prodDeviation;
  }

  public String getProcureType() {
    return procureType;
  }

  public void setProcureType(String procureType) {
    this.procureType = procureType;
  }

  public String getProdLocation() {
    return prodLocation;
  }

  public void setProdLocation(String prodLocation) {
    this.prodLocation = prodLocation;
  }

  public String getProdCompany() {
    return prodCompany;
  }

  public void setProdCompany(String prodCompany) {
    this.prodCompany = prodCompany;
  }

  public BigDecimal getProdNum() {
    return prodNum;
  }

  public void setProdNum(BigDecimal prodNum) {
    this.prodNum = prodNum;
  }

  public String getNumUnit() {
    return numUnit;
  }

  public void setNumUnit(String numUnit) {
    this.numUnit = numUnit;
  }

  public BigDecimal getProdPrice() {
    return prodPrice;
  }

  public void setProdPrice(BigDecimal prodPrice) {
    this.prodPrice = prodPrice;
  }

  public BigDecimal getProdSumPrice() {
    return this.prodPrice.multiply(prodNum);
  }

  public String getProdGuaranteePeriod() {
    return prodGuaranteePeriod;
  }

  public void setProdGuaranteePeriod(String prodGuaranteePeriod) {
    this.prodGuaranteePeriod = prodGuaranteePeriod;
  }

  public String getAutoEmission() {
    return autoEmission;
  }

  public void setAutoEmission(String autoEmission) {
    this.autoEmission = autoEmission;
  }

  public String getIsEnergySaving() {
    return isEnergySaving;
  }

  public void setIsEnergySaving(String isEnergySaving) {
    this.isEnergySaving = isEnergySaving;
  }

  public String getIsWarterSaving() {
    return isWarterSaving;
  }

  public void setIsWarterSaving(String isWarterSaving) {
    this.isWarterSaving = isWarterSaving;
  }

  public String getIsEnvironmentProtection() {
    return isEnvironmentProtection;
  }

  public void setIsEnvironmentProtection(String isEnvironmentProtection) {
    this.isEnvironmentProtection = isEnvironmentProtection;
  }

  public String getIsInnovative() {
    return isInnovative;
  }

  public void setIsInnovative(String isInnovative) {
    this.isInnovative = isInnovative;
  }

  public String getStatisticsIndicator() {
    return statisticsIndicator;
  }

  public void setStatisticsIndicator(String statisticsIndicator) {
    this.statisticsIndicator = statisticsIndicator;
  }

  public String getProcureSize() {
    return procureSize;
  }

  public void setProcureSize(String procureSize) {
    this.procureSize = procureSize;
  }

  public String getProcureArea() {
    return procureArea;
  }

  public void setProcureArea(String procureArea) {
    this.procureArea = procureArea;
  }

  public SupplierPriceDetail() {
    super();
  }

  
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    } else {
      if (!(obj instanceof SupplierPriceDetail)) {
        return false;
      } else {
        return this.getDetailId().equals(((SupplierPriceDetail) obj).getDetailId());
      }
    }
  }

  
  public void initialize() {
    this.detailId = Guid.genID();
  }

  /**
   * @return the gongHuoDate
   */
  public Date getGongHuoDate() {
    return gongHuoDate;
  }

  /**
   * @param gongHuoDate the gongHuoDate to set
   */
  public void setGongHuoDate(Date gongHuoDate) {
    this.gongHuoDate = gongHuoDate;
  }

}
