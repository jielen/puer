/**
 * 
 */
package com.ufgov.zc.common.sf.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ufgov.zc.common.util.EmpMeta;
import com.ufgov.zc.common.zc.model.ZcBaseBill;

/**
 * 
 * @author Administrator
 *
 */
public class SfPayFees extends ZcBaseBill {

  /**
   * 
   */
  private static final long serialVersionUID = 3366518022043521128L;

  private SfEntrust entrust = new SfEntrust();

  private BigDecimal entrustId;

  private String entrustCode;

  private String entrsutName;

  private String majorName;

  private String jdFzr;

  private String jdFzrName;

  private String jdFhr;

  private String jdFhrName;

  private String entrustorName;

  private SfEntrustor entrustor = new SfEntrustor();

  /*
   *总费用
   */
  private BigDecimal totalFee;

  /**
   * 已缴纳费用
   */
  private BigDecimal payedFee;

  /**
   * 需缴纳费用
   */
  private BigDecimal needFee;

  /**
   * 缴纳时间，多次缴纳时，取最晚的缴纳时间
   */
  private Date payTime;

  /**
   * 缴费明细,成员是SfCharge
   */
  private List payedFeeslst = new ArrayList();

  public String getJdFzrName() {
    return EmpMeta.getEmpName(getJdFzr());
  }

  public void setJdFzrName(String jdFzrName) {
    this.jdFzrName = jdFzrName;
  }

  public String getJdFhrName() {
    return EmpMeta.getEmpName(getJdFhr());
  }

  public void setJdFhrName(String jdFhrName) {
    this.jdFhrName = jdFhrName;
  }

  public SfEntrust getEntrust() {
    return entrust;
  }

  public void setEntrust(SfEntrust entrust) {
    this.entrust = entrust;
  }

  public String getMajorName() {
    return majorName;
  }

  public void setMajorName(String majorName) {
    this.majorName = majorName;
  }

  public String getJdFzr() {
    return jdFzr;
  }

  public void setJdFzr(String jdFzr) {
    this.jdFzr = jdFzr;
  }

  public String getJdFhr() {
    return jdFhr;
  }

  public void setJdFhr(String jdFhr) {
    this.jdFhr = jdFhr;
  }

  public SfEntrustor getEntrustor() {
    return entrustor;
  }

  public void setEntrustor(SfEntrustor entrustor) {
    this.entrustor = entrustor;
  }

  public BigDecimal getTotalFee() {
    return totalFee;
  }

  public void setTotalFee(BigDecimal totalFee) {
    this.totalFee = totalFee;
  }

  public BigDecimal getPayedFee() {
    return payedFee;
  }

  public void setPayedFee(BigDecimal payedFee) {
    this.payedFee = payedFee;
  }

  public BigDecimal getNeedFee() {
    BigDecimal t = getTotalFee() == null ? new BigDecimal(0) : getTotalFee();
    BigDecimal p = getPayedFee() == null ? new BigDecimal(0) : getPayedFee();
    int i = t.compareTo(p);
    if (i == 1) {
      return t.subtract(p);
    } else {
      return new BigDecimal(0);
    }
  }

  public void setNeedFee(BigDecimal needFee) {
    this.needFee = needFee;
  }

  public Date getPayTime() {
    return payTime;
  }

  public void setPayTime(Date payTime) {
    this.payTime = payTime;
  }

  public List getPayedFeeslst() {
    return payedFeeslst;
  }

  public void setPayedFeeslst(List payedFeeslst) {
    this.payedFeeslst = payedFeeslst;
  }

  public BigDecimal getEntrustId() {
    if (getEntrust() != null)
      return getEntrust().getEntrustId();
    return entrustId;
  }

  public void setEntrustId(BigDecimal entrustId) {
    this.entrustId = entrustId;
  }

  public String getEntrustorName() {
    if (getEntrustor() != null)
      return getEntrustor().getName();
    return entrustorName;
  }

  public void setEntrustorName(String entrustorName) {
    this.entrustorName = entrustorName;
  }

  public String getEntrustCode() {
    if (getEntrust() != null)
      return getEntrust().getCode();
    return entrustCode;
  }

  public void setEntrustCode(String entrustCode) {
    this.entrustCode = entrustCode;
  }

  public String getEntrsutName() {
    if (getEntrust() != null)
      return getEntrust().getName();
    return entrsutName;
  }

  public void setEntrsutName(String entrsutName) {
    this.entrsutName = entrsutName;
  }

}
