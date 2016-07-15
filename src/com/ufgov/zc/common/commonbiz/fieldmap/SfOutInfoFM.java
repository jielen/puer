package com.ufgov.zc.common.commonbiz.fieldmap;

import java.util.HashMap;
import java.util.Map;

public class SfOutInfoFM {

  public static Map fieldMap = new HashMap();

  static {

    fieldMap.putAll(ZcBaseBillFM.fieldMap);

    fieldMap.put("ACCEPTOR","acceptor");
    fieldMap.put("ACCEPT_DATE","acceptDate");
    fieldMap.put("ENTRUST_CODE","entrustCode");
    fieldMap.put("ENTRUST_ID","entrustId");
    fieldMap.put("INPUTOR","inputor");
    fieldMap.put("INPUT_DATE","inputDate");
    fieldMap.put("NAME","name");
    fieldMap.put("ND","nd");
    fieldMap.put("OUT_INFO_ID","outInfoId");
    fieldMap.put("PROCESS_INST_ID","processInstId");
    fieldMap.put("STATUS","status");
    fieldMap.put("TGF","tgf");
    fieldMap.put("TGF_PHONE","tgfPhone");
    fieldMap.put("VALIDATOR","validator");
    fieldMap.put("VALIDAT_DATE","validatDate");
    fieldMap.put("VALIDAT_IS_PASS","validatIsPass");
    fieldMap.put("VALIDAT_OPINION","validatOpinion");

  }
}
