/**
 * 
 */
package com.ufgov.zc.common.sf.model;

import java.math.BigDecimal;

import com.ufgov.zc.common.zc.model.TreeNodeValueObject;

/**
 * @author Administrator
 *
 */
public class ZcFaCardType extends TreeNodeValueObject {

  /**
   * 
   */
  private static final long serialVersionUID = -4548924965915663063L;
  
  
  //P_CODE
  private String pCode;
  //NAME
  //FATYPE_CODE
  private String fatypeName;
  private String fatypeCode;
  private String fatypeDw;
  private BigDecimal useLife ;
  private BigDecimal resiRat;
 
  public String getpCode() {
    return pCode;
  }
  public void setpCode(String pCode) {
    this.pCode = pCode;
  }
  public String getFatypeName() {
    return fatypeName;
  }
  public void setFatypeName(String fatypeName) {
    this.fatypeName = fatypeName;
  }
  public String getFatypeCode() {
    return fatypeCode;
  }
  public void setFatypeCode(String fatypeCode) {
    this.fatypeCode = fatypeCode;
  }
  public String getFatypeDw() {
    return fatypeDw;
  }
  public void setFatypeDw(String fatypeDw) {
    this.fatypeDw = fatypeDw;
  }
  public BigDecimal getUseLife() {
    return useLife;
  }
  public void setUseLife(BigDecimal useLife) {
    this.useLife = useLife;
  }
  public BigDecimal getResiRat() {
    return resiRat;
  }
  public void setResiRat(BigDecimal resiRat) {
    this.resiRat = resiRat;
  }

}
