package com.ufgov.zc.common.zc.model;

import java.io.Serializable;
import java.util.List;

import com.ufgov.zc.common.system.model.AsFile;

public class ZcJingJiaModel implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = -334305812049317060L;

  private String publishDate;

  private String snCode;

  private String year;

  private String month;

  private String day;

  private String maxPrice;

  private String linkMan;

  private String linkTel;

  private String endDate;

  private String tax;

  private String address;

  private String zbFileCode;

  //支付方式
  private String zcPayTypeName;

  private List item;

  private AsFile file;

  public String getPublishDate() {
    return publishDate;
  }

  public void setPublishDate(String publishDate) {
    this.publishDate = publishDate;
  }

  public String getSnCode() {
    return snCode;
  }

  public void setSnCode(String snCode) {
    this.snCode = snCode;
  }

  public String getYear() {
    return year;
  }

  public void setYear(String year) {
    this.year = year;
  }

  public String getMonth() {
    return month;
  }

  public void setMonth(String month) {
    this.month = month;
  }

  public String getDay() {
    return day;
  }

  public void setDay(String day) {
    this.day = day;
  }

  public String getMaxPrice() {
    return maxPrice;
  }

  public void setMaxPrice(String maxPrice) {
    this.maxPrice = maxPrice;
  }

  public String getLinkMan() {
    return linkMan;
  }

  public void setLinkMan(String linkMan) {
    this.linkMan = linkMan;
  }

  public String getLinkTel() {
    return linkTel;
  }

  public void setLinkTel(String linkTel) {
    this.linkTel = linkTel;
  }

  public String getEndDate() {
    return endDate;
  }

  public void setEndDate(String endDate) {
    this.endDate = endDate;
  }

  public String getTax() {
    return tax;
  }

  public void setTax(String tax) {
    this.tax = tax;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getZbFileCode() {
    return zbFileCode;
  }

  public void setZbFileCode(String zbFileCode) {
    this.zbFileCode = zbFileCode;
  }

  public List getItem() {
    return item;
  }

  public void setItem(List item) {
    this.item = item;
  }

  public AsFile getFile() {
    return file;
  }

  public void setFile(AsFile file) {
    this.file = file;
  }

  /**
   * @return the zcPayTypeName
   */
  public String getZcPayTypeName() {
    return zcPayTypeName;
  }

  /**
   * @param zcPayTypeName the zcPayTypeName to set
   */
  public void setZcPayTypeName(String zcPayTypeName) {
    this.zcPayTypeName = zcPayTypeName;
  }

}
