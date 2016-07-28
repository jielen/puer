package com.ufgov.zc.common.commonbiz.fieldmap;

import java.util.HashMap;
import java.util.Map;

public class SfJdReportFM {
	public static Map fieldMap = new HashMap();

	  static {

	    fieldMap.putAll(ZcBaseBillFM.fieldMap);
	    
	    fieldMap.put("CO_CODE","coCode");
	    fieldMap.put("ENTRUST_ID","entrustId");
	    fieldMap.put("INPUTOR","inputor");
	    fieldMap.put("INPUT_DATE","inputDate");
	    fieldMap.put("JD_REPORT_FILE_ID","jdReportFileId");
	    fieldMap.put("JD_REPOR_ID","jdReporId");
	    fieldMap.put("JD_RESULT_ID","jdResultId");
	    fieldMap.put("NAME","name");
	    fieldMap.put("ND","nd");
	    fieldMap.put("PROCESS_INST_ID","processInstId");
	    fieldMap.put("PUBLISH_TIME","publishTime");
	    fieldMap.put("REMARK","remark");
	    fieldMap.put("REPORT_CODE","reportCode");
	    fieldMap.put("STATUS","status");
	    fieldMap.put("REPORT_TYPE","reportType");

	  }
}
