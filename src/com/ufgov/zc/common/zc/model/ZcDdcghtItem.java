package com.ufgov.zc.common.zc.model;

import java.math.BigDecimal;

public class ZcDdcghtItem extends ZcBaseBill {

  private String zcHtCode;

  private String itemCode;

  private String zcCatalogueCode;//品目名称

  private String zcCatalogueName;//品目代码

  private String zcBraName;//品牌

  private BigDecimal discount;//折扣率（优惠率）

  private String tempId;

  /**
   * @return the zcHtCode
   */
  public String getZcHtCode() {
    return zcHtCode;
  }

  /**
   * @param zcHtCode the zcHtCode to set
   */
  public void setZcHtCode(String zcHtCode) {
    this.zcHtCode = zcHtCode;
  }

  /**
   * @return the itemCode
   */
  public String getItemCode() {
    return itemCode;
  }

  /**
   * @param itemCode the itemCode to set
   */
  public void setItemCode(String itemCode) {
    this.itemCode = itemCode;
  }

  /**
   * @return the zcCatalogueCode
   */
  public String getZcCatalogueCode() {
    return zcCatalogueCode;
  }

  /**
   * @param zcCatalogueCode the zcCatalogueCode to set
   */
  public void setZcCatalogueCode(String zcCatalogueCode) {
    this.zcCatalogueCode = zcCatalogueCode;
  }

  /**
   * @return the zcCatalogueName
   */
  public String getZcCatalogueName() {
    return zcCatalogueName;
  }

  /**
   * @param zcCatalogueName the zcCatalogueName to set
   */
  public void setZcCatalogueName(String zcCatalogueName) {
    this.zcCatalogueName = zcCatalogueName;
  }

  /**
   * @return the zcBraName
   */
  public String getZcBraName() {
    return zcBraName;
  }

  /**
   * @param zcBraName the zcBraName to set
   */
  public void setZcBraName(String zcBraName) {
    this.zcBraName = zcBraName;
  }

  /**
   * @return the discount
   */
  public BigDecimal getDiscount() {
    return discount;
  }

  /**
   * @param discount the discount to set
   */
  public void setDiscount(BigDecimal discount) {
    this.discount = discount;
  }

  /**
   * @return the tempId
   */
  public String getTempId() {
    return tempId;
  }

  /**
   * @param tempId the tempId to set
   */
  public void setTempId(String tempId) {
    this.tempId = tempId;
  }

}
