package com.ufgov.zc.common.zc.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ufgov.zc.common.commonbiz.model.Company;

public class ZcQx extends ZcBaseBill {



   /**
   * 
   */
  private static final long serialVersionUID = 3037218273644454458L;
  
  private String qxCode;
   private String qxName;
   private String excutor;
   private String excutorName;
   private Date inputDate;
   private String supplier;
   private String supplierName;
   private String suBankAccount;
   private String suBank;
   private String suBankShoukuanren;   
   private BigDecimal qxSum;
   private String remark;
   private String status;
   private BigDecimal zheRangLv;
   private String suLinkMan;
   private String suLinkTel;
   private Date wxDate;
   private BigDecimal realQxSum;
   private String coLinkMan;
   private BigDecimal biSum;
   
   private String detailModelFileName;
   private String detailModelFileId;
   
     
   private List biList=new ArrayList();
   private List ItemList=new ArrayList();
   

   
   public String getDetailModelFileName() {
    return detailModelFileName;
  }
  public void setDetailModelFileName(String detailModelFileName) {
    this.detailModelFileName = detailModelFileName;
  }
  public String getDetailModelFileId() {
    return detailModelFileId;
  }
  public void setDetailModelFileId(String detailModelFileId) {
    this.detailModelFileId = detailModelFileId;
  }
  
  public String getExcutor() {
    return excutor;
  }
  public void setExcutor(String excutor) {
    this.excutor = excutor;
  }
  public String getExcutorName() {
    return excutorName;
  }
  public void setExcutorName(String excutorName) {
    this.excutorName = excutorName;
  }
  public Date getInputDate() {
    return inputDate;
  }
  public void setInputDate(Date inputDate) {
    this.inputDate = inputDate;
  }
  public String getSupplier() {
    return supplier;
  }
  public void setSupplier(String supplier) {
    this.supplier = supplier;
  }
  public String getSupplierName() {
    return supplierName;
  }
  public void setSupplierName(String supplierName) {
    this.supplierName = supplierName;
  }
  public String getSuBankAccount() {
    return suBankAccount;
  }
  public void setSuBankAccount(String suBankAccount) {
    this.suBankAccount = suBankAccount;
  }
  public String getSuBank() {
    return suBank;
  }
  public void setSuBank(String suBank) {
    this.suBank = suBank;
  }
  public String getSuBankShoukuanren() {
    return suBankShoukuanren;
  }
  public void setSuBankShoukuanren(String suBankShoukuanren) {
    this.suBankShoukuanren = suBankShoukuanren;
  }
  public String getRemark() {
    return remark;
  }
  public void setRemark(String remark) {
    this.remark = remark;
  }
  public String getStatus() {
    return status;
  }
  public void setStatus(String status) {
    this.status = status;
  }
  public List getBiList() {
    return biList;
  }
  public void setBiList(List biList) {
    this.biList = biList;
  }
  public List getItemList() {
    return ItemList;
  }
  public void setItemList(List itemList) {
    ItemList = itemList;
  }
  public BigDecimal getZheRangLv() {
    return zheRangLv;
  }
  public void setZheRangLv(BigDecimal zheRangLv) {
    this.zheRangLv = zheRangLv;
  }
  public String getQxName() {
    return qxName;
  }
  public void setQxName(String qxName) {
    this.qxName = qxName;
  }
  public String getQxCode() {
    return qxCode;
  }
  public void setQxCode(String qxCode) {
    this.qxCode = qxCode;
  }
  public BigDecimal getQxSum() {
    return qxSum;
  }
  public void setQxSum(BigDecimal qxSum) {
    this.qxSum = qxSum;
  }
  public String getSuLinkTel() {
    return suLinkTel;
  }
  public void setSuLinkTel(String suLinkTel) {
    this.suLinkTel = suLinkTel;
  }
  public String getSuLinkMan() {
    return suLinkMan;
  }
  public void setSuLinkMan(String suLinkMan) {
    this.suLinkMan = suLinkMan;
  }
  public Date getWxDate() {
    return wxDate;
  }
  public void setWxDate(Date wxDate) {
    this.wxDate = wxDate;
  }
  public BigDecimal getRealQxSum() {
    return realQxSum;
  }
  public void setRealQxSum(BigDecimal realQxSum) {
    this.realQxSum = realQxSum;
  }
  public String getCoLinkMan() {
    return coLinkMan;
  }
  public void setCoLinkMan(String coLinkMan) {
    this.coLinkMan = coLinkMan;
  }
  public BigDecimal getBiSum() {
    return biSum;
  }
  public void setBiSum(BigDecimal biSum) {
    this.biSum = biSum;
  }
   
}
