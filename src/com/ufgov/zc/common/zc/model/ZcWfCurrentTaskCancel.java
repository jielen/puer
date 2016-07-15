package com.ufgov.zc.common.zc.model;

import java.io.Serializable;

public class ZcWfCurrentTaskCancel implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = -3125029505990586610L;

  private String entrustCancelId;

  private String tblName;

  private String keyColName;

  private String statusCol;

  private String status;

  private int currentTaskId;

  private String instanceId;

  private int nodeId;

  private String executor;

  private int delegationId;

  private String owner;

  private String creator;

  private String createTime;

  private String limitExecuteTime;

  private int responsibility;

  private int parentTaskId;

  private String gkSendStatus;
 
  private String granterInfo;
 
  private String granterId;

  private String mqSmsStatus;

  public String getKeyColName() {
    return keyColName;
  }

  public void setKeyColName(String keyColName) {
    this.keyColName = keyColName;
  }

  public String getEntrustCancelId() {
    return entrustCancelId;
  }

  public void setEntrustCancelId(String entrustCancelId) {
    this.entrustCancelId = entrustCancelId;
  }

  public String getTblName() {
    return tblName;
  }

  public void setTblName(String tblName) {
    this.tblName = tblName;
  }

  public String getStatusCol() {
    return statusCol;
  }

  public void setStatusCol(String statusCol) {
    this.statusCol = statusCol;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public int getCurrentTaskId() {
    return currentTaskId;
  }

  public void setCurrentTaskId(int currentTaskId) {
    this.currentTaskId = currentTaskId;
  }

  public String getInstanceId() {
    return instanceId;
  }

  public void setInstanceId(String instanceId) {
    this.instanceId = instanceId;
  }

  public int getNodeId() {
    return nodeId;
  }

  public void setNodeId(int nodeId) {
    this.nodeId = nodeId;
  }

  public String getExecutor() {
    return executor;
  }

  public void setExecutor(String executor) {
    this.executor = executor;
  }

  public int getDelegationId() {
    return delegationId;
  }

  public void setDelegationId(int delegationId) {
    this.delegationId = delegationId;
  }

  public String getOwner() {
    return owner;
  }

  public void setOwner(String owner) {
    this.owner = owner;
  }

  public String getCreator() {
    return creator;
  }

  public void setCreator(String creator) {
    this.creator = creator;
  }

  public String getCreateTime() {
    return createTime;
  }

  public void setCreateTime(String createTime) {
    this.createTime = createTime;
  }

  public String getLimitExecuteTime() {
    return limitExecuteTime;
  }

  public void setLimitExecuteTime(String limitExecuteTime) {
    this.limitExecuteTime = limitExecuteTime;
  }

  public int getResponsibility() {
    return responsibility;
  }

  public void setResponsibility(int responsibility) {
    this.responsibility = responsibility;
  }

  public int getParentTaskId() {
    return parentTaskId;
  }

  public void setParentTaskId(int parentTaskId) {
    this.parentTaskId = parentTaskId;
  }

  public String getGkSendStatus() {
    return gkSendStatus;
  }

  public void setGkSendStatus(String gkSendStatus) {
    this.gkSendStatus = gkSendStatus;
  }

  public String getGranterInfo() {
    return granterInfo;
  }

  public void setGranterInfo(String granterInfo) {
    this.granterInfo = granterInfo;
  }

  public String getGranterId() {
    return granterId;
  }

  public void setGranterId(String granterId) {
    this.granterId = granterId;
  }

  public String getMqSmsStatus() {
    return mqSmsStatus;
  }

  public void setMqSmsStatus(String mqSmsStatus) {
    this.mqSmsStatus = mqSmsStatus;
  }

}
