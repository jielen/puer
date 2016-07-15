package com.ufgov.zc.common.commonbiz.fieldmap;

import java.util.HashMap;
import java.util.Map;

public class SfOutInfoValidateDetailFM {

  public static Map fieldMap = new HashMap();
  static {

    fieldMap.putAll(ZcBaseBillFM.fieldMap);
    fieldMap.put("OUT_INFO_ID","outInfoId");
    fieldMap.put("OUT_INFO_REQ_CODE","infoReq.outInfoReqCode");
    fieldMap.put("OUT_INFO_REQ_NAME","infoReq.outInfoReqName");
    fieldMap.put("REMARK","remark");
    fieldMap.put("VALIDATE_RESULT","validateResult");

    
  }
}
