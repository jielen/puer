package com.ufgov.zc.common.commonbiz.fieldmap;

import java.util.HashMap;
import java.util.Map;

public class SfJdDocAuditFM {

  public static Map fieldMap = new HashMap();

  static {

    fieldMap.putAll(ZcBaseBillFM.fieldMap);

    fieldMap.put("DOC_QUATITY","docQuatity");
    fieldMap.put("ENTRUST_CODE","entrustCode");
    fieldMap.put("ENTRUST_ID","entrustId");
    fieldMap.put("INPUTOR","inputor");
    fieldMap.put("INPUT_DATE","inputDate");
    fieldMap.put("JD_DOC_AUDIT_ID","jdDocAuditId");
    fieldMap.put("NAME","name");
    fieldMap.put("ND","nd");
    fieldMap.put("PHOTOGRAPHER","photographer");
    fieldMap.put("PROCESS_INST_ID","processInstId");
    fieldMap.put("REMARK","remark");
    fieldMap.put("REPORT_TYPE","reportType");
    fieldMap.put("STATUS","status");
    fieldMap.put("WTF","wtf");

  }
}
