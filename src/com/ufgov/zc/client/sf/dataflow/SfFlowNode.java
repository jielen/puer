/**
 * 
 */
package com.ufgov.zc.client.sf.dataflow;

/**
 * @author Administrator
 *
 */
public class SfFlowNode {

  private String compoId;
  private String text;
  private String toolTip;
  private String icon;
  private ISfFlowNodeBusiness nodeBusiness;
  /**
   * 是否可以存在多条记录
   */
  private boolean isMutliRecord=false;

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public String getToolTip() {
    return toolTip;
  }

  public void setToolTip(String toolTip) {
    this.toolTip = toolTip;
  }

  public String getIcon() {
    return icon;
  }

  public void setIcon(String icon) {
    this.icon = icon;
  }


  public String getCompoId() {
    return compoId;
  }

  public void setCompoId(String compoId) {
    this.compoId = compoId;
  }

  public ISfFlowNodeBusiness getNodeBusiness() {
    return nodeBusiness;
  }

  public void setNodeBusiness(ISfFlowNodeBusiness nodeBusiness) {
    this.nodeBusiness = nodeBusiness;
  }

  public boolean isMutliRecord() {
    return isMutliRecord;
  }

  public void setMutliRecord(boolean isMutliRecord) {
    this.isMutliRecord = isMutliRecord;
  }
  
  
}
