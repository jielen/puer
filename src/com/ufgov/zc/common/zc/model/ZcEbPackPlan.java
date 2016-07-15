package com.ufgov.zc.common.zc.model;

import java.io.Serializable;
import java.util.Date;

public class ZcEbPackPlan extends ZcBaseBill implements Serializable {

  private String projCode;

  private String packCode;

  private Date bidEndTime;

  private Date openBidTime;

  private Date sellEndTime;

  public Date getSellEndTime() {
    return sellEndTime;
  }

  public void setSellEndTime(Date sellEndTime) {
    this.sellEndTime = sellEndTime;
  }

  public String getProjCode() {
    return projCode;
  }

  public void setProjCode(String projCode) {
    this.projCode = projCode;
  }

  public String getPackCode() {
    return packCode;
  }

  public void setPackCode(String packCode) {
    this.packCode = packCode;
  }

  public Date getBidEndTime() {
    return bidEndTime;
  }

  public void setBidEndTime(Date bidEndTime) {
    this.bidEndTime = bidEndTime;
  }

  public Date getOpenBidTime() {
    return openBidTime;
  }

  public void setOpenBidTime(Date openBidTime) {
    this.openBidTime = openBidTime;
  }

}
