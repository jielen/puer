package com.ufgov.zc.common.commonbiz.fieldmap;

import java.util.HashMap;
import java.util.Map;

public class SfSjGroupFM {

  public static Map fieldMap = new HashMap();

  static {

    fieldMap.putAll(ZcBaseBillFM.fieldMap);
    
    fieldMap.put("CO_CODE","coCode"); 
    fieldMap.put("GROUP_ID","groupId");  
    fieldMap.put("GROUP_NAME","groupName");  

  }
}
