package com.ufgov.zc.common.sf.model;

import java.math.BigDecimal;
import java.util.Date;

import com.ufgov.zc.common.zc.model.ZcBaseBill;

public class SfZongheZhiban extends ZcBaseBill{
    /**
   * 
   */
  private static final long serialVersionUID = -1438837437022183394L;
  
  public static final String SEQ_SF_ZONGHE_ZHIBAN_ID="SEQ_SF_ZONGHE_ZHIBAN_ID";

    private BigDecimal zhibanId;

    private String mac;

    private String userId; 

    private Date startTime;

    private Date endTime;

    private String ip;

    public BigDecimal getZhibanId() {
        return zhibanId;
    }

    public void setZhibanId(BigDecimal zhibanId) {
        this.zhibanId = zhibanId;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    } 
    

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}