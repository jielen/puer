package com.ufgov.zc.common.commonbiz.fieldmap;

import java.util.HashMap;
import java.util.Map;

public class SfNoticeFm {

	  public static Map fieldMap = new HashMap();

	  static {

	    fieldMap.putAll(ZcBaseBillFM.fieldMap);
	    
	    fieldMap.put("BEFORE_TIMES1","beforeTimes1");
	    fieldMap.put("BEFORE_TIMES2","beforeTimes2");
	    fieldMap.put("BUSINESS_HANDLER","businessHandler");
	    fieldMap.put("NAME","name");
	    fieldMap.put("NOTICE_ID","noticeId");
	    fieldMap.put("RATE1","rate1");
	    fieldMap.put("RATE1_TYPE","rate1Type");
	    fieldMap.put("RATE2","rate2");
	    fieldMap.put("RATE2_TYPE","rate2Type");
	    fieldMap.put("REMARK","remark");
	    fieldMap.put("STATUS","status");
	    fieldMap.put("TIME_UNIT1","timeUnit1");
	    fieldMap.put("TIME_UNIT2","timeUnit2");

	  }
}
