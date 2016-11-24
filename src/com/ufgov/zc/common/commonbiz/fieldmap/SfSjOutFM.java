package com.ufgov.zc.common.commonbiz.fieldmap;

import java.util.HashMap;
import java.util.Map;

public class SfSjOutFM {
  public static Map fieldMap = new HashMap();

  static {

    fieldMap.putAll(ZcBaseBillFM.fieldMap);
    
    fieldMap.put("AMOUNT","amount");
    fieldMap.put("CO_CODE","coCode");
    fieldMap.put("INPUTOR","inputor");
    fieldMap.put("INPUT_DATE","inputDate");
    fieldMap.put("IN_ID","sjin.inId");
    fieldMap.put("SJ_NAME","sjin.sj.name");
    fieldMap.put("LOSS_REASON","lossReason");
    fieldMap.put("LOSS_TIME","lossTime");
    fieldMap.put("ND","nd");
    fieldMap.put("OUT_BILL_CODE","outBillCode");
    fieldMap.put("OUT_DATE","outDate");
    fieldMap.put("OUT_ID","outId");
    fieldMap.put("OUT_TYPE","outType"); 
    fieldMap.put("PROPOSER","proposer");
    fieldMap.put("PROPOSER_NAME","proposerName");
    fieldMap.put("PROPOSER_DEPT","proposerDept");
    fieldMap.put("REMARK","remark");
    fieldMap.put("STATUS","status");


  }
}
