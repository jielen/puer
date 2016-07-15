package com.ufgov.zc.common.commonbiz.fieldmap;

import java.util.HashMap;
import java.util.Map;

public class SfMaterialsTransferDetailFM {

  public static Map fieldMap = new HashMap();

  static {

    fieldMap.putAll(ZcBaseBillFM.fieldMap);
    
    fieldMap.put("MATERIAL_ID","material_id");
    fieldMap.put("OUT_INFO_DETAIL_ID","out_info_detail_id");
    fieldMap.put("PROCESSING","processing");
    fieldMap.put("PROCESSING_DATE","processing_date");
    fieldMap.put("PROCESSING_MAN","processing_man");
    fieldMap.put("QUANTITY","quantity");
    fieldMap.put("REMARK","remark");
    fieldMap.put("TRANSFER_ID","transfer_id");
    fieldMap.put("UNIT","unit");

  }
}
