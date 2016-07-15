package com.ufgov.zc.common.commonbiz.fieldmap;

import java.util.HashMap;
import java.util.Map;

public class SfReceiptFM {

  public static Map fieldMap = new HashMap();

  static {

    fieldMap.putAll(ZcBaseBillFM.fieldMap);

    fieldMap.put("ENTRUST_CODE","entrustCode");
    fieldMap.put("ENTRUST_ID","entrustId");
    fieldMap.put("INPUTOR","inputor");
    fieldMap.put("INPUT_DATE","inputDate");
    fieldMap.put("NAME","name");
    fieldMap.put("ND","nd");
    fieldMap.put("PROCESS_INST_ID","processInstId");
    fieldMap.put("RECEIPT_ID","receiptId");
    fieldMap.put("RECEIPT_TYPE","receiptType");
    fieldMap.put("REMARK","remark");
    fieldMap.put("STATUS","status");
    fieldMap.put("FILE_ID","fileId");

  }

}
