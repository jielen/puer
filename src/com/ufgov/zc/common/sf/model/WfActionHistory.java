package com.ufgov.zc.common.sf.model;

import java.math.BigDecimal;

import com.ufgov.zc.common.zc.model.ZcBaseBill;

public class WfActionHistory extends ZcBaseBill {
    private BigDecimal actionHistoryId;

	private BigDecimal instanceId;

	private BigDecimal nodeId;

	private String actionName;

	private String executor;

	private String executeTime;

	private String description;

	private String owner;

	private String limitExecuteTime;

  public BigDecimal getActionHistoryId() {
    return actionHistoryId;
  }

  public void setActionHistoryId(BigDecimal actionHistoryId) {
    this.actionHistoryId = actionHistoryId;
  }

  public BigDecimal getInstanceId() {
    return instanceId;
  }

  public void setInstanceId(BigDecimal instanceId) {
    this.instanceId = instanceId;
  }

  public BigDecimal getNodeId() {
    return nodeId;
  }

  public void setNodeId(BigDecimal nodeId) {
    this.nodeId = nodeId;
  }

  public String getActionName() {
    return actionName;
  }

  public void setActionName(String actionName) {
    this.actionName = actionName;
  }

  public String getExecutor() {
    return executor;
  }

  public void setExecutor(String executor) {
    this.executor = executor;
  }

  public String getExecuteTime() {
    return executeTime;
  }

  public void setExecuteTime(String executeTime) {
    this.executeTime = executeTime;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getOwner() {
    return owner;
  }

  public void setOwner(String owner) {
    this.owner = owner;
  }

  public String getLimitExecuteTime() {
    return limitExecuteTime;
  }

  public void setLimitExecuteTime(String limitExecuteTime) {
    this.limitExecuteTime = limitExecuteTime;
  } 
}