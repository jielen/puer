package com.ufgov.zc.common.zc.model;

import java.io.Serializable;

public class ZcEbSupQualificationLev implements Serializable {

  private String id;

  private String name;

  private String qualifId;

  private String lev;

  private String tempId;

  public String getTempId() {
    return tempId;
  }

  public void setTempId(String tempId) {
    this.tempId = tempId;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getQualifId() {
    return qualifId;
  }

  public void setQualifId(String qualifId) {
    this.qualifId = qualifId;
  }

  public String getLev() {
    return lev;
  }

  public void setLev(String lev) {
    this.lev = lev;
  }

}
