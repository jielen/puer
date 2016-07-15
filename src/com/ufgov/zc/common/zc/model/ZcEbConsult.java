package com.ufgov.zc.common.zc.model;

import java.io.Serializable;
import java.util.Date;

public class ZcEbConsult extends ZcBaseBill implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 5930619481596872368L;

  private String title;

  private String status;

  private String type;

  private String problem;

  private String reply;

  private Date replyDate;

  private String replier;

  private String replierName;

  public String getReplier() {
    return replier;
  }

  public void setReplier(String replier) {
    this.replier = replier;
  }

  public String getReplierName() {
    return replierName;
  }

  public void setReplierName(String replierName) {
    this.replierName = replierName;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getProblem() {
    return problem;
  }

  public void setProblem(String problem) {
    this.problem = problem;
  }

  public String getReply() {
    return reply;
  }

  public void setReply(String reply) {
    this.reply = reply;
  }

  public Date getReplyDate() {
    return replyDate;
  }

  public void setReplyDate(Date replyDate) {
    this.replyDate = replyDate;
  }

}
