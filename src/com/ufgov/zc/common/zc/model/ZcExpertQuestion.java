package com.ufgov.zc.common.zc.model;

import java.io.Serializable;
import java.util.List;

/**
 * The persistent class for the ZC_EXPERT_QUESTION database table.
 * 
 */

public class ZcExpertQuestion implements Serializable {
  private static final long serialVersionUID = 1L;

  private long id;

  private String expertName;

  private String packCode;

  private String projCode;

  private String supplierName;

  private List zcExpertQuestionContents;

  public ZcExpertQuestion() {
  }

  public long getId() {
    return this.id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getExpertName() {
    return this.expertName;
  }

  public void setExpertName(String expertName) {
    this.expertName = expertName;
  }

  public String getPackCode() {
    return this.packCode;
  }

  public void setPackCode(String packCode) {
    this.packCode = packCode;
  }

  public String getProjCode() {
    return this.projCode;
  }

  public void setProjCode(String projCode) {
    this.projCode = projCode;
  }

  public String getSupplierName() {
    return this.supplierName;
  }

  public void setSupplierName(String supplierName) {
    this.supplierName = supplierName;
  }

  public List getZcExpertQuestionContents() {
    return this.zcExpertQuestionContents;
  }

  public void setZcExpertQuestionContents(List zcExpertQuestionContents) {
    this.zcExpertQuestionContents = zcExpertQuestionContents;
  }

}