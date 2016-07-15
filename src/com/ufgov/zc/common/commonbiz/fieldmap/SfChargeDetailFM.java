package com.ufgov.zc.common.commonbiz.fieldmap;

import java.util.HashMap;
import java.util.Map;

public class SfChargeDetailFM {

  public static Map fieldMap = new HashMap();

  static {

    fieldMap.putAll(ZcBaseBillFM.fieldMap);
    fieldMap.put("ENTRUST_ID", "entrustId");
    fieldMap.put("CHARGE_STANDARD_ID", "chargeStandardId");
    fieldMap.put("CHARGE_STANDARD_NAME", "chargeStandardName");
    fieldMap.put("PRICE", "price");
    fieldMap.put("PRICE_TYPE", "priceType");
    fieldMap.put("QUANTITY", "quantity");
    fieldMap.put("REMARK", "remark");
    fieldMap.put("TOTAL_PRICE", "totalPrice");

  }
}
