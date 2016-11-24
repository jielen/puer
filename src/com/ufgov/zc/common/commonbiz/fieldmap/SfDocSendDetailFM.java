package com.ufgov.zc.common.commonbiz.fieldmap;

import java.util.HashMap;
import java.util.Map;

public class SfDocSendDetailFM {

  public static Map fieldMap = new HashMap();

  static {

    fieldMap.putAll(ZcBaseBillFM.fieldMap);

    fieldMap.put("DOC_NAME","docAuditDetail.docName");
    fieldMap.put("QUANTITY","docAuditDetail.quantity");
    fieldMap.put("REMARK","remark");
    fieldMap.put("SEND_ID","sendId");
    fieldMap.put("UNIT","unit");


  }
}
