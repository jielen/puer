/*
 * *
 *  Copyright 2012 by Beijing UFIDA Government Affairs Software Co.,Ltd.,
 *  All rights reserved.
 *
 *  版权所有：北京用友政务软件有限公司
 *  未经本公司许可，不得以任何方式复制或使用本程序任何部分，
 *  侵权者将受到法律追究。
 * /
 */

package com.ufgov.zc.common.zc.model;

/**
 * <p>PURPOSE:
 * <p>DESCRIPTION:
 * <p>CALLED BY:	qianmingjin
 * <p>CREATE DATE:  12-3-23
 * <p>UPDATE DATE: 12-3-23
 * <p>UPDATE USER: qianmingjin
 * <p>HISTORY:		1.0
 *
 * @author qianmingjin
 * @version 1.0
 * @see
 * @since java 1.5.0
 */
public class ZCDiYuCtg extends ZcBaseBill {
  private String diYuCode;

  private String diYuName;

  private String billStatus;

  public String getBillStatus() {
    return billStatus;
  }

  public void setBillStatus(String billStatus) {
    this.billStatus = billStatus;
  }

  public String getDiYuCode() {
    return diYuCode;
  }

  public void setDiYuCode(String diYuCode) {
    this.diYuCode = diYuCode;
  }

  public String getDiYuName() {
    return diYuName;
  }

  public void setDiYuName(String diYuName) {
    this.diYuName = diYuName;
  }
}
