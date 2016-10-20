/**
 * 
 */
package com.ufgov.zc.common.sf.model;

import com.ufgov.zc.common.zc.model.ZcBaseBill;

/**
 * @author Administrator
 *
 */
public class ZcFaCardField extends ZcBaseBill {

  /**
   * 
   */
  private static final long serialVersionUID = -4548924965915663063L;
  
  private String  faitemCode;
  private String  faitemName;
  private int  rowIndex;
  private int  colIndex;
  private String  isRo;
  private String  isVisi;
  private String  aIsNull;
  private String  isModify;
  private String  dataType;
  private String  isFpk;
  private String  fReFname;
  private String  valSetId;
  private String  bIsNull;
  private String  isPk;
  private String  fField;
  private boolean isReadOnly;
  
  public String getFaitemCode() {
    return faitemCode;
  }
  public void setFaitemCode(String faitemCode) {
    this.faitemCode = faitemCode;
  }
  public String getFaitemName() {
    return faitemName;
  }
  public void setFaitemName(String faitemName) {
    this.faitemName = faitemName;
  }
  public int getRowIndex() {
    return rowIndex;
  }
  public void setRowIndex(int rowIndex) {
    this.rowIndex = rowIndex;
  }
  public int getColIndex() {
    return colIndex;
  }
  public void setColIndex(int colIndex) {
    this.colIndex = colIndex;
  }
  public String getIsRo() {
    return isRo;
  }
  public void setIsRo(String isRo) {
    this.isRo = isRo;
  }
  public String getIsVisi() {
    return isVisi;
  }
  public void setIsVisi(String isVisi) {
    this.isVisi = isVisi;
  }
  public String getaIsNull() {
    return aIsNull;
  }
  public void setaIsNull(String aIsNull) {
    this.aIsNull = aIsNull;
  }
  public String getIsModify() {
    return isModify;
  }
  public void setIsModify(String isModify) {
    this.isModify = isModify;
  }
  public String getDataType() {
    return dataType;
  }
  public void setDataType(String dataType) {
    this.dataType = dataType;
  }
  public String getIsFpk() {
    return isFpk;
  }
  public void setIsFpk(String isFpk) {
    this.isFpk = isFpk;
  }
  public String getfReFname() {
    return fReFname;
  }
  public void setfReFname(String fReFname) {
    this.fReFname = fReFname;
  }
  public String getValSetId() {
    return valSetId;
  }
  public void setValSetId(String valSetId) {
    this.valSetId = valSetId;
  }
  public String getbIsNull() {
    return bIsNull;
  }
  public void setbIsNull(String bIsNull) {
    this.bIsNull = bIsNull;
  }
  public String getIsPk() {
    return isPk;
  }
  public void setIsPk(String isPk) {
    this.isPk = isPk;
  }
  public String getfField() {
    return fField;
  }
  public void setfField(String fField) {
    this.fField = fField;
  }
  public boolean isReadOnly() {
    return isReadOnly;
  }
  public void setReadOnly(boolean isReadOnly) {
    this.isReadOnly = isReadOnly;
  }

}
