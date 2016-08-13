package com.ufgov.zc.common.commonbiz.fieldmap;

import java.util.HashMap;
import java.util.Map;

public class SfSjFM {

	  public static Map fieldMap = new HashMap();

	  static {

	    fieldMap.putAll(ZcBaseBillFM.fieldMap);
	    
	    fieldMap.put("CO_CODE","coCode");
	    fieldMap.put("HAS_TIAOMA","hasTiaoma");
	    fieldMap.put("NAME","name");
	    fieldMap.put("PACK_SPEC","packSpec");
	    fieldMap.put("PIZHUN_DOC_CODE","pizhunDocCode");
	    fieldMap.put("PRODUCTOR_ID","productor.productorId");
	    fieldMap.put("PRODUCTOR_NAME","productor.name");
	    fieldMap.put("PYM","pym");
	    fieldMap.put("REMARK","remark");
	    fieldMap.put("SJ_ID","sjId");
	    fieldMap.put("STATUS","status");
	    fieldMap.put("STOP_REASON","stopReason");
	    fieldMap.put("STORE_CONDITION","storeCondition");
	    fieldMap.put("STORE_LIMIT_MAX","storeLimitMax");
	    fieldMap.put("STORE_LIMIT_MIN","storeLimitMin");
	    fieldMap.put("TXM","txm");
	    fieldMap.put("UNIT","unit");
	    fieldMap.put("REGIST_CODE","registCode");

	  }
}
