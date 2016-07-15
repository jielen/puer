package com.ufgov.zc.common.commonbiz.fieldmap;

import java.util.HashMap;
import java.util.Map;

public class SfOutInfoDetailFM {

  public static Map fieldMap = new HashMap();
  static {

    fieldMap.putAll(ZcBaseBillFM.fieldMap);
    fieldMap.put("DESCRIPTION", "description");
    fieldMap.put("NAME", "name");
    fieldMap.put("OUT_INFO_DETAIL_ID", "outInfoDetailId");
    fieldMap.put("OUT_INFO_ID", "outInfoId");
    fieldMap.put("OUT_INFO_TYPE_CODE", "infoType.outInfoTypeCode");
    fieldMap.put("OUT_INFO_TYPE_NAME", "infoType.outInfoTypeName");
    fieldMap.put("QUANTITY", "quantity");
    fieldMap.put("TI_QU_FANG_SHI", "tiQuFangShi");
    fieldMap.put("UNIT", "unit");
    fieldMap.put("PRODUCT_TIME", "productTime");

  }
}
