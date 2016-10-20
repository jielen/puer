/**
 * 
 */
package com.ufgov.zc.common.sf.model;

import com.ufgov.zc.common.zc.model.ZcBaseBill;

/**
 * @author Administrator
 *
 */
public class ZcFaCardStyle extends ZcBaseBill {

  /**
   * 
   */
  private static final long serialVersionUID = -5802182809711546392L;
  
  private String cardStylCode;
  private String cardStylName;
  private String isDeprStyl;
  private String stylImages;
  public String getCardStylCode() {
    return cardStylCode;
  }
  public void setCardStylCode(String cardStylCode) {
    this.cardStylCode = cardStylCode;
  }
  public String getCardStylName() {
    return cardStylName;
  }
  public void setCardStylName(String cardStylName) {
    this.cardStylName = cardStylName;
  }
  public String getIsDeprStyl() {
    return isDeprStyl;
  }
  public void setIsDeprStyl(String isDeprStyl) {
    this.isDeprStyl = isDeprStyl;
  }
  public String getStylImages() {
    return stylImages;
  }
  public void setStylImages(String stylImages) {
    this.stylImages = stylImages;
  }
}
