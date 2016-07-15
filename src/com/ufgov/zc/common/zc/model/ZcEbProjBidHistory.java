package com.ufgov.zc.common.zc.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 
* @ClassName: ZcEbProjBidHistory
* @Description: TCJLODO(供应商投标历史记录)
* @date: May 21, 2013 11:00:37 AM
* @version: V1.0 
* @since: 1.0
* @author: Administrator
* @modify:
 */
public class ZcEbProjBidHistory implements Serializable {

  private String snCode;

  private String projCode;

  private String projName;

  private String packCode;

  private String packName;

  private Date sellEndTime;

  private Date openBidTime;

  private Date bidEndTime;

  private String coCode;

  private String orgCode;

  private String attnName;

  /**
   * @return the snCode
   */
  public String getSnCode() {
    return snCode;
  }

  /**
   * @param snCode the snCode to set
   */
  public void setSnCode(String snCode) {
    this.snCode = snCode;
  }

  /**
   * @return the projCode
   */
  public String getProjCode() {
    return projCode;
  }

  /**
   * @param projCode the projCode to set
   */
  public void setProjCode(String projCode) {
    this.projCode = projCode;
  }

  /**
   * @return the projName
   */
  public String getProjName() {
    return projName;
  }

  /**
   * @param projName the projName to set
   */
  public void setProjName(String projName) {
    this.projName = projName;
  }

  /**
   * @return the packCode
   */
  public String getPackCode() {
    return packCode;
  }

  /**
   * @param packCode the packCode to set
   */
  public void setPackCode(String packCode) {
    this.packCode = packCode;
  }

  /**
   * @return the packName
   */
  public String getPackName() {
    return packName;
  }

  /**
   * @param packName the packName to set
   */
  public void setPackName(String packName) {
    this.packName = packName;
  }

  /**
   * @return the sellEndTime
   */
  public Date getSellEndTime() {
    return sellEndTime;
  }

  /**
   * @param sellEndTime the sellEndTime to set
   */
  public void setSellEndTime(Date sellEndTime) {
    this.sellEndTime = sellEndTime;
  }

  /**
   * @return the openBidTime
   */
  public Date getOpenBidTime() {
    return openBidTime;
  }

  /**
   * @param openBidTime the openBidTime to set
   */
  public void setOpenBidTime(Date openBidTime) {
    this.openBidTime = openBidTime;
  }

  /**
   * @return the bidEndTime
   */
  public Date getBidEndTime() {
    return bidEndTime;
  }

  /**
   * @param bidEndTime the bidEndTime to set
   */
  public void setBidEndTime(Date bidEndTime) {
    this.bidEndTime = bidEndTime;
  }

  /**
   * @return the coCode
   */
  public String getCoCode() {
    return coCode;
  }

  /**
   * @param coCode the coCode to set
   */
  public void setCoCode(String coCode) {
    this.coCode = coCode;
  }

  /**
   * @return the orgCode
   */
  public String getOrgCode() {
    return orgCode;
  }

  /**
   * @param orgCode the orgCode to set
   */
  public void setOrgCode(String orgCode) {
    this.orgCode = orgCode;
  }

  /**
   * @return the attnName
   */
  public String getAttnName() {
    return attnName;
  }

  /**
   * @param attnName the attnName to set
   */
  public void setAttnName(String attnName) {
    this.attnName = attnName;
  }

}
