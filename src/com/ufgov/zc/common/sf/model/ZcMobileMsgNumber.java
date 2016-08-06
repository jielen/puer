/**
 * 
 */
package com.ufgov.zc.common.sf.model;

import com.ufgov.zc.common.zc.model.ZcBaseBill;

/**
 * @author Administrator
 */
public class ZcMobileMsgNumber extends ZcBaseBill {

  /**
   * 
   */
  private static final long serialVersionUID = 3003022298749506073L;

  private String code;

  private String mobile;

  private String mobileHide;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public String getMobileHide() {
    return mobileHide;
  }

  public void setMobileHide(String mobileHide) {
    this.mobileHide = mobileHide;
  }

}
