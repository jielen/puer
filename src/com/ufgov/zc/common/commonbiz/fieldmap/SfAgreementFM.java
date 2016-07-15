package com.ufgov.zc.common.commonbiz.fieldmap;

import java.util.HashMap;
import java.util.Map;

public class SfAgreementFM {

  public static Map fieldMap = new HashMap();

  static {

    fieldMap.putAll(ZcBaseBillFM.fieldMap);
    
    fieldMap.put("AGREEMENT_ID","agreementId");
    fieldMap.put("ENTRUST_CODE","entrustCode");
    fieldMap.put("ENTRUST_ID","entrustId");
    fieldMap.put("FILE_ID","fileId");
    fieldMap.put("INPUTOR","inputor");
    fieldMap.put("INPUT_DATE","inputDate");
    fieldMap.put("NAME","name");
    fieldMap.put("ND","nd");
    fieldMap.put("REMARK","remark");
    fieldMap.put("STATUS","status");


  }

}
