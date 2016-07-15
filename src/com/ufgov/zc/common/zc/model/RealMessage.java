package com.ufgov.zc.common.zc.model;

import java.io.Serializable;

public class RealMessage implements Serializable {
  private String id;

  private String type;

  private String body;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public RealMessage() {
  }
}
