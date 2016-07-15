package com.ufgov.zc.common.zc.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ZcSupQuaType extends ZcBaseBill implements Serializable {

  private String typeCode;

  private String typeName;

  private String parentTypeCode;

  private String parentTypeName;

  private String zcCatalogueCode;

  private Date createTime;

  private String executor;

  private String isLowest;

  /**

   * 孩子列表

   */

  private List childrenList = new ArrayList();

  public String getTypeCode() {
    return typeCode;
  }

  public void setTypeCode(String typeCode) {
    this.typeCode = typeCode;
  }

  /**
   * @return the typeName
   */
  public String getTypeName() {
    return typeName;
  }

  /**
   * @param typeName the typeName to set
   */
  public void setTypeName(String typeName) {
    this.typeName = typeName;
  }

  public String getParentTypeCode() {
    return parentTypeCode;
  }

  public void setParentTypeCode(String parentTypeCode) {
    this.parentTypeCode = parentTypeCode;
  }

  public String getParentTypeName() {
    return parentTypeName;
  }

  public void setParentTypeName(String parentTypeName) {
    this.parentTypeName = parentTypeName;
  }

  public String getZcCatalogueCode() {
    return zcCatalogueCode;
  }

  public void setZcCatalogueCode(String zcCatalogueCode) {
    this.zcCatalogueCode = zcCatalogueCode;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public String getExecutor() {
    return executor;
  }

  public void setExecutor(String executor) {
    this.executor = executor;
  }

  public String getIsLowest() {
    return isLowest;
  }

  public void setIsLowest(String isLowest) {
    this.isLowest = isLowest;
  }

  /**
   * @return the childrenList
   */
  public List getChildrenList() {
    return childrenList;
  }

  /**
   * @param childrenList the childrenList to set
   */
  public void setChildrenList(List childrenList) {
    this.childrenList = childrenList;
  }

  public String toString() {

    if (this.typeCode == null || "".equals(this.typeCode.trim())) {

      return this.typeName;

    }

    return "[" + this.typeCode + "]" + this.typeName;

  }
}