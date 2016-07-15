package com.ufgov.zc.common.zc.model;

import java.io.Serializable;

public class ZcNumNo implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 8771021939581420693L;

  private String compoId;
  private String preNo;
  private Long no;

  public String getCompoId() {
    return compoId;
  }
  public void setCompoId(String compoId) {
    this.compoId = compoId;
  }
  public String getPreNo() {
    return preNo;
  }
  public void setPreNo(String preNo) {
    this.preNo = preNo;
  }
  public Long getNo() {
    return no;
  }
  public void setNo(Long no) {
    this.no = no;
  }

}
