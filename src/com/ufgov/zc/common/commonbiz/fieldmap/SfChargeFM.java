package com.ufgov.zc.common.commonbiz.fieldmap;

import java.util.HashMap;
import java.util.Map;

public class SfChargeFM {
  public static Map fieldMap = new HashMap();

  static {

    fieldMap.putAll(ZcBaseBillFM.fieldMap);

    fieldMap.put("CASHIER", "cashier");
    fieldMap.put("CASH_DATE", "cashDate");
    fieldMap.put("CHARGE_ID", "chargeId");
    fieldMap.put("ENTRUST_CODE", "entrustCode");
    fieldMap.put("ENTRUST_ID", "entrustId");
    fieldMap.put("INPUTOR", "inputor");
    fieldMap.put("INPUT_DATE", "inputDate");
    fieldMap.put("NAME", "name");
    fieldMap.put("ND", "nd");
    fieldMap.put("PAYER", "payer");
    fieldMap.put("REMARK", "remark");
    fieldMap.put("STATUS", "status");
    fieldMap.put("TOTAL_PRICE", "totalPrice");
    fieldMap.put("PAYER_TEL", "payerTel");

  }
}
