package com.ufgov.zc.common.commonbiz.fieldmap;

import java.util.HashMap;
import java.util.Map;

public class SfMaterialsTransferFM {

  public static Map fieldMap = new HashMap();

  static {

    fieldMap.putAll(ZcBaseBillFM.fieldMap);
    
    fieldMap.put("CL_QUALITY","clQuality");
    fieldMap.put("COMPLETION_DESC","completionDesc");
    fieldMap.put("ENTRUST_CODE","entrustCode");
    fieldMap.put("ENTRUST_ID","entrustId");
    fieldMap.put("JIE_SHOU_DATE","jieShouDate");
    fieldMap.put("JIE_SHOU_DESC","jieShouDesc");
    fieldMap.put("JIE_SHOU_REN","jieShouRen");
    fieldMap.put("MATERIALS_DESC","materialsDesc");
    fieldMap.put("NAME","name");
    fieldMap.put("ND","nd");
    fieldMap.put("PARENT_ID","parentId");
    fieldMap.put("PROCESS_INST_ID","processInstId");
    fieldMap.put("REMARK","remark");
    fieldMap.put("STATUS","status");
    fieldMap.put("TRANSFER_ID","transferId");
    fieldMap.put("YB_QUALITY","ybQuality");
    fieldMap.put("YI_JIAO_DATE","yiJiaoDate");
    fieldMap.put("YI_JIAO_DESC","yiJiaoDesc");
    fieldMap.put("YI_JIAO_REN","yiJiaoRen");


  }
}
