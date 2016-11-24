package com.ufgov.zc.common.sf.model;

import java.math.BigDecimal;

import com.ufgov.zc.common.zc.model.ZcBaseBill;

public class SfSjGroup extends ZcBaseBill {
  
  /**
   * 
   */
  private static final long serialVersionUID = -2626805573165983729L;
  
  public static final String COL_CO_CODE="SF_SJ_GROUP_CO_CODE"; // 鉴定机构
  public static final String COL_GROUP_ID="SF_SJ_GROUP_GROUP_ID"; // 组别ID
  public static final String COL_GROUP_NAME="SF_SJ_GROUP_GROUP_NAME"; // 组别名称

  

  public static final String SEQ_SF_SJ_GROUP_ID="SEQ_SF_SJ_GROUP_ID";
  
  public static final String TAB_ID_SF_SJ_GROUP="SfSjGroup_Tab";
  
    private BigDecimal groupId;

    private String groupName;
 

    public BigDecimal getGroupId() {
        return groupId;
    }

    public void setGroupId(BigDecimal groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
 
}