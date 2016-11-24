package com.ufgov.zc.common.commonbiz.fieldmap;

import java.util.HashMap;
import java.util.Map;

public class SfDocSendFM {


  public static Map fieldMap = new HashMap();

  static {

    fieldMap.putAll(ZcBaseBillFM.fieldMap);

    fieldMap.put("CO_CODE","coCode");
    fieldMap.put("ENTRUST_CODE","entrust.code");
    fieldMap.put("ENTRUST_ID","entrust.entrustId");
    fieldMap.put("JD_DOC_AUDIT_ID","jdDocAuditId");
    fieldMap.put("ND","nd");
    fieldMap.put("RECIEVOR","recievor");
    fieldMap.put("RECIEVOR_TEL","recievorTel");
    fieldMap.put("REMARK","remark");
    fieldMap.put("SENDOR","sendor");
    fieldMap.put("SEND_DATE","sendDate");
    fieldMap.put("SEND_ID","sendId");
    fieldMap.put("SEND_TYPE","sendType"); 
    fieldMap.put("WTF_NAME","entrust.entrustor.name");

  }
}

