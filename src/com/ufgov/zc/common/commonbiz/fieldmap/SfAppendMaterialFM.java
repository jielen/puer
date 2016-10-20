package com.ufgov.zc.common.commonbiz.fieldmap;

import java.util.HashMap;
import java.util.Map;

public class SfAppendMaterialFM {

  public static Map fieldMap = new HashMap();

  static {

    fieldMap.putAll(ZcBaseBillFM.fieldMap);

    fieldMap.put("ACCEPTOR","acceptor");
    fieldMap.put("ACCEPT_DATE","acceptDate");
    fieldMap.put("APPEND_MATERIAL_ID","appendMaterialId");
    fieldMap.put("CO_CODE","coCode");
    fieldMap.put("ENTRUST_CODE","entrustCode");
    fieldMap.put("ENTRUST_ID","entrustId");
    fieldMap.put("INPUTOR","inputor");
    fieldMap.put("INPUT_DATE","inputDate");
    fieldMap.put("NAME","name");
    fieldMap.put("ND","nd");
    fieldMap.put("PROCESS_INST_ID","processInstId");
    fieldMap.put("REMARK","remark");
    fieldMap.put("SJR","sjr");
    fieldMap.put("SJR_TEL","sjrTel");
    fieldMap.put("SJR_ZJ_CODE","sjrZjCode");
    fieldMap.put("SJR_ZJ_TYPE","sjrZjType");
    fieldMap.put("STATUS","status");
    fieldMap.put("VALIDATOR","validator");
    fieldMap.put("VALIDAT_DATE","validatDate");
    fieldMap.put("VALIDAT_IS_PASS","validatIsPass");
    fieldMap.put("VALIDAT_OPINION","validatOpinion");


  }

}
