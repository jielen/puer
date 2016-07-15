package com.ufgov.zc.common.zc.model;

import java.io.Serializable;
import java.util.Date;

/**
 * The persistent class for the ZC_EXPERT_QUESTION_CONTENT database table.
 * 
 */

public class ZcExpertQuestionContent implements Serializable {
  private static final long serialVersionUID = 1L;

  private long id;

  private long dialogueId;

  private String sendContent;

  private Date sendTime;

  private String sender;

  private ZcExpertQuestion zcExpertQuestion;

  public ZcExpertQuestionContent() {
  }

  public long getId() {
    return this.id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getDialogueId() {
    return dialogueId;
  }

  public void setDialogueId(long dialogueId) {
    this.dialogueId = dialogueId;
  }

  public String getSendContent() {
    return this.sendContent;
  }

  public void setSendContent(String sendContent) {
    this.sendContent = sendContent;
  }

  public Date getSendTime() {
    return this.sendTime;
  }

  public void setSendTime(Date sendTime) {
    this.sendTime = sendTime;
  }

  public String getSender() {
    return this.sender;
  }

  public void setSender(String sender) {
    this.sender = sender;
  }

  public ZcExpertQuestion getZcExpertQuestion() {
    return this.zcExpertQuestion;
  }

  public void setZcExpertQuestion(ZcExpertQuestion zcExpertQuestion) {
    this.zcExpertQuestion = zcExpertQuestion;
  }

}