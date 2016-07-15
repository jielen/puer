package com.ufgov.zc.common.zc.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ZcEbEntrust extends ZcBaseBill implements Serializable {
  /**
   * 
   */
  private static final long serialVersionUID = -1893493724571212830L;

  public static final String STATUS_DRAFT = "draft";

  public static final String STATUS_WAIT_SEND = "0";

  public static final String STATUS_WAIT_ACCEPT = "1";

  public static final String STATUS_ACCEPTED = "2";

  public static final String STATUS_UNACCEPT = "3";

  public static final String SEQ_NAME = "ZcEbUtil.getEntrustNextSeqVal";

  private String sn;

  private String zcMakeCode;

  private String zcMakeName;

  private String zcMakeLinkman;

  private String zcMakeTel;

  private String status;

  private String zcInputCode;

  private Date zcInputDate;

  private BigDecimal zcMoneyBiSum;

  //采购单位申报的总计划金额。
  private BigDecimal zcMakeSum;

  private String zcPifuCgfs;

  private String zcDiyuDaima;

  private Date zcWeitoDate;

  private String zcIsNotary;

  private String zcIsImp;

  private String zcImpFile;

  private String zcImpFileBlobid;

  private String untreadReason;

  private String leaderId;

  private String leaderTel;

  private List detailList = new ArrayList();

  private List biList = new ArrayList();

  private String remark;

  private String snCode;//中心任务单编号

  private String isPub;

  private String isDesSup;

  //采购类型
  private String zcFukuanType;

  //可用预算
  private BigDecimal useBudget;

  private String isCar;

  public String getIsCar() {
    return isCar;
  }

  public void setIsCar(String isCar) {
    this.isCar = isCar;
  }

  public BigDecimal getUseBudget() {
    return useBudget;
  }

  public void setUseBudget(BigDecimal useBudget) {
    this.useBudget = useBudget;
  }

  public String getIsPub() {
    return isPub;
  }

  public void setIsPub(String isPub) {
    this.isPub = isPub;
  }

  public String getIsDesSup() {
    return isDesSup;
  }

  public void setIsDesSup(String isDesSup) {
    this.isDesSup = isDesSup;
  }

  public String getOrgCode() {
    return orgCode;
  }

  public void setOrgCode(String orgCode) {
    this.orgCode = orgCode;
  }

  public List getDetailList() {
    return detailList;
  }

  public void setDetailList(List detailList) {
    this.detailList = detailList;
  }

  public String getSn() {
    return sn;
  }

  public void setSn(String sn) {
    this.sn = sn;
    this.setTitleField(sn);
  }

  public String getZcMakeCode() {
    return zcMakeCode;
  }

  public void setZcMakeCode(String zcMakeCode) {
    this.zcMakeCode = zcMakeCode;
  }

  public String getZcMakeName() {
    return zcMakeName;
  }

  public void setZcMakeName(String zcMakeName) {
    this.zcMakeName = zcMakeName;
  }

  public String getZcMakeLinkman() {
    return zcMakeLinkman;
  }

  public void setZcMakeLinkman(String zcMakeLinkman) {
    this.zcMakeLinkman = zcMakeLinkman;
  }

  public String getZcMakeTel() {
    return zcMakeTel;
  }

  public void setZcMakeTel(String zcMakeTel) {
    this.zcMakeTel = zcMakeTel;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getZcInputCode() {
    return zcInputCode;
  }

  public void setZcInputCode(String zcInputCode) {
    this.zcInputCode = zcInputCode;
  }

  public Date getZcInputDate() {
    return zcInputDate;
  }

  public void setZcInputDate(Date zcInputDate) {
    this.zcInputDate = zcInputDate;
  }

  public BigDecimal getZcMoneyBiSum() {
    return zcMoneyBiSum;
  }

  public void setZcMoneyBiSum(BigDecimal zcMoneyBiSum) {
    this.zcMoneyBiSum = zcMoneyBiSum;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public String getZcPifuCgfs() {
    return zcPifuCgfs;
  }

  public void setZcPifuCgfs(String zcPifuCgfs) {
    this.zcPifuCgfs = zcPifuCgfs;
  }

  public String getZcDiyuDaima() {
    return zcDiyuDaima;
  }

  public void setZcDiyuDaima(String zcDiyuDaima) {
    this.zcDiyuDaima = zcDiyuDaima;
  }

  public String getZcIsNotary() {
    return zcIsNotary;
  }

  public void setZcIsNotary(String zcIsNotary) {
    this.zcIsNotary = zcIsNotary;
  }

  public String getZcIsImp() {
    return zcIsImp;
  }

  public void setZcIsImp(String zcIsImp) {
    this.zcIsImp = zcIsImp;
  }

  public String getZcImpFile() {
    return zcImpFile;
  }

  public void setZcImpFile(String zcImpFile) {
    this.zcImpFile = zcImpFile;
  }

  public String getZcImpFileBlobid() {
    return zcImpFileBlobid;
  }

  public void setZcImpFileBlobid(String zcImpFileBlobid) {
    this.zcImpFileBlobid = zcImpFileBlobid;
  }

  public String getUntreadReason() {
    return untreadReason;
  }

  public void setUntreadReason(String untreadReason) {
    this.untreadReason = untreadReason;
  }

  public String getLeaderId() {
    return leaderId;
  }

  public void setLeaderId(String leaderId) {
    this.leaderId = leaderId;
  }

  public Date getZcWeitoDate() {
    return zcWeitoDate;
  }

  public void setZcWeitoDate(Date zcWeitoDate) {
    this.zcWeitoDate = zcWeitoDate;
  }

  public String getLeaderTel() {
    return leaderTel;
  }

  public void setLeaderTel(String leaderTel) {
    this.leaderTel = leaderTel;
  }

  public void caculateAllItemSum() {
    // TCJLODO Auto-generated method stub
    if (this.detailList == null)
      return;
    this.zcMoneyBiSum = new BigDecimal(0);
    for (int i = 0; i < this.detailList.size(); i++) {
      ZcEbEntrustDetail d = (ZcEbEntrustDetail) this.detailList.get(i);
      this.zcMoneyBiSum = this.zcMoneyBiSum.add(d.getZcItemSum() == null ? new BigDecimal(0) : d.getZcItemSum());
    }
  }

  public BigDecimal getZcMakeSum() {
    return zcMakeSum;
  }

  /**
   * @param zcMakeSum the zcMakeSum to set
   */
  public void setZcMakeSum(BigDecimal zcMakeSum) {
    this.zcMakeSum = zcMakeSum;
  }

  /**
   * @return the biList
   */
  public List getBiList() {
    return biList;
  }

  /**
   * @param biList the biList to set
   */
  public void setBiList(List biList) {
    this.biList = biList;
  }

  public String getSnCode() {
    return snCode;
  }

  public void setSnCode(String snCode) {
    this.snCode = snCode;
  }

  /**
   * @return the zcFukuanType
   */
  public String getZcFukuanType() {
    return zcFukuanType;
  }

  /**
   * @param zcFukuanType the zcFukuanType to set
   */
  public void setZcFukuanType(String zcFukuanType) {
    this.zcFukuanType = zcFukuanType;
  }

}
