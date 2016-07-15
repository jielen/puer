package com.ufgov.zc.common.zc.model;

import java.io.Serializable;
import java.util.Date;

public class WorkFlowAction implements Serializable {

  private String snCode;//任务单编号

  private String templatName;

  private String actionName;

  private String nodeId;

  private String nodeName;

  private Date executeTime;

  private String excutor;

  private String userName;

  private String description;

  private String instanceId;

  /**
   * @return the snCode
   */
  public String getSnCode() {
    return snCode;
  }

  /**
   * @param snCode the snCode to set
   */
  public void setSnCode(String snCode) {
    this.snCode = snCode;
  }

  /**
   * @return the templatName
   */
  public String getTemplatName() {
    return templatName;
  }

  /**
   * @param templatName the templatName to set
   */
  public void setTemplatName(String templatName) {
    this.templatName = templatName;
  }

  /**
   * @return the actionName
   */
  public String getActionName() {
    return actionName;
  }

  /**
   * @param actionName the actionName to set
   */
  public void setActionName(String actionName) {
    this.actionName = actionName;
  }

  /**
   * @return the nodeId
   */
  public String getNodeId() {
    return nodeId;
  }

  /**
   * @param nodeId the nodeId to set
   */
  public void setNodeId(String nodeId) {
    this.nodeId = nodeId;
  }

  /**
   * @return the nodeName
   */
  public String getNodeName() {
    return nodeName;
  }

  /**
   * @param nodeName the nodeName to set
   */
  public void setNodeName(String nodeName) {
    this.nodeName = nodeName;
  }

  /**
   * @return the executeTime
   */
  public Date getExecuteTime() {
    return executeTime;
  }

  /**
   * @param executeTime the executeTime to set
   */
  public void setExecuteTime(Date executeTime) {
    this.executeTime = executeTime;
  }

  /**
   * @return the excutor
   */
  public String getExcutor() {
    return excutor;
  }

  /**
   * @param excutor the excutor to set
   */
  public void setExcutor(String excutor) {
    this.excutor = excutor;
  }

  /**
   * @return the userName
   */
  public String getUserName() {
    return userName;
  }

  /**
   * @param userName the userName to set
   */
  public void setUserName(String userName) {
    this.userName = userName;
  }

  /**
   * @return the description
   */
  public String getDescription() {
    return description;
  }

  /**
   * @param description the description to set
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * @return the instanceId
   */
  public String getInstanceId() {
    return instanceId;
  }

  /**
   * @param instanceId the instanceId to set
   */
  public void setInstanceId(String instanceId) {
    this.instanceId = instanceId;
  }

}
