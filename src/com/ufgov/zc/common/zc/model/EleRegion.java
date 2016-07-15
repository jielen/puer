package com.ufgov.zc.common.zc.model;

import java.io.Serializable;

public class EleRegion extends TreeNodeValueObject implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = -4232680848067173348L;
  private String setYear;
  private String chrCode;
  private String chrName;
  private Integer isTop;
  public String getSetYear() {
    return setYear;
  }
  public void setSetYear(String setYear) {
    this.setYear = setYear;
  }
  public String getChrCode() {
    return chrCode;
  }
  public void setChrCode(String chrCode) {
    this.chrCode = chrCode;
  }
  public String getChrName() {
    return chrName;
  }
  public void setChrName(String chrName) {
    this.chrName = chrName;
  }
  public Integer getIsTop() {
    return isTop;
  }
  public void setIsTop(Integer isTop) {
    this.isTop = isTop;
  }

}
