package com.ufgov.zc.common.commonbiz.fieldmap;

import java.util.HashMap;
import java.util.Map;

public class SfJdResultFM {
  public static Map fieldMap = new HashMap();

  static {

    fieldMap.putAll(ZcBaseBillFM.fieldMap);

    fieldMap.put("BRIEF", "brief");
    fieldMap.put("ENTRUST_CODE", "entrustCode");
    fieldMap.put("ENTRUST_ID", "entrustId");
    fieldMap.put("FILE_ID", "fileId");
    fieldMap.put("INPUTOR", "inputor");
    fieldMap.put("INPUT_DATE", "inputDate");
    fieldMap.put("JD_ADDRESS", "jdAddress");
    fieldMap.put("JD_DATE", "jdDate");
    fieldMap.put("JD_OPINION", "jdOpinion");
    fieldMap.put("JD_PROCESS", "jdProcess");
    fieldMap.put("JD_RESULT", "jdResult");
    fieldMap.put("JD_RESULT_ID", "jdResultId");
    fieldMap.put("NAME", "name");
    fieldMap.put("ND", "nd");
    fieldMap.put("PROCESS_INST_ID", "processInstId");
    fieldMap.put("REMARK", "remark");
    fieldMap.put("RESULT_TYPE", "resultType");
    fieldMap.put("STATUS", "status");
    fieldMap.put("ZC_PERSONS", "zcPersons");
    fieldMap.put("JDR", "jdr");
    fieldMap.put("ZHU_SU", "zhuSu");
    fieldMap.put("JD_METHOD", "jdMethod");
    fieldMap.put("JD_TARGET", "jdTarget");

  }
}
