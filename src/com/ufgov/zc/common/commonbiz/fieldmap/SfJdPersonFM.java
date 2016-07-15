package com.ufgov.zc.common.commonbiz.fieldmap;

import java.util.HashMap;
import java.util.Map;

public class SfJdPersonFM {

  public static Map fieldMap = new HashMap();

  static {

    fieldMap.putAll(ZcBaseBillFM.fieldMap);

    fieldMap.put("ACCOUNT","account");
    fieldMap.put("BIRTHDAY","birthday");
    fieldMap.put("CERTIFICATE_NO","certificateNo");
    fieldMap.put("JD_PERSON_ID","jdPersonId");
    fieldMap.put("NAME","name");
    fieldMap.put("PASSWORD","password");
    fieldMap.put("REMARK","remark");
    fieldMap.put("SEX","sex");
    fieldMap.put("STATUS","status");
    fieldMap.put("TECH_TITLE","techTitle");
  }
}
