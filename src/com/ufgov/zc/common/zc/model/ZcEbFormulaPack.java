package com.ufgov.zc.common.zc.model;

import java.util.List;

/**
 * 
* @ClassName: ZcEbFormulaPack
* @Description: TCJLODO(封装 一个评标方法被多个标段引用的数据对象)
* @date: Aug 30, 2012 5:35:56 AM
* @version: V1.0 
* @since: 1.0
* @author: Administrator
* @modify:
 */
public class ZcEbFormulaPack extends ZcBaseBill {
  private String formulaCode;

  private List packList;

  private ZcEbFormula zcEbFormula;

  private boolean isDataChanged = false;

  /**
   * @return the isDataChanged
   */
  public boolean isDataChanged() {
    return isDataChanged;
  }

  /**
   * @param isDataChanged the isDataChanged to set
   */
  public void setDataChanged(boolean isDataChanged) {
    this.isDataChanged = isDataChanged;
  }

  /**
   * @return the formulaCode
   */
  public String getFormulaCode() {
    return formulaCode;
  }

  /**
   * @param formulaCode the formulaCode to set
   */
  public void setFormulaCode(String formulaCode) {
    this.formulaCode = formulaCode;
  }

  /**
   * @return the packList
   */
  public List getPackList() {
    return packList;
  }

  /**
   * @param packList the packList to set
   */
  public void setPackList(List packList) {
    this.packList = packList;
  }

  /**
   * @return the zcEbFormula
   */
  public ZcEbFormula getZcEbFormula() {
    return zcEbFormula;
  }

  /**
   * @param zcEbFormula the zcEbFormula to set
   */
  public void setZcEbFormula(ZcEbFormula zcEbFormula) {
    this.zcEbFormula = zcEbFormula;
  }

}
