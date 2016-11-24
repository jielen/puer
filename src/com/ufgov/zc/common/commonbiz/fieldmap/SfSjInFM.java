package com.ufgov.zc.common.commonbiz.fieldmap;

import java.util.HashMap;
import java.util.Map;

public class SfSjInFM {

  public static Map fieldMap = new HashMap();

  static {

    fieldMap.putAll(ZcBaseBillFM.fieldMap);

    fieldMap.put("SJ_ID","sj.sjId");  
    fieldMap.put("SJ_NAME","sj.name");  
    
    fieldMap.put("AMOUNT","amount");
    fieldMap.put("BUY_CODE","buyCode");
    fieldMap.put("CO_CODE","coCode");
    fieldMap.put("EXPIRY_DATE","expiryDate");
    fieldMap.put("INPUTOR","inputor");
    fieldMap.put("INVOICE_CODE","invoiceCode");
    fieldMap.put("IN_BILL_CODE","inBillCode");
    fieldMap.put("IN_DATE","inDate");
    fieldMap.put("IN_ID","inId");
    fieldMap.put("ND","nd");
    fieldMap.put("PRICE","price");
    fieldMap.put("SHIJI_PIHAO","shijiPihao"); 
    fieldMap.put("STORE_CODE","storeCode");
    fieldMap.put("SUPPLIER_ID","supplier.supplierId");
    fieldMap.put("TOTAL_NUM","totalNum");
    fieldMap.put("UNIT_CODE","unitCode");


  }
}
