package com.ufgov.zc.common.zc.model;

import java.io.Serializable;

public class ZcEbSupplierType extends ZcBaseBill implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = -8472103608857846070L;

  private String zcSuCode;

  private String typeCode;

  private String typeName;
  
  private Boolean isSelected;
  
  private String tempId;

  public String getZcSuCode() {
    return zcSuCode;
  }

  public void setZcSuCode(String zcSuCode) {
    this.zcSuCode = zcSuCode;
  }

  public String getTypeCode() {
    return typeCode;
  }

  public void setTypeCode(String typeCode) {
    this.typeCode = typeCode;
  }

  public String getTypeName() {
    return typeName;
  }

  public void setTypeName(String typeName) {
    this.typeName = typeName;
  }

  public Boolean getIsSelected() {
    return isSelected;
  }

  public void setIsSelected(Boolean isSelected) {
    this.isSelected = isSelected;
  }

  public String getTempId() {
    return tempId;
  }

  public void setTempId(String tempId) {
    this.tempId = tempId;
  }

}
