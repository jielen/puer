package com.ufgov.zc.common.commonbiz.model;

import java.io.Serializable;

public class EAcc extends BaseElement implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = -7419557506980695L;

  
  private String isLowest;
  
  private String chrId;


  public String getIsLowest() {
    return isLowest;
  }


  public void setIsLowest(String isLowest) {
    this.isLowest = isLowest;
  }


  public String getChrId() {
    return chrId;
  }


  public void setChrId(String chrId) {
    this.chrId = chrId;
  }

}
