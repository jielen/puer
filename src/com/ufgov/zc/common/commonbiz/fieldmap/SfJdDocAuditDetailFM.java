package com.ufgov.zc.common.commonbiz.fieldmap;

import java.util.HashMap;
import java.util.Map;

public class SfJdDocAuditDetailFM {


  public static Map fieldMap = new HashMap();

  static {

    fieldMap.putAll(ZcBaseBillFM.fieldMap);

    fieldMap.put("DOC_TYPE_CODE","docType.docTypeCode");
    fieldMap.put("DOC_TYPE_NAME","docType.docTypeName");
    fieldMap.put("JD_DOC_AUDIT_ID","jdDocAuditId");
    fieldMap.put("REMARK","remark");

  }
}
