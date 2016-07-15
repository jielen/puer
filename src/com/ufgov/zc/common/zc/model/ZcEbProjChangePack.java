package com.ufgov.zc.common.zc.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 按照分包变更的数据对象
 * Created with IntelliJ IDEA.
 * User: qianmingjin
 * Date: 12-10-27
 * Time: 下午3:44
 * To change this template use File | Settings | File Templates.
 */
public class ZcEbProjChangePack extends ZcBaseBill implements Serializable, Cloneable {
  private String ID;

  private String projCode; //项目编号

  private String chgID; //变更编号

  private String packCode;//分包ID

  private String packName;//分包编号

  /**
   * @return the iD
   */
  public String getID() {
    return ID;
  }

  /**
   * @param iD the iD to set
   */
  public void setID(String iD) {
    ID = iD;
  }

  private String packDesc;//分包名称

  private BigDecimal packBudget;//预算

  private int chgNum;//变更次数

  private String purSrcWay;//原采购方式

  private String purWay;//批复采购方式

  public String getProjCode() {
    return projCode;
  }

  public void setProjCode(String projCode) {
    this.projCode = projCode;
  }

  public String getChgID() {
    return chgID;
  }

  public void setChgID(String chgID) {
    this.chgID = chgID;
  }

  public String getPackCode() {
    return packCode;
  }

  public void setPackCode(String packCode) {
    this.packCode = packCode;
  }

  public String getPackName() {
    return packName;
  }

  public void setPackName(String packName) {
    this.packName = packName;
  }

  public BigDecimal getPackBudget() {
    return packBudget;
  }

  public void setPackBudget(BigDecimal packBudget) {
    this.packBudget = packBudget;
  }

  public int getChgNum() {
    return chgNum;
  }

  public void setChgNum(int chgNum) {
    this.chgNum = chgNum;
  }

  public String getPurSrcWay() {
    return purSrcWay;
  }

  public void setPurSrcWay(String purSrcWay) {
    this.purSrcWay = purSrcWay;
  }

  public String getPurWay() {
    return purWay;
  }

  public void setPurWay(String purWay) {
    this.purWay = purWay;
  }

  /**
   * @return the packDesc
   */
  public String getPackDesc() {
    return packDesc;
  }

  /**
   * @param packDesc the packDesc to set
   */
  public void setPackDesc(String packDesc) {
    this.packDesc = packDesc;
  }

}
