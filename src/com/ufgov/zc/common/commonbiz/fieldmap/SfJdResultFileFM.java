package com.ufgov.zc.common.commonbiz.fieldmap;

import java.util.HashMap;
import java.util.Map;

public class SfJdResultFileFM {

	  public static Map fieldMap = new HashMap();

	  static {

	    fieldMap.putAll(ZcBaseBillFM.fieldMap);
	    
	    fieldMap.put("FILE_ID","fileId");
	    fieldMap.put("FILE_NAME","fileName");
	    fieldMap.put("JD_RESULT_ID","jdResultId");
	    fieldMap.put("MODEL_ID","modelId");
	    fieldMap.put("NAME","name");
	    fieldMap.put("REMARK","remark");
	    fieldMap.put("SF_JD_RESULT_FILE_ID","sfJdResultFileId");

	  }
}
